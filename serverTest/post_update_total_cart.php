<?php
 
    $response = array();
    
    require_once __DIR__ . '/db_connect.php';
    
    $db = new db_connect();

    $total = $_POST['total'];
    $idCart = $_POST['idCart'];

    $result = mysqli_query($db->connect(),"UPDATE cart SET total = '$total' WHERE id = '$idCart'") or die(mysqli_error());
    

    
?>