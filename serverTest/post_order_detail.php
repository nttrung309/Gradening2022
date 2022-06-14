<?php
 
    $response = array();
    

    
    $db = new db_connect();

    $idOrder = $_POST['idOrder'];
    $idProduct = $_POST['idProduct'];
    $quantity = $_POST['quantity'];

    // $idOrder = "1";
    // $idProduct = "1";
    // $quantity = "2";

    $query = mysqli_query($db->connect(),
    "INSERT INTO order_detail(`idOrder`, `idProduct`, `quantity`) VALUES ('$idOrder','$idProduct','$quantity')") or die(mysqli_error());

    echo json_encode($query);
?>