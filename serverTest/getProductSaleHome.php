<?php
 
    /*
    * Following code will list all the products
    */
    
    // array for JSON response
    $response = array();
    
    // include db connect class
    require_once __DIR__ . '/db_connect.php';
    
    // connecting to db
    $db = new db_connect();
    
    // get all products from products table
    $result = mysqli_query($db->connect(),"SELECT *  from product ORDER BY discount DESC LIMIT 1") or die(mysqli_error());
    
    // check for empty result
    if (mysqli_num_rows($result) > 0) {
        // looping through all results
        // products node
        $response = array();
    
        while ($row = mysqli_fetch_array($result)) {
            // temp user array
            $product = array();
            $product["id"] = $row["id"];
            $product["idCategory"] = $row["idCategory"];
            $product["name"] = $row["name"];
            $product["price"]=$row["price"];
            $product["discount"]=$row["discount"];
            $product["image"] = $row["image"];
            $product["quantity"]=$row["quantity"];
            $product["rating"]=$row["rating"];
            $product["note"]=$row["note"];
            // push single product into final response array
            array_push($response, $product);
        }
        // success
        // $response["success"] = 1;
    
        // echoing JSON response
        echo json_encode($response);
    } else {
        // no products found
        // $response["success"] = 0;
        // $response["message"] = "No products found";
    
        // echo no users JSON
        echo json_encode($response);
    }
?>