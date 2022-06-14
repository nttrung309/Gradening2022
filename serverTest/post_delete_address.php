<?php
    $response = array();
    
    // include db connect class
    require_once __DIR__ . '/db_connect.php';
    
    $db = new db_connect();

    $addressId = $_POST['id'];
   
    $sql = "DELETE FROM user_address WHERE id ='$addressId'";

    $control = mysqli_query($db->connect(), $sql) or die(mysqli_error());
    if (mysqli_num_rows($control) > 0) {
        $response = array();
        $response["success"] = 1;
        $response["message"] = "Xóa thành công";
         echo json_encode($response);
    }
     else {
        
        $response["success"] = 2;
        $response["message"] = "Xóa thất bại";
         echo json_encode($response);
    }
?>