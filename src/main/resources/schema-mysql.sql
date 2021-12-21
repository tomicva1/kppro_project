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
                            role varchar(255) not null
) ENGINE=MyISAM;

DROP TABLE IF EXISTS `vacation`;
CREATE TABLE `vacation` (
                            id int auto_increment
                                primary key,
                            employee_id int not null,
                            dateFrom date not null,
                            dateTo date not null,
                            note varchar(255) null,
                            approved boolean not null,
                            approvedBy int null,
                            approvalTime date null
) ENGINE=MyISAM;

create index FK6qrwq18c5kq468gvsmwm43cgp
    on vacation (employee_id);

DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
                            id int auto_increment
                                primary key,
                            map int null,
                            description varchar(255) null,
                            name varchar(255) not null
) ENGINE=MyISAM;

DROP TABLE IF EXISTS `feedback`;
CREATE TABLE `feedback` (
                              id int auto_increment
                                  primary key,
                              quality int not null,
                              note varchar(255) not null,
                              employee_id int not null
) ENGINE=MyISAM;

create index FK6qrwq18c5kq468gvsmwm43cgp
    on feedback (employee_id);


--
-- Inserting data for table `employee`
--

INSERT INTO `employee`
VALUES
(1,'Jan', 'Novak','$2a$10$h7B0/27moSbdj47MAjH0iuJDrit6eJARN7ff9nHDJ6umUooauHYn6','USER','user1','bydliste 10','bydliste','2020-01-17', 122333444),
(2,'Jan1', 'Novak1','$2a$10$h7B0/27moSbdj47MAjH0iuJDrit6eJARN7ff9nHDJ6umUooauHYn6','USER','user2','bydliste 10','bydliste','2020-01-17', 122333444),
(3,'Jan2', 'Novak2','$2a$12$DT1PviXFiw889rX4OITUKeyAvCWrzqRDTBf9Gp63cv6Ap1X2YrY9W','USER','user3','bydliste 10','bydliste','2020-01-17', 122333444),
(4,'Jan3', 'Novak3','$2a$12$DT1PviXFiw889rX4OITUKeyAvCWrzqRDTBf9Gp63cv6Ap1X2YrY9W','USER','user4','bydliste 10','bydliste','2020-01-17', 122333444),
(5,'Jan4', 'Novak4','$2a$12$DT1PviXFiw889rX4OITUKeyAvCWrzqRDTBf9Gp63cv6Ap1X2YrY9W','USER','user5','bydliste 10','bydliste','2020-01-17', 122333444),
(6,'Jan8', 'Novak8','$2a$12$DT1PviXFiw889rX4OITUKeyAvCWrzqRDTBf9Gp63cv6Ap1X2YrY9W','MANAGER','manager1','bydliste 10','bydliste','2020-01-17', 122333444);

