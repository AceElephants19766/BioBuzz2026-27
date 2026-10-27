package org.firstinspires.ftc.teamcode.teleops;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.seattlesolvers.solverslib.command.CommandScheduler;
import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;

import org.firstinspires.ftc.teamcode.Utils;
import org.firstinspires.ftc.teamcode.commands.Drive;
import org.firstinspires.ftc.teamcode.commands.conveyorCommands.ConveyorActivate;
import org.firstinspires.ftc.teamcode.commands.conveyorCommands.ConveyorDeactivate;
import org.firstinspires.ftc.teamcode.commands.conveyorCommands.ConveyorReverse;
import org.firstinspires.ftc.teamcode.commands.intakeCommands.IntakeActivate;
import org.firstinspires.ftc.teamcode.commands.intakeCommands.IntakeDeactivate;
import org.firstinspires.ftc.teamcode.commands.intakeCommands.IntakeOut;
import org.firstinspires.ftc.teamcode.commands.intakeCommands.IntakeReverse;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.intake.IntakeRoller;

@TeleOp
public class AceTeleOp extends UpgradedTeleop{

    @Override
    public void kojaInit() {

        CommandScheduler.getInstance().setDefaultCommand(Drivetrain.getInstance(), new Drive());

        Utils.getInstance().gamepadEx1.getGamepadButton(GamepadKeys.Button.LEFT_BUMPER).toggleWhenPressed(
                new ConveyorActivate(), new ConveyorDeactivate()
        );

        Utils.getInstance().gamepadEx1.getGamepadButton(GamepadKeys.Button.RIGHT_BUMPER).toggleWhenPressed(
                new IntakeActivate(), new IntakeDeactivate()
        );

        Utils.getInstance().gamepadEx2.getGamepadButton(GamepadKeys.Button.RIGHT_BUMPER).whenPressed(
                new IntakeReverse()
        );

        Utils.getInstance().gamepadEx2.getGamepadButton(GamepadKeys.Button.LEFT_BUMPER).whenPressed(
                new ConveyorReverse()
        );


    }
}
