USE OceanViewResortDB;
GO

IF COL_LENGTH('Users', 'email') IS NULL
BEGIN
    ALTER TABLE Users ADD email VARCHAR(100) NULL;
END
GO


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
    -- Create unique constraint only if it doesn't exist
    IF EXISTS (SELECT 1 FROM sys.columns WHERE object_id = OBJECT_ID('Users') AND name = 'email')
    BEGIN
        -- Make sure there are no duplicates before enabling UNIQUE
        -- You can manually edit emails if duplicates exist.
        ALTER TABLE Users ADD CONSTRAINT UQ_Users_Email UNIQUE (email);
    END
END
GO
