-- --------------------------------------------------------
-- Servidor:                     127.0.0.1
-- Versão do servidor:           6.0.0-alpha-community-nt-debug - MySQL Community Server (GPL)
-- OS do Servidor:               Win32
-- HeidiSQL Versão:              10.3.0.5771
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Copiando estrutura do banco de dados para clinica
CREATE DATABASE IF NOT EXISTS `clinica` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `clinica`;

-- Copiando estrutura para tabela clinica.tbmedicos
CREATE TABLE IF NOT EXISTS `tbmedicos` (
  `idMedico` int(11) NOT NULL AUTO_INCREMENT,
  `NomeMedico` varchar(50) DEFAULT NULL,
  `EspecialidadeMedico` varchar(100) DEFAULT NULL,
  `Crm` varchar(15) DEFAULT NULL,
  `Uf` varchar(20) DEFAULT NULL,
  `EnderecoMedico` varchar(150) DEFAULT NULL,
  `TelefoneMedico` varchar(50) DEFAULT NULL,
  `CidadeMedico` varchar(50) DEFAULT NULL,
  `UfMedico` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`idMedico`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

-- Copiando dados para a tabela clinica.tbmedicos: ~1 rows (aproximadamente)
/*!40000 ALTER TABLE `tbmedicos` DISABLE KEYS */;
INSERT INTO `tbmedicos` (`idMedico`, `NomeMedico`, `EspecialidadeMedico`, `Crm`, `Uf`, `EnderecoMedico`, `TelefoneMedico`, `CidadeMedico`, `UfMedico`) VALUES
	(1, 'JOÃO DA SILVA', '103 Cirurgia Geral', '2222', 'GO', 'AV. IRÁ, 801, CENTRO ', '(62)99211-9212', 'GOIANIA', 'GO');
/*!40000 ALTER TABLE `tbmedicos` ENABLE KEYS */;

-- Copiando estrutura para tabela clinica.tbpaciente
CREATE TABLE IF NOT EXISTS `tbpaciente` (
  `IdPaciente` int(11) NOT NULL AUTO_INCREMENT,
  `DataCadastro` date DEFAULT NULL,
  `NomePaciente` varchar(100) DEFAULT NULL,
  `documentoPaciente` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`IdPaciente`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

-- Copiando dados para a tabela clinica.tbpaciente: ~1 rows (aproximadamente)
/*!40000 ALTER TABLE `tbpaciente` DISABLE KEYS */;
INSERT INTO `tbpaciente` (`IdPaciente`, `DataCadastro`, `NomePaciente`, `documentoPaciente`) VALUES
	(2, '2020-07-06', 'ADRIANO ZANETTE', '813.887.809-68');
/*!40000 ALTER TABLE `tbpaciente` ENABLE KEYS */;

-- Copiando estrutura para tabela clinica.tb_conforme
CREATE TABLE IF NOT EXISTS `tb_conforme` (
  `codigo` int(20) NOT NULL AUTO_INCREMENT,
  `DataCadastro` date NOT NULL DEFAULT '0000-00-00',
  `Conforme` varchar(100) NOT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

-- Copiando dados para a tabela clinica.tb_conforme: ~1 rows (aproximadamente)
/*!40000 ALTER TABLE `tb_conforme` DISABLE KEYS */;
INSERT INTO `tb_conforme` (`codigo`, `DataCadastro`, `Conforme`) VALUES
	(1, '2020-06-28', 'Conforme Orientação Médica');
/*!40000 ALTER TABLE `tb_conforme` ENABLE KEYS */;

-- Copiando estrutura para tabela clinica.tb_convenios
CREATE TABLE IF NOT EXISTS `tb_convenios` (
  `codigo` int(20) NOT NULL AUTO_INCREMENT,
  `DataCadastro` varchar(50) NOT NULL DEFAULT '0000-00-00',
  `tipoConvenio` varchar(100) NOT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

-- Copiando dados para a tabela clinica.tb_convenios: ~2 rows (aproximadamente)
/*!40000 ALTER TABLE `tb_convenios` DISABLE KEYS */;
INSERT INTO `tb_convenios` (`codigo`, `DataCadastro`, `tipoConvenio`) VALUES
	(1, '29/06/2020', 'UNIMED'),
	(2, '29/06/2020', 'IPASGO');
/*!40000 ALTER TABLE `tb_convenios` ENABLE KEYS */;

-- Copiando estrutura para tabela clinica.tb_intervalo_medicamentos
CREATE TABLE IF NOT EXISTS `tb_intervalo_medicamentos` (
  `codigo` int(20) NOT NULL AUTO_INCREMENT,
  `DataCadastro` date NOT NULL DEFAULT '0000-00-00',
  `Intervalo` varchar(100) NOT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

-- Copiando dados para a tabela clinica.tb_intervalo_medicamentos: ~3 rows (aproximadamente)
/*!40000 ALTER TABLE `tb_intervalo_medicamentos` DISABLE KEYS */;
INSERT INTO `tb_intervalo_medicamentos` (`codigo`, `DataCadastro`, `Intervalo`) VALUES
	(1, '2020-05-25', '6 / 6hs'),
	(2, '2020-05-25', '12 / 12hs'),
	(3, '2020-05-25', '8 / 8hs');
/*!40000 ALTER TABLE `tb_intervalo_medicamentos` ENABLE KEYS */;

-- Copiando estrutura para tabela clinica.tb_medicacao
CREATE TABLE IF NOT EXISTS `tb_medicacao` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `NomeMedicacao` varchar(100) NOT NULL DEFAULT '0',
  `ViaAcesso` varchar(50) NOT NULL DEFAULT '0',
  `ComoTomar` varchar(50) NOT NULL DEFAULT '0',
  `Quantidade` varchar(50) NOT NULL DEFAULT '0',
  `Tipo` varchar(50) NOT NULL DEFAULT '0',
  `VesesDia` varchar(50) NOT NULL DEFAULT '0',
  `Durante` varchar(50) NOT NULL DEFAULT '0',
  `DiasDuracao` varchar(50) NOT NULL DEFAULT '0',
  `Periodo` varchar(50) NOT NULL DEFAULT '0',
  `Intervalo` varchar(50) NOT NULL DEFAULT '0',
  `Orientacao` varchar(50) NOT NULL DEFAULT '0',
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

-- Copiando dados para a tabela clinica.tb_medicacao: ~1 rows (aproximadamente)
/*!40000 ALTER TABLE `tb_medicacao` DISABLE KEYS */;
INSERT INTO `tb_medicacao` (`codigo`, `NomeMedicacao`, `ViaAcesso`, `ComoTomar`, `Quantidade`, `Tipo`, `VesesDia`, `Durante`, `DiasDuracao`, `Periodo`, `Intervalo`, `Orientacao`) VALUES
	(1, 'Domperidona 10 mg - 60 comprimidos ', 'ORAL', 'Tomar', '1', 'Comprimido(s)', '1 vez ao dia', 'Durante', '8', 'Dia(s)', '6 / 6hs', 'Conforme Orientação Médica');
/*!40000 ALTER TABLE `tb_medicacao` ENABLE KEYS */;

-- Copiando estrutura para tabela clinica.tb_modo_tomar
CREATE TABLE IF NOT EXISTS `tb_modo_tomar` (
  `codigo` int(20) NOT NULL AUTO_INCREMENT,
  `DataCadastro` date NOT NULL DEFAULT '0000-00-00',
  `Tomar` varchar(100) NOT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

-- Copiando dados para a tabela clinica.tb_modo_tomar: ~4 rows (aproximadamente)
/*!40000 ALTER TABLE `tb_modo_tomar` DISABLE KEYS */;
INSERT INTO `tb_modo_tomar` (`codigo`, `DataCadastro`, `Tomar`) VALUES
	(1, '2020-06-28', 'Tomar'),
	(2, '2020-06-28', 'Administrar'),
	(3, '2020-06-28', 'Ingerir'),
	(4, '2020-06-28', 'dedeeeee');
/*!40000 ALTER TABLE `tb_modo_tomar` ENABLE KEYS */;

-- Copiando estrutura para tabela clinica.tb_tipo
CREATE TABLE IF NOT EXISTS `tb_tipo` (
  `codigo` int(20) NOT NULL AUTO_INCREMENT,
  `DataCadastro` date NOT NULL DEFAULT '0000-00-00',
  `Tipo` varchar(100) NOT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

-- Copiando dados para a tabela clinica.tb_tipo: ~21 rows (aproximadamente)
/*!40000 ALTER TABLE `tb_tipo` DISABLE KEYS */;
INSERT INTO `tb_tipo` (`codigo`, `DataCadastro`, `Tipo`) VALUES
	(1, '2020-06-28', 'Ampola'),
	(2, '2020-06-28', 'Bolsa'),
	(3, '2020-06-28', 'Caixa(s)'),
	(4, '2020-06-28', 'Capsulas'),
	(5, '2020-06-28', 'Comprimido(s)'),
	(6, '2020-06-28', 'Envelope'),
	(7, '2020-06-28', 'Frasco'),
	(8, '2020-06-28', 'Gotas'),
	(9, '2020-06-28', ' Inalação'),
	(10, '2020-06-28', ' Liquido'),
	(11, '2020-06-28', ' Loção'),
	(12, '2020-06-28', ' Sachês'),
	(13, '2020-06-28', 'Spray'),
	(14, '2020-06-28', 'Suspensão'),
	(15, '2020-06-28', ' Tópico'),
	(16, '2020-06-28', 'Unidade'),
	(17, '2020-06-28', ' Xarope'),
	(18, '2020-06-28', 'Soro 100'),
	(19, '2020-06-28', 'Soro 250'),
	(20, '2020-06-28', 'Soro 500'),
	(21, '2020-06-28', 'Soro 1000');
/*!40000 ALTER TABLE `tb_tipo` ENABLE KEYS */;

-- Copiando estrutura para tabela clinica.tb_tipo_exame
CREATE TABLE IF NOT EXISTS `tb_tipo_exame` (
  `codigo` int(20) NOT NULL AUTO_INCREMENT,
  `DataCadastro` date NOT NULL DEFAULT '0000-00-00',
  `tipoExame` varchar(100) NOT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

-- Copiando dados para a tabela clinica.tb_tipo_exame: ~13 rows (aproximadamente)
/*!40000 ALTER TABLE `tb_tipo_exame` DISABLE KEYS */;
INSERT INTO `tb_tipo_exame` (`codigo`, `DataCadastro`, `tipoExame`) VALUES
	(1, '2020-07-06', 'HEMOGRAMA'),
	(2, '2020-07-06', 'PCR'),
	(3, '2020-07-06', 'VHS'),
	(4, '2020-07-06', 'FERRITINA'),
	(5, '2020-07-06', 'ASLO'),
	(6, '2020-07-06', 'EAS'),
	(7, '2020-07-06', 'UROCULTURA'),
	(8, '2020-07-06', 'CREATININA'),
	(9, '2020-07-06', 'SÓDIO'),
	(10, '2020-07-06', 'POTÁSSIO'),
	(11, '2020-07-06', 'CÁLCIO'),
	(12, '2020-07-06', 'TGP'),
	(13, '2020-07-06', 'TGO');
/*!40000 ALTER TABLE `tb_tipo_exame` ENABLE KEYS */;

-- Copiando estrutura para tabela clinica.tb_tipo_exame_radiologico
CREATE TABLE IF NOT EXISTS `tb_tipo_exame_radiologico` (
  `codigo` int(20) NOT NULL AUTO_INCREMENT,
  `DataCadastro` date NOT NULL DEFAULT '0000-00-00',
  `tipoExame` varchar(100) NOT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

-- Copiando dados para a tabela clinica.tb_tipo_exame_radiologico: ~7 rows (aproximadamente)
/*!40000 ALTER TABLE `tb_tipo_exame_radiologico` DISABLE KEYS */;
INSERT INTO `tb_tipo_exame_radiologico` (`codigo`, `DataCadastro`, `tipoExame`) VALUES
	(1, '2020-07-06', 'TC TORAX'),
	(2, '2020-07-06', 'TC ABDOMEN TOTAL'),
	(3, '2020-07-06', 'TC CRÂNIO'),
	(4, '2020-07-06', 'RESSONANCIA MÁGNETICA COLUNA LOMBAR'),
	(5, '2020-07-06', 'RESSONANCIA MÁGNETICA JOELHO'),
	(6, '2020-07-06', 'ULTRASSON '),
	(7, '2020-07-07', 'RAIO X');
/*!40000 ALTER TABLE `tb_tipo_exame_radiologico` ENABLE KEYS */;

-- Copiando estrutura para tabela clinica.tb_usuarios
CREATE TABLE IF NOT EXISTS `tb_usuarios` (
  `iduser` int(11) NOT NULL AUTO_INCREMENT,
  `DataCadastro` varchar(50) DEFAULT NULL,
  `Usuario` varchar(50) DEFAULT NULL,
  `Login` varchar(15) DEFAULT NULL,
  `Senha` varchar(15) DEFAULT NULL,
  `Perfil` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`iduser`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

-- Copiando dados para a tabela clinica.tb_usuarios: ~2 rows (aproximadamente)
/*!40000 ALTER TABLE `tb_usuarios` DISABLE KEYS */;
INSERT INTO `tb_usuarios` (`iduser`, `DataCadastro`, `Usuario`, `Login`, `Senha`, `Perfil`) VALUES
	(1, '13/07/2020', 'ADRIANO', 'ADMIN', 'admin', 'admin'),
	(2, '13/07/2020', 'LUCAS', 'ADMIN', '123', 'admin');
/*!40000 ALTER TABLE `tb_usuarios` ENABLE KEYS */;

-- Copiando estrutura para tabela clinica.tb_vias_acesso
CREATE TABLE IF NOT EXISTS `tb_vias_acesso` (
  `codigo` int(20) NOT NULL AUTO_INCREMENT,
  `DataCadastro` date NOT NULL DEFAULT '0000-00-00',
  `ViasAcesso` varchar(100) NOT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

-- Copiando dados para a tabela clinica.tb_vias_acesso: ~6 rows (aproximadamente)
/*!40000 ALTER TABLE `tb_vias_acesso` DISABLE KEYS */;
INSERT INTO `tb_vias_acesso` (`codigo`, `DataCadastro`, `ViasAcesso`) VALUES
	(1, '2020-05-25', 'ORAL'),
	(2, '2020-05-25', 'SUBLINGUAL'),
	(3, '2020-05-25', 'IV'),
	(4, '2020-05-25', 'IM'),
	(5, '2020-05-25', 'SC'),
	(6, '2020-05-27', 'RETAL');
/*!40000 ALTER TABLE `tb_vias_acesso` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
