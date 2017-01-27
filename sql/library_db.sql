-- MySQL Script generated by MySQL Workbench
-- Thu 19 Jan 2017 10:05:36 PM EET
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema library_db
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema library_db
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `library_db` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin ;
USE `library_db` ;

-- -----------------------------------------------------
-- Table `library_db`.`category`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `library_db`.`category` ;

CREATE TABLE IF NOT EXISTS `library_db`.`category` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC))
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `library_db`.`catalog`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `library_db`.`catalog` ;

CREATE TABLE IF NOT EXISTS `library_db`.`catalog` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(100) NOT NULL,
  `author` VARCHAR(100) NOT NULL,
  `year_of_publication` INT NOT NULL,
  `category_id` INT NOT NULL,
  `ISBN` VARCHAR(20) NOT NULL,
  `amount_all` INT NOT NULL,
  `amount_available` INT NOT NULL,
  `status` ENUM('ACTIVE', 'DELETED') NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  INDEX `fk_catalog_1_idx` (`category_id` ASC),
  CONSTRAINT `fk_catalog_1`
  FOREIGN KEY (`category_id`)
  REFERENCES `library_db`.`category` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `library_db`.`book`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `library_db`.`book` ;

CREATE TABLE IF NOT EXISTS `library_db`.`book` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `catalog_id` INT NOT NULL,
  `lib_identifier` VARCHAR(45) NOT NULL,
  `state` VARCHAR(50) NOT NULL,
  `location` ENUM('LIBRARY', 'ON_HAND', 'READING_ROOM') NOT NULL,
  `status` ENUM('ACTIVE', 'DELETED') NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  UNIQUE INDEX `lib_identifier_UNIQUE` (`lib_identifier` ASC),
  INDEX `fk_book_1_idx` (`catalog_id` ASC),
  CONSTRAINT `fk_book_1`
  FOREIGN KEY (`catalog_id`)
  REFERENCES `library_db`.`catalog` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `library_db`.`user_role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `library_db`.`user_role` ;

CREATE TABLE IF NOT EXISTS `library_db`.`user_role` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(25) NOT NULL,
  `status` ENUM('ACTIVE', 'DEACTIVATED') NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC))
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `library_db`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `library_db`.`user` ;

CREATE TABLE IF NOT EXISTS `library_db`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(45) NOT NULL,
  `f_name` VARCHAR(55) NOT NULL,
  `l_name` VARCHAR(55) NULL,
  `email` VARCHAR(75) NOT NULL,
  `password` VARCHAR(50) NOT NULL,
  `role_id` INT NOT NULL,
  `status` ENUM('ACTIVE', 'DEACTIVATED') NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  UNIQUE INDEX `login_UNIQUE` (`login` ASC),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC),
  INDEX `fk_user_1_idx` (`role_id` ASC),
  CONSTRAINT `fk_user_1`
  FOREIGN KEY (`role_id`)
  REFERENCES `library_db`.`user_role` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `library_db`.`order`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `library_db`.`order` ;

CREATE TABLE IF NOT EXISTS `library_db`.`order` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `catalog_id` INT NOT NULL,
  `date_of_issue` DATE NOT NULL,
  `expected_date_of_return` DATE NOT NULL,
  `date_of_return` DATE NULL,
  `order_type` ENUM('NEW', 'IN_PROCESSING', 'COMPLETED') NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  INDEX `fk_order_1_idx` (`user_id` ASC),
  INDEX `fk_order_2_idx` (`catalog_id` ASC),
  CONSTRAINT `fk_order_1`
  FOREIGN KEY (`user_id`)
  REFERENCES `library_db`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_order_2`
  FOREIGN KEY (`catalog_id`)
  REFERENCES `library_db`.`catalog` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `library_db`.`lib_response`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `library_db`.`lib_response` ;

CREATE TABLE IF NOT EXISTS `library_db`.`lib_response` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `order_id` INT NOT NULL,
  `processing_date` DATE NOT NULL,
  `librarian_id` INT NOT NULL,
  `catalog_id` INT NOT NULL,
  `book_id` INT NOT NULL,
  `book_location` ENUM('ON_HAND', 'READING_ROOM') NOT NULL,
  `date_of_return` DATE NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  INDEX `fk_lib_response_1_idx` (`order_id` ASC),
  INDEX `fk_lib_response_3_idx` (`librarian_id` ASC),
  INDEX `fk_lib_response_4_idx` (`book_id` ASC),
  INDEX `fk_lib_response_2_idx` (`catalog_id` ASC),
  CONSTRAINT `fk_lib_response_1`
  FOREIGN KEY (`order_id`)
  REFERENCES `library_db`.`order` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_lib_response_2`
  FOREIGN KEY (`catalog_id`)
  REFERENCES `library_db`.`catalog` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_lib_response_3`
  FOREIGN KEY (`librarian_id`)
  REFERENCES `library_db`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_lib_response_4`
  FOREIGN KEY (`book_id`)
  REFERENCES `library_db`.`book` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;