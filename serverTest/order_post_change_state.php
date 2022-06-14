<?php
 
    $response = array();
    
    require_once __DIR__ . '/db_connect.php';
    
    $db = new db_connect();

    $idOrder = $_POST['idOrder'];
    $state = $_POST['state'];

    $result = mysqli_query($db->connect(),"UPDATE order_ SET state = '$state' WHERE id = '$idOrder'") or die(mysqli_error());
    
?>