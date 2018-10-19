package org.firstinspires.ftc.robotcontroller.internal;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name="StndTele", group="9191")
public class StndTele extends OpMode {
    private DcMotor frontLeft; //Front left wheel
    private DcMotor frontRight; //Front right wheel
    private DcMotor backLeft; //Back left wheel
    private DcMotor backRight; //Back right wheel
    private DcMotor armLeft; //Left arm motor
    private DcMotor armRight; //Right arm motor
    private Servo leftServo; //Left servo on arm
    private Servo rightServo; //Right servo on arm

    @Override
    public void init() { //Setting the names for the phones of each motor and servo
        frontLeft = hardwareMap.dcMotor.get("frontLeft");
        frontRight = hardwareMap.dcMotor.get("frontRight");
        backLeft = hardwareMap.dcMotor.get("backLeft");
        backRight = hardwareMap.dcMotor.get("backRight");
        armLeft = hardwareMap.dcMotor.get("armLeft");
        armRight = hardwareMap.dcMotor.get("armRight");
        leftServo = hardwareMap.servo.get("leftServo");
        rightServo = hardwareMap.servo.get("rightServo");
        frontRight.setDirection(DcMotorSimple.Direction.REVERSE); //Right wheels are always negative
        backRight.setDirection(DcMotorSimple.Direction.REVERSE); //Right wheels are always negative
        armLeft.setDirection(DcMotorSimple.Direction.REVERSE); //Right arm is always negative for lifting
        leftServo.setPosition(-1); //Setting leftServo to start in initialization at start position
        rightServo.setPosition(1); //Setting rightServo to start in initialization at start position
    }
    @Override
    public void loop() {
        double leftServoPos = leftServo.getPosition();
        double rightServoPos = rightServo.getPosition();
        //Servos
        if (gamepad2.right_stick_x < 0 && leftServoPos < .8 && rightServoPos > -.8) { //If the right stick is pushed left, the servos close
            leftServo.setPosition(leftServoPos + .04);
            rightServo.setPosition(rightServoPos - .04);
        } else if (gamepad2.right_stick_x > 0 && leftServoPos != 0 && rightServoPos != 1) { //If the right stick is pushed right, the servos open
            leftServo.setPosition(leftServoPos - .04);
            rightServo.setPosition(rightServoPos + .04);
        }
        //Driving
        if (gamepad1.left_stick_y != 0) {
            //If the left stick of controller 1 y not = 0, the robot will move forward or backward, setting the power to how far the stick is pushed
            frontLeft.setPower(gamepad1.left_stick_y * .55);
            frontRight.setPower(gamepad1.left_stick_y * .55);
            backLeft.setPower(gamepad1.left_stick_y * -.55);
            backRight.setPower(gamepad1.left_stick_y * -.55);
        } else if (gamepad1.right_stick_x != 0) { //Turning
            //If the right stick of controller 1 x not = 0, the robot will turn right or left, setting the power to how far the stick is pushed
            frontLeft.setPower(-gamepad1.right_stick_x * .35);
            frontRight.setPower(gamepad1.right_stick_x * .35);
            backLeft.setPower(-gamepad1.right_stick_x * .35);
            backRight.setPower(gamepad1.right_stick_x * .35);
        } else { //Set power to 0
            frontLeft.setPower(0);
            frontRight.setPower(0);
            backLeft.setPower(0);
            backRight.setPower(0);
        }
        //Arm
        if (gamepad2.left_stick_y < 0) { //If left stick is pushed up, arm moves up
            armLeft.setPower(gamepad2.left_stick_y * .5);
            armRight.setPower(gamepad2.left_stick_y * .5);
        } else if (gamepad2.left_stick_y > 0 ) { //If left stick is pushed down, arm moves down
            armLeft.setPower(gamepad2.left_stick_y * .1);
            armRight.setPower(gamepad2.left_stick_y * .1);
        } else { //Null
            armLeft.setPower(0);
            armRight.setPower(0);
        } if (gamepad2.right_bumper){ //Button to set servos just outside of the size of the cube
            leftServo.setPosition(.7);//More towards 1 is more open
            rightServo.setPosition(.3);//More towards 1 is more open
        }
        telemetry.addLine("Right :: " + rightServoPos);
        telemetry.addLine("Left :: " + leftServoPos);
    }
}