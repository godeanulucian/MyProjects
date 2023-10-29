<?php

session_start();
if (!isset($_SESSION['username'])) { // verify a declared and not null variable -> username from actual session
    header("Location: ../pages/index.html");
    exit();
}

// define our variables
$username = $_SESSION['username'];
$topUpSum = $_POST['topUpSum'];

// check empty
if (empty($topUpSum)) {
    echo '<script>alert("Error: All fields are required.")</script>';
    echo '<script>window.location.href="http://localhost/online-banking-system/pages/user.php"</script>';
    exit();
}

// validation
if ($topUpSum <= 1 || $topUpSum > 10000) {
    echo '<script>alert("Error: Top-up sum should be between $1 and $10,000.")</script>';
    echo '<script>window.location.href="http://localhost/online-banking-system/pages/user.php"</script>';
    exit();
}

// db connect
$db = mysqli_connect('127.0.0.1:3307', 'root', '', 'online_banking_system');
if (!$db) {
    die("Connection failed: " . mysqli_connect_error());
}

// get users's amount from db
$users_query = $db->prepare("SELECT amount FROM USERS WHERE username = ?");
$users_query->bind_param("s", $username);
$users_query->execute();
$users_query->bind_result($amount);
$users_query->fetch();
$users_query->close();

// add topUpSum with amount and update database
$newAmount = $amount + $topUpSum;
$users_query = $db->prepare("UPDATE USERS SET amount = ? WHERE username = ?");
$users_query->bind_param("ds", $newAmount, $username);
if ($users_query->execute()) {
    header('location: http://localhost/online-banking-system/pages/user.php');
}
$users_query->close();

mysqli_close($db);

?>