package org.firstinspires.ftc.teamcode.commands;

import com.seattlesolvers.solverslib.command.CommandBase;
import com.seattlesolvers.solverslib.command.InstantCommand;

import org.firstinspires.ftc.teamcode.subsystems.Tort;

public class TortGetToAngle extends InstantCommand {

    public TortGetToAngle(double angle) {
        super(() -> Tort.getInstance().setAngle(angle));

    }
}
