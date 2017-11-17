package org.firstinspires.ftc.robotcontroller.internal;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;


@Autonomous(name = "StndAuto", group = "9191")
public class StndAuto extends LinearOpMode {
    private DcMotor left;
    private DcMotor right;
    private DcMotor armLeft;
    private DcMotor armRight; //Motor for lifting
    private Servo servo1; //Right servo on arm
    private Servo servo2; //Left servo on arm
    double servo1Pos = servo1.getPosition(); //Creates a variable that has the value of the current position of servo 1
    double servo2Pos = servo2.getPosition(); //Creates a variable that has the value of the current position of servo 2

    private void goForward(double power, int runtime) {
        left.setPower(power);
        right.setPower(power);
        sleep(runtime);
        left.setPower(0);
        right.setPower(0);
    }

    private void goBackward(double power, int runtime) {
        left.setPower(-power);
        right.setPower(-power);
        sleep(runtime);
        left.setPower(0);
        right.setPower(0);
    }

    private void turnLeft(double power, int runtime) {
        left.setPower(power);
        right.setPower(-power);
        ;
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
    private void closeArm (double power, int runtime){
        servo1.setPosition(servo1Pos + .01);
        servo2.setPosition(servo2Pos - .01);
    }
    private void openArm (double power, int runtime){
        servo1.setPosition(servo1Pos - .01);
        servo2.setPosition(servo2Pos + .01);

    }

    @Override
    public void runOpMode() throws InterruptedException {
        left = hardwareMap.dcMotor.get("left"); //left wheel motor is defined and named
        right = hardwareMap.dcMotor.get("right"); //right wheel motor is defined and named
        armLeft = hardwareMap.dcMotor.get("armLeft");  //Left motor arm is defined and named
        armRight = hardwareMap.dcMotor.get("armRight"); //Right motor arm is defined and named
        servo1 = hardwareMap.servo.get("servo1"); //servo1 (gripper servo) is defined
        servo2 = hardwareMap.servo.get("servo2"); //servo2 (gripper servo) is defined
        right.setDirection(DcMotorSimple.Direction.REVERSE); //Right wheel is always negative, for wheel base, because of base assembly
        armLeft.setDirection(DcMotorSimple.Direction.REVERSE); //Right arm is always negative for lifting, because of assembly
        servo1.setPosition(-1); //Setting servo1 to start in initialization at start position, has to be between 0 and 1
        servo2.setPosition(1); //Setting servo2 to start in initialization at start position, has to be between 0 and 1
        double servo1Pos = servo1.getPosition(); //Creates a variable that has the value of the current position of servo 1
        double servo2Pos = servo2.getPosition(); //Creates a variable that has the value of the current position of servo 2
        waitForStart();

        goForward(1, 1000); //Drives forward at full power for 1000 milliseconds
        goBackward(1, 1000); //Drives backward at full power for 1000 milliseconds
        turnLeft(1, 1000); //Turns left at full power for 1000 milliseconds
        turnRight(1, 1000); //Turns right at full power for 1000 milliseconds
    }
}