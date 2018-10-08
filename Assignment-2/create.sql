CREATE SCHEMA `assignment_2`;

DROP TABLE IF EXISTS person;
CREATE TABLE `person` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `username` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `dob` date DEFAULT NULL,
  PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS developer;
CREATE TABLE `developer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `developer_key` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `developer_person_generalization` FOREIGN KEY (`id`) REFERENCES `person` (`id`)
);

DROP TABLE IF EXISTS user;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_agreement` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `user_person_generalization` FOREIGN KEY (`id`) REFERENCES `person` (`id`)
);

DROP TABLE IF EXISTS website;
CREATE TABLE `website` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  `created` date DEFAULT NULL,
  `updated` date DEFAULT NULL,
  `visits` int(11) DEFAULT NULL,
  `developer_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `website_developer_aggregation_idx` (`developer_id`),
  CONSTRAINT `website_developer_aggregation` FOREIGN KEY (`developer_id`) REFERENCES `developer` (`id`)
);


DROP TABLE IF EXISTS page;
CREATE TABLE `page` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `created` date DEFAULT NULL,
  `updated` date DEFAULT NULL,
  `views` int(11) DEFAULT NULL,
  `website_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `page_website_composition_idx` (`website_id`),
  CONSTRAINT `page_website_composition` FOREIGN KEY (`website_id`) REFERENCES `website` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
);

DROP TABLE IF EXISTS widget;
CREATE TABLE `widget` (
  `dtype` varchar(45) DEFAULT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `width` int(11) DEFAULT NULL,
  `height` int(11) DEFAULT NULL,
  `css_class` varchar(45) DEFAULT NULL,
  `css_style` varchar(45) DEFAULT NULL,
  `text` varchar(45) DEFAULT NULL,
  `order` int(11) DEFAULT NULL,
  `url` varchar(45) DEFAULT NULL,
  `shareble` tinyint(4) DEFAULT NULL,
  `expandable` tinyint(4) DEFAULT NULL,
  `src` varchar(45) DEFAULT NULL,
  `size` int(11) DEFAULT '2',
  `html` varchar(45) DEFAULT NULL,
  `page_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `page_id_idx` (`page_id`),
  CONSTRAINT `widget_page_composition` FOREIGN KEY (`page_id`) REFERENCES `page` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
);

DROP TABLE IF EXISTS address;
CREATE TABLE `address` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `street1` varchar(45) DEFAULT NULL,
  `street2` varchar(20) DEFAULT NULL,
  `city` varchar(20) DEFAULT NULL,
  `state` varchar(2) DEFAULT NULL,
  `zip` varchar(5) DEFAULT NULL,
  `primary` tinyint(4) DEFAULT NULL,
  `person_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `address_person_composition_idx` (`person_id`),
  CONSTRAINT `address_person_composition` FOREIGN KEY (`person_id`) REFERENCES `person` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
);

DROP TABLE IF EXISTS phone;
CREATE TABLE `phone` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `phone` varchar(20) DEFAULT NULL,
  `primary` tinyint(4) DEFAULT NULL,
  `person_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `phone_person_composition_idx` (`person_id`),
  CONSTRAINT `phone_person_composition` FOREIGN KEY (`person_id`) REFERENCES `person` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
);

DROP TABLE IF EXISTS priviledge;
CREATE TABLE `priviledge` (
  `id` varchar(6) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
);

INSERT INTO priviledge(id) values('CREATE');
INSERT INTO priviledge(id) values('READ');
INSERT INTO priviledge(id) values('UPDATE');
INSERT INTO priviledge(id) values('DELETE');

DROP TABLE IF EXISTS role;
CREATE TABLE `role` (
  `id` varchar(8) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
);

INSERT INTO role(id) values('OWNER');
INSERT INTO role(id) values('ADMIN');
INSERT INTO role(id) values('WRITER');
INSERT INTO role(id) values('EDITOR');
INSERT INTO role(id) values('REVIEWER');

DROP TABLE IF EXISTS website_priviledge;
CREATE TABLE `website_priviledge` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `priviledge` varchar(6) DEFAULT NULL,
  `developer_id` int(11) NOT NULL,
  `website_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `website_priviledge_developer_association_idx` (`developer_id`),
  KEY `website_priviledge_website_association_idx` (`website_id`),
  KEY `website_priviledge_priviledge_enumeration_idx` (`priviledge`),
  CONSTRAINT `website_priviledge_developer_association` FOREIGN KEY (`developer_id`) REFERENCES `developer` (`id`),
  CONSTRAINT `website_priviledge_priviledge_enumeration` FOREIGN KEY (`priviledge`) REFERENCES `priviledge` (`id`),
  CONSTRAINT `website_priviledge_website_association` FOREIGN KEY (`website_id`) REFERENCES `website` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
);


DROP TABLE IF EXISTS website_role;
CREATE TABLE `website_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role` varchar(8) DEFAULT NULL,
  `developer_id` int(11) NOT NULL,
  `website_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `website_role_developer_association_idx` (`developer_id`),
  KEY `website_role_website_association_idx` (`website_id`),
  KEY `website_role_role_enumeration_idx` (`role`),
  CONSTRAINT `website_role_developer_association_idx` FOREIGN KEY (`developer_id`) REFERENCES `developer` (`id`),
  CONSTRAINT `website_role_role_enumeration` FOREIGN KEY (`role`) REFERENCES `role` (`id`),
  CONSTRAINT `website_role_website_association_idx` FOREIGN KEY (`website_id`) REFERENCES `website` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
);

DROP TABLE IF EXISTS page_priviledge;
CREATE TABLE `page_priviledge` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `priviledge` varchar(6) DEFAULT NULL,
  `developer_id` int(11) NOT NULL,
  `page_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `page_priviledge_developer_association_idx` (`developer_id`),
  KEY `page_priviledge_page_association_idx` (`page_id`),
  KEY `page_priviledge_priviledge_enumeration_idx` (`priviledge`),
  CONSTRAINT `page_priviledge_developer_association` FOREIGN KEY (`developer_id`) REFERENCES `developer` (`id`),
  CONSTRAINT `page_priviledge_page_association` FOREIGN KEY (`page_id`) REFERENCES `page` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `page_priviledge_priviledge_enumeration` FOREIGN KEY (`priviledge`) REFERENCES `priviledge` (`id`)
);


DROP TABLE IF EXISTS page_role;
CREATE TABLE `page_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role` varchar(8) DEFAULT NULL,
  `developer_id` int(11) NOT NULL,
  `page_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `page_role_developer_association_idx` (`developer_id`),
  KEY `page_role_page_association_idx` (`page_id`),
  KEY `page_role_role_enumeration_idx` (`role`),
  CONSTRAINT `page_role_developer_association_idx` FOREIGN KEY (`developer_id`) REFERENCES `developer` (`id`),
  CONSTRAINT `page_role_page_association_idx` FOREIGN KEY (`page_id`) REFERENCES `page` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `page_role_role_enumeration` FOREIGN KEY (`role`) REFERENCES `role` (`id`)
);