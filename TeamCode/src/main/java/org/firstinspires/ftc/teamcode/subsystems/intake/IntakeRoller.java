package org.firstinspires.ftc.teamcode.subsystems.intake;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import org.firstinspires.ftc.teamcode.Utils;

public class IntakeRoller extends SubsystemBase {

    private static IntakeRoller instance;

    private final DcMotor intakeMotor;

    public static final double ACTIVE_ROLLER_POWER = 0.3;
    public static final double INACTIVE_ROLLER_POWER = 0;
    public static final double REVERSE_POWER = -0.3;

    public static IntakeRoller getInstance() {
        if (instance == null) instance = new IntakeRoller();
        return instance;
    }

    public IntakeRoller() {

        intakeMotor = Utils.getInstance().hardwareMap.get(DcMotor.class, "intakeMotor");
    }

    public void setPower(double power) {

        intakeMotor.setPower(power);
    }
}
