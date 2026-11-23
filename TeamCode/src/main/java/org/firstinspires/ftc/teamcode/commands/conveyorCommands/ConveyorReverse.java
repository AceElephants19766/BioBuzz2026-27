package org.firstinspires.ftc.teamcode.commands.conveyorCommands;

import com.seattlesolvers.solverslib.command.InstantCommand;
import org.firstinspires.ftc.teamcode.subsystems.Conveyor;

public class ConveyorReverse extends InstantCommand {
    public ConveyorReverse() {
        super(() -> Conveyor.getInstance().setPower(Conveyor.CONVEYOR_REVERSE_POWER), Conveyor.getInstance());
    }
}
