<?php
    class db_connect {
        private $conn;
        // constructor
        function __construct() {
            // connecting to database
            $this->connect();
        }
     
        // destructor
        function __destruct() {
            // closing db connection
            $this->close();
        }
     
        /**
         * Function to connect with database
         */
        function connect() {
            // import database connection variables
            include_once(dirname(__FILE__) . '/db_config.php');
     
            // Connecting to mysql database
            $this->conn = mysqli_connect(DB_SERVER, DB_USER, DB_PASSWORD) or die(mysqli_error());
            mysqli_query($this->conn, "SET NAMES 'utf8'");

            // Selecing database
            mysqli_select_db($this->conn,DB_DATABASE) or die(mysql_error());
     
            // returing connection cursor
            return $this->conn;
        }
     
        /**
         * Function to close db connection
         */
        function close() {
            // closing db connection
            mysqli_close($this->conn);
        }
     
    }
     
?>
