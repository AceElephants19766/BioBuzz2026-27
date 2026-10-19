package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.hardware.bosch.BNO055IMUNew;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.geometry.Vector2d;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.Utils;

public class Drivetrain extends SubsystemBase {

    private static Drivetrain instance;
    private final BNO055IMUNew imu;
    private final DcMotor frontLeft;
    private final DcMotor frontRight;
    private final DcMotor backLeft;
    private final DcMotor backRight;

    public static Drivetrain getInstance() {
        if (instance == null) instance = new Drivetrain();
        return instance;
    }

    public Drivetrain() {
        frontLeft = Utils.getInstance().hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = Utils.getInstance().hardwareMap.get(DcMotor.class, "frontRight");
        backLeft = Utils.getInstance().hardwareMap.get(DcMotor.class, "backLeft");
        backRight = Utils.getInstance().hardwareMap.get(DcMotor.class, "backRight");

        imu = Utils.getInstance().hardwareMap.get(BNO055IMUNew.class, "imu");
        imu.initialize(
                new BNO055IMUNew.Parameters(
                        new RevHubOrientationOnRobot(
                                RevHubOrientationOnRobot.LogoFacingDirection.RIGHT,
                                RevHubOrientationOnRobot.UsbFacingDirection.DOWN
                        )
                )
        );
        imu.resetYaw();


        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backRight.setDirection(DcMotorSimple.Direction.FORWARD);
        frontRight.setDirection(DcMotorSimple.Direction.FORWARD);

    }

    public void resetIMU() {
        imu.resetYaw();
    }

    public void arcadeDrive(Gamepad gamepad1) {
        double x = -gamepad1.left_stick_x;
        double y = -gamepad1.left_stick_y;
        double zSpin = gamepad1.right_stick_x;

        arcadeDrive(x, y, zSpin);
    }

    public void fieldOrientedDrive(Gamepad gamepad1) {
        double x = -gamepad1.left_stick_x;
        double y = -gamepad1.left_stick_y;
        double zSpin = gamepad1.right_stick_x;

        Vector2d joystickVec = new Vector2d(x, y);
        Vector2d fieldOrientedVec = joystickVec.rotateBy(getAngleYaw());

        arcadeDrive(fieldOrientedVec.getX(), fieldOrientedVec.getY(), zSpin);
    }

    public void arcadeDrive(double x, double y, double zSpin) {

        double denominator = Math.max(Math.abs(x) + Math.abs(y) + Math.abs(zSpin), 1);

        frontLeft.setPower((y - x + zSpin) / denominator);
        frontRight.setPower((y + x - zSpin) / denominator);
        backRight.setPower((y - x - zSpin) / denominator);
        backLeft.setPower((y + x + zSpin) / denominator);

    }

    public double getAngleYaw() {
        return imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES);
    }

}
