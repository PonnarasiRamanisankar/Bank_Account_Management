🏦 Bank Account Transfer System (Java + Oracle)

A Java JDBC–based bank account management system that supports secure fund transfers between accounts using an Oracle database.
The system validates account balance before transfer and throws an Insufficient Funds Exception when the balance is not enough.

📌 Key Features

            Transfer amount from one bank account to another
            
            Automatic balance update after successful transfer
            
            Displays Insufficient Funds error if balance is low
            
            Uses custom exception handling
            
            Implements clean layered architecture (Bean → DAO → Service → Main)
            
            Oracle database integration using JDBC

🏗️ Project Structure
         
             com.wipro.bank
            │
            ├── bean
            │   └── TransferBean.java
            │
            ├── dao
            │   └── BankDao.java
            │
            ├── service
            │   └── BankService.java
            │
            ├── util
            │   ├── DBUtil.java
            │   └── InsufficientFundsException.java
            │
            └── main
                └── BankMain.java
🧩 Package Description

🔹 bean
          TransferBean.java
          Holds account details like account number, balance, and transfer amount.

🔹 dao

          BankDao.java
           Handles database operations such as:          
                Fetching balance          
                Updating sender and receiver accounts

🔹 service
          BankService.java
            Contains business logic:          
                Validates sufficient balance          
                Performs transfer operation 
                Throws InsufficientFundsException if needed

🔹 util
          
          DBUtil.java
          Manages Oracle database connection
          InsufficientFundsException.java
          Custom exception for insufficient balance

🔹 main

          BankMain.java
          Entry point of the application
          Executes account transfer scenarios


<img width="924" height="514" alt="image" src="https://github.com/user-attachments/assets/73378e23-d46b-4577-918b-66d7b3a89c10" />
<img width="724" height="339" alt="image" src="https://github.com/user-attachments/assets/6d43d91d-d3ff-44b7-b3e8-aa0ce6466d2a" />
<img width="997" height="434" alt="image" src="https://github.com/user-attachments/assets/aaa23f70-abaf-4f58-b5f2-075855e6985d" />
<img width="1280" height="631" alt="image" src="https://github.com/user-attachments/assets/0904afff-8632-496a-a478-7849c25df095" />
