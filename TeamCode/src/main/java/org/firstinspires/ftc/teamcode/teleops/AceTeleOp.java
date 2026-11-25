package org.firstinspires.ftc.teamcode.teleops;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.command.CommandScheduler;
import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;
import org.firstinspires.ftc.teamcode.Utils;
import org.firstinspires.ftc.teamcode.commands.Drive;
import org.firstinspires.ftc.teamcode.commands.ShooterGetToRPM;
import org.firstinspires.ftc.teamcode.commands.conveyorCommands.ConveyorActivate;
import org.firstinspires.ftc.teamcode.commands.conveyorCommands.ConveyorDeactivate;
import org.firstinspires.ftc.teamcode.commands.conveyorCommands.ConveyorReverse;
import org.firstinspires.ftc.teamcode.commands.intakeCommands.IntakeActivate;
import org.firstinspires.ftc.teamcode.commands.intakeCommands.IntakeDeactivate;
import org.firstinspires.ftc.teamcode.commands.intakeCommands.IntakeReverse;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.ShooterPID;

@TeleOp
public class AceTeleOp extends UpgradedTeleop {

    private ShooterPID shooterPID;

    @Override
    public void kojaInit() {

        shooterPID = new ShooterPID(hardwareMap);

        Utils.getInstance().gamepadEx1.getGamepadButton(GamepadKeys.Button.X).toggleWhenPressed(
                new ShooterGetToRPM(shooterPID, 2000)
        );
        CommandScheduler.getInstance().setDefaultCommand(Drivetrain.getInstance(), new Drive());

        Utils.getInstance().gamepadEx1.getGamepadButton(GamepadKeys.Button.LEFT_BUMPER).toggleWhenPressed(
                new ConveyorActivate(), new ConveyorDeactivate()
        );

        Utils.getInstance().gamepadEx1.getGamepadButton(GamepadKeys.Button.RIGHT_BUMPER).toggleWhenPressed(
                new IntakeActivate(), new IntakeDeactivate()
        );

        Utils.getInstance().gamepadEx2.getGamepadButton(GamepadKeys.Button.RIGHT_BUMPER).toggleWhenPressed(
                new IntakeReverse(), new IntakeDeactivate()
        );

        Utils.getInstance().gamepadEx2.getGamepadButton(GamepadKeys.Button.LEFT_BUMPER).toggleWhenPressed(
                new ConveyorReverse(), new ConveyorDeactivate()
        );

        Utils.getInstance().gamepadEx1.getGamepadButton(GamepadKeys.Button.START).whenPressed(
                new InstantCommand(
                        () -> Drivetrain.getInstance().resetIMU()
                )
        );
    }
}
