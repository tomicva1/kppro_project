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
) ENGINE=MyISAM;

DROP TABLE IF EXISTS `vacation`;
CREATE TABLE `vacation` (
                            id int auto_increment
                                primary key,
                            employee_id date not null,
                            start_date date not null,
                            end_date date not null,
                            approved boolean not null,
                            approvedBy varchar(255) null,
                            approval_time date null
) ENGINE=MyISAM;

create index FK6qrwq18c5kq468gvsmwm43cgp
    on vacation (employee_id);



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

