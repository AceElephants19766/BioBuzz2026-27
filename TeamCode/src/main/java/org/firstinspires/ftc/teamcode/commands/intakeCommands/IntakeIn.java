package org.firstinspires.ftc.teamcode.commands.intakeCommands;

import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.Intake;

public class IntakeIn extends CommandBase {
    public IntakeIn() {
        Intake.getInstance().setPosition(0);
    }
}
