USE OceanViewResortDB;
GO

IF OBJECT_ID('dbo.Reservations', 'U') IS NULL
BEGIN
    CREATE TABLE dbo.Reservations (
        res_number INT IDENTITY(1000,1) PRIMARY KEY,
        guest_name VARCHAR(100) NOT NULL,
        address VARCHAR(255) NOT NULL,
        contact VARCHAR(30) NOT NULL,
        room_type VARCHAR(50) NOT NULL,
        checkin_date DATE NOT NULL,
        checkout_date DATE NOT NULL,
        room_price DECIMAL(10,2) NOT NULL
    );
END
ELSE
BEGIN
    IF COL_LENGTH('dbo.Reservations', 'res_number') IS NULL
    BEGIN
        IF OBJECT_ID('dbo.Reservations_new', 'U') IS NOT NULL
            DROP TABLE dbo.Reservations_new;

        CREATE TABLE dbo.Reservations_new (
            res_number INT IDENTITY(1000,1) PRIMARY KEY,
            guest_name VARCHAR(100) NOT NULL,
            address VARCHAR(255) NOT NULL,
            contact VARCHAR(30) NOT NULL,
            room_type VARCHAR(50) NOT NULL,
            checkin_date DATE NOT NULL,
            checkout_date DATE NOT NULL,
            room_price DECIMAL(10,2) NOT NULL CONSTRAINT DF_Reservations_room_price DEFAULT (0)
        );

        INSERT INTO dbo.Reservations_new (guest_name, address, contact, room_type, checkin_date, checkout_date)
        SELECT
            CAST(guest_name AS VARCHAR(100)),
            CAST(address AS VARCHAR(255)),
            CAST(contact_no AS VARCHAR(30)),
            CAST(room_type AS VARCHAR(50)),
            check_in_date,
            check_out_date
        FROM dbo.Reservations;

        DROP TABLE dbo.Reservations;
        EXEC sp_rename 'dbo.Reservations_new', 'Reservations';
    END

    IF COL_LENGTH('dbo.Reservations', 'room_price') IS NULL
    BEGIN
        ALTER TABLE dbo.Reservations ADD room_price DECIMAL(10,2) NOT NULL CONSTRAINT DF_Reservations_room_price2 DEFAULT (0);
    END
END
GO
