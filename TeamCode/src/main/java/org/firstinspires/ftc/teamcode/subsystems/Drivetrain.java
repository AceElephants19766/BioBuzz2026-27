package org.firstinspires.ftc.teamcode.subsystems;

import com.pedropathing.follower.Follower;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.seattlesolvers.solverslib.command.SubsystemBase;

import org.firstinspires.ftc.teamcode.Utils;

public class Drivetrain extends SubsystemBase {

    private static Drivetrain instance;

    public DcMotor frontLeft;
    public DcMotor frontRight;
    public DcMotor backLeft;
    public DcMotor backRight;

    public static Drivetrain getInstance() {
        if (instance == null) instance = new Drivetrain();
        return instance;
    }

    private Drivetrain() {
        frontLeft = Utils.getInstance().hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = Utils.getInstance().hardwareMap.get(DcMotor.class, "frontRight");
        backLeft = Utils.getInstance().hardwareMap.get(DcMotor.class, "backLeft");
        backRight = Utils.getInstance().hardwareMap.get(DcMotor.class, "backRight");


    }




}