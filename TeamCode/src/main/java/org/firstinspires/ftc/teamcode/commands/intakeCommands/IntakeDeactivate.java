package org.firstinspires.ftc.teamcode.commands.intakeCommands;

import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.Intake;

public class IntakeDeactivate extends CommandBase {

    public IntakeDeactivate () {
        Intake.getInstance().setPower(0);
    }
}