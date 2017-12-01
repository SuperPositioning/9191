package org.firstinspires.ftc.robotcontroller.internal;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Brandt_Ross on 12/1/17.
 */

@Autonomous(name = "OmniAuto", group = "9191")
public class ORLL extends LinearOpMode {
    //TODO: Add the variables for operating the arm
    private DcMotor frontLeft; //This defines the front left motor as a motor
    private DcMotor frontRight; //This defines the front right motor as a motor
    private DcMotor backLeft; //This defines the back left motor as a motor
    private DcMotor backRight; //This defines the back right motor as a motor

    private Servo gripperLeftHand; //This defines the left servo of the gripper as a servo
    private Servo gripperRightHand; //This defines the right servo of the gripper as a servo

    private void goForward (double power, int runtime){
        frontLeft.setPower(power);
        frontRight.setPower(-power);
        backLeft.setPower(power);
        backRight.setPower(-power);
        sleep(runtime);
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
    }

    private void goBack (double power, int runtime){
        frontLeft.setPower(-power);
        frontRight.setPower(power);
        backLeft.setPower(-power);
        backRight.setPower(power);
        sleep(runtime);
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
    }

    private void rotateLeft (double power, int runtime){
        frontLeft.setPower(-power);
        frontRight.setPower(-power);
        backLeft.setPower(-power);
        backRight.setPower(-power);
        sleep(runtime);
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
    }

    private void rotateRight (double power, int runtime){
        frontLeft.setPower(power);
        frontRight.setPower(power);
        backLeft.setPower(power);
        backRight.setPower(power);
        sleep(runtime);
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
    }

    @Override
    public void runOpMode() throws InterruptedException {
        //TODO: Finish defenitions of parts
        frontLeft = hardwareMap.dcMotor.get("frontLeft"); //Sets a value for the front left motor
        frontRight = hardwareMap.dcMotor.get("frontRight"); //Sets a value for the front right motor
        backLeft = hardwareMap.dcMotor.get("backLeft"); //Sets a value for the back left motor
        backRight = hardwareMap.dcMotor.get("backRight"); //Sets a value for the back right motor

        gripperLeftHand = hardwareMap.servo.get("gripperLeftHand"); //Sets a value for the left hand servo of the gripper
        gripperRightHand = hardwareMap.servo.get("gripperRightHand"); //Sets a value for the right hand servo of the gripper

        gripperRightHand.setPosition(1); //This initializes the right servo for the gripper
        gripperLeftHand.setPosition(0); //This initializes the left servo for the gripper

        waitForStart(); //Anything after this will be run after we press initialize
        //TODO: Test values
        rotateRight(1, 1000); //to face back of robot to jewel
        goBack(1, 1000); //to knock the jewel off
        goForward(1, 1000); //just a little to have room to turn
        rotateLeft(1, 1000); //face perpendicular to the center line
        goForward(1,1000); //get passed the balancing stone
        rotateRight(1, 1000); //90˚ around the balancing stone
        goForward(1, 1000); //have the robot lined up with the cryptobox parking
        rotateRight(1, 1000); //90˚ around the balancing stone
        goForward(1, 1000); //until robot is in the cryptobox parking
    }
}
