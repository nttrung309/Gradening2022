<?php

    $response = array();
    
    require_once __DIR__ . '/db_connect.php';
    
    $db = new db_connect();

    $idCart = $_POST['idCart'];
    
    $result = mysqli_query($db->connect(),"SELECT * FROM cart_detail WHERE idCart = '$idCart'") or die(mysqli_error());
    
    if (mysqli_num_rows($result) > 0) {

        $response = array();
    
        while ($row = mysqli_fetch_array($result)) {

            $product = array();

            $product["id"] = $row["id"];
            $product["idCart"] = $row["idCart"];
            $product["idProduct"] = $row["idProduct"];
            $product["quantity"]=$row["quantity"];
            
            array_push($response, $product);

        }

        echo json_encode($response);

    } else {

        echo json_encode($response);
    }
?>