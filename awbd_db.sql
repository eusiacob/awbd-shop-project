-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Jun 09, 2025 at 06:03 PM
-- Server version: 9.1.0
-- PHP Version: 8.3.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `awbd_db`
--
CREATE DATABASE IF NOT EXISTS `awbd_db` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
USE `awbd_db`;

-- --------------------------------------------------------

--
-- Table structure for table `authorities`
--

DROP TABLE IF EXISTS `authorities`;
CREATE TABLE IF NOT EXISTS `authorities` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `authority` varchar(255) DEFAULT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKhjuy9y4fd8v5m3klig05ktofg` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Truncate table before insert `authorities`
--

TRUNCATE TABLE `authorities`;
--
-- Dumping data for table `authorities`
--

INSERT DELAYED IGNORE INTO `authorities` (`id`, `authority`, `username`) VALUES
(6, 'ROLE_USER', 'user'),
(7, 'ROLE_ADMIN', 'admin');

-- --------------------------------------------------------

--
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
CREATE TABLE IF NOT EXISTS `categories` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Truncate table before insert `categories`
--

TRUNCATE TABLE `categories`;
--
-- Dumping data for table `categories`
--

INSERT DELAYED IGNORE INTO `categories` (`id`, `name`) VALUES
(1, 'Electronics'),
(2, 'Clothing'),
(3, 'Home & Kitchen'),
(4, 'Books'),
(5, 'Sports'),
(6, 'Toys');

-- --------------------------------------------------------

--
-- Table structure for table `distributors`
--

DROP TABLE IF EXISTS `distributors`;
CREATE TABLE IF NOT EXISTS `distributors` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Truncate table before insert `distributors`
--

TRUNCATE TABLE `distributors`;
--
-- Dumping data for table `distributors`
--

INSERT DELAYED IGNORE INTO `distributors` (`id`, `name`) VALUES
(1, 'Distributor A'),
(2, 'Distributor B'),
(3, 'Distributor C');

-- --------------------------------------------------------

--
-- Table structure for table `favorites`
--

DROP TABLE IF EXISTS `favorites`;
CREATE TABLE IF NOT EXISTS `favorites` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `product_id` bigint DEFAULT NULL,
  `user_username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6sgu5npe8ug4o42bf9j71x20c` (`product_id`),
  KEY `FKhxspw468wqkn1nt48trtmwnkd` (`user_username`)
) ENGINE=InnoDB AUTO_INCREMENT=81 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Truncate table before insert `favorites`
--

TRUNCATE TABLE `favorites`;
-- --------------------------------------------------------

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
CREATE TABLE IF NOT EXISTS `products` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `description` varchar(500) NOT NULL,
  `name` varchar(100) NOT NULL,
  `price` double DEFAULT NULL,
  `category_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKog2rp4qthbtt2lfyhfo32lsw9` (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Truncate table before insert `products`
--

TRUNCATE TABLE `products`;
--
-- Dumping data for table `products`
--

INSERT DELAYED IGNORE INTO `products` (`id`, `description`, `name`, `price`, `category_id`) VALUES
(5, 'Automatic coffee machine with multiple settings', 'Coffee Maker', 99.99, 3),
(6, 'Powerful vacuum cleaner for home use', 'Vacuum Cleaner', 120, 3),
(8, 'Delicious recipes for home chefs', 'Cookbook', 20, 4),
(9, 'Professional football for outdoor games', 'Football', 30, 5),
(11, 'Creative Lego set for kids', 'Lego Set', 45, 6),
(12, 'High-speed remote control car', 'Remote Control Car', 55, 6),
(13, 'Fitness and health tracking smartwatch', 'Smartwatch', 250, 1),
(14, 'Noise-cancelling headphones for music lovers', 'Headphones', 120, 1),
(15, 'Warm winter jacket', 'Jacket', 80, 2),
(16, 'Soft woolen sweater for winter', 'Sweater', 40, 2),
(17, 'Smoothie maker with multiple attachments', 'Blender', 60, 3),
(19, 'High-quality yoga mat for workouts', 'Yoga Mat', 25, 5),
(21, 'Laptop performant pentru business.', 'Laptop Lenovo', 3500, 1),
(22, 'Telefon mobil de ultimă generație.', 'Smartphone Samsung', 2800, 1),
(23, 'Căști wireless cu bass puternic.', 'Casti JBL', 450, 1),
(24, 'Ghid complet pentru programatori.', 'Carte - Java Programming', 120, 4),
(25, 'Echipament complet pentru fotbal.', 'Echipament Fotbal Adidas', 250, 5),
(26, 'Set de 5 cratițe antiaderente.', 'Set cratite Tefal', 750, 3),
(27, 'Construcție Lego pentru copii.', 'Jucărie Lego City', 300, 6),
(28, 'Rochie elegantă de seară.', 'Rochie Zara', 400, 2),
(29, 'Blender performant pentru smoothie-uri.', 'Blender Philips', 380, 3),
(30, 'Carte faimoasă despre programare.', 'Carte - Design Patterns', 150, 4),
(32, 'Eficientă și economică.', 'Masina de spalat Whirlpool', 2000, 3),
(33, 'Papusa originală Barbie.', 'Papusa Barbie', 250, 6),
(34, 'Tricou sport.', 'Tricou Puma', 180, 2),
(35, 'Laptop pentru uz office.', 'Laptop HP', 2700, 1),
(36, 'Tabletă performantă.', 'Tableta Apple iPad', 3200, 1),
(37, 'Cum să folosești Spring Boot.', 'Carte - Spring Boot in Action', 140, 4),
(39, 'Mixer profesional de bucătărie.', 'Mixer Bosch', 500, 3),
(40, 'Puzzle 1000 piese.', 'Puzzle Ravensburger', 200, 6),
(41, 'KATANA 15 B13V', 'Laptom MSI', 5500, 1),
(45, 'adsadsadasdasdasdda', 'nume produs', 575, 1),
(46, 'asdfghjasdasd', 'test', 1000, 1),
(48, 'descriere test pentru produs', 'test', 120, 1),
(49, 'KATANA 15 B13V', 'laptop msi', 4500, 1),
(51, 'descriere test', 'produs adaugat pentru test', 4500, 1);

-- --------------------------------------------------------

--
-- Table structure for table `product_distributor`
--

DROP TABLE IF EXISTS `product_distributor`;
CREATE TABLE IF NOT EXISTS `product_distributor` (
  `product_id` bigint NOT NULL,
  `distributor_id` bigint NOT NULL,
  KEY `FKgnjpgt07mi4yb1n95aoul4mdy` (`distributor_id`),
  KEY `FKrlei737kde7c8snpv5u15gt31` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Truncate table before insert `product_distributor`
--

TRUNCATE TABLE `product_distributor`;
--
-- Dumping data for table `product_distributor`
--

INSERT DELAYED IGNORE INTO `product_distributor` (`product_id`, `distributor_id`) VALUES
(5, 2),
(8, 2),
(9, 3),
(11, 2),
(12, 3),
(13, 1),
(14, 2),
(15, 3),
(16, 1),
(17, 2),
(19, 1),
(6, 2),
(6, 3),
(21, 1),
(22, 2),
(23, 1),
(24, 3),
(25, 2),
(26, 1),
(27, 3),
(28, 2),
(29, 1),
(30, 3),
(32, 1),
(33, 3),
(34, 2),
(35, 1),
(36, 1),
(37, 3),
(39, 1),
(40, 3),
(41, 2),
(41, 3),
(46, 3),
(48, 1),
(49, 1),
(49, 2),
(49, 3),
(51, 1);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `username` varchar(255) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `enabled` bit(1) NOT NULL,
  `authority_id` bigint DEFAULT NULL,
  PRIMARY KEY (`username`),
  UNIQUE KEY `UKld22ky2pkjx9k6l1meeby3fko` (`authority_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Truncate table before insert `users`
--

TRUNCATE TABLE `users`;
--
-- Dumping data for table `users`
--

INSERT DELAYED IGNORE INTO `users` (`username`, `password`, `enabled`, `authority_id`) VALUES
('admin', '$2a$10$DEEPtgouIaK5lhCbUbB6.uE3RHGaap9/bqMYlU7GrSsgKTNg3Y6N.', b'1', NULL),
('user', '$2a$10$kAC1XjR8D7YOfkKyQEIhfezrh5VjWyRz/6wMVltELT54tsGpQdnRq', b'1', NULL);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `authorities`
--
ALTER TABLE `authorities`
  ADD CONSTRAINT `FKhjuy9y4fd8v5m3klig05ktofg` FOREIGN KEY (`username`) REFERENCES `users` (`username`);

--
-- Constraints for table `favorites`
--
ALTER TABLE `favorites`
  ADD CONSTRAINT `FK6sgu5npe8ug4o42bf9j71x20c` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`),
  ADD CONSTRAINT `FKhxspw468wqkn1nt48trtmwnkd` FOREIGN KEY (`user_username`) REFERENCES `users` (`username`);

--
-- Constraints for table `products`
--
ALTER TABLE `products`
  ADD CONSTRAINT `FKog2rp4qthbtt2lfyhfo32lsw9` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`);

--
-- Constraints for table `product_distributor`
--
ALTER TABLE `product_distributor`
  ADD CONSTRAINT `FKgnjpgt07mi4yb1n95aoul4mdy` FOREIGN KEY (`distributor_id`) REFERENCES `distributors` (`id`),
  ADD CONSTRAINT `FKrlei737kde7c8snpv5u15gt31` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`);

--
-- Constraints for table `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `FKrixikjhjfel192yj7w7xapds7` FOREIGN KEY (`authority_id`) REFERENCES `authorities` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
