package org.firstinspires.ftc.robotcontroller.internal;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name="StndTele", group="9191")

public class ChaseTele extends OpMode {
    //Defining variable for L&R wheels
    public DcMotor frontLeft; //left wheel
    public DcMotor frontRight; //right wheel
    public DcMotor backLeft; //left wheel
    public DcMotor backRight; //right wheel
    public DcMotor armLeft; //Left arm motor
    public DcMotor armRight; //Right arm motor
    public Servo servo1; //Right servo on arm
    public Servo servo2; //Left servo on arm

    @Override
    public void init() {
        frontLeft = hardwareMap.dcMotor.get("left"); //Left wheel motor is defined and named
        frontRight = hardwareMap.dcMotor.get("right"); //Right wheel motor is defined and named
        backLeft = hardwareMap.dcMotor.get("left"); //Left wheel motor is defined and named
        backRight = hardwareMap.dcMotor.get("right"); //Back right wheel motor is defined and named
        armLeft = hardwareMap.dcMotor.get("armLeft");  //Left motor arm is defined and named
        armRight = hardwareMap.dcMotor.get("armRight"); //Right motor arm is defined and named
        servo1 = hardwareMap.servo.get("servo1"); //servo1 (gripper servo) is defined
        servo2 = hardwareMap.servo.get("servo2"); //servo2 (gripper servo) is defined
        frontRight.setDirection(DcMotorSimple.Direction.REVERSE); //Right wheels are always negative, for wheel base, because of base assembly
        backRight.setDirection(DcMotorSimple.Direction.REVERSE); //Right wheels are always negative, for wheel base, because of base assembly
        armLeft.setDirection(DcMotorSimple.Direction.REVERSE); //Right arm is always negative for lifting, because of assembly
        servo1.setPosition(-1); //Setting servo1 to start in initialization at start position, has to be between 0 and 1
        servo2.setPosition(1); //Setting servo2 to start in initialization at start position, has to be between 0 and 1
    }
    @Override
    public void loop() {
        double servo1Pos = servo1.getPosition(); //Creates a variable that has the value of the current position of servo 1 and has decimal values
        double servo2Pos = servo2.getPosition(); //Creates a variable that has the value of the current position of servo 2 and has decimal values
        //Servos
        if (gamepad2.right_stick_x < 0 && servo1Pos < .8 && servo2Pos > -.8) { //If the right stick is pushed left, the servos close
            servo1.setPosition(servo1Pos + .02);
            servo2.setPosition(servo2Pos - .02);
        } else if (gamepad2.right_stick_x > 0 && servo1Pos != 0 && servo2Pos != 1) { //If the left stick is pushed right, the servos open
            servo1.setPosition(servo1Pos - .02);
            servo2.setPosition(servo2Pos + .02);
        }
        //Driving
        if (gamepad1.left_stick_y != 0) { //Driving
            //If the left stick of controller 1 y not = 0, the robot will move forward or backward, setting the power to how far the stick is pushed
            frontLeft.setPower(gamepad1.left_stick_y * .8);
            frontRight.setPower(gamepad1.left_stick_y * .8);
            backLeft.setPower(gamepad1.left_stick_y * .8);
            backRight.setPower(gamepad1.left_stick_y * .8);
        } else if (gamepad1.right_stick_x != 0) { //Turning
            //If the right stick of controller 1 x not = 0, the robot will turn right or left, setting the power to how far the stick is pushed
            frontLeft.setPower(-gamepad1.right_stick_x * .5);
            frontRight.setPower(gamepad1.right_stick_x * .5);
            backLeft.setPower(-gamepad1.right_stick_x * .5);
            backRight.setPower(gamepad1.right_stick_x * .5);
        } else {//Null
            frontLeft.setPower(0);
            frontRight.setPower(0);
        }
        //Arm
        if (gamepad2.left_stick_y < 0) { //If left stick is pushed up, arm moves up
            armLeft.setPower(gamepad2.left_stick_y * .4);
            armRight.setPower(gamepad2.left_stick_y * .4);
        } else if (gamepad2.left_stick_y > 0) { //If left stick is pushed down, arm moves down
            armLeft.setPower(gamepad2.left_stick_y * .1);
            armRight.setPower(gamepad2.left_stick_y * .1);
        } else { //Null
            armLeft.setPower(0);
            armRight.setPower(0);
        }
        if (gamepad2.left_bumper){ //Button to set servos just outside of the size of the cube
            servo1.setPosition(.5);
            servo2.setPosition(.5);
        }
    }
}
