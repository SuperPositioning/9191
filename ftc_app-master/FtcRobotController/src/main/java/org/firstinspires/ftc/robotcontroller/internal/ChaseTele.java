package org.firstinspires.ftc.robotcontroller.internal;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;


@TeleOp(name="StndTele", group="Chase")

public class ChaseTele extends OpMode {

    //Defining variable for L&R wheels
    public DcMotor left; //left wheel
    public DcMotor right; //right wheel
    public Servo servo1; //Servo number 1
    public Servo servo2; //Servo number 2
    public Servo servo3; //Servo number 3
    public Servo servo4; //Servo number 4

    @Override
    public void init() {
        left = hardwareMap.dcMotor.get("left"); //left is defined above, left is the same as phone
        right = hardwareMap.dcMotor.get("right"); //right is defined above, right is the same as phone

        servo1 = hardwareMap.servo.get("servo1"); //servo1 is defined, servo1 is same as phone
        servo2 = hardwareMap.servo.get("servo2"); //servo2 is defined, servo 2 is same as phone
        servo3 = hardwareMap.servo.get("servo3"); //servo2 is defined, servo 3 is same as phone
        servo4 = hardwareMap.servo.get("servo4"); //servo2 is defined, servo 4 is same as phone


        right.setDirection(DcMotorSimple.Direction.REVERSE); //Right wheel is always negative, for wheel base, because of base assembly
        /*
        servo1.setPosition(0); //Setting servo1 to start in initialization at start position, has to be between 0 and 1
        servo2.setPosition(1); //Setting servo2 to start in initialization at start position, has to be between 0 and 1
        servo3.setPosition(0); //Setting servo3 to start in initialization at start position, has to be between 0 and 1
        servo4.setPosition(0); //Setting servo4 to start in initialization at start position, has to be between 0 and 1
        */
    }

    @Override
    public void loop() {

        double servo1Pos = servo1.getPosition(); //Creates a variable that has the value of the current position of servo 1
        double servo2Pos = servo2.getPosition(); //Creates a variable that has the value of the current position of servo 2
        double servo4Pos = servo3.getPosition(); //Creates a variable that has the value of the current position of servo 3
        double servo3Pos = servo4.getPosition(); //Creates a variable that has the value of the current position of servo 4
        telemetry.addLine("Servo 1: " + servo1Pos);
        telemetry.addLine("Servo 2: " + servo2Pos);
        telemetry.addLine("Servo 3: " + servo3Pos);
        telemetry.addLine("Servo 4: " + servo4Pos);

        /*
        //Servos
        if (gamepad2.right_stick_x < 0 && servo1Pos != 1 && servo2Pos != 0) { //Reads if the right stick of the 2nd controller is being pushed left or right and if the servos postitions are not equal to 1
            servo1.setPosition(servo1Pos + .01); //Increases positions by .01 degree
            servo2.setPosition(servo2Pos - .01);

        } else if (gamepad2.right_stick_x > 0 && servo1Pos != 0 && servo2Pos != 1) { //Reads if the right stick of the 2nd controller is being pushed left or right and if the servos postitions are not equal to 0
            servo1.setPosition(servo1Pos - .01); //Decreases positions by .01 degree
            servo2.setPosition(servo2Pos + .01);
        }

        if (gamepad2.left_stick_y < 0 && servo3Pos != 1 && servo4Pos != 0) { //Reads if the gamepad left stick is pushed right/left, also if the servos position is equal to 1
            servo3.setPosition(servo3Pos +.01); //Increases positions by .01 degree
            servo4.setPosition(servo4Pos -.01);
        }
        else if(gamepad2.left_stick_y > 0 && servo3Pos != 0 && servo4Pos != 1) { //Reads if the gamepad left stick is pushed up/down, also if the servos position is equal to 0
            servo3.setPosition(servo3Pos - .01); //Decreases positions by .01 degree
            servo4.setPosition(servo4Pos + .01);
        }

        //Motors
        if (gamepad1.left_stick_y != 0) {
            //If the left stick of controller 1 y not = 0, the robot will move forward or backward, setting the power to how far the stick is pushed
            left.setPower(gamepad1.left_stick_y);
            right.setPower(gamepad1.left_stick_y);
        }
        else if (gamepad1.right_stick_x != 0) {
            //If the right stick of controller 1 x not = 0, the robot will turn right or left, setting the power to how far the stick is pushed
            right.setPower(-gamepad1.right_stick_x);
            left.setPower(gamepad1.right_stick_x);
        } else {
            left.setPower(0);
            right.setPower(0);
        }
        */
    }
}