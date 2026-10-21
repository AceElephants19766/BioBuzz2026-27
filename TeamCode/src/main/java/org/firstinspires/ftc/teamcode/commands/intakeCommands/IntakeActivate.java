package org.firstinspires.ftc.teamcode.commands.intakeCommands;

import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.Intake;

public class IntakeActivate extends CommandBase {

    public IntakeActivate() {
        Intake.getInstance().setPower(1);
    }
}
