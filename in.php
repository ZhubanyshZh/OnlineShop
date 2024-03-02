<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
<body class="d-flex flex-column h-100">
        <main class="flex-shrink-0">
            
            <!-- Features section-->
            <section class="py-5" id="features">
                <div class="container px-5 my-5">
                    <div class="row gx-5">
                        <div class="col-lg-4 mb-5 mb-lg-0"><h2 class="fw-bolder mb-0">Стиль – это способ сказать, кто вы есть, без слов. (Р. Зоуи)</h2></div>
                        <div class="col-lg-8">
                            <div class="row gx-5 row-cols-1 row-cols-md-2">
                                <div class="col mb-5 h-100">
                                    <div class="feature bg-primary bg-gradient text-white rounded-3 mb-3"><i class="bi bi-collection"></i></div>
                                    <h2 class="h5">НАША ЗАДАЧА</h2>
                                    <p class="mb-0">заключается в том, что мы дарим стиль и красоту</p>
                                </div>
                                <div class="col mb-5 h-100">
                                    <div class="feature bg-primary bg-gradient text-white rounded-3 mb-3"><i class="bi bi-building"></i></div>
                                    <h2 class="h5">БОЛЕЕ 10 ЛЕТ НА РЫНКЕ</h2>
                                    <p class="mb-0">наш магазин был основан в 2013 году 29 ноября</p>
                                </div>
                                <div class="col mb-5 mb-md-0 h-100">
                                    <div class="feature bg-primary bg-gradient text-white rounded-3 mb-3"><i class="bi bi-toggles2"></i></div>
                                    <h2 class="h5">МАСШТАБИРУЕМСЯ</h2>
                                    <p class="mb-0">данный момент мы работаем по всему Казахстану, но в дальнейшем планируем выйти на мировой рынок</p>
                                </div>
                                <div class="col h-100">
                                    <div class="feature bg-primary bg-gradient text-white rounded-3 mb-3"><i class="bi bi-toggles2"></i></div>
                                    <h2 class="h5">КАЧЕСТВО</h2>
                                    <p class="mb-0">более 5 миллионов довольных клиентов, мы работаем только на качество, наши товары актуальны в нашей стране  </p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
            <!-- Testimonial section-->
            <div class="py-5 bg-light">
                <div class="container px-5 my-5">
                    <div class="row gx-5 justify-content-center">
                        <div class="col-lg-10 col-xl-7">
                            <div class="text-center">
                                <div class="fs-4 mb-4 fst-italic">"Canelli co - мужской магазин для стильных мужчин. Наша цель - дарить людям радость благодаря нашему продукту."</div>
                                <div class="d-flex align-items-center justify-content-center">
                                    <img class="rounded-circle me-3" src="https://dummyimage.com/40x40/ced4da/6c757d" alt="...">
                                    <div class="fw-bold">
                                        Dias Kapashov
                                        <span class="fw-bold text-primary mx-1">/</span>
                                        Zhubanysh Zharylkassynov
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- Blog preview section-->
            <section class="py-5">
                <div class="container px-5 my-5">
                    <div class="row gx-5 justify-content-center">
                        <div class="col-lg-8 col-xl-6">
                            <div class="text-center">
                                <h2 class="fw-bolder">НАША КОЛЛЕКЦИЯ</h2>
                                <p class="lead fw-normal text-muted mb-5">Вы тут можете посмотреть новые джинсы, кофты, футболки и обувь.</p>
                            </div>
                        </div>
                    </div>
                    <div class="row gx-5">
                        <?php 
                        require_once 'Database.php';
                            $products = $db->get_all_products();
                            $i=0;
                        foreach ($products as $product) {
                        if($i==12) break;
                        $i++;
                        ?>
                            <div class="col-lg-4 mb-3">
                            <div class="card h-100 shadow border-0">
                                <img class="card-img-top" src="img/<?= $product->get_photo()?>" alt="...">
                                <div class="card-body p-4">
                                    <a class="text-decoration-none link-dark stretched-link" href="To_more_clothe.php?id=<?= $product->get_id() ?>" ><h5 class="card-title mb-3"><?=$product->get_name()?> <?=$product->get_price()?> KZT</h5></a>
                                    <p class="card-text mb-0"><?=$product->get_brand()?> | Это стильная и удобная мужская одежда, созданная для комфорта и элегантности в повседневной жизни.</p>
                                    <p class="card-text mb-0">Размер: <?=$product->get_size()?></p>
                                </div>
                            </div>
                        </div>
                        <?php }?>
                    </div>
                </div>
            </section>
        </main>
        <!-- Bootstrap core JS-->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
        <!-- Core theme JS-->
        <script src="js/scripts.js"></script>
    

</body>