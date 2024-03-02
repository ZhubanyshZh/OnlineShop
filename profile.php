<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Profile</title>
</head>
<style>
    .error{
    background-color: rgba(0,0,0,0.5);
    bottom: 0;
    left: 0;
    max-width: 100%;
    position: fixed;
    right: 0;
    top: 0;
    z-index: 500;
}
.error-panel {
    background-color: #fff;
    background-size: 90px;
    border-radius: 10px;
    box-sizing: border-box;
    left: inherit;
    margin: auto;
    max-width: 540px;
    padding: 30px 20px;
    position: absolute;
    right: inherit;
    text-align: center;
    margin-top: 15px;
}
.h3{
    font-size: 24px;
    font-weight: bold;
    font-family: 'Arial';
}
.error-panel ul{
    list-style-type: none;
}
.error-panel .error-close input{
    background: black; 
    color: white;
    font-family: 'Montserrat';
    padding: 0 16px;
    width: 100%;
    height: 40px;
    padding-left: 16px;
    border-radius: 3px;
    border: 1px solid;
    box-sizing: border-box;
    line-height: 17px;
}
.error-panel .error-close input:hover{
    cursor: pointer;
}

header{
        background: black;
    }
    
</style>
<body>
    <?php
    session_start();

    if(isset($_SESSION['client_id'])){
        require_once 'header.php';
        
            if(isset($_GET['success'])){?>
                <div class="error" style="display: block;" onclick="myfunc()">
                    <div class="error-panel">
                        <span class="h3">Успешно изменен</span>
                        <div class="error-close">
                            <input type="button" value="Закрыть сообщение">
                        </div>
                    </div>
                </div>
            <?}elseif(isset($_GET['deleted'])){?>
                <div class="error" style="display: block;" onclick="myfunc()">
                    <div class="error-panel">
                        <span class="h3">Успешно удален</span>
                        <div class="error-close">
                            <input type="button" value="Закрыть сообщение">
                        </div>
                    </div>
                </div>
            <?}elseif(isset($_GET['product_added'])){?>
                <div class="error" style="display: block;" onclick="myfunc()">
                    <div class="error-panel">
                        <span class="h3">Успешно добавлен</span>
                        <div class="error-close">
                            <input type="button" value="Закрыть сообщение">
                        </div>
                    </div>
                </div>
            <?}elseif(isset($_GET['new_pass_error'])){?>
                <div class="error" style="display: block;" onclick="myfunc()">
                    <div class="error-panel">
                        <span class="p">Новый пароль должен содержать цифры, знаки, буквы в верхнем и нижнем регистре и должен содержать 8 символов</span>
                        <div class="error-close">
                            <input type="button" value="Закрыть сообщение">
                        </div>
                    </div>
                </div>
            <?}elseif(isset($_GET['old_pass_error'])){?>
                <div class="error" style="display: block;" onclick="myfunc()">
                    <div class="error-panel">
                        <span class="p">Старый пароль не совпадает!</span>
                        <div class="error-close">
                            <input type="button" value="Закрыть сообщение">
                        </div>
                    </div>
                </div>
            <?}elseif(isset($_GET['pass_change_success'])){?>
                <div class="error" style="display: block;" onclick="myfunc()">
                    <div class="error-panel">
                        <span class="p">Пароль успешно изменен!</span>
                        <div class="error-close">
                            <input type="button" value="Закрыть сообщение">
                        </div>
                    </div>
                </div>
            <?}elseif(isset($_GET['confirm_error'])){?>
                <div class="error" style="display: block;" onclick="myfunc()">
                    <div class="error-panel">
                        <span class="p">Новые пароли не совпадает!</span>
                        <div class="error-close">
                            <input type="button" value="Закрыть сообщение">
                        </div>
                    </div>
                </div>
            <?}
            if($_SESSION['role'] == "admin"){
            require_once 'admin_profile.php';
            require_once 'Database.php';
        }else{
            require_once 'user_profile.php';
        }
        require_once 'footer.php';
    }else{
        header("Location: Login.php?first_log");
    }
    ?>
    <script>
        function myfunc(){
            document.getElementsByClassName("error")[0].style.display = "none";
        }
        if (performance.navigation.type === 1) {
            window.location.href = 'profile.php';
        }
    </script>
</body>
</html>