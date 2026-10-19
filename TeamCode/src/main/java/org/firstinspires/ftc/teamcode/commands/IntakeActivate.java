package org.firstinspires.ftc.teamcode.commands;

import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.Intake;

public class IntakeActivate extends CommandBase {

    public IntakeActivate() {
        Intake intake = Intake.getInstance();
    }
}
