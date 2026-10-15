//package org.firstinspires.ftc.teamcode.commands;
//
//import com.seattlesolvers.solverslib.command.CommandBase;
//
//import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
//
//public class Drive extends CommandBase {
//
//
//    public void Drive() {
//  Drivetrain.getInstance().
//
//    }
//
//}

//package org.firstinspires.ftc.teamcode.commands;
//
//import com.qualcomm.robotcore.hardware.Gamepad;
//import com.seattlesolvers.solverslib.command.CommandBase;
//
//import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
//
//public class Drive extends CommandBase {
//    private final Drivetrain drivetrain;
//    private final Gamepad gamepad;
//
//    public Drive(Gamepad gamepad) {
//        this.drivetrain = Drivetrain.getInstance();
//        this.gamepad = gamepad;
//        addRequirements(this.drivetrain);
//    }
//
//    @Override
//    public void execute() {
//        double strafe  = gamepad.left_stick_x;
//        double forward = -gamepad.left_stick_y; // Inverted so pushing stick up pushes robot forward
//        double turn    = gamepad.right_stick_x;
//
//        // Joystick deadzones to prevent slight resting drift
//        if (Math.abs(strafe) < 0.05)  strafe = 0;
//        if (Math.abs(forward) < 0.05) forward = 0;
//        if (Math.abs(turn) < 0.05)    turn = 0;
//
//        drivetrain.driveFieldOriented(strafe, forward, turn);
//    }

//    @Override
//    public void end(boolean interrupted) {
//        // Stop the motors instantly when the command finishes or gets interrupted
//        drivetrain.driveFieldOriented(0, 0, 0);
//    }
//}
package org.firstinspires.ftc.teamcode.commands;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;

public class Drive extends CommandBase {

    private final Drivetrain drivetrain;
    private final Gamepad gamepad;

    public Drive(Drivetrain drivetrain, Gamepad gamepad) {
        this.drivetrain = drivetrain;
        this.gamepad = gamepad;

        addRequirements(drivetrain);
    }

    @Override
    public void execute() {

        double forward = -gamepad.left_stick_y;
        double strafe = gamepad.left_stick_x;
        double turn = gamepad.right_stick_x;

        if (Math.abs(forward) < 0.05) forward = 0.0;
        if (Math.abs(strafe) < 0.05) strafe = 0.0;
        if (Math.abs(turn) < 0.05) turn = 0.0;

        forward = Math.signum(forward) * forward * forward;
        strafe = Math.signum(strafe) * strafe * strafe;
        turn = Math.signum(turn) * turn * turn;

        drivetrain.driveFieldOriented(strafe, forward, turn);
    }

    @Override
    public void end(boolean interrupted) {
        drivetrain.stop();
    }
}