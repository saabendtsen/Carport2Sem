-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema carport
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema carport
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `carport` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `carport` ;

-- -----------------------------------------------------
-- Table `carport`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `carport`.`user` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(16) NOT NULL,
  `role` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `carport`.`order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `carport`.`order` (
  `order_id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `orderdate` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`order_id`, `user_id`),
  INDEX `fk_order_user_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_order_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `carport`.`user` (`user_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `carport`.`carport`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `carport`.`carport` (
  `carport_id` INT NOT NULL AUTO_INCREMENT,
  `order_id` INT NOT NULL,
  `total` DOUBLE NULL DEFAULT NULL,
  `length` DOUBLE NOT NULL,
  `width` DOUBLE NOT NULL,
  PRIMARY KEY (`carport_id`),
  INDEX `fk_carport_order1_idx` (`order_id` ASC) VISIBLE,
  CONSTRAINT `fk_carport_order1`
    FOREIGN KEY (`order_id`)
    REFERENCES `carport`.`order` (`order_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `carport`.`material`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `carport`.`material` (
  `material_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `length` DOUBLE NULL DEFAULT NULL,
  `width` DOUBLE NULL DEFAULT NULL,
  `price` DOUBLE NOT NULL,
  PRIMARY KEY (`material_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `carport`.`carport_has_material_list`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `carport`.`carport_has_material_list` (
  `carport_id` INT NOT NULL,
  `material_id` INT NOT NULL,
  `quantity` FLOAT NOT NULL,
  PRIMARY KEY (`carport_id`, `material_id`),
  INDEX `fk_carport_has_material_list_carport1_idx` (`carport_id` ASC, `material_id` ASC) VISIBLE,
  INDEX `fk_carport_has_material_list_material1_idx` (`material_id` ASC) VISIBLE,
  CONSTRAINT `fk_carport_has_material_list_carport1`
    FOREIGN KEY (`carport_id`)
    REFERENCES `carport`.`carport` (`carport_id`),
  CONSTRAINT `fk_carport_has_material_list_material1`
    FOREIGN KEY (`material_id`)
    REFERENCES `carport`.`material` (`material_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `carport`.`material_category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `carport`.`material_category` (
  `material_category_id` INT NOT NULL AUTO_INCREMENT,
  `category_name` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`material_category_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `carport`.`material_has_material_category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `carport`.`material_has_material_category` (
  `material_id` INT NOT NULL,
  `material_category_id` INT NOT NULL,
  PRIMARY KEY (`material_id`, `material_category_id`),
  INDEX `fk_material_has_material_category_material_category1_idx` (`material_category_id` ASC) VISIBLE,
  CONSTRAINT `fk_material_has_material_category_material1`
    FOREIGN KEY (`material_id`)
    REFERENCES `carport`.`material` (`material_id`),
  CONSTRAINT `fk_material_has_material_category_material_category1`
    FOREIGN KEY (`material_category_id`)
    REFERENCES `carport`.`material_category` (`material_category_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `carport`.`shed`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `carport`.`shed` (
  `shed_id` INT NOT NULL AUTO_INCREMENT,
  `order_id` INT NOT NULL,
  `total` DOUBLE NULL DEFAULT NULL,
  `length` DOUBLE NULL DEFAULT NULL,
  `width` DOUBLE NULL DEFAULT NULL,
  PRIMARY KEY (`shed_id`),
  INDEX `fk_shed_order1_idx` (`order_id` ASC) VISIBLE,
  CONSTRAINT `fk_shed_order1`
    FOREIGN KEY (`order_id`)
    REFERENCES `carport`.`order` (`order_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `carport`.`shed_has_material_list`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `carport`.`shed_has_material_list` (
  `shed_id` INT NOT NULL,
  `material_id` INT NOT NULL,
  `quantity` FLOAT NOT NULL,
  PRIMARY KEY (`material_id`, `shed_id`),
  INDEX `fk_shed_has_material_list_material1_idx` (`material_id` ASC) VISIBLE,
  INDEX `fk_shed_has_material_list_shed1_idx` (`shed_id` ASC) VISIBLE,
  CONSTRAINT `fk_shed_has_material_list_material1`
    FOREIGN KEY (`material_id`)
    REFERENCES `carport`.`material` (`material_id`),
  CONSTRAINT `fk_shed_has_material_list_shed1`
    FOREIGN KEY (`shed_id`)
    REFERENCES `carport`.`shed` (`shed_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
