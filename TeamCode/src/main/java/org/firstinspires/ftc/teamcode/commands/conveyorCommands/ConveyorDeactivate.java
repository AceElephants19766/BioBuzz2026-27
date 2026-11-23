package org.firstinspires.ftc.teamcode.commands.conveyorCommands;

import com.seattlesolvers.solverslib.command.CommandBase;
import com.seattlesolvers.solverslib.command.InstantCommand;

import org.firstinspires.ftc.teamcode.subsystems.Conveyor;

public class ConveyorDeactivate extends InstantCommand {
    public ConveyorDeactivate() {
        super(() -> Conveyor.getInstance().setPower(Conveyor.INACTIVE_CONVEYOR_POWER), Conveyor.getInstance());
    }
}
