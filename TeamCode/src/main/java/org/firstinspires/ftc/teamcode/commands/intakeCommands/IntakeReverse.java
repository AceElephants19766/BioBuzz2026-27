package org.firstinspires.ftc.teamcode.commands.intakeCommands;

import com.seattlesolvers.solverslib.command.InstantCommand;
import org.firstinspires.ftc.teamcode.subsystems.intake.IntakeRoller;

public class IntakeReverse extends InstantCommand {
    public IntakeReverse() {
        super(() -> IntakeRoller.getInstance().setPower(IntakeRoller.REVERSE_POWER), IntakeRoller.getInstance());
    }
}
