package org.firstinspires.ftc.teamcode.commands.intakeCommands;

import com.seattlesolvers.solverslib.command.InstantCommand;
import org.firstinspires.ftc.teamcode.subsystems.intake.IntakeRoller;

public class IntakeDeactivate extends InstantCommand {

    public IntakeDeactivate () {
        super(() -> IntakeRoller.getInstance().setPower(IntakeRoller.INACTIVE_ROLLER_POWER), IntakeRoller.getInstance());
    }
}
