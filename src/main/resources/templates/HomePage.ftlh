<!DOCTYPE html>
<html lang="en">
<head>
    <title>Canelli co. - Home Page</title>
</head>
<link href='https://fonts.googleapis.com/css?family=Montserrat' rel='stylesheet'>
<link href="https://fonts.cdnfonts.com/css/bebas-neue" rel="stylesheet">
<link href='https://fonts.googleapis.com/css?family=Montserrat' rel='stylesheet'>
<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css'>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" integrity="sha512-9usAa10IRO0HhonpyAIVpjrylPvoDwiPUiKdWk5t3PyolY1cOd4DSE0Ga+ri4AuTroPR5aQvXU9xC6qOPnzFeg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
<style>
    header{
        display: flex;
        flex-wrap: nowrap;
        justify-content: space-between;
        align-items: center;
        padding: 0;
        height: 80px;
        transition: 0.5s;
        width: 100%;
    }
    header:hover{
        background: white;
    }
    header:hover .links ul li a{
        color: black;
    }
    header:hover svg{
        color: black;
    }
    header:hover .btn{
        color: black;
    }
    .btn{
        color: white;
        font-family: 'Montserrat';
        font-size: 13px;
    }
    .links{
        padding: 0;
        height: 60px;
    }
    .links ul{
        display: flex;
        list-style-type: none;
    }
    div.links li{
        padding: 18px 15px 26px 15px;
    }
    div.links ul li a{
        color: black;
        text-decoration: none;
        font-family: 'Montserrat';
        color: white;
        font-size: 15px;
    }
    .moncler{
        margin: 70px 0 18px 30px;
        width: 55%;
        cursor: pointer;
        color: white;
    }
    .monclerlogo{
        z-index: 999;
    }
    .links svg{
        border-radius: 50%;
        padding: 3px;
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
    .links input{
        border-radius: 20px;
    }
    ul li ul.dropdown li{
        transition: 0.5s;
        display: block;
        padding: 0;
    }
    ul li ul.dropdown li a{
        color: black;
        font-size: 15px;
        font-family: 'Montserrat';
    }
    ul li ul.dropdown{
        background: white;
        position: absolute;
        top: 80px;
        left: 0;
        z-index: 900;
        display: none;
        grid-template-columns: auto auto auto auto;
        padding: 100px 100px 80px 400px;
        column-gap: 80px;
        row-gap: 5px;
    }
    ul li:hover ul.dropdown{
        display: grid;
    }
    img.present{
        width: 100%;
    }
    li.item_img{
        grid-row: 1 / 10;
        grid-column: 4 / 5;
        width: 60%;
        height: auto;
    }

    ul li ul.dropdown1 li{
        transition: 0.5s;
        display: block;
        padding: 0;
    }
    /*dropdown1*/
    ul li ul.dropdown1 h5{
        padding: 0;
        margin: 0;
    }
    ul li ul.dropdown1 li a{
        color: black;
        font-size: 15px;
        font-family: 'Montserrat';
    }
    ul li ul.dropdown1{
        background: white;
        position: absolute;
        top: 80px;
        left: 0;
        z-index: 900;
        display: none;
        grid-template-columns: auto auto auto auto auto;
        padding: 100px 100px 80px 300px;
        column-gap: 80px;
        row-gap: 5px;
    }
    ul li:hover ul.dropdown1{
        display: grid;
    }
    /* img.present{
        width: 100%;
    } */
    li.item_img1{
        grid-row: 1 / 16;
        grid-column: 5 / 6;
        width: 60%;
        height: auto;
    }
    ul li ul.dropdown2 li{
        transition: 0.5s;
        display: block;
        padding: 0;
    }
    ul li ul.dropdown2{
        background: white;
        position: absolute;
        top: 80px;
        left: 1670px;
        z-index: 900;
        display: none;
        grid-template-columns: auto;
        column-gap: 20px;
        row-gap: 13px;
        padding: 0 10px;
        text-align: center;
    }
    ul li:hover ul.dropdown2{
        display: grid;
    }
    ul li ul.dropdown2 li a{
        color: black;
        font-size: 20px;
        font-family: 'Montserrat';
    }
</style>
</style>
<body>

<#--<div class="error" style="display: block;" onclick="myfunc()">-->
<#--    <div class="error-panel">-->
<#--        <span class="h3">Произошла ошибка</span>-->
<#--        <ul>-->
<#--            <li>Извините, нам не удалось получить ответ от серверов. Повторите попытку позже.</li>-->
<#--        </ul>-->
<#--        <div class="error-close">-->
<#--            <input type="button" value="Закрыть сообщение">-->
<#--        </div>-->
<#--    </div>-->
<#--</div>-->

<#--<div class="error" style="display: block;" onclick="myfunc()">-->
<#--    <div class="error-panel">-->
<#--        <span class="h3">Вы успешно зарегистрировались</span>-->
<#--        <div class="error-close">-->
<#--            <input type="button" value="Закрыть сообщение">-->
<#--        </div>-->
<#--    </div>-->
<#--</div>-->

<#--<div class="error" style="display: block;" onclick="myfunc()">-->
<#--    <div class="error-panel">-->
<#--        <span class="h3">Сначала зарегистрировайтесь</span>-->
<#--        <div class="error-close">-->
<#--            <input type="button" value="Закрыть сообщение">-->
<#--        </div>-->
<#--    </div>-->
<#--</div>-->

<header>
    <div class="monclerlogo">
        <a href="HomePage"><img src="../../../../public/img/monclerlogo.webp" alt="123" onclick="clicklogo" class="moncler"></a>
    </div>
<#--    <?php-->
<#--            session_start();-->
<#--            if(isset($_SESSION['client_id'])){-->
<#--        ?>-->
    <div class="links">
        <ul>
            <li><a href="#">Новинки</a>
                <ul class="dropdown">
                    <li><h5>НОВЫЕ ПОСТУПЛЕНИЯ</h5></li>
                    <li><h5>MONCLER CURATORS</h5></li>
                    <li><h5>ИСТОРИИ</h5></li>
                    <li class="item_img"><img src="../../../../public/img/present.webp" alt="123" class="present"></li>
                    <li><a href="#">Новинки для Него</a></li>
                    <li><a href="#">Смотреть весь раздел</a></li>
                    <li><a href="#">Смотреть все истории</a></li>
                    <li><a href="#">Новинки для Нее</a></li>
                    <li><a href="#">Curators</a></li>
                    <li><a href="#">For the Love of Winter</a></li>
                    <li><a href="#">Moncler Karakorum</a></li>
                    <li><a href="#">Garbiñe Muguruza</a></li>
                    <li><a href="#">Inter x Moncler by Arsham</a></li>
                    <li><a href="#">Moncler x Palm Angels</a></li>
                    <li><a href="#">Shaun White</a></li>
                    <li><a href="#">Moncler x Palm Angels</a></li>
                    <li><a href="#">Inter x Moncler</a></li>
                    <li><a href="#">Nigel Sylvester</a></li>
                    <li><a href="#">Moncler x adidas Originals</a></li>
                    <li></li>
                    <li></li>
                    <li><a href="#">Moncler x Pharrell</a></li>
                </ul>
            </li>
            <li><a href="#">Одежды</a>
                <ul class="dropdown1">
                    <li><h5>ВЕРХНЯЯ ОДЕЖДА</h5></li>
                    <li><h5>ОБУВЬ</h5></li>
                    <li><h5>ДЛЯ ЛЫЖНОГО СПОРТА &<br>OUTDOOR</h5></li>
                    <li><h5>ПОДАРКИ</h5></li>
                    <li class="item_img1"><img src="../../../../public/img/present.webp" alt="123" class="present"></li>
                    <li><a href="#">Смотреть всю верхнюю одежду</a></li>
                    <li><a href="#">Смотреть всю обувь</a></li>
                    <li><a href="#">Лыжные куртки</a></li>
                    <li><a href="#">Подарки для Мужчин</a></li>
                    <li><a href="#">Все пуховики</a></li>
                    <li><a href="#">Кроссовки</a></li>
                    <li><a href="#">Лыжные брюки</a></li>
                    <li><a href="#">Small Gifts</a></li>
                    <li><a href="#">Длинные пуховики</a></li>
                    <li><a href="#">Сапоги</a></li>
                    <li><a href="#">Аксессуары для лыжы</a></li>
                    <li><a href="#">Персонализированные куртки</a></li>
                    <li><a href="#">Короткие пуховики</a></li>
                    <li><a href="#">Сандали и шлепанцы</a></li>
                    <li><a href="#">Лыжные шлемы</a></li>
                    <li></li>
                    <li><a href="#">Жилеты</a></li>
                    <li></li>
                    <li></li>
                    <li><h5>НОВИНКИ</h5></li>
                    <li><a href="#">Ветровки и плащи</a></li>
                    <li><h5>АКСЕССУАРЫ</h5></li>
                    <li></li>
                    <li><a href="#">Смотреть все новинки</a></li>
                    <li><a href="#">Пальто и куртки</a></li>
                    <li><a href="#">Смотреть все аксессуары</a></li>
                    <li></li>
                    <li><a href="#">Культовые модели</a></li>
                    <li><a href="#">Лыжные куртки</a></li>
                    <li><a href="#">Шляпы и шапки</a></li>
                    <li></li>
                    <li><a href="#">Осень-Зима</a></li>
                    <li></li>
                    <li><a href="#">Шарфы и перчатки</a></li>
                    <li></li>
                    <li></li>
                    <li><h5>ОДЕЖДА</h5></li>
                    <li><a href="#">Сумки</a></li>
                    <li></li>
                    <li></li>
                    <li><a href="#">Смотреть всю одежду</a></li>
                    <li></li>
                    <li></li>
                    <li></li>
                    <li><a href="Clothes.php?cat=Bottom">Низ</a></li>
                    <li></li>
                    <li></li>
                    <li></li>
                    <li><a href="Clothes.php?cat=Top">Верх</a></li>
                    <li></li>
                    <li></li>
                    <li></li>
                    <li><a href="#">Поло и футболки</a></li>
                    <li></li>
                    <li></li>
                    <li></li>
                    <li><a href="#">Джинсы</a></li>

                </ul>
            </li>
            <li><a href="#Clothes.php?cat=Shoes">Обувь</a></li>
            <li><a href="#Clothes.php?cat=Classic">Классика</a></li>
            <li><a href="#Clothes.php?cat=Deuce">Костюмы</a></li>
            <li class="space"></li>
        </ul>
    </div>
    <div class="links">
        <ul style="padding: 0; margin: 0;">
            <li style="padding-top: 25px; font-family: 'Montserrat';"><a href="">
<#--                    ${userName}-->
<#--                    <?php-->
<#--                    echo $_SESSION['client_name'];-->
<#--                ?>-->
                </a>
                <ul class="dropdown2" style="display:;">
                    <li><a href="profile">Профиль</a></li>
                    <li><a href="/Login">Выйти</a></li>
                </ul>

            </li>
            <li><a href="fav"><svg aria-hidden="true" class="pre-nav-design-icon" focusable="false" viewBox="0 0 24 24" role="img" width="28px" height="28px" fill="none"><path stroke="currentColor" stroke-width="1.5" d="M16.794 3.75c1.324 0 2.568.516 3.504 1.451a4.96 4.96 0 010 7.008L12 20.508l-8.299-8.299a4.96 4.96 0 010-7.007A4.923 4.923 0 017.205 3.75c1.324 0 2.568.516 3.504 1.451l.76.76.531.531.53-.531.76-.76a4.926 4.926 0 013.504-1.451"></path></svg></a></li>
            <li><a href="bucket"><svg aria-hidden="true" class="pre-nav-design-icon" focusable="false" viewBox="0 0 24 24" role="img" width="28px" height="28px" fill="none"><path stroke="currentColor" stroke-width="1.5" d="M8.25 8.25V6a2.25 2.25 0 012.25-2.25h3a2.25 2.25 0 110 4.5H3.75v8.25a3.75 3.75 0 003.75 3.75h9a3.75 3.75 0 003.75-3.75V8.25H17.5"></path></svg></a></li>
        </ul>
    </div>
</header>


<script>
    function myfunc(){
        document.getElementsByClassName("error")[0].style.display = "none";
    }
    if (performance.navigation.type === 1) {
        window.location.href = 'HomePage';
    }
</script>
</body>
</html>