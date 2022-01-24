DROP DATABASE  IF EXISTS `kppro_dovolena`;

CREATE DATABASE  IF NOT EXISTS `kppro_dovolena`;
USE `kppro_dovolena`;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE `hibernate_sequence` (
    next_val bigint(20) null
) ENGINE=MyISAM;

DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee` (
                            id int auto_increment
                                primary key,
                            first_name varchar(255) not null,
                            last_name varchar(255) not null,
                            password varchar(255) not null,
                            role varchar(255) not null,
                            username varchar(255) not null,
                            department_id int not null,
                            token varchar(255) null
) ENGINE=MyISAM;

DROP TABLE IF EXISTS `vacation`;
CREATE TABLE `vacation` (
                            id int auto_increment
                                primary key,
                            employee_id int not null,
                            date_from datetime not null,
                            date_to datetime not null,
                            note varchar(255) null,
                            status int not null,
                            update_by int null,
                            update_time datetime null
) ENGINE=MyISAM;

create index FK6qrwq18c5kq468gvsmwm43cgp
    on vacation (update_by);

create index FK6qrwq18c5kq468gvsmwm44cgp
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
                            author int not null,
                            creation_time datetime not null,
                            employee_id int not null
) ENGINE=MyISAM;

create index FK6qrwq18c5kq468gvsmwm46cgp
    on feedback (author);

create index FK6qrwq18c5kq468gvsmwm43cgp
    on feedback (employee_id);


--
-- Inserting data for table `employee`
--
INSERT INTO `hibernate_sequence` VALUES (1000);

INSERT INTO `department`
VALUES
(1,123, 'Dev department', 'DEV'),
(2,123,'Product department', 'PROD');

INSERT INTO `employee`
VALUES
(1,'John', 'Nowak', '$2a$10$h7B0/27moSbdj47MAjH0iuJDrit6eJARN7ff9nHDJ6umUooauHYn6','USER','user1', 1, ''),
(2,'Peter', 'Lesser','$2a$10$h7B0/27moSbdj47MAjH0iuJDrit6eJARN7ff9nHDJ6umUooauHYn6','USER','user2', 1, ''),
(3,'Bob', 'Smith','$2a$12$DT1PviXFiw889rX4OITUKeyAvCWrzqRDTBf9Gp63cv6Ap1X2YrY9W','USER','user3', 2, ''),
(4,'Barack', 'Obama','$2a$12$DT1PviXFiw889rX4OITUKeyAvCWrzqRDTBf9Gp63cv6Ap1X2YrY9W','USER','user4', 2, ''),
(5,'Jonnathan', 'Plane','$2a$12$DT1PviXFiw889rX4OITUKeyAvCWrzqRDTBf9Gp63cv6Ap1X2YrY9W','USER','user5', 2, ''),
(6,'Nyguen', 'Juges','$2a$12$DT1PviXFiw889rX4OITUKeyAvCWrzqRDTBf9Gp63cv6Ap1X2YrY9W','MANAGER','manager1', 1, '');

INSERT INTO `feedback`
VALUES
(1,5, 'Work morale is scarce', 6, cast('2022-01-25 00:00:00' AS datetime ), 1),
(2,1, 'Sample Employee', 6, cast('2022-01-25 00:00:00' AS datetime ), 2),
(3,4, 'Effort there is, but unfortunately the result of work zero', 6, cast('2022-01-25 00:00:00' AS datetime ), 3),
(4,3, 'Good average, something worked, something not.', 6, cast('2022-01-25 00:00:00' AS datetime ), 4),
(5,5, 'Very below average performance', 6, cast('2022-01-25 00:00:00' AS datetime ), 1);

INSERT INTO `vacation`
VALUES (1, 1, cast('2022-01-26 00:00:00' AS datetime ), cast('2022-01-31 00:00:00' AS datetime ),'First vacation',0, null, null),
       (2, 1, cast('2022-01-26 00:00:00' AS datetime ), cast('2022-01-31 00:00:00' AS datetime ),'Second vacation',0, null, null),
       (3, 1, cast('2022-01-26 00:00:00' AS datetime ), cast('2022-01-31 00:00:00' AS datetime ),'Third vacation',0, null, null),
       (4, 1, cast('2022-01-26 00:00:00' AS datetime ), cast('2022-01-31 00:00:00' AS datetime ),'Fouth vacation',0, null, null),
       (5, 1, cast('2022-01-26 00:00:00' AS datetime ), cast('2022-01-31 00:00:00' AS datetime ),'Fifth vacation',0, null, null),
       (6, 1, cast('2022-01-26 00:00:00' AS datetime ), cast('2022-01-31 00:00:00' AS datetime ),'Sixth vacation',0, null, null),
       (7, 1, cast('2022-01-26 00:00:00' AS datetime ), cast('2022-01-31 00:00:00' AS datetime ),'Seventh vacation',0, null, null);