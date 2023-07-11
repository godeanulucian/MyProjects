#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/socket.h> //  provides the necessary definitions and structures for socket programming
#include <netinet/in.h> //  includes definitions and structures related to ip's
#include <arpa/inet.h> // provides functions for manipulating IP addresses and converting between different formats
#include <unistd.h> // provides access to various POSIX operating system API functions
#define BUFFER_SIZE 1024

// list files from server
void list_files(int server_socket) {
    char buffer[BUFFER_SIZE];

    // send command ls to trigger server to list files
    send(server_socket, "ls", 2, 0);

    while (1) {
        // receive data from server
        int bytes_received = recv(server_socket, buffer, sizeof(buffer) - 1, 0);
        if (bytes_received <= 0) {
            // printf("\nls failed1"); // test
            break;
        }

        buffer[bytes_received] = '\0';

        // compare to null
        if (strcmp(buffer, "\0") == 0){
            // printf("\nls failed2"); // test
            break;
        }
        
        // print data stored in buffer to console
        printf("%s", buffer);
    }
}

// get file from server
void get_file(int server_socket, const char* filename) {
    // send filename to trigger server to send files
    send(server_socket, filename, strlen(filename), 0);
    
    char buffer[BUFFER_SIZE];
    ssize_t bytes_received;

    // write to file in binary mode
    FILE* file = fopen(filename, "wb");
    if (file == NULL) {
        perror("Failed to open file");
        return;
    }
    //printf("\nfile open ok"); // test

    // read the file data in chunks
    while ((bytes_received = recv(server_socket, buffer, BUFFER_SIZE, 0)) > 0) {
        if (fwrite(buffer, sizeof(char), bytes_received, file) != bytes_received) {
            perror("Failed to write to file");
            break;
        }
    }

    fclose(file);
    printf("Received file: %s\n", filename);
}



int main() {
    int client_socket;
    struct sockaddr_in server_address;
    char buffer[BUFFER_SIZE];
    const char *server_ip = "127.0.0.1"; // localhost
    int server_port = 8888;

    // create socket
    client_socket = socket(AF_INET, SOCK_STREAM, 0); // set as internet protocol, tcp communication
    if (client_socket == -1) {
        perror("Socket creation failed");
        exit(EXIT_FAILURE);
    }

    // Set server address
    server_address.sin_family = AF_INET;
    // listens to specified network (localhost)
    server_address.sin_addr.s_addr = inet_addr(server_ip);
    // convert 16 bit host format (port 8888) to nbo
    server_address.sin_port = htons(server_port);

    // Connect to the server
    if (connect(client_socket, (struct sockaddr *)&server_address, sizeof(server_address)) == -1) {
        perror("Connection failed");
        exit(EXIT_FAILURE);
    }

    while (1) {
        printf("\nEnter command (ls, get): ");
        fgets(buffer, sizeof(buffer), stdin); // read text from buffer
        buffer[strcspn(buffer, "\n")] = '\0'; // return nr of chars till it matches "\n"

        if (strcmp(buffer, "ls") == 0) {
            list_files(client_socket);
        } else if (strncmp(buffer, "get", 3) == 0) {
            const char *filename = buffer + 4; // Skip "get " prefix
            get_file(client_socket, filename);
            //printf("\nget done"); // test
        } else {
            printf("Invalid command\n");
        }
    }

    close(client_socket); // close client connection

    return 0;
}
