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
  `nama` varchar(30) NOT NULL,
  `nomor` int(2) NOT NULL,
  `sponsor` varchar(20) NOT NULL DEFAULT '',
  `negara` varchar(20) NOT NULL,
  `foto` text DEFAULT 'https://i.ibb.co/37QR5QX/blank-profile-photo.png',
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NULL DEFAULT NULL ON UPDATE current_timestamp(),
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

INSERT INTO `riders` (`id`, `nama`, `nomor`, `sponsor`, `negara`, `foto`, `created_at`, `updated_at`) VALUES
(1, 'Francesco Bagnaia', 81, 'Ducati', 'Italia', 'https://i.ibb.co/rGpcft6/Francesco-Bagnaia.jpg', '2023-05-29 15:08:56', '2023-05-29 15:15:16');
INSERT INTO `riders` (`id`, `nama`, `nomor`, `sponsor`, `negara`, `foto`, `created_at`, `updated_at`) VALUES
(3, 'Fabio Quartararo', 20, 'Yamaha', 'Prancis', 'https://i.ibb.co/QYhfF58/Fabio-Quartararo.jpg', '2023-05-29 15:15:16', '2023-05-29 15:17:05');
INSERT INTO `riders` (`id`, `nama`, `nomor`, `sponsor`, `negara`, `foto`, `created_at`, `updated_at`) VALUES
(4, 'Marc Marquez', 93, 'Honda', 'Spanyol', 'https://i.ibb.co/xMKp2XT/Marc-M-rquez.jpg', '2023-05-29 15:17:53', '2023-05-29 15:18:45');
INSERT INTO `riders` (`id`, `nama`, `nomor`, `sponsor`, `negara`, `foto`, `created_at`, `updated_at`) VALUES
(5, 'Jack Miller', 43, 'KTM', 'Australia', 'https://i.ibb.co/N2rq6rZ/Jack-Miller.jpg', '2023-05-29 15:19:38', '2023-05-29 15:20:30'),
(6, 'Brad Binder', 33, 'KTM', 'Afrika Selatan', 'https://i.ibb.co/37QR5QX/blank-profile-photo.png', '2023-05-29 15:21:46', '2023-05-29 15:21:54'),
(9, 'Rizal Yuri', 40, 'Honda ', 'Indonesia ', 'https://i.ibb.co/37QR5QX/blank-profile-photo.png', '2023-05-30 10:44:43', '2023-05-30 12:08:19'),
(10, 'Steven Universe ', 31, 'KTM', 'Spanyol', 'https://i.ibb.co/37QR5QX/blank-profile-photo.png', '2023-05-30 11:15:23', '2023-05-30 13:21:19');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;