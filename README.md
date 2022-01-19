#  
# LIMS  (Lab Inventory Management System)  

## Frontend - JAVA FX

### Pages

>Login Page: To log into the system.   
>  
>Inventory Page :  To add/delete/issue/return/show the items in the inventory.  
>  
>Credential Page: To add/delete/change the login credentials for users/admins.  
>    
>Logs Page: Displays the log table.  
>  
>Return Page:  Shows the issued items & has an option to return them.  


Fxml files have been designed using Scene Builder.  

The pages have the respective .fxml files stored @ src.main.resources.com.example.project.  

## Backend - JAVA

The pages have their respective controller classes @ src.main.java.com.example.project.  

The Inventory/Credential/Logs/Return pages have their respective tables in their own seperate classes @ src.main.java.com.example.project.  

Last was the database connection class  @ src.main.java.com.example.project.DbConnection

## Database - SQLITE

The database was built using SQLite Studio.   

It has 3 tables - inventory, credential, logs.  

The Database connectivity was done using a sqlite-JDBC-driver library @ RESOURCES/sqlite-jdbc-3.21.0.jar  

The database itself @ src/main/java/com/example/Project/ILMS.sqlite  



# To run the clone:  

#### Change  


>  'jdbc:sqlite:/home/user/IdeaProjects/demo1/src/main/java/com/example/Project/ILMS.sqlite'   
    @ src/main/java/com/example/Project/DbConnection.java 12:59  
    to 'jdbc:sqlite:/$$$/src/main/java/com/example/Project/ILMS.sqlite'    
    
    

#### Add  

> [Java SDK 17.0.1] (https://gluonhq.com/products/javafx/)
