-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema payments_v_01
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema payments_v_01
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `payments_v_01`;
CREATE SCHEMA IF NOT EXISTS `payments_v_01` DEFAULT CHARACTER SET utf8 ;
USE `payments_v_01` ;

-- -----------------------------------------------------
-- Table `payments_v_01`.`user_status`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `payments_v_01`.`user_status` (
  `id` INT NOT NULL,
  `status` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `status_UNIQUE` (`status` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `payments_v_01`.`role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `payments_v_01`.`role` (
  `id` INT NOT NULL,
  `name` VARCHAR(15) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `payments_v_01`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `payments_v_01`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `first_name` VARCHAR(20) NOT NULL,
  `second_name` VARCHAR(20) NOT NULL,
  `email` VARCHAR(45) NULL,
  `tel` VARCHAR(13) NULL,
  `user_status_id` INT NOT NULL,
  `role_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `login_UNIQUE` (`login` ASC) VISIBLE,
  UNIQUE INDEX `password_UNIQUE` (`password` ASC) VISIBLE,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
  UNIQUE INDEX `tel_UNIQUE` (`tel` ASC) VISIBLE,
  UNIQUE INDEX `user_id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_user_user_status1_idx` (`user_status_id` ASC) VISIBLE,
  INDEX `fk_user_role1_idx` (`role_id` ASC) VISIBLE,
  CONSTRAINT `fk_user_user_status1`
    FOREIGN KEY (`user_status_id`)
    REFERENCES `payments_v_01`.`user_status` (`id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `fk_user_role1`
    FOREIGN KEY (`role_id`)
    REFERENCES `payments_v_01`.`role` (`id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `payments_v_01`.`account_status`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `payments_v_01`.`account_status` (
  `id` INT NOT NULL,
  `status` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `status_UNIQUE` (`status` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `payments_v_01`.`account`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `payments_v_01`.`account` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `sum` DECIMAL(14,2) NOT NULL,
  `credit_limit` DECIMAL(14,2) NOT NULL,
  `account_status_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `account_id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_account_account_status1_idx` (`account_status_id` ASC) VISIBLE,
  INDEX `fk_account_user1_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_account_account_status1`
    FOREIGN KEY (`account_status_id`)
    REFERENCES `payments_v_01`.`account_status` (`id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `fk_account_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `payments_v_01`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `payments_v_01`.`payment_status`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `payments_v_01`.`payment_status` (
  `id` INT NOT NULL,
  `status_description` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `status_description_UNIQUE` (`status_description` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `payments_v_01`.`payment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `payments_v_01`.`payment` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `recipient_account` INT UNSIGNED NOT NULL,
  `recipient_card_number` BIGINT(16) UNSIGNED NULL,
  `sum` DECIMAL(14,2) UNSIGNED NOT NULL,
  `date` DATETIME NOT NULL,
  `payment_description` VARCHAR(45) NULL,
  `payment_status_id` INT NOT NULL,
  `account_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `payment_id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_payment_payment_status1_idx` (`payment_status_id` ASC) VISIBLE,
  INDEX `fk_payment_account1_idx` (`account_id` ASC) VISIBLE,
  CONSTRAINT `fk_payment_payment_status1`
    FOREIGN KEY (`payment_status_id`)
    REFERENCES `payments_v_01`.`payment_status` (`id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `fk_payment_account1`
    FOREIGN KEY (`account_id`)
    REFERENCES `payments_v_01`.`account` (`id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `payments_v_01`.`card`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `payments_v_01`.`card` (
  `card_number` BIGINT(16) UNSIGNED NOT NULL,
  `due_date` VARCHAR(5) NOT NULL,
  `cvv` INT UNSIGNED NOT NULL,
  `card_name` VARCHAR(25) NOT NULL,
  `account_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`card_number`),
  UNIQUE INDEX `card_number_UNIQUE` (`card_number` ASC) VISIBLE,
  INDEX `fk_card_account1_idx` (`account_id` ASC) VISIBLE,
  CONSTRAINT `fk_card_account1`
    FOREIGN KEY (`account_id`)
    REFERENCES `payments_v_01`.`account` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

insert into `role` values (0, 'admin');
insert into `role` values (1, 'user');

insert into user_status values (0, 'active');
insert into user_status values (1, 'blocked');

insert into account_status values(0, 'active');
insert into account_status values(1, 'blocked');

insert into payment_status values(0,'prepared');
insert into payment_status values(1,'sent');

insert into `user`(id, login, `password`, first_name, second_name, email, tel, user_status_id, role_id) 
values(1, 'admin','admin', 'Bohdan', 'Shostak', 'bohdanshostak93@gmail.com', '+380639704925', 0, 0);
insert into `user`(id, login, `password`, first_name, second_name, email, tel, user_status_id, role_id) 
values(2, 'user_01','user_01', 'Ivan', 'Karpan', 'user_01@gmail.com', '+380997416521', 0, 1);
insert into `user`(id, login, `password`, first_name, second_name, email, tel, user_status_id,  role_id)
values(3, 'user_02','user_02', 'Yana', 'Demyan', 'user_02@gmail.com', '+380985216522', 0, 1);
insert into `user`(id, login, `password`, first_name, second_name, email, tel, user_status_id, role_id) 
values(4, 'user_03','user_03', 'Igor', 'Perig', 'user_03@gmail.com', '+380964716452', 0, 1);
insert into `user`(id, login, `password`, first_name, second_name, email, tel, user_status_id, role_id) 
values(5, 'user_04','user_04', 'Lida', 'Taran', 'user_04@gmail.com', '+380999614527', 0, 1);

insert into `account`(id, sum, user_id, credit_limit, account_status_id) values(1, 53547.00, 1, 100000.00, 0);
insert into `account`(id, sum, user_id, credit_limit, account_status_id) values(2, 1200.00, 2, 5000.00, 0);
insert into `account`(id, sum, user_id, credit_limit, account_status_id) values(3, 0.00, 3, 0.00, 0);
insert into `account`(id, sum, user_id, credit_limit, account_status_id) values(4, 22000.00, 4, 20000.00, 0);
insert into `account`(id, sum, user_id, credit_limit, account_status_id) values(5, 13500.55, 5, 10000.00, 1);
insert into `account`(id, sum, user_id, credit_limit, account_status_id) values(6, 45598.41, 5, 50000.00, 0);

insert into card(card_number, due_date, cvv, card_name, account_id) values (1234567890990001, '07/22', 123, 'admin vip gold card', 1);
insert into card(card_number, due_date, cvv, card_name, account_id) values (1234567890990002, '10/25', 958, 'credit card', 2);
insert into card(card_number, due_date, cvv, card_name, account_id) values (1234567890990003, '03/24', 412, 'students credit card', 3);
insert into card(card_number, due_date, cvv, card_name, account_id) values (1234567890990004, '02/22', 951, 'card for payments', 4);
insert into card(card_number, due_date, cvv, card_name, account_id) values (1234567890990005, '11/24', 357, 'universal card', 5);
insert into card(card_number, due_date, cvv, card_name, account_id) values (1234567890990006, '09/23', 852, 'platinum credit card', 6);

insert into payment(id, recipient_account, recipient_card_number, sum, `date`, payment_description, account_id, payment_status_id) 
values(1, 3581318, 1234567890995874, 2000.00, '2021-03-22 14:25:08', 'salary', 1, 1);
insert into payment(id, recipient_account, recipient_card_number, sum, `date`, payment_description, account_id, payment_status_id) 
values(2, 4138414, 1234567890995214, 5214.00, '2020-06-02 15:21:41', 'money', 2, 1);
insert into payment(id, recipient_account, recipient_card_number, sum, `date`, payment_description, account_id, payment_status_id) 
values(3, 6186181, 1234567890995147, 2587.00, '2021-10-15 08:48:44', 'with love from user', 3, 1);
insert into payment(id, recipient_account, recipient_card_number, sum, `date`, payment_description, account_id, payment_status_id) 
values(4, 5478521, 1234567890995478, 9999.50, '2019-01-23 10:15:52', 'hhh', 4, 1);
insert into payment(id, recipient_account, recipient_card_number, sum, `date`, payment_description, account_id, payment_status_id) 
values(5, 9621478, 1234567890996325, 1245.25, '2021-05-06 16:35:04', 'salary', 5, 0);


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
