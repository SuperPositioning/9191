package org.firstinspires.ftc.robotcontroller.internal;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name="StndAuto", group="9191")
public class StndAuto extends LinearOpMode {
    public DcMotor frontLeft; //left wheel
    public DcMotor frontRight; //right wheel
    public DcMotor backLeft; //left wheel
    public DcMotor backRight; //right wheel
    public DcMotor armLeft; //Left arm motor
    public DcMotor armRight; //Right arm motor
    public Servo leftServo; //Left servo on arm
    public Servo rightServo; //Right servo on arm


    private void goBackward(double power, int runtime) {
        frontLeft.setPower(power);
        frontRight.setPower(power);
        backLeft.setPower(-power);
        backRight.setPower(-power);
        sleep(runtime);
        backLeft.setPower(0);
        backRight.setPower(0);
        frontLeft.setPower(0);
        frontRight.setPower(0);
    }

    @Override
    public void runOpMode() throws InterruptedException {
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

        waitForStart();
        goBackward(.25,1500);
    }
}