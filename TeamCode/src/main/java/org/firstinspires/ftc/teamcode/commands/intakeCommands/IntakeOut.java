package org.firstinspires.ftc.teamcode.commands.intakeCommands;

import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.Intake;

public class IntakeOut extends CommandBase {
    public  IntakeOut() {
        Intake.getInstance().setPosition(0.3);
    }
}
