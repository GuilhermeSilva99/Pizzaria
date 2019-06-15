-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: 15-Jun-2019 às 03:40
-- Versão do servidor: 10.1.38-MariaDB
-- versão do PHP: 7.1.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `pizzaria`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `cargo`
--

CREATE TABLE `cargo` (
  `ID` int(11) NOT NULL,
  `NOME` varchar(70) NOT NULL,
  `DESCRICAO` text NOT NULL,
  `SALARIO_BASE` double NOT NULL,
  `INATIVO` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `cargo`
--

INSERT INTO `cargo` (`ID`, `NOME`, `DESCRICAO`, `SALARIO_BASE`, `INATIVO`) VALUES
(1, 'administrador', 'manda em tudo', 0, 0),
(2, 'gerente', 'manda em quase tudo', 2000, 0),
(3, 'caixa', 'fica no caixa', 1100, 1),
(4, 'caixa', 'fica no caixa', 900, 0);

-- --------------------------------------------------------

--
-- Estrutura da tabela `comanda`
--

CREATE TABLE `comanda` (
  `CHAVE_PRIMARIA` varchar(30) NOT NULL,
  `DATA_COMPRA` varchar(20) NOT NULL,
  `HORARIO_COMPRA` varchar(20) NOT NULL,
  `FORMA_PAGAMENTO` varchar(50) NOT NULL,
  `FUNCIONARIO` int(11) NOT NULL,
  `VALOR_COMANDA` double NOT NULL,
  `ENTREGA` varchar(10) NOT NULL,
  `NOME_CLIENTE` varchar(100) DEFAULT NULL,
  `CIDADE` varchar(100) DEFAULT NULL,
  `RUA` varchar(100) DEFAULT NULL,
  `NUMERO` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `comanda`
--

INSERT INTO `comanda` (`CHAVE_PRIMARIA`, `DATA_COMPRA`, `HORARIO_COMPRA`, `FORMA_PAGAMENTO`, `FUNCIONARIO`, `VALOR_COMANDA`, `ENTREGA`, `NOME_CLIENTE`, `CIDADE`, `RUA`, `NUMERO`) VALUES
('2019-06-1414:21:27', '2019-06-14', '14:21:27', 'À Vista', 1, 30, 'Não', 'jose', 'Pizzaria', '', ''),
('2019-06-1414:22:52', '2019-06-14', '14:22:52', 'À Vista', 1, 30, 'Não', '', 'Pizzaria', '', ''),
('2019-06-1414:28:25', '2019-06-14', '14:28:25', 'À Vista', 1, 20, 'Não', '', 'Pizzaria', '', ''),
('2019-06-1414:28:45', '2019-06-14', '14:28:45', 'À Vista', 1, 30, 'Não', '', 'Pizzaria', '', ''),
('2019-06-1414:29:00', '2019-06-14', '14:29:00', 'À Vista', 1, 50, 'Não', '', 'Pizzaria', '', ''),
('2019-06-1414:33:38', '2019-06-14', '14:33:38', 'À Vista', 1, 30, 'Entregue', 'jose', 'ddddddd', 'ddddddddd', '30'),
('2019-06-1414:40:45', '2019-06-14', '14:40:45', 'À Vista', 1, 20, 'Entregue', 'lll', 'mmmm', 'mmm', '9'),
('2019-06-1414:47:32', '2019-06-14', '14:47:32', 'À Vista', 1, 20, 'Entregue', '', 'Pizzaria', '', ''),
('2019-06-1422:28:24', '2019-06-14', '22:28:24', 'À Vista', 1, 30, 'Não', '', 'Pizzaria', '', ''),
('2019-06-1422:28:32', '2019-06-14', '22:28:32', 'À Vista', 1, 20, 'Entregue', '', 'Pizzaria', '', '');

-- --------------------------------------------------------

--
-- Estrutura da tabela `funcionario`
--

CREATE TABLE `funcionario` (
  `ID` int(11) NOT NULL,
  `NOME` varchar(100) NOT NULL,
  `CPF` varchar(11) NOT NULL,
  `EMAIL` varchar(100) NOT NULL,
  `SENHA` varchar(100) NOT NULL,
  `CARGO` int(11) NOT NULL,
  `STATUS` varchar(30) NOT NULL,
  `DATA_ADMISSAO` varchar(20) NOT NULL,
  `CIDADE` varchar(100) NOT NULL,
  `RUA` varchar(100) NOT NULL,
  `NUMERO` varchar(10) NOT NULL,
  `INATIVO` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `funcionario`
--

INSERT INTO `funcionario` (`ID`, `NOME`, `CPF`, `EMAIL`, `SENHA`, `CARGO`, `STATUS`, `DATA_ADMISSAO`, `CIDADE`, `RUA`, `NUMERO`, `INATIVO`) VALUES
(1, 'Gulherme', '00000000000', 'admin', 'admin', 1, '', '', '------', '------', 'SN', 0),
(2, 'felipe', '11304353427', 'felipe', '12345', 2, 'Ativo', '2019-06-04', 'santana', 'centro', '100', 0),
(3, 'jose', '11111111111', 'josee', '11111', 2, 'Ativo', '2019-06-04', 'ddddd', 'ddddd', 'SN', 1),
(4, 'jose', '11111111111', 'jose1', '12345', 2, 'Ativo', '2019-06-12', 'garanhuns', 'centro', 'SN', 0);

-- --------------------------------------------------------

--
-- Estrutura da tabela `pizza`
--

CREATE TABLE `pizza` (
  `ID` int(11) NOT NULL,
  `NOME` varchar(100) NOT NULL,
  `VALOR` double NOT NULL,
  `INGREDIENTES` text NOT NULL,
  `TIPO_PIZZA` varchar(30) NOT NULL,
  `DISPONIBILIDADE` tinyint(1) NOT NULL,
  `INATIVO` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `pizza`
--

INSERT INTO `pizza` (`ID`, `NOME`, `VALOR`, `INGREDIENTES`, `TIPO_PIZZA`, `DISPONIBILIDADE`, `INATIVO`) VALUES
(1, 'calabresa', 25, 'cabrela com massa', 'Salgada', 0, 1),
(2, 'calabresa', 20, 'calabresa', 'Salgada', 1, 0),
(3, 'frango', 30, 'frango', 'Salgada', 1, 0);

-- --------------------------------------------------------

--
-- Estrutura da tabela `pizzas_comanda`
--

CREATE TABLE `pizzas_comanda` (
  `ID` int(11) NOT NULL,
  `COMANDA` varchar(30) NOT NULL,
  `PIZZA` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `pizzas_comanda`
--

INSERT INTO `pizzas_comanda` (`ID`, `COMANDA`, `PIZZA`) VALUES
(2, '2019-06-1414:21:27', 2),
(3, '2019-06-1414:21:27', 3),
(4, '2019-06-1414:22:52', 3),
(5, '2019-06-1414:28:25', 2),
(6, '2019-06-1414:28:45', 3),
(7, '2019-06-1414:29:00', 2),
(8, '2019-06-1414:29:00', 3),
(15, '2019-06-1414:33:38', 3),
(16, '2019-06-1414:40:45', 2),
(17, '2019-06-1414:47:32', 2),
(18, '2019-06-1422:28:24', 3),
(19, '2019-06-1422:28:32', 2);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `cargo`
--
ALTER TABLE `cargo`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `comanda`
--
ALTER TABLE `comanda`
  ADD PRIMARY KEY (`CHAVE_PRIMARIA`),
  ADD KEY `FUNCIONARIO` (`FUNCIONARIO`);

--
-- Indexes for table `funcionario`
--
ALTER TABLE `funcionario`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `CARGO` (`CARGO`);

--
-- Indexes for table `pizza`
--
ALTER TABLE `pizza`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `pizzas_comanda`
--
ALTER TABLE `pizzas_comanda`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `PIZZA` (`PIZZA`),
  ADD KEY `COMANDA` (`COMANDA`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `cargo`
--
ALTER TABLE `cargo`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `funcionario`
--
ALTER TABLE `funcionario`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `pizza`
--
ALTER TABLE `pizza`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `pizzas_comanda`
--
ALTER TABLE `pizzas_comanda`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- Constraints for dumped tables
--

--
-- Limitadores para a tabela `comanda`
--
ALTER TABLE `comanda`
  ADD CONSTRAINT `comanda_ibfk_1` FOREIGN KEY (`FUNCIONARIO`) REFERENCES `funcionario` (`ID`);

--
-- Limitadores para a tabela `funcionario`
--
ALTER TABLE `funcionario`
  ADD CONSTRAINT `funcionario_ibfk_1` FOREIGN KEY (`CARGO`) REFERENCES `cargo` (`ID`);

--
-- Limitadores para a tabela `pizzas_comanda`
--
ALTER TABLE `pizzas_comanda`
  ADD CONSTRAINT `pizzas_comanda_ibfk_2` FOREIGN KEY (`PIZZA`) REFERENCES `pizza` (`ID`),
  ADD CONSTRAINT `pizzas_comanda_ibfk_3` FOREIGN KEY (`COMANDA`) REFERENCES `comanda` (`CHAVE_PRIMARIA`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
