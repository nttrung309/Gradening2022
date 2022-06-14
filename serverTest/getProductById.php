<?php

    $response = array();
    
    require_once __DIR__ . '/db_connect.php';
    
    $db = new db_connect();

    $idProduct = $_POST['idProduct'];
    
    $result = mysqli_query($db->connect(),"SELECT * FROM product WHERE id = '$idProduct'") or die(mysqli_error());
    
    if (mysqli_num_rows($result) > 0) {

        $response = array();
    
        while ($row = mysqli_fetch_array($result)) {

            $product = array();
            
            $product["id"] = $row["id"];
            $product["idCategory"] = $row["idCategory"];
            $product["name"] = $row["name"];
            $product["price"]= $row["price"];
            $product["discount"]=$row["discount"];
            $product["image"] = $row["image"];
            $product["quantity"]=$row["quantity"];
            $product["rating"]=$row["rating"];
            $product["note"]=$row["note"];

            array_push($response, $product);
        }

        echo json_encode($response[0], JSON_UNESCAPED_UNICODE);

    } else {

        echo json_encode($response);

    }
?>