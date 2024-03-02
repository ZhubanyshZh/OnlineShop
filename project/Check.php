<?php
try{
    require_once('db.php');
        if(isset($_POST['email1']) && isset($_POST['password1'])){
            if(check($_POST['email1'], $_POST['password1'])!=null){
                $a = check($_POST['email1'], $_POST['password1']);
                session_start();
                $_SESSION = $a;
                header("Location: Homepage.php");
            }else header("Location: Login.php?error=true");
        }
    
}catch(PDOException $e){
    echo $e;
}
?>