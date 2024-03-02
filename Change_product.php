<?php
    session_start();
    if($_SERVER['REQUEST_METHOD']=='POST' && $_SESSION['role'] == 'admin'){
        if(isset($_POST['id']) && isset($_POST['name']) && isset($_POST['brand']) && isset($_POST['price']) && isset($_POST['size']) && isset($_POST['amount']) && isset($_POST['cat'])){
            require_once 'Database.php';
            $db->change_product($_POST['id'] , $_POST['name'], $_POST['brand'], $_POST['price'], $_POST['size'], $_POST['amount'], $_POST['cat']);
            header("Location: profile.php?success");
        }
    }
?>