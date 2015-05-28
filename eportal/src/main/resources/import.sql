INSERT INTO `sec_role` (`role_id`,`create_date`, `modified_date`, `name`) VALUES (1,now(), now(), 'admin');
INSERT INTO `sec_role` (`role_id`,`create_date`, `modified_date`, `name`) VALUES (2,now(), now(), 'user');
INSERT INTO `sec_role` (`role_id`,`create_date`, `modified_date`, `name`) VALUES (3,now(), now(), 'salesrep');
INSERT INTO `sec_role` (`role_id`,`create_date`, `modified_date`, `name`) VALUES (4,now(), now(), 'superuser');

INSERT INTO `sec_user` (`email`, `first_name`, `last_name`, `password`,`enabled`,`create_date`,`modified_date`,role_id) VALUES ('rob@yarrumsoftware.com', 'ROb', 'Murray', 'pAssword13!',1,now(),now(),4);
INSERT INTO `sec_user` (`email`, `first_name`, `last_name`, `password`,`enabled`,`create_date`,`modified_date`,role_id) VALUES ('amy.fitts@flukenetworks.com', 'Amy', 'Fitts', 'portal',1,now(),now(),1);
INSERT INTO `sec_user` (`email`, `first_name`, `last_name`, `password`,`enabled`,`create_date`,`modified_date`,role_id) VALUES ('peter.schmidt@flukenetworks.com', 'Peter', 'Schmidt', 'portal',1,now(),now(),1);
INSERT INTO `sec_user` (`email`, `first_name`, `last_name`, `password`,`enabled`,`create_date`,`modified_date`,role_id) VALUES ('user@yarrumsoftware.com', 'Bill', 'Murray', 'pAssword13!',1,now(),now(),2);
INSERT INTO `sec_user` (`email`, `first_name`, `last_name`, `password`,`enabled`,`create_date`,`modified_date`,role_id) VALUES ('sales@yarrumsoftware.com', 'Smith', 'Simpson', 'pAssword13!',1,now(),now(),3);

