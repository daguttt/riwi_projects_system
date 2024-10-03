# Riwi Projects System
## Descripción

REST API to manage projects and tasks.

# Repository URL
https://github.com/daguttt/riwi_projects_system

# Project Setup

## 1. Environment variables

1. Copy the [`.env.example`](./src/main/resources/.env.example) file of the `resources/` folder and create the **`.env` file**  in the same folder (`resources/`) **replacing with real variables**.

2. The `JWT_SECRET_KEY` must be an HMAC hash string of 256 bits; otherwise, the token generation will throw an error. **Use [this website](https://www.devglan.com/online-tools/hmac-sha256-online?ref=blog.tericcabrel.com) to generate one**.

> [!TIP]
> Use whichever text for the *"Text to Compute Hash"* and *"Secret Key"* fields in the website.

## 2. Database Setup
Create the database named as in the [`.env.example` file](./src/main/resources/.env.example).

## 3. Run the application
You can now safely run the application using your preferred way:
1. Via IDE.
2. Via command line:

    ```bash
    ./mvnw spring-boot:run
    ```