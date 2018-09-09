# grocery-store
grocery-store

# Steps to import the project
a)Clone it and import the project </br>
b)mvn clean install </br>
c)Run App.java </br>


# Items
Every item has a item code and parent item code </br>
Parent item code refers to the main category of item eg:Chips,Drinks,Biscuits </br>
Item code refers to the sub item category eg Coke , Pepsi </br>

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

For now all these codes are in json and not stored ANYWHERE. refer items.json </br>
The above table is just a sample table . Please refer to items.json for the actual codes </br>

# Handling discounts
For group discounts parent code is used </br>
For individual items item code is directly used </br>

# CAVEATS
This code doesnt handle any concurrent transactions </br>
If there are insufficient items in the inventory , then exception is thrown </br>
No logging framework used </br>
More extensive test cases </br>
Right now there are only two or three products in item.json for demo purposes . Can be extended
