--Create and populate LineItems table
USE [OrdersDatabase];
GO

use [OrdersDatabase] EXEC sp_changedbowner 'sa'

CREATE TABLE Sales.LineItems
(OrderID INT NOT NULL
,ProductID INT NOT NULL
,UnitPrice MONEY NOT NULL
,Quantity SMALLINT NOT NULL
CONSTRAINT PK_LineItems PRIMARY KEY (OrderID,ProductID)
CONSTRAINT FK_LineItems_Orders FOREIGN KEY (OrderID)
REFERENCES Sales.Orders (OrderID));
GO

--Create a DEFAULT constraint on the Sales.Orders table
ALTER TABLE Sales.Orders
ADD CONSTRAINT DF_OrderDate DEFAULT GETDATE() FOR OrderDate;
GO

--Test the DEFAULT constraint
INSERT INTO Sales.Orders
VALUES(110, 3, DEFAULT);
GO

SELECT * FROM Sales.Orders
WHERE OrderID = 110;
GO




