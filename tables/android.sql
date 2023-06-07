/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

DROP TABLE IF EXISTS `riders`;
CREATE TABLE `riders` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `nama` varchar(50) NOT NULL,
  `nomor` int(2) NOT NULL,
  `sponsor` varchar(50) NOT NULL,
  `negara` varchar(50) NOT NULL,
  `foto` text DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NULL DEFAULT NULL ON UPDATE current_timestamp(),
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `name` varchar(30) NOT NULL,
  `email` text NOT NULL,
  `password` text NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NULL DEFAULT NULL ON UPDATE current_timestamp(),
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

INSERT INTO `riders` (`id`, `nama`, `nomor`, `sponsor`, `negara`, `foto`, `created_at`, `updated_at`) VALUES
(1, 'Francesco Bagnaia', 1, 'Ducati Lenovo Team', 'Italy', 'https://cdn.crash.net/styles/article/s3/pa/3269573.0008.jpg', '2023-06-07 10:02:52', '2023-06-07 10:05:53');
INSERT INTO `riders` (`id`, `nama`, `nomor`, `sponsor`, `negara`, `foto`, `created_at`, `updated_at`) VALUES
(2, 'Johann Zarco', 5, 'Prima Pramac Racing', 'Prancis', NULL, '2023-06-07 10:09:14', NULL);
INSERT INTO `riders` (`id`, `nama`, `nomor`, `sponsor`, `negara`, `foto`, `created_at`, `updated_at`) VALUES
(3, 'Alex Rins', 42, 'Honda Paling Irit', 'Spanyol', NULL, '2023-06-07 10:09:47', '2023-06-07 15:16:14');
INSERT INTO `riders` (`id`, `nama`, `nomor`, `sponsor`, `negara`, `foto`, `created_at`, `updated_at`) VALUES
(5, 'Jonathan Smith', 88, 'Yamaha Semakin Didepan', 'Australia', 'https://media.suara.com/pictures/653x366/2022/11/06/27140-open-minded-freepik.jpg', '2023-06-07 11:35:07', '2023-06-07 15:18:15'),
(6, 'Alexandria Ollas', 35, 'Repsol Honda ', 'Jerman', NULL, '2023-06-07 20:09:19', '2023-06-07 21:13:42');

INSERT INTO `users` (`name`, `email`, `password`, `created_at`, `updated_at`, `id`) VALUES
('Yusuf Rizal', 'rizal@email.com', 'Pa$$w0rd', '2023-06-07 19:31:23', '2023-06-07 19:31:36', 1);
INSERT INTO `users` (`name`, `email`, `password`, `created_at`, `updated_at`, `id`) VALUES
('Benazir Shea', 'benazir.shea@email.com', 'Pa$$w0rd', '2023-06-07 19:32:03', NULL, 2);



/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;