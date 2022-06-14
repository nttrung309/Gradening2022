<?php

    $response = array();
    
    require_once __DIR__ . '/db_connect.php';
    
    $db = new db_connect();

    $idOrderList = $_POST['idOrder'];
    // $idOrderList = "1,2";
    $idOrderList = explode(",", $idOrderList);
    $response = array();
    
    foreach ($idOrderList as $idOrder) {

        $result = mysqli_query($db->connect(),"SELECT COUNT(*) as total FROM order_detail WHERE idOrder = $idOrder") or die(mysqli_error());

        while ($row = mysqli_fetch_assoc($result)) {

            $list = array();
            $list = $row["total"];

            array_push($response, $list);
        }
    }

    echo json_encode($response, JSON_UNESCAPED_UNICODE);

?>