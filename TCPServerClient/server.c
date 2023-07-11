#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <dirent.h> //  provides functions and structures for directory operations
#include <sys/socket.h> //  provides the necessary definitions and structures for socket programming
#include <netinet/in.h> //  includes definitions and structures related to ip's
#include <arpa/inet.h> // provides functions for manipulating IP addresses and converting between different formats
#include <unistd.h> // provides access to various POSIX operating system API functions
#include <sys/types.h>
#include <sys/stat.h>
#define SERVER_DIRECTORY "/home/lucian/www/rc/server_dir/"
#define BUFFER_SIZE 1024


// list files to client
void list_files(int client_socket) {
    // declare variables we need to work with directories
    DIR *dir;
    struct dirent *ent;
    struct stat file_stat;

    // open server directory
    if ((dir = opendir(SERVER_DIRECTORY)) != NULL) {
        //printf("\ndir ok"); // test
        while ((ent = readdir(dir)) != NULL) {
            char file_path[256];
            // format SERVER_DIRECTORY string to file_path buffer 
            // concat ent serv path with entry to form absolute path
            snprintf(file_path, sizeof(file_path), "%s/%s", SERVER_DIRECTORY, ent->d_name);

            // check if the entry is a regular file
            if (stat(file_path, &file_stat) == 0 && S_ISREG(file_stat.st_mode)) {
                // send the file name(buffer) to the client
                send(client_socket, ent->d_name, strlen(ent->d_name), 0);
                send(client_socket, "\n", 1, 0); // signal end of file
            }
        }
        //printf("\ndata sent"); // test
        closedir(dir);
    } else {
        perror("Failed to open directory");
        send(client_socket, "\0", 1, 0); // signal end of command execution
        return;
    }

    // send single null chars to mark end of file and command
    send(client_socket, "\0", 1, 0); // signal end of file list
    send(client_socket, "\0", 1, 0); // signal end of command execution
    printf("\nSent list of files to client!");
}

// send file to client
void send_file(int client_socket, const char* filename) {
    char file_path[256];
    // format SERVER_DIRECTORY string to file_path buffer 
    // concat file path wit filename
    snprintf(file_path, sizeof(file_path), "%s/%s", SERVER_DIRECTORY, filename);

    // read file in binary mode 
    FILE* file = fopen(file_path, "rb");
    if (file == NULL) {
        perror("Failed to open file");
        send(client_socket, "\0", 1, 0); // signal end of command execution
        return;
    }
    //printf("\nfile ok open"); // test

    char buffer[BUFFER_SIZE];
    ssize_t bytes_read;

    // read and send the file data in chunks
    while ((bytes_read = fread(buffer, sizeof(char), BUFFER_SIZE, file)) > 0) {
        ssize_t bytes_sent = send(client_socket, buffer, bytes_read, 0);
        //printf("\nsent"); // test
        if (bytes_sent < 0) {
            perror("Failed to send file");
            break;
        }
    }

    fclose(file); // close file
    send(client_socket, "\0", 1, 0); // signal end of command execution
    printf("File sent: %s\n", filename);
}



int main() {
    int server_socket, client_socket;
    struct sockaddr_in server_address, client_address;
    socklen_t client_address_length;
    char buffer[BUFFER_SIZE];

    /*
                AF_UNIX AF_INET
    SOCK_STREAM    Da     TCP
    SOCK_DGRAM     Da     UDP
    SOCK_RAW              IP
    */

    // we use only one process because the server is iterative

    // create socket
    server_socket = socket(AF_INET, SOCK_STREAM, 0); // set as internet protocol, tcp communication
    if (server_socket == -1) {
        perror("Socket creation failed");
        exit(EXIT_FAILURE);
    }

    // set server address
    server_address.sin_family = AF_INET;
    // listens to all network interference existing on the system
    server_address.sin_addr.s_addr = INADDR_ANY; // localhost in our case
    // convert 16 bit host format (port 8888) to nbo
    server_address.sin_port = htons(8888);

    // bind socket to address
    if (bind(server_socket, (struct sockaddr *)&server_address, sizeof(server_address)) == -1) {
        perror("Bind failed");
        exit(EXIT_FAILURE);
    }

    // listen for connections
    if (listen(server_socket, 1) == -1) {
        perror("Listen failed");
        exit(EXIT_FAILURE);
    }

    printf("\nServer listening on 127.0.0.1:8888...\n");

    while (1) {
        // accept client connection so we create the new socket for client
        client_address_length = sizeof(client_address);
        client_socket = accept(server_socket, (struct sockaddr *)&client_address, &client_address_length);
        if (client_socket == -1) {
            perror("Accept failed");
            exit(EXIT_FAILURE);
        }

        // convert an IPv4 address from binary form to a string and convert nbo to host
        printf("Client connected: %s:%d\n", inet_ntoa(client_address.sin_addr), ntohs(client_address.sin_port));

        while (1) {
            // Receive client command
            int command_length = recv(client_socket, buffer, sizeof(buffer), 0);
            if (command_length <= 0)
                break;

            // assigns the null character to cmd length position to respect c convention
            buffer[command_length] = '\0';

            // Process client command
            if (strcmp(buffer, "ls") == 0) {
                list_files(client_socket);
            } else if (strncmp(buffer, "get", 3) == 0) { // compare the first 3 characters
                char filename[256];
                strcpy(filename, buffer + 4); // add 4 to skip "get " prefix
                send_file(client_socket, filename);
            }
        }

        close(client_socket); // close connection with client
        printf("\nClient disconnected\n");
    }

    close(server_socket); // close server connection

    return 0;
}
