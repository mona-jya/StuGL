/*
SQLyog Community v13.1.7 (64 bit)
MySQL - 8.0.26 : Database - stu_gl
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`stu_gl` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `stu_gl`;

/*Table structure for table `stu_info` */

DROP TABLE IF EXISTS `stu_info`;

CREATE TABLE `stu_info` (
  `name` varchar(10) DEFAULT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  `photo` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '学生相片上传地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `stu_info` */

insert  into `stu_info`(`name`,`id`,`photo`) values 
('mona46',1,'/src/assets/img/1.jpg'),
('5',5,'/src/assets/img/5.jpg'),
('6',6,'/src/assets/img/6.jpg'),
('7',7,'/src/assets/img/7.jpg'),
('mona',8,'/src/assets/img/8.jpg'),
('9',9,'/src/assets/img/9.jpg'),
('10',10,'/src/assets/img/10.jpg'),
('11',11,'/src/assets/img/11.jpg'),
('12',12,'/src/assets/img/12.jpg'),
('13',13,'/src/assets/img/13.jpg'),
('15',15,'/src/assets/img/15.jpg'),
('17',17,'/src/assets/img/17.jpg');

/*Table structure for table `stu_system` */

DROP TABLE IF EXISTS `stu_system`;

CREATE TABLE `stu_system` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(9) DEFAULT NULL,
  `password` varchar(44) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_index` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=74 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `stu_system` */

insert  into `stu_system`(`id`,`name`,`password`) values 
(1,'mona','a30874820b2bcd7ef4406775daa3bad7'),
(2,'mona22','11c3aa0093ea565fa91c0d7c2afaca76'),
(3,'mona2','827ccb0eea8a706c4c34a16891f84e7b'),
(4,'mona4','1415aa1a344156e7be92c6df20a8ba2e'),
(5,'monahh','2eef9ea89f42cb7f4a9300a569e0a12c'),
(6,'admin','98ae1d2b9e8e5f36a02cc15303b7e9d9'),
(7,'mona7','65ed674042f8c01637647b0be7e30964');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
