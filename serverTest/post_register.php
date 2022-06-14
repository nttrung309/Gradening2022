<?php
    $response = array();
    
    
    require_once __DIR__ . '/db_connect.php';
    // include db connect class
        
    // connecting to db
    $db = new db_connect();

    $userName = $_POST['username'];
    $userPassword = $_POST['password'];
    $userEmail=$_POST['email'];
    // $userName = "nguyenvanc";
    // $userPassword = "1";
    // $userEmail="nguyenvanc@gmail.com";
    $userNameName = "no_name";
    $userdate = "2001-01-01";
    $usergender="Other";
    $usergtelephone="0123456789";
    $query = mysqli_query($db->connect(),"SELECT * FROM users WHERE username = '$userName'");
    
    if (mysqli_num_rows($query) > 0) {
        $response = array();
        $response["success"] = 0;
        $response["message"] = "Exists username";
         echo json_encode($response, JSON_UNESCAPED_UNICODE);
    } else {
        
        $query1 = mysqli_query($db->connect(),"SELECT * FROM users WHERE email = '$userEmail'");
        
        if (mysqli_num_rows($query1) > 0) {
            $response = array();
            $response["success"] = 0;
            $response["message"] = "Exists email";
             echo json_encode($response, JSON_UNESCAPED_UNICODE);
        } else {
            
           $control = mysqli_query($db->connect(),"INSERT INTO users(username,password,name,date,gender,telephone,email) VALUES ('$userName','$userPassword','$userNameName','$userdate','$usergender','$usergtelephone','$userEmail')");
           
            if ($control) {
                $response = array();
                $response["success"] = 1;
                $response["message"] = "Success";
                echo json_encode($response, JSON_UNESCAPED_UNICODE);
            }
            else {
                $response = array();
                $response["success"] = 0;
                $response["message"] = "Failed";
                echo json_encode($response, JSON_UNESCAPED_UNICODE);
            }
        }
    }
?>