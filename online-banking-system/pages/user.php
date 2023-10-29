<?php
include '../server-side/userInfo.php';
?>

<!DOCTYPE html>
<html>

<head>

    <link rel="stylesheet" type="text/css" href="../styles/style.css">

</head>

<body
    style="background-image: url('../images/mainFramePic.jpg'); background-position: right; background-size: contain; background-repeat: no-repeat;">
    <div class="MainFrame">
        <div class="UserDiv">

            <p id="fullName" style="text-align: center;"><b>
                    <?php echo $fullName; ?>
                </b></p>
            <hr style="color: #0078d4; border: 0.3px solid #ffffff; width: 180 px;">

            <button class="UserButtons" id="personalInfoBtn" style="width: 150px;">Personal Information</button>
            <div id="personalInfo" class="hidden info" style="margin-left: 40px; text-align: left;">
                <!-- Personal Information Content -->
                <b>User:</b>
                <?php echo $username; ?> <br>
                <b>Email:</b>
                <?php echo $email; ?><br> <!--email display sometimes bugging-->
                <b>Other Information:</b>
                <?php echo $otherInfo; ?><br>

            </div>

            <button class="UserButtons" id="cardInfoBtn" style="width: 150px;">Card Information</button>
            <div id="cardInfo" class="hidden info" style="margin-left: 40px; text-align: left;">
                <!-- Card Information Content -->
                <b> Card Number:</b>
                <?php echo $cardNumber; ?><br>
            </div>

            <button class="UserButtons" id="paymentsBtn" style="width: 150px;">Payments</button>
            <div id="payments" class="hidden info" style="margin-left: 40px; text-align: left;">
                <!-- Payments Content -->
                <b>Transaction ID:</b>
                <?php echo $paymentID; ?><br>
                <b>Sender:</b>
                <?php echo $sender; ?><br>
                <b>Receiver:</b>
                <?php echo $receiver; ?><br>
                <b>Sum:</b>
                $
                <?php echo $sum; ?><br>
                <b>Timestamp:</b>
                <?php echo $paymentTimestamp; ?><br>
                <a href="../pages/payments.php" target="_blank">View all payments</a><br>
            </div>
        </div>

        <div class="AmountDiv">
            <p id="amount" style="text-align: center;"><b>Amount: </b>$
                <?php echo $amount; ?>
            </p>

            <hr style="color: #0078d4; border: 1.2px solid #ffffff;">

            <form id="addMoneyForm" action="../server-side/topUpAction.php" method="POST"
                style="float: left; margin-top: 20px; margin-left: 10px;">
                <center><b>Top-Up Balance</b><br></center>
                <br><label for="topUpSum">Sum: </label>
                <input type="text" id="topUpSum" name="topUpSum" required style="width: 100px;"> <br><br>

                <center><button type="submit" class="UserButtons" id="addMoneyBtn" style="float: none;">Add
                        Money</button></center>
            </form>

            <form id="sendMoneyForm" action="../server-side/paymentAction.php" method="POST"
                style="float: right; margin-top: 20px; margin-right: 10px;">
                <center><b>Transfer founds</b><br></center>
                <br><label for="receiver">Receiver: </label>
                <input type="text" id="receiver" name="receiver" required style="width: 100px; float: right;"> <br><br>

                <label for="transferSum">Sum: </label>
                <input type="text" id="transferSum" name="transferSum" required style="width: 100px; float: right;">
                <br><br>

                <center><button type="submit" class="UserButtons" id="sendMoneyBtn" style="float: none;">Send
                        Money</button></center>
            </form>
        </div>
    </div>

    <footer>
        <form id="logoutForm" action="../server-side/logout.php" method="POST">
            <p>&copy; 2023 Online Banking <button class="UserButtons" id="logoutBtn"
                    style="float: none; margin-left: 900px; box-shadow: 5px 5px 15px rgba(0, 0, 0, 0.3);">Logout</button>
            </p>

        </form>
    </footer>

    <script src="../scripts/script.js"></script>

</body>

</html>