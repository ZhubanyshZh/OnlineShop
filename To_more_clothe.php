<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Canelli co.</title>
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
    if(isset($_GET['id'])){
    require_once 'header.php';
    require_once 'Database.php';
    $product = $db->get_product($_GET['id']);
    ?>
        <div class="container mt-5">
            <div class="row">
                <div class="col-lg-6">
                    <article>
                        <figure class="mb-4"><img class="img-fluid rounded" src="img/<?=$product->get_photo()?>" style="height: 700px;" alt="..."></figure>
                    </article>
                    <section class="mb-5">
                        <div class="card bg-light">
                            <div class="card-body">
                                <!-- Comment form-->
                                <form class="mb-4" action="add_feedback.php" method="post">
                                    <textarea class="form-control" rows="3" placeholder="Join the discussion and leave a comment!" name="desc" required></textarea>
                                    <button type="submit" class="btn btn-secondary mt-2">Отправить</button>
                                    <input type="hidden" name="p_id" value="<?=$_GET['id']?>">
                                </form>
                                <?php
                                    $feedback = $db->get_feedback($_GET['id']);
                                    foreach($feedback as $f){
                                    ?>
                                <!-- Comment with nested comments-->
                                <div class="d-flex mb-4">
                                    <!-- Parent comment-->
                                        <div class="flex-shrink-0"><img class="rounded-circle" src="https://dummyimage.com/50x50/ced4da/6c757d.jpg" alt="..."></div>
                                        <div class="ms-3">
                                            <div class="fw-bold"><?= $f['client_name'] ?></div>
                                            <?= $f['feedback_desc'] ?>
                                        </div>
                                </div>
                                <?php } ?>
                            </div>
                        </div>
                    </section>
                </div>
                <?php
                ?>
                <!-- Side widgets-->
                <div class="col-lg-4">
                    <!-- Search widget-->
                    <div class="card mb-4">
                        <div class="card-header"><h5 class="card-title mb-3"><?=$product->get_name()?> <?=$product->get_price()?> KZT</h5> <?=$product->get_brand()?></div>
                        <div class="card-body">
                            <p class="card-text mb-0">Это стильная и удобная мужская одежда, созданная для комфорта и элегантности в повседневной жизни.</p>
                            <p class="card-text mb-0">Размер: <?=$product->get_size()?></p>
                        </div>
                    </div>
                    <!-- Side widget-->
                    <div class="card mb-4">
                        <div class="card-body" style="display: flex; justify-content: space-around;">
                            <?php
                            if($db->check_bucket($_GET['id'], $_SESSION['client_id'])){?>
                                <form action="bucket.php" method="post">
                                    <button type="submit" class="btn btn-danger">Перейти в корзину 🛒</button>
                                </form>
                            <?php }else{?>
                                <a href= "add_to_bucket.php?id=<?=$_GET['id']?>" class="btn btn-secondary">Добавить в корзину 🛒</a>
                            <?php }
                            ?>
                            <button class="btn btn-danger"> Оформить сейчас </button>
                            <?php
                            require_once 'db.php';
                            
                            if($db->get_fav_by_prod_id($_GET['id'], $_SESSION['client_id'])){?>
                                <button class="btn btn-danger" id="red" onclick="redclick()"><svg aria-hidden="true" class="pre-nav-design-icon" focusable="false" viewBox="0 0 24 24" role="img" width="15px" height="15px" fill="none"><path stroke="currentColor" stroke-width="1.5" d="M16.794 3.75c1.324 0 2.568.516 3.504 1.451a4.96 4.96 0 010 7.008L12 20.508l-8.299-8.299a4.96 4.96 0 010-7.007A4.923 4.923 0 017.205 3.75c1.324 0 2.568.516 3.504 1.451l.76.76.531.531.53-.531.76-.76a4.926 4.926 0 013.504-1.451"></path></svg></button>
                            <?php }else{?>
                                <button class="btn btn-dark" id="dark" onclick="darkclick()"><svg aria-hidden="true" class="pre-nav-design-icon" focusable="false" viewBox="0 0 24 24" role="img" width="15px" height="15px" fill="none"><path stroke="currentColor" stroke-width="1.5" d="M16.794 3.75c1.324 0 2.568.516 3.504 1.451a4.96 4.96 0 010 7.008L12 20.508l-8.299-8.299a4.96 4.96 0 010-7.007A4.923 4.923 0 017.205 3.75c1.324 0 2.568.516 3.504 1.451l.76.76.531.531.53-.531.76-.76a4.926 4.926 0 013.504-1.451"></path></svg></button>
                            <?php }
                            ?>
                            
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Bootstrap core JS-->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
        <!-- Core theme JS-->
        <script src="js/scripts.js"></script>
        <script>
            function darkclick(){
                
                document.getElementById("dark").classList.remove("btn-dark");
                document.getElementById("dark").classList.add("btn-danger");
                var xhr = new XMLHttpRequest();
                xhr.open("GET", "add_to_fav.php?id=<?php echo $_GET['id']; ?>&client_id=<?php echo $_SESSION['client_id']; ?>", true);
                xhr.onload = function() {
                    if (xhr.status === 200) {
                        // Успешный запрос - обновляем страницу
                        location.reload();
                    } else {
                        console.error("Ошибка запроса");
                    }
                };
                xhr.send();
            }

            function redclick(){
                
                document.getElementById("red").classList.remove("btn-danger");
                document.getElementById("red").classList.add("btn-dark");
                var xhr = new XMLHttpRequest();
                xhr.open("GET", "del_fav.php?id=<?php echo $_GET['id']; ?>&client_id=<?php echo $_SESSION['client_id']; ?>", true);
                xhr.onload = function() {
                    if (xhr.status === 200) {
                        // Успешный запрос - обновляем страницу
                        location.reload();
                    } else {
                        console.error("Ошибка запроса");
                    }
                };
                xhr.send();
            }
        </script>
        
        <?php }else if(isset($_SESSION['client_id'])){
            header('Location: Clothes.php');
        }else{
            header("Location: Login.php");
        } ?>
</body>
</html>