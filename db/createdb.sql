# CREATE SCHEMA `engagementportal` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci ;



DROP TABLE IF EXISTS `ep_Customer`;
CREATE TABLE `ep_Customer` (
  `customerId` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `credits` int(11) NOT NULL DEFAULT '0',
  `createDate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modifiedDate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`customerId`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
DROP TABLE IF EXISTS `ep_SalesOrder`;

CREATE TABLE `ep_SalesOrder` (
  `salesOrderId` int(11) NOT NULL AUTO_INCREMENT,
  `customerId` int(11) NOT NULL,
  `classRegSent` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `reportedRevRec` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `salesOrderNumber` int(11) NOT NULL DEFAULT '0',
  `status` varchar(1) COLLATE utf8_unicode_ci NOT NULL,
  `bookDate` datetime DEFAULT NULL,
  `shipDate` datetime DEFAULT NULL,
  `planningMeetingDate` datetime DEFAULT NULL,
  `kickoffMeetingDate` datetime DEFAULT NULL,
  `onSiteStartDate` datetime DEFAULT NULL,
  `OnSiteEndDate` datetime DEFAULT NULL,
  `releaseForRevenueRecDate` datetime DEFAULT NULL,
  `waitTime` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `bookedToKickOff` int(11) DEFAULT '0',
  `daysToClose` int(11) DEFAULT '0',
  `amount` decimal(10,2) DEFAULT NULL,
  `notes` text COLLATE utf8_unicode_ci,
  `location` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `region` varchar(3) COLLATE utf8_unicode_ci DEFAULT NULL,
  `modelGroup` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `service` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `accountTeam` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `remote` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `onsite` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `equipmentList` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `shawdow` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `createDate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modifiedDate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`salesOrderId`,`customerId`),
  KEY `_idx` (`customerId`),
  CONSTRAINT `FK_SalesOrderCustomer` FOREIGN KEY (`customerId`) REFERENCES `ep_Customer` (`customerId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE `ep_SalesOrderStatus` (
  `statusCode` CHAR(2) NOT NULL,
  PRIMARY KEY (`statusCode`),
  UNIQUE INDEX `statusCode_UNIQUE` (`statusCode` ASC));

DROP TABLE IF EXISTS `import_Master`;
CREATE TABLE `import_Master` (
  `classRegSent` varchar(10) COLLATE utf8_unicode_ci NULL,
  `reportedRevRec` varchar(10) COLLATE utf8_unicode_ci NULL,
  `credits` int(11) NULL,
  `name` varchar(100) COLLATE utf8_unicode_ci NULL,
  `status` varchar(1) COLLATE utf8_unicode_ci NULL,
  `bookDate` datetime NULL,
  `shipDate` datetime NULL,
  `planningMeetingDate` datetime NULL,
  `kickoffMeetingDate` datetime NULL,
  `onSiteStartDate` datetime NULL,
  `OnSiteEndDate` datetime NULL,
  `releaseForRevenueRecDate` datetime NULL,
  `waitTime` varchar(20) COLLATE utf8_unicode_ci NULL,
  `bookedToKickOff` int(11)  NULL,
  `daysToClose` int(11) null,
  `amount` decimal(10,2) NULL,
  `notes` text COLLATE utf8_unicode_ci null,
  `location` varchar(20) COLLATE utf8_unicode_ci NULL,
  `region` varchar(3) COLLATE utf8_unicode_ci NULL,
  `modelGroup` varchar(20) COLLATE utf8_unicode_ci NULL,
  `service` varchar(50) COLLATE utf8_unicode_ci NULL,
  `accountTeam` varchar(200) COLLATE utf8_unicode_ci NULL,
  `remote` varchar(50) COLLATE utf8_unicode_ci NULL,
  `onsite` varchar(50) COLLATE utf8_unicode_ci NULL,
  `equipmentList` varchar(200) COLLATE utf8_unicode_ci NULL,
  `shawdow` varchar(200) COLLATE utf8_unicode_ci NULL,
  `importDate` datetime NULL,
  `importProcessedDate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `importStatus` char(1) not null default 'I'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

DROP TABLE IF EXISTS `import_OracleOBI`;
CREATE TABLE `import_OracleOBI` (
  `FNET Region 1` varchar(10) COLLATE utf8_unicode_ci NULL,
  `salesAgentName` varchar(100) COLLATE utf8_unicode_ci NULL,
  `activityYear` int(11) NULL,
  `activityMonth` int(11) NULL,
  `activityDate` datetime NULL,
  `shipDate` datetime NULL,
  `planningMeetingDate` datetime NULL,
  `kickoffMeetingDate` datetime NULL,
  `stCustomerName` varchar(100) COLLATE utf8_unicode_ci NULL,
  `stChannelName` varchar(100) COLLATE utf8_unicode_ci NULL,
  `btCustomerName` varchar(100) COLLATE utf8_unicode_ci NULL,
  `orderNumber` int(11) NULL,
  `productFamilyCode` varchar(30) COLLATE utf8_unicode_ci NULL,
  `modelGroupCode` varchar(30) COLLATE utf8_unicode_ci NULL,
  `forecastGroupCode` varchar(30) COLLATE utf8_unicode_ci NULL,
  `orderedQuantity` int(11)  NULL,
  `netUSD` decimal(10,2) NULL,
  `contractStatusCode` varchar(20) COLLATE utf8_unicode_ci NULL,
  `endUserName` varchar(50) COLLATE utf8_unicode_ci NULL,
  `importDate` datetime NULL,
  `importProcessedDate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `importStatus` char(1) not null default 'I'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


DROP TABLE IF EXISTS `import_Salesforce`;
CREATE TABLE `import_Salesforce` (
  `importDate` datetime NULL,
  `importProcessedDate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `importStatus` char(1) not null default 'I'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

DROP TABLE IF EXISTS `import_SharePoint`;
CREATE TABLE `import_SharePoint` (
  `importDate` datetime NULL,
  `importProcessedDate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `importStatus` char(1) not null default 'I'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

