-- Indexes for primary keys have been explicitly created

-- Table for validation queries from the connection pool -----------------

DROP   TABLE PingTable;
CREATE TABLE PingTable (foo CHAR(1));

-- Drops -----------------------------------------------------------------

DROP TABLE Role;
DROP TABLE UserProfile;

-- UserProfile -----------------------------------------------------------

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

-- Role ------------------------------------------------------------------

CREATE TABLE Role (
    name   VARCHAR(11) NOT NULL,
    userId BIGINT NOT NULL,
    CONSTRAINT RoleUserProfileFK FOREIGN KEY (userId)
        REFERENCES UserProfile(usrId) ) 
    ENGINE = InnoDB;

CREATE INDEX RoleIndexByRoleAndUserId ON Role (name, userId);
