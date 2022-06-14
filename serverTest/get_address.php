<?php
 
    $response = array();
    
    require_once __DIR__ . '/db_connect.php';
    
    $db = new db_connect();
    $userId = $_POST['idUser'];
    
    $result = mysqli_query($db->connect(),"SELECT * FROM user_address WHERE idUser='$userId'") or die(mysqli_error());
    
    if (mysqli_num_rows($result) > 0) {

        $response = array();
    
        while ($row = mysqli_fetch_array($result)) {
            $address = array();
            $address["id"] = $row["id"];
            $address["idUser"] = $row["idUser"];
            $address["address_name"] = $row["address_name"];
            $address["address_number"] = $row["address_number"];
            $address["address_line"] = $row["address_line"];
            $address["province"] = $row["province"];
            $address["district"] = $row["district"];
            $address["ward"] = $row["ward"];

            array_push($response, $address);
        }
        echo json_encode($response, JSON_UNESCAPED_UNICODE);

    } else {

        echo json_encode($response, JSON_UNESCAPED_UNICODE);
    }
?>