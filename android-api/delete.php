<?php

require_once('./config/connection.php');

$response = array();

if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    $id = $_POST["id"];

    $query = "DELETE FROM riders WHERE id = '$id'";
    $execute = mysqli_query($conn, $query);
    $check = mysqli_affected_rows($conn);

    if ($check > 0) {
        $response["code"] = 1;
        $response["message"] = "Success to delete data!";
    } else {
        $response["code"] = 0;
        $response["message"] = "Failed to delete data!";
    }
} else {
    $response["code"] = 0;
    $response["message"] = "Here must be POST method!";
}

echo json_encode($response);
mysqli_close($conn);
