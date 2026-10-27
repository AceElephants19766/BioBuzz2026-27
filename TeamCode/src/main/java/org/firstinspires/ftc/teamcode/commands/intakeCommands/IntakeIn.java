package org.firstinspires.ftc.teamcode.commands.intakeCommands;

import com.seattlesolvers.solverslib.command.InstantCommand;
import org.firstinspires.ftc.teamcode.subsystems.intake.IntakeLifter;

public class IntakeIn extends InstantCommand {
    public IntakeIn() {
        super(() -> IntakeLifter.getInstance().setPosition(IntakeLifter.NOT_IN_POSITION), IntakeLifter.getInstance());
    }
}
