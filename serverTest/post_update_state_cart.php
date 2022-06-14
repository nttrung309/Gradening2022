<?php
 
    $response = array();
    
    require_once __DIR__ . '/db_connect.php';
    
    $db = new db_connect();

    $idCart = $_POST['idCart'];

    $result = mysqli_query($db->connect(),"UPDATE cart SET state = '1' WHERE id = '$idCart'") or die(mysqli_error());
    
?>