<?php

$host = "127.0.0.1";
$user = "root";
$password = "";
$database = "android";

$conn = mysqli_connect($host, $user, $password, $database);

if (mysqli_connect_errno()) {
    echo "Failed to connect: " . mysqli_connect_error();
}
