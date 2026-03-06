USE OceanViewResortDB;
GO

IF COL_LENGTH('Users', 'email') IS NULL
BEGIN
    ALTER TABLE Users ADD email VARCHAR(100) NULL;
END
GO

;WITH u AS (
    SELECT id
    FROM Users
    WHERE email IS NULL OR LTRIM(RTRIM(email)) = ''
)
UPDATE Users
SET email = CONCAT('user_', id, '@oceanview.local')
WHERE id IN (SELECT id FROM u);
GO

IF EXISTS (
    SELECT email
    FROM Users
    GROUP BY email
    HAVING COUNT(*) > 1
)
BEGIN
    PRINT 'Duplicate emails exist. Resolve duplicates before adding UNIQUE constraint.';
END
ELSE
BEGIN
    -- 4) Add UNIQUE constraint if not present
    IF NOT EXISTS (
        SELECT 1
        FROM sys.indexes i
        JOIN sys.index_columns ic ON i.object_id = ic.object_id AND i.index_id = ic.index_id
        JOIN sys.columns c ON c.object_id = ic.object_id AND c.column_id = ic.column_id
        WHERE i.object_id = OBJECT_ID('Users')
          AND i.is_unique = 1
          AND c.name = 'email'
    )
    BEGIN
        ALTER TABLE Users ADD CONSTRAINT UQ_Users_Email UNIQUE (email);
    END
END
GO
