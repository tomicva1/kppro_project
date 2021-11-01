DROP DATABASE  IF EXISTS `kppro_dovolena`;

CREATE DATABASE  IF NOT EXISTS `kppro_dovolena`;
USE `kppro_dovolena`;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee` (
                            id int auto_increment
                                primary key,
                            first_name varchar(255) not null,
                            last_name varchar(255) not null,
                            username varchar(255) not null,
                            password varchar(255) not null,
                            role varchar(255) not null,
                            address varchar(255) not null,
                            city varchar(255) not null,
                            hire_date date null,
                            telephone varchar(255) null,
  PRIMARY KEY (`username`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Inserting data for table `users`
--

INSERT INTO `employee`
VALUES
('Jan', 'Novak','contractor1','$2a$12$QCZNsJTN7pDHl.ytUZixaONiI4VC0x/N75Fny/jXWhhOYUD5Nb0cu','USER','bydliste 10','bydliste','2020-01-17', 122333444);

