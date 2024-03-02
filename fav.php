<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Избранное</title>
    <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">

</head>
<style>
    header{
        background: black;
    }
</style>
<body>
    <?php
    session_start();
    require_once 'header.php';
    if(isset($_SESSION['client_id'])){
        require_once 'Database.php';
        $favorites = $db->get_fav_by_client_id($_SESSION['client_id']);
        if($favorites == null){?>
            <div style="justify-content: center; text-align: center; margin-top: 10%; height: 50vh;">
            <svg aria-hidden="true" class="pre-nav-design-icon" focusable="false" viewBox="0 0 24 24" role="img" width="200px" height="200px" fill="none"><path stroke="currentColor" stroke-width="1.5" d="M16.794 3.75c1.324 0 2.568.516 3.504 1.451a4.96 4.96 0 010 7.008L12 20.508l-8.299-8.299a4.96 4.96 0 010-7.007A4.923 4.923 0 017.205 3.75c1.324 0 2.568.516 3.504 1.451l.76.76.531.531.53-.531.76-.76a4.926 4.926 0 013.504-1.451"></path></svg>
                <h1><b>ЗДЕСЬ БУДУТ<br>ИЗБРАННЫЕ ТОВАРЫ</b></h1>
                <a href="Clothes.php" style="color: red;"><b>НАЧАТЬ ПОКУПКИ</b></a>
            </div>
        <? }else{?>
        <section class="py-5">
            <div class="container px-5 my-5">
                <div class="row gx-5 ">
                    <?php foreach($favorites as $f){    
                    ?>
                        <div class="col-lg-4 mb-3">
                            <div class="card h-100 shadow border-0">
                                <img class="card-img-top" src="img/<?=$f['product_photo']?>" alt="...">
                                <div class="card-body p-4">
                                    <a class="text-decoration-none link-dark stretched-link" href="To_more_clothe.php?id=<?= $f['product_id'] ?>"></a><h5 class="card-title mb-3"><?=$f['product_name']?> <?=$f['product_price']?> KZT</h5>
                                    <p class="card-text mb-0"><?=$f['product_brand']?> | Это стильная и удобная мужская одежда, созданная для комфорта и элегантности в повседневной жизни.</p>
                                    <p class="card-text mb-0">Размер: <?=$f['product_size']?></p>
                                </div>
                            </div>
                        </div>
                    <?php }?>
                </div>
            </div>
        </section>        
    <?php }
    }else{
        header("Location: Login.php?first_log");
    }
    require_once 'footer.php';
    ?>
</body>
</html>