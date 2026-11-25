package org.firstinspires.ftc.teamcode.commands;

import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.ShooterPID;
import com.acmerobotics.dashboard.FtcDashboard;
import java.lang.annotation.Target;

public class ShooterGetToRPM extends CommandBase {

    private final ShooterPID shooterPID;
    private final double target;

    public ShooterGetToRPM(ShooterPID shooterPID, int target) {
        this.shooterPID = shooterPID;
        this.target = target;
        addRequirements(shooterPID);
    }

    @Override
    public void initialize() {
        shooterPID.pidController.setSetPoint(target);
    }

    @Override
    public void execute() {
        shooterPID.pidController.setP(ShooterPID.kP); //lev
        shooterPID.pidController.setSetPoint(ShooterPID.TARGET_RPM); //lev changed
        double feedforward = ShooterPID.kF * ShooterPID.TARGET_RPM;
        double correction = shooterPID.pidController.calculate(shooterPID.getRPM());
        shooterPID.setPower(
                feedforward + correction );
        FtcDashboard.getInstance().getTelemetry().addData("power", feedforward + correction);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        shooterPID.setPower(0);
    }
}
