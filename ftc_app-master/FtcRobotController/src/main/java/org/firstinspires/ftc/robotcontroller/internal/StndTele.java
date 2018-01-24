package org.firstinspires.ftc.robotcontroller.internal;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name="StndTele", group="9191")

public class StndTele extends OpMode {
    //Defining variable for L&R wheels
    public DcMotor frontLeft; //Front left wheel
    public DcMotor frontRight; //Front right wheel
    public DcMotor backLeft; //Back left wheel
    public DcMotor backRight; //Back right wheel
    public DcMotor armLeft; //Left arm motor
    public DcMotor armRight; //Right arm motor
    public Servo leftServo; //Left servo on arm
    public Servo rightServo; //Right servo on arm

    @Override
    public void init() {
        frontLeft = hardwareMap.dcMotor.get("frontLeft"); //Left wheel motor is defined and named
        frontRight = hardwareMap.dcMotor.get("frontRight"); //Right wheel motor is defined and named
        backLeft = hardwareMap.dcMotor.get("backLeft"); //Left wheel motor is defined and named
        backRight = hardwareMap.dcMotor.get("backRight"); //Back right wheel motor is defined and named
        armLeft = hardwareMap.dcMotor.get("armLeft");  //Left motor arm is defined and named
        armRight = hardwareMap.dcMotor.get("armRight"); //Right motor arm is defined and named
        leftServo = hardwareMap.servo.get("leftServo"); //leftServo (gripper servo) is defined
        rightServo = hardwareMap.servo.get("rightServo"); //rightServo (gripper servo) is defined
        frontRight.setDirection(DcMotorSimple.Direction.REVERSE); //Right wheels are always negative, for wheel base, because of base assembly
        backRight.setDirection(DcMotorSimple.Direction.REVERSE); //Right wheels are always negative, for wheel base, because of base assembly
        armLeft.setDirection(DcMotorSimple.Direction.REVERSE); //Right arm is always negative for lifting, because of assembly
        leftServo.setPosition(-1); //Setting leftServo to start in initialization at start position, has to be between 0 and 1
        rightServo.setPosition(1); //Setting rightServo to start in initialization at start position, has to be between 0 and 1
    }
    @Override
    public void loop() {
        double leftServoPos = leftServo.getPosition(); //Creates a variable that has the value of the current position of servo 1 and has decimal values
        double rightServoPos = rightServo.getPosition(); //Creates a variable that has the value of the current position of servo 2 and has decimal values
        //Servos
        if (gamepad2.right_stick_x < 0 && leftServoPos < .8 && rightServoPos > -.8) { //If the right stick is pushed left, the servos close
            leftServo.setPosition(leftServoPos + .02);
            rightServo.setPosition(rightServoPos - .02);
        } else if (gamepad2.right_stick_x > 0 && leftServoPos != 0 && rightServoPos != 1) { //If the left stick is pushed right, the servos open
            leftServo.setPosition(leftServoPos - .02);
            rightServo.setPosition(rightServoPos + .02);
        }
        //Driving
        if (gamepad1.left_stick_y != 0) { //Driving
            //If the left stick of controller 1 y not = 0, the robot will move forward or backward, setting the power to how far the stick is pushed
            frontLeft.setPower(gamepad1.left_stick_y * .8);
            frontRight.setPower(gamepad1.left_stick_y * .8);
            backLeft.setPower(gamepad1.left_stick_y * -.8);
            backRight.setPower(gamepad1.left_stick_y * -.8);
        } else if (gamepad1.right_stick_x != 0) { //Turning
            //If the right stick of controller 1 x not = 0, the robot will turn right or left, setting the power to how far the stick is pushed
            frontLeft.setPower(-gamepad1.right_stick_x * .5);
            frontRight.setPower(gamepad1.right_stick_x * .5);
            backLeft.setPower(-gamepad1.right_stick_x * .5);
            backRight.setPower(gamepad1.right_stick_x * .5);
        } else {//Null
            frontLeft.setPower(0);
            frontRight.setPower(0);
            backLeft.setPower(0);
            backRight.setPower(0);
        }
        //Arm
        if (gamepad2.left_stick_y < 0) { //If left stick is pushed up, arm moves up
            armLeft.setPower(gamepad2.left_stick_y * .5);
            armRight.setPower(gamepad2.left_stick_y * .5);
        } else if (gamepad2.left_stick_y > 0) { //If left stick is pushed down, arm moves down
            armLeft.setPower(gamepad2.left_stick_y * .1);
            armRight.setPower(gamepad2.left_stick_y * .1);
        } else { //Null
            armLeft.setPower(0);
            armRight.setPower(0);
        }
        if (gamepad2.right_bumper){ //Button to set servos just outside of the size of the cube
            leftServo.setPosition(.65);//More towards 1 is more open
            rightServo.setPosition(.45);//More towards 1 is more open
        }
    }
}
