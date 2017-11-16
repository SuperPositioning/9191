package org.firstinspires.ftc.robotcontroller.internal;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name="StndTele", group="9191")

public class ChaseTele extends OpMode {
    //Defining variable for L&R wheels
    public DcMotor left; //left wheel
    public DcMotor right; //right wheel
    public DcMotor armLeft; //Left arm motor
    public DcMotor armRight; //Right arm motor
    public Servo servo1; //Right servo on arm
    public Servo servo2; //Left servo on arm

    @Override
    public void init() {
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
    }
    @Override
    public void loop() {
        double servo1Pos = servo1.getPosition(); //Creates a variable that has the value of the current position of servo 1 and has decimal values
        double servo2Pos = servo2.getPosition(); //Creates a variable that has the value of the current position of servo 2 and has decimal values
        //Servos
        if (gamepad2.right_stick_x < 0 && servo1Pos < .43 && servo2Pos > -.43) { //Closing arm
            servo1.setPosition(servo1Pos + .01);
            servo2.setPosition(servo2Pos - .01);
        } else if (gamepad2.right_stick_x > 0 && servo1Pos != 0 && servo2Pos != 1) { //Opening arm
            servo1.setPosition(servo1Pos - .01);
            servo2.setPosition(servo2Pos + .01);
        }
        //Driving
        if (gamepad1.left_stick_y != 0) { //Driving
            //If the left stick of controller 1 y not = 0, the robot will move forward or backward, setting the power to how far the stick is pushed
            left.setPower(gamepad1.left_stick_y * .8);
            right.setPower(gamepad1.left_stick_y * .8);
        } else if (gamepad1.right_stick_x != 0) { //Turning
            //If the right stick of controller 1 x not = 0, the robot will turn right or left, setting the power to how far the stick is pushed
            right.setPower(gamepad1.right_stick_x * .5);
            left.setPower(-gamepad1.right_stick_x * .5);
        } else {//Null
            left.setPower(0);
            right.setPower(0);
        }

        if (gamepad2.left_stick_y !=0) { //Arm lift
            armLeft.setPower(gamepad2.left_stick_y * .3);
            armRight.setPower(gamepad2.left_stick_y * .3);
        }else{ //Null
            armLeft.setPower(0);
            armRight.setPower(0);
        }
    }
}