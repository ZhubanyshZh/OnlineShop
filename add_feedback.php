<?php
    session_start();
    if ($_SERVER['REQUEST_METHOD'] == 'POST' && isset($_SESSION['client_id'])) {
        require_once 'Database.php';
        $desc = $_POST['desc'] ?? '';
        $p_id = $_POST['p_id'] ?? '';
        $client_id = $_SESSION['client_id'] ?? '';
        $db->add_feedback($desc, $p_id, $client_id);
        
        header("Location: To_more_clothe.php?id=$p_id");
    }else{
        header("Location: Login.php?first_log");
    }
?>