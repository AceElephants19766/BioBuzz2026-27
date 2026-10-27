package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.seattlesolvers.solverslib.command.SubsystemBase;

import org.firstinspires.ftc.teamcode.Utils;

public class Conveyor extends SubsystemBase {

    private static Conveyor instance;

    private final DcMotor firstConveyorMotor;
    private final DcMotor secondConveyorMotor;

    public static final double INACTIVE_CONVEYOR_POWER = 0;
    public static final double ACTIVE_CONVEYOR_POWER = 1;
    public static final double CONVEYOR_REVERSE_POWER = -1;

    public static Conveyor getInstance() {
        if (instance == null) instance = new Conveyor();
        return instance;
    }

    public Conveyor() {
        firstConveyorMotor = Utils.getInstance().hardwareMap.get(DcMotor.class, "firstConveyorMotor");
        secondConveyorMotor = Utils.getInstance().hardwareMap.get(DcMotor.class, "secondConveyorMotor");
    }

    public void setPower(double power) {
        firstConveyorMotor.setPower(power);
        secondConveyorMotor.setPower(power);
    }
}
