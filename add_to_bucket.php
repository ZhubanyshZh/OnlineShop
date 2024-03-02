<?php
session_start();
if($_SERVER['REQUEST_METHOD']=='GET' && isset($_SESSION['client_id'])){
    require_once 'Database.php';
    $id = $_GET['id'];
    $db->add_to_bucket($id, $_SESSION['client_id']);
    header("Location: To_more_clothe.php?id=$id");
}else{
    header("Location: Login.php?first_log");
}
?>