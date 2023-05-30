<?php
$server = "127.0.0.1";
$user = "root";
$password = "";
$database = "bwamysql";

$conn = mysqli_connect($server, $user, $password, $database);

if (mysqli_connect_errno()) {
    echo "Failed to connect: " . mysqli_connect_error();
}
