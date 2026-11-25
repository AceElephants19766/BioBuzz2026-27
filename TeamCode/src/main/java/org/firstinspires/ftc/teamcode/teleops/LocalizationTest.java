package org.firstinspires.ftc.teamcode.teleops;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.seattlesolvers.solverslib.command.CommandOpMode;

import org.firstinspires.ftc.teamcode.Constantino;
import org.firstinspires.ftc.teamcode.pedroPathing.Constants;

@TeleOp
public class LocalizationTest extends CommandOpMode {

    DcMotorEx encoderX;
    DcMotorEx encoderY;

    @Override
    public void initialize() {
        encoderX = hardwareMap.get(DcMotorEx.class, Constantino.BACK_RIGHT);
        encoderY = hardwareMap.get(DcMotorEx.class, Constantino.FRONT_LEFT);

        encoderX.setDirection(DcMotorSimple.Direction.REVERSE);
        encoderY.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    @Override
    public void run() {
        super.run();

        telemetry.addData("x ", encoderX.getCurrentPosition());
        telemetry.addData("y ", encoderY.getCurrentPosition());
        telemetry.update();
    }
}
