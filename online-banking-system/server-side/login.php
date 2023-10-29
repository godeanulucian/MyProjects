<?php

$conn = new mysqli("127.0.0.1:3307", "root", "", "online_banking_system");

if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

$username = $_POST["username"];
$password = $_POST["password"];

session_start();

// prepare and execute a SQL query to check the login
$sql = "SELECT * FROM USERS WHERE username = '$username'";
$result = $conn->query($sql);

if ($result->num_rows > 0) {
    $row = $result->fetch_assoc();
    if (password_verify($password, $row["password"])) {
        echo "Login successful!";
        $_SESSION['username'] = $_POST['username'];

        echo '<script>window.location.href="http://localhost/online-banking-system/pages/user.php"</script>';
    } else {
        // echo "Invalid password.";
        echo '<script>alert("Error: Invalid password.")</script>';
        echo '<script>window.location.href="http://localhost/online-banking-system/pages/index.html"</script>';
    }
} else {
    // echo "User not found.";
    echo '<script>alert("Error: User not found.")</script>';
    echo '<script>window.location.href="http://localhost/online-banking-system/pages/index.html"</script>';
}

$conn->close();

?>