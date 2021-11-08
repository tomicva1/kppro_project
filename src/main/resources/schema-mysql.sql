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
                            telephone varchar(255) null
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Inserting data for table `employee`
--

INSERT INTO `employee`
VALUES
(1,'Jan', 'Novak','$2a$12$DT1PviXFiw889rX4OITUKeyAvCWrzqRDTBf9Gp63cv6Ap1X2YrY9W','USER','user1','bydliste 10','bydliste','2020-01-17', 122333444),
(2,'Jan', 'Novak','$2a$12$DT1PviXFiw889rX4OITUKeyAvCWrzqRDTBf9Gp63cv6Ap1X2YrY9W','USER','user2','bydliste 10','bydliste','2020-01-17', 122333444),
(3,'Jan', 'Novak','$2a$12$DT1PviXFiw889rX4OITUKeyAvCWrzqRDTBf9Gp63cv6Ap1X2YrY9W','USER','user3','bydliste 10','bydliste','2020-01-17', 122333444),
(4,'Jan', 'Novak','$2a$12$DT1PviXFiw889rX4OITUKeyAvCWrzqRDTBf9Gp63cv6Ap1X2YrY9W','USER','user4','bydliste 10','bydliste','2020-01-17', 122333444),
(5,'Jan', 'Novak','$2a$12$DT1PviXFiw889rX4OITUKeyAvCWrzqRDTBf9Gp63cv6Ap1X2YrY9W','USER','user5','bydliste 10','bydliste','2020-01-17', 122333444),
(6,'Jan', 'Novak','$2a$12$DT1PviXFiw889rX4OITUKeyAvCWrzqRDTBf9Gp63cv6Ap1X2YrY9W','USER','user6','bydliste 10','bydliste','2020-01-17', 122333444);


