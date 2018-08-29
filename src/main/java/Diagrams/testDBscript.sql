-- -----------------------------------------------------
-- Schema fogTest
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `fogTest`;
CREATE SCHEMA IF NOT EXISTS `fogTest` DEFAULT CHARACTER SET utf8 ;
USE `fogTest`;

CREATE TABLE fogTest.Zipcode LIKE fog.Zipcode;
CREATE TABLE fogTest.Customer LIKE fog.Customer;
CREATE TABLE fogTest.Employee LIKE fog.Employee;
CREATE TABLE fogTest.Inquiry LIKE fog.Inquiry;
CREATE TABLE fogTest.Product LIKE fog.Product;

-- make an other copy for copy purpuses
CREATE TABLE ZipcodeTest LIKE fogTest.Zipcode;
CREATE TABLE CustomerTest LIKE fogTest.Customer;
CREATE TABLE EmployeeTest LIKE fogTest.Employee;
CREATE TABLE InquiryTest LIKE fogTest.Inquiry;
CREATE TABLE ProductTest LIKE fogTest.Product;

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
CustomerTest (email, name, surname, phonenumber, address, zipcode, password)
VALUES 
('test1@test.dk','Hans','Hansen',11223344,'Torskevej 1',2750, 'Hansen1'),
('test2@test.dk','Tom','Tomsen',44332211,'Sildevej 2',2000, 'Tomsen1'),
('test3@test.dk','Hanne','Hannesen',11112233,'Makrelvej 3',2800, 'Hannesen'),
('test4@test.dk','Pernille','Pernillesen',44443322,'Spættevej 4',2800, 'Pernillesen'),
('test5@test.dk','Dorthe','Larsen',44533322,'Totalvej 2',2800, 'Dorthel'),
('test8@test.dk','Anders','Andersen',42343322,'Absvej 2',2730, '333aaa'),
('test6@test.dk','Oliver','Håkonsson',24566333,'Taskestrupvej 42',2000, 'gggg');

INSERT INTO
Employee (name, surname, password)
VALUES
('Martin', 'Fogmaster', 'emp1'),
('Johannes', 'Fog','emp2'),
('Frodo', 'Baggings','emp3');

INSERT INTO
InquiryTest (carportHeight,carportLength,carportWidth,shackWidth,shackLength,roofType,roofMaterial,angle,commentCustomer,commentEmployee,period, status, email)
VALUES
(320,420,320,200,120,'fladt','CEMBRIT OVENLYSPLADE B7 PVC GLASKLAR 1100X610X1MM',null,null,null,null,'gemt','test1@test.dk'),
(320,420,320,0,0,'rejsning','ROEDE VINGETAGSTEN GL. DANSK FORBRUG: 14,6 STK/M2','20','send Email',null,'2017-12-20','gemt','test1@test.dk'),
(240,470,360,360,220,'fladt','CEMBRIT OVENLYSPLADE B7 PVC GLASKLAR 1100X610X1MM',null,'Kan der vælges andre tag-materialer end det viste?',null,'2017-12-24','ny','test2@test.dk'),
(320,420,320,210,150,'rejsning','ROEDE VINGETAGSTEN GL. DANSK FORBRUG: 14,6 STK/M2','15','Hurtigts muligt','Ring op omkring leveringstidspunkt',null,'behandlet','test3@test.dk'),
(210,420,320,null,null,'rejsning','ROEDE VINGETAGSTEN GL. DANSK FORBRUG: 14,6 STK/M2','15',null,null,null,'behandlet','test3@test.dk'),
(320,570,410,null,null,'rejsning','ROEDE VINGETAGSTEN GL. DANSK FORBRUG: 14,6 STK/M2','25','Kan jeg sætte en tagrende på denne carport?','Kontakt vedr. valg af tagtype','2017-07-14','ny','test5@test.dk'),
(270,570,410,null,null,'rejsning','ROEDE VINGETAGSTEN GL. DANSK FORBRUG: 14,6 STK/M2','25',null,null,'2017-07-15','ny','test8@test.dk'),
(320,420,320,null,null,'rejsning','ROEDE VINGETAGSTEN GL. DANSK FORBRUG: 14,6 STK/M2','15','Ring tak!',null,null,'ny','test6@test.dk');


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