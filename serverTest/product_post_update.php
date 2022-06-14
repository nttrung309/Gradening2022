<?php
 
    $response = array();
    
    require_once __DIR__ . '/db_connect.php';
    
    $db = new db_connect();

    $quantity = $_POST['quantity'];
    $price = $_POST['price'];
    $name = $_POST['name'];
    $idProduct = $_POST['idProduct'];

    $result = mysqli_query($db->connect(),"UPDATE product SET quantity = '$quantity' AND price = '$price' AND name = '$name' WHERE id = '$idProduct'") or die(mysqli_error());
    

    
?>