<p align="center">

<h3 align="center">Information Sistem API Koperasi</h3>
</p>
<p align="justify">
The API Koperasi Information System is a Backend Application created to help develop systems that are easier to implement. In this API there are several features including:
</p>
<ul>
    <li>Authentication and Authorization</li>
    <li>Management Member</li>
    <li>Management Employee</li>
    <li>Management Position</li>
    <li>Management Position</li>
    <li>Management Cash</li>
    <li>Management Loan</li>
    <li>Transaction Loan</li>
    <li>Transaction Saving or Withdraw Cash</li>
</ul>

### Built With

- [Springboot v3.2.4](https://spring.io/)
- [Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [PostgreSQL](https://www.postgresql.org/docs/)
- [Swagger](https://realrashid.github.io/sweet-alert/)
- [Maven](https://maven.apache.org/)

### Prerequisites

This is an example of how to list things you need to use the software and how to install them.

- Java IDEA
- PgAdmin or other PostgreSQL DBMS
- Postman or other API Testing Apps<br/>
- Maven Build Tools

### Installation

1. Clone the repo
   ```sh
    git clone https://github.com/Myudhaap/api-koperasi
   ```
2. Rename application.properties.example to application.properties and change few config

   ```properties
    spring.application.name=koperasi
    spring.datasource.username=YOUR_USERNAME_DATABASE
    spring.datasource.password=YOUR_PASSWORD_DATABASE
    spring.datasource.url=jdbc:postgresql://localhost:5432/YOUR_NAME_DATABASE
    spring.datasource.driver-class-name=org.postgresql.Driver
    spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
    
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.show-sql=true
    
    app.koperasi.jwt.jwt-secret=JWT_SECRET
    app.koperasi.jwt.jwt-app-name=koperasi api
    app.koperasi.jwt.jwt-app-expired=3600
   ```
3. Install all dependencies and run Maven reload

4. Then run the project on branch development

## API Documentation

Postman : https://documenter.getpostman.com/view/28401427/2sA35G2ghD

### Example Request

- Endpoint : ```/api/v1/transactions```
- Method : POST
- Header :
    - Content-Type: application/json
    - Accept: application/json
    - Authorization: Bearer token
- Body :

```json
{
  "loanTypeId": "89d82c38-967f-4102-ba8d-e829d3b4f5ab",
  "instalmentTypeId": "c29905bb-ec4e-49f0-954d-2cc7fd949574",
  "memberId": "546eaeb0-8347-46fa-b271-5e1e6cdcc332",
  "nominal": 100000
}
```

### Example Response

```json
{
  "message": "Successfully created transaction",
  "data": {
    "id": "9377cede-ecce-4088-b3d9-e4feb6109377",
    "loanTypeId": "89d82c38-967f-4102-ba8d-e829d3b4f5ab",
    "instalmentTypeId": "c29905bb-ec4e-49f0-954d-2cc7fd949574",
    "memberId": "546eaeb0-8347-46fa-b271-5e1e6cdcc332",
    "employeeId": null,
    "nominal": 100000.0,
    "approvedAt": null,
    "approvalStatus": null,
    "loanTransactionDetails": [],
    "createdAt": 1711682841025,
    "updatedAt": null
  },
  "paging": null
}
```

### Entity Relationship Diagram

![erd](./Sistem%20Informasi%20Koperasi%20Simpan%20Pinjam.drawio.png)
<!-- CONTACT -->

## Contact

Muhammad Yudha Adi Pratama -
[@Intagram](https://instagram.com/myudha_ap) -
[@Linkedin](https://www.linkedin.com/in/muhammad-yudha-adi-pratama-116433177/)


Project Link API Koperasi: [https://github.com/Myudhaap/api-koperasi](https://github.com/Myudhaap/api-koperasi)
