DROP TABLE IF EXISTS USERS;

CREATE TABLE USERS ( 
	userId          varchar(12)		NOT NULL, 
	password		varchar(12)		NOT NULL,
	name			varchar(20)		NOT NULL,
	email			varchar(50),
  	
	PRIMARY KEY               (userId),  	
	INDEX USERS_userId_idx  (userId)
);

INSERT INTO USERS VALUES('javajigi', 'password', '자바지기', 'admin@javajigi.net');

DROP TABLE IF EXISTS USERS_LOGIN_EVENT;
CREATE TABLE USERS_LOGIN_EVENT (
    id              mediumint       NOT NULL    AUTO_INCREMENT,
    createdDate     timestamp       NOT NULL,
	userId          varchar(12)		NOT NULL, 
	password		varchar(12)		NOT NULL,
	ipaddress       varchar(15)     NOT NULL,
	success         bit             NOT NULL,
  	
	PRIMARY KEY               (id),  	
	INDEX USERS_LOGIN_EVENT_id_idx  (id)
);
