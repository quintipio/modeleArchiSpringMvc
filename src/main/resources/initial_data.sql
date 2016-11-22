create schema modelearchi;

create table commune(
	id INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
	code_insee VARCHAR(5) NOT NULL,
	code_postal VARCHAR(5) NOT NULL,
	libelle VARCHAR(255) NOT NULL
);

/*All User's gets stored in APP_USER table*/
create table USER (
   id BIGINT NOT NULL AUTO_INCREMENT,
   version BIGINT  NULL DEFAULT NULL,
   sso_id VARCHAR(255) NOT NULL,
   password VARCHAR(100) NOT NULL,
   first_name VARCHAR(255) NOT NULL,
   last_name  VARCHAR(255) NOT NULL,
   email VARCHAR(255) NOT NULL,
   birth_date DATETIME NULL DEFAULT NULL,
   idCommune INT NULL DEFAULT NULL,
   PRIMARY KEY (id),
   UNIQUE (sso_id),
   INDEX `FK30vdyr7ek346qgdk0wcigw1ew` (`idcommune`),
	CONSTRAINT `FK30vdyr7ek346qgdk0wcigw1ew` FOREIGN KEY (`idcommune`) REFERENCES `commune` (`id`)
);

/* USER_PROFILE table contains all possible roles */
create table USER_PROFILE(
   id BIGINT NOT NULL AUTO_INCREMENT,
   type VARCHAR(30) NOT NULL,
   PRIMARY KEY (id),
   UNIQUE (type)
);

/* JOIN TABLE for MANY-TO-MANY relationship*/
CREATE TABLE APP_USER_USER_PROFILE (
    user_id BIGINT NOT NULL,
    user_profile_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, user_profile_id),
    CONSTRAINT FK_APP_USER FOREIGN KEY (user_id) REFERENCES USER (id),
    CONSTRAINT FK_USER_PROFILE FOREIGN KEY (user_profile_id) REFERENCES USER_PROFILE (id)
);

/* Populate USER_PROFILE Table */
INSERT INTO USER_PROFILE(type)
VALUES ('ROLE_USER');

INSERT INTO USER_PROFILE(type)
VALUES ('ROLE_ADMIN');

INSERT INTO USER_PROFILE(type)
VALUES ('ROLE_DBA');

INSERT INTO COMMUNE(libelle,code_postal,code_insee) VALUES('TOURS', '37000','37001');
INSERT INTO COMMUNE(libelle,code_postal,code_insee) VALUES('TOURS', '37100','37002');
INSERT INTO COMMUNE(libelle,code_postal,code_insee) VALUES('GOUVIEUX', '60270','60270');
INSERT INTO COMMUNE(libelle,code_postal,code_insee) VALUES('CREIL', '60100','60100');
INSERT INTO COMMUNE(libelle,code_postal,code_insee) VALUES('TOULON', '83000','83001');


/* Create persistent_logins Table used to store rememberme related stuff*/
CREATE TABLE persistent_logins (
    username VARCHAR(64) NOT NULL,
    series VARCHAR(64) NOT NULL,
    token VARCHAR(64) NOT NULL,
    last_used TIMESTAMP NOT NULL,
    PRIMARY KEY (series)
);