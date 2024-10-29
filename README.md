# CRUD REST API for Delivery App

###

![Scala](https://img.shields.io/badge/Scala-DC3E00?style=flat&logo=scala&logoColor=white) 
![Akka](https://img.shields.io/badge/Akka-0F1C2D?style=flat&logo=akka&logoColor=white) 
![Slick](https://img.shields.io/badge/Slick-38A9A6?style=flat&logoColor=white) 
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-336791?style=flat&logo=postgresql&logoColor=white)

This project is a CRUD REST API for managing content in a delivery application, developed using Scala and Akka. The API supports create, read, update, and delete operations for various entities: users, couriers, products, categories and orders.

### Entities:

- **Users**: Provide create, read, update, and delete operations for user information, including personal details such as name, surname, phone number, and address.
- **Couriers**: Enable create, read, update, and delete operations for couriers responsible for order deliveries, including personal details such as name, surname and phone number.
- **Products**: Support create, read, update, and delete operations for products available for purchase. Each product has information such as name, price, and category affiliation.
- **Categories**: Provide create, read, update, and delete operations for product categories, under which various products are classified for better organization and navigation.
- **Orders**: Enable create, read, update, and delete operations for customer orders, containing details about the user, assigned courier, list of products and total price.

## Installation

### Requirements

- Java Development Kit (JDK): 21 or higher
- Scala 2.13 or higher
- Gradle
- PostgreSQL
- Akka
- Slick

### Cloning the Repository

Clone this repository to your local computer:

```bash
git clone https://github.com/tsunamicxde/crud_delivery.git
```

## Database Setup

Configure the database connection parameters in the **application.conf** file:

```bash
db {
  url = "jdbc:postgresql://localhost:5432/your_db_name"
  user = "your_username"
  password = "your_db_password"
  driver = "org.postgresql.Driver"
  connectionPool = "HikariCP"
  keepAliveConnection = true
}
```

## Running the Application
1. Navigate to the root directory of the project.
2. Use Gradle to build and run the application:

```bash
gradle run
```

## Using the API

### Users

#### Get All Users

```bash
GET /users
```

#### Get User By Id

```bash
GET /users/{id}
```

#### Create a new User

```bash
POST /users
```

#### Request Body

```json
{
  "name": "Franklin",
  "surname": "Clinton",
  "phone": "+1234567890",
  "address": "16 Green Yard Lane, Springfield"
}
```

#### Update User Information

```bash
PUT /users/{id}
```

#### Request Body

```json
{
  "name": "Franklin",
  "surname": "Clinton",
  "phone": "+1634567890",
  "address": "16 Green Yard Lane, Springfield"
}
```

#### Delete User

```bash
DELETE /users/{id}
```

### Couriers

#### Get All Couriers

```bash
GET /couriers
```

#### Get Courier By Id

```bash
GET /couriers/{id}
```

#### Create a new Courier

```bash
POST /couriers
```

#### Request Body

```json
{
  "name": "Samen",
  "surname": "Logan",
  "phone": "+19876543210"
}
```

#### Update Courier Information

```bash
PUT /couriers/{id}
```

#### Request Body

```json
{
  "name": "Samen",
  "surname": "Logan",
  "phone": "+19876543852"
}
```

#### Delete Courier

```bash
DELETE /couriers/{id}
```

### Categories

#### Get All Categories

```bash
GET /categories
```

#### Get Category By Id

```bash
GET /categories/{id}
```

#### Create a new Category

```bash
POST /categories
```

#### Request Body

```json
{
  "name": "Fast Food"
}
```

#### Update Category Information

```bash
PUT /categories/{id}
```

#### Request Body

```json
{
  "name": "Burgers"
}
```

#### Delete Category

```bash
DELETE /categories/{id}
```

### Products

#### Get All Products

```bash
GET /products
```

#### Get Product By Id

```bash
GET /products/{id}
```

#### Create a new Product

```bash
POST /products
```

#### Request Body

```json
{
  "name": "Double Cheeseburger",
  "price": 299.99,
  "categoryId": 1
}
```

#### Update Product Information

```bash
PUT /products/{id}
```

#### Request Body

```json
{
  "name": "Double Cheeseburger",
  "price": 290.00,
  "categoryId": 1
}
```

#### Delete Product

```bash
DELETE /products/{id}
```

### Orders

#### Get All Orders

```bash
GET /orders
```

#### Get Order By Id

```bash
GET /orders/{id}
```

#### Create a new Order

```bash
POST /orders?productIds=1,2,3
```

#### Request Body

```json
{
  "userId": 1,
  "courierId": 1
}
```

#### Update Order Information

```bash
PUT /orders/{id}
```

#### Request Body

```json
{
  "userId": 1,
  "courierId": 6
}
```

#### Delete Order

```bash
DELETE /orders/{id}
```

## Developers

- [tsunamicxde](https://github.com/tsunamicxde)
