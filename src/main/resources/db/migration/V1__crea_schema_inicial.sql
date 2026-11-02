
-- =====================================================
-- V1: Creación del esquema inicial
-- Proyecto: Distribuidora de Gas El Volcán
-- Motor: MySQL 9.x
-- Administrador de migraciones: Flyway
-- =====================================================

-- =====================================================
-- 1. COMUNAS
-- Tabla independiente: no tiene claves foráneas.
-- =====================================================

CREATE TABLE comunas (
	id INT UNSIGNED NOT NULL AUTO_INCREMENT,
	nombre VARCHAR(100) NOT NULL,
	region_id INT UNSIGNED NOT NULL
		COMMENT 'Pendiente de relacionar con la tabla regiones',
	PRIMARY KEY (id)
) ENGINE = InnoDB
	DEFAULT CHARSET = utf8mb4
	COLLATE = utf8mb4_0900_ai_ci;


-- =====================================================
-- 2. ROLES
-- Tabla independiente: no tiene claves foráneas.
-- =====================================================

CREATE TABLE roles (
	id TINYINT UNSIGNED NOT NULL AUTO_INCREMENT,
	nombre VARCHAR(50) NOT NULL,
	PRIMARY KEY (id)
) ENGINE = InnoDB
	DEFAULT CHARSET = utf8mb4
	COLLATE = utf8mb4_0900_ai_ci;


-- =====================================================
-- 3. PRODUCTOS
-- Tabla independiente: no tiene claves foráneas.
-- =====================================================

CREATE TABLE productos (
	id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
	nombre VARCHAR(150) NOT NULL,
	precio DECIMAL(10, 2) NOT NULL,
	capacidad INT UNSIGNED NOT NULL,
	descripcion TEXT NOT NULL,
	imagen VARCHAR(255) NULL,
	PRIMARY KEY (id)
) ENGINE = InnoDB
	DEFAULT CHARSET = utf8mb4
	COLLATE = utf8mb4_0900_ai_ci;


-- =====================================================
-- 4. USUARIOS
-- Depende de: comunas, roles.
-- =====================================================

CREATE TABLE usuarios (
	id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
	rut VARCHAR(12) NOT NULL,
	nombre VARCHAR(100) NOT NULL,
	apellido VARCHAR(100) NOT NULL,
	email VARCHAR(255) NOT NULL,
	telefono VARCHAR(20) NOT NULL,
	contrasena VARCHAR(255) NOT NULL
		COMMENT 'Espacio preparado para hashes BCrypt/Argon2',
	direccion VARCHAR(255) NOT NULL,
	creado_en TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	comuna_id INT UNSIGNED NOT NULL,
	rol_id TINYINT UNSIGNED NOT NULL,
	PRIMARY KEY (id),
	-- Restricciones de unicidad
	UNIQUE KEY uk_usuario_email (email),
	UNIQUE KEY uk_usuario_rut (rut),
	-- Índices para claves foráneas
	KEY fk_usuario_comuna_idx (comuna_id),
	KEY fk_usuario_rol_idx (rol_id),
	-- Relaciones
	CONSTRAINT fk_usuario_comuna
		FOREIGN KEY (comuna_id)
		REFERENCES comunas (id)
		ON DELETE RESTRICT
		ON UPDATE CASCADE,
	CONSTRAINT fk_usuario_rol
		FOREIGN KEY (rol_id)
		REFERENCES roles (id)
		ON DELETE RESTRICT
		ON UPDATE CASCADE
) ENGINE = InnoDB
	DEFAULT CHARSET = utf8mb4
	COLLATE = utf8mb4_0900_ai_ci;


-- =====================================================
-- 5. VENTAS
-- Depende de: usuarios.
-- =====================================================

CREATE TABLE ventas (
	id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
	usuario_id BIGINT UNSIGNED NOT NULL,
	total DECIMAL(12, 2) NOT NULL,
	fecha TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY (id),
	-- Índice para clave foránea
	KEY fk_venta_usuario_idx (usuario_id),
	-- Relación
	CONSTRAINT fk_venta_usuario
		FOREIGN KEY (usuario_id)
		REFERENCES usuarios (id)
		ON DELETE RESTRICT
		ON UPDATE CASCADE
) ENGINE = InnoDB
	DEFAULT CHARSET = utf8mb4
	COLLATE = utf8mb4_0900_ai_ci;


-- =====================================================
-- 6. DETALLE DE VENTAS
-- Depende de: ventas, productos.
-- =====================================================

CREATE TABLE detalle_ventas (
	id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
	venta_id BIGINT UNSIGNED NOT NULL,
	producto_id BIGINT UNSIGNED NOT NULL,
	cantidad INT UNSIGNED NOT NULL,
	precio_unitario DECIMAL(10, 2) NOT NULL
		COMMENT 'Precio del producto al momento de la compra',
	subtotal DECIMAL(12, 2) NOT NULL,
	PRIMARY KEY (id),
	-- Índices para claves foráneas
	KEY fk_detalle_venta_idx (venta_id),
	KEY fk_detalle_producto_idx (producto_id),
	-- Relaciones
	CONSTRAINT fk_detalle_venta
		FOREIGN KEY (venta_id)
		REFERENCES ventas (id)
		ON DELETE CASCADE
		ON UPDATE CASCADE,
	CONSTRAINT fk_detalle_producto
		FOREIGN KEY (producto_id)
		REFERENCES productos (id)
		ON DELETE RESTRICT
		ON UPDATE CASCADE
) ENGINE = InnoDB
	DEFAULT CHARSET = utf8mb4
	COLLATE = utf8mb4_0900_ai_ci;


-- =====================================================
-- 7. PAGOS
-- Depende de: ventas.
-- =====================================================

CREATE TABLE pagos (
	id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
	venta_id BIGINT UNSIGNED NOT NULL,
	tipo VARCHAR(50) NOT NULL,
	fecha_pago TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY (id),
	-- Índice para clave foránea
	KEY fk_pago_venta_idx (venta_id),
	-- Relación
	CONSTRAINT fk_pago_venta
		FOREIGN KEY (venta_id)
		REFERENCES ventas (id)
		ON DELETE CASCADE
		ON UPDATE CASCADE
) ENGINE = InnoDB
	DEFAULT CHARSET = utf8mb4
	COLLATE = utf8mb4_0900_ai_ci;


-- =====================================================
-- FIN DE LA MIGRACIÓN V1
-- =====================================================
