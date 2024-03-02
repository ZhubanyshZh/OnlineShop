<?php 
    session_start();
    if($_SERVER['REQUEST_METHOD']=='POST' && $_SESSION['role']=='admin' && isset($_POST['id'])){ 
        require_once 'Database.php';
        print_r($_POST['id']);
        $db->delete_user($_POST['id']);
        header("Location: profile.php?deleted");
    } 
?>

<!-- User ди өшіру үшін биринши фидбактарын өшіріп сосын барып негизги таблицадан оширемиз
    Корзина Фаворитс косканда осыны карау керек дурыстап
-->
