package org.firstinspires.ftc.teamcode.teleops;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.seattlesolvers.solverslib.command.CommandScheduler;

import org.firstinspires.ftc.teamcode.commands.Drive;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;

@TeleOp
public class AceTeleOp extends UpgradedTeleop{

    @Override
    public void kojaInit() {
        CommandScheduler.getInstance().setDefaultCommand(Drivetrain, new Drive(Drivetrain, gamepad1));
    }
}
