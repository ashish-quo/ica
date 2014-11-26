    
Insert into attribute values (1,'Network','',1,'visitednetwork','java.lang.String',2,'homenetwork');
Insert into attribute values (2,'Roaming Category','',3,'overalltripcategory','java.lang.Integer',1,'overalltripcategory');
Insert into attribute values (3,'Domestic ARPU','',8,'overalldomesticcategory','java.lang.Integer',1,'overalldomesticcategory');
Insert into attribute values (4,'Payment Type','',9,'paymenttype','java.lang.Integer',1,'paymenttype');
Insert into attribute values (6,'Roaming MO','',4,'mocallminutesquadroam','java.lang.Integer',1,'mocallminutesquadroam');
Insert into attribute values (7,'Roaming MT','',5,'mtcallminutesquadroam','java.lang.Integer',1,'mtcallminutesquadroam');
Insert into attribute values (8,'Roaming Data','',6,'tonnagequadroam','java.lang.Integer',1,'tonnagequadroam');
Insert into attribute values (9,'Roaming SMS ','',7,'mosmscountquadroam','java.lang.Integer',1,'mosmscountquadroam');
Insert into attribute values (10,'Network Group','',2,'visitednetwork','java.lang.String',3,'homenetwork');
    


Insert into attribute_category values (1,'Silent',1,2,'1');
Insert into attribute_category values (2,'Value',2,2,'2');
Insert into attribute_category values (3,'Premium',3,2,'3');
Insert into attribute_category values (4,'Low',1,3,'4');
Insert into attribute_category values (5,'Medium',2,3,'5');
Insert into attribute_category values (6,'High',3,3,'6');
Insert into attribute_category values (7,'PRE PAID',1,4,'1');
Insert into attribute_category values (8,'POST PAID',2,4,'0');
Insert into attribute_category values (9,'MO Zero',1,6,'1');
Insert into attribute_category values (10,'MO Moderate',2,6,'2');
Insert into attribute_category values (11,'MO Heavy',3,6,'3');
Insert into attribute_category values (12,'MT Zero',1,7,'1');
Insert into attribute_category values (13,'MT Moderate',2,7,'2');
Insert into attribute_category values (14,'MT Heavy',3,7,'3');
Insert into attribute_category values (15,'Data Zero',1,8,'1');
Insert into attribute_category values (16,'Data Moderate',2,8,'2');
Insert into attribute_category values (17,'Data Heavy',3,8,'3');
Insert into attribute_category values (18,'SMS Zero',1,9,'1');
Insert into attribute_category values (19,'SMS Moderate',2,9,'2');
Insert into attribute_category values (20,'SMS  Heavy',3,9,'3');
    
--- insert sql for userinfo and user_roles table
INSERT INTO userinfo(username, password, enabled)     VALUES ('smruti', '$2a$10$QAFuRJEH1HfDnvrmiCmfrOCRdnzWF2mLA7AuJmqWjFAjzPK6znDj2', TRUE);
INSERT INTO user_roles(role_name, username) VALUES ( 'ROLE_ADMIN', 'smruti');

--- insert sql for userinfo and user_roles table
INSERT INTO userinfo(username, password, enabled)     VALUES ('RAtest', '$2a$10$Nhdoa1IYol1MWD8xDPlAnOoyYilKpiVVxb2iL.Jkb4.q7laJwmkXC', TRUE);
INSERT INTO user_roles(role_name, username) VALUES ( 'ROLE_ADMIN', 'RAtest');

