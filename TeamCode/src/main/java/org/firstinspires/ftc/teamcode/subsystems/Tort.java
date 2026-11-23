package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.Servo;
import com.seattlesolvers.solverslib.command.SubsystemBase;

import org.firstinspires.ftc.teamcode.Constantino;
import org.firstinspires.ftc.teamcode.Utils;

public class Tort extends SubsystemBase {

    private static Tort instance;

    private final Servo turretServo;
    private final double ANGLE_RANGE = 90;

    public static Tort getInstance() {
        if (instance == null) instance = new Tort();
        return instance;
    }


    public Tort() {
        turretServo = Utils.getInstance().hardwareMap.get(Servo.class, Constantino.TURRET_SERVO);
    }
    private  void setPosition(double position) {
        turretServo.setPosition(position);
    }

    public void setAngle(double angle) {
        setPosition(rangeInterp(angle/ANGLE_RANGE));
    }

    private double rangeInterp(double value) {
        return 0.5 * value + 0.5;
    }

}
