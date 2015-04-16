CREATE TABLE PingTable (foo CHAR(1));
CREATE TABLE UserProfile (
    usrId       BIGINT NOT NULL AUTO_INCREMENT,
    email       VARCHAR(30) COLLATE latin1_bin NOT NULL,
    enPassword  VARCHAR(13) NOT NULL,
    name        VARCHAR(40) NOT NULL,
    birthDate   VARCHAR(11) NULL,
    phoneNumber VARCHAR(10) NULL,
    account     VARCHAR(40) NOT NULL,
    CONSTRAINT UserProfilePK PRIMARY KEY (usrId),
    CONSTRAINT EmailUniqueKey UNIQUE (email) ) 
    ENGINE = InnoDB;

CREATE INDEX UserProfileIndexByUsrId ON UserProfile (usrId);
CREATE INDEX UserProfileIndexByEmail ON UserProfile (email);

CREATE TABLE Role (
    name   VARCHAR(11) NOT NULL,
    userId BIGINT NOT NULL,
    CONSTRAINT RoleUserProfileFK FOREIGN KEY (userId)
        REFERENCES UserProfile(usrId) ) 
    ENGINE = InnoDB;

CREATE INDEX RoleIndexByRoleAndUserId ON Role (name, userId);


INSERT INTO UserProfile (usrId, email, enPassword, name, birthDate, phoneNumber, account) 
VALUES (1, 'admin2@triatlon.com', 'BLaNeeniglZgQ', 'Administrador1', '1975/01/23', 601601601, '0049005203650360');
INSERT INTO UserProfile (usrId, email, enPassword, name, birthDate, phoneNumber, account) 
VALUES (2, 'admin1@triatlon.com', 'BLaNeeniglZgQ', 'Administrador2', '1977/03/19', 633622611, '0049005201177908');
INSERT INTO UserProfile (usrId, email, enPassword, name, birthDate, phoneNumber, account) 
VALUES (3, 'user1A@gmail.com', 'ABsFuZ3PufkXY', 'Usuario 1A', '1979/07/15', 666000258, '0049005202540258');
INSERT INTO UserProfile (usrId, email, enPassword, name, birthDate, phoneNumber, account) 
VALUES (4, 'user2B@gmail.com', 'ABsFuZ3PufkXY', 'Usuario 2B', '1983/03/22', 666210210, '0049005225747471');
INSERT INTO UserProfile (usrId, email, enPassword, name, birthDate, phoneNumber, account) 
VALUES (5, 'user3C@gmail.com', 'ABsFuZ3PufkXY', 'Usuario 3C', '1991/02/03', 667557447, '0049005200025883');

INSERT INTO Role (name, userId) VALUES ('admin', 1);
INSERT INTO Role (name, userId) VALUES ('admin', 2);
INSERT INTO Role (name, userId) VALUES ('user',  3);
INSERT INTO Role (name, userId) VALUES ('user',  4);
INSERT INTO Role (name, userId) VALUES ('user',  5);


