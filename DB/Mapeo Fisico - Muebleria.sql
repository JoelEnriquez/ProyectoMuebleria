

-- -----------------------------------------------------
-- Schema ProyectoMuebleria
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS ProyectoMuebleria ;

-- -----------------------------------------------------
-- Schema ProyectoMuebleria
-- -----------------------------------------------------
CREATE SCHEMA ProyectoMuebleria ;
USE ProyectoMuebleria;

-- -----------------------------------------------------
-- Table Pieza
-- -----------------------------------------------------
CREATE TABLE Pieza (
  tipo VARCHAR(40) NOT NULL,
  cantidad_stock INT NOT NULL,
  PRIMARY KEY (tipo));

-- -----------------------------------------------------
-- Table Asignacion_Precio
-- -----------------------------------------------------
CREATE TABLE Asignacion_Precio (
  id INT NOT NULL AUTO_INCREMENT,
  precio DOUBLE NOT NULL,
  utilizada TINYINT NOT NULL,
  tipo_pieza VARCHAR(40) NOT NULL,
  PRIMARY KEY (id),
  INDEX fk_Asignacion_Precio_Pieza_idx (tipo_pieza ASC) VISIBLE,
  CONSTRAINT fk_Asignacion_Precio_Pieza
    FOREIGN KEY (tipo_pieza)
    REFERENCES Pieza (tipo)
    ON DELETE NO ACTION
    ON UPDATE CASCADE);



-- -----------------------------------------------------
-- Table Mueble
-- -----------------------------------------------------
CREATE TABLE Mueble (
  nombre VARCHAR(40) NOT NULL,
  precio DOUBLE NOT NULL,
  PRIMARY KEY (nombre));


-- -----------------------------------------------------
-- Table Ensamble_Pieza
-- -----------------------------------------------------
CREATE TABLE Ensamble_Pieza (
  nombre_mueble VARCHAR(40) NOT NULL,
  tipo_pieza VARCHAR(40) NOT NULL,
  cantidad_pieza INT NOT NULL,
  PRIMARY KEY (nombre_mueble, tipo_pieza),
  INDEX fk_Ensamble_Pieza_Mueble1_idx (nombre_mueble ASC) VISIBLE,
  CONSTRAINT fk_Ensamble_Pieza_Pieza1
    FOREIGN KEY (tipo_pieza)
    REFERENCES Pieza (tipo)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT fk_Ensamble_Pieza_Mueble1
    FOREIGN KEY (nombre_mueble)
    REFERENCES Mueble (nombre)
    ON DELETE NO ACTION
    ON UPDATE CASCADE);



-- -----------------------------------------------------
-- Table Usuario
-- -----------------------------------------------------
CREATE TABLE  Usuario (
  nombre VARCHAR(50) NOT NULL,
  tipo INT NOT NULL,
  password VARCHAR(45) NOT NULL,
  revocado TINYINT NULL,
  PRIMARY KEY (nombre));



-- -----------------------------------------------------
-- Table Ensamble_Mueble
-- -----------------------------------------------------
CREATE TABLE Ensamble_Mueble (
  id INT NOT NULL AUTO_INCREMENT,
  fecha_ensamble DATE NOT NULL,
  precio_ensamble DOUBLE NOT NULL,
  nombre_usuario VARCHAR(50) NOT NULL,
  nombre_mueble VARCHAR(40) NOT NULL,
  PRIMARY KEY (id),
  INDEX fk_Ensamble_Mueble_Usuario1_idx (nombre_usuario ASC) VISIBLE,
  INDEX fk_Ensamble_Mueble_Mueble1_idx (nombre_mueble ASC) VISIBLE,
  CONSTRAINT fk_Ensamble_Mueble_Usuario1
    FOREIGN KEY (nombre_usuario)
    REFERENCES Usuario (nombre)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT fk_Ensamble_Mueble_Mueble1
    FOREIGN KEY (nombre_mueble)
    REFERENCES Mueble (nombre)
    ON DELETE NO ACTION
    ON UPDATE CASCADE);



-- -----------------------------------------------------
-- Table Cliente
-- -----------------------------------------------------
CREATE TABLE Cliente (
  NIT VARCHAR(15) NOT NULL,
  nombre VARCHAR(50) NOT NULL,
  direccion VARCHAR(60) NOT NULL,
  municipio VARCHAR(30) NULL,
  departamento VARCHAR(30) NULL,
  PRIMARY KEY (NIT));



-- -----------------------------------------------------
-- Table Factura
-- -----------------------------------------------------
CREATE TABLE Factura (
  id INT NOT NULL AUTO_INCREMENT,
  fecha_compra DATE NOT NULL,
  precio_compra DOUBLE NOT NULL,
  NIT_cliente VARCHAR(15) NOT NULL,
  nombre_usuario VARCHAR(50) NOT NULL,
  INDEX fk_Factura_Cliente1_idx (NIT_cliente ASC) VISIBLE,
  PRIMARY KEY (id),
  INDEX fk_Factura_Usuario1_idx (nombre_usuario ASC) VISIBLE,
  CONSTRAINT fk_Factura_Cliente1
    FOREIGN KEY (NIT_cliente)
    REFERENCES Cliente (NIT)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_Factura_Usuario1
    FOREIGN KEY (nombre_usuario)
    REFERENCES Usuario (nombre)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);



-- -----------------------------------------------------
-- Table Detalle_Compra
-- -----------------------------------------------------
CREATE TABLE Detalle_Compra (
  id_ensamble INT NOT NULL,
  precio DOUBLE NOT NULL,
  devolucion TINYINT NOT NULL,
  fecha_devolucion DATE NULL,
  reutilizacion_piezas TINYINT NOT NULL,
  id_factura INT NOT NULL,
  INDEX fk_Detalle_Compra_Factura1_idx (id_factura ASC) VISIBLE,
  PRIMARY KEY (id_ensamble),
  CONSTRAINT fk_Detalle_Compra_Factura1
    FOREIGN KEY (id_factura)
    REFERENCES Factura (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_Detalle_Compra_Ensamble_Mueble1
    FOREIGN KEY (id_ensamble)
    REFERENCES Ensamble_Mueble (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

-- ---------------------------------------------
-- CREACION DEL USUARIO ADMIN
-- --------------------------------------------
DROP USER IF EXISTS 'admin_muebleria'@'localhost';
CREATE USER 'admin_muebleria'@'localhost' identified by 'Proyecto_Muebleria_2021';
GRANT ALL PRIVILEGES ON ProyectoMuebleria.* TO admin_muebleria@localhost;
FLUSH PRIVILEGES;


