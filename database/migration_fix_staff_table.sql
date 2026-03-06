USE OceanViewResortDB;
GO


IF OBJECT_ID('dbo.Staff', 'U') IS NOT NULL
BEGIN
    DECLARE @isIdentity BIT = 0;
    SELECT @isIdentity = c.is_identity
    FROM sys.columns c
    WHERE c.object_id = OBJECT_ID('dbo.Staff') AND c.name = 'id';

    IF (@isIdentity = 1)
    BEGIN
        IF OBJECT_ID('dbo.Staff_new', 'U') IS NOT NULL
            DROP TABLE dbo.Staff_new;

        CREATE TABLE dbo.Staff_new (
            id INT NOT NULL PRIMARY KEY,
            CONSTRAINT FK_Staff_Users FOREIGN KEY (id) REFERENCES dbo.Users(id) ON DELETE CASCADE
        );

        -- Populate from existing Users table (all receptionists become staff)
        INSERT INTO dbo.Staff_new (id)
        SELECT u.id
        FROM dbo.Users u
        WHERE UPPER(LTRIM(RTRIM(u.role))) IN ('RECEPTIONIST', 'STAFF');

        DROP TABLE dbo.Staff;
        EXEC sp_rename 'dbo.Staff_new', 'Staff';
    END
END
ELSE
BEGIN
    CREATE TABLE dbo.Staff (
        id INT NOT NULL PRIMARY KEY,
        CONSTRAINT FK_Staff_Users FOREIGN KEY (id) REFERENCES dbo.Users(id) ON DELETE CASCADE
    );

    INSERT INTO dbo.Staff (id)
    SELECT u.id
    FROM dbo.Users u
    WHERE UPPER(LTRIM(RTRIM(u.role))) IN ('RECEPTIONIST', 'STAFF');
END
GO
