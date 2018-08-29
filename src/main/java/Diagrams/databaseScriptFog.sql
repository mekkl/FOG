-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema fog
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `fog` ;

-- -----------------------------------------------------
-- Schema fog
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `fog` DEFAULT CHARACTER SET utf8 ;
USE `fog` ;

-- -----------------------------------------------------
-- Table `fog`.`Zipcode`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fog`.`Zipcode` (
  `zipcode` INT NOT NULL,
  `city` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`zipcode`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fog`.`Customer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fog`.`Customer` (
  `email` VARCHAR(45) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `surname` VARCHAR(45) NOT NULL,
  `phonenumber` INT NOT NULL,
  `address` VARCHAR(45) NOT NULL,
  `zipcode` INT NOT NULL,
  `password` VARCHAR(45) NULL,
  PRIMARY KEY (`email`),
  INDEX `postnummer_idx` (`zipcode` ASC),
  CONSTRAINT `zipcode`
    FOREIGN KEY (`zipcode`)
    REFERENCES `fog`.`Zipcode` (`zipcode`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fog`.`Employee`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fog`.`Employee` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(30) NOT NULL,
  `surname` VARCHAR(30) NOT NULL,
  `password` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fog`.`Inquiry`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fog`.`Inquiry` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `carportHeight` INT NOT NULL,
  `carportLength` INT NOT NULL,
  `carportWidth` INT NOT NULL,
  `shackWidth` INT NULL,
  `shackLength` INT NULL,
  `roofType` ENUM('fladt', 'rejsning') NOT NULL,
  `roofMaterial` VARCHAR(100),
  `angle` ENUM('15', '20', '25', '30', '35', '40', '45') NULL,
  `commentCustomer` VARCHAR(2000) NULL,
  `commentEmployee` VARCHAR(2000) NULL,
  `period` DATE NULL,
  `status` ENUM('gemt','ny', 'behandles', 'behandlet') NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `id_employee` INT NULL,
  `date` DATETIME DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  INDEX `email_idx` (`email` ASC),
  INDEX `id_ansat_idx` (`id_employee` ASC),
  CONSTRAINT `email`
    FOREIGN KEY (`email`)
    REFERENCES `fog`.`Customer` (`email`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `id_employee`
    FOREIGN KEY (`id_employee`)
    REFERENCES `fog`.`Employee` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fog`.`Product`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fog`.`Product` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(200) NOT NULL,
  `category` ENUM('bræt', 'rem', 'spær', 'værktøj', 'stolpe', 'skrue', 'søm', 'lægte', 'tagpap', 'stern', 'vindskede','beklædning','tagsten','trapeztag','løsholt') NOT NULL,
  `price` LONG NOT NULL,
  `length` INT NULL,
  `width` INT NULL,
  `height` INT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- DUMMYDATA 
-- -----------------------------------------------------
INSERT INTO 
Zipcode (zipcode, city)
VALUES
(2750,'Ballerup'),
(2000,'Frederiksberg'),
(2800, 'Kongens Lyngby'),
(9999, 'Gotham');

INSERT INTO 
Customer (email, name, surname, phonenumber, address, zipcode, password)
VALUES 
('test1@test.dk','Hans','Hansen',11223344,'Torskevej 1',2750, 'Hansen1')
;

INSERT INTO
Inquiry (carportHeight,carportLength,carportWidth,shackWidth,shackLength,roofType,roofMaterial,angle,commentCustomer,commentEmployee,period, status, email)
VALUES
(320,420,320,200,120,'fladt','CEMBRIT OVENLYSPLADE B7 PVC GLASKLAR 1100X610X1MM',null,null,null,null,'gemt','test1@test.dk'),
(320,420,320,0,0,'rejsning','ROEDE VINGETAGSTEN GL. DANSK FORBRUG: 14,6 STK/M2','20','send Email',null,'2017-12-20','gemt','test1@test.dk');

INSERT INTO
Employee (name, surname, password)
VALUES
('Martin', 'Fogmaster', 'emp1'),
('Johannes', 'Fog','emp2'),
('Frodo', 'Baggings','emp3');

-- --------------------------------------------------------------------------
-- CATEGORIES =  'bræt', 'rem', 'spær', 'værktøj', 'stolpe', 'skrue', 'søm', 'lægte', 'tagpap', 'stern', 'vindskede','beklædning','tagsten','trapeztag',','løsholt'
-- --------------------------------------------------------------------------
INSERT INTO
Product (name,category,price,length,width,height)
VALUES
-- bræt
('25X200 MM VTA TRYKIMPR. NTR/AB 600 CM','bræt',29970,6000,200,25),
('25X175 MM VTA TRYKIMPR. NTR/AB 600 CM','bræt',26370,6000,175,25),
('25X150 MM VTA TRYKIMPR. NTR/AB 600 CM','bræt',22770,6000,150,25),

-- rem - 
('45x195mm. spærtræ ubh.','rem',6145,7500,45,295),
('45x195mm. spærtræ ubh.','rem',5900,7200,45,295),
('45x195mm. spærtræ ubh.','rem',5654,6900,45,295),
('45x195mm. spærtræ ubh.','rem',5408,6600,45,295),
('45x195mm. spærtræ ubh.','rem',5162,6300,45,295),
('45x195mm. spærtræ ubh.','rem',4916,6000,45,295),
('45x195mm. spærtræ ubh.','rem',4670,5700,45,295),
('45x195mm. spærtræ ubh.','rem',4425,5400,45,295),
('45x195mm. spærtræ ubh.','rem',4719,5100,45,295),
('45x195mm. spærtræ ubh.','rem',3933,4800,45,295),


-- spær
('45x195mm. spærtræ ubh. til spær','spær',59000,7200,45,295), -- fladt
('færdigskåret (byg-selv-spær)','spær',70000,0,0,0), -- rejsning

-- værktøj
('SKRUEMASKINE','værktøj',9999,0,0,0),
('HÅNDSAV','værktøj',1590,0,0,0),
('VATERPAS','værktøj',5190,0,0,0),

-- stolpe
('97x97mm. trykimp. Stolpe','stolpe',10905,3900,97,97),
('97x97mm. trykimp. Stolpe','stolpe',10065,3600,97,97),
('97x97mm. trykimp. Stolpe','stolpe',9223,3300,97,97),
('97x97mm. trykimp. Stolpe','stolpe',8385,3000,97,97),


-- skrue
('NKT SPUN+ SKRUE UHJ 3X25MM TORX ELFORZINKET','skrue',3600,0,0,0),
('NKT FRANSK SKRUE 8X120MM VFZ 50 STK/PK','skrue',2990,0,0,0),
('NKT SPUN+ SKRUE UHJ 3,5X30MM TORX ELFORZINKET','skrue',3600,0,0,0),

-- søm
('NKT FIRKANT SØM 1,2X20MM BLANKE','søm',2700,0,0,0),
('NKT FIRKANT SØM 1,6X25MM BLANKE','søm',2700,0,0,0),
('NKT FIRKANT SØM 1,6X25MM VARMFORZINKET','søm',3600,0,0,0),

-- lægte
('38x73mm. Lægte ubh.', 'lægte', 6993, 5400, 38, 73),
('38x73mm. Lægte ubh.', 'lægte', 6604, 5100, 38, 73),
('38x73mm. Lægte ubh.', 'lægte', 6216, 4800, 38, 73),
('38x73mm. Lægte ubh.', 'lægte', 5827, 4500, 38, 73),
('38x73mm. Lægte ubh.', 'lægte', 5439, 4200, 38, 73),
('38x73mm. Lægte ubh.', 'lægte', 5050, 3900, 38, 73),
('38x73mm. Lægte ubh.', 'lægte', 4662, 3600, 38, 73),

-- tagpap
('ICOPAL BASE 411 P 1X8M', 'tagpap', 84900, 8000, 1000, 0),
('ICOPAL TOP 310 G SORT 0,6X7,5M SKURPAP', 'tagpap', 29900, 7500, 600, 0),
('ICOPAL TOP 500P SORT 1X5M', 'tagpap', 59900, 5000, 1000, 0),

-- stern
('25X125 MM STERN OVERBRÆDT TRYKIMP. FYR NTR/AB - 540 CM', 'stern', 1293, 5400, 125, 25),
('25X125 MM STERN MELLEMBRÆDT TRYKIMPR. FYR NTR/AB - 540 CM', 'stern', 1293, 5400, 125, 25),
('25X125 MM STERN UNDERBRÆDT TRYKIMPR. FYR NTR/AB - 540 CM', 'stern', 1293, 5400, 125, 25),

-- vindskede
('22X195 VINDSKEDE M/RU FREMSIDE GRAN US/VTA NTR-GRAN IMPR. - 540 CM','vindskede', 23193, 5400, 195, 22),
('22X195 VINDSKEDE M/RU FREMSIDE GRAN US/VTA NTR-GRAN IMPR. - 480 CM','vindskede', 20616, 4800, 195, 22),
('22X195 VINDSKEDE M/RU FREMSIDE GRAN US/VTA NTR-GRAN IMPR. - 420 CM','vindskede', 18039, 4200, 195, 22),

-- beklædning
('19x100 mm. trykimp. Brædt','beklædning', 8136, 4800, 100, 19),
('19x100 mm. trykimp. Brædt','beklædning', 7119, 4200, 100, 19),
('19x100 mm. trykimp. Brædt','beklædning', 6103, 3600, 100, 19),
('19x100 mm. trykimp. Brædt','beklædning', 5085, 3000, 100, 19),

('19X100 BRÆDDER FYR SAVSKÅRET VTA','beklædning', 8073, 5400, 100, 19),
('19X100 BRÆDDER FYR SAVSKÅRET VTA','beklædning', 7625, 5100, 100, 19),
('19X100 BRÆDDER FYR SAVSKÅRET VTA','beklædning', 7176, 4800, 100, 19),
('19X100 BRÆDDER FYR SAVSKÅRET VTA','beklædning', 6728, 4500, 100, 19),
('19X100 BRÆDDER FYR SAVSKÅRET VTA','beklædning', 6279, 4200, 100, 19),
('19X100 BRÆDDER FYR SAVSKÅRET VTA','beklædning', 5830, 3900, 100, 19),
('19X100 BRÆDDER FYR SAVSKÅRET VTA','beklædning', 5383, 3600, 100, 19),
('19X100 BRÆDDER FYR SAVSKÅRET VTA','beklædning', 4934, 3300, 100, 19),
('19X100 BRÆDDER FYR SAVSKÅRET VTA','beklædning', 4485, 3000, 100, 19),
('19X100 BRÆDDER FYR SAVSKÅRET VTA','beklædning', 4036, 2700, 100, 19),
('19X100 BRÆDDER FYR SAVSKÅRET VTA','beklædning', 3588, 2400, 100, 19),
('19X100 BRÆDDER FYR SAVSKÅRET VTA','beklædning', 3140, 2100, 100, 19),
('19X100 BRÆDDER FYR SAVSKÅRET VTA','beklædning', 2691, 1800, 100, 19),

('25X100 MM FYR SEKSTA TRYKIMPR. NTR/AB','beklædning', 6453, 5400, 100, 25),
('25X100 MM FYR SEKSTA TRYKIMPR. NTR/AB','beklædning', 5736, 4800, 100, 25),
('25X100 MM FYR SEKSTA TRYKIMPR. NTR/AB','beklædning', 5019, 4200, 100, 25),
('25X100 MM FYR SEKSTA TRYKIMPR. NTR/AB','beklædning', 4303, 3600, 100, 25),
('25X100 MM FYR SEKSTA TRYKIMPR. NTR/AB','beklædning', 3585, 3000, 100, 25),
('25X100 MM FYR SEKSTA TRYKIMPR. NTR/AB','beklædning', 2868, 2400, 100, 25), 

-- tagsten
('ROEDE VINGETAGSTEN GL. DANSK FORBRUG: 14,6 STK/M2','tagsten', 1495, 404, 236, 0),
('ROEDE RYGSTEN MODEL VOLSTRUP DANSKTAG- FORBRUG: 3,5 STK/LBM','tagsten', 8995, 0, 0, 0),  -- ingen mål defineret

-- trapeztag
('CEMBRIT OVENLYSPLADE B7 PVC GLASKLAR 1100X610X1MM','trapeztag', 24900, 1100, 610, 1),
('CEMBRIT B6S FK GRAA BOELGEPLADE 1090X1180MM - (MODEL 2013)','trapeztag', 25900, 1090, 1180, 1),

-- løsholt
('45x95 Reglar ubh.','løsholt',7533,5400,95,45),
('45x95 Reglar ubh.','løsholt',7115,5100,95,45),
('45x95 Reglar ubh.','løsholt',6696,4800,95,45),
('45x95 Reglar ubh.','løsholt',6278,4500,95,45),
('45x95 Reglar ubh.','løsholt',5859,4200,95,45),
('45x95 Reglar ubh.','løsholt',5440,3900,95,45),
('45x95 Reglar ubh.','løsholt',5023,3600,95,45),
('45x95 Reglar ubh.','løsholt',4604,3300,95,45),
('45x95 Reglar ubh.','løsholt',4185,3000,95,45),
('45x95 Reglar ubh.','løsholt',3766,2700,95,45),
('45x95 Reglar ubh.','løsholt',3348,2400,95,45)
;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;