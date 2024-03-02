<?php
    try{
        $conn = new PDO("mysql:host=localhost;dbname=mens shop;","root","root");
    }catch(PDOException $e){
        echo "<h4 style='colorL red;'>". $e.getMessage()."</h4>";
    }

    function addClients($name, $phonenum, $birthday, $city, $email, $password){
        global $conn;
        $role = "user";

        $query = $conn->prepare("
            INSERT INTO `clients`(`client_id`, `client_name`, `client_phone_num`, `client_birthday`, `client_city`, `email`, `password`, `role`) 
            VALUES (NULL, :n, :p, :b, :c, :e, :ps , :r)
        ");

        $query->execute(array('n'=> $name, 'p'=> $phonenum, 'b'=> $birthday, 'c'=> $city, 'e'=>$email, 'ps'=> md5($password), 'r'=>$role));
    }

    function check($email, $password){
        global $conn;

        $result = $conn->prepare("
            SELECT * FROM `clients` WHERE `email`= :email AND `password` = :password
        ");

        $result->execute(["email"=>$email, "password"=>md5($password)]);
        $user = $result->fetch();
        return $user;
    }

    function istheresuchanaccount($email,$phonenum){
        global $conn;

        $result = $conn->prepare("
            SELECT * FROM `clients` WHERE `email`= :email or `client_phone_num` = :p
        ");

        $result->execute(["email"=>$email,"p"=>$phonenum]);
        $user = $result->fetch();
        return $user;
    }

    function get_all_products(){
        global $conn;
    
        $get_products = $conn->prepare("
            SELECT `product_id`,`product_name`, `product_brand`,`product_price`,`product_size`, `product_quantity`,`product_photo`, `cat_name`
            FROM `products` JOIN `categories` ON `cat_id` = `fk_cat_id`
        ");
        $get_products->execute();
        $products = $get_products->fetchAll();
        return $products;
    }
    function get_all_users(){
        global $conn;
    
        $get_users = $conn->prepare("
            SELECT * FROM `clients`
        ");
        $get_users->execute();
        $users = $get_users->fetchAll();
        return $users;
    }
?>