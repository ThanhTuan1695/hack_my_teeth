-- phpMyAdmin SQL Dump
-- version 4.6.6deb5
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Jul 17, 2018 at 09:45 PM
-- Server version: 10.1.29-MariaDB-6+b1
-- PHP Version: 7.1.16-1+b2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `dentist`
--

-- --------------------------------------------------------

--
-- Table structure for table `appointment`
--

CREATE TABLE `appointment` (
  `appID` int(10) NOT NULL,
  `title` text NOT NULL,
  `time` datetime NOT NULL,
  `description` text,
  `cusID` int(10) NOT NULL,
  `denID` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `appointment`
--

INSERT INTO `appointment` (`appID`, `title`, `time`, `description`, `cusID`, `denID`) VALUES
(1, 'Fixing teeth!', '2018-07-19 10:15:00', 'I need dr Tin to fix my teeth', 1, 4),
(2, 'My mouth needs checking', '2018-07-18 15:00:00', 'My mouth is really hurt and i need doctor to check it for me', 5, 2),
(3, 'Itchy mouth', '2018-07-19 17:00:00', 'I have really bad mouth ulcers ', 3, 2);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `userID` int(11) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(255) NOT NULL,
  `firstName` varchar(255) NOT NULL,
  `lastName` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `role` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`userID`, `username`, `password`, `firstName`, `lastName`, `email`, `role`) VALUES
(1, 'ardy01', 'ardy123', 'Ardyanto', NULL, 'ardyoo@gmail.com', 0),
(2, 'drEvil01', 'iamevil', 'Doctor Evil', 'Man', 'evil01@gmail.com', 1),
(3, 'john02', 'johnny', 'John', 'Billing', 'john02@gmail.com', 0),
(4, 'drTin02', 'iamtin', 'Tin', 'Tran', 'iamtin02@gmail.com', 1),
(5, 'alex03', 'alex123', 'Alexander', 'Ryan', 'alexy@gmail.com', 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `appointment`
--
ALTER TABLE `appointment`
  ADD PRIMARY KEY (`appID`),
  ADD KEY `den constrain` (`cusID`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`userID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `appointment`
--
ALTER TABLE `appointment`
  MODIFY `appID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `userID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `appointment`
--
ALTER TABLE `appointment`
  ADD CONSTRAINT `cus constrain` FOREIGN KEY (`cusID`) REFERENCES `user` (`userID`) ON DELETE NO ACTION;

-- Table structure for table `session`
--

CREATE TABLE `session` (
  `userID` int(11) NOT NULL,
  `sessCookie` varchar(64) NOT NULL,
  `username` varchar(20) NOT NULL,
  `role` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `session`
--
ALTER TABLE `session`
  ADD KEY `user cons` (`userID`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `session`
--
ALTER TABLE `session`
  ADD CONSTRAINT `user cons` FOREIGN KEY (`userID`) REFERENCES `user` (`userID`) ON DELETE CASCADE ON UPDATE CASCADE;


/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
