<?php
try{    
    if($_SERVER['REQUEST_METHOD'] == 'POST'){
        if(isset($_POST['email1']) && isset($_POST['password1'])){
            require_once 'Database.php';
            $a = $db->check($_POST['email1'], $_POST['password1']);
            if($a!=null){
                session_start();
                $_SESSION = $a;
                header("Location: Homepage.php");
            }else header("Location: Login.php?error=true");
        }else{
            header("Location: Login.php");
        }
    }else{
        header("Location: Login.php");
    }
}catch(PDOException $e){
    echo $e;
}
?>