<?php
    $response = array();
    
    // include db connect class
    require_once __DIR__ . '/db_connect.php';
    
    $db = new db_connect();
    $addressId=$_POST['id'];
    $addressFullName = $_POST['address_name'];
    $addressTelephone = $_POST['address_number'];
    $addressLine = $_POST['address_line'];
    $Province = $_POST['province'];
    $District= $_POST['district'];
    $Ward= $_POST['ward'];

    $sql= "UPDATE user_address SET address_name = '$addressFullName', address_number='$addressTelephone'  ,
     address_line='$addressLine' ,province='$Province', district ='$District', ward='$Ward'  WHERE id='$addressId'"; 
    $control = mysqli_query($db->connect(),$sql) or die(mysqli_error());
    if ($control) {
        $response = array();
        $response["success"] = 1;
        $response["message"] = "Cập nhập thành công";
         echo json_encode($response, JSON_UNESCAPED_UNICODE);
    }
     else {
        
        $response["success"] = 2;
        $response["message"] = "Cập nhập thất bại";
         echo json_encode($response, JSON_UNESCAPED_UNICODE);
    }
?>