-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: sm_resort
-- ------------------------------------------------------
-- Server version	8.0.36

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `customers`
--

DROP TABLE IF EXISTS `customers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customers` (
  `CustomerID` varchar(50) NOT NULL,
  `FirstName` varchar(255) DEFAULT NULL,
  `LastName` varchar(255) DEFAULT NULL,
  `Nationality` varchar(255) DEFAULT NULL,
  `Email` varchar(255) DEFAULT NULL,
  `Phone` varchar(255) DEFAULT NULL,
  `Address` text,
  `RegistrationDate` date DEFAULT NULL,
  `TotalReservations` int DEFAULT NULL,
  PRIMARY KEY (`CustomerID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customers`
--

LOCK TABLES `customers` WRITE;
/*!40000 ALTER TABLE `customers` DISABLE KEYS */;
INSERT INTO `customers` VALUES ('CUST001','John','Doe','Spanish','johndoe@example.com','+1234567892','123 Main St, Anytown, USA','2023-04-01',0),('CUST002','Jane','Smith','British','janesmith@example.com','+441234567890','456 High St, London, UK','2023-04-05',NULL),('CUST003','Muhammad','Ali','Sri Lankan','malikali@example.com','+94771234567','789 Galle Rd, Colombo, Sri Lanka','2023-04-08',NULL),('CUST004','Maria','Garcia','Spanish','mariagarcia@example.com','+34678901234','321 Calle Principal, Madrid, Spain','2023-04-12',NULL),('CUST005','Hiroshi','Tanaka','Japanese','hiroshitanaka@example.com','+819012345678','123 Tokyo St, Tokyo, Japan','2023-04-15',NULL),('CUST006','Sofia','Gomez','Colombian','sofiagomez@example.com','+573123456789','456 Carrera St, Bogota, Colombia','2023-04-20',NULL),('CUST007','Chen','Wei','Chinese','chenwei@example.com','+8612345678901','789 Beijing Rd, Shanghai, China','2023-04-25',NULL),('CUST008','Anna','Kowalska','Polish','annakowalska@example.com','+48123456789','123 Krakowska St, Warsaw, Poland','2023-04-30',NULL),('CUST009','Ramesh','Patel','Indian','rameshpatel@example.com','+919876543210','456 Mumbai Rd, Mumbai, India','2023-05-02',NULL),('CUST010','Fatima','Al-Farsi','Omani','fatimaalfarsi@example.com','+96812345678','789 Muscat St, Muscat, Oman','2023-05-07',NULL),('CUST011','Tom','Brown','British','tombrown@example.com','+441234567891','234 Oxford St, London, UK','2023-05-12',NULL),('CUST012','Jose','Santos','Brazilian','josesantos@example.com','+5511987654321','567 Copacabana Ave, Rio de Janeiro, Brazil','2023-05-15',NULL),('CUST013','Li','Ying','Chinese','liying@example.com','+8612345678902','890 Nanjing Rd, Beijing, China','2023-05-18',NULL),('CUST014','Hans','Mueller','German','hansmueller@example.com','+491234567890','123 Berliner Strasse, Berlin, Germany','2023-05-22',NULL),('CUST015','Ana','Gonzalez','Spanish','anagonzalez@example.com','+34612345678','456 Plaza Mayor, Madrid, Spain','2023-05-25',NULL),('CUST016','Yuki','Yamamoto','Japanese','yukiyamamoto@example.com','+819012345679','789 Osaka St, Osaka, Japan','2023-05-28',NULL),('CUST017','Priya','Singh','Indian','priyasingh@example.com','+917654321098','234 Delhi Rd, Delhi, India','2023-06-02',NULL),('CUST018','Sven','Andersen','Danish','svenandersen@example.com','+4512345678','567 Copenhagen St, Copenhagen, Denmark','2023-06-04',NULL),('CUST019','Luis','Martinez','Mexican','luismartinez@example.com','+521234567890','890 Avenida Reforma, Mexico City, Mexico','2023-06-10',NULL),('CUST020','Jasmine','Lee','Korean','jasminelee@example.com','+821012345678','123 Gangnam St, Seoul, South Korea','2023-06-15',NULL),('CUST021','Giovanni','Ricci','Italian','giovanniricci@example.com','+391234567890','456 Via Roma, Rome, Italy','2023-06-18',NULL),('CUST022','Ahmed','Abdel-Maguid','Egyptian','ahmedabdelmaguid@example.com','+201234567890','789 Sharia el-Nil, Cairo, Egypt','2023-06-19',NULL),('CUST023','Eva','Novak','Czech','evanovak@example.com','+420123456789','234 Prague St, Prague, Czech Republic','2023-06-22',NULL),('CUST024','Yusuf','Khan','Pakistani','yusufkhan@example.com','+923456789012','567 Lahore Rd, Lahore, Pakistan','2023-06-28',NULL),('CUST025','Sophie','Lefevre','French','sophielefevre@example.com','+33123456789','890 Champs-Élysées, Paris, France','2023-07-03',NULL),('CUST026','Carlos','Silva','Portuguese','carlossilva@example.com','+351123456789','123 Lisbon St, Lisbon, Portugal','2023-07-10',NULL),('CUST027','Lina','Petrescu','Romanian','linapetrescu@example.com','+40123456789','456 Bucharest St, Bucharest, Romania','2023-07-12',NULL),('CUST028','Anil','Verma','Indian','anilverma@example.com','+918765432109','789 Bangalore Rd, Bangalore, India','2023-07-18',NULL),('CUST029','Nadia','Ivanova','Russian','nadiaivanova@example.com','+79123456789','234 Moscow St, Moscow, Russia','2023-07-21',NULL),('CUST030','Federico','Lopez','Argentinian','federicolopez@example.com','+541123456789','567 Avenida Corrientes, Buenos Aires, Argentina','2023-07-24',NULL),('CUST031','Kamal','Perera','Sri Lankan','kamalperera@example.com','+94773456789','123 Negombo Rd, Negombo, Sri Lanka','2023-07-26',NULL),('CUST032','Samantha','Fernando','Sri Lankan','samanthafernando@example.com','+94774456789','456 Kandy Rd, Kandy, Sri Lanka','2023-07-30',NULL),('CUST033','Rohan','Silva','Sri Lankan','rohansilva@example.com','+94775456789','789 Colombo Rd, Colombo, Sri Lanka','2023-08-08',NULL),('CUST034','Julia','Schneider','German','juliaschneider@example.com','+491234567891','123 Munich St, Munich, Germany','2023-08-11',NULL),('CUST035','David','Smith','Australian','davidsmith@example.com','+611234567890','456 Sydney St, Sydney, Australia','2023-08-14',NULL),('CUST036','Maria','Perez','Mexican','mariaperez@example.com','+521234567891','789 Tijuana Rd, Tijuana, Mexico','2023-08-17',NULL),('CUST037','Ali','Mohammed','Emirati','alimohammed@example.com','+971234567890','234 Dubai St, Dubai, UAE','2023-08-21',NULL),('CUST038','Elena','Ivanova','Russian','elenaiivanova@example.com','+791234567891','567 Moscow St, Moscow, Russia','2023-08-24',NULL),('CUST039','Mark','Johnson','Canadian','markjohnson@example.com','+141612345678','890 Toronto St, Toronto, Canada','2023-08-26',NULL),('CUST040','Hassan','Al-Mansoor','Saudi Arabian','hassanalmansoor@example.com','+966123456789','123 Riyadh St, Riyadh, Saudi Arabia','2023-08-28',NULL),('CUST041','Yan','Chen','Chinese','yanchen@example.com','+8612345678903','456 Guangzhou Rd, Guangzhou, China','2023-09-03',NULL),('CUST042','Lucia','Ricci','Italian','luciaricci@example.com','+391234567891','789 Venice St, Venice, Italy','2023-09-05',NULL),('CUST043','Kim','Soo-jin','Korean','kimsoojin@example.com','+821234567890','123 Busan St, Busan, South Korea','2023-09-08',NULL),('CUST044','Oliver','Smith','British','oliversmith@example.com','+441234567892','234 Manchester St, Manchester, UK','2023-09-16',NULL),('CUST045','Emma','Andersson','Swedish','emmaandersson@example.com','+46123456789','567 Stockholm St, Stockholm, Sweden','2023-09-19',NULL),('CUST046','Youssef','Abdel-Maguid','Egyptian','youssefabdelmaguid@example.com','+201234567891','890 Alexandria St, Alexandria, Egypt','2023-09-23',NULL),('CUST047','Sophia','Mueller','German','sophiamueller@example.com','+491234567892','123 Hamburg St, Hamburg, Germany','2023-09-25',NULL),('CUST048','Jose','Martinez','Mexican','josemartinez@example.com','+521234567892','456 Guadalajara St, Guadalajara, Mexico','2023-09-30',NULL),('CUST049','Ahmed','Al-Farsi','Omani','ahmedalfarsi@example.com','+96812345679','789 Salalah St, Salalah, Oman','2023-10-05',NULL),('CUST050','Nadia','Petrova','Russian','nadiapetrova@example.com','+791234567892','234 Saint Petersburg St, Saint Petersburg, Russia','2023-10-08',NULL),('CUST051','Michael','Brown','American','michaelbrown@example.com','+12345678901','567 Los Angeles St, Los Angeles, USA','2023-10-10',NULL),('CUST052','Anna','Kovács','Hungarian','annakovacs@example.com','+3612345678','890 Budapest St, Budapest, Hungary','2023-10-12',NULL),('CUST053','Luca','Russo','Italian','lucarusso@example.com','+391234567892','123 Milan St, Milan, Italy','2023-10-15',NULL),('CUST054','Chen','Wei','Chinese','chenwei@example.com','+8612345678904','456 Hong Kong Rd, Hong Kong, China','2023-10-18',NULL),('CUST055','Maria','Gonzalez','Spanish','mariagonzalez@example.com','+34612345679','789 Barcelona St, Barcelona, Spain','2023-10-21',NULL),('CUST056','Satoshi','Suzuki','Japanese','satoshisuzuki@example.com','+819012345670','123 Nagoya St, Nagoya, Japan','2023-10-28',NULL),('CUST057','Anusha','Chopra','Indian','anushachopra@example.com','+917654321097','234 Mumbai Rd, Mumbai, India','2023-11-07',NULL),('CUST058','Emil','Andersson','Swedish','emilandersson@example.com','+461234567891','567 Gothenburg St, Gothenburg, Sweden','2023-11-10',NULL),('CUST059','Giulia','Ricci','Italian','giuliaricci@example.com','+391234567893','890 Florence St, Florence, Italy','2023-11-12',NULL),('CUST060','James','Brown','British','jamesbrown@example.com','+441234567893','123 Birmingham St, Birmingham, UK','2023-11-15',NULL),('CUST061','Olga','Ivanova','Russian','olgaivanova@example.com','+791234567893','456 Novosibirsk St, Novosibirsk, Russia','2023-11-18',NULL),('CUST062','Lucas','Ferreira','Brazilian','lucasferreira@example.com','+5511987654322','789 Sao Paulo St, Sao Paulo, Brazil','2023-11-20',NULL),('CUST063','Aisha','Al-Mansoor','Saudi Arabian','aishaalmansoor@example.com','+966123456790','234 Jeddah St, Jeddah, Saudi Arabia','2023-11-25',NULL),('CUST064','Ahmed','Mahmoud','Egyptian','ahmedmahmoud@example.com','+201234567892','567 Luxor St, Luxor, Egypt','2023-11-29',NULL),('CUST065','Rohan','Fernando','Sri Lankan','rohanfernando@example.com','+94776456789','890 Kegalle Rd, Kegalle, Sri Lanka','2023-12-05',NULL),('CUST066','Hannah','Smith','American','hannahsmith@example.com','+12345678902','123 Chicago St, Chicago, USA','2023-12-10',NULL),('CUST067','Li','Wei','Chinese','liwei@example.com','+8612345678905','456 Shanghai Rd, Shanghai, China','2023-12-12',NULL),('CUST068','Antonio','Gonzalez','Spanish','antoniogonzalez@example.com','+34612345680','789 Valencia St, Valencia, Spain','2023-12-18',NULL),('CUST069','Takumi','Takahashi','Japanese','takumitakahashi@example.com','+819012345671','234 Fukuoka St, Fukuoka, Japan','2023-12-23',NULL),('CUST070','Kavya','Sharma','Indian','kavyasharma@example.com','+917654321096','567 Chennai Rd, Chennai, India','2023-12-25',NULL),('CUST071','André','Silva','Portuguese','andresilva@example.com','+351123456790','890 Porto St, Porto, Portugal','2023-12-27',NULL),('CUST072','Marta','Novak','Czech','martanovak@example.com','+420123456788','123 Brno St, Brno, Czech Republic','2023-12-28',NULL),('CUST073','Aryan','Singh','Indian','aryansingh@example.com','+918765432108','456 Kolkata Rd, Kolkata, India','2024-01-05',NULL),('CUST074','Irina','Popova','Russian','irinapopova@example.com','+791234567894','789 Kazan St, Kazan, Russia','2024-01-10',NULL),('CUST075','John','Johnson','Canadian','johnjohnson@example.com','+141612345679','234 Vancouver St, Vancouver, Canada','2024-01-15',NULL),('CUST076','Ali','Abdullah','Emirati','aliabdullah@example.com','+971234567891','567 Abu Dhabi St, Abu Dhabi, UAE','2024-01-20',NULL),('CUST077','Elena','Petrova','Russian','elenapetrova@example.com','+791234567895','890 Ufa St, Ufa, Russia','2024-01-25',NULL),('CUST078','Thomas','Smith','American','thomassmith@example.com','+12345678903','123 New York St, New York, USA','2024-01-28',NULL),('CUST079','Eva','Kovács','Hungarian','evakovacs@example.com','+3612345679','456 Szeged St, Szeged, Hungary','2024-01-29',NULL),('CUST080','Marco','Rossi','Italian','marcorossi@example.com','+391234567894','789 Turin St, Turin, Italy','2024-01-31',NULL),('CUST081','Emily','Brown','British','emilybrown@example.com','+441234567894','123 Liverpool St, Liverpool, UK','2024-02-15',NULL),('CUST082','Anna','Ivanova','Russian','annaivanova@example.com','+791234567896','234 Ekaterinburg St, Ekaterinburg, Russia','2024-02-20',NULL),('CUST083','Luca','Bianchi','Italian','lucabianchi@example.com','+391234567895','567 Naples St, Naples, Italy','2024-02-25',NULL),('CUST084','Chen','Xu','Chinese','chenxu@example.com','+8612345678906','890 Shenzhen Rd, Shenzhen, China','2024-02-10',NULL),('CUST085','Marta','Gomez','Spanish','martagomez@example.com','+34612345681','123 Seville St, Seville, Spain','2024-02-05',NULL),('CUST086','Yusuke','Sato','Japanese','yusukesato@example.com','+819012345672','456 Sapporo St, Sapporo, Japan','2024-02-20',NULL),('CUST087','Sara','Müller','German','saramueller@example.com','+491234567893','789 Stuttgart St, Stuttgart, Germany','2024-02-15',NULL),('CUST088','Neha','Sharma','Indian','nehasharma@example.com','+917654321095','234 Hyderabad Rd, Hyderabad, India','2024-02-20',NULL),('CUST089','Rasmus','Andersen','Danish','rasmusandersen@example.com','+45123456789','567 Aarhus St, Aarhus, Denmark','2024-03-05',NULL),('CUST090','Jorge','Silva','Portuguese','jorgesilva@example.com','+351123456791','890 Braga St, Braga, Portugal','2024-03-08',NULL),('CUST091','Alicia','Lopez','Mexican','alicialopez@example.com','+521234567893','123 Cancun St, Cancun, Mexico','2024-03-11',NULL),('CUST092','Muhammad','Khan','Pakistani','muhammadkhan@example.com','+923456789013','456 Islamabad Rd, Islamabad, Pakistan','2024-03-14',NULL),('CUST093','Anna','Larsson','Swedish','annalarsson@example.com','+461234567892','789 Malmo St, Malmo, Sweden','2024-03-15',NULL),('CUST094','Aya','Yamamoto','Japanese','ayayamamoto@example.com','+819012345673','123 Hiroshima St, Hiroshima, Japan','2024-03-20',NULL),('CUST095','Sasha','Ivanov','Russian','sashaivanov@example.com','+791234567897','456 Rostov-on-Don St, Rostov-on-Don, Russia','2024-03-22',NULL),('CUST096','David','Jones','American','davidjones@example.com','+12345678904','789 Houston St, Houston, USA','2024-03-25',NULL),('CUST097','Elena','Kovaleva','Russian','elenakovaleva@example.com','+791234567898','234 Vladivostok St, Vladivostok, Russia','2024-03-28',NULL),('CUST098','Antonio','Gomez','Spanish','antoniogomez@example.com','+34612345682','567 Granada St, Granada, Spain','2024-03-29',NULL),('CUST099','Chamath','Dilshan','Sri lanka','Dilshancolonne123@gmail.com','0775616104','kaluthara','2024-05-12',0);
/*!40000 ALTER TABLE `customers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee` (
  `EmployeeID` varchar(50) NOT NULL,
  `FirstName` varchar(255) DEFAULT NULL,
  `LastName` varchar(255) DEFAULT NULL,
  `Position` varchar(255) DEFAULT NULL,
  `Email` varchar(255) DEFAULT NULL,
  `Phone` varchar(255) DEFAULT NULL,
  `HireDate` date DEFAULT NULL,
  `Salary` decimal(10,2) DEFAULT NULL,
  `Department` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`EmployeeID`),
  UNIQUE KEY `Email` (`Email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES ('EMP001','Kamal','Silva','Manager','kamalsilva@example.com','771234567','2022-05-15',60000.00,'Management'),('EMP002','Nimal','Fernando','Assistant Manager','nimalfernando@example.com','772345678','2022-05-20',50000.00,'Management'),('EMP003','Sunil','Perera','Front Desk Officer','sunilperera@example.com','773456789','2022-06-01',40000.00,'Front Office'),('EMP004','Anoma','de Silva','Front Desk Officer','anomadesilva@example.com','774567890','2022-06-05',40000.00,'Front Office'),('EMP005','Chaminda','Gunawardena','Service Supervisor','chamindagunawardena@example.com','775678901','2022-06-10',35050.00,'Services'),('EMP006','Saman','Perera','Room Boy','samanperera@example.com','776789012','2022-06-15',30300.00,'Services'),('EMP007','Amandi','Silva','Receptionist','amandisilva@example.com','775616105','2022-08-20',30640.00,'Administration'),('EMP008','Priyantha','Jayawardena','Chef','priyanthajayawardena@example.com','778901234','2022-07-01',45000.00,'Food and Beverage'),('EMP009','Malith','Fernando','Sous Chef','malithfernando@example.com','779012345','2022-07-05',40000.00,'Food and Beverage'),('EMP010','Tharindu','Silva','Waiter','tharindusilva@example.com','770123456','2022-07-10',25000.00,'Food and Beverage'),('EMP011','Dilshan','Perera','Accountant','dilshanperera@example.com','771234567','2022-07-15',55000.00,'Finance'),('EMP012','Samantha','Fernando','Assistant Accountant','samanthafernando@example.com','772345678','2022-07-20',45000.00,'Finance'),('EMP013','Nayana','de Silva','HR Manager','nayanadesilva@example.com','773456789','2022-08-01',60000.00,'Human Resources'),('EMP014','Ravindu','Rathnayake','IT Manager','ravindurathnayake@example.com','775678901','2022-08-10',60000.00,'Information Technology'),('EMP015','Anjana','Gunawardena','HR Assistant','anjanagunawardena@example.com','774567890','2023-08-05',40000.00,'Human Resources'),('EMP016','Thilina','Jayawardena','IT Assistant','thilinajayawardena@example.com','776789012','2023-08-15',45000.00,'Information Technology');
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee_service_info`
--

DROP TABLE IF EXISTS `employee_service_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee_service_info` (
  `EmployeeID` varchar(50) NOT NULL,
  `ServiceID` varchar(50) NOT NULL,
  `HoursAllocated` decimal(5,2) DEFAULT NULL,
  `ServiceDate` date DEFAULT NULL,
  `TotalPayment` double DEFAULT NULL,
  PRIMARY KEY (`EmployeeID`,`ServiceID`),
  KEY `ServiceID` (`ServiceID`),
  CONSTRAINT `employee_service_info_ibfk_1` FOREIGN KEY (`EmployeeID`) REFERENCES `employee` (`EmployeeID`),
  CONSTRAINT `employee_service_info_ibfk_2` FOREIGN KEY (`ServiceID`) REFERENCES `service` (`ServiceID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee_service_info`
--

LOCK TABLES `employee_service_info` WRITE;
/*!40000 ALTER TABLE `employee_service_info` DISABLE KEYS */;
INSERT INTO `employee_service_info` VALUES ('EMP006','SER001',5.00,'2024-05-16',100),('EMP006','SER002',5.00,'2024-05-16',200),('EMP007','SER001',5.00,'2024-05-16',100),('EMP007','SER002',5.00,'2024-05-16',200),('EMP007','SER003',5.00,'2024-05-15',300),('EMP007','SER005',5.00,'2024-05-15',260);
/*!40000 ALTER TABLE `employee_service_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `event`
--

DROP TABLE IF EXISTS `event`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `event` (
  `EventID` varchar(50) NOT NULL,
  `ReservationID` varchar(50) DEFAULT NULL,
  `Name` varchar(255) DEFAULT NULL,
  `Date` date DEFAULT NULL,
  `NumberOfAttendees` int DEFAULT NULL,
  `CustomerPhone` int DEFAULT NULL,
  `Hole Number` int DEFAULT NULL,
  PRIMARY KEY (`EventID`),
  KEY `ReservationID` (`ReservationID`),
  CONSTRAINT `event_ibfk_1` FOREIGN KEY (`ReservationID`) REFERENCES `reservation` (`ReservationID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `event`
--

LOCK TABLES `event` WRITE;
/*!40000 ALTER TABLE `event` DISABLE KEYS */;
INSERT INTO `event` VALUES ('EV001','RES005','Elite Island Wedding','2024-05-16',250,775616104,2),('EV002','RES008','Charming Garden Wedding','2024-05-16',250,775616104,2),('EV003','RES009','Lavish Castle Wedding','2024-05-16',250,775616104,2),('EV004','RES010','Boutique Hotel Wedding','2024-05-17',200,775616104,3),('EV005','RES013','Winter Wonderland Wedding','2024-05-16',500,775616104,2);
/*!40000 ALTER TABLE `event` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inventory`
--

DROP TABLE IF EXISTS `inventory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `inventory` (
  `InventoryID` varchar(50) NOT NULL,
  `MealID` varchar(50) DEFAULT NULL,
  `Name` varchar(255) DEFAULT NULL,
  `Quantity` int DEFAULT NULL,
  `MinimumStockLevel` int DEFAULT NULL,
  `Ingredients` text,
  PRIMARY KEY (`InventoryID`),
  KEY `MealID` (`MealID`),
  CONSTRAINT `inventory_ibfk_1` FOREIGN KEY (`MealID`) REFERENCES `meal` (`MealID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inventory`
--

LOCK TABLES `inventory` WRITE;
/*!40000 ALTER TABLE `inventory` DISABLE KEYS */;
INSERT INTO `inventory` VALUES ('INV001','MEAL001','Scrambled Eggs',145,50,'Eggs, Salt, Pepper, Butter'),('INV002','MEAL002','Toast',230,75,'Bread, Butter'),('INV003','MEAL003','Cereal with Milk',300,100,'Cereal, Milk, Sugar'),('INV004','MEAL004','Grilled Cheese Sandwich',250,60,'Bread, Cheese, Butter'),('INV005','MEAL005','Roast Chicken',95,40,'Chicken, Salt, Pepper, Olive Oil, Herbs'),('INV006','MEAL006','Beef Stir-Fry',100,50,'Beef, Soy Sauce, Garlic, Ginger, Vegetables'),('INV007','MEAL007','Pork Chops',140,45,'Pork, Salt, Pepper, Olive Oil'),('INV008','MEAL008','Rice Pilaf',250,125,'Rice, Onion, Chicken Broth, Olive Oil, Herbs'),('INV009','MEAL009','Spaghetti Carbonara',150,75,'Pasta, Bacon, Eggs, Parmesan Cheese, Black Pepper'),('INV010','MEAL010','Caprese Salad',180,90,'Tomatoes, Mozzarella Cheese, Basil, Olive Oil, Balsamic Vinegar'),('INV011','MEAL011','Mashed Potatoes',200,100,'Potatoes, Butter, Milk, Salt, Pepper'),('INV012','MEAL012','French Onion Soup',150,75,'Onions, Beef Broth, Gruyere Cheese, Baguette, Butter'),('INV013','MEAL013','Carrot Cake',120,60,'Carrots, Flour, Sugar, Eggs, Oil'),('INV014','MEAL014','Caesar Salad',100,50,'Lettuce, Croutons, Parmesan Cheese, Caesar Dressing'),('INV015','MEAL015','Apple Pie',180,90,'Apples, Flour, Sugar, Butter, Cinnamon'),('INV016','MEAL016','Banana Bread',150,75,'Bananas, Flour, Sugar, Eggs, Butter'),('INV017','MEAL017','Orange Chicken',120,60,'Chicken, Orange Juice, Soy Sauce, Garlic, Ginger'),('INV018','MEAL018','Grape Jelly',100,50,'Grapes, Sugar, Pectin'),('INV019','MEAL019','Strawberry Smoothie',120,60,'Strawberries, Yogurt, Milk, Honey'),('INV020','MEAL020','Blueberry Pancakes',100,50,'Flour, Blueberries, Milk, Eggs, Butter'),('INV021','MEAL021','Yogurt Parfait',200,100,'Yogurt, Granola, Berries, Honey'),('INV022','MEAL022','Butter Chicken',150,75,'Chicken, Butter, Cream, Tomato Sauce, Spices'),('INV023','MEAL023','Olive Oil Pasta',120,60,'Pasta, Olive Oil, Garlic, Parmesan Cheese, Parsley'),('INV024','MEAL024','Vegetable Stir-Fry',150,75,'Assorted Vegetables, Soy Sauce, Garlic, Ginger, Sesame Oil'),('INV025','MEAL025','Sugar Cookies',200,100,'Flour, Sugar, Butter, Eggs, Vanilla Extract'),('INV026','MEAL026','Salted Caramel Brownies',250,125,'Chocolate, Butter, Sugar, Eggs, Salt'),('INV027','MEAL027','Pepper Steak',200,100,'Beef, Bell Peppers, Onion, Soy Sauce, Black Pepper'),('INV028','MEAL028','Garlic Shrimp',150,75,'Shrimp, Garlic, Butter, Lemon Juice, Parsley'),('INV029','MEAL029','Ginger Beef',120,60,'Beef, Ginger, Soy Sauce, Garlic, Green Onions'),('INV030','MEAL030','Cumin-Spiced Chicken',100,50,'Chicken, Cumin, Paprika, Garlic Powder, Olive Oil'),('INV031','MEAL031','Coriander Rice',120,60,'Rice, Coriander, Lime, Salt, Onion'),('INV032','MEAL032','Paprika Chicken',100,50,'Chicken, Paprika, Garlic Powder, Onion Powder, Salt'),('INV033','MEAL033','Cinnamon Roll',150,75,'Flour, Sugar, Cinnamon, Butter, Cream Cheese'),('INV034','MEAL034','Vanilla Cupcakes',120,60,'Flour, Sugar, Butter, Eggs, Vanilla Extract'),('INV035','MEAL035','Lemon Bars',200,100,'Flour, Sugar, Lemon Juice, Eggs, Butter'),('INV036','MEAL036','Lime Margarita',180,90,'Tequila, Lime Juice, Triple Sec, Salt, Ice'),('INV037','MEAL037','Vinegar Coleslaw',150,75,'Cabbage, Carrots, Mayonnaise, Vinegar, Sugar'),('INV038','MEAL038','Soy Glazed Salmon',120,60,'Salmon, Soy Sauce, Honey, Garlic, Ginger'),('INV039','MEAL039','Fish Tacos',100,50,'Fish, Tortillas, Cabbage, Lime, Cilantro'),('INV040','MEAL040','Oysters Rockefeller',120,60,'Oysters, Spinach, Bacon, Bread Crumbs, Parmesan Cheese'),('INV041','MEAL041','Hot Wings',150,75,'Chicken Wings, Hot Sauce, Butter, Vinegar, Garlic Powder'),('INV042','MEAL042','Ketchup Meatloaf',180,90,'Ground Beef, Ketchup, Bread Crumbs, Onion, Egg'),('INV043','MEAL043','Mayonnaise Potato Salad',200,100,'Potatoes, Mayonnaise, Mustard, Celery, Eggs'),('INV044','MEAL044','Honey Mustard Chicken',150,75,'Chicken, Honey, Mustard, Garlic, Lemon Juice'),('INV045','MEAL045','Cilantro Lime Rice',120,60,'Rice, Lime Juice, Cilantro, Salt, Olive Oil'),('INV046','MEAL046','Sesame Noodles',100,50,'Noodles, Soy Sauce, Sesame Oil, Green Onions, Garlic'),('INV047','MEAL047','Maple Glazed Carrots',115,60,'Carrots, Maple Syrup, Butter, Salt, Pepper'),('INV048','MEAL048','Honey Garlic Pork Chops',100,50,'Pork Chops, Honey, Garlic, Soy Sauce, Olive Oil'),('INV049','MEAL049','Balsamic Glazed Brussels Sprouts',150,75,'Brussels Sprouts, Balsamic Vinegar, Olive Oil, Garlic, Salt'),('INV050','MEAL050','Pesto Pasta',200,100,'Pasta, Basil, Pine Nuts, Parmesan Cheese, Garlic'),('INV051','MEAL051','Sweet and Sour Chicken',180,90,'Chicken, Pineapple, Bell Peppers, Onion, Sweet and Sour Sauce'),('INV052','MEAL052','Honey Soy Salmon',150,75,'Salmon, Honey, Soy Sauce, Garlic, Ginger'),('INV053','MEAL053','Garlic Parmesan Wings',120,60,'Chicken Wings, Garlic, Parmesan Cheese, Butter, Parsley'),('INV054','MEAL054','Creamy Mushroom Risotto',100,50,'Arborio Rice, Mushrooms, White Wine, Parmesan Cheese, Onion'),('INV055','MEAL055','Beef Tacos',150,75,'Ground Beef, Tortillas, Lettuce, Tomato, Cheese'),('INV056','MEAL056','Lemon Garlic Shrimp',120,60,'Shrimp, Lemon Juice, Garlic, Butter, Parsley'),('INV057','MEAL057','Teriyaki Chicken',100,50,'Chicken, Teriyaki Sauce, Garlic, Ginger, Green Onions'),('INV058','MEAL058','Chocolate Cake',200,100,'Flour, Sugar, Cocoa Powder, Eggs, Butter'),('INV059','MEAL059','Cornstarch',120,60,'Corn'),('INV060','MEAL060','Oats',100,50,'Oats'),('INV061','MEAL061','Chocolate',150,75,'Cocoa Beans, Sugar, Milk'),('INV062','MEAL062','Marshmallows',118,60,'Sugar, Gelatin, Water'),('INV063','MEAL063','Peanuts',95,50,'Peanuts'),('INV064','MEAL064','Almonds',150,75,'Almonds'),('INV065','MEAL065','Walnuts',120,60,'Walnuts'),('INV066','MEAL066','Pistachios',100,50,'Pistachios'),('INV067','MEAL067','Cashews',150,75,'Cashews'),('INV068','MEAL068','Macadamia Nuts',120,60,'Macadamia Nuts'),('INV069','MEAL069','Hazelnuts',100,50,'Hazelnuts'),('INV070','MEAL070','Sunflower Seeds',150,75,'Sunflower Seeds'),('INV071','MEAL071','Pumpkin Seeds',120,60,'Pumpkin Seeds'),('INV072','MEAL072','Popcorn',100,50,'Popcorn Kernels'),('INV073','MEAL073','Raisins',150,75,'Grapes'),('INV074','MEAL074','Dates',120,60,'Dates'),('INV075','MEAL075','Figs',100,50,'Figs'),('INV076','MEAL076','Coconut',150,75,'Coconut'),('INV077','MEAL077','Chia Seeds',120,60,'Chia Seeds'),('INV078','MEAL078','Quinoa',100,50,'Quinoa'),('INV079','MEAL079','Flax Seeds',150,75,'Flax Seeds'),('INV080','MEAL080','Hemp Seeds',120,60,'Hemp Seeds'),('INV081','MEAL081','Lemon Garlic Chicken',120,60,'Chicken, Lemon Juice, Garlic, Olive Oil, Thyme'),('INV082','MEAL082','Beef Stroganoff',150,75,'Beef, Mushrooms, Onion, Sour Cream, Beef Broth'),('INV083','MEAL083','Shrimp Scampi',120,60,'Shrimp, Garlic, Butter, White Wine, Lemon Juice'),('INV084','MEAL084','Eggplant Parmesan',100,50,'Eggplant, Tomato Sauce, Mozzarella Cheese, Parmesan Cheese, Bread Crumbs'),('INV085','MEAL085','Spinach and Feta Stuffed Chicken',150,75,'Chicken, Spinach, Feta Cheese, Garlic, Olive Oil'),('INV086','MEAL086','Vegetable Lasagna',120,60,'Lasagna Noodles, Tomato Sauce, Ricotta Cheese, Mozzarella Cheese, Assorted Vegetables'),('INV087','MEAL087','Beef Wellington',100,50,'Beef Tenderloin, Puff Pastry, Mushroom Duxelles, Prosciutto, Egg Wash'),('INV088','MEAL088','Miso Soup',150,75,'Dashi Stock, Miso Paste, Tofu, Wakame Seaweed, Green Onions'),('INV089','MEAL089','Beef and Broccoli Stir-Fry',120,60,'Beef, Broccoli, Soy Sauce, Garlic, Ginger'),('INV090','MEAL090','Cajun Shrimp Pasta',100,50,'Shrimp, Pasta, Heavy Cream, Cajun Seasoning, Parmesan Cheese'),('INV091','MEAL091','Beef Enchiladas',145,75,'Beef, Tortillas, Enchilada Sauce, Cheddar Cheese, Onion'),('INV092','MEAL092','Chicken Cordon Bleu',120,60,'Chicken, Ham, Swiss Cheese, Bread Crumbs, Dijon Mustard'),('INV093','MEAL093','Vegetable Curry',100,50,'Assorted Vegetables, Coconut Milk, Curry Paste, Onion, Garlic'),('INV094','MEAL094','Baked Ziti',150,75,'Ziti Pasta, Tomato Sauce, Ricotta Cheese, Mozzarella Cheese, Parmesan Cheese'),('INV095','MEAL095','Chicken Quesadillas',120,60,'Chicken, Tortillas, Cheddar Cheese, Onion, Bell Pepper'),('INV096','MEAL096','Teriyaki Salmon',100,50,'Salmon, Teriyaki Sauce, Soy Sauce, Garlic, Ginger'),('INV097','MEAL097','Mushroom Risotto',150,75,'Arborio Rice, Mushrooms, White Wine, Parmesan Cheese, Onion'),('INV098','MEAL098','Vegetable Paella',120,60,'Rice, Assorted Vegetables, Saffron, Vegetable Broth, Olive Oil'),('INV099','MEAL099','Chicken Tikka Masala',100,50,'Chicken, Tomato Sauce, Yogurt, Cream, Garam Masala'),('INV100','MEAL100','Vegetarian Chili',150,75,'Beans, Tomatoes, Bell Pepper, Onion, Chili Powder');
/*!40000 ALTER TABLE `inventory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inventory_supplier_info`
--

DROP TABLE IF EXISTS `inventory_supplier_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `inventory_supplier_info` (
  `InventoryID` varchar(50) NOT NULL,
  `SupplierID` varchar(50) NOT NULL,
  `SupplyQuantity` int DEFAULT NULL,
  `DeliveryDate` date DEFAULT NULL,
  `PricePerUnit` decimal(10,2) DEFAULT NULL,
  `TotalPrice` varchar(100) DEFAULT NULL,
  KEY `InventoryID` (`InventoryID`),
  KEY `SupplierID` (`SupplierID`),
  CONSTRAINT `inventory_supplier_info_ibfk_1` FOREIGN KEY (`InventoryID`) REFERENCES `inventory` (`InventoryID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `inventory_supplier_info_ibfk_2` FOREIGN KEY (`SupplierID`) REFERENCES `supplier` (`SupplierID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inventory_supplier_info`
--

LOCK TABLES `inventory_supplier_info` WRITE;
/*!40000 ALTER TABLE `inventory_supplier_info` DISABLE KEYS */;
INSERT INTO `inventory_supplier_info` VALUES ('INV001','SUP001',5,'2024-05-12',500.00,'2500'),('INV002','SUP001',5,'2024-05-12',500.00,'2500'),('INV003','SUP001',5,'2024-05-12',500.00,'2500'),('INV001','SUP001',20,'2024-05-12',1000.00,'20000'),('INV002','SUP001',5,'2024-05-12',1000.00,'5000'),('INV003','SUP001',5,'2024-05-12',1000.00,'5000'),('INV001','SUP001',20,'2024-05-12',1000.00,'20000'),('INV002','SUP001',5,'2024-05-12',1000.00,'5000'),('INV003','SUP001',5,'2024-05-12',1000.00,'5000'),('INV003','SUP001',25,'2024-05-12',2500.00,'62500'),('INV003','SUP001',25,'2024-05-13',26.00,'650'),('INV003','SUP001',25,'2024-05-13',26.00,'650'),('INV001','SUP001',5,'2024-05-16',50.00,'250'),('INV002','SUP001',5,'2024-05-16',50.00,'250');
/*!40000 ALTER TABLE `inventory_supplier_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `meal`
--

DROP TABLE IF EXISTS `meal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `meal` (
  `MealID` varchar(50) NOT NULL,
  `RoomID` varchar(50) DEFAULT NULL,
  `Name` varchar(255) DEFAULT NULL,
  `Type` varchar(255) DEFAULT NULL,
  `Price` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`MealID`),
  KEY `RoomID` (`RoomID`),
  CONSTRAINT `meal_ibfk_1` FOREIGN KEY (`RoomID`) REFERENCES `room` (`RoomID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `meal`
--

LOCK TABLES `meal` WRITE;
/*!40000 ALTER TABLE `meal` DISABLE KEYS */;
INSERT INTO `meal` VALUES ('MEAL001','RM030','American Breakfast','Breakfast',500.00),('MEAL002','RM029','Italian Seafood Pasta','Snack',550.00),('MEAL003','RM028','Moroccan Tagine','Dinner',600.00),('MEAL004','RM027','Thai Green Curry','Dinner',650.00),('MEAL005','RM026','Vegan Tex-Mex Bowl','Lunch',700.00),('MEAL006','RM025','Szechuan Tofu Stir-Fry','Breakfast',750.00),('MEAL007','RM024','Argentine Asado','Snack',800.00),('MEAL008','RM023','Turkish Kebab','Dinner',850.00),('MEAL009','RM022','Greek Moussaka','Dinner',900.00),('MEAL010','RM021','French Ratatouille','Lunch',950.00),('MEAL011','RM020','Japanese Ramen','Breakfast',1000.00),('MEAL012','RM019','Korean Bibimbap','Snack',1050.00),('MEAL013','RM018','Indian Butter Chicken','Dinner',1100.00),('MEAL014','RM017','Ethiopian Doro Wat','Dinner',1150.00),('MEAL015','RM016','Spanish Paella','Lunch',1200.00),('MEAL016','RM015','German Schnitzel','Breakfast',1250.00),('MEAL017','RM014','Lebanese Falafel','Snack',1300.00),('MEAL018','RM013','Brazilian Feijoada','Dinner',1350.00),('MEAL019','RM012','Vietnamese Pho','Dinner',1400.00),('MEAL020','RM011','Belgian Waffles','Lunch',1450.00),('MEAL021','RM018','English Fish and Chips','Dinner',1100.00),('MEAL022','RM017','Jamaican Jerk Chicken','Dinner',1150.00),('MEAL023','RM016','Russian Beef Stroganoff','Lunch',1200.00),('MEAL024','RM015','Peruvian Ceviche','Breakfast',1250.00),('MEAL025','RM014','Australian Meat Pie','Snack',1300.00),('MEAL026','RM013','Canadian Poutine','Dinner',1350.00),('MEAL027','RM012','Swedish Meatballs','Dinner',1400.00),('MEAL028','RM011','Dutch Stamppot','Lunch',1450.00),('MEAL029','RM010','Cuban Sandwich','Breakfast',1500.00),('MEAL030','RM009','Mexican Enchiladas','Snack',1550.00),('MEAL031','RM008','Egyptian Koshari','Dinner',1600.00),('MEAL032','RM007','Hungarian Goulash','Dinner',1650.00),('MEAL033','RM006','Polish Pierogi','Lunch',1700.00),('MEAL034','RM005','Filipino Adobo','Breakfast',1750.00),('MEAL035','RM004','Chinese Dim Sum','Snack',1800.00),('MEAL036','RM003','Indonesian Nasi Goreng','Dinner',1850.00),('MEAL037','RM002','Malaysian Laksa','Dinner',1900.00),('MEAL038','RM001','Nepalese Momos','Lunch',1950.00),('MEAL039','RM030','Mongolian Beef','Breakfast',2000.00),('MEAL040','RM029','Israeli Shakshuka','Snack',2050.00),('MEAL041','RM028','Portuguese Bacalhau','Dinner',2100.00),('MEAL042','RM027','New Zealand Lamb Chops','Dinner',2150.00),('MEAL043','RM026','South African Bunny Chow','Lunch',2200.00),('MEAL044','RM025','Sicilian Pizza','Breakfast',2250.00),('MEAL045','RM024','Mediterranean Quinoa Salad','Snack',2300.00),('MEAL046','RM023','Texan BBQ Ribs','Dinner',2350.00),('MEAL047','RM022','Alaskan Salmon Bake','Dinner',2400.00),('MEAL048','RM021','Cajun Seafood Gumbo','Lunch',2450.00),('MEAL049','RM020','Vermont Maple Glazed Chicken','Breakfast',2500.00),('MEAL050','RM019','Hawaiian Poke','Snack',2550.00),('MEAL051','RM018','Tuscan Bean Soup','Dinner',2600.00),('MEAL052','RM017','Bavarian Pretzel','Dinner',2650.00),('MEAL053','RM016','Moroccan Couscous','Lunch',2700.00),('MEAL054','RM015','Peruvian Lomo Saltado','Breakfast',2750.00),('MEAL055','RM014','Korean Kimchi Jjigae','Snack',2800.00),('MEAL056','RM013','Irish Beef Stew','Dinner',2850.00),('MEAL057','RM012','Balinese Satay','Dinner',2900.00),('MEAL058','RM011','Icelandic Fish Stew','Lunch',2950.00),('MEAL059','RM010','Tunisian Brik','Breakfast',3000.00),('MEAL060','RM009','Laotian Larb','Snack',3050.00),('MEAL061','RM008','Oaxacan Mole','Dinner',3100.00),('MEAL062','RM007','Afghan Kabuli Pulao','Dinner',3150.00),('MEAL063','RM006','Zimbabwean Sadza','Lunch',3200.00),('MEAL064','RM005','Venetian Risotto','Breakfast',3250.00),('MEAL065','RM004','Burmese Khao Suey','Snack',3300.00),('MEAL066','RM003','Danish Smørrebrød','Dinner',3350.00),('MEAL067','RM002','Chilean Empanadas','Dinner',3400.00),('MEAL068','RM001','Fijian Kokoda','Lunch',3450.00),('MEAL069','RM030','Greek Gyros','Breakfast',3500.00),('MEAL070','RM029','Salvadoran Pupusas','Snack',3550.00),('MEAL071','RM028','French Coq Au Vin','Dinner',3600.00),('MEAL072','RM027','Austrian Apfelstrudel','Dinner',3650.00),('MEAL073','RM026','Colombian Arepas','Lunch',3700.00),('MEAL074','RM025','Swiss Fondue','Breakfast',3750.00),('MEAL075','RM024','Punjabi Tandoori Chicken','Snack',3800.00),('MEAL076','RM023','Andalusian Gazpacho','Dinner',3850.00),('MEAL077','RM022','Belarusian Draniki','Dinner',3900.00),('MEAL078','RM021','Welsh Rarebit','Lunch',3950.00),('MEAL079','RM020','Ecuadorian Hornado','Breakfast',4000.00),('MEAL080','RM019','Finnish Karelian Pies','Snack',4050.00),('MEAL081','RM018','Slovak Bryndzové Halu?ky','Dinner',4100.00),('MEAL082','RM017','Turkish Delight','Dinner',4150.00),('MEAL083','RM016','Senegalese Thieboudienne','Lunch',4200.00),('MEAL084','RM015','Dutch Poffertjes','Breakfast',4250.00),('MEAL085','RM014','Bolivian Salteñas','Snack',4300.00),('MEAL086','RM013','Cypriot Halloumi','Dinner',4350.00),('MEAL087','RM012','Maltese Rabbit Stew','Dinner',4400.00),('MEAL088','RM011','Jordanian Mansaf','Lunch',4450.00),('MEAL089','RM010','Bhutanese Ema Datshi','Breakfast',4500.00),('MEAL090','RM009','Qatari Machboos','Snack',4550.00),('MEAL091','RM008','Algerian Chakchouka','Dinner',4600.00),('MEAL092','RM007','Slovak Bryndzové Halu?ky','Dinner',4650.00),('MEAL093','RM006','Norwegian Lutefisk','Lunch',4700.00),('MEAL094','RM005','Panamanian Sancocho','Breakfast',4750.00),('MEAL095','RM004','Uzbek Plov','Snack',4800.00),('MEAL096','RM003','Korean Hotteok','Dinner',4850.00),('MEAL097','RM002','Tanzanian Ugali','Dinner',4900.00),('MEAL098','RM001','Kazakh Beshbarmak','Lunch',4950.00),('MEAL099','RM030','Omani Shuwa','Breakfast',5000.00),('MEAL100','RM029','Lao Khao Poon','Snack',5000.00);
/*!40000 ALTER TABLE `meal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `package`
--

DROP TABLE IF EXISTS `package`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `package` (
  `PackageID` varchar(50) NOT NULL,
  `Name` varchar(255) DEFAULT NULL,
  `Price` int DEFAULT NULL,
  `ServicesIncluded` text,
  PRIMARY KEY (`PackageID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `package`
--

LOCK TABLES `package` WRITE;
/*!40000 ALTER TABLE `package` DISABLE KEYS */;
INSERT INTO `package` VALUES ('PKG001','Relaxation Package',150,'Massage, Spa Treatment'),('PKG002','City Tour Package',100,'Guided Tours, Transportation'),('PKG003','Adventure Package',200,'Hiking, Zip-lining, Adventure Activities'),('PKG004','Cultural Experience Package',180,'Cultural Shows, Traditional Meals'),('PKG005','Luxury Spa Package',250,'Massages, Facials, Spa Treatments'),('PKG006','Beach Getaway Package',220,'Beach Access, Water Sports'),('PKG007','Shopping Package',120,'Personal Shopping Assistance, Discounts'),('PKG008','Family Fun Package',300,'Theme Park Tickets, Family Games'),('PKG009','Sightseeing Package',180,'Guided Tours, Sightseeing Activities'),('PKG010','Romantic Getaway Package',280,'Romantic Dinners, Couple Spa Treatments'),('PKG011','Adventure Trekking Package',220,'Trekking, Camping, Adventure Activities'),('PKG012','Wine Tasting Package',150,'Wine Tastings, Vineyard Tours'),('PKG013','Gourmet Dining Package',200,'Gourmet Meals, Fine Dining'),('PKG014','Historical Tour Package',160,'Historical Tours, Museum Visits'),('PKG015','Wellness Retreat Package',230,'Yoga Sessions, Meditation, Wellness Activities'),('PKG016','Cruise Adventure Package',350,'Cruise Tickets, Onboard Entertainment'),('PKG017','Culinary Tour Package',180,'Cooking Classes, Food Tours, Gastronomic Experiences'),('PKG018','Adventure Package',200,'Hiking, Zip-lining, Adventure Activities'),('PKG019','Cultural Experience Package',180,'Cultural Shows, Traditional Meals'),('PKG020','Luxury Spa Package',250,'Massages, Facials, Spa Treatments'),('PKG021','Beach Getaway Package',220,'Beach Access, Water Sports'),('PKG022','Shopping Package',120,'Personal Shopping Assistance, Discounts'),('PKG023','Family Fun Package',300,'Theme Park Tickets, Family Games'),('PKG024','Sightseeing Package',180,'Guided Tours, Sightseeing Activities'),('PKG025','Romantic Getaway Package',280,'Romantic Dinners, Couple Spa Treatments'),('PKG026','Adventure Trekking Package',220,'Trekking, Camping, Adventure Activities'),('PKG027','Wine Tasting Package',150,'Wine Tastings, Vineyard Tours'),('PKG028','Gourmet Dining Package',200,'Gourmet Meals, Fine Dining'),('PKG029','Historical Tour Package',160,'Historical Tours, Museum Visits'),('PKG030','Wellness Retreat Package',230,'Yoga Sessions, Meditation, Wellness Activities'),('PKG031','Cruise Adventure Package',350,'Cruise Tickets, Onboard Entertainment'),('PKG032','Culinary Tour Package',180,'Cooking Classes, Food Tours, Gastronomic Experiences'),('PKG033','Relaxation Package',150,'Massage, Spa Treatment'),('PKG034','City Tour Package',100,'Guided Tours, Transportation'),('PKG035','Adventure Package',200,'Hiking, Zip-lining, Adventure Activities'),('PKG036','Cultural Experience Package',180,'Cultural Shows, Traditional Meals'),('PKG037','Luxury Spa Package',250,'Massages, Facials, Spa Treatments'),('PKG038','Beach Getaway Package',220,'Beach Access, Water Sports'),('PKG039','Shopping Package',120,'Personal Shopping Assistance, Discounts'),('PKG040','Family Fun Package',300,'Theme Park Tickets, Family Games'),('PKG041','Sightseeing Package',180,'Guided Tours, Sightseeing Activities'),('PKG042','Romantic Getaway Package',280,'Romantic Dinners, Couple Spa Treatments'),('PKG043','Adventure Trekking Package',220,'Trekking, Camping, Adventure Activities'),('PKG044','Wine Tasting Package',150,'Wine Tastings, Vineyard Tours'),('PKG045','Gourmet Dining Package',200,'Gourmet Meals, Fine Dining'),('PKG046','Historical Tour Package',160,'Historical Tours, Museum Visits'),('PKG047','Wellness Retreat Package',230,'Yoga Sessions, Meditation, Wellness Activities'),('PKG048','Cruise Adventure Package',350,'Cruise Tickets, Onboard Entertainment'),('PKG049','Culinary Tour Package',180,'Cooking Classes, Food Tours, Gastronomic Experiences'),('PKG050','Relaxation Package',150,'Massage, Spa Treatment'),('PKG051','City Tour Package',100,'Guided Tours, Transportation'),('PKG052','Adventure Package',200,'Hiking, Zip-lining, Adventure Activities'),('PKG053','Cultural Experience Package',180,'Cultural Shows, Traditional Meals'),('PKG054','Luxury Spa Package',250,'Massages, Facials, Spa Treatments'),('PKG055','Beach Getaway Package',220,'Beach Access, Water Sports'),('PKG056','Shopping Package',120,'Personal Shopping Assistance, Discounts'),('PKG057','Family Fun Package',300,'Theme Park Tickets, Family Games'),('PKG058','Sightseeing Package',180,'Guided Tours, Sightseeing Activities'),('PKG059','Romantic Getaway Package',280,'Romantic Dinners, Couple Spa Treatments'),('PKG060','Adventure Trekking Package',220,'Trekking, Camping, Adventure Activities'),('PKG061','Elegant Wedding Escape',500,'Venue, Catering, Music, Decorations'),('PKG062','Romantic Beach Wedding',750,'Beach Venue, Flowers, Photographer, Catering'),('PKG063','Luxury Resort Wedding',1000,'Resort Venue, Luxury Suite, Fine Dining, Spa Treatment'),('PKG064','Sunset Wedding Package',600,'Outdoor Venue, Sunset View, Decorations, Catering'),('PKG065','Tropical Wedding Getaway',800,'Tropical Island Venue, Accommodations, Meals, Entertainment'),('PKG066','Classic Vineyard Wedding',700,'Vineyard Venue, Wine Tasting, Gourmet Meals, Photographer'),('PKG067','Elite Island Wedding',950,'Private Island, Catering, Floral Arrangements, Music Band'),('PKG068','Charming Garden Wedding',550,'Garden Venue, Decorations, Photographer, Catering'),('PKG069','Grand Ballroom Wedding',1200,'Ballroom Venue, Multi-Course Meal, Live Orchestra, Decorations'),('PKG070','Seaside Nuptials Package',650,'Seaside Venue, Seafood Catering, Decorations, Music DJ'),('PKG071','Historic Mansion Wedding',900,'Mansion Venue, Catering, Historic Tours, Decorations'),('PKG072','Winter Wonderland Wedding',800,'Indoor Snow Theme, Catering, Ice Sculptures, Winter Decor'),('PKG073','Rustic Countryside Wedding',450,'Countryside Venue, Rustic Decor, Buffet Style Meals, Barn Dance'),('PKG074','Lavish Castle Wedding',1500,'Castle Venue, Royal Decor, Gourmet Catering, Classical Music Quartet'),('PKG075','Boutique Hotel Wedding',700,'Boutique Hotel, Customized Decor, Fine Dining, Exclusive Service');
/*!40000 ALTER TABLE `package` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment`
--

DROP TABLE IF EXISTS `payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment` (
  `PaymentID` varchar(50) NOT NULL,
  `ReservationID` varchar(50) DEFAULT NULL,
  `Amount` decimal(10,2) DEFAULT NULL,
  `Date` date DEFAULT NULL,
  `Method` varchar(255) DEFAULT NULL,
  `Status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`PaymentID`),
  KEY `ReservationID` (`ReservationID`),
  CONSTRAINT `payment_ibfk_1` FOREIGN KEY (`ReservationID`) REFERENCES `reservation` (`ReservationID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment`
--

LOCK TABLES `payment` WRITE;
/*!40000 ALTER TABLE `payment` DISABLE KEYS */;
INSERT INTO `payment` VALUES ('PAY001','RES001',70400.00,'2024-05-14','Debit Card','confirmed'),('PAY002','RES002',121400.00,'2024-05-14','Credit Card','confirmed'),('PAY003','RES003',49000.00,'2024-05-14','Cash','confirmed'),('PAY004','RES004',87000.00,'2024-05-14','Cash','completed'),('PAY005','RES005',250000.00,'2024-05-16','Credit Card','confirmed'),('PAY006','RES006',142000.00,'2024-05-15','Credit Card','completed'),('PAY007','RES007',100800.00,'2024-05-15','Credit Card','completed'),('PAY008','RES008',250000.00,'2024-05-16','Debit Card','completed'),('PAY009','RES009',25000.00,'2024-05-16','Credit Card','completed'),('PAY010','RES010',250000.00,'2024-05-17','Debit Card','completed'),('PAY011','RES011',87000.00,'2024-05-16','Debit Card','completed'),('PAY012','RES012',62000.00,'2024-05-16','Debit Card','confirmed'),('PAY013','RES013',250000.00,'2024-05-16','Cash','completed');
/*!40000 ALTER TABLE `payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservation`
--

DROP TABLE IF EXISTS `reservation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reservation` (
  `ReservationID` varchar(50) NOT NULL,
  `CheckInDate` date DEFAULT NULL,
  `CheckOutDate` date DEFAULT NULL,
  `ReservationDate` date DEFAULT NULL,
  `Duration` varchar(10) DEFAULT NULL,
  `NumberofGuests` int DEFAULT NULL,
  `Status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ReservationID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservation`
--

LOCK TABLES `reservation` WRITE;
/*!40000 ALTER TABLE `reservation` DISABLE KEYS */;
INSERT INTO `reservation` VALUES ('RES001','2024-05-14','2024-05-15','2024-05-14','1 days',7,'confirmed'),('RES002','2024-05-14','2024-05-15','2024-05-14','1 days',4,'confirmed'),('RES003','2024-05-14','2024-05-15','2024-05-14','1 days',4,'confirmed'),('RES004','2024-05-14','2024-05-15','2024-05-14','1 days',5,'completed'),('RES005','2024-05-16','2024-05-16','2024-05-16','1 Day',250,'Wedding'),('RES006','2024-05-15','2024-05-16','2024-05-15','1 days',7,'completed'),('RES007','2024-05-15','2024-05-15','2024-05-15','0 days',3,'completed'),('RES008','2024-05-16','2024-05-16','2024-05-16','1 Day',250,'Wedding'),('RES009','2024-05-16','2024-05-16','2024-05-16','1 Day',250,'Wedding'),('RES010','2024-05-17','2024-05-17','2024-05-17','1 Day',200,'Wedding'),('RES011','2024-05-16','2024-05-17','2024-05-16','1 days',4,'completed'),('RES012','2024-05-16','2024-05-17','2024-05-16','1 days',3,'confirmed'),('RES013','2024-05-16','2024-05-16','2024-05-16','1 Day',500,'Wedding');
/*!40000 ALTER TABLE `reservation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservation_package_info`
--

DROP TABLE IF EXISTS `reservation_package_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reservation_package_info` (
  `ReservationID` varchar(50) NOT NULL,
  `PackageID` varchar(50) NOT NULL,
  `CustomerID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`ReservationID`,`PackageID`),
  KEY `PackageID` (`PackageID`),
  CONSTRAINT `reservation_package_info_ibfk_1` FOREIGN KEY (`ReservationID`) REFERENCES `reservation` (`ReservationID`),
  CONSTRAINT `reservation_package_info_ibfk_2` FOREIGN KEY (`PackageID`) REFERENCES `package` (`PackageID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservation_package_info`
--

LOCK TABLES `reservation_package_info` WRITE;
/*!40000 ALTER TABLE `reservation_package_info` DISABLE KEYS */;
INSERT INTO `reservation_package_info` VALUES ('RES001','PKG003','CUST099'),('RES002','PKG003','CUST099'),('RES002','PKG004','CUST099'),('RES003','PKG001','CUST099'),('RES004','PKG001','CUST099'),('RES004','PKG002','CUST099'),('RES006','PKG003','CUST099'),('RES006','PKG005','CUST099'),('RES007','PKG003','CUST099'),('RES007','PKG007','CUST099'),('RES011','PKG001','CUST099'),('RES011','PKG002','CUST099'),('RES012','PKG003','CUST099');
/*!40000 ALTER TABLE `reservation_package_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservation_room_info`
--

DROP TABLE IF EXISTS `reservation_room_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reservation_room_info` (
  `ReservationID` varchar(50) NOT NULL,
  `RoomID` varchar(50) NOT NULL,
  PRIMARY KEY (`ReservationID`,`RoomID`),
  KEY `RoomID` (`RoomID`),
  CONSTRAINT `reservation_room_info_ibfk_1` FOREIGN KEY (`ReservationID`) REFERENCES `reservation` (`ReservationID`),
  CONSTRAINT `reservation_room_info_ibfk_2` FOREIGN KEY (`RoomID`) REFERENCES `room` (`RoomID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservation_room_info`
--

LOCK TABLES `reservation_room_info` WRITE;
/*!40000 ALTER TABLE `reservation_room_info` DISABLE KEYS */;
INSERT INTO `reservation_room_info` VALUES ('RES003','RM001'),('RES004','RM002'),('RES012','RM004'),('RES004','RM005'),('RES011','RM009'),('RES002','RM011'),('RES002','RM014'),('RES001','RM018'),('RES011','RM019'),('RES007','RM020'),('RES006','RM024'),('RES007','RM025'),('RES006','RM028');
/*!40000 ALTER TABLE `reservation_room_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservation_service_info`
--

DROP TABLE IF EXISTS `reservation_service_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reservation_service_info` (
  `ReservationID` varchar(50) NOT NULL,
  `ServiceID` varchar(50) NOT NULL,
  PRIMARY KEY (`ReservationID`,`ServiceID`),
  KEY `ServiceID` (`ServiceID`),
  CONSTRAINT `reservation_service_info_ibfk_1` FOREIGN KEY (`ReservationID`) REFERENCES `reservation` (`ReservationID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `reservation_service_info_ibfk_2` FOREIGN KEY (`ServiceID`) REFERENCES `service` (`ServiceID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservation_service_info`
--

LOCK TABLES `reservation_service_info` WRITE;
/*!40000 ALTER TABLE `reservation_service_info` DISABLE KEYS */;
INSERT INTO `reservation_service_info` VALUES ('RES003','SER001'),('RES004','SER001'),('RES011','SER001'),('RES004','SER002'),('RES011','SER002'),('RES006','SER004'),('RES007','SER004'),('RES012','SER004'),('RES001','SER005'),('RES006','SER007'),('RES002','SER009'),('RES007','SER009'),('RES002','SER012');
/*!40000 ALTER TABLE `reservation_service_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room`
--

DROP TABLE IF EXISTS `room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `room` (
  `RoomID` varchar(50) NOT NULL,
  `Type` varchar(255) DEFAULT NULL,
  `Status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`RoomID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room`
--

LOCK TABLES `room` WRITE;
/*!40000 ALTER TABLE `room` DISABLE KEYS */;
INSERT INTO `room` VALUES ('RM001','Single','Available'),('RM002','Double','Available'),('RM003','Suite','Available'),('RM004','Single','Booked'),('RM005','Double','Available'),('RM006','Suite','Available'),('RM007','Single','Available'),('RM008','Double','Available'),('RM009','Suite','Available'),('RM010','Single','Available'),('RM011','Double','Available'),('RM012','Suite','Available'),('RM013','Single','Available'),('RM014','Double','Available'),('RM015','Suite','Available'),('RM016','Single','Available'),('RM017','Double','Available'),('RM018','Suite','Available'),('RM019','Single','Available'),('RM020','Double','Available'),('RM021','Suite','Available'),('RM022','Single','Available'),('RM023','Double','Available'),('RM024','Suite','Available'),('RM025','Single','Available'),('RM026','Double','Available'),('RM027','Suite','Available'),('RM028','Single','Available'),('RM029','Double','Available'),('RM030','Suite','Available');
/*!40000 ALTER TABLE `room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `service`
--

DROP TABLE IF EXISTS `service`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `service` (
  `ServiceID` varchar(50) NOT NULL,
  `Name` varchar(255) DEFAULT NULL,
  `Description` text,
  `Price` decimal(10,2) DEFAULT NULL,
  `Availability` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ServiceID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service`
--

LOCK TABLES `service` WRITE;
/*!40000 ALTER TABLE `service` DISABLE KEYS */;
INSERT INTO `service` VALUES ('SER001','Managerial Consultation','One-on-one consultation with experienced managers to address managerial challenges and enhance leadership skills.',20.00,'By appointment'),('SER002','Assistant Manager Training Program','A comprehensive training program designed for aspiring assistant managers to develop leadership and management skills.',40.00,'Scheduled sessions'),('SER003','Front Desk Assistance','Dedicated support for efficient front desk operations, including check-ins, check-outs, and guest inquiries.',60.00,'Available 24/7'),('SER004','Front Desk Software Installation','Installation and configuration of front desk software to streamline operations and improve guest experience.',10.00,'By appointment'),('SER005','Service Supervisor Guidance','Guidance and support for service supervisors to ensure smooth service delivery and customer satisfaction.',52.00,'Available during business hours'),('SER006','Room Cleaning and Maintenance','Professional cleaning and maintenance services to ensure guest rooms are always clean, comfortable, and well-maintained.',12.00,'Available daily'),('SER007','Receptionist Training Program','A training program designed to enhance the skills and efficiency of receptionists in handling guest inquiries and reservations.',25.00,'Scheduled sessions'),('SER008','Culinary Experience Package','A gourmet dining experience curated by our expert chefs, offering a taste of luxury and culinary excellence.',12.00,'By reservation'),('SER009','Sous Chef Assistance Program','Personalized assistance and guidance provided by experienced sous chefs to support kitchen operations and ensure food quality.',14.00,'By appointment'),('SER010','Professional Waiter Service','Highly trained waitstaff providing professional and attentive service to enhance the dining experience for guests.',15.00,'Available during meal times'),('SER011','Financial Consultation Services','Expert financial advice and consultation to assist in budgeting, financial planning, and investment decisions.',48.00,'By appointment'),('SER012','Financial Analysis and Reporting','Comprehensive financial analysis and reporting services to provide insights into business performance and trends.',23.00,'By appointment'),('SER013','HR Management Solutions','Tailored HR management solutions including recruitment, employee relations, and performance management.',13.00,'By appointment'),('SER014','IT Infrastructure Management','Management and maintenance of IT infrastructure to ensure optimal performance, reliability, and security.',13.00,'By appointment'),('SER015','HR Administrative Support','Administrative support services for HR functions including documentation, record-keeping, and compliance.',15.00,'Available during business hours'),('SER016','IT Help Desk Support','24/7 technical support services to address IT issues, troubleshoot problems, and provide solutions.',18.00,'Available 24/7'),('SER017','Managerial Leadership Workshops','Interactive workshops focused on developing leadership skills, strategic thinking, and effective decision-making.',8.00,'Scheduled workshops'),('SER018','Assistant Manager Coaching','Personalized coaching sessions for assistant managers to develop leadership competencies and managerial capabilities.',23.00,'By appointment'),('SER019','Customer Service Training Program','Training programs designed to enhance customer service skills, including communication, empathy, and problem-solving.',15.00,'Scheduled sessions'),('SER020','Front Desk Software Training','Training sessions to familiarize staff with front desk software features, functionality, and best practices.',13.00,'By appointment'),('SER021','Service Quality Assessment','Comprehensive assessment of service quality standards, customer feedback, and operational processes to drive continuous improvement.',50.00,'By appointment'),('SER022','Room Maintenance and Repairs','Prompt maintenance and repair services for guest rooms, ensuring comfort, safety, and functionality.',19.00,'Available during business hours'),('SER023','Receptionist Skills Development','Skill development programs focusing on communication, problem-solving, and guest interaction skills for receptionists.',17.00,'Scheduled sessions'),('SER024','Culinary Workshops','Interactive workshops conducted by our chefs covering various cuisines, cooking techniques, and culinary trends.',29.00,'Scheduled workshops'),('SER025','Kitchen Equipment Maintenance','Regular maintenance and servicing of kitchen equipment to ensure efficiency, safety, and compliance with regulations.',25.00,'By appointment'),('SER026','Waiter Etiquette Training','Training sessions focusing on professional etiquette, service standards, and guest engagement for waitstaff.',30.00,'Scheduled sessions'),('SER027','Financial Planning Consultation','Personalized financial planning consultations to set financial goals, manage investments, and plan for the future.',16.00,'By appointment'),('SER028','Financial Risk Analysis','Thorough analysis of financial risks, market trends, and investment opportunities to optimize portfolio performance and minimize risk exposure.',23.00,'By appointment'),('SER029','HR Policy Development','Customized development of HR policies and procedures aligned with organizational goals, legal requirements, and best practices.',45.00,'By appointment'),('SER030','IT System Upgrade','Planning, implementation, and testing of IT system upgrades to enhance functionality, performance, and security.',17.00,'By appointment'),('SER031','Employee Training Program','Tailored training programs covering onboarding, skill development, compliance training, and career advancement opportunities.',26.00,'Scheduled sessions'),('SER032','Employee Performance Evaluation','Comprehensive evaluation services to assess employee performance, identify strengths and areas for improvement, and support career development.',15.00,'By appointment'),('SER033','IT Security Audit','Thorough assessment of IT security systems, policies, and procedures to identify vulnerabilities, mitigate risks, and ensure compliance.',48.00,'By appointment'),('SER034','Managerial Leadership Coaching','Personalized coaching sessions for managers to enhance leadership effectiveness, interpersonal skills, and team management capabilities.',16.00,'By appointment'),('SER035','Assistant Manager Mentoring','Structured mentoring programs pairing assistant managers with experienced mentors to provide guidance, support, and career development opportunities.',46.00,'By appointment'),('SER036','Customer Feedback Analysis','In-depth analysis of customer feedback, satisfaction surveys, and reviews to gain insights, identify trends, and drive service improvements.',23.00,'By appointment'),('SER037','Front Desk Operations Training','Training sessions covering front desk procedures, systems, and protocols to ensure efficiency, accuracy, and guest satisfaction.',32.00,'Scheduled sessions'),('SER038','Service Improvement Consultation','Consultation services to assess service gaps, identify improvement opportunities, and develop action plans to enhance service quality and customer experience.',56.00,'By appointment'),('SER039','Room Service Training','Training programs for room service staff focusing on menu knowledge, order-taking, presentation, and guest interaction skills.',42.00,'Scheduled sessions'),('SER040','Receptionist Performance Evaluation','Objective evaluation services to assess receptionist performance, measure key performance indicators, and provide constructive feedback for improvement.',53.00,'By appointment'),('SER041','Culinary Skills Enhancement Program','Structured programs designed to enhance culinary skills, creativity, and efficiency for chefs and kitchen staff.',35.00,'By appointment'),('SER042','Kitchen Hygiene Audit','Comprehensive audit services to assess kitchen hygiene practices, sanitation standards, and compliance with health and safety regulations.',14.00,'By appointment'),('SER043','Waiter Communication Skills Training','Training sessions focusing on effective communication, active listening, and interpersonal skills for waitstaff to deliver exceptional service experiences.',27.00,'Scheduled sessions'),('SER044','Financial Forecasting Analysis','Advanced financial analysis techniques to forecast revenues, expenses, and cash flows, supporting strategic decision-making and planning.',35.00,'By appointment'),('SER045','HR Compliance Audit','Thorough audit services to review HR policies, procedures, and practices for compliance with legal requirements, industry standards, and best practices.',26.00,'By appointment'),('SER046','IT Troubleshooting Services','Prompt troubleshooting and resolution of IT issues, technical problems, and system errors to minimize downtime and ensure operational continuity.',65.00,'Available during business hours'),('SER047','Employee Engagement Program','Strategic initiatives and programs aimed at fostering employee engagement, motivation, and satisfaction to improve retention, productivity, and organizational performance.',34.00,'Scheduled sessions'),('SER048','IT Training Workshops','Hands-on workshops covering various IT topics, tools, and technologies to enhance skills, knowledge, and proficiency for IT professionals.',30.00,'Scheduled workshops'),('SER049','Service Excellence Awards','Recognition program to celebrate and reward employees for outstanding service performance, dedication, and contributions to guest satisfaction and loyalty.',20.00,'Annual event'),('SER050','Managerial Consultation','One-on-one consultation with experienced managers to address managerial challenges and enhance leadership skills.',40.00,'By appointment');
/*!40000 ALTER TABLE `service` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `supplier`
--

DROP TABLE IF EXISTS `supplier`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `supplier` (
  `SupplierID` varchar(50) NOT NULL,
  `Name` varchar(255) DEFAULT NULL,
  `PostalCode` varchar(255) DEFAULT NULL,
  `EmailAddress` varchar(255) DEFAULT NULL,
  `ContactNumber` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`SupplierID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `supplier`
--

LOCK TABLES `supplier` WRITE;
/*!40000 ALTER TABLE `supplier` DISABLE KEYS */;
INSERT INTO `supplier` VALUES ('SUP001','Sri Lanka Spice Traders','20000','info@srilankaspicetraders.lk','0775616104'),('SUP002','Ceylon Tea Distributors','21000','sales@ceylonteadistributors.lk','+94 11 3456789'),('SUP003','Colombo Coconut Exporters','22000','info@colombococonutexporters.lk','+94 11 4567890'),('SUP004','Galle Gem Merchants','23000','info@gallegemmerchants.lk','+94 11 5678901'),('SUP005','Kandy Kitchen Supplies','24000','sales@kandykitchensupplies.lk','+94 11 6789012'),('SUP006','Jaffna Jam Producers','25000','info@jaffnajamproducers.lk','+94 11 7890123'),('SUP007','Bentota Biscuit Manufacturers','26000','sales@bentotabiscuitmanufacturers.lk','+94 11 8901234'),('SUP008','Negombo Nut Packers','27000','info@negombonutpackers.lk','+94 11 9012345'),('SUP009','Matara Mango Farms','28000','sales@mataramangofarms.lk','+94 11 0123456'),('SUP010','Polonnaruwa Pottery Works','29000','info@polonnaruwapotteryworks.lk','+94 11 1234567'),('SUP011','Anuradhapura Artisan Crafts','30000','sales@anuradhapuraartisancrafts.lk','+94 11 2345678'),('SUP012','Trincomalee Textile Mills','31000','info@trincomaleetextilemills.lk','+94 11 3456789'),('SUP013','Batticaloa Basket Weavers','32000','sales@batticaloabasketweavers.lk','+94 11 4567890'),('SUP014','Nuwara Eliya Elixir Enterprises','33000','info@nuwaraeliyaelixireneterprises.lk','+94 11 5678901'),('SUP015','Polonnaruwa Paint Suppliers','34000','sales@polonnaruwapaintsuppliers.lk','+94 11 6789012'),('SUP016','Hikkaduwa Handicrafts Pvt Ltd','35000','info@hikkaduwahandicrafts.lk','+94 11 7890123'),('SUP017','Mirissa Memento Makers','36000','sales@mirissamementomakers.lk','+94 11 8901234'),('SUP018','Ella Exporters','37000','info@ellaexporters.lk','+94 11 9012345'),('SUP019','Tangalle Textile Traders','38000','sales@tangalletextiletraders.lk','+94 11 0123456'),('SUP020','Kataragama Knitwear','39000','info@kataragamaknitwear.lk','+94 11 1234567'),('SUP021','Badulla Basket Co.','40000','sales@badullabasketco.lk','+94 11 2345678'),('SUP022','Kalutara Craft Creations','41000','info@kalutaracraftcreations.lk','+94 11 3456789'),('SUP023','Dambulla Delight Distributors','42000','sales@dambulladelightdistributors.lk','+94 11 4567890'),('SUP024','Hambantota Handicrafts Ltd','43000','info@hambantotahandicrafts.lk','+94 11 5678901'),('SUP025','Balapitiya Bakes Pvt Ltd','44000','sales@balapitiyabakes.lk','+94 11 6789012'),('SUP026','Ambalangoda Artisan Works','45000','info@ambalangodaartisanworks.lk','+94 11 7890123'),('SUP027','Koggala Knitwear Manufacturers','46000','sales@koggalaknitwearmanufacturers.lk','+94 11 8901234'),('SUP028','Yala Yarn Supplies','47000','info@yalayarnsupplies.lk','+94 11 9012345'),('SUP029','Dondra Decor Designers','48000','sales@dondradecordesigners.lk','+94 11 0123456'),('SUP030','Polonnaruwa Pottery Makers','490000','info@polonnaruwapotterymakers.lk','+94 11 1234567');
/*!40000 ALTER TABLE `supplier` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `UserID` varchar(50) NOT NULL,
  `EmployeeID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `Username` varchar(255) DEFAULT NULL,
  `Password` varchar(255) DEFAULT NULL,
  `LastLoginDate` date DEFAULT NULL,
  `LastLoginTime` time DEFAULT NULL,
  `IsActive` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`UserID`),
  UNIQUE KEY `Username` (`Username`),
  KEY `EmployeeID` (`EmployeeID`),
  CONSTRAINT `user_ibfk_1` FOREIGN KEY (`EmployeeID`) REFERENCES `employee` (`EmployeeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('1','EMP007','amandi silva','1','2024-05-16','21:48:17',1),('USR001','EMP001','kamalsilva','Kamal12@','2024-04-16','12:29:41',1),('USR002','EMP002','nimalfernando','nimal12','2024-04-07','08:49:22',1),('USR003','EMP003','sunilperera','sunil12','2024-04-18','23:01:18',1),('USR004','EMP004','anomadesilva','anoma','2024-04-07','08:49:22',1),('USR005','EMP005','chamindagunawardena','p@$$w0rd','2024-04-30','00:15:50',1),('USR006','EMP006','Chamath','chama12','2024-05-01','18:59:26',1),('USR007','EMP008','CHAMATH12','AMANDI','2024-05-16','08:55:48',1),('USR008','EMP009','ChamathDilshan','Chamath123@','2024-05-16','09:39:33',1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'sm_resort'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-16 21:55:49
