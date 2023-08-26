
CREATE TABLE USERS (
                       userID SERIAL PRIMARY KEY,
                       username VARCHAR(255) UNIQUE,
                       password VARCHAR(255),
                       email VARCHAR(255),
                       contactInformation VARCHAR(255),
                       fullName VARCHAR(255),
                       dateOfBirth DATE,
                       gender VARCHAR(10),
                       address VARCHAR(255),
                       isDoctor BOOLEAN
);

INSERT INTO USERS (username, password, email, contactInformation, fullName, dateOfBirth, gender, address, isDoctor)
VALUES
    ('john_doe', 'password123', 'john@example.com', '123-456-7890', 'John Doe', '1990-05-15', 'Male', '123 Main St', false),
    ('jane_smith', 'securepass', 'jane@example.com', '987-654-3210', 'Jane Smith', '1985-12-10', 'Female', '456 Elm St', true),
    ('alice_johnson', 'pass123', 'alice@example.com', '555-123-4567', 'Alice Johnson', '2000-08-25', 'Female', '789 Oak St', false),
    ('michael_williams', 'mypass', 'michael@example.com', '222-333-4444', 'Michael Williams', '1978-03-03', 'Male', '987 Pine St', false),
    ('emily_davis', 'emilypass', 'emily@example.com', '666-777-8888', 'Emily Davis', '1995-11-20', 'Female', '654 Birch St', true);


CREATE TABLE PATIENTS (
                          patientID SERIAL PRIMARY KEY,
                          fullName VARCHAR(255),
                          dateOfBirth DATE,
                          gender VARCHAR(10),
                          email VARCHAR(255),
                          phoneNumber VARCHAR(20) UNIQUE,
                          socialMedia VARCHAR(255),
                          address VARCHAR(255),
                          hasInsurance BOOLEAN,
                          emergencyContact VARCHAR(255),
                          bloodType VARCHAR(5),
                          height DOUBLE PRECISION,
                          weight DOUBLE PRECISION,
                          language VARCHAR(50),
                          primaryCarePhysician VARCHAR(255),
                          allergies TEXT,
                          medications TEXT,
                          nextOfKinFullName VARCHAR(255),
                          testName VARCHAR(255),
                          prescriptionName VARCHAR(255),
                          hasAppointment BOOLEAN,
                          hasPayment BOOLEAN
);

INSERT INTO PATIENTS (fullName, dateOfBirth, gender, email, phoneNumber, socialMedia, address, hasInsurance, emergencyContact, bloodType, height, weight, language, primaryCarePhysician, allergies, medications, nextOfKinFullName, testName, prescriptionName, hasAppointment, hasPayment)
VALUES
    ('John Doe', '1990-05-15', 'Male', 'john@example.com', '1234567890', 'john_doe', '123 Main St', true, 'Jane Doe', 'A+', 175.5, 70.2, 'English', 'Dr. Smith', 'Pollen', 'Aspirin', 'Jane Doe', 'Blood Test', 'Painkiller', true, true),
    ('Jane Smith', '1985-12-10', 'Female', 'jane@example.com', '9876543210', 'jane_smith', '456 Elm St', false, 'John Smith', 'O-', 160.0, 55.5, 'Spanish', 'Dr. Johnson', 'Penicillin', 'Antibiotic', 'John Smith', 'X-ray', 'Antibiotic', false, true),
    ('Alice Johnson', '2000-08-25', 'Female', 'alice@example.com', '5551234567', 'alice_johnson', '789 Oak St', true, 'Bob Johnson', 'B+', 165.7, 60.0, 'French', 'Dr. Brown', 'None', 'None', 'Bob Johnson', 'Checkup', 'None', true, false),
    ('Michael Williams', '1978-03-03', 'Male', 'michael@example.com', '2223334444', 'michael_williams', '987 Pine St', false, 'Laura Williams', 'AB-', 180.2, 85.7, 'German', 'Dr. Miller', 'Dust', 'Inhaler', 'Laura Williams', 'CT Scan', 'Asthma Medication', false, true),
    ('Emily Davis', '1995-11-20', 'Female', 'emily@example.com', '6667778888', 'emily_davis', '654 Birch St', true, 'David Davis', 'A-', 162.5, 58.0, 'Italian', 'Dr. Wilson', 'Shellfish', 'EpiPen', 'David Davis', 'Ultrasound', 'Allergy Medication', true, true);


CREATE TABLE PROVIDERS (
                           providerID SERIAL PRIMARY KEY,
                           fullName VARCHAR(255),
                           specialization VARCHAR(255),
                           address VARCHAR(255),
                           contactInformation VARCHAR(20),
                           services TEXT,
                           licenseNumber VARCHAR(255) UNIQUE,
                           language TEXT,
                           averageRating DOUBLE PRECISION,
                           acceptsInsurance BOOLEAN,
                           acceptedInsurancePlans TEXT,
                           hasPharmacyInventory BOOLEAN,
                           isAvailable BOOLEAN,
                           hasAppointment BOOLEAN,
                           hasPayment BOOLEAN
);

INSERT INTO PROVIDERS (fullName, specialization, address, contactInformation, services, licenseNumber, language, averageRating, acceptsInsurance, acceptedInsurancePlans, hasPharmacyInventory, isAvailable, hasAppointment, hasPayment)
VALUES
    ('Dr. Smith', 'Cardiologist', '123 Heart St', '555-123-4567', 'Cardiac care, Stress test', 'DOC123', 'English', 4.5, true, 'Plan A, Plan B', true, true, true, true),
    ('Dr. Johnson', 'Dermatologist', '456 Skin Ave', '555-987-6543', 'Skin conditions, Botox', 'SKIN456', 'Spanish', 4.2, true, 'Plan B, Plan C', false, true, true, false);


CREATE TABLE APPOINTMENTS (
                              appointmentID SERIAL PRIMARY KEY,
                              date DATE,
                              time TIME,
                              patientName VARCHAR(255),
                              providerName VARCHAR(255),
                              type VARCHAR(255),
                              status VARCHAR(255),
                              reason TEXT,
                              location VARCHAR(255),
                              notes TEXT
);

INSERT INTO APPOINTMENTS (date, time, patientName, providerName, type, status, reason, location, notes)
VALUES
    ('2023-08-15', '10:00:00', 'John Doe', 'Dr. Smith', 'Checkup', 'Scheduled', 'Routine checkup', 'Clinic A', 'No special notes'),
    ('2023-08-17', '15:30:00', 'Jane Smith', 'Dr. Johnson', 'Follow-up', 'Completed', 'Review of test results', 'Hospital B', 'Prescribed medication');


CREATE TABLE INSURANCES (
                            insuranceID SERIAL PRIMARY KEY,
                            patientFullName VARCHAR(255),
                            companyName VARCHAR(255),
                            startDate DATE,
                            endDate DATE,
                            coveragePercent DOUBLE PRECISION,
                            contactInformation TEXT
);

INSERT INTO INSURANCES (patientFullName, companyName, startDate, endDate, coveragePercent, contactInformation)
VALUES
    ('John Doe', 'ABC Insurance', '2023-01-01', '2024-01-01', 0.6, 'contact@abcinsurance.com'),
    ('Jane Smith', 'XYZ Insurance', '2023-02-15', '2023-12-31', 0.8, 'contact@xyzinsurance.com');


CREATE TABLE LAB_TESTS (
                           labTestID SERIAL PRIMARY KEY,
                           testName VARCHAR(255),
                           patientFullName VARCHAR(255),
                           type VARCHAR(255),
                           testDate DATE,
                           result TEXT,
                           technician VARCHAR(255),
                           location VARCHAR(255),
                           comments TEXT
);

INSERT INTO LAB_TESTS (testName, patientFullName, type, testDate, result, technician, location, comments)
VALUES
    ('Blood Test', 'John Doe', 'Medical', '2023-08-15', 'Normal', 'Alice Johnson', 'Lab A', 'Routine check'),
    ('X-ray', 'Jane Smith', 'Radiology', '2023-08-17', 'Fracture detected', 'Michael Williams', 'Hospital B', 'Injury evaluation');


CREATE TABLE KINS (
                      kinID SERIAL PRIMARY KEY,
                      patientFullName VARCHAR(255),
                      fullName VARCHAR(255),
                      dateOfBirth DATE,
                      gender VARCHAR(10),
                      bloodType VARCHAR(5),
                      relationStatus VARCHAR(255),
                      contactInfo TEXT,
                      language VARCHAR(50)
);

INSERT INTO KINS (patientFullName, fullName, dateOfBirth, gender, bloodType, relationStatus, contactInfo, language)
VALUES
    ('John Doe', 'Jane Doe', '1975-03-10', 'Female', 'A+', 'Spouse', 'jane@example.com', 'English'),
    ('Jane Smith', 'David Smith', '1982-09-22', 'Male', 'O-', 'Sibling', 'david@example.com', 'Spanish');


CREATE TABLE PAYMENTS (
                          paymentID SERIAL PRIMARY KEY,
                          providerFullName VARCHAR(255),
                          patientFullName VARCHAR(255),
                          timestamp TIMESTAMP,
                          amount DOUBLE PRECISION,
                          status VARCHAR(255),
                          description TEXT
);

INSERT INTO PAYMENTS (providerFullName, patientFullName, timestamp, amount, status, description)
VALUES
    ('Dr. Smith', 'John Doe', '2023-08-15 10:30:00', 150.0, 'Completed', 'Checkup payment'),
    ('Dr. Johnson', 'Jane Smith', '2023-08-17 15:00:00', 200.0, 'Pending', 'Follow-up payment');


CREATE TABLE PHARMACY_INVENTORY (
                                    itemID SERIAL PRIMARY KEY,
                                    itemName VARCHAR(255),
                                    providerFullName VARCHAR(255),
                                    category VARCHAR(255),
                                    quantity INT,
                                    unitPrice DOUBLE PRECISION,
                                    manufacturer VARCHAR(255),
                                    expirationDate DATE,
                                    batchNumber VARCHAR(255),
                                    stockLevel INT,
                                    storageConditions TEXT,
                                    notes TEXT
);

INSERT INTO PHARMACY_INVENTORY (itemName, providerFullName, category, quantity, unitPrice, manufacturer, expirationDate, batchNumber, stockLevel, storageConditions, notes)
VALUES
    ('Painkiller', 'ABC Pharmacy', 'Over-the-counter medications', 100, 5.99, 'MedCo', '2023-12-31', 'B123', 20, 'Store in cool, dry place', 'For minor pain relief'),
    ('Antibiotic', 'XYZ Pharmacy', 'Prescription drugs', 50, 12.99, 'HealthPharm', '2024-06-30', 'A456', 10, 'Refrigerate at 2-8°C', 'For bacterial infections');


CREATE TABLE PRESCRIPTIONS (
                               prescriptionID SERIAL PRIMARY KEY,
                               prescriptionName VARCHAR(255),
                               prescriptionDate DATE,
                               patientFullName VARCHAR(255),
                               providerFullName VARCHAR(255),
                               medication VARCHAR(255),
                               refills DOUBLE PRECISION,
                               pharmacy VARCHAR(255),
                               instructions TEXT
);

INSERT INTO PRESCRIPTIONS (prescriptionName, prescriptionDate, patientFullName, providerFullName, medication, refills, pharmacy, instructions)
VALUES
    ('Pain Relief', '2023-08-15', 'John Doe', 'Dr. Smith', 'Ibuprofen', 2.0, 'ABC Pharmacy', 'Take with food'),
    ('Antibiotic', '2023-08-17', 'Jane Smith', 'Dr. Johnson', 'Amoxicillin', 1.0, 'XYZ Pharmacy', 'Complete the full course');

