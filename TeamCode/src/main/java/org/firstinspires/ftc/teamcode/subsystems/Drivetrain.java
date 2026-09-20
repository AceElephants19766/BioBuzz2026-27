package org.firstinspires.ftc.teamcode.subsystems;

import com.pedropathing.follower.Follower;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.seattlesolvers.solverslib.command.SubsystemBase;

import org.firstinspires.ftc.teamcode.Utils;

public class Drivetrain extends SubsystemBase {

    private static Drivetrain instance;

    public static Drivetrain getInstance() {
        if (instance == null) instance = new Drivetrain();
        return instance;
    }

    public Drivetrain() {
        Utils.getInstance().hardwareMap.get(DcMotor.class, "frontBack");
    }
}
