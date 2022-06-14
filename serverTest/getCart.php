<?php

    $response = array();
    
    require_once __DIR__ . '/db_connect.php';
    
    $db = new db_connect();

    $idUser = $_POST['idUser'];
    
    $result = mysqli_query($db->connect(),"SELECT * FROM cart WHERE idUser = '$idUser' AND state = '0'") or die(mysqli_error());
    
    if (mysqli_num_rows($result) > 0) {

        $response = array();
    
        while ($row = mysqli_fetch_array($result)) {

            $product = array();

            $product["id"] = $row["id"];
            $product["idUser"] = $row["idUser"];
            $product["total"] = $row["total"];
            $product["state"]=$row["state"];
            
            array_push($response, $product);

        }

        echo json_encode($response[0]);

    } else {

         $query = mysqli_query($db->connect(),"INSERT INTO cart(`idUser`, `total`, `state`) VALUES('$idUser','0','0')") or die(mysqli_error());

        if ($query) {

            $get = mysqli_query($db->connect(),"SELECT * FROM cart WHERE idUser = '$idUser' AND state = '0'") or die(mysqli_error());

            if (mysqli_num_rows($get) > 0) {

                $response = array();
            
                while ($row = mysqli_fetch_array($get)) {
        
                    $product = array();
        
                    $product["id"] = $row["id"];
                    $product["idUser"] = $row["idUser"];
                    $product["total"] = $row["total"];
                    $product["state"]=$row["state"];
                    
                    array_push($response, $product);
        
                }
        
                echo json_encode($response[0]);
        
            }
    
        } else {
            
        }
    }
?>