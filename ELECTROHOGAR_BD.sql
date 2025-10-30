CREATE DATABASE IF NOT EXISTS db_electrohogar;
USE db_electrohogar;

CREATE TABLE categoria (
    id_categoria BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    descripcion VARCHAR(150)
);

CREATE TABLE proveedor (
    id_proveedor BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    ruc VARCHAR(15),
    direccion VARCHAR(100),
    telefono VARCHAR(15),
    correo VARCHAR(100)
);

CREATE TABLE producto (
    id_producto BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    marca VARCHAR(50),
    precio DECIMAL(10,2),
    stock INT,
    descripcion VARCHAR(200),
    id_categoria BIGINT,
    FOREIGN KEY (id_categoria) REFERENCES categoria(id_categoria)
);

CREATE TABLE producto_proveedor (
    id_producto BIGINT,
    id_proveedor BIGINT,
    precio_compra DECIMAL(10,2),
    PRIMARY KEY (id_producto, id_proveedor),
    FOREIGN KEY (id_producto) REFERENCES producto(id_producto),
    FOREIGN KEY (id_proveedor) REFERENCES proveedor(id_proveedor)
);

CREATE TABLE cliente (
    id_cliente BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    dni VARCHAR(15),
    telefono VARCHAR(15),
    correo VARCHAR(100),
    direccion VARCHAR(150)
);

CREATE TABLE usuario (
    id_usuario BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre_usuario VARCHAR(50),
    password VARCHAR(100),
    rol ENUM('ADMIN', 'VENDEDOR')
);

CREATE TABLE venta (
    id_venta BIGINT AUTO_INCREMENT PRIMARY KEY,
    fecha DATETIME,
    total DECIMAL(10,2),
    id_cliente BIGINT,
    id_usuario BIGINT,
    FOREIGN KEY (id_cliente) REFERENCES cliente(id_cliente),
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario)
);

CREATE TABLE detalle_venta (
    id_detalle BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_venta BIGINT,
    id_producto BIGINT,
    cantidad INT,
    subtotal DECIMAL(10,2),
    FOREIGN KEY (id_venta) REFERENCES venta(id_venta),
    FOREIGN KEY (id_producto) REFERENCES producto(id_producto)
);

CREATE TABLE pago (
    id_pago BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_venta BIGINT,
    fecha DATETIME,
    monto DECIMAL(10,2),
    metodo VARCHAR(30),
    estado VARCHAR(20),
    FOREIGN KEY (id_venta) REFERENCES venta(id_venta)
);

CREATE TABLE notificacion (
    id_notificacion BIGINT AUTO_INCREMENT PRIMARY KEY,
    tipo VARCHAR(30),
    mensaje VARCHAR(200),
    fecha DATETIME,
    id_venta BIGINT,
    FOREIGN KEY (id_venta) REFERENCES venta(id_venta)
);

CREATE TABLE movimiento_stock (
    id_movimiento BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_producto BIGINT,
    tipo ENUM('ENTRADA', 'SALIDA'),
    cantidad INT,
    fecha DATETIME,
    referencia VARCHAR(20),
    FOREIGN KEY (id_producto) REFERENCES producto(id_producto)
);