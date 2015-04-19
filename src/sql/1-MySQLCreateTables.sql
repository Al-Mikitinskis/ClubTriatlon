/* ClubTriatlon: a web app to management of administrative work of a triathlon club
   Copyright (C) 2015 Alejandro Mikitinskis

   This program is free software; you can redistribute it and/or modify
   it under the terms of the GNU General Public License as published by
   the Free Software Foundation; either version 3 of the License, or
   (at your option) any later version.

   This program is distributed in the hope that it will be useful,
   but WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   GNU General Public License for more details.

   You should have received a copy of the GNU General Public License
   along with this program; if not, write to the Free Software Foundation,
   Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301  USA

  Contact here: alejandro.mikitinskis@udc.es */

-- Indexes for primary keys have been explicitly created

-- Table for validation queries from the connection pool -----------------

DROP   TABLE PingTable;
CREATE TABLE PingTable (foo CHAR(1));

-- Drops -----------------------------------------------------------------

DROP TABLE UserProfile;
DROP TABLE Role;

-- Role ------------------------------------------------------------------

CREATE TABLE Role (
    roleId BIGINT NOT NULL AUTO_INCREMENT,
    name   VARCHAR(13) NOT NULL,
    CONSTRAINT RolePK PRIMARY KEY (roleId),
    CONSTRAINT RoleNameUniqueKey UNIQUE (name) ) 
    ENGINE = InnoDB;

CREATE INDEX RoleIndexByRoleId ON Role (roleId);
CREATE INDEX RoleIndexByName   ON Role (name);

-- UserProfile -----------------------------------------------------------

CREATE TABLE UserProfile (
    usrId       BIGINT NOT NULL AUTO_INCREMENT,
    email       VARCHAR(30) COLLATE latin1_bin NOT NULL,
    enPassword  VARCHAR(13) NOT NULL,
    name        VARCHAR(40) NOT NULL,
    birthDate   VARCHAR(11) NULL,
    phoneNumber VARCHAR(10) NULL,
    account     VARCHAR(40) NOT NULL,
    role        BIGINT NOT NULL,
    CONSTRAINT UserProfilePK PRIMARY KEY (usrId),
    CONSTRAINT EmailUniqueKey UNIQUE (email),
    CONSTRAINT RoleIdFK FOREIGN KEY(role)
        REFERENCES Role(roleId) ON DELETE CASCADE ) 
    ENGINE = InnoDB;

CREATE INDEX UserProfileIndexByUsrId ON UserProfile (usrId);
CREATE INDEX UserProfileIndexByEmail ON UserProfile (email);


