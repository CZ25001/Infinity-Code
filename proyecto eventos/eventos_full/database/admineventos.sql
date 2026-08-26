-- MySQL dump 10.13  Distrib 8.0.46, for Win64 (x86_64)
--
-- Host: localhost    Database: AdminEventos
-- ------------------------------------------------------
-- Server version	8.0.46

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `configuracionanticipo`
--

DROP TABLE IF EXISTS `configuracionanticipo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `configuracionanticipo` (
  `IdConfiguracionAnticipo` int NOT NULL AUTO_INCREMENT,
  `Porcentaje` decimal(5,2) NOT NULL,
  `FechaActualizacion` datetime NOT NULL,
  `Activo` tinyint DEFAULT '1',
  PRIMARY KEY (`IdConfiguracionAnticipo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `configuracionanticipo`
--

LOCK TABLES `configuracionanticipo` WRITE;
/*!40000 ALTER TABLE `configuracionanticipo` DISABLE KEYS */;
/*!40000 ALTER TABLE `configuracionanticipo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detalleventa`
--

DROP TABLE IF EXISTS `detalleventa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `detalleventa` (
  `IdDetalleVenta` int NOT NULL AUTO_INCREMENT,
  `IdVenta` int NOT NULL,
  `Descripcion` varchar(200) NOT NULL,
  `Cantidad` int NOT NULL,
  `PrecioUnitario` decimal(10,2) NOT NULL,
  `Subtotal` decimal(10,2) NOT NULL,
  PRIMARY KEY (`IdDetalleVenta`),
  KEY `IdVenta` (`IdVenta`),
  CONSTRAINT `detalleventa_ibfk_1` FOREIGN KEY (`IdVenta`) REFERENCES `venta` (`IdVenta`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detalleventa`
--

LOCK TABLES `detalleventa` WRITE;
/*!40000 ALTER TABLE `detalleventa` DISABLE KEYS */;
/*!40000 ALTER TABLE `detalleventa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pago`
--

DROP TABLE IF EXISTS `pago`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pago` (
  `IdPago` int NOT NULL AUTO_INCREMENT,
  `IdReserva` int NOT NULL,
  `Monto` decimal(10,2) NOT NULL,
  `MetodoPago` varchar(20) NOT NULL,
  `Estado` varchar(20) DEFAULT 'PENDIENTE',
  `FechaPago` datetime NOT NULL,
  `NumeroComprobante` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`IdPago`),
  UNIQUE KEY `NumeroComprobante` (`NumeroComprobante`),
  KEY `IdReserva` (`IdReserva`),
  CONSTRAINT `pago_ibfk_1` FOREIGN KEY (`IdReserva`) REFERENCES `reserva` (`IdReserva`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pago`
--

LOCK TABLES `pago` WRITE;
/*!40000 ALTER TABLE `pago` DISABLE KEYS */;
/*!40000 ALTER TABLE `pago` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `paquete`
--

DROP TABLE IF EXISTS `paquete`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `paquete` (
  `IdPaquete` int NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(100) NOT NULL,
  `Descripcion` varchar(200) DEFAULT NULL,
  `Tipo` varchar(20) NOT NULL,
  `Precio` decimal(10,2) NOT NULL,
  `Activo` tinyint DEFAULT '1',
  PRIMARY KEY (`IdPaquete`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `paquete`
--

LOCK TABLES `paquete` WRITE;
/*!40000 ALTER TABLE `paquete` DISABLE KEYS */;
/*!40000 ALTER TABLE `paquete` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reserva`
--

DROP TABLE IF EXISTS `reserva`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reserva` (
  `IdReserva` int NOT NULL AUTO_INCREMENT,
  `IdUsuario` int NOT NULL,
  `IdSalon` int NOT NULL,
  `IdTipoEvento` int DEFAULT NULL,
  `FechaEvento` date NOT NULL,
  `FechaSolicitud` datetime NOT NULL,
  `CantidadInvitados` int NOT NULL,
  `CostoTotal` decimal(10,2) NOT NULL,
  `PorcentajeAnticipo` decimal(5,2) DEFAULT NULL,
  `Estado` varchar(20) DEFAULT 'PENDIENTE',
  PRIMARY KEY (`IdReserva`),
  KEY `IdUsuario` (`IdUsuario`),
  KEY `IdSalon` (`IdSalon`),
  KEY `IdTipoEvento` (`IdTipoEvento`),
  CONSTRAINT `reserva_ibfk_1` FOREIGN KEY (`IdUsuario`) REFERENCES `usuario` (`IdUsuario`),
  CONSTRAINT `reserva_ibfk_2` FOREIGN KEY (`IdSalon`) REFERENCES `salon` (`IdSalon`),
  CONSTRAINT `reserva_ibfk_3` FOREIGN KEY (`IdTipoEvento`) REFERENCES `tipoevento` (`IdTipoEvento`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reserva`
--

LOCK TABLES `reserva` WRITE;
/*!40000 ALTER TABLE `reserva` DISABLE KEYS */;
/*!40000 ALTER TABLE `reserva` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservapaquete`
--

DROP TABLE IF EXISTS `reservapaquete`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reservapaquete` (
  `IdReservaPaquete` int NOT NULL AUTO_INCREMENT,
  `IdReserva` int NOT NULL,
  `IdPaquete` int NOT NULL,
  `PrecioAplicado` decimal(10,2) NOT NULL,
  PRIMARY KEY (`IdReservaPaquete`),
  KEY `IdReserva` (`IdReserva`),
  KEY `IdPaquete` (`IdPaquete`),
  CONSTRAINT `reservapaquete_ibfk_1` FOREIGN KEY (`IdReserva`) REFERENCES `reserva` (`IdReserva`),
  CONSTRAINT `reservapaquete_ibfk_2` FOREIGN KEY (`IdPaquete`) REFERENCES `paquete` (`IdPaquete`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservapaquete`
--

LOCK TABLES `reservapaquete` WRITE;
/*!40000 ALTER TABLE `reservapaquete` DISABLE KEYS */;
/*!40000 ALTER TABLE `reservapaquete` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservaservicio`
--

DROP TABLE IF EXISTS `reservaservicio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reservaservicio` (
  `IdReservaServicio` int NOT NULL AUTO_INCREMENT,
  `IdReserva` int NOT NULL,
  `IdServicioAdicional` int NOT NULL,
  `PrecioAplicado` decimal(10,2) NOT NULL,
  PRIMARY KEY (`IdReservaServicio`),
  KEY `IdServicioAdicional` (`IdServicioAdicional`),
  CONSTRAINT `reservaservicio_ibfk_1` FOREIGN KEY (`IdServicioAdicional`) REFERENCES `servicioadicional` (`IdServicioAdicional`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservaservicio`
--

LOCK TABLES `reservaservicio` WRITE;
/*!40000 ALTER TABLE `reservaservicio` DISABLE KEYS */;
/*!40000 ALTER TABLE `reservaservicio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rol`
--

DROP TABLE IF EXISTS `rol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rol` (
  `IdRol` int NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(25) NOT NULL,
  PRIMARY KEY (`IdRol`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rol`
--

LOCK TABLES `rol` WRITE;
/*!40000 ALTER TABLE `rol` DISABLE KEYS */;
/*!40000 ALTER TABLE `rol` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `salon`
--

DROP TABLE IF EXISTS `salon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `salon` (
  `IdSalon` int NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(100) NOT NULL,
  `Descripcion` varchar(255) DEFAULT NULL,
  `Ubicacion` varchar(100) NOT NULL,
  `Capacidad` int NOT NULL,
  `Precio` decimal(10,2) NOT NULL,
  `Activo` tinyint DEFAULT '1',
  PRIMARY KEY (`IdSalon`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `salon`
--

LOCK TABLES `salon` WRITE;
/*!40000 ALTER TABLE `salon` DISABLE KEYS */;
/*!40000 ALTER TABLE `salon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `servicioadicional`
--

DROP TABLE IF EXISTS `servicioadicional`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `servicioadicional` (
  `IdServicioAdicional` int NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(100) NOT NULL,
  `Descripcion` varchar(200) DEFAULT NULL,
  `Precio` decimal(10,2) NOT NULL,
  `Activo` tinyint DEFAULT '1',
  PRIMARY KEY (`IdServicioAdicional`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `servicioadicional`
--

LOCK TABLES `servicioadicional` WRITE;
/*!40000 ALTER TABLE `servicioadicional` DISABLE KEYS */;
/*!40000 ALTER TABLE `servicioadicional` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipoevento`
--

DROP TABLE IF EXISTS `tipoevento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tipoevento` (
  `IdTipoEvento` int NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(60) NOT NULL,
  `Descripcion` varchar(200) DEFAULT NULL,
  `Activo` tinyint DEFAULT '1',
  PRIMARY KEY (`IdTipoEvento`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipoevento`
--

LOCK TABLES `tipoevento` WRITE;
/*!40000 ALTER TABLE `tipoevento` DISABLE KEYS */;
/*!40000 ALTER TABLE `tipoevento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `IdUsuario` int NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(50) NOT NULL,
  `Apellido` varchar(50) NOT NULL,
  `Email` varchar(150) NOT NULL,
  `Clave` varchar(255) NOT NULL,
  `Telefono` varchar(9) DEFAULT NULL,
  `IdRol` int NOT NULL,
  `Estado` tinyint DEFAULT '1',
  PRIMARY KEY (`IdUsuario`),
  UNIQUE KEY `Correo` (`Email`),
  KEY `IdRol` (`IdRol`),
  CONSTRAINT `usuario_ibfk_1` FOREIGN KEY (`IdRol`) REFERENCES `rol` (`IdRol`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `venta`
--

DROP TABLE IF EXISTS `venta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `venta` (
  `IdVenta` int NOT NULL AUTO_INCREMENT,
  `IdReserva` int NOT NULL,
  `FechaVenta` datetime DEFAULT CURRENT_TIMESTAMP,
  `Total` decimal(10,2) NOT NULL,
  `Estado` varchar(30) DEFAULT 'Pendiente',
  PRIMARY KEY (`IdVenta`),
  KEY `IdReserva` (`IdReserva`),
  CONSTRAINT `venta_ibfk_1` FOREIGN KEY (`IdReserva`) REFERENCES `reserva` (`IdReserva`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `venta`
--

LOCK TABLES `venta` WRITE;
/*!40000 ALTER TABLE `venta` DISABLE KEYS */;
/*!40000 ALTER TABLE `venta` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-08-23 22:26:58
