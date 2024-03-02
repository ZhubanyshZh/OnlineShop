<?php
    class Database{
        private $conn;

        public function __construct(){
            try{
                $this->conn = new PDO("mysql:host=localhost;dbname=mens shop;","root","root");
            }catch(PDOException $e){
                echo "<h4 style='colorL red;'>". $e.getMessage()."</h4>";
            }
        }


        public function get_all_products(){
            $get = $this->conn->prepare("SELECT `product_id`,`product_name`, `product_brand`,`product_price`,`product_size`, `product_quantity`,`product_photo`, `cat_name` AS `cat`
            FROM `products` JOIN `categories` ON `cat_id` = `fk_cat_id`");
            $get->execute();
            $products = [];
            $rows = $get->fetchAll();
            foreach($rows as $row){
                $product = new Products($row['product_id'], $row['product_name'], $row['product_brand'], $row['product_price'], $row['product_size'], $row['product_quantity'], $row['product_photo'], $row['cat']);
                $products[] = $product;
            }
            return $products;
        }

        public function get_product($id){
            $get_product = $this->conn->prepare("SELECT * FROM `products` JOIN `categories` ON `cat_id` = `fk_cat_id` WHERE `product_id` = :id");
            $get_product->execute(["id"=>$id]);
            $row = $get_product->fetch();
            $product = new Products($row['product_id'], $row['product_name'], $row['product_brand'], $row['product_price'], $row['product_size'], $row['product_quantity'], $row['product_photo'], $row['cat_name']);

            return $product;
        }

        public function get_all_products_asc(){
            $get_products = $this->conn->prepare("
                SELECT `product_id`,`product_name`, `product_brand`,`product_price`,`product_size`, `product_quantity`,`product_photo`, `cat_name` AS `cat`
                FROM `products` JOIN `categories` ON `cat_id` = `fk_cat_id`
                ORDER BY `product_price` DESC
            ");
            $get_products->execute();
            $rows = $get_products->fetchAll();
            $products = [];
            foreach ($rows as $row) {
                $product = new Products($row['product_id'], $row['product_name'], $row['product_brand'], $row['product_price'], $row['product_size'], $row['product_quantity'], $row['product_photo'], $row['cat']);
                $products[] = $product;
            }
            
            return $products;
        }

        public function get_all_products_desc(){
            $get_products = $this->conn->prepare("
                SELECT `product_id`,`product_name`, `product_brand`,`product_price`,`product_size`, `product_quantity`,`product_photo`, `cat_name` AS `cat`
                FROM `products` JOIN `categories` ON `cat_id` = `fk_cat_id`
                ORDER BY `product_price` ASC
            ");
            $get_products->execute();
            $rows = $get_products->fetchAll();
            $products = [];
            foreach ($rows as $row) {
                $product = new Products($row['product_id'], $row['product_name'], $row['product_brand'], $row['product_price'], $row['product_size'], $row['product_quantity'], $row['product_photo'], $row['cat']);
                $products[] = $product;
            }
            
            return $products;
        }

        public function change_product($id, $name, $brand, $price, $size, $amount, $cat){
            $which_cat = $this->conn->prepare("SELECT cat_id FROM `categories` WHERE `cat_name` = :c");
            $which_cat->execute(["c" => $cat]);
            $cat_id = $which_cat->fetchColumn();
        
            $change = $this->conn->prepare("
                UPDATE `products` SET `product_name` = :pname , `product_brand` = :pbrand, `product_price` = :pprice, `product_size` = :psize, `product_quantity` = :pamount, `fk_cat_id` = :pcat
                WHERE `product_id` = :pid
            ");
            $change->execute([
                "pname" => $name,
                "pbrand" => $brand,
                "pprice" => $price,
                "psize" => $size,
                "pamount" => $amount,
                "pcat" => $cat_id,
                "pid" => $id
            ]);
        }

        public function delete_product($id){
            $delete_feedbacks = $this->conn->prepare(" DELETE FROM `feedback` WHERE `product_id` = :id");
            $delete_feedbacks->execute(["id" => $id]);

            $delete_buckets = $this->conn->prepare(" DELETE FROM `bucket` WHERE `client_id` = :id");
            $delete_buckets->execute(["id" => $id]);

            $delete_favs = $this->conn->prepare(" DELETE FROM `favorites` WHERE `client_id` = :id");
            $delete_favs->execute(["id" => $id]);

            $delete = $this->conn->prepare("DELETE FROM `products` WHERE `product_id` = :id");
            $delete->execute(["id" => $id]);
        }

        public function add_product($name, $brand, $price, $size, $amount, $photo, $cat){
            $which_cat = $this->conn->prepare("SELECT cat_id FROM `categories` WHERE `cat_name` = :c");
            $which_cat->execute(["c" => $cat]);
            $cat_id = $which_cat->fetchColumn();

            $add_product = $this->conn->prepare("INSERT INTO `products`(`product_name`, `product_brand`, `product_price`, `product_size`, `product_quantity`, `product_photo`, `fk_cat_id`) 
            VALUES (:pname,:pbrand,:pprice,:psize,:pquantity,:pphoto,:pcat_id)
            ");
            $add_product->execute([
                "pname" => $name,
                "pbrand" => $brand,
                "pprice" => $price,
                "psize" => $size,
                "pquantity" => $amount,
                "pphoto" => $photo,
                "pcat_id" => $cat_id
            ]);
        }

        public function addClients($name, $phonenum, $birthday, $city, $email, $password){
            $query = $this->conn->prepare("
                INSERT INTO `clients`(`client_id`, `client_name`, `client_phone_num`, `client_birthday`, `client_address`, `email`, `password`, `role`) 
                VALUES (NULL, :n, :p, :b, :c, :e, :ps , :r)
            ");

            $query->execute(array('n'=> $name, 'p'=> $phonenum, 'b'=> $birthday, 'c'=> $city, 'e'=>$email, 'ps'=> md5($password), 'r'=>"user"));
        }

        public function check($email, $password){    
            $result = $this->conn->prepare("SELECT * FROM `clients` WHERE `email`= :email AND `password` = :password");
    
            $result->execute(["email"=>$email, "password"=>md5($password)]);
            $user = $result->fetch();
            return $user;
        }

        public function istheresuchanaccount($email,$phonenum){    
            $result = $this->conn->prepare("SELECT * FROM `clients` WHERE `email`= :email or `client_phone_num` = :p");
    
            $result->execute(["email"=>$email,"p"=>$phonenum]);
            $user = $result->fetch();
            return $user;
        }

        public function get_all_users(){        
            $get_users = $this->conn->prepare("SELECT * FROM `clients`");
            $get_users->execute();
            $rows = $get_users->fetchAll();
            $users = [];
            foreach($rows as $row){
                $user = new Users($row['client_id'] ,$row['client_name'] ,$row['client_phone_num'] ,$row['client_address'] ,$row['client_birthday'] ,$row['email'] ,$row['password'], $row['role']);
                $users[] = $user;
            }

            return $users;
        }

        public function get_user($id){        
            $get_user = $this->conn->prepare("SELECT * FROM `clients` WHERE `client_id` = :id");
            $get_user->execute(["id"=> $id]);
            $u = $get_user->fetch();
            $user = new Users($u['client_id'] ,$u['client_name'] ,$u['client_phone_num'] ,$u['client_address'] ,$u['client_birthday'] ,$u['email'] ,$u['password'],$u['role']);
            return $user;
        }

        public function get_brands(){
            $get_brands = $this->conn->prepare("SELECT DISTINCT(`product_brand`) FROM `products`");
            $get_brands->execute();
            $brands = $get_brands->fetchAll();
            return $brands;
        }

        public function get_size(){
            $get_brands = $this->conn->prepare("SELECT DISTINCT(`product_size`) FROM `products`");
            $get_brands->execute();
            $brands = $get_brands->fetchAll();
            return $brands;
        }

        public function get_cats(){
            $get_cats = $this->conn->prepare("SELECT DISTINCT(`cat_name`) AS `cat_name` FROM `products` p JOIN `categories` c ON c.`cat_id` = p.`fk_cat_id`");
            $get_cats->execute();
            $cats = $get_cats->fetchAll();
            return $cats;
        }

        public function filter_products($product_brand, $product_size, $product_price_ot, $product_price_do, $cat){ 
            $filter_products = $this->conn->prepare(" 
                SELECT * FROM `products` p JOIN `categories` c ON c.`cat_id` = p.`fk_cat_id`
                WHERE (p.product_brand = :product_brand) OR (p.product_size = :product_size) OR (p.product_price BETWEEN :product_price_ot AND :product_price_do) OR (c.`cat_name` = :cat)
            "); 
            $filter_products->execute(['product_brand'=>$product_brand, 'product_size'=>$product_size, 'product_price_ot'=>$product_price_ot, 'product_price_do'=>$product_price_do ,"cat" => $cat]); 
            $rows = $filter_products->fetchAll(); 
            $products = []; 
            foreach ($rows as $row){ 
                $product = new Products($row['product_id'], $row['product_name'], $row['product_brand'], $row['product_price'], $row['product_size'], $row['product_quantity'], $row['product_photo'], $row['cat_name']); 
                $products[] = $product; 
            } 
     
            return $products; 
        }

        public function search_products($search){
            $search_products = $this->conn->prepare(" 
                SELECT p.*,c.cat_name as cat FROM products p JOIN categories c ON c.cat_id = p.fk_cat_id
                WHERE p.product_name = :search OR p.product_brand = :search OR c.cat_name = :search
            ");
            $search_products->execute(['search'=>$search]);
            $rows = $search_products->fetchAll(); 
            $products = []; 
            foreach ($rows as $row){ 
                $product = new Products($row['product_id'], $row['product_name'], $row['product_brand'], $row['product_price'], $row['product_size'], $row['product_quantity'], $row['product_photo'], $row['cat']); 
                $products[] = $product; 
            } 

            return $products; 
        }

        public function change_user($id, $name, $phone_num, $address , $birthday, $email, $password, $role){      
            $changes = $this->conn->prepare(" 
                UPDATE `clients` SET `client_name` = :cname , `client_phone_num` = :cphone_num, `client_address` = :caddress, `client_birthday` = :cbirthday, `email` = :cemail, `password` = :cpass, `role` = :crole 
                WHERE `client_id` = :cid 
                 "); 
            $changes->execute([ 
                "cname" => $name, 
                "cphone_num" => $phone_num, 
                "caddress" => $address, 
                "cbirthday" => $birthday, 
                "cemail" => $email, 
                "cpass" => $password, 
                "crole" => $role,
                "cid" => $id 
            ]); 
        }
        
        public function delete_user($id){             
            $delete_feedbacks = $this->conn->prepare(" DELETE FROM `feedback` WHERE `client_id` = :id");
            $delete_feedbacks->execute(["id" => $id]);

            $delete_buckets = $this->conn->prepare(" DELETE FROM `bucket` WHERE `client_id` = :id");
            $delete_buckets->execute(["id" => $id]);

            $delete_favs = $this->conn->prepare(" DELETE FROM `favorites` WHERE `client_id` = :id");
            $delete_favs->execute(["id" => $id]);
    
            $delete = $this->conn->prepare("DELETE FROM `clients` WHERE `client_id` = :id"); 
            $delete->execute(["id" => $id]);
        }

        public function changePassword($password, $id){ 
            $query = $this->conn->prepare(" UPDATE `clients` SET `password` = :password WHERE `client_id` = :id "); 
            $query->execute(["password"=>md5($password), "id"=>$id]);
        }

        public function check_pass($pass , $id){    
            $check = $this->conn->prepare("SELECT * FROM `clients` WHERE `client_id` = :id AND `password` = :password"); 
            $check->execute(["id"=>$id ,"password"=>md5($pass)]); 
            $result = $check->fetch();
            if(!empty($result)){
                return true;
            }else return false;
        }

        public function add_to_bucket($p_id, $c_id){            
            $addback = $this->conn->prepare("INSERT INTO `bucket`(`product_id`,`client_id`,`amount`) VALUES(:p_id, :c_id, :amount)");
            $addback->execute(["p_id" => $p_id, "c_id" => $c_id, "amount" => 1]);
        }

        public function get_all_from_bucket($id){    
            $get = $this->conn->prepare("SELECT p.`product_id`, p.`product_name`, SUM(b.`amount`) as `amount`, p.`product_price`, p.`product_size`, p.`product_brand`, p.`product_photo` 
            FROM `bucket` b JOIN `products` p ON p.`product_id` = b.`product_id` WHERE `client_id` = :id GROUP BY p.`product_id`, p.`product_name` , p.`product_price`, p.`product_size`, p.`product_brand`, p.`product_photo`");
            $get->execute(["id" => $id]);
            $rows = $get->fetchAll();
            $bucket = [];
            foreach($rows as $row){
                $product = new Bucket($row['product_id'], $row['product_name'], $row['product_brand'], $row['product_price'], $row['product_size'], $row['product_photo'],$row['amount']);
                $bucket[] = $product;
            }
            return $bucket;
        }

        public function update_backet($c_id, $p_id, $amount){    
            if($amount == 0){
                $delete = $this->conn->prepare("DELETE FROM `bucket` WHERE `client_id` = :c_id AND `product_id` = :p_id");
                $delete->execute(["c_id"=> $c_id, "p_id"=> $p_id]);
            }else{
                $update = $this->conn->prepare("UPDATE `bucket` SET `amount` = :amount WHERE `client_id` = :c_id AND `product_id` = :p_id");
                $update->execute(["c_id"=> $c_id, "p_id" => $p_id, "amount" => $amount]);
            }
        }

        public function get_feedback($id){        
            $get_feedback = $this->conn->prepare("SELECT `client_name`, `feedback_desc` FROM `feedback` f JOIN `clients` c ON c.`client_id` = f.`client_id` WHERE `product_id` = :id");
            $get_feedback->execute(["id" => $id]);
            $feedback = $get_feedback->fetchAll();
            return $feedback;
        }

        public function add_feedback($desc, $p_id, $c_id){
            $add_feedback = $this->conn->prepare("INSERT INTO `feedback`(`client_id`, `product_id`, `feedback_desc`) VALUES (:c_id , :p_id, :f_desc)");
            $add_feedback->execute(["c_id" => $c_id, "p_id" => $p_id, "f_desc" => $desc]);
        }

        public function get_fav_by_client_id($id){    
            $get = $this->conn->prepare("SELECT p.`product_id`, p.`product_name`, p.`product_price`, p.`product_size`, p.`product_brand`, p.`product_photo` 
            FROM `favorites` f JOIN `products` p ON f.`product_id` = p.`product_id` WHERE `client_id` = :c_id");
            $get->execute(["c_id" => $id]);
            $favs = $get->fetchAll();
            return $favs;
        }

        public function get_fav_by_prod_id($p_id , $c_id){
            $get = $this->conn->prepare("SELECT * FROM `favorites` WHERE `client_id` = :c_id AND `product_id` = :p_id");
            $get->execute(["c_id" => $c_id , "p_id" => $p_id]);
    
            $res = $get->fetch();
            if(!empty($res)){
                return true;
            }else{
                return false;
            }
        }

        public function delete_fav_by_prod_id($p_id , $c_id){    
            $del = $this->conn->prepare("DELETE FROM `favorites` WHERE `client_id` = :c_id AND `product_id` = :p_id");
            $del->execute(["c_id" => $c_id , "p_id" => $p_id]);
        }
    
        public function set_to_fav($p_id , $c_id){    
            $set = $this->conn->prepare("INSERT INTO `favorites`(`client_id` , `product_id`) VALUES(:c_id, :p_id)");
            $set->execute(["p_id" => $p_id , "c_id" => $c_id ]);
        }
    
        public function check_bucket($p_id , $c_id){    
            $check = $this->conn->prepare("SELECT * FROM `bucket` WHERE `client_id` = :c_id AND `product_id` = :p_id");
            $check->execute(["c_id" => $c_id, "p_id" => $p_id]);
    
            $res = $check->fetch();
            if(!empty($res)){
                return true;
            }else{
                return false;
            }
        }
    }

    class Products{
        protected $product_id;
        protected $product_name;
        protected $product_brand;
        protected $product_price;
        protected $product_size;
        protected $product_quantity;
        protected $product_photo;
        protected $cat;

        public function __construct($product_id, $product_name, $product_brand, $product_price, $product_size, $product_quantity, $product_photo, $cat){
            $this->product_id = $product_id;
            $this->product_name = $product_name;
            $this->product_brand = $product_brand;
            $this->product_price = $product_price;
            $this->product_size = $product_size;
            $this->product_quantity = $product_quantity;
            $this->product_photo = $product_photo;
            $this->cat = $cat;
        }

        public function get_id(){
            return $this->product_id;
        }

        public function get_name(){
            return $this->product_name;
        }

        public function get_brand(){
            return $this->product_brand;
        }

        public function get_price(){
            return $this->product_price;
        }

        public function get_size(){
            return $this->product_size;
        }

        public function get_quantity(){
            return $this->product_quantity;
        }

        public function get_photo(){
            return $this->product_photo;
        }

        public function get_cat(){
            return $this->cat;
        }
    }

    class Users{
        private $user_id;
        private $user_name;
        private $user_phone_num;
        private $user_address;
        private $user_birthday;
        private $email;
        private $password;
        private $role; 

        public function __construct($user_id ,$user_name ,$user_phone_num ,$user_address ,$user_birthday ,$email ,$password, $role){
            $this->user_id = $user_id;
            $this->user_name = $user_name;
            $this->user_phone_num = $user_phone_num;
            $this->user_address = $user_address;
            $this->user_birthday = $user_birthday;
            $this->email = $email;
            $this->password = $password;
            $this->role = $role;
        }

        public function get_id(){
            return $this->user_id;
        }
        public function get_name(){
            return $this->user_name;
        }
        public function get_phone_num(){
            return $this->user_phone_num;
        }
        public function get_address(){
            return $this->user_address;
        }
        public function get_birthday(){
            return $this->user_birthday;
        }
        public function get_email(){
            return $this->email;
        }
        public function get_password(){
            return $this->password;
        }
        public function get_role(){
            return $this->role;
        }
    }

    class Bucket extends Products{
        private $amount;

        public function __construct($product_id, $product_name, $product_brand, $product_price, $product_size, $product_photo,$amount){
            parent::__construct($product_id, $product_name, $product_brand, $product_price, $product_size, null, $product_photo, null);
            $this->amount = $amount;
        }

        public function get_amount(){
            return $this->amount;
        }
    }

    $db = new Database();
    // $db->add_product('Sport Suit', 'Nike', 55000, 48, 3, 'photo.png', 'Deuce');

    // $product = $db->get_product(34);
    // print_r($product);
    // $db->delete_product(34);
    // print_r($product);
    // print_r($product);
    // $products = $db->get_all_products();
    // foreach($products as $p){
    //     echo($p->get_id())."<br>";
    // }

    // $products = $db->get_all_products_desc();
    // foreach($products as $p){
    //     echo($p->get_price())."<br>";
    // }

    // $db->addClients('Madiyar', '87787894545', '2005-07-20', 'Aqtobe', 'madiyar@narxoz.kz', 'MmMmMm.123');
    // echo  $db->check('madiyar@narxoz.kz', 'MmMmMm.123');
    // print_r($db->istheresuchanaccount('zhubanysh@narxoz.kz' ,'87754560099'));

    // $users = $db->get_all_users();

    // foreach($users as $user){
    //     echo $user->get_name();
    //     echo "<br>";
    // }

    // print_r($db->get_user(2));

    // $db->change_user(2, 'Dias', '87789999999', 'Qaragandy' , '2005-11-29', 'kapashov@narxoz.kz', 'Dias.09', 'admin');

    // $db->delete_user(56);

    // $db->changePassword('AAAaaa.123', 55);

    // echo $db->check_pass('Zhubanysh.05' , 54);

    // $db->add_to_bucket(34, 2);
    // $a = $db->get_all_from_bucket(55);
    // foreach($a as $b){
    //     echo $b->get_name();
    // }
?>