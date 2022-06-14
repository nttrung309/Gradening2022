<?php
 
    $response = array();
    
    require_once __DIR__ . '/db_connect.php';
    
    $db = new db_connect();

    $idUser = $_POST['idUser'];
    $idAddress = $_POST['idAddress'];
    $date = $_POST['date'];
    $state = $_POST['state'];
    $provisional_money = $_POST['provisional_money'];
    $shipping = $_POST['shipping'];
    $total = $_POST['total'];

    // $idUser = "1";
    // $idAddress = "0";
    // $date = "20001-09-21";
    // $state = "đặt hàng";
    // $provisional_money = "1000000";
    // $shipping = "2000";
    // $total = "100000000";
    
    $query = mysqli_query($db->connect(),
    "INSERT INTO order_(`idUser`, `idAddress`, `date`, `state`, `provisional_money`, `shipping`, `total`) 
    VALUES ('$idUser','$idAddress','$date','$state','$provisional_money','$shipping','$total')") or die(mysqli_error());

    if ($query) {

        $result = mysqli_query($db->connect(),"SELECT MAX(id) as id FROM order_") or die(mysqli_error());
    
        $row = mysqli_fetch_assoc($result);

        echo json_encode($row['id']);

    } else {

        echo json_encode("0");
        
    }
?>