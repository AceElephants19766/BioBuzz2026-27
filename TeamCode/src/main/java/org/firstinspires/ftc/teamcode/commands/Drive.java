package org.firstinspires.ftc.teamcode.commands;

import com.seattlesolvers.solverslib.command.CommandBase;
import org.firstinspires.ftc.teamcode.Utils;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;

public class Drive extends CommandBase {


    public Drive() {
        addRequirements(Drivetrain.getInstance());
    }

    @Override
    public void execute() {
        Drivetrain.getInstance().fieldOrientedDrive(Utils.getInstance().gamepad1);
    }
}


