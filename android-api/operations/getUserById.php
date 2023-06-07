<?php

require_once('../configuration/connection.php');

if ($_SERVER['REQUEST_METHOD'] == 'GET') {
    $id = $_GET["id"];
    $query = "SELECT * FROM users WHERE id = '$id'";
    $execute = mysqli_query($conn, $query);

    $check = mysqli_affected_rows($conn);
    if ($check > 0) {
        $response["status"] = 1;
        $response["message"] = "Data is available!";
        $response["data"] = array();

        while ($get = mysqli_fetch_object($execute)) {
            $data["id"] = $get->id;
            $data["name"] = $get->name;
            $data["email"] = $get->email;
            $data["password"] = $get->password;
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
