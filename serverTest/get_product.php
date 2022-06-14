<?php
    
    require_once __DIR__ . '/db_connect.php';
    
    $db = new db_connect();

    $idProductList = $_POST['idProduct'];
    $idProductList = explode(",", $idProductList);
    $response = array();
    
    foreach ($idProductList as $idProduct) {

        $result = mysqli_query($db->connect(),"SELECT * FROM product") or die(mysqli_error());

        while ($row = mysqli_fetch_assoc($result)) {

            $product = array();
            $product["id"] = $row["id"];
            $product["idCategory"] = $row["idCategory"];
            $product["name"] = $row["name"];
            $product["price"]=$row["price"];
            $product["discount"]=$row["discount"];
            $product["image"] = $row["image"];
            $product["quantity"]=$row["quantity"];
            $product["rating"]=$row["rating"];
            $product["note"]=$row["note"];

            array_push($response, $product);
        }

    }

    echo json_encode($response, JSON_UNESCAPED_UNICODE);
?>