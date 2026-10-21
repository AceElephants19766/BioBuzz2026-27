package org.firstinspires.ftc.teamcode.commands.conveyorCommands;

import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.Conveyor;

public class ConveyorActivate extends CommandBase {
    public ConveyorActivate() {
        Conveyor.getInstance().setPower(1);
    }
}
