-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.2.8-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for newbeams
CREATE DATABASE IF NOT EXISTS `newbeams` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `newbeams`;

-- Dumping structure for table newbeams.checkl1
CREATE TABLE IF NOT EXISTS `checkl1` (
  `Description` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `s` double DEFAULT NULL,
  `I` double DEFAULT NULL,
  `W` double DEFAULT NULL,
  `ID1` double DEFAULT NULL,
  `ID2` double DEFAULT NULL,
  `ID3` double DEFAULT NULL,
  KEY `IX_CheckL1_S` (`s`),
  KEY `IX_CheckL1_I` (`I`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- Data exporting was unselected.
-- Dumping structure for table newbeams.checkl2
CREATE TABLE IF NOT EXISTS `checkl2` (
  `Description` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `s` double DEFAULT NULL,
  `I` double DEFAULT NULL,
  `W` double DEFAULT NULL,
  `ID1` double DEFAULT NULL,
  `ID2` double DEFAULT NULL,
  `ID3` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- Data exporting was unselected.
-- Dumping structure for table newbeams.checkl3
CREATE TABLE IF NOT EXISTS `checkl3` (
  `Description` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `s` double DEFAULT NULL,
  `I` double DEFAULT NULL,
  `W` double DEFAULT NULL,
  `ID1` double DEFAULT NULL,
  `ID2` double DEFAULT NULL,
  `ID3` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- Data exporting was unselected.
-- Dumping structure for table newbeams.checks1
CREATE TABLE IF NOT EXISTS `checks1` (
  `Description` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `s` double DEFAULT NULL,
  `I` double DEFAULT NULL,
  `W` double DEFAULT NULL,
  `Lookup` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `ID1` double DEFAULT NULL,
  `ID2` double DEFAULT NULL,
  `ID3` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- Data exporting was unselected.
-- Dumping structure for table newbeams.checks2
CREATE TABLE IF NOT EXISTS `checks2` (
  `Description` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `s` double DEFAULT NULL,
  `I` double DEFAULT NULL,
  `W` double DEFAULT NULL,
  `Lookup` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `ID1` double DEFAULT NULL,
  `ID2` double DEFAULT NULL,
  `ID3` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- Data exporting was unselected.
-- Dumping structure for table newbeams.checks3
CREATE TABLE IF NOT EXISTS `checks3` (
  `Description` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `s` double DEFAULT NULL,
  `I` double DEFAULT NULL,
  `W` double DEFAULT NULL,
  `Lookup` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `ID1` double DEFAULT NULL,
  `ID2` double DEFAULT NULL,
  `ID3` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- Data exporting was unselected.
-- Dumping structure for table newbeams.checkstd1
CREATE TABLE IF NOT EXISTS `checkstd1` (
  `Description` varchar(255) CHARACTER SET utf8 NOT NULL,
  `s` double DEFAULT NULL,
  `I` double DEFAULT NULL,
  `W` double DEFAULT NULL,
  `DescId` double DEFAULT NULL,
  `ID` smallint(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- Data exporting was unselected.
-- Dumping structure for table newbeams.checkstd2
CREATE TABLE IF NOT EXISTS `checkstd2` (
  `Description` varchar(255) CHARACTER SET utf8 NOT NULL,
  `s` double DEFAULT NULL,
  `I` double DEFAULT NULL,
  `W` double DEFAULT NULL,
  `DescId` double DEFAULT NULL,
  `ID` smallint(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- Data exporting was unselected.
-- Dumping structure for table newbeams.checkstd3
CREATE TABLE IF NOT EXISTS `checkstd3` (
  `Description` varchar(255) CHARACTER SET utf8 NOT NULL,
  `s` double DEFAULT NULL,
  `I` double DEFAULT NULL,
  `W` double DEFAULT NULL,
  `DescId` double DEFAULT NULL,
  `ID` smallint(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- Data exporting was unselected.
-- Dumping structure for table newbeams.checkstd4
CREATE TABLE IF NOT EXISTS `checkstd4` (
  `Description` varchar(255) CHARACTER SET utf8 NOT NULL,
  `s` double DEFAULT NULL,
  `I` double DEFAULT NULL,
  `W` double DEFAULT NULL,
  `DescId` double DEFAULT NULL,
  `ID` smallint(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- Data exporting was unselected.
-- Dumping structure for table newbeams.checkstd5
CREATE TABLE IF NOT EXISTS `checkstd5` (
  `Description` varchar(255) CHARACTER SET utf8 NOT NULL,
  `s` double DEFAULT NULL,
  `I` double DEFAULT NULL,
  `W` double DEFAULT NULL,
  `DescId` double DEFAULT NULL,
  `ID` smallint(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- Data exporting was unselected.
-- Dumping structure for table newbeams.check_exist
CREATE TABLE IF NOT EXISTS `check_exist` (
  `Description` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL,
  `SX` double DEFAULT NULL,
  `I` double DEFAULT NULL,
  `GroupId` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- Data exporting was unselected.
-- Dumping structure for table newbeams.commanlvl
CREATE TABLE IF NOT EXISTS `commanlvl` (
  `Description` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `s` double DEFAULT NULL,
  `I` double DEFAULT NULL,
  `W` double DEFAULT NULL,
  `ID1` double DEFAULT NULL,
  `ID2` double DEFAULT NULL,
  `ID3` double DEFAULT NULL,
  `ID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- Data exporting was unselected.
-- Dumping structure for table newbeams.dbo_psl2
CREATE TABLE IF NOT EXISTS `dbo_psl2` (
  `Description` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `s` double DEFAULT NULL,
  `I` double DEFAULT NULL,
  `W` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- Data exporting was unselected.
-- Dumping structure for table newbeams.dbo_psl3
CREATE TABLE IF NOT EXISTS `dbo_psl3` (
  `Description` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `s` double DEFAULT NULL,
  `I` double DEFAULT NULL,
  `W` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- Data exporting was unselected.
-- Dumping structure for table newbeams.dtproperties
CREATE TABLE IF NOT EXISTS `dtproperties` (
  `id` int(11) NOT NULL,
  `objectid` int(11) DEFAULT NULL,
  `property` varchar(64) COLLATE utf8mb4_bin NOT NULL,
  `value` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `uvalue` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `lvalue` longblob DEFAULT NULL,
  `version` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- Data exporting was unselected.
-- Dumping structure for table newbeams.flilvl2l1p
CREATE TABLE IF NOT EXISTS `flilvl2l1p` (
  `Description` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `s` double DEFAULT NULL,
  `I` double DEFAULT NULL,
  `W` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- Data exporting was unselected.
-- Dumping structure for table newbeams.flilvl3l2p
CREATE TABLE IF NOT EXISTS `flilvl3l2p` (
  `Description` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `s` double DEFAULT NULL,
  `I` double DEFAULT NULL,
  `W` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- Data exporting was unselected.
-- Dumping structure for table newbeams.flilvl4l1p
CREATE TABLE IF NOT EXISTS `flilvl4l1p` (
  `Description` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `s` double DEFAULT NULL,
  `I` double DEFAULT NULL,
  `W` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- Data exporting was unselected.
-- Dumping structure for table newbeams.flistd2s1p
CREATE TABLE IF NOT EXISTS `flistd2s1p` (
  `Description` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `s` double DEFAULT NULL,
  `I` double DEFAULT NULL,
  `W` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- Data exporting was unselected.
-- Dumping structure for table newbeams.flistd3s2p
CREATE TABLE IF NOT EXISTS `flistd3s2p` (
  `Description` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `s` double DEFAULT NULL,
  `I` double DEFAULT NULL,
  `W` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- Data exporting was unselected.
-- Dumping structure for table newbeams.flistd4s1p
CREATE TABLE IF NOT EXISTS `flistd4s1p` (
  `Description` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `s` double DEFAULT NULL,
  `I` double DEFAULT NULL,
  `W` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- Data exporting was unselected.
-- Dumping structure for table newbeams.idtable
CREATE TABLE IF NOT EXISTS `idtable` (
  `ID` int(11) DEFAULT NULL,
  `Description` varchar(255) CHARACTER SET utf8 DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- Data exporting was unselected.
-- Dumping structure for table newbeams.lvl
CREATE TABLE IF NOT EXISTS `lvl` (
  `Description` varchar(255) CHARACTER SET utf8 NOT NULL,
  `s` double DEFAULT NULL,
  `I` double DEFAULT NULL,
  `W` double DEFAULT NULL,
  `Depth` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- Data exporting was unselected.
-- Dumping structure for table newbeams.lvlcheck1
CREATE TABLE IF NOT EXISTS `lvlcheck1` (
  `Description` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `s` double DEFAULT NULL,
  `I` double DEFAULT NULL,
  `W` double DEFAULT NULL,
  `ID` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- Data exporting was unselected.
-- Dumping structure for table newbeams.lvlcheck2
CREATE TABLE IF NOT EXISTS `lvlcheck2` (
  `Description` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `s` double DEFAULT NULL,
  `I` double DEFAULT NULL,
  `W` double DEFAULT NULL,
  `ID` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- Data exporting was unselected.
-- Dumping structure for table newbeams.lvlcheck3
CREATE TABLE IF NOT EXISTS `lvlcheck3` (
  `Description` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `s` double DEFAULT NULL,
  `I` double DEFAULT NULL,
  `W` double DEFAULT NULL,
  `ID` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- Data exporting was unselected.
-- Dumping structure for table newbeams.lvlcheck4
CREATE TABLE IF NOT EXISTS `lvlcheck4` (
  `Description` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `s` double DEFAULT NULL,
  `I` double DEFAULT NULL,
  `W` double DEFAULT NULL,
  `ID` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- Data exporting was unselected.
-- Dumping structure for table newbeams.lvlcheck5
CREATE TABLE IF NOT EXISTS `lvlcheck5` (
  `Description` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `s` double DEFAULT NULL,
  `I` double DEFAULT NULL,
  `W` double DEFAULT NULL,
  `ID` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- Data exporting was unselected.
-- Dumping structure for table newbeams.lvlt1
CREATE TABLE IF NOT EXISTS `lvlt1` (
  `Description` varchar(255) CHARACTER SET utf8 NOT NULL,
  `s` double DEFAULT NULL,
  `I` double DEFAULT NULL,
  `W` double DEFAULT NULL,
  `DescID` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- Data exporting was unselected.
-- Dumping structure for table newbeams.lvlt2
CREATE TABLE IF NOT EXISTS `lvlt2` (
  `Description` varchar(255) CHARACTER SET utf8 NOT NULL,
  `s` double DEFAULT NULL,
  `I` double DEFAULT NULL,
  `W` double DEFAULT NULL,
  `DescID` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- Data exporting was unselected.
-- Dumping structure for table newbeams.lvlt3
CREATE TABLE IF NOT EXISTS `lvlt3` (
  `Description` varchar(255) CHARACTER SET utf8 NOT NULL,
  `s` double DEFAULT NULL,
  `I` double DEFAULT NULL,
  `W` double DEFAULT NULL,
  `DescID` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- Data exporting was unselected.
-- Dumping structure for table newbeams.lvlt4
CREATE TABLE IF NOT EXISTS `lvlt4` (
  `Description` varchar(255) CHARACTER SET utf8 NOT NULL,
  `s` double DEFAULT NULL,
  `I` double DEFAULT NULL,
  `W` double DEFAULT NULL,
  `DescID` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- Data exporting was unselected.
-- Dumping structure for table newbeams.lvlt5
CREATE TABLE IF NOT EXISTS `lvlt5` (
  `Description` varchar(255) CHARACTER SET utf8 NOT NULL,
  `s` double DEFAULT NULL,
  `I` double DEFAULT NULL,
  `W` double DEFAULT NULL,
  `DescID` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- Data exporting was unselected.
-- Dumping structure for table newbeams.lvl_index
CREATE TABLE IF NOT EXISTS `lvl_index` (
  `Id` int(11) DEFAULT NULL,
  `Description` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `s` double DEFAULT NULL,
  `I` double DEFAULT NULL,
  `W` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- Data exporting was unselected.
-- Dumping structure for table newbeams.profile
CREATE TABLE IF NOT EXISTS `profile` (
  `UserName` varchar(50) COLLATE utf8mb4_bin NOT NULL,
  `PassWord` varchar(50) COLLATE utf8mb4_bin NOT NULL,
  `FirstName` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL,
  `LastName` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL,
  `Email` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL,
  `Address1` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL,
  `City` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL,
  `State` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL,
  `Zip` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL,
  `WorkPhoneNum` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL,
  `CellPhoneNum` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- Data exporting was unselected.
-- Dumping structure for table newbeams.project
CREATE TABLE IF NOT EXISTS `project` (
  `UserName` varchar(50) COLLATE utf8mb4_bin NOT NULL,
  `Name` varchar(50) COLLATE utf8mb4_bin NOT NULL,
  `Address1` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL,
  `City` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL,
  `State` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL,
  `Zip` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- Data exporting was unselected.
-- Dumping structure for table newbeams.projectdetail
CREATE TABLE IF NOT EXISTS `projectdetail` (
  `UserName` varchar(50) COLLATE utf8mb4_bin NOT NULL,
  `ProjectName` varchar(50) COLLATE utf8mb4_bin NOT NULL,
  `Status` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL,
  `BeamSize` varchar(150) COLLATE utf8mb4_bin DEFAULT NULL,
  `Span` double DEFAULT NULL,
  `BeamDate` datetime DEFAULT NULL,
  `BeamLoad` double DEFAULT NULL,
  `Deflection` double DEFAULT NULL,
  `Material` varchar(150) COLLATE utf8mb4_bin DEFAULT NULL,
  `Tension` double DEFAULT NULL,
  `CSArea` double DEFAULT NULL,
  `RafterSize` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- Data exporting was unselected.
-- Dumping structure for table newbeams.psl
CREATE TABLE IF NOT EXISTS `psl` (
  `Description` varchar(255) CHARACTER SET utf8 NOT NULL,
  `s` double DEFAULT NULL,
  `I` double DEFAULT NULL,
  `W` double DEFAULT NULL,
  `Depth` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- Data exporting was unselected.
-- Dumping structure for table newbeams.psl1
CREATE TABLE IF NOT EXISTS `psl1` (
  `Description` varchar(255) CHARACTER SET utf8 NOT NULL,
  `s` double DEFAULT NULL,
  `I` double DEFAULT NULL,
  `W` double DEFAULT NULL,
  `DescId` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- Data exporting was unselected.
-- Dumping structure for table newbeams.psl2
CREATE TABLE IF NOT EXISTS `psl2` (
  `Description` varchar(255) CHARACTER SET utf8 NOT NULL,
  `s` double DEFAULT NULL,
  `I` double DEFAULT NULL,
  `W` double DEFAULT NULL,
  `DescId` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- Data exporting was unselected.
-- Dumping structure for table newbeams.std
CREATE TABLE IF NOT EXISTS `std` (
  `Description` varchar(255) CHARACTER SET utf8 NOT NULL,
  `s` double DEFAULT NULL,
  `I` double DEFAULT NULL,
  `W` double DEFAULT NULL,
  `Depth` double DEFAULT NULL,
  `ID` smallint(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- Data exporting was unselected.
-- Dumping structure for table newbeams.steelt
CREATE TABLE IF NOT EXISTS `steelt` (
  `Description` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `s` double DEFAULT NULL,
  `I` double DEFAULT NULL,
  `W` double DEFAULT NULL,
  `groupid` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- Data exporting was unselected.
-- Dumping structure for table newbeams.steelt_future
CREATE TABLE IF NOT EXISTS `steelt_future` (
  `Description` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `s` double DEFAULT NULL,
  `I` double DEFAULT NULL,
  `W` double DEFAULT NULL,
  `groupid` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- Data exporting was unselected.
-- Dumping structure for table newbeams.tjilvlflange
CREATE TABLE IF NOT EXISTS `tjilvlflange` (
  `Description` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `S` double DEFAULT NULL,
  `I` double DEFAULT NULL,
  `W` double DEFAULT NULL,
  `TWeb` double DEFAULT NULL,
  `Depth` double DEFAULT NULL,
  `a` double DEFAULT NULL,
  `b` double DEFAULT NULL,
  `manufacturer` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `Id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- Data exporting was unselected.
-- Dumping structure for table newbeams.tjiwoodflange
CREATE TABLE IF NOT EXISTS `tjiwoodflange` (
  `Description` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `S` double DEFAULT NULL,
  `I` double DEFAULT NULL,
  `W` double DEFAULT NULL,
  `TWeb` double DEFAULT NULL,
  `Depth` double DEFAULT NULL,
  `a` double DEFAULT NULL,
  `b` double DEFAULT NULL,
  `manufacturer` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `Id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- Data exporting was unselected.
-- Dumping structure for table newbeams.woodishape
CREATE TABLE IF NOT EXISTS `woodishape` (
  `Description` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `S` double DEFAULT NULL,
  `I` double DEFAULT NULL,
  `W` double DEFAULT NULL,
  `TWeb` double DEFAULT NULL,
  `Depth` double DEFAULT NULL,
  `a` double DEFAULT NULL,
  `b` double DEFAULT NULL,
  `Flangemat` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL,
  `manufacturer` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- Data exporting was unselected.
-- Dumping structure for table newbeams.woodishape1
CREATE TABLE IF NOT EXISTS `woodishape1` (
  `Description` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `Flangemat` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL,
  `S` double DEFAULT NULL,
  `I` double DEFAULT NULL,
  `W` double DEFAULT NULL,
  `TWeb` double DEFAULT NULL,
  `Depth` double DEFAULT NULL,
  `a` double DEFAULT NULL,
  `b` double DEFAULT NULL,
  `manufacture` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- Data exporting was unselected.
-- Dumping structure for table newbeams.woodishape_notused
CREATE TABLE IF NOT EXISTS `woodishape_notused` (
  `Description` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `Flangemat` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL,
  `S` double DEFAULT NULL,
  `I` double DEFAULT NULL,
  `W` double DEFAULT NULL,
  `TWeb` double DEFAULT NULL,
  `Depth` double DEFAULT NULL,
  `a` double DEFAULT NULL,
  `b` double DEFAULT NULL,
  `manufacture` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- Data exporting was unselected.
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
