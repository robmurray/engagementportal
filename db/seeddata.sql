INSERT INTO `sec_user` (`email`, `first_name`, `last_name`, `password`,`enabled`,`create_date`,`modified_date`) VALUES ('rob@yarrumsoftware.com', 'ROb', 'Murray', 'pAssword13!',1,now(),now());
INSERT INTO `sec_user_role` (`user_id`, `create_date`, `modified_date`, `name`) VALUES ('1', now(), now(), 'superuser');

INSERT INTO `sec_user` (`email`, `first_name`, `last_name`, `password`,`enabled`,`create_date`,`modified_date`) VALUES ('amy.fitts@flukenetworks.com', 'Amy', 'Fitts', 'portal',1,now(),now());
INSERT INTO `sec_user_role` (`user_id`, `create_date`, `modified_date`, `name`) VALUES ('1', now(), now(), 'admin');

INSERT INTO `sec_user` (`email`, `first_name`, `last_name`, `password`,`enabled`,`create_date`,`modified_date`) VALUES ('peter.schmidt@flukenetworks.com', 'Peter', 'Schmidt', 'portal',1,now(),now());
INSERT INTO `sec_user_role` (`user_id`, `create_date`, `modified_date`, `name`) VALUES ('1', now(), now(), 'admin');

INSERT INTO `sec_user` (`email`, `first_name`, `last_name`, `password`,`enabled`,`create_date`,`modified_date`) VALUES ('bill@yarrumsoftware.com', 'Bill', 'Murray', 'pAssword13!',1,now(),now());
INSERT INTO `sec_user_role` (`user_id`, `create_date`, `modified_date`, `name`) VALUES ('1', now(), now(), 'admin');