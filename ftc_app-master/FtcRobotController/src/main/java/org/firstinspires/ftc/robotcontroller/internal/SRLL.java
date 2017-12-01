package org.firstinspires.ftc.robotcontroller.internal;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name="SRLL", group="9191")
public    class SRLL   extends LinearOpMode{
    private DcMotor right;
    private DcMotor left;
    private DcMotor ArmLeft;
    private DcMotor ArmRight; //Motor for lifting
    private Servo servo1; //Right servo on arm
    private Servo servo2; //Left servo on arm
    //double servo1Pos = servo1.getPosition(); //Creates a variable that has the value of the current position of servo 1
    //double servo2Pos = servo2.getPosition(); //Creates a variable that has the value of the current position of servo 2

    private void goForward(double power, int runtime) {
        left.setPower(power);
        right.setPower(power);
        sleep(runtime);
        left.setPower(0);
        right.setPower(0);
    }
    private void goBack(double power, int runtime) {
        left.setPower(-power);
        right.setPower(-power);
        sleep(runtime);
        left.setPower(0);
        right.setPower(0);
    }
    private void turnLeft(double power, int runtime) {
        left.setPower(power);
        right.setPower(-power);
        sleep(runtime);
        left.setPower(0);
        right.setPower(0);
    }
    private void turnRight(double power, int runtime) {
        left.setPower(-power);
        right.setPower(power);
        sleep(runtime);
        left.setPower(0);
        right.setPower(0);
    }

    @Override
    public void runOpMode() throws InterruptedException {
        left = hardwareMap.dcMotor.get("left"); //left wheel motor is defined and named
        right = hardwareMap.dcMotor.get("right"); //right wheel motor is defined and named
        ArmLeft = hardwareMap.dcMotor.get("armLeft");  //Left motor arm is defined and named
        ArmRight = hardwareMap.dcMotor.get("armRight"); //Right motor arm is defined and named
        servo1 = hardwareMap.servo.get("servo1"); //servo1 (gripper servo) is defined
        servo2 = hardwareMap.servo.get("servo2"); //servo2 (gripper servo) is defined
        right.setDirection(DcMotorSimple.Direction.REVERSE); //Right wheel is always negative, for wheel base, because of base assembly
        ArmLeft.setDirection(DcMotorSimple.Direction.REVERSE); //Right arm is always negative for lifting, because of assembly
        servo1.setPosition(-1); //Setting servo1 to start in initialization at start position, has to be between 0 and 1
        servo2.setPosition(1); //Setting servo2 to start in initialization at start position, has to be between 0 and 1
        waitForStart();

    turnRight(1,200);
    goBack(1,1000);
    goForward(1,200);
    turnLeft(1,1000);
    goForward(1,1000);
    turnRight(1,1000);
    goForward(1,1000);
    turnRight(1,1000);
    goForward(1,1000);

    }
}
