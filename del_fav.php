<?php
session_start();
if($_SERVER['REQUEST_METHOD']=='GET' && isset($_SESSION['client_id'])){
    require_once 'Database.php';
    $id = $_GET['id'];
    $db->delete_fav_by_prod_id($id, $_GET['client_id']);
}
?>