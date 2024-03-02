<?php
    session_start();
    if(isset($_SESSION['client_id'])){
        if($_SESSION['role']=='admin'){
            if($_SERVER['REQUEST_METHOD']=='POST'){
                require_once 'Database.php';
                $db->add_product($_POST['name'], $_POST['brand'], $_POST['price'], $_POST['size'], $_POST['amount'], $_POST['photo'],$_POST['cat']);
                header("Location: profile.php?product_added");
            }else{
                header("Location: profile.php");
            }
        }else{
            header("Location: profile.php");
        }
    }else{
        header("Location: Login.php?first_log");
    }
?>