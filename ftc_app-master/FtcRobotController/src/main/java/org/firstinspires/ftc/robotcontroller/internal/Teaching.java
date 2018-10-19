package org.firstinspires.ftc.robotcontroller.internal; //Take notes
import com.qualcomm.robotcore.hardware.DcMotor; //Please
import com.qualcomm.robotcore.hardware.Servo; //You need them
import com.qualcomm.robotcore.hardware.DcMotorSimple; //Not on Eclipse either
import com.qualcomm.robotcore.eventloop.opmode.OpMode; //Use the notes app
import com.qualcomm.robotcore.eventloop.opmode.TeleOp; //Or docs, idc

@TeleOp (name = "Chase", group = "9191")

public class Teaching extends OpMode  {
    private DcMotor backLeft; //Please don't using this naming convention
    private DcMotor backRight;
    private Servo leftServo;
    private Servo rightServo;

    @Override
    public void init(){ //Telling each variable what to be called on the phone
        backLeft = hardwareMap.dcMotor.get("backLeft");
        backRight = hardwareMap.dcMotor.get("backRight");
        leftServo = hardwareMap.servo.get("leftServo");
        rightServo = hardwareMap.servo.get("rightServo");
        backRight.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    @Override
    public void loop() {
        double leftServoPos = leftServo.getPosition();
        double rightServoPos = rightServo.getPosition();
        if(gamepad1.left_stick_y != 0) {
            backLeft.setPower(gamepad1.left_stick_y);
            backRight.setPower(gamepad1.left_stick_y);
        } else if(gamepad1.right_stick_x != 0){
            backLeft.setPower(-gamepad1.right_stick_x);
            backRight.setPower(gamepad1.right_stick_x);
        } else {
            backLeft.setPower(0);
            backRight.setPower(0);
        } if(gamepad2.right_bumper && leftServoPos != 1 && rightServoPos != -1) {
            leftServo.setPosition(leftServoPos + .01);
            rightServo.setPosition(rightServoPos - .01);
        } else if(gamepad2.left_bumper && leftServoPos != -1 && rightServoPos != 1) {
            leftServo.setPosition(leftServoPos - .01);
            rightServo.setPosition(rightServoPos + .01);
        }
    }
}
