CREATE DATABASE OceanViewResortDB;
GO

USE OceanViewResortDB;
GO

CREATE TABLE Users (
    id INT IDENTITY(1,1) PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    role VARCHAR(20) NOT NULL CHECK (role IN ('RECEPTIONIST', 'MANAGER'))
);
GO

CREATE TABLE Staff (
    id INT PRIMARY KEY,
    CONSTRAINT FK_Staff_Users FOREIGN KEY (id) REFERENCES Users(id) ON DELETE CASCADE
);
GO

CREATE TABLE Reservations (
    res_number INT IDENTITY(1000,1) PRIMARY KEY,
    guest_name VARCHAR(100) NOT NULL,
    address VARCHAR(255) NOT NULL,
    contact VARCHAR(30) NOT NULL,
    room_type VARCHAR(50) NOT NULL,
    checkin_date DATE NOT NULL,
    checkout_date DATE NOT NULL,
    room_price DECIMAL(10,2) NOT NULL
);
GO

INSERT INTO Users (name, username, password, email, role)
VALUES ('Hotel Manager', 'admin', 'admin123', 'admin@oceanview.local', 'MANAGER');
GO
