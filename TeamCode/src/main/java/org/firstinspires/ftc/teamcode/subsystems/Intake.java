package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.seattlesolvers.solverslib.command.SubsystemBase;

import org.firstinspires.ftc.teamcode.Utils;

public class Intake extends SubsystemBase {

    private static Intake instance;

    private final DcMotor intakeMotor;
    private final Servo intakeServo;

    public static Intake getInstance() {
        if (instance == null) instance = new Intake();
        return instance;
    }

    public Intake() {

        intakeMotor = Utils.getInstance().hardwareMap.get(DcMotor.class, "intakeMotor");
        intakeServo = Utils.getInstance().hardwareMap.get(Servo.class, "intakeServo");
    }

    public void setPower(double power) {
        intakeMotor.setPower(power);
    }
    public void setPosition(double position) {
        intakeServo.setPosition(position);
    }
}
