/*
SQLyog Ultimate v8.71 
MySQL - 5.1.62-community : Database - ssm
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`ssm` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `ssm`;

/*Table structure for table `item` */

DROP TABLE IF EXISTS `item`;

CREATE TABLE `item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `pic` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

/*Data for the table `item` */

insert  into `item`(`id`,`description`,`title`,`pic`,`price`) values (1,'遵循传统配方，以竹蔗、茅根、马蹄等原料进行熬煮而成，甘甜清润之余，更能享用真材实料。','竹蔗茅根水','http://3.tthunbohui.cn/n/00401ctq004N1aqvqx00ca8.jpg',35.6),(3,'好吃实惠，还有优惠','茴香鲜肉馄饨','http://2.tthunbohui.cn/n/00401ctt004Q19Cd8gBw6h8.jpg',25),(4,'testtest','八宝榛子酱','http://1.tthunbohui.cn/n/00801cmA004P0SETPxtMfE8.jpg',89),(5,'软硬兼施，老少咸宜，甜辣皆适，色香俱佳','糖焖莲子','http://localhost:8088/file/upload/20170310154251327979.jpg',100),(8,'有调节和营养、刺激肠胃、增进食欲之效。','清炖蟹粉狮子头','upload/20170310154314831749.jpg',125),(9,'这道菜可以是家常炒菜，  也可以是火锅。辣子鸡在炸干 水汽后可以保存较长时间','七星鱼丸汤','upload/20170310153942944759.jpg',200),(10,'黔菜中最有名气的传统菜之\r\n一。为贵州独创的酱辣味菜，\r\n具有红而不辣、辣而不猛、辣香味浓、油而不腻和鲜香嫩脆\r\n的特点。 ','清炒土豆片','upload/20170310153856994848.jpg',150),(13,'清汤活海参选用山东胶东半岛活海参，海参俗称海中人参，用雅轩自制的清鸡汤制作而成，成品口感脆爽，汤清味醇，长期食用可延年益寿。','麻辣火锅','upload/20170310154528463421.jpg',160);

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `sex` tinyint(4) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`id`,`name`,`sex`,`age`,`password`) values (1,'zhangsan',1,30,'test'),(2,'lisi',1,25,'test'),(3,'wangwu',1,20,'test'),(4,'test',0,26,'test'),(5,'abc',1,35,'test'),(7,'haha1111111111',0,30,'test'),(12,'李四',1,18,'test'),(13,'王小二',1,21,'abc'),(14,'张红',0,22,'abc'),(15,'abcd',1,24,'abc'),(16,'hhahah',0,30,'abc'),(17,'ffff',0,35,'abc');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
