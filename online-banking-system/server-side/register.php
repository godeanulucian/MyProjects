<?php

$newUsername = $_POST['newUsername'];
$newPassword = $_POST['newPassword'];
$email = $_POST['email'];

$fullName = $_POST['fullName'];
$cardNumber = $_POST['cardNumber'];
$amount = $_POST['amount'];
$otherInfo = $_POST['otherInfo'];

// check if all required fields are filled
if (empty($newUsername) || empty($email) || empty($newPassword) || empty($fullName) || empty($cardNumber)) {
    echo '<script>alert("Error: All fields are required.")</script>';
    // redirect back to the form page
    echo '<script>window.location.href="http://localhost/online-banking-system/pages/index.html"</script>';
    exit();
}

if (preg_match('/^\d{16}$/', $cardNumber)) { // only 16 digits, only numbers
    echo "Card number is valid.";
} else {
    echo '<script>alert("Error: Invalid card number.")</script>';
    echo '<script>window.location.href="http://localhost/online-banking-system/pages/index.html"</script>';
    exit();
}

// connect to the database
$db = mysqli_connect('127.0.0.1:3307', 'root', '', 'online_banking_system');

if (!$db) {
    die("Connection failed: " . mysqli_connect_error());
}

// check if the username already exists
$stmt = $db->prepare("SELECT * FROM USERS WHERE username = ?");
$stmt->bind_param("s", $newUsername);
$stmt->execute();
$result = $stmt->get_result();

if (mysqli_num_rows($result) > 0) {
    echo '<script>alert("Error: Username already exists. Please choose a different username.")</script>';
    echo '<script>window.location.href="http://localhost/online-banking-system/pages/index.html"</script>';
    $stmt->close();
    mysqli_close($db);
    exit();
}

// check if the email already exists
$stmt = $db->prepare("SELECT * FROM USERS WHERE email = ?");
$stmt->bind_param("s", $email);
$stmt->execute();
$result = $stmt->get_result();

if (mysqli_num_rows($result) > 0) {
    echo '<script>alert("Error: Email already exists. Please use a different email address.")</script>';
    echo '<script>window.location.href="http://localhost/online-banking-system/pages/index.html"</script>';
    $stmt->close();
    mysqli_close($db);
    exit();
}

// check if the cardNumber already exists
$stmt = $db->prepare("SELECT * FROM USERS WHERE cardNumber = ?");
$stmt->bind_param("s", $cardNumber);
$stmt->execute();
$result = $stmt->get_result();

if (mysqli_num_rows($result) > 0) {
    echo '<script>alert("Error: Card number already exists. Please use a different card number.")</script>';
    echo '<script>window.location.href="http://localhost/online-banking-system/pages/index.html"</script>';
    $stmt->close();
    mysqli_close($db);
    exit();
}

$hashedPassword = password_hash($newPassword, PASSWORD_BCRYPT); // apply a php encryption algorithm for the password
// prepare and execute the query to add the new user to the database
$stmt = $db->prepare("INSERT INTO USERS (username, password, email, fullName, cardNumber, amount, otherInfo) VALUES (?, ?, ?, ?, ?, ?, ?)");
$stmt->bind_param("sssssds", $newUsername, $hashedPassword, $email, $fullName, $cardNumber, $amount, $otherInfo);
$stmt->execute();

// check if the query was executed successfully
if ($stmt->affected_rows == 1) {
    echo '<script>alert("Registration completed successfully")</script>';
    echo '<script>window.location.href="http://localhost/online-banking-system/pages/index.html"</script>';
} else {
    echo '<script>alert("Error: Could not register the user. Please try again later.")</script>';
    echo '<script>window.location.href="http://localhost/online-banking-system/pages/index.html"</script>';
}

$stmt->close();
mysqli_close($db);
?>