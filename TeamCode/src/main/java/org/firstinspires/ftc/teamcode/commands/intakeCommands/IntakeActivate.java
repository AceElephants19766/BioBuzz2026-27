package org.firstinspires.ftc.teamcode.commands.intakeCommands;

import com.seattlesolvers.solverslib.command.InstantCommand;
import org.firstinspires.ftc.teamcode.subsystems.intake.IntakeRoller;

public class IntakeActivate extends InstantCommand {
    public IntakeActivate() {
        super(() -> IntakeRoller.getInstance().setPower(IntakeRoller.ACTIVE_ROLLER_POWER), IntakeRoller.getInstance());
    }
}

