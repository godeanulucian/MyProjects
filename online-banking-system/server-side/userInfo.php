<?php
session_start();

if (!isset($_SESSION['username'])) {
    // if the user is not logged in, redirect them to the login page
    header("Location: ../pages/index.html");
    exit();
}

$username = $_SESSION['username'];

$connection = new mysqli("127.0.0.1:3307", "root", "", "online_banking_system");

if ($connection->connect_error) {
    die("Connection failed: " . $connection->connect_error);
}

// query to fetch user information based on the username (its unique)
$users_query = $connection->prepare("SELECT fullName, email, otherInfo, cardNumber, amount FROM USERS WHERE username = ?");
$users_query->bind_param("s", $username);
$users_query->execute();
$users_query->bind_result($fullName, $email, $otherInfo, $cardNumber, $amount);
$users_query->fetch();
$users_query->close();

$payments_query = $connection->prepare("SELECT id, sender, receiver, amount, paymentTimestamp FROM PAYMENTS WHERE sender = ? OR receiver = ? ORDER BY paymentTimestamp DESC LIMIT 2");
$payments_query->bind_param("ss", $username, $username);
$payments_query->execute();
$payments_query->bind_result($paymentID, $sender, $receiver, $sum, $paymentTimestamp);
$payments_query->fetch();
$payments_query->close();

$connection->close();
?>