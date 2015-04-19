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

-- ----------------------------------------------------------------------------
-- Put here INSERT statements for inserting data required by the application.
-------------------------------------------------------------------------------

DELETE FROM Role;
DELETE FROM UserProfile;

-- Role -----------------------------------------------------------------------

INSERT INTO Role (roleId, name) VALUES (1, 'Administrador');
INSERT INTO Role (roleId, name) VALUES (2, 'Usuario');

-- UserProfile ----------------------------------------------------------------
-- Passwords ==> 'admin' = 'BLaNeeniglZgQ' 'user' = 'ABsFuZ3PufkXY'

INSERT INTO UserProfile (usrId, email, enPassword, name, birthDate, phoneNumber, account, role) 
VALUES (1, 'admin2@triatlon.com', 'BLaNeeniglZgQ', 'Administrador1', '1975/01/23', 601601601, '0049005203650360', 1);
INSERT INTO UserProfile (usrId, email, enPassword, name, birthDate, phoneNumber, account, role) 
VALUES (2, 'admin1@triatlon.com', 'BLaNeeniglZgQ', 'Administrador2', '1977/03/19', 633622611, '0049005201177908', 1);
INSERT INTO UserProfile (usrId, email, enPassword, name, birthDate, phoneNumber, account, role) 
VALUES (3, 'user1A@gmail.com', 'ABsFuZ3PufkXY', 'Usuario 1A', '1979/07/15', 666000258, '0049005202540258', 2);
INSERT INTO UserProfile (usrId, email, enPassword, name, birthDate, phoneNumber, account, role) 
VALUES (4, 'user2B@gmail.com', 'ABsFuZ3PufkXY', 'Usuario 2B', '1983/03/22', 666210210, '0049005225747471', 2);
INSERT INTO UserProfile (usrId, email, enPassword, name, birthDate, phoneNumber, account, role) 
VALUES (5, 'user3C@gmail.com', 'ABsFuZ3PufkXY', 'Usuario 3C', '1991/02/03', 667557447, '0049005200025883', 2);

