-- phpMyAdmin SQL Dump
-- version 5.1.2
-- https://www.phpmyadmin.net/
--
-- Хост: localhost:3306
-- Время создания: Фев 24 2024 г., 13:27
-- Версия сервера: 5.7.24
-- Версия PHP: 8.0.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База данных: `mens shop`
--

-- --------------------------------------------------------

--
-- Структура таблицы `bucket`
--

CREATE TABLE `bucket` (
  `id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `client_id` int(11) NOT NULL,
  `amount` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `bucket`
--

INSERT INTO `bucket` (`id`, `product_id`, `client_id`, `amount`) VALUES
(4, 17, 2, 2),
(5, 34, 2, 1),
(13, 37, 57, 1),
(14, 36, 57, 1),
(15, 19, 2, 2);

-- --------------------------------------------------------

--
-- Структура таблицы `categories`
--

CREATE TABLE `categories` (
  `cat_id` int(11) NOT NULL,
  `cat_name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `categories`
--

INSERT INTO `categories` (`cat_id`, `cat_name`) VALUES
(1, 'Top'),
(2, 'Bottom'),
(3, 'Shoes'),
(4, 'Classic'),
(5, 'Deuce');

-- --------------------------------------------------------

--
-- Структура таблицы `clients`
--

CREATE TABLE `clients` (
  `client_id` int(11) NOT NULL,
  `client_name` varchar(15) NOT NULL,
  `client_phone_num` varchar(11) NOT NULL,
  `client_address` text,
  `client_birthday` date NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `clients`
--

INSERT INTO `clients` (`client_id`, `client_name`, `client_phone_num`, `client_address`, `client_birthday`, `email`, `password`, `role`) VALUES
(2, 'Dias', '87789999999', 'Qaragandy', '2005-11-29', 'kapashov@narxoz.kz', 'c20e80d5ed9bb7215d08c66f9c17defd', 'admin'),
(54, 'Zhubanysh', '87783500809', 'Ақтөбе', '2005-02-18', 'zhubanysh@narxoz.kz', '6dc1e58a725592a5b58f6649730a82e8', 'admin'),
(57, 'Ayaulim', '87021233214', 'Қарағанды', '2022-10-07', 'ayau@narxoz.kz', 'ac7f684e5664b5d50d38799244c040c6', 'user'),
(58, 'Bekassyl', '87775935614', 'Тараз', '2023-12-09', 'beke@narxoz.kz', '2f05707c55bc00e807e42b9201368993', 'user'),
(59, 'Madiyar', '87471231212', 'Ақтөбе', '2023-12-17', 'madi@narxoz.kz', '5619ebaae4d1591bd27ccd064ff54b61', 'user');

-- --------------------------------------------------------

--
-- Структура таблицы `employees`
--

CREATE TABLE `employees` (
  `employee_id` int(11) NOT NULL,
  `employee_name` varchar(15) NOT NULL,
  `employee_job_title` varchar(30) NOT NULL,
  `employee_phone_num` varchar(11) NOT NULL,
  `employee_birthday` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Структура таблицы `favorites`
--

CREATE TABLE `favorites` (
  `client_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `favorites`
--

INSERT INTO `favorites` (`client_id`, `product_id`) VALUES
(57, 15),
(57, 37),
(2, 15);

-- --------------------------------------------------------

--
-- Структура таблицы `feedback`
--

CREATE TABLE `feedback` (
  `feedback_id` int(11) NOT NULL,
  `client_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `feedback_desc` text NOT NULL,
  `feedback_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `feedback`
--

INSERT INTO `feedback` (`feedback_id`, `client_id`, `product_id`, `feedback_desc`, `feedback_date`) VALUES
(6, 2, 12, '!!!\r\n', NULL),
(10, 2, 15, 'Asfdfgh', NULL),
(11, 2, 26, 'hjk', NULL),
(12, 2, 10, 'sdfhgjh', NULL),
(13, 2, 16, 'dgfb', NULL),
(14, 2, 24, '!!!!', NULL),
(15, 59, 36, 'ihijnk\r\n', NULL),
(16, 2, 19, 'asdfg', NULL);

-- --------------------------------------------------------

--
-- Структура таблицы `orders`
--

CREATE TABLE `orders` (
  `order_id` int(11) NOT NULL,
  `order_date` date NOT NULL,
  `product_id` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `client_id` int(11) NOT NULL,
  `employee_id` int(11) NOT NULL,
  `order_status` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Структура таблицы `products`
--

CREATE TABLE `products` (
  `product_id` int(11) NOT NULL,
  `product_name` varchar(15) NOT NULL,
  `product_brand` varchar(15) NOT NULL,
  `product_price` int(11) NOT NULL,
  `product_size` varchar(5) NOT NULL,
  `product_quantity` smallint(6) NOT NULL,
  `product_photo` text NOT NULL,
  `fk_cat_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `products`
--

INSERT INTO `products` (`product_id`, `product_name`, `product_brand`, `product_price`, `product_size`, `product_quantity`, `product_photo`, `fk_cat_id`) VALUES
(4, 'Jeans', 'Armani', 25000, '31', 3, 'jeans1.jpg', 2),
(6, 'Jeans', 'Armani', 20000, '33', 6, 'jeans2.jpg', 2),
(8, 'Shoes ', 'Boss', 36000, '40', 10, 'shoes3.jpg', 3),
(9, 'Jeans', 'Gucci', 40000, '28', 6, 'jeans1.jpg', 2),
(10, 'Jeans', 'Gucci', 40000, '29', 6, 'jeans1.jpg', 2),
(11, 'Jeans', 'Prada', 44000, '30', 5, 'jeans2.jpg', 2),
(12, 'Jeans', 'Prada', 45000, '31', 7, 'jeans2.jpg', 2),
(15, 'T-shirt', 'Louis Vuitton', 14000, '50', 5, 'summer1.png', 1),
(16, 'Shoes', 'Nike', 45000, '41', 3, 'shoes4.jpg', 3),
(18, 'Shoes', 'Adidas', 35000, '42', 4, 'shoes6.jpg', 3),
(19, 'Shoes', 'Adidas', 45000, '41', 6, 'shoes7.jpg', 3),
(20, 'Shoes', 'Nike', 36000, '43', 5, 'shoes5.jpg', 3),
(21, 'Shoes', 'Nike', 35500, '43', 5, 'shoes2.jpg', 3),
(22, 'Shoes', 'Zara', 40000, '44', 5, 'shoes3.jpg', 3),
(23, 'Shoes', 'Zara', 43000, '45', 5, 'shoes4.jpg', 3),
(24, 'Classic suit', 'Shoqan', 150000, '46', 6, 'classic1.jpg', 4),
(25, 'Classic suit', 'Shoqan', 170000, '48', 5, 'classic2.PNG', 4),
(26, 'Classic suit', 'GLASMAN', 200000, '50', 5, 'classic3.PNG', 4),
(27, 'Classic suit', 'GLASMAN', 20000, '52', 6, 'classic4.PNG', 4),
(28, 'Classic suit', 'Kanzler', 250000, '52', 6, 'classic5.PNG', 4),
(33, 'Sport Suit', 'Adidas', 50000, '42', 5, '', 5),
(35, 'Sport Suit', 'Puma', 45000, '50', 2, '', 5),
(36, 'T-shirt', 'Moncler', 16000, '52', 3, 'summer1.png', 1),
(37, 'Shoes', 'Nike', 25000, '41', 6, 'shoes5.jpg', 3),
(38, 'Sport Suit', 'Nike', 55000, '48', 3, '', 5),
(39, 'Shoes', 'Boss', 45000, '43', 6, 'shoes5.jpg', 3),
(40, 'Shoes', 'Nike', 55000, '42', 3, 'shoes3.jpg', 3);

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `bucket`
--
ALTER TABLE `bucket`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `categories`
--
ALTER TABLE `categories`
  ADD PRIMARY KEY (`cat_id`);

--
-- Индексы таблицы `clients`
--
ALTER TABLE `clients`
  ADD PRIMARY KEY (`client_id`),
  ADD UNIQUE KEY `client_phone_num` (`client_phone_num`),
  ADD UNIQUE KEY `client_phone_num_2` (`client_phone_num`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Индексы таблицы `employees`
--
ALTER TABLE `employees`
  ADD PRIMARY KEY (`employee_id`);

--
-- Индексы таблицы `favorites`
--
ALTER TABLE `favorites`
  ADD KEY `client_id` (`client_id`),
  ADD KEY `product_id` (`product_id`);

--
-- Индексы таблицы `feedback`
--
ALTER TABLE `feedback`
  ADD PRIMARY KEY (`feedback_id`),
  ADD KEY `client_id` (`client_id`),
  ADD KEY `product_id` (`product_id`);

--
-- Индексы таблицы `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`order_id`),
  ADD KEY `product_id` (`product_id`),
  ADD KEY `client_id` (`client_id`),
  ADD KEY `employee_id` (`employee_id`);

--
-- Индексы таблицы `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`product_id`),
  ADD KEY `fk_cat_id` (`fk_cat_id`);

--
-- AUTO_INCREMENT для сохранённых таблиц
--

--
-- AUTO_INCREMENT для таблицы `bucket`
--
ALTER TABLE `bucket`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT для таблицы `categories`
--
ALTER TABLE `categories`
  MODIFY `cat_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT для таблицы `clients`
--
ALTER TABLE `clients`
  MODIFY `client_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=60;

--
-- AUTO_INCREMENT для таблицы `employees`
--
ALTER TABLE `employees`
  MODIFY `employee_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT для таблицы `feedback`
--
ALTER TABLE `feedback`
  MODIFY `feedback_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT для таблицы `orders`
--
ALTER TABLE `orders`
  MODIFY `order_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT для таблицы `products`
--
ALTER TABLE `products`
  MODIFY `product_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=41;

--
-- Ограничения внешнего ключа сохраненных таблиц
--

--
-- Ограничения внешнего ключа таблицы `favorites`
--
ALTER TABLE `favorites`
  ADD CONSTRAINT `favorites_ibfk_1` FOREIGN KEY (`client_id`) REFERENCES `clients` (`client_id`),
  ADD CONSTRAINT `favorites_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `products` (`product_id`);

--
-- Ограничения внешнего ключа таблицы `feedback`
--
ALTER TABLE `feedback`
  ADD CONSTRAINT `feedback_ibfk_1` FOREIGN KEY (`client_id`) REFERENCES `clients` (`client_id`),
  ADD CONSTRAINT `feedback_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `products` (`product_id`);

--
-- Ограничения внешнего ключа таблицы `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `products` (`product_id`),
  ADD CONSTRAINT `orders_ibfk_2` FOREIGN KEY (`client_id`) REFERENCES `clients` (`client_id`),
  ADD CONSTRAINT `orders_ibfk_3` FOREIGN KEY (`employee_id`) REFERENCES `employees` (`employee_id`);

--
-- Ограничения внешнего ключа таблицы `products`
--
ALTER TABLE `products`
  ADD CONSTRAINT `products_ibfk_1` FOREIGN KEY (`fk_cat_id`) REFERENCES `categories` (`cat_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
