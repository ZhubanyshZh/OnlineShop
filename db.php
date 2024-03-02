<?php
    try{
        $conn = new PDO("mysql:host=localhost;dbname=mens shop;","root","root");
    }catch(PDOException $e){
        echo "<h4 style='colorL red;'>". $e.getMessage()."</h4>";
    }
// done
    function addClients($name, $phonenum, $birthday, $city, $email, $password){
        global $conn;
        $role = "user";

        $query = $conn->prepare("
            INSERT INTO `clients`(`client_id`, `client_name`, `client_phone_num`, `client_birthday`, `client_address`, `email`, `password`, `role`) 
            VALUES (NULL, :n, :p, :b, :c, :e, :ps , :r)
        ");

        $query->execute(array('n'=> $name, 'p'=> $phonenum, 'b'=> $birthday, 'c'=> $city, 'e'=>$email, 'ps'=> md5($password), 'r'=>$role));
    }
// done
    function check($email, $password){
        global $conn;

        $result = $conn->prepare("
            SELECT * FROM `clients` WHERE `email`= :email AND `password` = :password
        ");

        $result->execute(["email"=>$email, "password"=>md5($password)]);
        $user = $result->fetch();
        return $user;
    }
// done
    function istheresuchanaccount($email,$phonenum){
        global $conn;

        $result = $conn->prepare("
            SELECT * FROM `clients` WHERE `email`= :email or `client_phone_num` = :p
        ");

        $result->execute(["email"=>$email,"p"=>$phonenum]);
        $user = $result->fetch();
        return $user;
    }
//done
    function get_product($id){
        global $conn;
    
        $get_product = $conn->prepare("
            SELECT * FROM `products` WHERE `product_id` = :id
        ");
        $get_product->execute(["id"=>$id]);
        $product = $get_product->fetch();
        return $product;
    }
//done
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
//done
    function get_all_products_asc(){
        global $conn;
    
        $get_products = $conn->prepare("
            SELECT `product_id`,`product_name`, `product_brand`,`product_price`,`product_size`, `product_quantity`,`product_photo`, `cat_name`
            FROM `products` JOIN `categories` ON `cat_id` = `fk_cat_id`
            ORDER BY `product_price` DESC
        ");
        $get_products->execute();
        $products = $get_products->fetchAll();
        return $products;
    }
//done
    function get_all_products_desc(){
        global $conn;
    
        $get_products = $conn->prepare("
            SELECT `product_id`,`product_name`, `product_brand`,`product_price`,`product_size`, `product_quantity`,`product_photo`, `cat_name`
            FROM `products` JOIN `categories` ON `cat_id` = `fk_cat_id`
            ORDER BY `product_price` ASC
        ");
        $get_products->execute();
        $products = $get_products->fetchAll();
        return $products;
    }

// done
    function get_all_users(){
        global $conn;
    
        $get_users = $conn->prepare("
            SELECT * FROM `clients`
        ");
        $get_users->execute();
        $users = $get_users->fetchAll();
        return $users;
    }
// done
    function get_user($id){
        global $conn;
    
        $get_user = $conn->prepare("
            SELECT * FROM `clients` WHERE `client_id` = :id
        ");
        $get_user->execute(["id"=> $id]);
        $user = $get_user->fetch();
        return $user;
    }
//done
    function change_product($id, $name, $brand, $price, $size, $amount, $cat){
        global $conn;
    
        $which_cat = $conn->prepare("SELECT cat_id FROM `categories` WHERE `cat_name` = :c");
        $which_cat->execute(["c" => $cat]);
        $cat_id = $which_cat->fetchColumn();
    
        $change = $conn->prepare("
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
//done
    function delete_product($id){
        global $conn;
        
        $delete_feedbacks = $conn->prepare(" DELETE FROM `feedback` WHERE `product_id` = :id");
        $delete_feedbacks->execute(["id" => $id]);

        $delete = $conn->prepare("DELETE FROM `products` WHERE `product_id` = :id");
        $delete->execute(["id" => $id]);
    }
//done
    function add_product($name, $brand, $price, $size, $amount, $photo, $cat){
        global $conn;
    
        $which_cat = $conn->prepare("SELECT cat_id FROM `categories` WHERE `cat_name` = :c");
        $which_cat->execute(["c" => $cat]);
        $cat_id = $which_cat->fetchColumn();

        $add_product = $conn->prepare("INSERT INTO `products`(`product_name`, `product_brand`, `product_price`, `product_size`, `product_quantity`, `product_photo`, `fk_cat_id`) 
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
// done
    function change_user($id, $name, $phone_num, $address , $birthday, $email, $password, $role){ 
        global $conn; 
 
        $changes = $conn->prepare(" 
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
// done
    function delete_user($id){ 
        global $conn; 
        
        $delete_feedbacks = $conn->prepare(" DELETE FROM `feedback` WHERE `client_id` = :id");
        $delete_feedbacks->execute(["id" => $id]);

        $delete = $conn->prepare("DELETE FROM `clients` WHERE `client_id` = :id"); 
        $delete->execute(["id" => $id]);
    }
// done
    function changePassword($password, $id){ 
        global $conn; 
        $query = $conn->prepare(" 
                UPDATE `clients` SET `password` = :password WHERE `client_id` = :id 
            "); 
        $query->execute(["password"=>md5($password), "id"=>$id]);
    }
// done
    function check_pass($pass , $id){
        global $conn; 

        $check = $conn->prepare(" 
                SELECT * FROM `clients` WHERE `client_id` = :id AND `password` = :password
            "); 
        $check->execute(["id"=>$id ,"password"=>md5($pass)]); 
        $result = $check->fetch();
        if(!empty($result)){
            return true;
        }else return false;
    }
// done
    function get_feedback($id){
        global $conn;
    
        $get_feedback = $conn->prepare("SELECT `client_name`, `feedback_desc` FROM `feedback` f JOIN `clients` c ON c.`client_id` = f.`client_id` WHERE `product_id` = :id");
        $get_feedback->execute(["id" => $id]);
        $feedback = $get_feedback->fetchAll();
        return $feedback;
    }
// done
    function add_feedback($desc, $p_id, $c_id){
        global $conn;
    
        $add_feedback = $conn->prepare("INSERT INTO `feedback`(`client_id`, `product_id`, `feedback_desc`) VALUES (:c_id , :p_id, :f_desc)");
        $add_feedback->execute(["c_id" => $c_id, "p_id" => $p_id, "f_desc" => $desc]);
    }
// done
    function get_fav_by_client_id($id){
        global $conn;

        $get = $conn->prepare("SELECT p.`product_id`, p.`product_name`, p.`product_price`, p.`product_size`, p.`product_brand`, p.`product_photo` 
        FROM `favorites` f JOIN `products` p ON f.`product_id` = p.`product_id` WHERE `client_id` = :c_id");
        $get->execute(["c_id" => $id]);
        $favs = $get->fetchAll();
        return $favs;
    }
// done
    function get_fav_by_prod_id($p_id , $c_id){
        global $conn;

        $get = $conn->prepare("SELECT * FROM `favorites` WHERE `client_id` = :c_id AND `product_id` = :p_id");
        $get->execute(["c_id" => $c_id , "p_id" => $p_id]);

        $res = $get->fetch();
        if(!empty($res)){
            return true;
        }else{
            return false;
        }
    }
// done
    function delete_fav_by_prod_id($p_id , $c_id){
        global $conn;

        $del = $conn->prepare("DELETE FROM `favorites` WHERE `client_id` = :c_id AND `product_id` = :p_id");
        $del->execute(["c_id" => $c_id , "p_id" => $p_id]);
    }
// done

    function set_to_fav($p_id , $c_id){
        global $conn;

        $set = $conn->prepare("INSERT INTO `favorites`(`client_id` , `product_id`) VALUES(:c_id, :p_id)");
        $set->execute(["p_id" => $p_id , "c_id" => $c_id ]);
    }
// done

    function check_bucket($p_id , $c_id){
        global $conn;

        $check = $conn->prepare("SELECT * FROM `bucket` WHERE `client_id` = :c_id AND `product_id` = :p_id");
        $check->execute(["c_id" => $c_id, "p_id" => $p_id]);

        $res = $check->fetch();
        if(!empty($res)){
            return true;
        }else{
            return false;
        }
    }
// done
    function add_to_bucket($p_id, $c_id){
        global $conn;
        
        $addback = $conn->prepare("INSERT INTO `bucket`(`product_id`,`client_id`,`amount`) VALUES(:p_id, :c_id, :amount)");
        $addback->execute(["p_id" => $p_id, "c_id" => $c_id, "amount" => 1]);
        
    }
// done
    function get_all_from_bucket($id){
        global $conn;

        $get = $conn->prepare("SELECT p.`product_id`, p.`product_name`, SUM(b.`amount`) as `amount`, p.`product_price`, p.`product_size`, p.`product_brand`, p.`product_photo` 
        FROM `bucket` b JOIN `products` p ON p.`product_id` = b.`product_id` WHERE `client_id` = :id GROUP BY p.`product_id`, p.`product_name` , p.`product_price`, p.`product_size`, p.`product_brand`, p.`product_photo`");
        $get->execute(["id" => $id]);
        $res = $get->fetchAll();
        return $res;
    }
// done
    function update_backet($c_id, $p_id, $amount){
        global $conn;

        if($amount == 0){
            $delete = $conn->prepare("DELETE FROM `bucket` WHERE `client_id` = :c_id AND `product_id` = :p_id");
            $delete->execute(["c_id"=> $c_id, "p_id"=> $p_id]);
        }else{
            $update = $conn->prepare("UPDATE `bucket` SET `amount` = :amount WHERE `client_id` = :c_id AND `product_id` = :p_id");
            $update->execute(["c_id"=> $c_id, "p_id" => $p_id, "amount" => $amount]);
        }
    }
?>