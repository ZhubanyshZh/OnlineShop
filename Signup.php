<!DOCTYPE html>
<html lang="en">
<head>
    <title>Добро Пожаловать в Canelli Co!</title>
</head>
<link href='https://fonts.googleapis.com/css?family=Montserrat' rel='stylesheet'>
<link href="https://fonts.cdnfonts.com/css/bebas-neue" rel="stylesheet">
<style>
     @import url('https://fonts.cdnfonts.com/css/bebas-neue');
.body{
    width: auto;
    display: flex;
    justify-content: center;
    align-items: center;
    text-align: center;
    background: white;
    margin-top: 30px;
}
form label {
    width:150px;
    clear:left;
    text-align:left;
    padding-right:10px;
    color: #343434;
}

form input, label {
    float:left;
}
form h2{
    color: #566A7F;
}
button{
    width: 100%;
    padding: 4px;
}
form input{
    font-family: 'Montserrat';
    padding: 0 16px;
    width: 100%;
    height: 40px;
    padding-left: 16px;
    border-radius: 3px;
    border: 1px solid;
    box-sizing: border-box;
    color: #8d8d8d;
    line-height: 17px;
}
select{
    font-family: 'Montserrat';
    width: 100%;
    height: 40px;
    padding-left: 16px;
    border-radius: 3px;
    color: #8d8d8d;
    line-height: 17px;

}
option{
    color: black;
}
form a{
    font-family: 'Arial';
    font-weight: 600; 
    color: black;
}
form div{
    color: red;
    font-family: TradeGothicforNike365-BdCn,Helvetica!important;
    font-size: 14px;
    padding: 5px 0 5px 0;
}
form input:focus{
    outline: none;
}
form .error-message {
  display: none;
  font-weight: bold;
  color: red;
}
form input:invalid {
  border-color: red;
}

form input:invalid ~ .error-message {
  display: block; 
}

form input:valid {
  border-color: var(--color-valid);
}

form h2{
    margin: 0;
    color: #111;
    
}
form h2.h2{
    font-family: 'Bebas Neue', sans-serif;
    font-size: 34px;
    font-weight: 900;
}
form .logo{
    font-family: 'Bebas Neue', sans-serif;
    font-weight: 400;
    font-size: 26px;
    margin-bottom: 30px;
}
form p{
    font-family: 'Montserrat';
}
form img.img1{
    width: 35%;
}
input::placeholder{
    font-family: 'Montserrat';
    font-size: 14px;
}
/* .checkbox{
    float: right;
}
input.check1{
    float: left;
} */
button{
    background-color: #000;
    border: 1px solid #000;
    border-radius: 3px;
    color: #fff;
    cursor: pointer;
    display: block;
    font-family: TradeGothicforNike365-BdCn,Helvetica!important;
    font-size: 15px;
    margin: auto;
    height: 40px;
}

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

/* HEADER CSS */
/* body{
        background-image: url("img/monc.webp");
    } */
    header{
        display: flex;
        flex-wrap: nowrap;
        justify-content: space-between;
        align-items: center;
        padding: 0;
        height: 80px;
        background: black;
        width: 100%;
    }
    .links ul{
        display: flex;
        list-style-type: none;
    }
    div.links li{
        padding: 18px 15px 18px 15px;
    }
    div.links ul li a{
        color: black;
        text-decoration: none;
        font-family: 'Montserrat';
        color: white;
        font-size: 19px;
    }
    .moncler{
        margin: 70px 0 18px 30px;
        width: 55%;
        cursor: pointer;
    }
    .links svg{
        border-radius: 50%;
        padding: 4px;
        color: white;
    }
    .links svg:hover{
        background: #e5e5e5;
        cursor: pointer;
    }
    div.actions{
        display: flex;
    }
    div.searchs{
        margin: 30px 2px 30px 2px;
    }
    .links ul li input{
        margin-top: 10px;
    }
    .links ul li input{
        border-radius: 20px;
    }
</style>
<body>
    <?php
    $nameErr = $dateErr = $emailErr = $phonenumberErr = $usernameErr = $passwordErr = $cityErr = "";

    $name = $date = $email = $phonenumber = $username = $password = $city = "";
    if($_SERVER['REQUEST_METHOD']=='POST'){
        

        if(empty($_POST['name'])){
            $nameErr = "Укажите действительное имя";
        }else{
            $name = $_POST["name"];
            if (!preg_match("/^[a-zA-Z-' ]*$/",$name)) {
                $nameErr = "Разрешены только буквы и пробелы";
            }
        } 

        if(empty($_POST['birthday'])){
            $dateErr="Укажите действительную дату рождения";
        }else{
            $date = $_POST['birthday'];
        }

        if(empty($_POST['city'])){
            $cityErr = "Укажите город проживания";
        }else{
            $city = $_POST['city'];
        }


        if(empty($_POST['phonenumber'])){
            $phonenumberErr = "Укажите действительный номер телефона";
        }else{
            $phonenumber = $_POST['phonenumber'];
            if(preg_match('`[0-9]`',$_POST['phonenumber']) && !preg_match("/^[a-zA-Z-' ]*$/",$_POST['phonenumber'])){
                if(strlen($_POST['phonenumber'])==11) $phonenumber = $_POST['phonenumber'];
                else $phonenumberErr = "Длина должна содержать 11";
            }else{
                $phonenumberErr = "Должны быть только цифры";
            }
        }

        if (empty($_POST["email"])) {
            $emailErr = "Укажите действительный адрес электронной почты";
        } else {
            $email = $_POST["email"];
            if (!filter_var($email, FILTER_VALIDATE_EMAIL)) {
              $emailErr = "Недопустимый формат электронной почты";
            }
        }

        if(empty($_POST['password'])){
            $passwordErr = "Укажите пароль";
        }else{
            if(!preg_match('`[0-9]`',$_POST['password'])){
                $passwordErr .= "Пароль должен содержать цифры 0-9"."<br>";
            }if(!preg_match('`[A-Z]`',$_POST['password'])){
                $passwordErr .= "Пароль должен содержать буквы A-Z"."<br>";
            }if(!preg_match('`[a-z]`',$_POST['password'])){
                $passwordErr .= "Пароль должен содержать буквы a-z"."<br>";
            }if(!preg_match('/[.,:;@&%$]/',$_POST['password'])){
                $passwordErr .= "Пароль должен содержать знаки"."<br>";
            }
            if(strlen($_POST['password']) <= 8){
                $passwordErr .= "Пароль должен содержать 8 символ";
            }
            if(empty($passwordErr)){
                $password = $_POST['password'];
            }
        }

        if(empty($nameErr) && empty($dateErr) && empty($emailErr) && empty($phonenumberErr) && empty($passwordErr) && empty($cityErr)){
            require_once('Database.php');
            if($_SERVER['REQUEST_METHOD']==='POST'){
                if(isset($_POST['name']) && isset($_POST['phonenumber']) && isset($_POST['birthday']) && isset($_POST['city']) && isset($_POST['email']) && isset($_POST['password'])){
                    if(!$db->istheresuchanaccount($_POST['email'],$_POST['phonenumber'])){
                        $db->addClients($_POST['name'], $_POST['phonenumber'], $_POST['birthday'], $_POST['city'], $_POST['email'], $_POST['password']);
                        header("location: Login.php?logedsucces");
                    }else{?>
                            <div class="error" style="display: block;" onclick="myfunc()">
                                <div class="error-panel">
                                    <span class="h3">Произошла ошибка</span>
                                    <ul>
                                        <li>Пользователя с такой почтой или номереом телефона уже существует.</li>
                                    </ul>
                                    <div class="error-close">
                                        <input type="button" value="Закрыть сообщение">
                                    </div>
                                </div>
                            </div>
                        <?php
                    }
                }
            }
        }
        
    }
    require_once 'header.php';
    ?>
    <div class="body">
    <form action="Signup.php" method="post">
        <h2 class="h2">БУДЬ С НАМИ В МОДЕ</h2>
        <h2 class="logo">Canelli co.</h2>
        <p>Создай профиль члена клуба Canelli co. и получи<br> доступ к лучшим товарам Canelli co., источникам<br> вдохновения и общению в сообществе.</p><br><br>
        <input type="text" name="name" id="name" value="<?=$name?>" placeholder="First name"><br><div><?=$nameErr?></div>
        <?php
        if($nameErr==""){
            echo "<br><br>";
        }
        ?>

        <p class="error-message">Please enter a valid e-mail address</p>
        <input type="text" name="phonenumber" id="phone" value="<?=$phonenumber?>" placeholder="Phone Number"><br><div><?=$phonenumberErr?></div>
        <?php
        if($phonenumberErr==""){
            echo "<br><br>";
        }
        ?>

        <input type="date" name="birthday" value=<?=$date?>><br><br><div><?=$dateErr?></div>
        <?php
        if($dateErr==""){
            echo "<br>";
        }
        ?>

        <select name="city">
            <option value="<?=$city?>">
            <?php
            if(empty($cityErr)){
                if($_SERVER['REQUEST_METHOD']=='POST') echo $city;
                else echo "Chose city";
            }else{
                echo "Chose city";
            }
            ?>
            </option>
            <option value="БатысҚ">Батыс Қазақстан</option>
            <option value="Орал">Орал</option>
            <option value="Атырау">Атырау</option>
            <option value="Ақтөбе">Ақтөбе</option>
            <option value="Маңғыстау">Маңғыстау</option>
            <option value="Қостанай">Қостанай</option>
            <option value="Қызылорда">Қызылорда</option>
            <option value="Ұлытау">Ұлытау</option>
            <option value="Түркістан">Түркістан</option>
            <option value="Шымкент">Шымкент</option>
            <option value="Тараз">Тараз</option>
            <option value="Алматы">Алматы</option>
            <option value="Талдықорған">Талдықорған</option>
            <option value="Семей">Семей</option>
            <option value="Өскемен">Өскемен</option>
            <option value="Қарағанды">Қарағанды</option>
            <option value="Павлодар">Павлодар</option>
            <option value="Астана">Астана</option>
            <option value="Көкшетау">Көкшетау</option>
            <option value="Петропавл">Петропавл</option>
        </select><br><div><?=$cityErr?></div>
        <?php
        if($cityErr==""){
            echo "<br>";
        }
        ?>

        <input type="email" name="email" id="email" value="<?=$email?>" placeholder="Email"><br><div><?=$emailErr?></div>
        <p class="error-message">Please enter a valid e-mail address</p>
        <?php
        if($emailErr==""){
            echo "<br><br>";
        }
        ?>
        
        <input type="password" name="password" id="password" value="<?=$password?>" placeholder="Password"><br><div><?=$passwordErr?></div><br><?php
        if($passwordErr==""){
            echo "<br><br>";
        }
        ?>

        <!-- <input type="checkbox" name="message" id="chechbox" class="check1">
        <label for="checkbox" class="checkbox">Подпишись на рассылку, чтобы получать новости<br> от Canelli co. о товарах, а также предложения и<br> преимущества для членов клуба</label> -->

        <button type="submit">ДАВАЙ С НАМИ</button>
        <p>Ты уже участник? <a href="Login.php">Войди</a></p>
    </form>
    </div>
    <script>
        function myfunc(){
            document.getElementsByClassName("error")[0].style.display = "none";
        }
        if (performance.navigation.type === 1) {
            window.location.href = 'Signup.php';
        }
    </script>
</body>
</html>