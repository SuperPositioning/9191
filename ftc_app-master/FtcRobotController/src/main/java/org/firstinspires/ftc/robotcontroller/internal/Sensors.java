package org.firstinspires.ftc.robotcontroller.internal;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;

@TeleOp (name = "Sensors", group ="9191")
public class Sensors extends OpMode {

    //DigitalChannel digitalTouch;
    OpticalDistanceSensor opticalDistance;


    //private DigitalChannel touch;
    //private OpticalDistanceSensor distance;


    @Override
    public void init() {
       // digitalTouch = hardwareMap.get(DigitalChannel.class, "digitalTouch");
        opticalDistance = hardwareMap.opticalDistanceSensor.get ("opticalDistance");
        opticalDistance.enableLed(true);
        //digitalTouch.setMode(DigitalChannel.Mode.INPUT);
    }

    @Override
    public void loop() {
       // telemetry.addLine(String.valueOf(digitalTouch.getState()));
        telemetry.addLine(String.valueOf(opticalDistance));
        telemetry.addData("Raw",    opticalDistance.getRawLightDetected()); //Copied from documentation, should be the color getting returned
        telemetry.addData("Normal", opticalDistance.getLightDetected());
        telemetry.update();
    }
}