<?php

require_once('./config/connection.php');

if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    $nama = $_POST["nama"];
    $nomor = $_POST["nomor"];
    $sponsor = $_POST["sponsor"];
    $negara = $_POST["negara"];

    $query = "INSERT INTO riders(nama, nomor, sponsor, negara) VALUES('$nama', '$nomor', '$sponsor', '$negara')";
    $execute = mysqli_query($conn, $query);
    $check = mysqli_affected_rows($conn);

    if ($check > 0) {
        $response["code"] = 1;
        $response["message"] = "Success to save data!";
    } else {
        $response["code"] = 0;
        $response["message"] = "Failed to save data!";
    }
} else {
    $response["code"] = 0;
    $response["message"] = "Here must be POST method!";
}

echo json_encode($response);
mysqli_close($conn);
