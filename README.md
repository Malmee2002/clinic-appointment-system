Clinic Appointment Booking System

A web-based Clinic Appointment Booking System developed using Spring Boot that allows patients to book doctor appointments online and manage their visit details efficiently. The system is designed with a no-online-payment policy, where patients pay the consultation fee at the clinic reception.

Features

- Patient registration and login
- View available doctors
- Book clinic appointments
- Appointment confirmation with payment at reception
- Admin management of doctors and schedules

Technologies Used

- Java
- Spring Boot
- Maven
- Thymeleaf
- MS SQL Server
- HTML, CSS, Bootstrap
- IntelliJ IDEA

Project Structure

ClinicAppointmentSystem/
├─ src/main/java/com/clinic/
│   ├─ ClinicAppointmentSystemApplication.java
│   ├─ config/
│   │   └─ SecurityConfig.java
│   ├─ controller/
│   │   ├─ AuthController.java
│   │   ├─ PatientController.java
│   │   └─ AdminController.java
│   ├─ entity/
│   │   ├─ User.java
│   │   ├─ Doctor.java
│   │   └─ Appointment.java
│   ├─ repository/
│   │   ├─ UserRepository.java
│   │   ├─ DoctorRepository.java
│   │   └─ AppointmentRepository.java
│   └─ service/
│       ├─ UserService.java
│       ├─ DoctorService.java
│       └─ AppointmentService.java
├─ src/main/resources/
│   ├─ application.properties
│   ├─ static/
│   │   └─ css/bootstrap.min.css
│   └─ templates/
│       ├─ login.html
│       ├─ register.html
│       ├─ patient_dashboard.html
│       ├─ appointment_form.html
│       ├─ appointment_confirmation.html
│       ├─  doctor_form.html
│       └─ admin_dashboard.html



How to Run the Project

1. Clone the repository
2. Open the project in IntelliJ IDEA
3. Configure MS SQL database
4. Run the Spring Boot application
5. Open browser and visit `http://localhost:8080`

Author

R.W.P.M.R.M.Y. Rajapakse
BSc(Hons) Information Technology specializing in Information Systems Engineering
Sri Lanka Institute of Information Technology (SLIIT)
