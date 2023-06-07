<?php

require_once('../configuration/connection.php');

if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    $name = $_POST["name"];
    $email = $_POST["email"];
    $password = $_POST["password"];

    $query = "INSERT INTO users(name, email, password)
        VALUES('$name', '$email', '$password')";
    $execute = mysqli_query($conn, $query);

    $check = mysqli_affected_rows($conn);
    if ($check > 0) {
        $response["status"] = 1;
        $response["message"] = "Success to insert user!";
    } else {
        $response["status"] = 0;
        $response["message"] = "Failed to insert user!";
    }
} else {
    $response["status"] = 0;
    $response["message"] = "Here must be POST method!";
}

echo json_encode($response);
mysqli_close($conn);
