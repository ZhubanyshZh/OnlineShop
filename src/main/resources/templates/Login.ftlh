<!DOCTYPE html>
<html lang="en" xmlns:th="http://thymeleaf.org">
<head>
    <title>Canelli co. - login</title>
</head>
<link href='https://fonts.googleapis.com/css?family=Montserrat' rel='stylesheet'>
<link href="https://fonts.cdnfonts.com/css/bebas-neue" rel="stylesheet">
<link href='https://fonts.googleapis.com/css?family=Montserrat' rel='stylesheet'>
<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css'>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css"
      integrity="sha512-9usAa10IRO0HhonpyAIVpjrylPvoDwiPUiKdWk5t3PyolY1cOd4DSE0Ga+ri4AuTroPR5aQvXU9xC6qOPnzFeg=="
      crossorigin="anonymous" referrerpolicy="no-referrer"/>
<style>
    @import url('https://fonts.cdnfonts.com/css/bebas-neue');

    .body {
        width: auto;
        display: flex;
        justify-content: center;
        align-items: center;
        text-align: center;
        background: white;
        height: 80vh;
    }

    form label {
        width: 150px;
        clear: left;
        text-align: left;
        padding-right: 10px;
        color: #343434;
    }

    form input, label {
        float: left;
    }

    form {
        padding: 20px;
        border-radius: 10px;
        background: #fff;
    }


    form input {
        font-family: 'Montserrat';
        padding: 0 16px;
        width: 100%;
        height: 40px;
        padding-left: 16px;
        border-radius: 3px;
        border: 1px solid;
        box-sizing: border-box;
        color: black;
        line-height: 17px;
    }

    form a {
        font-family: 'Arial';
        font-weight: 600;
        color: black;
    }

    form p {
        font-family: 'Montserrat';
    }

    form h2 {
        margin: 0;
        color: #111;

    }

    form h2.h2 {
        font-family: 'Bebas Neue', sans-serif;
        font-size: 34px;
        font-weight: 900;
    }

    form .logo {
        font-family: 'Bebas Neue', sans-serif;
        font-weight: 400;
        font-size: 26px;
        margin-bottom: 30px;
    }

    form img.img1 {
        width: 35%;
    }

    input::placeholder {
        font-family: 'Montserrat';
        font-size: 14px;
    }

    button {
        background-color: #000;
        border: 1px solid #000;
        border-radius: 3px;
        color: #fff;
        cursor: pointer;
        display: block;
        font-family: TradeGothicforNike365-BdCn, Helvetica !important;
        font-size: 15px;
        margin: auto;
        height: 40px;
        width: 100%;
    }

    .error {
        background-color: rgba(0, 0, 0, 0.5);
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

    .h3 {
        font-size: 24px;
        font-weight: bold;
        font-family: 'Arial';
    }

    .error-panel ul {
        list-style-type: none;
    }

    .error-panel .error-close input {
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

    .error-panel .error-close input:hover {
        cursor: pointer;
    }

    /* HEADER CSS */
    /* body{
            background-image: url("img/monc.webp");
        } */
    header {
        display: flex;
        flex-wrap: nowrap;
        justify-content: space-between;
        align-items: center;
        padding: 0;
        height: 80px;
        transition: 0.5s;
        width: 100%;
        background: black;
    }

    header:hover {
        background: white;
    }

    header:hover .links ul li a {
        color: black;
    }

    header:hover svg {
        color: black;
    }

    header:hover .btn {
        color: black;
    }

    .btn {
        color: white;
        font-family: 'Montserrat';
        font-size: 13px;
    }

    .links {
        padding: 0;
        height: 80px;
    }

    .links ul {
        display: flex;
        list-style-type: none;
    }

    div.links li {
        padding: 18px 15px 26px 15px;
    }

    div.links ul li a {
        color: black;
        text-decoration: none;
        font-family: 'Montserrat';
        color: white;
        font-size: 15px;
    }

    .moncler {
        margin: 70px 0 18px 30px;
        width: 55%;
        cursor: pointer;
        color: white;
    }

    .monclerlogo {
        z-index: 999;
    }

    .links svg {
        border-radius: 50%;
        padding: 3px;
        color: white;
    }

    .links svg:hover {
        background: #e5e5e5;
        cursor: pointer;
    }

    div.actions {
        display: flex;
    }

    div.searchs {
        margin: 30px 2px 30px 2px;
    }

    .links ul li input {
        margin-top: 10px;
    }

    .links input {
        border-radius: 20px;
    }

    ul li ul.dropdown li {
        transition: 0.5s;
        display: block;
        padding: 0;
    }

    ul li ul.dropdown li a {
        color: black;
        font-size: 15px;
        font-family: 'Montserrat';
    }

    ul li ul.dropdown {
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

    ul li:hover ul.dropdown {
        display: grid;
    }

    img.present {
        width: 100%;
    }

    li.item_img {
        grid-row: 1 / 10;
        grid-column: 4 / 5;
        width: 60%;
        height: auto;
    }

    ul li ul.dropdown1 li {
        transition: 0.5s;
        display: block;
        padding: 0;
    }

    /*dropdown1*/
    ul li ul.dropdown1 h5 {
        padding: 0;
        margin: 0;
    }

    ul li ul.dropdown1 li a {
        color: black;
        font-size: 15px;
        font-family: 'Montserrat';
    }

    ul li ul.dropdown1 {
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

    ul li:hover ul.dropdown1 {
        display: grid;
    }

    /* img.present{
        width: 100%;
    } */
    li.item_img1 {
        grid-row: 1 / 16;
        grid-column: 5 / 6;
        width: 60%;
        height: auto;
    }

    ul li ul.dropdown2 li {
        transition: 0.5s;
        display: block;
        padding: 0;
    }

    ul li ul.dropdown2 {
        background: white;
        position: absolute;
        top: 80px;
        left: 1695px;
        z-index: 900;
        display: none;
        grid-template-columns: auto;
        column-gap: 20px;
        row-gap: 13px;
        padding: 0 10px;
        text-align: center;
    }

    ul li:hover ul.dropdown2 {
        display: grid;
    }

    ul li ul.dropdown2 li a {
        color: black;
        font-size: 20px;
        font-family: 'Montserrat';
    }
</style>
<body>
<header>
<div class="monclerlogo">
    <a href="HomePage"><img src="img/monclerlogo.webp" alt="Bekassyl" onclick="clicklogo" class="moncler"></a>
</div>
<div class="links">
    <ul>
        <li><a href="#">О компаний</a></li>
    </ul>
</div>
<div class="links">
    <ul>
        <li><a href="Login">Login</a></li>
        <li>|</li>
        <li><a href="SignUp">Signup</a></li>
    </ul>
</div>
</header>

<div class="body">
    <form th:method="POST" th:action="@{/Login}" th:object="{user}">

        <h2 class="h2">БУДЬ С НАМИ В МОДЕ</h2>
        <h2 class="logo">Men's Shop</h2>
        <p>Создай профиль члена клуба Canelli co. и получи<br> доступ к лучшим товарам Men's Shop, источникам<br>
            вдохновения и общению в сообществе.</p>
        <br><br>
        <input type="email" name="email" placeholder="Email" required><br><br><br>
        <input type="password" name="password" placeholder="Password" required><br><br><br><br>
        <button type="submit">ВОЙТИ</button>
        <p>Еще нет аккаунта? <a href="SignUp">Давай с нами</a></p>
    </form>
</div>
<script>
    function myfunc() {
        document.getElementsByClassName("error")[0].style.display = "none";
    }

    if (performance.navigation.type === 1) {
        window.location.href = 'Login';
    }
</script>
</body>
</html>