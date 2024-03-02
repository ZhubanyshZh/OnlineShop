<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Profile</title>
</head>
<style>
    header{
        background: black;
    }
</style>
<body>
    <?php
    session_start();
    if(isset($_SESSION['client_id'])){
        require_once 'header.php';
        if($_SESSION['role'] == "admin"){
            require_once 'admin_profile.php';
            require_once 'db.php';
        }else{
            require_once 'user_profile.php';
        }
        require_once 'footer.php';
    }else{
        header("Location: Login.php");
    }
    ?>
</body>
</html>