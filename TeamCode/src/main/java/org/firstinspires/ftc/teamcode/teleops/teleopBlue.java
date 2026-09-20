package org.firstinspires.ftc.teamcode.teleops;

import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;

public class teleopBlue extends UpgradedTeleop {

    @Override
    public void kojaInit() {
        Drivetrain.getInstance();
    }

}
