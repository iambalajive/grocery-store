# grocery-store
grocery-store

# Steps to import the project
a)Clone it and import the project
b)mvn clean install
c)Run App.java


# Items
Every item has a item code and parent item code
Parent item code refers to the main category of item eg:Chips,Drinks,Biscuits
Item code refers to the sub item category eg Coke , Pepsi

| Parent item   | parent item code |
| ------------- | -------------    |
| Chips         | 1                |
| Drinks        | 2                |

| item name     | item code     | parent code |
| ------------- | ------------- | -------------
| Lays chips    | 11            |   1         |
| Balaji chips  | 12            |   1         |
| Coke          | 13            |   2         |
| Pepsi         | 14            |   2         |

For now all these codes are in json and not stored ANYWHERE. refer items.json
The above table is just a sample table . Please refer to items.json for the actual codes

# Handling discounts
For group discounts parent code is used
For individual items item code is directly used

# CAVEATS
This code doesnt handle any concurrent transactions
If there are insufficient items in the inventory , then exception is thrown
No logging framework used
More extensive test cases
