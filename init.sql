-- MySQL Workbench Forward Engineering Refactorizado para MySQL 9.x
-- Ajustado para esquema volcan_gas_db

SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0;
SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0;
SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema volcan_gas_db
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `volcan_gas_db`;
CREATE SCHEMA IF NOT EXISTS `volcan_gas_db`
	DEFAULT CHARACTER SET utf8mb4
	COLLATE utf8mb4_0900_ai_ci;

USE `volcan_gas_db`;

-- -----------------------------------------------------
-- Table `volcan_gas_db`.`comunas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `volcan_gas_db`.`comunas` (
	`id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
	`nombre` VARCHAR(100) NOT NULL,
	`region_id` INT UNSIGNED NOT NULL COMMENT 'Debe referenciar a una tabla regiones en el futuro',
	PRIMARY KEY (`id`)
) ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `volcan_gas_db`.`roles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `volcan_gas_db`.`roles` (
	`id` TINYINT UNSIGNED NOT NULL AUTO_INCREMENT,
	`nombre` VARCHAR(50) NOT NULL,
	PRIMARY KEY (`id`)
) ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `volcan_gas_db`.`usuarios`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `volcan_gas_db`.`usuarios` (
	`id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
	`rut` VARCHAR(12) NOT NULL,
	`nombre` VARCHAR(100) NOT NULL,
	`apellido` VARCHAR(100) NOT NULL,
	`email` VARCHAR(255) NOT NULL,
	`telefono` VARCHAR(20) NOT NULL,
	`contrasena` VARCHAR(255) NOT NULL COMMENT 'Largo preparado para Hashes como Bcrypt/Argon2',
	`direccion` VARCHAR(255) NOT NULL,
	`creado_en` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`comuna_id` INT UNSIGNED NOT NULL,
	`rol_id` TINYINT UNSIGNED NOT NULL,
	PRIMARY KEY (`id`),
	UNIQUE INDEX `uk_usuario_email` (`email` ASC) VISIBLE,
	UNIQUE INDEX `uk_usuario_rut` (`rut` ASC) VISIBLE,
	INDEX `fk_usuario_comuna_idx` (`comuna_id` ASC) VISIBLE,
	INDEX `fk_usuario_rol_idx` (`rol_id` ASC) VISIBLE,
	CONSTRAINT `fk_usuario_comuna`
		FOREIGN KEY (`comuna_id`) REFERENCES `volcan_gas_db`.`comunas` (`id`)
		ON DELETE RESTRICT ON UPDATE CASCADE,
	CONSTRAINT `fk_usuario_rol`
		FOREIGN KEY (`rol_id`) REFERENCES `volcan_gas_db`.`roles` (`id`)
		ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `volcan_gas_db`.`productos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `volcan_gas_db`.`productos` (
	`id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
	`nombre` VARCHAR(150) NOT NULL,
	`precio` DECIMAL(10, 2) NOT NULL,
	`capacidad` INT UNSIGNED NOT NULL,
	`descripcion` TEXT NOT NULL,
	`imagen` VARCHAR(255) NULL,
	PRIMARY KEY (`id`)
) ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `volcan_gas_db`.`ventas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `volcan_gas_db`.`ventas` (
	`id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
	`usuario_id` BIGINT UNSIGNED NOT NULL COMMENT 'El usuario se asocia a la venta cabecera',
	`total` DECIMAL(12, 2) NOT NULL,
	`fecha` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY (`id`),
	INDEX `fk_venta_usuario_idx` (`usuario_id` ASC) VISIBLE,
	CONSTRAINT `fk_venta_usuario`
		FOREIGN KEY (`usuario_id`) REFERENCES `volcan_gas_db`.`usuarios` (`id`)
		ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `volcan_gas_db`.`detalle_ventas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `volcan_gas_db`.`detalle_ventas` (
	`id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
	`venta_id` BIGINT UNSIGNED NOT NULL,
	`producto_id` BIGINT UNSIGNED NOT NULL,
	`cantidad` INT UNSIGNED NOT NULL,
	`precio_unitario` DECIMAL(10, 2) NOT NULL COMMENT 'Guarda el precio al momento de la compra',
	`subtotal` DECIMAL(12, 2) NOT NULL,
	PRIMARY KEY (`id`),
	INDEX `fk_detalle_venta_idx` (`venta_id` ASC) VISIBLE,
	INDEX `fk_detalle_producto_idx` (`producto_id` ASC) VISIBLE,
	CONSTRAINT `fk_detalle_venta`
		FOREIGN KEY (`venta_id`) REFERENCES `volcan_gas_db`.`ventas` (`id`)
		ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT `fk_detalle_producto`
		FOREIGN KEY (`producto_id`) REFERENCES `volcan_gas_db`.`productos` (`id`)
		ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `volcan_gas_db`.`pagos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `volcan_gas_db`.`pagos` (
	`id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
	`venta_id` BIGINT UNSIGNED NOT NULL,
	`tipo` VARCHAR(50) NOT NULL,
	`fecha_pago` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY (`id`),
	INDEX `fk_pago_venta_idx` (`venta_id` ASC) VISIBLE,
	CONSTRAINT `fk_pago_venta`
		FOREIGN KEY (`venta_id`) REFERENCES `volcan_gas_db`.`ventas` (`id`)
		ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB;

SET SQL_MODE = @OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS;