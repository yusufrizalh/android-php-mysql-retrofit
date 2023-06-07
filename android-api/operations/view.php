<?php

require_once('../configuration/connection.php');

if ($_SERVER['REQUEST_METHOD'] == 'GET') {
    $query = "SELECT * FROM riders ORDER BY created_at DESC";
    $execute = mysqli_query($conn, $query);

    $check = mysqli_affected_rows($conn);
    if ($check > 0) {
        $response["status"] = 1;
        $response["message"] = "Data is available!";
        $response["data"] = array();

        while ($get = mysqli_fetch_object($execute)) {
            $data["id"] = $get->id;
            $data["nama"] = $get->nama;
            $data["nomor"] = $get->nomor;
            $data["sponsor"] = $get->sponsor;
            $data["negara"] = $get->negara;
            $data["foto"] = $get->foto;
            $data["created_at"] = $get->created_at;
            $data["updated_at"] = $get->updated_at;
            array_push($response["data"], $data);
        }
    } else {
        $response["status"] = 0;
        $response["message"] = "Oops! Data is not available!";
    }
}

echo json_encode($response);
mysqli_close($conn);
