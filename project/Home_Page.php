<!DOCTYPE html>
<html lang="en">
<head>
    <title>Home Page</title>
</head>
<body>
    <?php
        session_start();
        print_r($_SESSION);
    ?>
    <h1>HELLO <?=$_SESSION['data']['username']?></h1>
</body>
</html>