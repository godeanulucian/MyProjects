
CREATE TABLE USERS (
    id CHAR(18) DEFAULT (UUID()) PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    fullName VARCHAR(255) NOT NULL,
    cardNumber VARCHAR(16) NOT NULL UNIQUE,
    amount DECIMAL(10, 2) NOT NULL,
    otherInfo TEXT
);

INSERT INTO USERS (username, password, email, fullName, cardNumber, amount, otherInfo)
VALUES ('demoUser', 'demoPassword', 'demo@example.com', 'Demo User', '1234567890123456', 1000.00, 'This is some other information for the demo user.');

CREATE TABLE PAYMENTS (
    id CHAR(18) DEFAULT (UUID()) PRIMARY KEY,
    sender VARCHAR(255) NOT NULL,
    receiver VARCHAR(255) NOT NULL,
    amount DECIMAL(10, 2) NOT NULL,
    paymentTimestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP
)

INSERT INTO PAYMENTS (sender, receiver, amount)
VALUES ('John Doe', 'Alice Smith', 100.00);