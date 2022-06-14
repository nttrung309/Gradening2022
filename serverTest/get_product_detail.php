<?php

    $response = array();
    
    require_once __DIR__ . '/db_connect.php';
    
    $db = new db_connect();

    $idProduct = $_POST['idProduct'];
    
    $result = mysqli_query($db->connect(),"SELECT * FROM product_detail WHERE idProduct = '$idProduct'") or die(mysqli_error());
    
    if (mysqli_num_rows($result) > 0) {

        $response = array();
    
        while ($row = mysqli_fetch_array($result)) {

            $product = array();
            
            $product["idProduct"] = $row["idProduct"];
            $product["water"] = $row["water"];
            $product["sunlight"] = $row["sunlight"];

            array_push($response, $product);
        }

        echo json_encode($response[0]);

    } else {

        $product = array();
            
        $product["idProduct"] = "-1";
        $product["water"] = "No";
        $product["sunlight"] = "No";

        array_push($response, $product);
        
        echo json_encode($response[0]);

    }
?>