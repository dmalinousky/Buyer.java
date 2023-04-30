# DROP TABLE IF EXISTS Warehouses;
#
# CREATE TABLE Warehouses(
#                            Id INT(10) NOT NULL AUTO_INCREMENT PRIMARY KEY,
#                            EntityTitle VARCHAR(100) NOT NULL DEFAULT('Empty'),
#                            RegistrationNumber VARCHAR(30) NOT NULL,
#                            LegalAddress VARCHAR(100) NOT NULL DEFAULT('Empty'),
#                            RealAddress VARCHAR(100) NOT NULL DEFAULT('Empty'),
#                            GpsCoordinates VARCHAR(30) NOT NULL DEFAULT('Empty'),
#                            PhoneNumber VARCHAR(20) NOT NULL DEFAULT('Empty'),
#                            EmailAddress VARCHAR(30) NOT NULL DEFAULT('Empty'),
#                            IsTemperatureWH BOOLEAN NOT NULL DEFAULT('FALSE'),
#                            IsCustomWH BOOLEAN NOT NULL DEFAULT('FALSE'),
#                            SpaceFree DECIMAL(10, 2) NOT NULL DEFAULT('0'),
#                            Balance DECIMAL(10, 2) NOT NULL DEFAULT('0'),
#                            Status VARCHAR(10) NOT NULL DEFAULT('None'),
#                            Login VARCHAR(30) NOT NULL,
#                            Password VARCHAR(30) NOT NULL,
#                            WarehouseId BIGINT NOT NULL,
#                            CONSTRAINT `Warehouses_chk1` CHECK (EntityTitle NOT LIKE ''),
#                            CONSTRAINT `Warehouses_chk2` CHECK (RegistrationNumber NOT LIKE ''),
#                            CONSTRAINT `Warehouses_chk3` CHECK (LegalAddress NOT LIKE ''),
#                            CONSTRAINT `Warehouses_chk4` CHECK (RealAddress NOT LIKE ''),
#                            CONSTRAINT `Warehouses_chk5` CHECK (GpsCoordinates NOT LIKE ''),
#                            CONSTRAINT `Warehouses_chk6` CHECK (PhoneNumber NOT LIKE ''),
#                            CONSTRAINT `Warehouses_chk7` CHECK (EmailAddress NOT LIKE ''),
#                            CONSTRAINT `Warehouses_chk8` CHECK (SpaceFree >= 0),
#                            CONSTRAINT `Warehouses_chk9` CHECK (Status IN ('None', 'Active', 'Inactive', 'Blocked'))
# );