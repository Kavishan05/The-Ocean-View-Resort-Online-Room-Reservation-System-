

USE [master];
GO

IF NOT EXISTS (SELECT 1 FROM sys.sql_logins WHERE name = 'ocean_user')
BEGIN
    CREATE LOGIN [ocean_user] WITH PASSWORD = 'ChangeThisPassword!123', CHECK_POLICY = OFF;
END
GO

IF DB_ID('OceanViewResortDB') IS NULL
BEGIN
    PRINT 'Database OceanViewResortDB does not exist. Run schema.sql first.';
END
GO

USE [OceanViewResortDB];
GO

IF NOT EXISTS (SELECT 1 FROM sys.database_principals WHERE name = 'ocean_user')
BEGIN
    CREATE USER [ocean_user] FOR LOGIN [ocean_user];
END
GO

EXEC sp_addrolemember 'db_owner', 'ocean_user';
GO
