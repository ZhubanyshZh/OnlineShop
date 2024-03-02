<?php
session_start();
if($_SERVER['REQUEST_METHOD']=='GET' && isset($_SESSION['client_id'])){
    require_once 'Database.php';
    $id = $_GET['id'];
    $db->set_to_fav($id, $_GET['client_id']);
}
?>