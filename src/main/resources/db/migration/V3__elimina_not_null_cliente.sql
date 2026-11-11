-- V3: Modificación para permitir NULL en columnas de relaciones pendientes

-- Tabla clientes
ALTER TABLE clientes
    MODIFY COLUMN comuna_id INT UNSIGNED NULL DEFAULT NULL,
    MODIFY COLUMN rol_id TINYINT UNSIGNED NULL DEFAULT NULL;

-- Tabla ventas
ALTER TABLE ventas
    MODIFY COLUMN cliente_id BIGINT UNSIGNED NULL DEFAULT NULL,
    MODIFY COLUMN producto_id BIGINT UNSIGNED NULL DEFAULT NULL;