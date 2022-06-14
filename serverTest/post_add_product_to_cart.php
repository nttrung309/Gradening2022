<?php
 
    $response = array();
    
    require_once __DIR__ . '/db_connect.php';
    
    $db = new db_connect();

    $idCart = $_POST['idCart'];
    $idProduct = $_POST['idProduct'];
    $quantity = "1";

    $result = mysqli_query($db->connect(),"SELECT * FROM cart_detail WHERE idProduct = '$idProduct'") or die(mysqli_error());

    if (mysqli_num_rows($result) > 0) {

        $query = mysqli_query($db->connect(),"SELECT * FROM cart_detail WHERE idProduct = '$idProduct' AND quantity = '5'") or die(mysqli_error());

        if (mysqli_num_rows($query) > 0) {

            $rep = array();

            $rep["success"] = 0;
            $rep["message"] = "Bạn đã mua tối đa";

            array_push($response, $rep);

            echo json_encode($response[0], JSON_UNESCAPED_UNICODE);
    
        } else {

            $queryUpdate = mysqli_query($db->connect(),"UPDATE cart_detail SET quantity = quantity + '$quantity' WHERE idProduct = '$idProduct'") or die(mysqli_error());

            $queryUpdateProduct = mysqli_query($db->connect(),"UPDATE product SET quantity = quantity - '$quantity' WHERE id = '$idProduct'") or die(mysqli_error());

            $rep = array();

            $rep["success"] = 1;
            $rep["message"] = "Thêm sản phẩm thành công";
    
            array_push($response, $rep);
    
            echo json_encode($response[0], JSON_UNESCAPED_UNICODE);

        }
    } else { 

        $queryInsert = mysqli_query($db->connect(),"INSERT INTO cart_detail(`idCart`, `idProduct`, `quantity`) VALUES ('$idCart','$idProduct','$quantity')") or die(mysqli_error());
        
        $queryInsertUpdateProduct = mysqli_query($db->connect(),"UPDATE product SET quantity = quantity - '$quantity' WHERE id = '$idProduct'") or die(mysqli_error());

        $rep = array();

        $rep["success"] = 1;
        $rep["message"] = "Thêm sản phẩm thành công";

        array_push($response, $rep);

        echo json_encode($response[0], JSON_UNESCAPED_UNICODE);
    }
?>