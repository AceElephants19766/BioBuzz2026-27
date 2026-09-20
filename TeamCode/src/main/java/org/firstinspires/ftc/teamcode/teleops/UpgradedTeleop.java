package org.firstinspires.ftc.teamcode.teleops;

import com.seattlesolvers.solverslib.command.CommandOpMode;

import org.firstinspires.ftc.teamcode.Utils;

public abstract class UpgradedTeleop extends CommandOpMode {

    public abstract void kojaInit();

    @Override
    public void initialize() {
        Utils.getInstance().init(hardwareMap, telemetry, gamepad1, gamepad2);
        kojaInit();
    }
}
