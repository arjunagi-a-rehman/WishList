## User Wishlist Service

This service provides functionalities to manage user wishlists. Users can add, remove, and read items from their wishlist. The service is fully secured, requiring authentication for all operations except for user registration, which is open to all users.

**Deployed Link:** [User Wishlist Service](http://20.193.128.108/)
**Demo Link**:[video demo](https://sus9.in/xeswps)
## Language and Framework
![Java](https://img.shields.io/badge/Language-Java-green)
![Spring Boot](https://img.shields.io/badge/Framework-Spring%20Boot-brightgreen)

### Endpoints

#### User Controller

**Base Path:** `/api/vo/user`

- **POST /register**
  - **Description:** Create a new user in the system.
  - **Request Body:** UserDto
  - **Response:** UserDto
  - **Status Codes:**
    - 201: CREATED
    - 500: Internal Server Error

- **GET /{id}**
  - **Description:** Fetch a user by ID.
  - **Autherization require** ADMIN
  - **Path Variable:** id (Integer)
  - **Response:** UserDto
  - **Status Codes:**
    - 200: OK
    - 500: Internal Server Error

- **GET /**
  - **Description:** Fetch all users.
  - **Autherization require** ADMIN
  - **Response:** List of UserDto
  - **Status Codes:**
    - 200: OK
    - 500: Internal Server Error

- **PUT /**
  - **Description:** Update user details.
  - **Autherization require** ADMIN
  - **Request Body:** UserDto
  - **Response:** ResponseDto
  - **Status Codes:**
    - 200: OK
    - 417: Expectation Failed
    - 500: Internal Server Error

- **DELETE /{id}**
  - **Description:** Delete a user by ID.
  - **Autherization require** ADMIN
  - **Path Variable:** id (Integer)
  - **Response:** ResponseDto
  - **Status Codes:**
    - 200: OK
    - 417: Expectation Failed
    - 500: Internal Server Error

#### Wishlist Item Controller

**Base Path:** `/api/vo/wishListItem`

- **POST /**
  - **Description:** Create a new wishlist item for a user.
  - **Authentication require** YES
  - **Request Body:** WishListItemDto
  - **Response:** WishListItemDto
  - **Status Codes:**
    - 201: CREATED
    - 500: Internal Server Error

- **GET /user**
  - **Description:** Fetch all wishlist items for the authenticated user.
  - **Authentication require** YES
  - **Response:** List of WishListItemDto
  - **Status Codes:**
    - 200: OK
    - 500: Internal Server Error

- **DELETE /{id}**
  - **Description:** Delete a wishlist item by ID.
  - **Authentication require** YES
  - **Path Variable:** id (Integer)
  - **Response:** ResponseDto
  - **Status Codes:**
    - 200: OK
    - 417: Expectation Failed
    - 500: Internal Server Error

### Security

The service is secured using Spring Security. Authentication is required for all endpoints except user registration. Users can register at `/api/vo/user/register`, and then they must authenticate by visiting `/login` and entering their credentials.


### Getting Started

#### Deploying with Maven
**note:- please either fix envoirement varaible at your envoirement according to application.properties or hardcode it**
To deploy the User Wishlist Service using Maven, follow these steps:

1. **Clone the repository to your local machine:**
   ```sh
   git clone <repository-url>
   ```

2. **Navigate to the project directory:**
   ```sh
   cd UserWishList
   ```

3. **Build the project using Maven:**
   ```sh
   mvn clean package
   ```

4. **Start the application:**
   ```sh
   java -jar target/UserWishList-<version>.jar
   ```
   Replace `<version>` with the version of the JAR file generated.


### Deployment with Docker

#### Quick Deployment with Docker Compose

For quick deployment, users can use Docker Compose to pull and run the container. Follow these steps:

1. Run the following command to start the services defined in the `docker-compose.yml` file:
   ```sh
   docker-compose up
   ```

#### Custom Deployment

For creating your own Docker container, follow these steps:

1. **Update User Credentials in `pom.xml` Jib Plugin:**
   Modify the user credentials in the `pom.xml` file under the Jib plugin section.

2. **Build the Project with Maven:**
   Run the following command to build the project and create the JAR file:
   ```sh
   mvn clean install
   ```

3. **Build Docker Image:**
   Execute the following Maven command to compile and build the Docker image:
   ```sh
   mvn compile jib:dockerBuild
   ```

4. **Update Docker Compose Configuration:**
   Update the Docker Compose configuration (`docker-compose.yml`) with the required environment variables and replace the image name with the one you built.

5. **Start the Docker Containers:**
   Run the following command to start the services defined in the Docker Compose file:
   ```sh
   docker-compose up
   ```

