<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Bucket</title>
    <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <style>
    header{
        background: black;
    }
</style>
</head>
<body>
    <?php
    session_start();
    if(isset($_SESSION['client_id'])){
    require_once 'header.php';
    require_once 'Database.php';
    $bucket = $db->get_all_from_bucket($_SESSION['client_id']);
    if($bucket == null){?>
    <div style="justify-content: center; text-align: center; margin-top: 10%; height: 50vh;">
    <svg aria-hidden="true" class="pre-nav-design-icon" focusable="false" viewBox="0 0 24 24" role="img" width="200px" height="200px" fill="none"><path stroke="currentColor" stroke-width="1.5" d="M8.25 8.25V6a2.25 2.25 0 012.25-2.25h3a2.25 2.25 0 110 4.5H3.75v8.25a3.75 3.75 0 003.75 3.75h9a3.75 3.75 0 003.75-3.75V8.25H17.5"></path></svg>
        <h1><b>ВАША КОРЗИНА ПУСТА</b></h1>
        <a href="Clothes.php" style="color: red;"><b>НАЧАТЬ ПОКУПКИ</b></a>
    </div>
    <?php }else{?>
    <section class="py-5">
        <div class="container px-5 my-5">
            <div class="row gx-5 ">
                <?php foreach($bucket as $b){?>
                    <div class="col-lg-4 mb-3">
                        <div class="card h-100 shadow border-0">
                            <img class="card-img-top" src="img/<?=$b->get_photo()?>" alt="...">
                            <div class="card-body p-4">
                                <a class="text-decoration-none link-dark" href="To_more_clothe.php?id=<?= $b->get_id() ?>"></a><h5 class="card-title mb-3"><?=$b->get_name()?> <?=$b->get_price() * $b->get_amount()?> KZT</h5>
                                <p class="card-text mb-0"><?=$b->get_brand()?> | Это стильная и удобная мужская одежда, созданная для комфорта и элегантности в повседневной жизни.</p>
                                <p class="card-text mb-0">Размер: <?=$b->get_size()?></p>
                                <div style="display:flex; justify-content: end;">
                                    <form action="ChangeBasket_minus.php" method="post" style="display:flex; justify-content: end;">
                                        <button class="btn btn-danger" type="submit">-</button>
                                        <input type="hidden" value="<?=$b->get_id()?>" name="id">
                                        <input type="hidden" value="<?=$b->get_amount()?>" name="amount">
                                    </form>
                                    <input style="margin: 0 5px;" type="number" name="amount" max="3" min="0" readonly value="<?=$b->get_amount()?>">
                                    <form action="ChangeBasket_plus.php" method="post">
                                        <input type="hidden" value="<?=$b->get_id()?>" name="id">
                                        <input type="hidden" value="<?=$b->get_amount()?>" name="amount">
                                        <button class="btn btn-dark" type="submit">+</button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                <?php } ?>
            </div>
        </div>
    </section>
    <?php }
}else{
        header("Location: Login.php?log_first");
    }
    require_once 'footer.php';
    ?> 
</body>
</html>