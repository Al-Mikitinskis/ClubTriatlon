-- ----------------------------------------------------------------------------
-- Put here INSERT statements for inserting data required by the application
-- in the "pojo" database.
-------------------------------------------------------------------------------

Delete from UserProfile;

-- ----------------- UserProfile ----------------------------------------------

Insert into UserProfile (usrId, loginName, enPassword, firstName, lastName, road, num, postalCode, email, version) values (1, 'mmi', 'JKQWref7fmsOg', 'Miguel', 'de Cervantes', 'c/ Don Quijote', '5B', 15008,'migueldecervantes@gmail.com', 1);
Insert into UserProfile (usrId, loginName, enPassword, firstName, lastName, road, num, postalCode, email, version) values (2, 'aao', 'NAlLc2Tm4P21M', 'Antonio', 'Alcantara Cuentame', 'c/ Real', '29B', 15008, 'Antonio@gmail.com', 1);
