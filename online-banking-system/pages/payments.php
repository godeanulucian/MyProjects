<!DOCTYPE html>
<html>

<head>
    <style>
        table {
            border-collapse: collapse;
            width: 70%;
            border-radius: 20px;
            overflow: hidden;
        }

        th,
        td {
            border: 1px solid #0078d4;
            padding: 8px;
            text-align: center;
        }

        th {
            background-color: #0078d4;
            color: #fff;
        }

        tr:nth-child(even) {
            background-color: #f2f2f2;
        }

        #generatePdfButton {
            position: absolute;
            top: 10px;
            right: 10px;
            background-color: #0078d4;
            color: #fff;
            padding: 6px 6px;
            border: none;
            cursor: pointer;
            border-radius: 20px;
        }
    </style>
</head>

<body>
    <center>
        <table id="PaymentsTable">
            <tr>
                <th>Transaction ID</th>
                <th>Sender</th>
                <th>Receiver</th>
                <th>Sum</th>
                <th>Timestamp</th>
            </tr>
            <?php
            session_start();

            if (!isset($_SESSION['username'])) {
                // if the user is not logged in, redirect them to the login page
                header("Location: ../pages/index.html");
                exit();
            }

            // fetch user information based on the username stored in the session variable
            $username = $_SESSION['username'];

            $connection = new mysqli("127.0.0.1:3307", "root", "", "online_banking_system");

            if ($connection->connect_error) {
                die("Connection failed: " . $connection->connect_error);
            }

            $payments_query = $connection->prepare("SELECT id, sender, receiver, amount, paymentTimestamp FROM PAYMENTS WHERE sender = ? OR receiver = ? ORDER BY paymentTimestamp DESC");
            $payments_query->bind_param("ss", $username, $username);
            $payments_query->execute();
            $payments_query->bind_result($paymentID, $sender, $receiver, $sum, $paymentTimestamp);

            while ($payments_query->fetch()) {
                ?>
                <tr style="text-align: center;">
                    <td>
                        <center>
                            <?php echo $paymentID; ?>
                        </center>
                    </td>
                    <td>
                        <?php echo $sender; ?>
                    </td>
                    <td>
                        <?php echo $receiver; ?>
                    </td>
                    <td>$
                        <?php
                        if ($sender == $username)
                            echo -$sum;
                        else
                            echo +$sum;
                        ?><br>
                    </td>
                    <td>
                        <?php echo $paymentTimestamp; ?>
                    </td>
                </tr>
                <?php
            }

            $payments_query->close();

            $connection->close();
            ?>
        </table>
    <center>
    <button id="generatePdfButton" style="font-size: 10px;"><b>Generate PDF</b></button>
</body>

<footer>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
        $(document).ready(function () {
            $('#generatePdfButton').click(function () {
                $.ajax({
                    type: "GET",
                    url: "../scripts/generate_pdf.php",
                    success: function (response) {
                        // handle the response
                        alert("PDF generated successfully!");
                    }
                });
            });
        });
    </script>

</footer>

</html>