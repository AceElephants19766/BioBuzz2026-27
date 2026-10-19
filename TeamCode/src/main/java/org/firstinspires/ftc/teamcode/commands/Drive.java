package org.firstinspires.ftc.teamcode.commands;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.seattlesolvers.solverslib.command.CommandBase;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;

public class Drive extends CommandBase {
 
    private Gamepad gamepad1;
    private Drivetrain drivetrain;

    public Drive() {
      Drivetrain drivetrain = Drivetrain.getInstance();
    }



    public Drive(Drivetrain drivetrain, Gamepad gamepad1) {
        this.drivetrain = drivetrain;
        this.gamepad1 = gamepad1;
        addRequirements(drivetrain);
    }

    @Override
    public void execute() {
        drivetrain.fieldOrientedDrive(gamepad1);
    }

}


