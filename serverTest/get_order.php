<?php

    $response = array();
    
    require_once __DIR__ . '/db_connect.php';
    
    $db = new db_connect();

    $idUser = $_POST['idUser'];
    
    $result = mysqli_query($db->connect(),"SELECT * FROM order_ WHERE idUser = '$idUser'") or die(mysqli_error());
    
    if (mysqli_num_rows($result) > 0) {

        $response = array();
    
        while ($row = mysqli_fetch_array($result)) {

            $order = array();

            $order["id"] = $row["id"];
            $order["idUser"] = $row["idUser"];
            $order["idAddress"] = $row["idAddress"];
            $order["date"] = $row["date"];
            $order["state"] = $row["state"];
            $order["provisional_money"] = $row["provisional_money"];
            $order["shipping"] = $row["shipping"];
            $order["total"] = $row["total"];

            array_push($response, $order);
        }

        echo json_encode($response, JSON_UNESCAPED_UNICODE);

    } else {

        echo json_encode($response, JSON_UNESCAPED_UNICODE);

    }
?>