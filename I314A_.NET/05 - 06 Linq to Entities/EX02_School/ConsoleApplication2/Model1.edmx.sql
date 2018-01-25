
-- --------------------------------------------------
-- Entity Designer DDL Script for SQL Server 2005, 2008, 2012 and Azure
-- --------------------------------------------------
-- Date Created: 01/25/2018 01:46:31
-- Generated from EDMX file: C:\Users\sacre\Documents\3BIN\I314A_.NET\05 - 06 Linq to Entities\ConsoleApplication2\ConsoleApplication2\Model1.edmx
-- --------------------------------------------------

SET QUOTED_IDENTIFIER OFF;
GO
USE [test];
GO
IF SCHEMA_ID(N'dbo') IS NULL EXECUTE(N'CREATE SCHEMA [dbo]');
GO

-- --------------------------------------------------
-- Dropping existing FOREIGN KEY constraints
-- --------------------------------------------------

IF OBJECT_ID(N'[dbo].[FK_CourseProfessor]', 'F') IS NOT NULL
    ALTER TABLE [dbo].[Courses] DROP CONSTRAINT [FK_CourseProfessor];
GO
IF OBJECT_ID(N'[dbo].[FK_ProfessorSection]', 'F') IS NOT NULL
    ALTER TABLE [dbo].[Professors] DROP CONSTRAINT [FK_ProfessorSection];
GO
IF OBJECT_ID(N'[dbo].[FK_SectionStudent]', 'F') IS NOT NULL
    ALTER TABLE [dbo].[Students] DROP CONSTRAINT [FK_SectionStudent];
GO

-- --------------------------------------------------
-- Dropping existing tables
-- --------------------------------------------------

IF OBJECT_ID(N'[dbo].[Courses]', 'U') IS NOT NULL
    DROP TABLE [dbo].[Courses];
GO
IF OBJECT_ID(N'[dbo].[Professors]', 'U') IS NOT NULL
    DROP TABLE [dbo].[Professors];
GO
IF OBJECT_ID(N'[dbo].[Sections]', 'U') IS NOT NULL
    DROP TABLE [dbo].[Sections];
GO
IF OBJECT_ID(N'[dbo].[Students]', 'U') IS NOT NULL
    DROP TABLE [dbo].[Students];
GO

-- --------------------------------------------------
-- Creating all tables
-- --------------------------------------------------

-- Creating table 'Courses'
CREATE TABLE [dbo].[Courses] (
    [Course_Id] int IDENTITY(1,1) NOT NULL,
    [Name] nvarchar(max)  NOT NULL,
    [Professor_Professor_Id] int  NOT NULL
);
GO

-- Creating table 'Professors'
CREATE TABLE [dbo].[Professors] (
    [Professor_Id] int IDENTITY(1,1) NOT NULL,
    [Name] nvarchar(max)  NOT NULL,
    [Firstname] nvarchar(max)  NOT NULL,
    [Section_Section_Id] int  NOT NULL
);
GO

-- Creating table 'Sections'
CREATE TABLE [dbo].[Sections] (
    [Section_Id] int IDENTITY(1,1) NOT NULL,
    [Name] nvarchar(max)  NOT NULL
);
GO

-- Creating table 'Students'
CREATE TABLE [dbo].[Students] (
    [Student_Id] int IDENTITY(1,1) NOT NULL,
    [Name] nvarchar(max)  NOT NULL,
    [Firstname] nvarchar(max)  NOT NULL,
    [YearResult] nvarchar(max)  NOT NULL,
    [SectionSection_Id] int  NULL
);
GO

-- --------------------------------------------------
-- Creating all PRIMARY KEY constraints
-- --------------------------------------------------

-- Creating primary key on [Course_Id] in table 'Courses'
ALTER TABLE [dbo].[Courses]
ADD CONSTRAINT [PK_Courses]
    PRIMARY KEY CLUSTERED ([Course_Id] ASC);
GO

-- Creating primary key on [Professor_Id] in table 'Professors'
ALTER TABLE [dbo].[Professors]
ADD CONSTRAINT [PK_Professors]
    PRIMARY KEY CLUSTERED ([Professor_Id] ASC);
GO

-- Creating primary key on [Section_Id] in table 'Sections'
ALTER TABLE [dbo].[Sections]
ADD CONSTRAINT [PK_Sections]
    PRIMARY KEY CLUSTERED ([Section_Id] ASC);
GO

-- Creating primary key on [Student_Id] in table 'Students'
ALTER TABLE [dbo].[Students]
ADD CONSTRAINT [PK_Students]
    PRIMARY KEY CLUSTERED ([Student_Id] ASC);
GO

-- --------------------------------------------------
-- Creating all FOREIGN KEY constraints
-- --------------------------------------------------

-- Creating foreign key on [Professor_Professor_Id] in table 'Courses'
ALTER TABLE [dbo].[Courses]
ADD CONSTRAINT [FK_CourseProfessor]
    FOREIGN KEY ([Professor_Professor_Id])
    REFERENCES [dbo].[Professors]
        ([Professor_Id])
    ON DELETE NO ACTION ON UPDATE NO ACTION;
GO

-- Creating non-clustered index for FOREIGN KEY 'FK_CourseProfessor'
CREATE INDEX [IX_FK_CourseProfessor]
ON [dbo].[Courses]
    ([Professor_Professor_Id]);
GO

-- Creating foreign key on [Section_Section_Id] in table 'Professors'
ALTER TABLE [dbo].[Professors]
ADD CONSTRAINT [FK_ProfessorSection]
    FOREIGN KEY ([Section_Section_Id])
    REFERENCES [dbo].[Sections]
        ([Section_Id])
    ON DELETE NO ACTION ON UPDATE NO ACTION;
GO

-- Creating non-clustered index for FOREIGN KEY 'FK_ProfessorSection'
CREATE INDEX [IX_FK_ProfessorSection]
ON [dbo].[Professors]
    ([Section_Section_Id]);
GO

-- Creating foreign key on [SectionSection_Id] in table 'Students'
ALTER TABLE [dbo].[Students]
ADD CONSTRAINT [FK_SectionStudent]
    FOREIGN KEY ([SectionSection_Id])
    REFERENCES [dbo].[Sections]
        ([Section_Id])
    ON DELETE NO ACTION ON UPDATE NO ACTION;
GO

-- Creating non-clustered index for FOREIGN KEY 'FK_SectionStudent'
CREATE INDEX [IX_FK_SectionStudent]
ON [dbo].[Students]
    ([SectionSection_Id]);
GO

-- --------------------------------------------------
-- Script has ended
-- --------------------------------------------------