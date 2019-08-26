CREATE DATABASE IF NOT EXISTS `p2test`;
USE `p2test`;

CREATE TABLE IF NOT EXISTS `categoria`
(
    `id`     int(10) unsigned NOT NULL AUTO_INCREMENT,
    `nombre` varchar(20)      NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `nombre` (`nombre`)
);

CREATE TABLE IF NOT EXISTS `producto`
(
    `id`          int(10) unsigned NOT NULL,
    `nombre`      varchar(20)      NOT NULL,
    `precio`      int(11)          NOT NULL,
    `idCategoria` int(10) unsigned NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `nombre` (`nombre`),
    KEY `FK_producto_categoria` (`idCategoria`),
    CONSTRAINT `FK_producto_categoria` FOREIGN KEY (`idCategoria`) REFERENCES `categoria` (`id`)
);


CREATE TABLE IF NOT EXISTS `venta`
(
    `id`         int(10) unsigned NOT NULL,
    `fecha_hora` timestamp        NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `vendido`
(
    `idVenta`    int(10) unsigned NOT NULL,
    `idProducto` int(10) unsigned NOT NULL,
    `precio`     int(11)          NOT NULL,
    PRIMARY KEY (`idVenta`, `idProducto`),
    KEY `FK_vendido_producto` (`idProducto`),
    CONSTRAINT `FK_vendido_producto` FOREIGN KEY (`idProducto`) REFERENCES `producto` (`id`),
    CONSTRAINT `FK_vendido_venta` FOREIGN KEY (`idVenta`) REFERENCES `venta` (`id`)
);