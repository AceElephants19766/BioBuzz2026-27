package org.firstinspires.ftc.teamcode.commands.intakeCommands;

import com.seattlesolvers.solverslib.command.InstantCommand;

import org.firstinspires.ftc.teamcode.subsystems.intake.IntakeLifter;

public class IntakeOut extends InstantCommand {
    public  IntakeOut() {
        super(() -> IntakeLifter.getInstance().setPosition(IntakeLifter.IN_POSITION), IntakeLifter.getInstance());
    }
}
