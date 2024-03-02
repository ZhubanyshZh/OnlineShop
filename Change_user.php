<?php 
    session_start();
    if($_SERVER['REQUEST_METHOD']=='POST' && $_SESSION['role'] == 'admin'){
        if(isset($_POST['id']) && isset($_POST['name']) && isset($_POST['phone_num']) && isset($_POST['address']) && isset($_POST['birthday']) && isset($_POST['email']) && isset($_POST['password']) && isset($_POST['role'])){
            require_once 'Database.php'; 
            $db->change_user($_POST['id'] , $_POST['name'], $_POST['phone_num'], $_POST['address'], $_POST['birthday'], $_POST['email'], $_POST['password'],$_POST['role']); 
            header("Location: profile.php?success");
        }
    } 
?>