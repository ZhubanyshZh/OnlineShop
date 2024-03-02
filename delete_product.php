<?php
    session_start();
    if($_SERVER['REQUEST_METHOD']=='POST' && $_SESSION['role'] == 'admin' && isset($_POST['id'])){
        require_once 'Database.php';
        $db->delete_product($_POST['id']);
        header("Location: profile.php?deleted");
    }
?>