-- Indexes for primary keys have been explicitly created.

-- ---------- Table for validation queries from the connection pool. ----------

DROP   TABLE PingTable;
CREATE TABLE PingTable (foo CHAR(1));

---------------------------------- Drops ---------------------------------------

DROP TABLE UserProfile;

-- ----------------------------- UserProfile -----------------------------------

CREATE TABLE UserProfile (
    usrId       BIGINT NOT NULL AUTO_INCREMENT,
    loginName   VARCHAR(30) COLLATE latin1_bin NOT NULL,
    enPassword  VARCHAR(13) NOT NULL,
    firstName   VARCHAR(30) NOT NULL,
    lastName    VARCHAR(40) NOT NULL,
    road        VARCHAR(40) NULL,
    num         VARCHAR(40) NULL,
    postalCode  BIGINT  NULL,
    email       VARCHAR(60) NOT NULL,
    version     BIGINT,
    CONSTRAINT UserProfilePK PRIMARY KEY (usrId),
    CONSTRAINT LoginNameUniqueKey UNIQUE (loginName) ) 
    ENGINE = InnoDB;

CREATE INDEX UserProfileIndexByUsrId     ON UserProfile (usrId);
CREATE INDEX UserProfileIndexByLoginName ON UserProfile (loginName);

