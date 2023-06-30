# EasyShop Backend Project
This project is an online shop with a finished front end application. The
back end of the project needed more methods to be implemented in order to do
the following things :

POST Register<br>
POST Login - new user<br>
POST Login - as admin<br>
GET Get Categories<br>
POST Add Category - not admin<br>
GET Get Categories<br>
POST Add Category - as admin<br>
GET Get Categories - after add<br>
GET Get New Category by Id<br>
DEL Delete Category - as admin<br>
GET Get Category By Id - after delete<br>
GET Search Products<br>
![](new screenshot\1.png)


## Technologies Used

- Java
- Spring Boot
- MySQL

## Phase 1

### CategoriesController

Implemented methods:

- `getAll()` - GET request to retrieve all categories.
- `getById(id)` - GET request to retrieve a category by ID.
- `addCategory(category)` - POST request to add a new category (accessible only to administrators).
- `updateCategory(id, category)` - PUT request to update a category by ID (accessible only to administrators).
- `deleteCategory(id)` - DELETE request to delete a category by ID (accessible only to administrators).
- 
### MySqlCategoriesDao

Implemented methods:

- `getAllCategories()` - Retrieves all categories from the database.
- `getById(id)` - Retrieves a specific category from the database based on its ID.
- `create(category)` - Creates a new category in the database.
- `update(id, category)` - Updates an existing category in the database based on its ID.
- `delete(id)` - Deletes a category from the database based on its ID.



## Phase 2
## Bug Fixes

### Search/Filter Bug

- The search function was not returning the expected results.
- The query inside the  Search method of MySqlProduct  was updated to fix the issue.

### Duplicate Products Bug

- The bug caused a new product to be created whenever the admin tried to update a product.
- The `UpdateProduct()` method in the `ProductController` class was altered to fix this issue.
- The method now correctly uses the `update()` method of the `productDao` to update the product with the provided ID.

![](\new screenshot\2.png)

## Getting Started

To run the project locally, follow these steps:

1. Clone the repository.
2. Configure the MySQL database settings in the `application.properties` file.
3. Build the project using your preferred Java development environment.
4. Run the application.

## Acknowledgements
Many thanks to Paul Kimball, Maricarmen, Tshering Dolma, Raquan.

