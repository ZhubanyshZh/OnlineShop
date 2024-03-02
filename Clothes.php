<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Clothes</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
<style>
    header{
        background: black;
    }
    
</style>
</head>
<body>
    <?php
    require_once 'header.php';
            require_once 'Database.php';
            if(isset($_GET['desc'])){
                $products = $db->get_all_products_desc();
            }elseif(isset($_GET['asc'])){
                $products = $db->get_all_products_asc();
            }
            elseif (isset($_GET['brand']) || isset($_GET['size']) || isset($_GET['price_ot']) || isset($_GET['price_do']) || isset($_GET['cat'])) {
                $products = $db->filter_products($_GET['brand'], $_GET['size'], $_GET['price_ot'], $_GET['price_do'], $_GET['cat']);
            }elseif (isset($_GET['search'])) {
                $products = $db->search_products($_GET['search']);
            }
            else{
                $products = $db->get_all_products();
            }
            $brands = $db->get_brands();
            $sizes = $db->get_size();
            $cats = $db->get_cats();
            ?>
            <div class="modal-footer" style="background: gray; padding: 8px;">
                <form action="Clothes.php" method="get" style="display: flex;">
                    <div class="col-7" style="margin-right: 7px;">
                        <input type="text" name="search" class="form-control" required placeholder="Search" value="<?= $_GET['search'] ?>">
                    </div>
                    <button type="submit" class="btn btn-outline-danger" style="margin-right: 7px;">Search</button>
                </form>    
            
                <a href="Clothes.php?cat=<?=$_GET['cat']?>&asc" class="btn btn-dark" style="margin-right: 7px;">По убыванию</a>
                <a href="Clothes.php?cat=<?=$_GET['cat']?>&desc" class="btn btn-danger" style="margin-right: 7px;">По возрастанию</a>
                <button type="submit" class="btn btn-secondary" data-bs-toggle="modal" data-bs-target="#staticBackdrop_">Фильтр</button>
                <!-- Modal -->
                <div class="modal fade" id="staticBackdrop_" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                    <div class="modal-header">
                        <h1 class="modal-title fs-5" id="staticBackdropLabel">Фильтр</h1>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <form action="Clothes.php" method="GET">
                            <div class="mb-3">
                            <label><h6>КАТЕГОРИИ:</h6></label><br>
                            <?php 
                            foreach($cats as $b){?>
                                <div class="btn-group" role="group" aria-label="Basic radio toggle button group" style="margin: 5px;">
                                <input type="radio" class="btn-check" name="cat" id="<?=$b['cat_name']?>" autocomplete="off" value="<?=$b['cat_name']?>">
                                <label class="btn btn-outline-warning" for="<?=$b['cat_name']?>" style="color: black;"><?=$b['cat_name']?></label>
                                </div>
                            <?php }
                            ?>
                                <br><br><label><h6>БРЕНДЫ:</h6></label><br>
                            <?php 
                            foreach($brands as $b){?>
                                <div class="btn-group" role="group" aria-label="Basic radio toggle button group" style="margin: 5px;">
                                <input type="radio" class="btn-check" name="brand" id="<?=$b['product_brand']?>" autocomplete="off" value="<?=$b['product_brand']?>">
                                <label class="btn btn-outline-warning" for="<?=$b['product_brand']?>" style="color: black;"><?=$b['product_brand']?></label>
                                </div>
                            <?php }
                            ?>
                            <br><br>
                            <label><h6>ЦЕНЫ:</h6></label><br>
                            <input type="input" class="form-input" id="a" placeholder="От" name="price_ot">
                            <input type="input" class="form-input" id="b" placeholder="До" name="price_do"><br><br>
                            <label><h6>РАЗМЕРЫ:</h6></label><br>
                            <?php 
                            foreach($sizes as $b){?>
                                <div class="btn-group" role="group" aria-label="Basic radio toggle button group" style="margin: 5px;">
                                <input type="checkbox" class="btn-check" name="size" id="<?=$b['product_size']?>" autocomplete="off" value="<?=$b['product_size']?>">
                                <label class="btn btn-outline-warning" for="<?=$b['product_size']?>" style="color: black;"><?=$b['product_size']?></label>
                                </div>
                            <?php }
                            ?>

                            </div>
                            <button type="submit" class="btn btn-dark">Показать</button>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Закрыть</button>
                    </div>
                    </div>
                </div>
                </div>
                <!-- Modal -->
            </div>
    <section class="py-5">
        <div class="container px-5 my-5">
            <div class="row gx-5 ">
            <?php 
            if($products == null){?>
                <div style="justify-content: center; text-align: center; margin-top: 10%; height: 50vh;">
                <svg aria-hidden="true" class="pre-nav-design-icon" focusable="false" viewBox="0 0 24 24" role="img" width="200px" height="200px" fill="none"><path stroke="currentColor" stroke-width="1.5" d="M8.25 8.25V6a2.25 2.25 0 012.25-2.25h3a2.25 2.25 0 110 4.5H3.75v8.25a3.75 3.75 0 003.75 3.75h9a3.75 3.75 0 003.75-3.75V8.25H17.5"></path></svg>
                    <h1><b>ТОВАР НЕ НАЙДЕН!</b></h1>
                    <a href="Clothes.php" style="color: red;"><b>НАЗАД</b></a>
                </div>
            <?php }else{
            foreach ($products as $product) {
                
                if($product->get_cat()=='Top' && $_GET['cat']=='Top'){?>
                    <div class="col-lg-4 mb-3">
                        <div class="card h-100 shadow border-0">
                            <img class="card-img-top" src="img/<?=$product->get_photo()?>" alt="...">
                            <div class="card-body p-4">
                                <a class="text-decoration-none link-dark stretched-link" href="To_more_clothe.php?id=<?= $product->get_id() ?>"><h5 class="card-title mb-3"><?=$product->get_name()?> <?=$product->get_price()?> KZT</h5></a>
                                <p class="card-text mb-0"><?=$product->get_brand()?> | Это стильная и удобная мужская одежда, созданная для комфорта и элегантности в повседневной жизни.</p>
                                <p class="card-text mb-0">Размер: <?=$product->get_size()?></p>
                            </div>
                        </div>
                    </div>
                <? }elseif($_GET['cat']=='Bottom' && $product->get_cat()=='Bottom'){?>
                    <div class="col-lg-4 mb-3">
                            <div class="card h-100 shadow border-0">
                                <img class="card-img-top" src="img/<?=$product->get_photo()?>" alt="...">
                                <div class="card-body p-4">
                                    <a class="text-decoration-none link-dark stretched-link" href="To_more_clothe.php?id=<?= $product->get_id() ?>" ><h5 class="card-title mb-3"><?=$product->get_name()?> <?=$product->get_price()?> KZT</h5></a>
                                    <p class="card-text mb-0"><?=$product->get_brand()?> | Это стильная и удобная мужская одежда, созданная для комфорта и элегантности в повседневной жизни.</p>
                                    <p class="card-text mb-0">Размер: <?=$product->get_size()?></p>
                                </div>
                            </div>
                        </div>
                <? }elseif($_GET['cat']=='Shoes' && $product->get_cat()=='Shoes'){?>
                    <div class="col-lg-4 mb-3">
                            <div class="card h-100 shadow border-0">
                                <img class="card-img-top" src="img/<?=$product->get_photo()?>" alt="...">
                                <div class="card-body p-4">
                                    <a class="text-decoration-none link-dark stretched-link" href="To_more_clothe.php?id=<?= $product->get_id() ?>"><h5 class="card-title mb-3"><?=$product->get_name()?> <?=$product->get_price()?> KZT</h5></a>
                                    <p class="card-text mb-0"><?=$product->get_brand()?> | Это стильная и удобная мужская одежда, созданная для комфорта и элегантности в повседневной жизни.</p>
                                    <p class="card-text mb-0">Размер: <?=$product->get_size()?></p>
                                </div>
                            </div>
                        </div>
                <? }elseif($_GET['cat']=='Classic' && $product->get_cat()=='Classic'){ ?>
                    <div class="col-lg-4 mb-3">
                            <div class="card h-100 shadow border-0">
                                <img class="card-img-top" src="img/<?=$product->get_photo()?>" alt="...">
                                <div class="card-body p-4">
                                    <a class="text-decoration-none link-dark stretched-link" href="To_more_clothe.php?id=<?= $product->get_id() ?>"><h5 class="card-title mb-3"><?=$product->get_name()?> <?=$product->get_price()?> KZT</h5></a>
                                    <p class="card-text mb-0"><?=$product->get_brand()?> | Это стильная и удобная мужская одежда, созданная для комфорта и элегантности в повседневной жизни.</p>
                                    <p class="card-text mb-0">Размер: <?=$product->get_size()?></p>
                                </div>
                            </div>
                        </div>
                <? }elseif($_GET['cat']=='Deuce' && $product->get_cat()=='Deuce') { ?>
                    <div class="col-lg-4 mb-3">
                            <div class="card h-100 shadow border-0">
                                <img class="card-img-top" src="img/<?=$product->get_photo()?>" alt="...">
                                <div class="card-body p-4">
                                    <a class="text-decoration-none link-dark stretched-link" href="To_more_clothe.php?id=<?= $product->get_id() ?>"><h5 class="card-title mb-3"><?=$product->get_name()?> <?=$product->get_price()?> KZT</h5></a>
                                    <p class="card-text mb-0"><?=$product->get_brand()?> | Это стильная и удобная мужская одежда, созданная для комфорта и элегантности в повседневной жизни.</p>
                                    <p class="card-text mb-0">Размер: <?=$product->get_size()?></p>
                                </div>
                            </div>
                        </div>
                <?php }else if($_GET['cat']==''){ ?>
                    <div class="col-lg-4 mb-3">
                            <div class="card h-100 shadow border-0">
                                <img class="card-img-top" src="img/<?=$product->get_photo()?>" alt="...">
                                <div class="card-body p-4">
                                    <a class="text-decoration-none link-dark stretched-link" href="To_more_clothe.php?id=<?= $product->get_id() ?>"><h5 class="card-title mb-3"><?=$product->get_name()?> <?=$product->get_price()?> KZT</h5></a>
                                    <p class="card-text mb-0"><?=$product->get_brand()?> | Это стильная и удобная мужская одежда, созданная для комфорта и элегантности в повседневной жизни.</p>
                                    <p class="card-text mb-0">Размер: <?=$product->get_size()?></p>
                                </div>
                            </div>
                        </div>
                <?php }else if(isset($_GET['filter'])){ ?>
                    <div class="col-lg-4 mb-3">
                            <div class="card h-100 shadow border-0">
                                <img class="card-img-top" src="img/<?=$product->get_photo()?>" alt="...">
                                <div class="card-body p-4">
                                    <a class="text-decoration-none link-dark stretched-link" href="To_more_clothe.php?id=<?= $product->get_id() ?>"><h5 class="card-title mb-3"><?=$product->get_name()?> <?=$product->get_price()?> KZT</h5></a>
                                    <p class="card-text mb-0"><?=$product->get_brand()?> | Это стильная и удобная мужская одежда, созданная для комфорта и элегантности в повседневной жизни.</p>
                                    <p class="card-text mb-0">Размер: <?=$product->get_size()?></p>
                                </div>
                            </div>
                        </div>
                <?php }
            }
        }
        ?></div>
    </div>
</section>
<?php
require_once 'footer.php';
?>
</body>
</html>