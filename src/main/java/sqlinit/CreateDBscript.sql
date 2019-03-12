-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema CA2
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema CA2
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `CA2` DEFAULT CHARACTER SET utf8 ;
USE `CA2` ;

-- -----------------------------------------------------
-- Table `CA2`.`Hobby`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CA2`.`Hobby` (
  `name` VARCHAR(45) NOT NULL,
  `description` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`name`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `CA2`.`Cityinfo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CA2`.`Cityinfo` (
  `zipcode` INT NOT NULL,
  `city` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`zipcode`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `CA2`.`Address`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CA2`.`Address` (
  `Street` VARCHAR(245) NOT NULL,
  `additionalinfo` VARCHAR(45) NOT NULL,
  `Cityinfo_zipcode` INT NOT NULL,
  `ID` VARCHAR(45) NOT NULL,
  `Cityinfo_zipcode1` INT NOT NULL,
  PRIMARY KEY (`ID`, `Cityinfo_zipcode1`),
  INDEX `fk_Address_Cityinfo1_idx` (`Cityinfo_zipcode1` ASC),
  CONSTRAINT `fk_Address_Cityinfo1`
    FOREIGN KEY (`Cityinfo_zipcode1`)
    REFERENCES `CA2`.`Cityinfo` (`zipcode`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `CA2`.`Person`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CA2`.`Person` (
  `ID` INT NOT NULL,
  `email` VARCHAR(45) NULL,
  `firstname` VARCHAR(45) NULL,
  `lastname` VARCHAR(45) NULL,
  `Address_ID` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`ID`, `Address_ID`),
  INDEX `fk_Person_Address1_idx` (`Address_ID` ASC),
  CONSTRAINT `fk_Person_Address1`
    FOREIGN KEY (`Address_ID`)
    REFERENCES `CA2`.`Address` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `CA2`.`phone`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CA2`.`phone` (
  `number` VARCHAR(45) NOT NULL,
  `description` VARCHAR(45) NOT NULL,
  `Person_ID` INT NOT NULL,
  PRIMARY KEY (`number`, `Person_ID`),
  INDEX `fk_phone_Person1_idx` (`Person_ID` ASC),
  CONSTRAINT `fk_phone_Person1`
    FOREIGN KEY (`Person_ID`)
    REFERENCES `CA2`.`Person` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `CA2`.`Hobby_has_Person`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CA2`.`Hobby_has_Person` (
  `Hobby_name` VARCHAR(45) NOT NULL,
  `Person_ID` INT NOT NULL,
  PRIMARY KEY (`Hobby_name`, `Person_ID`),
  INDEX `fk_Hobby_has_Person_Person1_idx` (`Person_ID` ASC),
  INDEX `fk_Hobby_has_Person_Hobby_idx` (`Hobby_name` ASC),
  CONSTRAINT `fk_Hobby_has_Person_Hobby`
    FOREIGN KEY (`Hobby_name`)
    REFERENCES `CA2`.`Hobby` (`name`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Hobby_has_Person_Person1`
    FOREIGN KEY (`Person_ID`)
    REFERENCES `CA2`.`Person` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

USE `CA2` ;

-- -----------------------------------------------------
-- Placeholder table for view `CA2`.`view1`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CA2`.`view1` (`id` INT);

-- -----------------------------------------------------
-- Placeholder table for view `CA2`.`view2`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CA2`.`view2` (`id` INT);

-- -----------------------------------------------------
-- View `CA2`.`view1`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `CA2`.`view1`;
USE `CA2`;


-- -----------------------------------------------------
-- View `CA2`.`view2`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `CA2`.`view2`;
USE `CA2`;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
