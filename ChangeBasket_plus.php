<?php
session_start();
if($_SERVER["REQUEST_METHOD"]=='POST' && isset($_SESSION['client_id'])){
    require_once 'Database.php';
    $db->update_backet($_SESSION['client_id'], $_POST['id'], ($_POST['amount'] + 1));
    header("Location:bucket.php");
}else{
    header("Location: Login.php?first_log");
}
?>