-- phpMyAdmin SQL Dump
-- version 3.3.9
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Feb 15, 2020 at 12:06 AM
-- Server version: 5.1.53
-- PHP Version: 5.3.4

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `stockmgt_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `account_regform`
--

CREATE TABLE IF NOT EXISTS `account_regform` (
  `id` int(50) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `secret_word` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`,`secret_word`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Dumping data for table `account_regform`
--


-- --------------------------------------------------------

--
-- Table structure for table `admin_regform`
--

CREATE TABLE IF NOT EXISTS `admin_regform` (
  `id` int(50) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `secret_word` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`,`secret_word`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=9 ;

--
-- Dumping data for table `admin_regform`
--

INSERT INTO `admin_regform` (`id`, `name`, `password`, `secret_word`) VALUES
(1, 'becky', '123', 'owo'),
(4, 'omos', '1234', 'owo'),
(5, 'pauline', '1234', 'owow'),
(6, 'osaro', '1234', 'owo'),
(7, 'osato', '1234', 'owo'),
(8, 'simon', '123', 'owo');

-- --------------------------------------------------------

--
-- Table structure for table `all_privileges`
--

CREATE TABLE IF NOT EXISTS `all_privileges` (
  `id` int(50) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `secret_word` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`,`secret_word`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Dumping data for table `all_privileges`
--


-- --------------------------------------------------------

--
-- Table structure for table `cashflows`
--

CREATE TABLE IF NOT EXISTS `cashflows` (
  `Date` date NOT NULL,
  `Amount` int(50) NOT NULL,
  `Details` varchar(50) NOT NULL,
  `Category` varchar(50) NOT NULL,
  `Flow Type` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `cashflows`
--

INSERT INTO `cashflows` (`Date`, `Amount`, `Details`, `Category`, `Flow Type`) VALUES
('2019-08-06', 150, 'jhjkkn', 'Operating Activities', 'Inflow'),
('2019-03-12', 1200, 'jhjghjrh', 'Operating Activities', 'Inflow'),
('2019-08-02', 6789, 'fdfg', 'Investing Activities', 'Inflow'),
('2019-08-01', 560, 'jjgkf', 'Operating Activities', 'Inflow'),
('2019-08-01', 560, 'jjgkfhjn', 'Investing Activities', 'Inflow'),
('2019-08-01', 560, 'jjgkfhjn', 'Financing Activities', 'Inflow'),
('2019-08-01', 560, 'jjgkfhjn', 'Operating Activities', 'Inflow'),
('2019-08-01', 6000, 'ytyyughb', 'Investing Activities', 'Inflow'),
('2019-08-06', 150, 'jhjkkn', 'Operating Activities', 'Inflow'),
('2019-03-12', 1200, 'jhjghjrh', 'Operating Activities', 'Inflow'),
('2019-08-02', 6789, 'fdfg', 'Investing Activities', 'Inflow'),
('2019-08-01', 560, 'jjgkf', 'Operating Activities', 'Inflow'),
('2019-08-01', 560, 'jjgkfhjn', 'Investing Activities', 'Inflow'),
('2019-08-01', 560, 'jjgkfhjn', 'Financing Activities', 'Inflow'),
('2019-08-01', 560, 'jjgkfhjn', 'Operating Activities', 'Inflow'),
('2019-08-01', 6000, 'ytyyughb', 'Investing Activities', 'Inflow');

-- --------------------------------------------------------

--
-- Table structure for table `expenses`
--

CREATE TABLE IF NOT EXISTS `expenses` (
  `Date` date NOT NULL,
  `Amount Spent` int(50) NOT NULL,
  `Description` varchar(50) NOT NULL,
  `Type` varchar(50) NOT NULL,
  `Others` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `expenses`
--

INSERT INTO `expenses` (`Date`, `Amount Spent`, `Description`, `Type`, `Others`) VALUES
('2019-09-09', 300, 'hjfhj', 'Salary', 'jjj'),
('2019-07-09', 400, 'wareva', 'Utility Bill', 'kj'),
('2019-07-21', 500, 'wareva2', 'Dues', 'kj2'),
('2017-07-14', 5000, 'bvvb', 'Dues', 'hghjttfgvbv'),
('2019-08-05', 150, 'hjjk', 'Salary', 'jhjk'),
('2018-09-05', 5600, 'hhjgj', 'Rent', 'bfhbrjngj'),
('2019-08-07', 12000, 'fuel', 'Utility Bill', 'fhjhhv'),
('2019-09-09', 300, 'hjfhj', 'Salary', 'jjj'),
('2019-07-09', 400, 'wareva', 'Utility Bill', 'kj'),
('2019-07-21', 500, 'wareva2', 'Dues', 'kj2'),
('2017-07-14', 5000, 'bvvb', 'Dues', 'hghjttfgvbv'),
('2019-08-05', 150, 'hjjk', 'Salary', 'jhjk'),
('2018-09-05', 5600, 'hhjgj', 'Rent', 'bfhbrjngj'),
('2019-08-07', 12000, 'fuel', 'Utility Bill', 'fhjhhv');

-- --------------------------------------------------------

--
-- Table structure for table `payments`
--

CREATE TABLE IF NOT EXISTS `payments` (
  `Date` date NOT NULL,
  `Supplier Name` varchar(50) NOT NULL,
  `Balance Payment` int(50) NOT NULL,
  `Balance Receiveable` int(50) NOT NULL,
  `Signed By` varchar(50) NOT NULL,
  `Payment Type` varchar(50) NOT NULL,
  `Amount` int(50) NOT NULL,
  `Name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `payments`
--

INSERT INTO `payments` (`Date`, `Supplier Name`, `Balance Payment`, `Balance Receiveable`, `Signed By`, `Payment Type`, `Amount`, `Name`) VALUES
('2019-08-06', 'hgjbfjbkjf', 100, 200, 'Princess', 'Receive Payment', 2000, 'jfhjkdk'),
('2019-02-06', 'jnkjk', 450, 900, 'rhrhg', 'Make Payment', 560, 'Bulouere'),
('2019-08-06', 'hgjbfjbkjf', 100, 200, 'Princess', 'Receive Payment', 2000, 'jfhjkdk'),
('2019-02-06', 'jnkjk', 450, 900, 'rhrhg', 'Make Payment', 560, 'Bulouere');

-- --------------------------------------------------------

--
-- Table structure for table `pointofsales`
--

CREATE TABLE IF NOT EXISTS `pointofsales` (
  `invoice_number` int(50) NOT NULL,
  `product_category` varchar(50) NOT NULL,
  `Product Name` varchar(50) NOT NULL,
  `Quantity` int(50) NOT NULL,
  `Discount` int(50) NOT NULL,
  `Price` int(50) NOT NULL,
  `Total` int(50) NOT NULL,
  `Payment Method` varchar(50) NOT NULL,
  `date_of_transaction` date NOT NULL,
  `time_of_transaction` time NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pointofsales`
--

INSERT INTO `pointofsales` (`invoice_number`, `product_category`, `Product Name`, `Quantity`, `Discount`, `Price`, `Total`, `Payment Method`, `date_of_transaction`, `time_of_transaction`) VALUES
(1, 'drinks', 'wine', 10, 0, 2000, 20000, 'Cash', '2019-08-06', '05:00:00'),
(2, 'Bread', 'next bread', 20, 0, 350, 7000, 'Cash', '2019-06-05', '11:45:00'),
(3, 'cereal', 'rice', 10, 0, 3000, 30000, 'Cash', '2019-08-04', '09:00:00'),
(4, 'drinks', 'chivita', 2, 0, 2000, 4000, 'Cash', '2019-08-17', '03:05:00'),
(5, 'Bread', 'next bread', 4, 0, 350, 1400, 'Cash', '2019-08-09', '20:06:00'),
(6, 'Alcohol', 'rum', 4, 0, 2000, 8000, 'Cash', '2019-08-05', '05:00:00'),
(5, 'flowers', 'rose', 2, 0, 30000, 60000, 'Cash', '2019-08-01', '07:00:00'),
(7, 'Bread', 'Bon Bread', 5, 0, 400, 2000, 'Cash', '2019-08-20', '05:34:00'),
(8, 'Bread', 'next bread', 6, 0, 400, 2400, 'Card', '2019-08-05', '09:05:00'),
(0, '', 'wine', 10, 0, 2000, 20000, 'Cash', '0000-00-00', '00:00:00'),
(0, '', 'next bread', 20, 0, 350, 7000, 'Cash', '0000-00-00', '00:00:00'),
(0, '', 'rice', 10, 0, 3000, 30000, 'Cash', '0000-00-00', '00:00:00'),
(0, '', 'chivita', 2, 0, 2000, 4000, 'Cash', '0000-00-00', '00:00:00'),
(0, '', 'next bread', 4, 0, 350, 1400, 'Cash', '0000-00-00', '00:00:00'),
(0, '', 'rum', 4, 0, 2000, 8000, 'Cash', '0000-00-00', '00:00:00'),
(0, '', 'rose', 2, 0, 30000, 60000, 'Cash', '0000-00-00', '00:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

CREATE TABLE IF NOT EXISTS `products` (
  `product_id` varchar(50) NOT NULL,
  `product_category` varchar(50) NOT NULL,
  `product_name` varchar(50) NOT NULL,
  `vendor_name` varchar(50) NOT NULL,
  `batch_no` varchar(50) NOT NULL,
  `cost_price` int(50) NOT NULL,
  `selling_price` int(50) NOT NULL,
  `quantity_supplied` int(50) NOT NULL,
  `quantity_returned_by_customer` int(50) NOT NULL,
  `quantity_remaining` int(50) NOT NULL,
  `minimum_stock` int(50) NOT NULL,
  `minimum_shelf` int(50) NOT NULL,
  `quantity_in_shelf` int(50) NOT NULL,
  `manufacturing_date` date NOT NULL,
  `expiry_date` date NOT NULL,
  `supply_date` date NOT NULL,
  PRIMARY KEY (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `products`
--

INSERT INTO `products` (`product_id`, `product_category`, `product_name`, `vendor_name`, `batch_no`, `cost_price`, `selling_price`, `quantity_supplied`, `quantity_returned_by_customer`, `quantity_remaining`, `minimum_stock`, `minimum_shelf`, `quantity_in_shelf`, `manufacturing_date`, `expiry_date`, `supply_date`) VALUES
('0292', 'Milk', 'Peak Milk', '', 'BN12300', 0, 0, 0, 2, 67, 60, 20, 100, '2018-02-08', '2018-08-15', '2018-07-11'),
('0653', 'Milk', 'NuNu Milk', '', 'BN12300', 0, 0, 0, 2, 67, 60, 20, 100, '2018-02-08', '2019-08-15', '2018-07-11'),
('090381', 'Sugar', 'Bua Sugar', 'Select Vendor', 'BN12433', 0, 0, 0, 0, 450, 100, 120, 100, '2020-02-04', '2020-02-20', '2019-05-08'),
('09381', 'Sugar', 'Dangote Sugar', 'Select Vendor', 'BN12433', 0, 0, 0, 0, 450, 100, 120, 100, '2019-06-04', '2019-06-20', '2019-05-08'),
('09473', 'Soaps', 'Vix Soap', '', 'Ps12345', 0, 0, 0, 3, 300, 100, 120, 200, '2019-06-03', '2020-08-22', '2019-02-05'),
('09873', 'Soaps', 'Premier Soap', '', 'Ps12345', 0, 0, 0, 3, 300, 100, 120, 200, '2019-06-03', '2019-08-22', '2019-02-05'),
('10223', 'bread', 'flatbread', 'Vendor', '1000000', 120, 150, 200, 0, 180, 150, 0, 0, '2019-07-31', '2020-09-06', '2019-08-06'),
('1023', 'bread', 'flatbread', 'Vendor', '1000000', 120, 150, 200, 0, 180, 150, 0, 0, '2019-07-31', '2019-09-06', '2019-08-06'),
('11', 'bread', 'Bon Bread', 'Vendor', '3333', 300, 400, 50, 0, 41, 50, 0, 0, '2019-07-02', '2019-08-13', '2019-08-01'),
('1143', 'bread', 'Bon Bread', 'Vendor', '3333', 300, 400, 50, 0, 41, 50, 0, 0, '2019-07-02', '2020-08-13', '2019-08-01'),
('120', 'perfume', 'gucci', 'Vendor', '656333', 45000, 60000, 20, 0, 4, 10, 0, 0, '2017-08-08', '2021-08-07', '2019-08-07'),
('1202', 'perfume', 'gucci', 'Vendor', '656333', 45000, 60000, 20, 0, 4, 10, 0, 0, '2017-08-08', '2020-08-07', '2019-08-07'),
('34', 'alcohol', 'wine', 'Select Vendor', '675889', 1400, 2000, 250, 0, 246, 200, 0, 0, '2018-01-01', '2021-08-09', '2019-07-20'),
('342', 'alcohol', 'wine', 'Select Vendor', '675889', 1400, 2000, 250, 0, 246, 200, 0, 0, '2018-01-01', '2021-08-09', '2019-07-20'),
('382372', 'maggi', 'Star Maggi', '', 'mm34982', 0, 0, 0, 0, 209, 150, 50, 70, '2019-07-10', '2020-07-20', '2018-11-07'),
('38272', 'maggi', 'Star Maggi', '', 'mm34982', 0, 0, 0, 0, 209, 150, 50, 70, '2019-07-10', '2019-07-20', '2018-11-07'),
('3987', 'Milk', 'Milksi', '', 'BN12400', 0, 0, 0, 3, 300, 100, 120, 200, '2018-08-04', '2019-08-22', '2019-05-17'),
('39874', 'bread', 'EasyBite Bread', '', 'BN12764', 0, 0, 0, 0, 12, 60, 20, 1, '2018-02-08', '2018-02-15', '2019-07-24'),
('398746', 'bread', 'Square Bread', '', 'BN12764', 0, 0, 0, 0, 12, 60, 20, 1, '2018-02-08', '2018-02-15', '2019-07-24'),
('39875', 'Milk', 'Cowbell', '', 'BN12400', 0, 0, 0, 3, 300, 100, 120, 200, '2018-08-04', '2019-08-22', '2019-05-17'),
('453432', 'maggi', 'star', '', 'BN12498', 0, 0, 0, 3, 23, 100, 120, 2, '2019-06-04', '2019-06-13', '2019-05-13'),
('45432', 'maggi', 'Knor', '', 'BN12498', 0, 0, 0, 3, 23, 100, 120, 2, '2019-06-04', '2019-06-13', '2019-05-13'),
('4567', 'drinks', 'chivita', 'Vendor', '76876789', 100, 150, 200, 0, 195, 150, 0, 0, '2019-07-01', '2019-10-04', '2019-08-07'),
('45670', 'drinks', 'Happy Hour', 'Vendor', '76876789', 100, 150, 200, 0, 195, 150, 0, 0, '2020-03-01', '2019-10-04', '2019-08-07'),
('54625', 'Soft Drinks', 'Smoove', '', 'BB45736', 0, 0, 0, 12, 40, 50, 29, 200, '2019-05-06', '2020-04-02', '2019-06-11'),
('54628', 'Soft Drinks', 'Coke', '', 'BB45736', 0, 0, 0, 12, 40, 50, 29, 200, '2019-05-06', '2019-07-02', '2019-06-11'),
('567hhb', 'alcohol', 'rum', 'Item 4', '6767878', 1500, 2000, 100, 0, 96, 50, 0, 0, '2018-12-31', '2020-01-12', '2019-08-02'),
('567hvhb', 'alcohol', 'rum', 'Item 4', '6767878', 1500, 2000, 100, 0, 96, 50, 0, 0, '2018-12-31', '2020-01-12', '2019-08-02'),
('67', 'alcohol', 'rose', 'Item 1', '8789', 25000, 30000, 50, 0, 48, 25, 0, 0, '2018-07-18', '2020-12-30', '2019-08-08'),
('675', 'alcohol', 'rose', 'Item 1', '8789', 25000, 30000, 50, 0, 48, 25, 0, 0, '2018-07-18', '2020-12-30', '2019-08-08'),
('83736', 'Soft Drinks', 'Fanta', '', 'BB12336', 0, 0, 0, 1, 230, 340, 50, 200, '2019-07-01', '2019-07-10', '2019-06-03'),
('837536', 'Soft Drinks', 'Dubic Malt', '', 'BB12336', 0, 0, 0, 1, 230, 340, 50, 200, '2019-07-01', '2019-07-10', '2019-06-03'),
('89', 'bread', 'next bread', 'Vendor', '6789', 300, 400, 3000, 0, 2996, 2500, 0, 0, '2018-09-10', '2019-10-23', '2019-08-08'),
('89046', 'perfume', 'calvin', 'Select Vendor', '74675839', 60000, 65000, 10, 0, 10, 6, 0, 0, '2019-02-12', '2021-08-13', '2019-08-06'),
('8906', 'perfume', 'd&g', 'Select Vendor', '74675839', 60000, 65000, 10, 0, 10, 6, 0, 0, '2019-02-12', '2021-08-13', '2019-08-06'),
('894', 'bread', 'royal bread', 'Vendor', '6789', 300, 400, 3000, 0, 2996, 2500, 0, 0, '2018-09-10', '2019-10-23', '2019-08-08'),
('90', 'Select Category', 'handbag', 'Vendor', '909090', 45000, 60000, 10, 0, 10, 5, 0, 0, '2018-08-04', '2020-03-04', '2019-08-06'),
('904', 'Select Category', 'handbag', 'Vendor', '909090', 45000, 60000, 10, 0, 10, 5, 0, 0, '2018-08-04', '2020-03-04', '2019-08-06'),
('938474', 'bread', 'Nosa Bread', '', 'MB32412', 0, 0, 0, 0, 12, 60, 20, 100, '2019-06-14', '2019-05-12', '2019-03-05'),
('93874', 'bread', 'Mike Bread', '', 'MB32412', 0, 0, 0, 0, 12, 60, 20, 100, '2019-06-14', '2019-05-12', '2019-03-05'),
('98334', 'Soaps', 'Lux Soap', '', 'BN12300', 0, 0, 0, 2, 200, 60, 20, 100, '2019-06-03', '2018-06-15', '2019-04-08'),
('983344', 'Soaps', 'Dettol Soap', '', 'BN12300', 0, 0, 0, 2, 200, 60, 20, 100, '2019-06-03', '2018-06-15', '2019-04-08'),
('PID120PM', 'Milk', 'Peak Milk', 'xyz', 'BB9837', 200, 300, 78, 0, 67, 25, 20, 50, '2019-07-01', '2019-08-16', '2019-08-01'),
('PID1240PM', 'Milk', 'Peak Milk', 'xyz', 'BB9837', 200, 300, 78, 0, 67, 25, 20, 50, '2019-07-01', '2019-08-16', '2019-08-01'),
('PID1304PM', 'Milk', 'Peak Milk', 'xyz', 'BV0237', 200, 300, 78, 0, 67, 25, 20, 50, '2019-07-01', '2019-08-26', '2019-04-01'),
('PID130PM', 'Milk', 'Peak Milk', 'xyz', 'BV0237', 200, 300, 78, 0, 67, 25, 20, 50, '2019-07-01', '2019-08-26', '2019-04-01'),
('ydyui4f', 'Item 4', 'Fanta', 'Item 3', '145qu3', 150, 170, 300, 0, 0, 100, 0, 0, '2019-01-01', '2019-12-20', '2019-08-01'),
('ydyuif', 'Item 4', 'Fanta', 'Item 3', '145qu3', 150, 170, 300, 0, 0, 100, 0, 0, '2019-01-01', '2019-12-20', '2019-08-01');

-- --------------------------------------------------------

--
-- Table structure for table `receipts`
--

CREATE TABLE IF NOT EXISTS `receipts` (
  `invoice_number` varchar(12) NOT NULL,
  `date_made` datetime NOT NULL,
  `total` varchar(50) NOT NULL,
  `payment_method` varchar(4) NOT NULL,
  PRIMARY KEY (`invoice_number`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `receipts`
--

INSERT INTO `receipts` (`invoice_number`, `date_made`, `total`, `payment_method`) VALUES
('201908231612', '2019-08-23 11:50:10', '945.00', 'Cash'),
('201908235953', '2019-08-23 12:08:48', '4,896.00', 'Cash'),
('201908233828', '2019-08-23 12:13:37', '499,560.00', 'Cash'),
('201908294076', '2019-08-29 11:19:57', '750.00', 'Cash');

-- --------------------------------------------------------

--
-- Table structure for table `regform`
--

CREATE TABLE IF NOT EXISTS `regform` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(50) NOT NULL,
  `Password` varchar(50) NOT NULL,
  `Secret Word` varchar(50) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=32 ;

--
-- Dumping data for table `regform`
--

INSERT INTO `regform` (`Id`, `Name`, `Password`, `Secret Word`) VALUES
(1, 'krml;gmlmkl', 'ekmfgtetgf', 'egfetfg'),
(2, 'Segun', 'dafe', 'segun'),
(3, 'Segun', 'seg', 'segs'),
(4, '', '', ''),
(5, 'me', 'me', 'me'),
(6, 'you', 'you', 'you'),
(7, 'they', 'they', 'they'),
(8, 'them', 'them', 'them'),
(9, 'i', 'i', 'i'),
(10, 'f', 'g', 'g'),
(11, '', '', ''),
(12, 'S', 'S', 'S'),
(13, 'B', 'B', 'B'),
(14, 'h', 'h', 'h'),
(15, 'k', 'k', 'k'),
(16, 'm', 'm', 'm'),
(17, 'v', 'v', 'v'),
(18, 'Anthony', 'buchi', 'buchi'),
(19, '', '', ''),
(20, 'Segun', 'segun', 'segun'),
(21, 'd', 'd', 'd'),
(22, 'Tofu', 'tofu', 'bozo'),
(23, 'David', 'david', 'david'),
(24, 'Nicholas', 'Nicholas', 'Nicholas'),
(25, 'Ebuka', 'ebuka', 'ebuks'),
(26, 'PaJerry', 'jerry', 'joo'),
(27, 'James', 'jam', 'JAM'),
(28, 'Timothy', 'timo', 'name'),
(29, 'Israel Brume', 'israel', 'israel'),
(30, 'Israel John', 'john', 'john'),
(31, 'messi', 'messi', 'messi');

-- --------------------------------------------------------

--
-- Table structure for table `remittance`
--

CREATE TABLE IF NOT EXISTS `remittance` (
  `Sales Made` int(50) NOT NULL,
  `Cash Sales Made` int(50) NOT NULL,
  `Card Sales Made` int(50) NOT NULL,
  `Amount Remitted` int(50) NOT NULL,
  `Remitted To` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `remittance`
--

INSERT INTO `remittance` (`Sales Made`, `Cash Sales Made`, `Card Sales Made`, `Amount Remitted`, `Remitted To`) VALUES
(100, 200, 350, 35000, 'Segun'),
(100, 200, 350, 35000, 'Segun'),
(100, 20, 50, 55, 'Segun');

-- --------------------------------------------------------

--
-- Table structure for table `return purchase`
--

CREATE TABLE IF NOT EXISTS `return purchase` (
  `Date of Purchase` date NOT NULL,
  `Returned Product Name` varchar(50) NOT NULL,
  `Quantity Returned` int(50) NOT NULL,
  `Invoice Number` varchar(50) NOT NULL,
  `Quantity Purchased` int(50) NOT NULL,
  `Refunded Amount` int(50) NOT NULL,
  `Supervisor on Duty` varchar(50) NOT NULL,
  `Sales Staff on Duty` varchar(50) NOT NULL,
  `Reason for Return` varchar(50) NOT NULL,
  `Date of Return` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `return purchase`
--

INSERT INTO `return purchase` (`Date of Purchase`, `Returned Product Name`, `Quantity Returned`, `Invoice Number`, `Quantity Purchased`, `Refunded Amount`, `Supervisor on Duty`, `Sales Staff on Duty`, `Reason for Return`, `Date of Return`) VALUES
('0008-10-19', 'Item 2', 3, '222', 3, 233, 'vdfa', 'aea', '', '0008-10-19'),
('0008-10-19', 'Item 2', 3, '222', 3, 233, 'vdfa', 'aea', '', '0008-10-19');

-- --------------------------------------------------------

--
-- Table structure for table `sale`
--

CREATE TABLE IF NOT EXISTS `sale` (
  `invoice_number` varchar(12) NOT NULL,
  `product_id` varchar(30) NOT NULL,
  `quantity` int(30) NOT NULL,
  `discount` int(30) NOT NULL,
  `total` varchar(30) NOT NULL,
  KEY `product_id` (`product_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sale`
--

INSERT INTO `sale` (`invoice_number`, `product_id`, `quantity`, `discount`, `total`) VALUES
('201908231612', '1023', 7, 10, '945.00'),
('201908235953', 'PID400PM', 5, 4, '1,440.00'),
('201908235953', '11', 9, 4, '3,456.00'),
('201908233828', '1023', 8, 8, '1,104.00'),
('201908233828', 'PID400PM', 6, 8, '1,656.00'),
('201908233828', '120', 9, 8, '496,800.00'),
('201908294076', '1023', 5, 0, '750.00');

-- --------------------------------------------------------

--
-- Table structure for table `sales_regform`
--

CREATE TABLE IF NOT EXISTS `sales_regform` (
  `id` int(50) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `secret_word` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`,`secret_word`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Dumping data for table `sales_regform`
--


-- --------------------------------------------------------

--
-- Table structure for table `stockedgoods`
--

CREATE TABLE IF NOT EXISTS `stockedgoods` (
  `product_name` varchar(50) NOT NULL,
  `product_category` varchar(50) NOT NULL,
  `batch_number` varchar(50) NOT NULL,
  `manufacturing_date` date NOT NULL,
  `expiry_date` date NOT NULL,
  `minimum_stock_limit` int(50) NOT NULL,
  `minimum_shelf_limit` int(50) NOT NULL,
  `quantity_supplied` int(50) NOT NULL,
  `quantity_returned` int(50) NOT NULL,
  `quantity_in_stock` int(50) NOT NULL,
  `quantity_in_shelf` int(50) NOT NULL,
  KEY `product_category` (`product_category`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `stockedgoods`
--

INSERT INTO `stockedgoods` (`product_name`, `product_category`, `batch_number`, `manufacturing_date`, `expiry_date`, `minimum_stock_limit`, `minimum_shelf_limit`, `quantity_supplied`, `quantity_returned`, `quantity_in_stock`, `quantity_in_shelf`) VALUES
('Peak Milk', 'Milk', 'BN12300', '2018-02-08', '2018-08-15', 60, 20, 0, 2, 200, 100),
('Milksi', 'Milk', 'BN12400', '2018-08-04', '2019-08-22', 100, 120, 0, 3, 300, 200),
('Lux Soap', 'Soaps', 'BN12300', '2019-06-03', '2018-06-15', 60, 20, 0, 2, 200, 100),
('Premier Soap', 'Soaps', 'Ps12345', '2019-06-03', '2019-08-22', 100, 120, 0, 3, 300, 200),
('Bon Bread', 'bread', 'BN12764', '2018-02-08', '2018-02-15', 60, 20, 0, 0, 12, 1),
('Knor', 'maggi', 'BN12498', '2019-06-04', '2019-06-13', 100, 120, 0, 3, 23, 2),
('Mike Bread', 'bread', 'MB32412', '2019-06-14', '2019-05-12', 60, 20, 0, 0, 12, 100),
('Dangote Sugar', 'Sugar', 'BN12433', '2019-06-04', '2019-06-20', 100, 120, 0, 0, 450, 100),
('Fanta', 'Soft Drinks', 'BB12336', '2019-07-01', '2019-07-10', 340, 50, 0, 1, 230, 200),
('Coke', 'Soft Drinks', 'BB45736', '2019-05-06', '2019-07-02', 50, 29, 0, 12, 40, 200),
('Star Maggi', 'maggi', 'mm34982', '2019-07-10', '2019-07-20', 150, 50, 0, 0, 209, 70);

-- --------------------------------------------------------

--
-- Table structure for table `stockedgoodscategory`
--

CREATE TABLE IF NOT EXISTS `stockedgoodscategory` (
  `category_id` int(50) NOT NULL,
  `product_category` varchar(50) NOT NULL,
  PRIMARY KEY (`product_category`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `stockedgoodscategory`
--

INSERT INTO `stockedgoodscategory` (`category_id`, `product_category`) VALUES
(0, 'Milk'),
(2, 'Sugar'),
(1, 'Soaps'),
(0, 'bread'),
(0, 'maggi'),
(4, 'Soft Drinks');

-- --------------------------------------------------------

--
-- Table structure for table `suppliers`
--

CREATE TABLE IF NOT EXISTS `suppliers` (
  `id` int(50) NOT NULL,
  `vendorname` varchar(50) NOT NULL,
  PRIMARY KEY (`vendorname`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `suppliers`
--


-- --------------------------------------------------------

--
-- Table structure for table `suppliers_details`
--

CREATE TABLE IF NOT EXISTS `suppliers_details` (
  `vendorname` varchar(50) NOT NULL,
  `phone_number` varchar(50) NOT NULL,
  `vendor_address` varchar(50) NOT NULL,
  `vendor_email` varchar(50) NOT NULL,
  `goods_supplied` varchar(50) NOT NULL,
  `date_of_supply` date NOT NULL,
  `supplier_balance_payable` int(50) NOT NULL,
  `supplier_balance_receivable` int(50) NOT NULL,
  KEY `vendorname` (`vendorname`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `suppliers_details`
--

INSERT INTO `suppliers_details` (`vendorname`, `phone_number`, `vendor_address`, `vendor_email`, `goods_supplied`, `date_of_supply`, `supplier_balance_payable`, `supplier_balance_receivable`) VALUES
('Segun', '08065957175', 'Nigerian Reinsurance Building', 'seguno@gmail.com', 'Cars', '2018-09-19', 450000, 30000),
('Goodluck Jonathan International', '090456444', 'Kado Estate', 'goodluck@yahoo.com', 'Hats', '2018-07-24', 250000, 387468000),
('Okocha Wears', '0909756543', 'balogun market, lagos', 'okocha@yahoo.com', 'Shoes', '2018-07-09', 90000, 50000),
('Ronaldo International', '0909754444', 'Aminu Kano, Wuse 2', 'ronaldo@yahoo.com', 'Sports Jersey', '2018-07-15', 2390000, 50000000),
('Mike Company', '09039271983', 'Akure', 'mikecompany@yahoo.com', 'Mike Bread', '2019-08-09', 845000, 4523000),
('Omoba Nig Ltd', '0907094946', 'Kogi State', 'omobanigltd@yahoo.com', 'Chocolates', '2018-07-29', 14000, 373000);
