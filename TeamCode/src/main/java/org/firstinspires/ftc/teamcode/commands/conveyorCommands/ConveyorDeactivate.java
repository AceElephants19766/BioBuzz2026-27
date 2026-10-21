package org.firstinspires.ftc.teamcode.commands.conveyorCommands;

import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.Conveyor;

public class ConveyorDeactivate extends CommandBase {
    public ConveyorDeactivate() {
        Conveyor.getInstance().setPower(0);
    }
}
