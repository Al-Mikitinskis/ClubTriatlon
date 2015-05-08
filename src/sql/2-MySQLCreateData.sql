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
DELETE FROM Planning;
DELETE FROM Training;
DELETE FROM WeeklyPlanning;
DELETE FROM UserProfile;

-- WeeklyPlanning -------------------------------------------------------------
-- id, name, cDate

INSERT INTO WeeklyPlanning VALUES (1, '2015 - s.1', '2015-01-04');
INSERT INTO WeeklyPlanning VALUES (2, '2015 - s.2', '2015-01-11');
INSERT INTO WeeklyPlanning VALUES (3, '2015 - s.3', '2015-01-18');
INSERT INTO WeeklyPlanning VALUES (4, '2015 - s.4', '2015-01-25');
INSERT INTO WeeklyPlanning VALUES (5, '2015 - s.5', '2015-02-01');
INSERT INTO WeeklyPlanning VALUES (6, '2015 - s.6', '2015-02-08');
INSERT INTO WeeklyPlanning VALUES (7, '2015 - s.7', '2015-02-15');

-- Training -------------------------------------------------------------------
-- id, name, status

INSERT INTO Training VALUES (1, 'training1', 1);
INSERT INTO Training VALUES (2, 'training2', 1);
INSERT INTO Training VALUES (3, 'training3', 1);
INSERT INTO Training VALUES (4, 'Triatlón base', 1);
INSERT INTO Training VALUES (5, 'Carrera base', 1);
INSERT INTO Training VALUES (6, 'Ciclismo base', 0);
INSERT INTO Training VALUES (7, '2015 Peregrinos', 0);
INSERT INTO Training VALUES (8, 'Natación base', 0);

-- Planning -------------------------------------------------------------------
-- id, name, cDate, wpId, tId

INSERT INTO Planning (id, name, wpId, tId) VALUES (1, 'planning1', 7, 1);
INSERT INTO Planning (id, name, wpId, tId) VALUES (2, 'planning2', 7, 2);
INSERT INTO Planning (id, name, wpId, tId) VALUES (3, 'planning3', 7, 3);

-- Role -----------------------------------------------------------------------
-- roleId, name

INSERT INTO Role VALUES (1, 'Administrador');
INSERT INTO Role VALUES (2, 'Usuario');

-- UserProfile ----------------------------------------------------------------
-- usrId, email, enPassword, name, birthDate, phoneNumber, account, role
-- Passwords ==> 'admin' = 'BLaNeeniglZgQ' 'user' = 'ABsFuZ3PufkXY'

INSERT INTO UserProfile VALUES 
(1, 'admin1@triatlon.com', 'BLaNeeniglZgQ', 'Administrador1', '1975/01/23', 601601601, '0049005203650360', 1, NULL);
INSERT INTO UserProfile VALUES 
(2, 'admin2@triatlon.com', 'BLaNeeniglZgQ', 'Administrador2', '1977/03/19', 633622611, '0049005201177908', 1, NULL);
INSERT INTO UserProfile VALUES 
(5, 'user1@gmail.com', 'ABsFuZ3PufkXY', 'Usuario 1', '1983/03/22', 667557447, '0049005200025883', 2, 1);
INSERT INTO UserProfile VALUES 
(3, 'al.mikitinskis@gmail.com', 'ABsFuZ3PufkXY', 'Miki', '1991/02/03', 666000258, '0049005202540258', 2, 4);
INSERT INTO UserProfile VALUES 
(4, 'alejandro.mikitinskis@udc.es', 'ABsFuZ3PufkXY', 'Alejandro', '1991/02/03', 666000258, '0049005202540258', 2, 5);


