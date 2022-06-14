<?php
 
    $response = array();
    
    require_once __DIR__ . '/db_connect.php';
    
    $db = new db_connect();

    $idCartDetail = $_POST['idCartDetail'];
    
    $result = mysqli_query($db->connect(),"DELETE FROM cart_detail WHERE id = '$idCartDetail'") or die(mysqli_error());
    

    if (mysqli_num_rows($result) > 0) {

        $response = array();
    
        $response["success"] = 1;
        $response["message"] = "Xóa sản phẩm thành công";
    
        echo json_encode($response);

    } else {

        $response["success"] = 0;
        $response["message"] = "Xóa thất bại";

        echo json_encode($response);
    }
?>