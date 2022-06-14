<?php

    $response = array();
    
    require_once __DIR__ . '/db_connect.php';
        
    $db = new db_connect();

    $result = mysqli_query($db->connect(),"SELECT * FROM users") or die(mysqli_error());

    if (mysqli_num_rows($result) > 0) {

        $response = array();
    
        while ($row = mysqli_fetch_array($result)) {

            $user = array();
            $user["id"] = $row["id"];
            $user["username"] = $row["username"];
            $user["password"] = $row["password"];
            $user["name"] = $row["name"];
            $user["date"] = $row["date"];
            $user["gender"] = $row["gender"];
            $user["telephone"] = $row["telephone"];
            $user["email"] = $row["email"];
            
            array_push($response, $user);
        }
        echo json_encode($response, JSON_UNESCAPED_UNICODE);
    } else {
        echo json_encode($response, JSON_UNESCAPED_UNICODE);
    }
?>