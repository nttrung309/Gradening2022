<?php

    /*
    * Following code will list all the products
    */
    // array for JSON response
    $response = array();
    
    // include db connect class
    require_once __DIR__ . '/db_connect.php';
        
    // connecting to db
    $db = new db_connect();

    $userMail = $_POST['username'];
    $userPassword = $_POST['password'];

    $result = mysqli_query($db->connect(),"SELECT * FROM users WHERE email = '$userMail' AND password = '$userPassword'") or die(mysqli_error());

    if (mysqli_num_rows($result) > 0) {
        // looping through all results
        // products node
        $response = array();
    
        while ($row = mysqli_fetch_array($result)) {
            // temp user array
            $user = array();
            $user["id"] = $row["id"];
            $user["username"] = $row["username"];
            $user["password"] = $row["password"];
            $user["name"] = $row["name"];
            $user["date"] = $row["date"];
            $user["gender"] = $row["gender"];
            $user["telephone"] = $row["telephone"];
            $user["email"] = $row["email"];
            // push single product into final response array
            array_push($response, $user);
        }
        echo json_encode($response, JSON_UNESCAPED_UNICODE);
    } else {
        echo json_encode($response, JSON_UNESCAPED_UNICODE);
    }
    // if (mysqli_num_rows($control) > 0) {
       
    //     echo json_encode($response, JSON_UNESCAPED_UNICODE);
    // } else {
    //     $queryEmail = mysqli_query($db->connect(),"SELECT * FROM users WHERE email = '$userMail' AND password = '$userPassword'") or die(mysqli_error());
        
    //     if (mysqli_num_rows($queryEmail) > 0) {
    //         $response = array();
    //     $response["success"] = 1;
    //     $response["message"] = "Đăng nhập thành công";
    //     echo json_encode($response, JSON_UNESCAPED_UNICODE);
    //     } else {
    //         $response["success"] = 2;
    //         $response["message"] = "Đăng nhập thất bại";
    //         echo json_encode($response, JSON_UNESCAPED_UNICODE);
    //     }
    // }

?>