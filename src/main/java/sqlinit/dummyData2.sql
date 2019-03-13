INSERT INTO `CA2db`.`Cityinfo` (`zip`, `city`) VALUES ('2800', 'Lyngby');
INSERT INTO `CA2db`.`Cityinfo` (`zip`, `city`) VALUES ('2950', 'Vedbæk');
INSERT INTO `CA2db`.`Cityinfo` (`zip`, `city`) VALUES ('0555', 'Scanning');
INSERT INTO `CA2db`.`Cityinfo` (`zip`, `city`) VALUES ('0800', 'Høje Taastrup');
INSERT INTO `CA2db`.`Cityinfo` (`zip`, `city`) VALUES ('0877', 'København C');
INSERT INTO `CA2db`.`Cityinfo` (`zip`, `city`) VALUES ('0892', 'Sjælland USF P');
INSERT INTO `CA2db`.`Cityinfo` (`zip`, `city`) VALUES ('0893', 'Sjælland USF B');
INSERT INTO `CA2db`.`Cityinfo` (`zip`, `city`) VALUES ('0894', 'Udbetaling');
INSERT INTO `CA2db`.`Cityinfo` (`zip`, `city`) VALUES ('0897', 'eBrevsprækken');

INSERT INTO `CA2db`.`Address` (`Street`, `additionalinfo`, `Cityinfo_id`) VALUES ('Strandvejen 215', 'Whiskeybæltet', '1');
INSERT INTO `CA2db`.`Address` (`Street`, `additionalinfo`, `Cityinfo_id`) VALUES ('Klampenborgvej 483', 'Kongens Lyngby', '3');
INSERT INTO `CA2db`.`Address` (`Street`, `additionalinfo`, `Cityinfo_id`) VALUES ('Hovsavej!', 'Whiskeybæltet', '4');
INSERT INTO `CA2db`.`Address` (`Street`, `additionalinfo`, `Cityinfo_id`) VALUES ('Klampenborgvej 480', 'Kongens Lyngby', '6');

INSERT INTO `CA2db`.`Person` (`email`, `firstname`, `lastname`, `Address_ID`) VALUES ('hans@mail.dk', 'Hans', 'Henriksen', '1');
INSERT INTO `CA2db`.`Person` (`email`, `firstname`, `lastname`, `Address_ID`) VALUES ('jens@mail.dk', 'Jens', 'Mortensen', '2');
INSERT INTO `CA2db`.`Person` (`email`, `firstname`, `lastname`, `Address_ID`) VALUES ('bob@mail.dk', 'bob', 'bobsen', '3');
INSERT INTO `CA2db`.`Person` (`email`, `firstname`, `lastname`, `Address_ID`) VALUES ('carsten@mail.dk', 'carsten', 'carstensen', '3');

INSERT INTO `CA2db`.`phone` (`number`, `description`, `Person_ID`) VALUES ('12345678', 'Home', '1');
INSERT INTO `CA2db`.`phone` (`number`, `description`, `Person_ID`) VALUES ('23566434', 'Work', '1');
INSERT INTO `CA2db`.`phone` (`number`, `description`, `Person_ID`) VALUES ('64324567', 'Home', '2');
INSERT INTO `CA2db`.`phone` (`number`, `description`, `Person_ID`) VALUES ('53456723', 'Work', '2');

INSERT INTO `CA2db`.`Hobby` (`name`, `description`) VALUES ('Soccer', 'Test');
INSERT INTO `CA2db`.`Hobby` (`name`, `description`) VALUES ('Handball', 'Test');
INSERT INTO `CA2db`.`Hobby` (`name`, `description`) VALUES ('Surfing', 'Test');
INSERT INTO `CA2db`.`Hobby` (`name`, `description`) VALUES ('Golf', 'Test');

INSERT INTO `CA2db`.`person_hobby` (`hobbies_id`, `Person_ID`) VALUES ('4', '1');
INSERT INTO `CA2db`.`person_hobby` (`hobbies_id`, `Person_ID`) VALUES ('3', '1');
INSERT INTO `CA2db`.`person_hobby` (`hobbies_id`, `Person_ID`) VALUES ('2', '2');
INSERT INTO `CA2db`.`person_hobby` (`hobbies_id`, `Person_ID`) VALUES ('1', '2');


