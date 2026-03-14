/*
SQLyog Community v13.3.0 (64 bit)
MySQL - 8.0.18 : Database - 0_seminarski_biblioteka
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`0_seminarski_biblioteka` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `0_seminarski_biblioteka`;

/*Table structure for table `bibliotekar` */

DROP TABLE IF EXISTS `bibliotekar`;

CREATE TABLE `bibliotekar` (
  `idBibliotekar` bigint(20) NOT NULL AUTO_INCREMENT,
  `ime` varchar(30) DEFAULT NULL,
  `prezime` varchar(30) DEFAULT NULL,
  `korisnickoIme` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `email` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `lozinka` varchar(30) NOT NULL,
  PRIMARY KEY (`idBibliotekar`),
  UNIQUE KEY `UNIQUEusername` (`korisnickoIme`),
  UNIQUE KEY `UNIQUEemail` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `bibliotekar` */

insert  into `bibliotekar`(`idBibliotekar`,`ime`,`prezime`,`korisnickoIme`,`email`,`lozinka`) values 
(1,'Ana','Anic','aa1','ana123@gmail.com','abc'),
(2,'Marko','Markovic','mm2','marko123@gmail.com','abc'),
(3,'Nikola','Nikolic','nn76','nikola123@gmail.com','abc'),
(4,'Maja','Majic','mm123','maja123@gmail.com','abc'),
(5,'Mina','Minic','mm567','mina123@gmail.com','abc');

/*Table structure for table `bibliotekartermin` */

DROP TABLE IF EXISTS `bibliotekartermin`;

CREATE TABLE `bibliotekartermin` (
  `idBibliotekar` bigint(20) NOT NULL,
  `idTerminDezurstva` bigint(20) NOT NULL,
  `datumDezurstva` date NOT NULL,
  PRIMARY KEY (`idBibliotekar`,`idTerminDezurstva`),
  KEY `idTerminDezurstva` (`idTerminDezurstva`),
  CONSTRAINT `bibliotekartermin_ibfk_1` FOREIGN KEY (`idBibliotekar`) REFERENCES `bibliotekar` (`idBibliotekar`),
  CONSTRAINT `bibliotekartermin_ibfk_2` FOREIGN KEY (`idTerminDezurstva`) REFERENCES `termindezurstva` (`idTerminDezurstva`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `bibliotekartermin` */

insert  into `bibliotekartermin`(`idBibliotekar`,`idTerminDezurstva`,`datumDezurstva`) values 
(1,8,'2026-03-12'),
(2,8,'2026-03-11');

/*Table structure for table `iznajmljivanje` */

DROP TABLE IF EXISTS `iznajmljivanje`;

CREATE TABLE `iznajmljivanje` (
  `idIznajmljivanje` bigint(20) NOT NULL AUTO_INCREMENT,
  `datumIznajmljivanja` date DEFAULT NULL,
  `ukupanDug` double DEFAULT NULL,
  `idBibliotekar` bigint(20) DEFAULT NULL,
  `idStudent` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`idIznajmljivanje`),
  KEY `idBibliotekar` (`idBibliotekar`),
  KEY `idStudent` (`idStudent`),
  CONSTRAINT `iznajmljivanje_ibfk_1` FOREIGN KEY (`idBibliotekar`) REFERENCES `bibliotekar` (`idBibliotekar`),
  CONSTRAINT `iznajmljivanje_ibfk_2` FOREIGN KEY (`idStudent`) REFERENCES `student` (`idStudent`),
  CONSTRAINT `chk_dug` CHECK ((`ukupanDug` >= 0))
) ENGINE=InnoDB AUTO_INCREMENT=105 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `iznajmljivanje` */

insert  into `iznajmljivanje`(`idIznajmljivanje`,`datumIznajmljivanja`,`ukupanDug`,`idBibliotekar`,`idStudent`) values 
(96,'2026-03-10',0,1,1),
(97,'2026-02-10',1200,1,2),
(98,'2026-03-02',0,1,3),
(99,'2026-02-27',0,1,4),
(100,'2026-02-14',0,1,6),
(101,'2026-02-21',1230,1,7),
(102,'2026-03-03',0,1,1),
(103,'2026-03-10',0,1,2),
(104,'2026-02-22',445,1,1);

/*Table structure for table `knjiga` */

DROP TABLE IF EXISTS `knjiga`;

CREATE TABLE `knjiga` (
  `idKnjiga` bigint(20) NOT NULL AUTO_INCREMENT,
  `nazivKnjige` varchar(50) NOT NULL,
  `autor` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `zanr` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `cena` double NOT NULL,
  PRIMARY KEY (`idKnjiga`),
  CONSTRAINT `chk_cena` CHECK ((`cena` >= 0))
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `knjiga` */

insert  into `knjiga`(`idKnjiga`,`nazivKnjige`,`autor`,`zanr`,`cena`) values 
(1,'The Hacker Ethic','Pekka Himanen','PROGRAMIRANJE',2000),
(5,'The Unix Programming Environment','Kernighan & Pike','PROGRAMIRANJE',1500),
(6,'The C Programming Language','Kernighan & Ritchie','PROGRAMIRANJE',2400),
(7,'Just for Fun','Linus Torvalds','PROGRAMIRANJE',1950),
(8,'Effective Java','Joshua Bloch','PROGRAMIRANJE',1450),
(9,'Code Complete','Steve McConnell','PROGRAMIRANJE',1400),
(10,'The Lean Startup','Eric Ries','MENADZMENT',2100),
(11,'How To Solve It','George Pólya','MATEMATIKA',1850),
(12,'Thinking, Fast and Slow','Daniel Kahneman','EKONOMIJA',1450),
(13,'Statistics','Robert S. Witte','STATISTIKA',1670),
(14,'Database System Concepts','Silberschatz, Korth, Sudarshan','BAZE_PODATAKA',1800),
(15,'Computer Networks','Andrew Tanenbaum','MREZE',1700);

/*Table structure for table `nivostudija` */

DROP TABLE IF EXISTS `nivostudija`;

CREATE TABLE `nivostudija` (
  `idNivoStudija` bigint(20) NOT NULL AUTO_INCREMENT,
  `nazivNivoa` varchar(20) NOT NULL,
  PRIMARY KEY (`idNivoStudija`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `nivostudija` */

insert  into `nivostudija`(`idNivoStudija`,`nazivNivoa`) values 
(1,'OSNOVNE'),
(2,'MASTER'),
(3,'DOKTORSKE');

/*Table structure for table `stavkaiznajmljivanja` */

DROP TABLE IF EXISTS `stavkaiznajmljivanja`;

CREATE TABLE `stavkaiznajmljivanja` (
  `rb` bigint(20) NOT NULL AUTO_INCREMENT,
  `idIznajmljivanje` bigint(20) NOT NULL,
  `rokVracanja` date DEFAULT NULL,
  `datumVracanja` date DEFAULT NULL,
  `penali` double DEFAULT NULL,
  `idKnjiga` bigint(20) NOT NULL,
  PRIMARY KEY (`rb`,`idIznajmljivanje`),
  KEY `idIznajmljivanje` (`idIznajmljivanje`),
  KEY `idKnjiga` (`idKnjiga`),
  CONSTRAINT `stavkaiznajmljivanja_ibfk_1` FOREIGN KEY (`idIznajmljivanje`) REFERENCES `iznajmljivanje` (`idIznajmljivanje`),
  CONSTRAINT `stavkaiznajmljivanja_ibfk_2` FOREIGN KEY (`idKnjiga`) REFERENCES `knjiga` (`idKnjiga`),
  CONSTRAINT `chk_penali` CHECK ((`penali` >= 0))
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `stavkaiznajmljivanja` */

insert  into `stavkaiznajmljivanja`(`rb`,`idIznajmljivanje`,`rokVracanja`,`datumVracanja`,`penali`,`idKnjiga`) values 
(1,96,'2026-03-25',NULL,0,8),
(1,97,'2026-02-25',NULL,1200,5),
(1,98,'2026-03-17',NULL,0,10),
(1,99,'2026-03-14',NULL,0,15),
(1,100,'2026-03-01','2026-03-01',0,1),
(1,101,'2026-03-08',NULL,417.5,13),
(1,102,'2026-03-18','2026-03-10',0,10),
(1,103,'2026-03-25',NULL,0,9),
(1,104,'2026-03-09',NULL,300,5),
(2,96,'2026-03-25',NULL,0,14),
(2,98,'2026-03-17',NULL,0,12),
(2,101,'2026-03-08',NULL,462.5,11),
(2,102,'2026-03-18',NULL,0,1),
(2,104,'2026-03-09','2026-03-11',145,8),
(3,98,'2026-03-17',NULL,0,13),
(3,101,'2026-03-08',NULL,350,9);

/*Table structure for table `student` */

DROP TABLE IF EXISTS `student`;

CREATE TABLE `student` (
  `idStudent` bigint(20) NOT NULL AUTO_INCREMENT,
  `ime` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `prezime` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `email` varchar(40) DEFAULT NULL,
  `idNivoStudija` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`idStudent`),
  UNIQUE KEY `UNIQUEemail` (`email`),
  KEY `idNivoStudija` (`idNivoStudija`),
  CONSTRAINT `student_ibfk_1` FOREIGN KEY (`idNivoStudija`) REFERENCES `nivostudija` (`idNivoStudija`),
  CONSTRAINT `chk_email` CHECK ((`email` like _utf8mb3'%@fon.bg.ac.rs'))
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `student` */

insert  into `student`(`idStudent`,`ime`,`prezime`,`email`,`idNivoStudija`) values 
(1,'Magdalena','Djukic','md@fon.bg.ac.rs',1),
(2,'Ana','Markovic','am@fon.bg.ac.rs',3),
(3,'Jovan','Jovanovic','jj@fon.bg.ac.rs',3),
(4,'Nikola','Nikolic','nn@fon.bg.ac.rs',1),
(5,'Jana','Janic','jj1@fon.bg.ac.rs',1),
(6,'Vlatko','Balasevic','vb@fon.bg.ac.rs',1),
(7,'Danijela','Sofijanic','ds@fon.bg.ac.rs',3),
(8,'Petar','Petrovic','pp@fon.bg.ac.rs',2),
(68,'Aleksandra','Jovanovic','aj@fon.bg.ac.rs',1);

/*Table structure for table `termindezurstva` */

DROP TABLE IF EXISTS `termindezurstva`;

CREATE TABLE `termindezurstva` (
  `idTerminDezurstva` bigint(20) NOT NULL AUTO_INCREMENT,
  `vremeOd` time NOT NULL,
  `vremeDo` time NOT NULL,
  PRIMARY KEY (`idTerminDezurstva`),
  CONSTRAINT `chk_vreme` CHECK ((`vremeDo` > `vremeOd`))
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `termindezurstva` */

insert  into `termindezurstva`(`idTerminDezurstva`,`vremeOd`,`vremeDo`) values 
(8,'08:00:00','14:00:00'),
(9,'14:00:00','19:00:00');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
