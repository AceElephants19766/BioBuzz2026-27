package org.firstinspires.ftc.teamcode.teleops;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;

import org.firstinspires.ftc.teamcode.Utils;

@TeleOp
public class ShooterTest extends UpgradedTeleop {

    DcMotorEx shooterMotor;

    private final double TICKS_PER_REVOLUTION = 28;
    private final double RATIO = 20./20.;
    private final double SECOND_TO_MINUTE = 60;

    @Override
    public void kojaInit() {

        shooterMotor = hardwareMap.get(DcMotorEx.class, "shooter");

        Utils.getInstance().gamepadEx1.getGamepadButton(GamepadKeys.Button.A).toggleWhenPressed(
                new InstantCommand(() -> shooterMotor.setPower(1)),
                new InstantCommand(() -> shooterMotor.setPower(0))
        );

        Utils.getInstance().gamepadEx1.getGamepadButton(GamepadKeys.Button.X).toggleWhenPressed(
                new InstantCommand(() -> shooterMotor.setPower(-1)),
                new InstantCommand(() -> shooterMotor.setPower(0))
        );
    }

    public double getRPM() {
        return (shooterMotor.getVelocity() / TICKS_PER_REVOLUTION) * SECOND_TO_MINUTE * RATIO;
    }

    @Override
    public void run() {
        super.run();

        telemetry.addData("rpm = ", getRPM());
        telemetry.update();
    }
}
