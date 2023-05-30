<?php

require_once('./config/connection.php');

if ($_SERVER['REQUEST_METHOD'] == 'GET') {
    $query = "SELECT * FROM riders ORDER BY created_at DESC";
    $execute = mysqli_query($conn, $query);
    $check = mysqli_affected_rows($conn);

    if ($check > 0) {
        $response["code"] = 1;
        $response["message"] = "Data is available";
        $response["data"] = array();

        while ($get = mysqli_fetch_object($execute)) {
            $var["id"] = $get->id;
            $var["nama"] = $get->nama;
            $var["nomor"] = $get->nomor;
            $var["sponsor"] = $get->sponsor;
            $var["negara"] = $get->negara;
            $var["foto"] = $get->foto;
            $var["created_at"] = $get->created_at;
            $var["updated_at"] = $get->updated_at;

            array_push($response["data"], $var);
        }
    } else {
        $response["code"] = 0;
        $response["message"] = "Oops! Data is not available!";
    }
}
echo json_encode($response);
mysqli_close($conn);
