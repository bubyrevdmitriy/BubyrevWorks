--Create the stored procedure
USE OrdersDatabase;
GO

CREATE PROCEDURE USP_InsertOrders
@OrderID int, @CustomerID int 
AS
INSERT INTO Sales.Orders (OrderID, CustomerID)
VALUES
(@OrderID, @CustomerID);
GO


--Test the stored procedure
EXEC USP_InsertOrders 150,2;
GO
SELECT * FROM [OrdersDatabase].[Sales].[Orders] 
WHERE OrderID = 150
