CREATE DATABASE IF NOT EXISTS volcan_gas_db
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_0900_ai_ci;

USE volcan_gas_db;

-- =============================================
-- PRODUCTO
-- =============================================

CREATE TABLE IF NOT EXISTS productos (
    producto_id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(150) NOT NULL,
    precio DECIMAL(10,2) NOT NULL,
    capacidad INT UNSIGNED NOT NULL,
    descripcion TEXT NOT NULL,
    imagen VARCHAR(255) NULL,

    PRIMARY KEY (producto_id)
) ENGINE = InnoDB;

-- =============================================
-- CLIENTE
-- =============================================

CREATE TABLE IF NOT EXISTS clientes (
    cliente_id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    rut VARCHAR(12) NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    email VARCHAR(255) NOT NULL,
    telefono VARCHAR(20) NOT NULL,
    contrasena VARCHAR(255) NOT NULL
        COMMENT 'Almacenar únicamente el hash de la contraseña',
    direccion VARCHAR(255) NOT NULL,
    creado_en TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    comuna_id INT UNSIGNED NOT NULL,
    rol_id TINYINT UNSIGNED NOT NULL,

    PRIMARY KEY (cliente_id),
    UNIQUE KEY uk_cliente_rut (rut),
    UNIQUE KEY uk_cliente_email (email)
) ENGINE = InnoDB;

-- =============================================
-- VENTA
-- =============================================

CREATE TABLE IF NOT EXISTS ventas (
    venta_id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    cliente_id BIGINT UNSIGNED NOT NULL,
    producto_id BIGINT UNSIGNED NOT NULL,
    cantidad INT UNSIGNED NOT NULL,
    precio_unitario DECIMAL(10,2) NOT NULL,
    subtotal DECIMAL(12,2) NOT NULL,
    total DECIMAL(12,2) NOT NULL,
    fecha TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    tipo_pago VARCHAR(50) NULL,
    fecha_pago TIMESTAMP NULL,

    PRIMARY KEY (venta_id)
) ENGINE = InnoDB;