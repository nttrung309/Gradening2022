<?php
    $response = array();
    
    // include db connect class
    require_once __DIR__ . '/db_connect.php';
    
    $db = new db_connect();

    $userId = $_POST['idUser'];
    $userFullName = $_POST['name'];
    $userDate = $_POST['date'];
    $userGender = $_POST['gender'];
    $userTelephone = $_POST['telephone'];
    $userMail= $_POST['email'];
    
    $sql= "UPDATE users SET name = '$userFullName' , date='$userDate'  ,
     gender='$userGender' ,telephone='$userTelephone', email ='$userMail'  WHERE id='$userId'"; 
    $control = mysqli_query($db->connect(),$sql) or die(mysqli_error());
    if ($control) {
        $response = array();
        $response["success"] = 1;
        $response["message"] = "Cập nhập thành công";
         echo json_encode($response, JSON_UNESCAPED_UNICODE);
    }
     else {
        
        $response["success"] = 2;
        $response["message"] = "Nhập nhập thất bại";
         echo json_encode($response, JSON_UNESCAPED_UNICODE);
    }
?>