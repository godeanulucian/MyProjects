<?php
session_start();

// unset all session variables
$_SESSION = array();

session_destroy();

// redirect to user.php
header("Location: http://localhost/online-banking-system/pages/user.php");
exit();
?>