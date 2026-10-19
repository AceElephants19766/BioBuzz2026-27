package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.seattlesolvers.solverslib.command.SubsystemBase;

import org.firstinspires.ftc.teamcode.Utils;

public class Conveyor extends SubsystemBase {

    private final DcMotor firstConveyorMotor;
    private final DcMotor secondConveyorMotor;

    public Conveyor() {

        firstConveyorMotor = Utils.getInstance().hardwareMap.get(DcMotor.class, "firstConveyorMotor");
        secondConveyorMotor = Utils.getInstance().hardwareMap.get(DcMotor.class, "secondConveyorMotor");
    }

    public void setPower(double power) {
        firstConveyorMotor.setPower(power);
        secondConveyorMotor.setPower(power);
    }
}
