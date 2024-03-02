<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home Page</title>
</head>
<style>
    body{
        background : white;
    }
    img.photo{
        height: auto;
        position: relative;
        top: -98px;
        z-index: -1;
        width: 100%;
    }
    header{
        background: hsla(0, 0, 0, 0);
    }
    .options{
        display: flex;
        justify-content: center;
        margin-top: 50px;
    }
    .options ul{
        display: flex;
        list-style-type: none;
    }
    .options ul li{
        padding: 10px 8px;
        position: relative;
        top: -140px;
    }
    ul li p:hover{
        cursor:pointer;
    }
    .optionphoto{
        display: flex;
        width: 100%;
        position: relative;
        top: -170px;
    }
    .optionphoto img{
        width: 100%;
        padding: 5px;
    }
    .optionphoto ul li{
        list-style-type: none;
        margin-left: -40px;
        padding: 30px;
    }
    .optionphoto img:hover{
        transform: scale(1.02,1.02);
        transition: 0.5s linear;
        cursor: pointer;
    }
    .optionphoto2{
        display: none;
        width: 100%;
        position: relative;
        top: -170px;
    }
    .optionphoto2 img{
        width: 100%;
        padding: 5px;
    }
    .optionphoto2 ul li{
        list-style-type: none;
        margin-left: -40px;
        padding: 30px;
    }
    .optionphoto2 img:hover{
        transform: scale(1.02,1.02);
        transition: 0.5s linear;
        cursor: pointer;
    }
    .optionphoto3{
        display: none;
        width: 100%;
        position: relative;
        top: -170px;
    }
    .optionphoto3 img{
        width: 100%;
        padding: 5px;
    }
    .optionphoto3 ul li{
        list-style-type: none;
        margin-left: -40px;
        padding: 30px;
    }
    .optionphoto3 img:hover{
        transform: scale(1.02,1.02);
        transition: 0.5s linear;
        cursor: pointer;
    }
    .winter{
        text-decoration: underline;
    }
</style>
<body>
    <?php
        require_once 'header.php';
    ?>
    <img class="photo" src="img/Home.png" alt="123">
    <h1 style="position: relative; top: -90px; text-align: center;">НАШИ ЛУЧШИЕ ВАРИАНТЫ ДЛЯ ВАС</h1>
    <div class="options">
        <ul>
            <li><p class="winter" onclick="clickp()">WINTER</p></li>
            <li><p class="summer" onclick="clickp1()">SUMMER</p></li>
            <li><p class="classic" onclick="clickp2()">CLASSIC</p></li>
        </ul>
    </div>
    <div class="optionphoto">
        <ul style="display: flex;">
            <li><img src="img/winter1.png" alt=""></li>
            <li><img src="img/winter1.png" alt=""></li>
            <li><img src="img/winter1.png" alt=""></li>
            <li><img src="img/winter1.png" alt=""></li>
        </ul>
    </div>
    <div class="optionphoto2">
        <ul style="display: flex;">
            <li><img src="img/summer1.png" alt=""></li>
            <li><img src="img/summer1.png" alt=""></li>
            <li><img src="img/summer1.png" alt=""></li>
            <li><img src="img/summer1.png" alt=""></li>
        </ul>
    </div>
    <div class="optionphoto3">
        <ul style="display: flex;">
            <li><img src="img/classic1.png" alt=""></li>
            <li><img src="img/classic1.png" alt=""></li>
            <li><img src="img/classic1.png" alt=""></li>
            <li><img src="img/classic1.png" alt=""></li>
        </ul>
    </div>
    <?php
        require_once 'in.php';
        require_once 'footer.php';
    ?>
    <script>
        function clickp(){
            document.getElementsByClassName("winter")[0].style.textDecoration = "underline";
            document.getElementsByClassName("summer")[0].style.textDecoration = "none";
            document.getElementsByClassName("classic")[0].style.textDecoration = "none";
            var a = document.getElementsByClassName('optionphoto');
            var a1 = document.getElementsByClassName('optionphoto2');
            var a2 = document.getElementsByClassName('optionphoto3');
            a[0].style.display = "flex";
            a1[0].style.display = "none";
            a2[0].style.display = "none";
        }
        function clickp1(){
            document.getElementsByClassName("summer")[0].style.textDecoration = "underline";
            document.getElementsByClassName("winter")[0].style.textDecoration = "none";
            document.getElementsByClassName("classic")[0].style.textDecoration = "none";
            var a = document.getElementsByClassName('optionphoto');
            var a1 = document.getElementsByClassName('optionphoto2');
            var a2 = document.getElementsByClassName('optionphoto3');
            a[0].style.display = "none";
            a1[0].style.display = "flex";
            a2[0].style.display = "none";
        }
        function clickp2(){
            document.getElementsByClassName("classic")[0].style.textDecoration = "underline";
            document.getElementsByClassName("summer")[0].style.textDecoration = "none";
            document.getElementsByClassName("winter")[0].style.textDecoration = "none";
            var a = document.getElementsByClassName('optionphoto');
            var a1 = document.getElementsByClassName('optionphoto2');
            var a2 = document.getElementsByClassName('optionphoto3');
            a[0].style.display = "none";
            a1[0].style.display = "none";
            a2[0].style.display = "flex";
        }
    </script>
</body>
</html>