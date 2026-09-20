package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Utils {

    private static Utils instance;

    public static Utils getInstance() {
        if (instance == null) instance = new Utils();
        return instance;
    }

    public HardwareMap hardwareMap;
    public Telemetry telemetry;
    public Gamepad gamepad1, gamepad2;

    public void init(HardwareMap hardwareMap, Telemetry telemetry, Gamepad gamepad1, Gamepad gamepad2) {
        this.hardwareMap = hardwareMap;
        this.telemetry = telemetry;
        this.gamepad1 = gamepad1;
        this.gamepad2 = gamepad2;
    }


}
