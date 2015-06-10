-- --------------------------------------------------------------------------------
-- Routine DDL
-- Note: comments before and after the routine body will not be stored by the server
-- --------------------------------------------------------------------------------
DELIMITER $$

CREATE DEFINER=`epadmin`@`107.213.178.7` PROCEDURE `generateExportTable`()
BEGIN


DROP TABLE IF EXISTS export_epdata;


CREATE TABLE `export_epdata` (
  `project_id` bigint(20) NOT NULL,
  `create_date` datetime NOT NULL,
  `modified_date` datetime NOT NULL,
  `class_reg_sent` varchar(255) DEFAULT NULL,
  `credits` bigint(20) DEFAULT NULL,
  `health` varchar(255) DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `reported_rev_rec` varchar(255) DEFAULT NULL,
  `service` text,
  `status` varchar(255) DEFAULT NULL,
  `wait_time` varchar(255) DEFAULT NULL,
  `customer_id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `sales_order_id` bigint(20) NOT NULL,
  `activity_date` datetime DEFAULT NULL,
  `activity_month_number` int(11) DEFAULT NULL,
  `activity_year` int(11) DEFAULT NULL,
  `amount` decimal(19,2) DEFAULT NULL,
  `bt_customer_name` varchar(255) DEFAULT NULL,
  `contract_status_code` varchar(255) DEFAULT NULL,
  `end_user_name` varchar(255) DEFAULT NULL,
  `forecast_group_code` varchar(255) DEFAULT NULL,
  `model_group` varchar(255) DEFAULT NULL,
  `ordered_quantity` double DEFAULT NULL,
  `product_family_code` varchar(255) DEFAULT NULL,
  `region` varchar(255) DEFAULT NULL,
  `sales_order_number` varchar(255) DEFAULT NULL,
  `st_channel_name` varchar(255) DEFAULT NULL,
  `st_sales_agent_name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO export_epdata(
	project_id,
	create_date,
  modified_date,
  class_reg_sent,
  credits,
  health,
  location,
  reported_rev_rec,
  service,
  status,
  wait_time,
  customer_id,
  name,
  sales_order_id,
  activity_date,
  activity_month_number,
  activity_year,
  amount,
  bt_customer_name,
  contract_status_code,
  end_user_name,
forecast_group_code,
  model_group,
  ordered_quantity,
  product_family_code,
  region,
  sales_order_number,
  st_channel_name,
  st_sales_agent_name

)
	SELECT p.project_id,
           	p.create_date,
             p.modified_date,
             p.class_reg_sent,
             p.credits,
             p.health,
             p.location,
             p.reported_rev_rec,
             p.service,
             p.status,
             p.wait_time,
             c.customer_id,
             c.name,
             so.sales_order_id,
             so.activity_date,
             so.activity_month_number,
             so.activity_year,
             so.amount,
             so.bt_customer_name,
             so.contract_status_code,
             so.end_user_name,
           so.forecast_group_code,
             so.model_group,
             so.ordered_quantity,
             so.product_family_code,
             so.region,
             so.sales_order_number,
             so.st_channel_name,
             so.st_sales_agent_name

		FROM ep_project p, ep_customer c, ep_sales_order so
			where p.project_id=so.project_id AND so.customer_id=c.customer_id;





END