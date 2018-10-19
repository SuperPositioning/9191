package org.firstinspires.ftc.robotcontroller.internal;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "REVExpansionHub", group = "9191")
public    class REVExpansionHub extends OpMode {
    private DcMotor HDHexMotor;
    private Servo servo1;
    @Override
    public void init(){
        HDHexMotor = hardwareMap.dcMotor.get("HDHexMotor");
        servo1 = hardwareMap.servo.get("servo1");
        HDHexMotor.setDirection(DcMotorSimple.Direction.REVERSE);
    }
    @Override
    public void loop() {
        double servo1Pos = servo1.getPosition();
        if(gamepad1.left_stick_y != 0){
            HDHexMotor.setPower(gamepad1.left_stick_y);
        }
        else{
            HDHexMotor.setPower(0);
        }
        if(gamepad1.right_stick_x > 0){
            servo1.setPosition(servo1Pos + .01);
        }
        else if(gamepad1.right_stick_x < 0){
            servo1.setPosition(servo1Pos- .01);
        }
        telemetry.addLine(String.valueOf(HDHexMotor.getPower()));
    }
}
