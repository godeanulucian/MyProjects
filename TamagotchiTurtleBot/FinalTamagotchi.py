#!/usr/bin/env python3

# IMPORTS
import time
import rospy
import cv2
from geometry_msgs.msg import Twist

# CAMERA CONFIGURATION
cap = cv2.VideoCapture(0)
cap.set(3, 640)
cap.set(4, 480)

# COLOR RANGES IN HSV
# EAT
lower_yellow = (22, 93, 0)
upper_yellow = (45, 255, 255)
# PLAY
lower_blue = (78, 158, 124)
upper_blue = (138, 255, 255)
# LOVE
lower_orange = (5, 50, 50)
upper_orange = (15, 255, 255)
# ATTACK
lower_red = (0, 100, 20)
upper_red = (10, 255, 255)

jmp = 0
eat = False
play = False

rospy.init_node('puppy_node')
try:
    while True:
        _, frame = cap.read()
        hsv = cv2.cvtColor(frame, cv2.COLOR_BGR2HSV)

        # COLOR MASKS
        # EAT
        mask_yellow = cv2.inRange(hsv, lower_yellow, upper_yellow)
        # PLAY
        mask_blue = cv2.inRange(hsv, lower_blue, upper_blue)
        # LOVE
        mask_orange = cv2.inRange(hsv, lower_orange, upper_orange)
        # ATTACK
        mask_red = cv2.inRange(hsv, lower_red, upper_red)

        cv2.imshow("camera", frame)

        # 0. DEFAULT
        if cv2.countNonZero(mask_yellow) <= 0 and cv2.countNonZero(mask_blue) <= 0 and cv2.countNonZero(mask_orange) <= 0 and cv2.countNonZero(mask_red) <= 0 and jmp == 0:
            # if cv2.countNonZero(mask_yellow) > 0 and cv2.countNonZero(mask_blue) > 0 and cv2.countNonZero(mask_orange) > 0 and cv2.countNonZero(mask_red) > 0 and jmp == 0:
            print("puppy waiting for your attention")
            jmp = 1

        # 1. EAT
        if cv2.countNonZero(mask_yellow) > 0:
            # cv2.imshow("detect_yellow", mask_yellow)

            # Setam publisher ul pentru cmd_vel topic
            cmd_vel_pub = rospy.Publisher('cmd_vel', Twist, queue_size=10)
            num_mov = 0
            print("happy puppy eating\n")
            while True:
                twist = Twist()
                if num_mov == 3:
                    twist.linear.x = 0
                    twist.angular.z = 0
                    cmd_vel_pub.publish(twist)
                    break
                else:
                    linear_velocity = 0.1  # m/s
                    angular_velocity = 3.4  # rad/s

                    # Cream un mesaj de tipul Twist si il publicam
                    twist.linear.x = linear_velocity
                    twist.angular.z = angular_velocity
                    cmd_vel_pub.publish(twist)
                    time.sleep(2)
                    num_mov += 1
            eat = True
            jmp = 0

        # 2. POOP
        if eat == True:
            cmd_vel_pub = rospy.Publisher('cmd_vel', Twist, queue_size=10)
            num_mov = 0
            print("sad puppy is going to poop in about 3 seconds\n")
            while True:
                twist = Twist()
                if num_mov == 7:
                    twist.linear.x = 0.0
                    twist.angular.z = 0.0
                    cmd_vel_pub.publish(twist)
                    break

                elif num_mov == 6:
                    twist.linear.x = -0.8
                    twist.angular.z = 0.0
                    cmd_vel_pub.publish(twist)
                    num_mov = num_mov+1
                    time.sleep(0.5)

                elif num_mov == 5:
                    twist.linear.x = 0.0
                    twist.angular.z = 0.0
                    cmd_vel_pub.publish(twist)
                    num_mov = num_mov+1
                    time.sleep(0.5)

                elif num_mov == 4:
                    twist.linear.x = 0.8
                    twist.angular.z = 0.0
                    cmd_vel_pub.publish(twist)
                    num_mov = num_mov+1
                    time.sleep(0.5)

                elif num_mov == 3:
                    twist.linear.x = 0.0
                    twist.angular.z = 0.0
                    cmd_vel_pub.publish(twist)
                    num_mov = num_mov+1
                    time.sleep(0.5)

                elif num_mov == 2:
                    twist.linear.x = -0.8
                    twist.angular.z = 0.0
                    cmd_vel_pub.publish(twist)
                    num_mov = num_mov+1
                    time.sleep(0.5)

                elif num_mov == 1:
                    twist.linear.x = 0.0
                    twist.angular.z = 0.0
                    cmd_vel_pub.publish(twist)
                    num_mov = num_mov+1
                    time.sleep(0.5)

                elif num_mov == 0:
                    time.sleep(0.5)
                    twist.linear.x = 0.8
                    twist.angular.z = 0.0
                    cmd_vel_pub.publish(twist)
                    num_mov = num_mov+1
                    time.sleep(0.5)
            print("puppy has pooped\n")
            eat = False
            jmp = 0

        # 3. PLAY
        if cv2.countNonZero(mask_blue) > 0:
            # cv2.imshow("detect_blue", mask_blue)
            cmd_vel_pub = rospy.Publisher('cmd_vel', Twist, queue_size=10)
            num_mov = 0
            print("happy puppy running\n")
            # rate=rospy.Rate(10)
            while True:
                twist = Twist()
                if num_mov == 1:
                    twist.linear.x = 0.0
                    twist.angular.z = 0.0
                    cmd_vel_pub.publish(twist)
                    break

                else:
                    time.sleep(0.5)
                    linear_velocity = 0.5
                    angular_velocity = 1.0
                    twist.linear.x = linear_velocity
                    twist.angular.z = angular_velocity
                    cmd_vel_pub.publish(twist)
                    time.sleep(6.9)
                    # rate.sleep()
                    num_mov += 1
            play = True
            jmp = 0

        # 4. SLEEP
        if play == True:
            print("puppy is really tired\n")
            time.sleep(5)
            print("shhh puppy is sleeping\n")
            jmp = 0

        # 5. LOVE
        if cv2.countNonZero(mask_orange) > 0:
            # cv2.imshow("detect_orange", mask_orange)
            cmd_vel_pub = rospy.Publisher('cmd_vel', Twist, queue_size=10)
            num_mov = 0
            print("puppy loves you so much\n")
            while True:
                twist = Twist()
                if num_mov == 1:
                    twist.linear.x = 0.0
                    twist.angular.z = 0.0
                    cmd_vel_pub.publish(twist)
                    break

                else:
                    time.sleep(0.5)
                    linear_velocity = 0.5
                    angular_velocity = 0.0
                    distance = 1.0
                    movement_duration = distance / linear_velocity  # seconds
                    twist.linear.x = linear_velocity
                    twist.angular.z = angular_velocity
                    cmd_vel_pub.publish(twist)
                    rospy.sleep(movement_duration)
                    num_mov += 1

            jmp = 0

        # 6. ATTACK
        if cv2.countNonZero(mask_red) > 0:
            # cv2.imshow("detect_red", mask_red)
            cmd_vel_pub = rospy.Publisher('cmd_vel', Twist, queue_size=10)
            num_mov = 0
            print("run, puppy is in attack mode\n")
            while True:
                twist = Twist()
                if num_mov == 1:
                    twist.linear.x = 0.0
                    twist.angular.z = 0.0
                    cmd_vel_pub.publish(twist)
                    rospy.sleep(0)
                    break

                else:
                    time.sleep(0.5)
                    linear_velocity = 1.5
                    angular_velocity = 0.0
                    distance = 2.0
                    movement_duration = distance / linear_velocity
                    twist.linear.x = linear_velocity
                    twist.angular.z = angular_velocity
                    cmd_vel_pub.publish(twist)
                    rospy.sleep(movement_duration)
                    num_mov += 1
            jmp = 0

        # FORCE STOP CV
        if cv2.waitKey(1) & 0xFF == ord('q'):
            break
except KeyboardInterrupt:
    pass

cap.release()
# ROS MAIN LOOP
rospy.spin()
