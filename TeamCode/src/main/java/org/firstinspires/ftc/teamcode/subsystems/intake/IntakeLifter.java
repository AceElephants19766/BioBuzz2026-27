package org.firstinspires.ftc.teamcode.subsystems.intake;

import com.qualcomm.robotcore.hardware.Servo;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import org.firstinspires.ftc.teamcode.Utils;

public class IntakeLifter extends SubsystemBase {

    private static IntakeLifter instance;

    private final Servo intakeServo;

    public static final double IN_POSITION = 0.3;
    public static final double NOT_IN_POSITION = 0;

    public static IntakeLifter getInstance() {
        if (instance == null) instance = new IntakeLifter();
        return instance;
    }

    public IntakeLifter() {
        intakeServo = Utils.getInstance().hardwareMap.get(Servo.class, "intakeServo");
    }
    public void setPosition(double position) {
        intakeServo.setPosition(position);
    }
}


