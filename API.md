# InsightHub API

The insight hub API is completely free.

## Requirements

-   **JAVA 17**
-   **Maven**
-   **Git**
-   **MySql**

## Installation and Execution

-   **_clone the repository:_**

    ```bash
    git clone https://github.com/rodriguesxxx/InsightHub.git
    ```

-   **_access the api folder_:**

    ```bash
    cd api
    ```

-   **_configure the application_:**

    ```bash
    cd src/main/resources
    cp application.copy.properties application.properties
    nano application.properties #or > code application.properties
    ```

    Enter your database information

-   **_start the api_:**

    ```bash
    cd ../../../ #go back to the root
    mvn spring-boot:run
    ```
