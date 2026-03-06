USE OceanViewResortDB;
GO

IF NOT EXISTS (SELECT 1 FROM Users WHERE username = 'admin')
BEGIN
    INSERT INTO Users (name, username, password, email, role)
    VALUES ('Hotel Manager', 'admin', 'admin123', 'admin@oceanview.local', 'MANAGER');
END
GO

IF NOT EXISTS (SELECT 1 FROM Users WHERE username = 'reception1')
BEGIN
    INSERT INTO Users (name, username, password, email, role)
    VALUES ('Receptionist One', 'reception1', 'welcome123', 'reception1@oceanview.local', 'RECEPTIONIST');
END
GO

IF EXISTS (SELECT 1 FROM Users WHERE username = 'reception1')
BEGIN
    DECLARE @rid INT;
    SELECT @rid = id FROM Users WHERE username = 'reception1';
    IF NOT EXISTS (SELECT 1 FROM Staff WHERE id = @rid)
    BEGIN
        INSERT INTO Staff (id) VALUES (@rid);
    END
END
GO

IF NOT EXISTS (SELECT 1 FROM Reservations)
BEGIN
    INSERT INTO Reservations (guest_name, address, contact, room_type, checkin_date, checkout_date, room_price)
    VALUES
      ('John Silva', '12 Beach Road, Colombo', '0771234567', 'Deluxe Sea View', '2026-03-10', '2026-03-12', 25000.00),
      ('Kamal Perera', '45 Palm Street, Galle', '0717654321', 'Standard', '2026-03-15', '2026-03-18', 15000.00),
      ('Maria Fernando', '88 Ocean Ave, Negombo', '0721112223', 'Suite', '2026-03-20', '2026-03-23', 40000.00);
END
GO
