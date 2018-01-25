
-- --------------------------------------------------
-- Entity Designer DDL Script for SQL Server 2005, 2008, 2012 and Azure
-- --------------------------------------------------
-- Date Created: 07/16/2016 11:03:14
-- Generated from EDMX file: O:\TRAVAIL\COURS\DOTNET\2016-2017\05 - LINQ to Entities\05 - Solution\School\School\Model\Model1.edmx
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

IF OBJECT_ID(N'[dbo].[FK_ProfessorCourse]', 'F') IS NOT NULL
    ALTER TABLE [dbo].[CourseSet] DROP CONSTRAINT [FK_ProfessorCourse];
GO
IF OBJECT_ID(N'[dbo].[FK_SectionProfessor]', 'F') IS NOT NULL
    ALTER TABLE [dbo].[ProfessorSet] DROP CONSTRAINT [FK_SectionProfessor];
GO
IF OBJECT_ID(N'[dbo].[FK_StudentSection]', 'F') IS NOT NULL
    ALTER TABLE [dbo].[StudentSet] DROP CONSTRAINT [FK_StudentSection];
GO

-- --------------------------------------------------
-- Dropping existing tables
-- --------------------------------------------------

IF OBJECT_ID(N'[dbo].[CourseSet]', 'U') IS NOT NULL
    DROP TABLE [dbo].[CourseSet];
GO
IF OBJECT_ID(N'[dbo].[ProfessorSet]', 'U') IS NOT NULL
    DROP TABLE [dbo].[ProfessorSet];
GO
IF OBJECT_ID(N'[dbo].[SectionSet]', 'U') IS NOT NULL
    DROP TABLE [dbo].[SectionSet];
GO
IF OBJECT_ID(N'[dbo].[StudentSet]', 'U') IS NOT NULL
    DROP TABLE [dbo].[StudentSet];
GO

-- --------------------------------------------------
-- Creating all tables
-- --------------------------------------------------

-- Creating table 'CourseSet'
CREATE TABLE [dbo].[CourseSet] (
    [Course_Id] int IDENTITY(1,1) NOT NULL,
    [Name] nvarchar(max)  NOT NULL,
    [Professor_Professor_Id] int  NOT NULL
);
GO

-- Creating table 'ProfessorSet'
CREATE TABLE [dbo].[ProfessorSet] (
    [Professor_Id] int IDENTITY(1,1) NOT NULL,
    [Name] nvarchar(max)  NOT NULL,
    [Firstname] nvarchar(max)  NOT NULL,
    [Section_Section_Id] int  NOT NULL
);
GO

-- Creating table 'SectionSet'
CREATE TABLE [dbo].[SectionSet] (
    [Section_Id] int IDENTITY(1,1) NOT NULL,
    [Name] nvarchar(max)  NOT NULL
);
GO

-- Creating table 'StudentSet'
CREATE TABLE [dbo].[StudentSet] (
    [Student_Id] int IDENTITY(1,1) NOT NULL,
    [Name] nvarchar(max)  NOT NULL,
    [Firstname] nvarchar(max)  NOT NULL,
    [YearResult] bigint  NOT NULL,
    [Section_Section_Id] int  NULL
);
GO

-- --------------------------------------------------
-- Creating all PRIMARY KEY constraints
-- --------------------------------------------------

-- Creating primary key on [Course_Id] in table 'CourseSet'
ALTER TABLE [dbo].[CourseSet]
ADD CONSTRAINT [PK_CourseSet]
    PRIMARY KEY CLUSTERED ([Course_Id] ASC);
GO

-- Creating primary key on [Professor_Id] in table 'ProfessorSet'
ALTER TABLE [dbo].[ProfessorSet]
ADD CONSTRAINT [PK_ProfessorSet]
    PRIMARY KEY CLUSTERED ([Professor_Id] ASC);
GO

-- Creating primary key on [Section_Id] in table 'SectionSet'
ALTER TABLE [dbo].[SectionSet]
ADD CONSTRAINT [PK_SectionSet]
    PRIMARY KEY CLUSTERED ([Section_Id] ASC);
GO

-- Creating primary key on [Student_Id] in table 'StudentSet'
ALTER TABLE [dbo].[StudentSet]
ADD CONSTRAINT [PK_StudentSet]
    PRIMARY KEY CLUSTERED ([Student_Id] ASC);
GO

-- --------------------------------------------------
-- Creating all FOREIGN KEY constraints
-- --------------------------------------------------

-- Creating foreign key on [Professor_Professor_Id] in table 'CourseSet'
ALTER TABLE [dbo].[CourseSet]
ADD CONSTRAINT [FK_ProfessorCourse]
    FOREIGN KEY ([Professor_Professor_Id])
    REFERENCES [dbo].[ProfessorSet]
        ([Professor_Id])
    ON DELETE NO ACTION ON UPDATE NO ACTION;
GO

-- Creating non-clustered index for FOREIGN KEY 'FK_ProfessorCourse'
CREATE INDEX [IX_FK_ProfessorCourse]
ON [dbo].[CourseSet]
    ([Professor_Professor_Id]);
GO

-- Creating foreign key on [Section_Section_Id] in table 'ProfessorSet'
ALTER TABLE [dbo].[ProfessorSet]
ADD CONSTRAINT [FK_SectionProfessor]
    FOREIGN KEY ([Section_Section_Id])
    REFERENCES [dbo].[SectionSet]
        ([Section_Id])
    ON DELETE NO ACTION ON UPDATE NO ACTION;
GO

-- Creating non-clustered index for FOREIGN KEY 'FK_SectionProfessor'
CREATE INDEX [IX_FK_SectionProfessor]
ON [dbo].[ProfessorSet]
    ([Section_Section_Id]);
GO

-- Creating foreign key on [Section_Section_Id] in table 'StudentSet'
ALTER TABLE [dbo].[StudentSet]
ADD CONSTRAINT [FK_StudentSection]
    FOREIGN KEY ([Section_Section_Id])
    REFERENCES [dbo].[SectionSet]
        ([Section_Id])
    ON DELETE NO ACTION ON UPDATE NO ACTION;
GO

-- Creating non-clustered index for FOREIGN KEY 'FK_StudentSection'
CREATE INDEX [IX_FK_StudentSection]
ON [dbo].[StudentSet]
    ([Section_Section_Id]);
GO

-- --------------------------------------------------
-- Script has ended
-- --------------------------------------------------