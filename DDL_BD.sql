    PRODUCTOS

CREATE TABLE PRODUCTOS(
    idProducto INT NOT NULL PRIMARY KEY,
    descripcion VARCHAR(50) NOT NULL,
    stockAnual INT NOT NULL,
    pvp INT NOT NULL
);

    CLIENTES

CREATE TABLE CLIENTES(
    idCliente INT NOT NULL PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    direccion VARCHAR(50) NOT NULL, 
    poblacion VARCHAR(50) NOT NULL,
    telefono VARCHAR(20) NOT NULL,
    nif VARCHAR(10) NOT NULL
);

    VENTAS

CREATE TABLE VENTAS(
    idVenta INT NOT NULL PRIMARY KEY,
    fechaVenta DATE NOT NULL,
    idCliente INT,
    idProducto INT,
    cantidad INT,
    FOREIGN KEY (idCliente) REFERENCES clientes(idCliente),
    FOREIGN KEY (idProducto) REFERENCES productos(idProducto)
);