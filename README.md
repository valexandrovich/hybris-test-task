# HYBRIS ACADEMY
### Technical Task

## Installing and running via Windows Terminal

*Requirements:*
* JDK 8+
* Maven
* MySQL

1. Download source code to you computer
```
git clone https://github.com/valexandrovich/hybris-test-task.git
```
2. Enter in project directory
```
cd .\hybris-test-task\
```

3. Check database and master-password settings in file ***hybris-test-task\src\main\resources\local.properties***

4. Build artifact
```
mvn clean ; mvn package
```
5. Start application 
```
java -jar .\target\hybris.jar
```

## User Manual
After the Application is launched we can use Main Menu (to select menu item enter the number ot item and press *Enter*):
```
--------------------------------------------------
|                   HYBRIS APP                   |
--------------------------------------------------
1 - Products menu
2 - Order menu
3 - Exit application
``` 
**Product** menu 
```
---------------------------------------------------
                   PRODUCTS LIST
---------------------------------------------------
| ID   | NAME      | PRICE      | STATUS          |
---------------------------------------------------
| 34   | Carrot    | 64         | IN_STOCK        |
| 35   | Potato    | 12         | RUNNING_LOW     |
| 36   | Apple     | 7          | OUT_OF_STOCK    |

1 - Create product
2 - Show ordered products
3 - Delete product by ID
4 - Delete all product (need password)
5 - Back to Main Menu
```
**Order** menu
```
--------------------------------------------------------------------------------------
                                   ORDERS
--------------------------------------------------------------------------------------
| ORDER | ORDER ITEM ID | TOTAL PRICE  | PRODUCT NAME  | QUANTITY  | CREATED          |

--------------------------------------------------------------------------------------
| 7    | 11            | 2560         | Carrot         | 40        | 2021-06-19 18:06 |
| 7    | 12            | 24           | Potato         | 2         | 2021-06-19 18:06 |
| 7    | 13            | 7            | Apple          | 1         | 2021-06-19 18:06 |
| 6    | 9             | 2560         | Carrot         | 40        | 2021-06-19 18:06 |
| 6    | 10            | 24           | Potato         | 2         | 2021-06-19 18:06 |

1 - Create order
2 - Update order
3 - Find order by ID
4 - Back to Main menu
```

## Functional Requirements
Application  provide such operations:
* Create Product **(Main menu -> Product menu -> Create product)**
* Create Order with a list of the products specified by id. User Id should be auto generated **(Main menu -> Order menu -> Create order)**
* Update Order Entries quantities. During order creation Order Entries should be created 
in background **(Main menu -> Order menu -> Update order)**
* Show following views:

    *  | Product Name | Product Price | Product Status | for all products **(Main menu -> Products menu )**

    * List all products, which have been ordered at least once, with total ordered
quantity sorted descending by the quantity. Please implement it using SQL
query instead of doing calculation in Java **(Main menu -> Products menu -> Show ordered products)**

    * | Order ID | Products total Price | Product Name | Products Quantity in orderEntry
| Order Created Date [YYYY-MM-DD HH:MM ] | by order Id **(Main menu -> Order menu -> Find order by ID)**

    * List all orders using previous view **(Main menu -> Order menu )**

* Remove product by ID / Remove all products only if you enter a password **(Main menu -> Products menu -> Delete product by ID \ Delete all product (need password))**

