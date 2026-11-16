package org.firstinspires.ftc.teamcode.commands.conveyorCommands;

import com.seattlesolvers.solverslib.command.CommandBase;
import com.seattlesolvers.solverslib.command.InstantCommand;

import org.firstinspires.ftc.teamcode.subsystems.Conveyor;

public class ConveyorActivate extends InstantCommand {
    public ConveyorActivate() {
        super(() -> Conveyor.getInstance().setPower(Conveyor.ACTIVE_CONVEYOR_POWER), Conveyor.getInstance());
    }
}
