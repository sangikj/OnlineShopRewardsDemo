DROP TABLE IF EXISTS OrderTransaction;
CREATE TABLE Order_Transaction (
  transactionId VARCHAR(255) NOT NULL PRIMARY KEY,
  customerId VARCHAR(255) NOT NULL,
  productId VARCHAR(255) NOT NULL,
  quantity INT NOT NULL,
  totalAmount DECIMAL(10,2) NOT NULL,
  transactionDate DATE NOT NULL
);
