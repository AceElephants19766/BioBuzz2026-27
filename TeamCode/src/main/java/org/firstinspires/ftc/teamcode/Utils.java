package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Utils {

    private static Utils instance;

    public static Utils getInstance() {
        if (instance == null) instance = new Utils();
        return instance;
    }

    public HardwareMap hardwareMap;
    public Telemetry telemetry;
    public GamepadEx gamepadEx1, gamepadEx2;

    public void init(HardwareMap hardwareMap, Telemetry telemetry, Gamepad gamepad1, Gamepad gamepad2) {
        this.hardwareMap = hardwareMap;
        this.telemetry = telemetry;
        this.gamepadEx1 = new GamepadEx(gamepad1);
        this.gamepadEx2 = new GamepadEx(gamepad2);
    }


}
