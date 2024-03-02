<?php

require_once 'Database.php';
if(!validatePassword($_POST['new_password'])){
    header('Location: profile.php?new_pass_error');
    return;
}
  
 if($_SERVER['REQUEST_METHOD']=='POST'){ 
    session_start();
    
    if(isset($_SESSION['client_id'])){
        if($db->check_pass($_POST['old_password'], $_POST['id'])){
            if($_POST['new_password']==$_POST['confirm_password']){
                $db->changePassword($_POST['new_password'], $_POST['id']);
                header("Location:profile.php?pass_change_success");
            }else header('location:profile.php?confirm_error');
        }else header('location:profile.php?old_pass_error'); 
    }else{
        header('location:Login.php');
    }
}
function validatePassword($password) {
    $uppercase = preg_match('@[A-Z]@', $password);
    $lowercase = preg_match('@[a-z]@', $password);
    $number = preg_match('@[0-9]@', $password);
    $specialChars = preg_match('@[^\w]@', $password);

    if(!$uppercase || !$lowercase || !$number || !$specialChars || strlen($password) < 8) {
        return false;
    } else {
        return true;
    }
}
?>