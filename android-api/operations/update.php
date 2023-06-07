<?php

require_once('../configuration/connection.php');

$response = array();

if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    $id = $_POST["id"];
    $nama = $_POST["nama"];
    $nomor = $_POST["nomor"];
    $sponsor = $_POST["sponsor"];
    $negara = $_POST["negara"];

    $query = "UPDATE riders SET nama = '$nama', nomor = '$nomor', sponsor = '$sponsor', negara = '$negara' WHERE id = '$id'";
    $execute = mysqli_query($conn, $query);

    $check = mysqli_affected_rows($conn);
    if ($check > 0) {
        $response["status"] = 1;
        $response["message"] = "Success to update data!";
    } else {
        $response["status"] = 0;
        $response["message"] = "Failed to update data!";
    }
} else {
    $response["status"] = 0;
    $response["message"] = "Here must be POST method!";
}

echo json_encode($response);
mysqli_close($conn);
