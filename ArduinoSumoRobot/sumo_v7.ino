
/*
 * @godeanulucian
 * @guraualexandru
*/

// Define motor pins
int leftMotorEnable = 10;
int leftMotorForward = 6;
int leftMotorBackward = 9;
int rightMotorEnable = 11;
int rightMotorForward = 5;
int rightMotorBackward = 3;

// Define sensor pins
int ultrasonicTrig = 12;
int ultrasonicEcho = 13;

#define irFront A0

void setup() {
  // Set motor pins as output
  pinMode(leftMotorEnable, OUTPUT);
  pinMode(leftMotorForward, OUTPUT);
  pinMode(leftMotorBackward, OUTPUT);
  pinMode(rightMotorEnable, OUTPUT);
  pinMode(rightMotorForward, OUTPUT);
  pinMode(rightMotorBackward, OUTPUT);

  pinMode(ultrasonicTrig, OUTPUT);
  pinMode(ultrasonicEcho, INPUT);

  // Set sensor pins as input
  pinMode(irFront, INPUT);

  // Initialize serial communication
  Serial.begin(9600);
  delay(3000);
}

void loop() {
  
  // Read ultrasonic sensor
  long duration, distance;
  digitalWrite(ultrasonicTrig, LOW);
  delayMicroseconds(2);
  digitalWrite(ultrasonicTrig, HIGH);
  delayMicroseconds(10);
  digitalWrite(ultrasonicTrig, LOW);
  duration = pulseIn(ultrasonicEcho, HIGH);
  distance = (duration / 2) / 29.1;
  //distance = duration * 0.034 / 2;

  int irFrontsens = analogRead(irFront);
  
  // Rotate the robot
 
//  rotateRobot(170);
//    delay(20);
  
  // Check if opponent is within range
  if (distance < 15 && irFrontsens > 350) { // lumina
    Serial.print("Object detected - ");
    Serial.println(distance);
    stopRobot();
    delay(500);
    while (analogRead(irFront) > 350){ // lumina
     Serial.print("attack! - ");
    Serial.println(analogRead(irFront));
      attackOpponent(230);
  }
    delay(20);
    
  }
    
   else if (distance > 15  && irFrontsens > 350){ // lumina
    rotateRobot(200);
    Serial.print("Black detected - ");
    Serial.println(irFrontsens);
    delay(20);
    }

  // Check IR sensors for white color

    if (irFrontsens < 350){ // lumina
      //analogWrite(irFront, HIGH);
      rotateRobot(200);
    Serial.print("White detected - ");
    Serial.println(analogRead(irFront));
   delay(30);
    }

}



// Function to rotate the robot
void rotateRobot2(int Speed) {
  analogWrite(leftMotorEnable, Speed);
  digitalWrite(leftMotorForward, LOW);
  digitalWrite(leftMotorBackward, HIGH);
  analogWrite(rightMotorEnable, Speed);
  digitalWrite(rightMotorForward, HIGH);
  digitalWrite(rightMotorBackward, LOW);
}


// Function to rotate the robot
void rotateRobot(int Speed) {
  analogWrite(leftMotorEnable, Speed);
  digitalWrite(leftMotorForward, LOW);
  digitalWrite(leftMotorBackward, HIGH);
  analogWrite(rightMotorEnable, Speed);
  digitalWrite(rightMotorForward, HIGH);
  digitalWrite(rightMotorBackward, LOW);
}

// Function to stop the robot
void stopRobot() {
  digitalWrite(leftMotorEnable, LOW);
  digitalWrite(leftMotorForward, LOW);
  digitalWrite(leftMotorBackward, LOW);
  digitalWrite(rightMotorEnable, LOW);
  digitalWrite(rightMotorForward, LOW);
  digitalWrite(rightMotorBackward, LOW);
}

// Function to move the robot forward
void moveForward(int Speed) {
  analogWrite(leftMotorEnable, Speed);
  digitalWrite(leftMotorForward, HIGH);
  digitalWrite(leftMotorBackward, LOW);
  analogWrite(rightMotorEnable, Speed);
  digitalWrite(rightMotorForward, HIGH);
  digitalWrite(rightMotorBackward, LOW);
}

// Function to move the robot backward
void moveBackward(int Speed) {
  analogWrite(leftMotorEnable, Speed);
  digitalWrite(leftMotorForward, LOW);
  digitalWrite(leftMotorBackward, HIGH);
  analogWrite(rightMotorEnable, Speed);
  digitalWrite(rightMotorForward, LOW);
  digitalWrite(rightMotorBackward, HIGH);
}

// Function to attack the opponent
void attackOpponent(int Speed) {
  //stopRobot();
  //delay(100);
  analogWrite(leftMotorEnable, Speed);
  digitalWrite(leftMotorForward, HIGH);
  digitalWrite(leftMotorBackward, LOW);
  analogWrite(rightMotorEnable, Speed);
  digitalWrite(rightMotorForward, HIGH);
  digitalWrite(rightMotorBackward, LOW);
}
