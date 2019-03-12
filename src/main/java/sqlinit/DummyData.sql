INSERT INTO `CA2`.`Cityinfo` (`zipcode`, `city`) VALUES ('2800', 'Lyngby');
INSERT INTO `CA2`.`Cityinfo` (`zipcode`, `city`) VALUES ('2950', 'Vedbæk');

INSERT INTO `CA2`.`Address` (`Street`, `additionalinfo`, `Cityinfo_zipcode`, `ID`, `Cityinfo_zipcode1`) VALUES ('Strandvejen 215', 'Whiskeybæltet', '2800', '1', '2800');
INSERT INTO `CA2`.`Address` (`Street`, `additionalinfo`, `Cityinfo_zipcode`, `ID`, `Cityinfo_zipcode1`) VALUES ('Klampenborgvej 483', 'Kongens Lyngby', '2950', '2', '2800');

INSERT INTO `CA2`.`Person` (`ID`, `email`, `firstname`, `lastname`, `Address_ID`) VALUES ('1', 'hans@mail.dk', 'Hans', 'Henriksen', '1');
INSERT INTO `CA2`.`Person` (`ID`, `email`, `firstname`, `lastname`, `Address_ID`) VALUES ('2', 'jens@mail.dk', 'Jens', 'Mortensen', '2');

INSERT INTO `CA2`.`phone` (`number`, `description`, `Person_ID`) VALUES ('12345678', 'Home', '1');
INSERT INTO `CA2`.`phone` (`number`, `description`, `Person_ID`) VALUES ('23566434', 'Work', '1');
INSERT INTO `CA2`.`phone` (`number`, `description`, `Person_ID`) VALUES ('64324567', 'Home', '2');
INSERT INTO `CA2`.`phone` (`number`, `description`, `Person_ID`) VALUES ('53456723', 'Work', '2');

INSERT INTO `CA2`.`Hobby` (`name`, `description`) VALUES ('Soccer', 'Test');
INSERT INTO `CA2`.`Hobby` (`name`, `description`) VALUES ('Handball', 'Test');
INSERT INTO `CA2`.`Hobby` (`name`, `description`) VALUES ('Surfing', 'Test');
INSERT INTO `CA2`.`Hobby` (`name`, `description`) VALUES ('Golf', 'Test');

INSERT INTO `CA2`.`Hobby_has_Person` (`Hobby_name`, `Person_ID`) VALUES ('Soccer', '1');
INSERT INTO `CA2`.`Hobby_has_Person` (`Hobby_name`, `Person_ID`) VALUES ('Handball', '1');
INSERT INTO `CA2`.`Hobby_has_Person` (`Hobby_name`, `Person_ID`) VALUES ('Surfing', '2');
INSERT INTO `CA2`.`Hobby_has_Person` (`Hobby_name`, `Person_ID`) VALUES ('Golf', '2');


