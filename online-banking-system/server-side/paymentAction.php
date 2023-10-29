<?php
session_start();
if (!isset($_SESSION['username'])) {
    header("Location: ../pages/index.html");
    exit();
}

$username = $_SESSION['username'];
$receiver = $_POST['receiver']; // input receiver's username
$transferSum = $_POST['transferSum'];

// validation
if ($username == $receiver) {
    echo '<script>alert("You can not transfer money to yourself, you got topUp function for that!")</script>';
    echo '<script>window.location.href="http://localhost/online-banking-system/pages/user.php"</script>';
    exit();
}
if ($transferSum < 1 || $transferSum > 100000) {
    echo '<script>alert("Error: Transfer sum should be between $1 and $100,000.")</script>';
    echo '<script>window.location.href="http://localhost/online-banking-system/pages/user.php"</script>';
    exit();
}

// check empty
if (empty($receiver) || empty($transferSum)) {
    echo '<script>alert("Error: All fields are required.")</script>';
    echo '<script>window.location.href="http://localhost/online-banking-system/pages/user.php"</script>';
    exit();
}

// db connect
$db = mysqli_connect('127.0.0.1:3307', 'root', '', 'online_banking_system');
if (!$db) {
    die("Connection failed: " . mysqli_connect_error());
}

// check if receiver exists
$stmt = $db->prepare("SELECT * FROM USERS WHERE username = ?");
$stmt->bind_param("s", $receiver);
$stmt->execute();
$result = $stmt->get_result();
if (mysqli_num_rows($result) <= 0) {
    echo '<script>alert("Error: Receiver with this username does not exist. Please choose a different receiver.")</script>';
    echo '<script>window.location.href="http://localhost/online-banking-system/pages/user.php"</script>';
    $stmt->close();
    mysqli_close($db);
    exit();
}

// get users's amount from db
$users_query = $db->prepare("SELECT amount FROM USERS WHERE username = ?");
$users_query->bind_param("s", $username);
$users_query->execute();
$users_query->bind_result($amount);
$users_query->fetch();
$users_query->close();

// substract transferSum from user's amount and update database
$newAmount = $amount - $transferSum;
$users_query = $db->prepare("UPDATE USERS SET amount = ? WHERE username = ?");
$users_query->bind_param("ds", $newAmount, $username);
if ($users_query->execute()) {
    header('location: http://localhost/online-banking-system/pages/user.php');
}
$users_query->close();

// get receiver's amount from db
$users_query = $db->prepare("SELECT amount FROM USERS WHERE username = ?");
$users_query->bind_param("s", $receiver);
$users_query->execute();
$users_query->bind_result($amount);
$users_query->fetch();
$users_query->close();

// add transferSum with amount and update database
$newAmount = $amount + $transferSum;
$users_query = $db->prepare("UPDATE USERS SET amount = ? WHERE username = ?");
$users_query->bind_param("ds", $newAmount, $receiver);
if ($users_query->execute()) {
    header('location: http://localhost/online-banking-system/pages/user.php');
}
$users_query->close();

// add transfer to payment database
$sender = $username;
$payments_query = $db->prepare("INSERT INTO PAYMENTS(sender, receiver, amount) VALUES(?, ?, ?)");
$payments_query->bind_param("ssd", $sender, $receiver, $transferSum);
if ($payments_query->execute()) {
    header('location: http://localhost/online-banking-system/pages/user.php');
}
$payments_query->close();

// get receiver's email
$users_query = $db->prepare("SELECT email FROM USERS WHERE username = ?");
$users_query->bind_param("s", $receiver);
$users_query->execute();
$users_query->bind_result($receiverEmail);
$users_query->fetch();
$users_query->close();

echo '<script>alert("go go")</script>';
// function to send payment notification to receiver -----------------------------------------------------------------------
function sendPaymentNotificationEmail($receiverEmail, $transferSum, $username)
{
    $subject = "Payment Notification";
    $message = "$username has sent you $$transferSum";

    // set up the email headers
    $headers = "From: legendarugodi@gmail.com"; // let's say that this is the company's address
    $headers .= "MIME-Version: 1.0\r\n";
    $headers .= "Content-type: text/html; charset=iso-8859-1\r\n";
    // send the email
    $success = mail($receiverEmail, $subject, $message, $headers);

    if ($success) {
        echo "Payment notification email sent successfully.";
        echo '<script>alert("ok")</script>';
    } else {
        echo "Failed to send the payment notification email.";
        echo '<script>alert("NOT ok")</script>';
        exit();
    }
}
echo '<script>alert("go go")</script>';
sendPaymentNotificationEmail($receiverEmail, $transferSum, $username);

mysqli_close($db);

?>