/*  Return all rows from all columns in the Sales.SalesOrderHeader table  */
USE AdventureWorks2016;
GO
--1 Step
SELECT * FROM Sales.SalesOrderHeader;
GO

/*  Return all rows from the SalesOrderID and OrderDate columns from 
the Sales.SalesOrderHeader table 2Step */

--2 step
SELECT SalesOrderID
      ,OrderDate
FROM Sales.SalesOrderHeader;
GO

/*  Return only rows from the SalesOrderID, OrderDate, and SalesPersonID columns 
for which the SalespersonID = 282  */

--3
SELECT SalesOrderID
      ,OrderDate, SalesPersonID
FROM Sales.SalesOrderHeader
WHERE SalespersonID = 282;
GO

/*  Return only rows from the SalesOrderID, OrderDate, and SalesPersonID columns 
for which the SalespersonID > 282  */

--4
SELECT SalesOrderID
      ,OrderDate, SalesPersonID
FROM Sales.SalesOrderHeader
WHERE SalespersonID > 282;
GO

/*  Return only rows from the SalesOrderID and OrderDate columns 
for which the SalespersonID = 282 and the orderdate is from the year 2013  */

--5
SELECT SalesOrderID
      ,OrderDate, SalespersonID
FROM Sales.SalesOrderHeader
WHERE SalespersonID = 282 AND Year(OrderDate) = '2013';
GO