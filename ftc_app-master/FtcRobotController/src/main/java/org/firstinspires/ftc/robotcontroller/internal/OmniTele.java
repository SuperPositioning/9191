package org.firstinspires.ftc.robotcontroller.internal;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Brandt_Ross on 9/25/17.
 */

@TeleOp(name = "OmniTele", group = "9191")
public class OmniTele extends OpMode {

    //TODO: Add the variables for operating the arm
    private DcMotor frontLeft; //This defines the front left motor as a motor
    private DcMotor frontRight; //This defines the front right motor as a motor
    private DcMotor backLeft; //This defines the back left motor as a motor
    private DcMotor backRight; //This defines the back right motor as a motor
    private DcMotor armRight; //This defines the arm motor as a motor
    private DcMotor armLeft; //This defines the arm motor as a motor

    private Servo gripperLeftHand; //This defines the left servo of the gripper as a servo
    private Servo gripperRightHand; //This defines the right servo of the gripper as a servo

    @Override
    public void init(){
        frontLeft = hardwareMap.dcMotor.get("frontLeft"); //Sets a value for the front left motor
        frontRight = hardwareMap.dcMotor.get("frontRight"); //Sets a value for the front right motor
        backLeft = hardwareMap.dcMotor.get("backLeft"); //Sets a value for the back left motor
        backRight = hardwareMap.dcMotor.get("backRight"); //Sets a value for the back right motor

        armRight = hardwareMap.dcMotor.get("armRight"); //Sets a value for the right arm motor
        armLeft = hardwareMap.dcMotor.get("armLeft"); //Sets a value for the left arm motor

        gripperLeftHand = hardwareMap.servo.get("gripperLeftHand"); //Sets a value for the left hand servo of the gripper
        gripperRightHand = hardwareMap.servo.get("gripperRightHand"); //Sets a value for the right hand servo of the gripper

        gripperRightHand.setPosition(1); //This initializes the right servo for the gripper
        gripperLeftHand.setPosition(0); //This initializes the left servo for the gripper

        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backRight.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public void loop(){
        //This stores the current position of the left and right hand servos of the gripper as there current position
        //This needs to be in the loop because it needs to preform this action a lot
        double gripperLeftHandPos = gripperLeftHand.getPosition();
        double gripperRightHandPos = gripperRightHand.getPosition();

        //This deals with moving the robot forward, backward, left, or right
        if (gamepad1.left_stick_y != 0){
            frontLeft.setPower(gamepad1.left_stick_y);
            frontRight.setPower(gamepad1.left_stick_y);
            backLeft.setPower(-gamepad1.left_stick_y);
            backRight.setPower(-gamepad1.left_stick_y);
        } else if(gamepad1.left_stick_x != 0){
            frontLeft.setPower(-gamepad1.left_stick_x);
            frontRight.setPower(gamepad1.left_stick_x);
            backLeft.setPower(gamepad1.left_stick_x);
            backRight.setPower(-gamepad1.left_stick_x);
        } else if (gamepad1.right_stick_x != 0) {
            frontLeft.setPower(gamepad1.right_stick_x);
            frontRight.setPower(gamepad1.right_stick_x);
            backLeft.setPower(gamepad1.right_stick_x);
            backRight.setPower(gamepad1.right_stick_x);
        } else {
            frontLeft.setPower(0);
            frontRight.setPower(0);
            backLeft.setPower(0);
            backRight.setPower(0);
        }

        //This code will have the gripper close and open
        if (gamepad2.left_bumper && gripperLeftHandPos != .5) {
            gripperLeftHand.setPosition(gripperLeftHandPos - .1);
        } else if (gamepad2.left_trigger != 0 && gripperLeftHandPos != 1){
            gripperLeftHand.setPosition(gripperLeftHandPos + .1);
        }

        if (gamepad2.right_bumper && gripperRightHandPos != 1) {
            gripperRightHand.setPosition(gripperRightHandPos + .1);
        } else if (gamepad2.right_trigger != 0 && gripperRightHandPos != .5){
            gripperRightHand.setPosition(gripperRightHandPos - .1);
        }

        //TODO: redo the arm completely
        //This adds support for the arm
        if (gamepad2.left_stick_y != 0){
            armRight.setPower(gamepad2.left_stick_y * .25);
            armLeft.setPower(-gamepad2.left_stick_y * .25);
        } else {
            armRight.setPower(0);
            armLeft.setPower(0);
        }
    }
}
