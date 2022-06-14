<?php
    $response = array();
    
    // include db connect class
    require_once __DIR__ . '/db_connect.php';
    
    $db = new db_connect();

    $userId = $_POST['idUser'];
    $addressFullName = $_POST['address_name'];
    $addressTelephone = $_POST['address_number'];
    $addressLine = $_POST['address_line'];
    $Province = $_POST['province'];
    $District= $_POST['district'];
    $Ward= $_POST['ward'];
    // $userId = "6";
    // $addressFullName = "nguyen van c";
    // $addressTelephone = "0783249260";
    // $addressLine = "Hill street";
    // $Province = "QN";
    // $District= "DX";
    // $Ward= "DP";
    $sql = mysqli_query($db->connect(),
    "INSERT INTO user_address (idUser,address_name,address_number,address_line,province,district,ward) VALUES
    ('$userId','$addressFullName','$addressTelephone','$addressLine','$Province','$District','$Ward')");

    if ($sql) {
        $response = array();
        $response["success"] = 1;
        $response["message"] = "Tạo mới thành công";
         echo json_encode($response, JSON_UNESCAPED_UNICODE);
    }
     else {
        
        $response["success"] = 2;
        $response["message"] = "Tạo mới thất bại";
         echo json_encode($response, JSON_UNESCAPED_UNICODE);
    }
?>