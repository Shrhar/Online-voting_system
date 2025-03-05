# Online Voting System

## 📌 Project Overview
The **Online Voting System** is a web application built using **Spring Boot** that allows users to cast votes securely and efficiently. This system ensures fairness and transparency in elections, making it ideal for organizations, institutions, or government bodies.

## 🔥 Features
- 🔐 **User Authentication & Role-Based Access** (Admin & Voter)
- 🗳️ **Candidate Management** (Admin can add/update candidates)
- 📊 **Real-time Voting & Result Calculation**
- 📜 **Secure Vote Submission**
- 📁 **Database Integration with MongoDB/MySQL**
- 🛠️ **RESTful API for Future Scalability**

## 🏗️ Tech Stack
- **Backend:** Spring Boot, Spring MVC, Spring Security
- **Database:** MongoDB / MySQL
- **Frontend:** HTML, CSS, JavaScript (or React.js if used)
- **Build Tool:** Maven / Gradle
- **Version Control:** Git & GitHub

## 🚀 Getting Started

### 1️⃣ Clone the Repository
```sh
 git clone https://github.com/Shrhar/Online-voting_system.git
 cd Online-voting_system
```

### 2️⃣ Configure Database
- Modify `application.properties` (for MySQL):
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/voting_system
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
```
- OR (for MongoDB):
```properties
spring.data.mongodb.uri=mongodb://localhost:27017/voting_system
```

### 3️⃣ Run the Application
```sh
 mvn spring-boot:run
```

## 🔑 User Roles & Access
| Role    | Permissions |
|---------|------------|
| Admin  | Add/Edit/Delete Candidates, View Results |
| Voter  | Cast Vote, View Results |

## 📜 API Endpoints
| Method | Endpoint | Description |
|--------|---------|------------|
| `POST` | `/api/auth/register` | Register a new user |
| `POST` | `/api/auth/login` | User login |
| `GET` | `/api/candidates` | View candidate list |
| `POST` | `/api/vote` | Cast a vote |
| `GET` | `/api/results` | View election results |

## 🛡️ Security & Authentication
- JWT-based authentication for API endpoints
- Role-based authorization (Admin/Voter)

## 📌 Future Enhancements
- 📲 Add a Frontend UI using React.js
- 🗳️ Implement Multi-Round Voting
- 📧 Email Notifications for Election Updates
- ☁️ Cloud Deployment on AWS/GCP

## 🤝 Contributing
Feel free to submit issues or pull requests to enhance the project! 🎉

## 📜 License
This project is licensed under the **MIT License**.

---
🔥 Happy Coding! 🚀
