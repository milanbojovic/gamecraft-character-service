-- Create a new database
IF NOT EXISTS (SELECT * FROM sys.databases WHERE name = 'character_db')
    BEGIN
        CREATE DATABASE character_db;
    END;
GO

-- Switch context to the new database
USE character_db;
GO


-- Create a new schema in the database
IF NOT EXISTS (SELECT * FROM sys.schemas WHERE name = 'character_schema')
    BEGIN
        EXEC('CREATE SCHEMA character_schema');
    END;
GO