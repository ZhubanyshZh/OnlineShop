<?php
    require_once('db.php');
    if($_SERVER['REQUEST_METHOD']==='GET'){
        if(isset($_GET['name']) && isset($_GET['phonenumber']) && isset($_GET['birthday']) && isset($_GET['city']) && isset($_GET['email']) && isset($_GET['username']) && isset($_GET['password'])){
            addClients($_GET['name'], $_GET['phonenumber'], $_GET['birthday'], $_GET['city'], $_GET['email'], $_GET['username'], $_GET['password']);
            
        }
        header("location: Login.php");
    }
    
?>