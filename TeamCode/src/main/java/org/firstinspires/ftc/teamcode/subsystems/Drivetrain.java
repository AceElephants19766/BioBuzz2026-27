////package org.firstinspires.ftc.teamcode.subsystems;
////
////import com.pedropathing.follower.Follower;
////import com.qualcomm.robotcore.hardware.DcMotor;
////import com.qualcomm.robotcore.hardware.DcMotorEx;
////import com.seattlesolvers.solverslib.command.SubsystemBase;
////
////import org.firstinspires.ftc.teamcode.Utils;
////
////public class Drivetrain extends SubsystemBase {
////
////    private static Drivetrain instance;
////
////    private DcMotor frontleft;
////    private DcMotor frontRight;
////    private DcMotor backLeft;
////    private DcMotor backRight;
////
////    public static Drivetrain getInstance() {
////        if (instance == null) instance = new Drivetrain();
////        return instance;
////    }
////
////    private Drivetrain() {
////        frontleft = Utils.getInstance().hardwareMap.get(DcMotor.class, "frontLeft");
////        frontRight = Utils.getInstance().hardwareMap.get(DcMotor.class, "frontRight");
////        backLeft = Utils.getInstance().hardwareMap.get(DcMotor.class, "backLeft");
////        backRight = Utils.getInstance().hardwareMap.get(DcMotor.class, "backRight");
////
////
////    }
////
////
////
////
////}
//package org.firstinspires.ftc.teamcode.subsystems;
//
//import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
//import com.qualcomm.robotcore.hardware.DcMotor;
//import com.qualcomm.robotcore.hardware.DcMotorSimple;
//import com.qualcomm.robotcore.hardware.IMU;
//import com.seattlesolvers.solverslib.command.SubsystemBase;
//import com.qualcomm.robotcore.hardware.HardwareMap;
//import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
//import org.firstinspires.ftc.teamcode.Utils;
//
//public class Drivetrain extends SubsystemBase {
//    private static Drivetrain instance;
//
//    // Clean, explicit motor variable names matching your config
//    public DcMotor frontLeft;
//    public DcMotor frontRight;
//    public DcMotor backLeft;
//    public DcMotor backRight;
//    public IMU imu;
//
//    public static Drivetrain getInstance() {
//        if (instance == null) {
//            instance = new Drivetrain();
//        }
//        return instance;
//    }
//
//    private Drivetrain() {
//        // Fetching your motors using your global Utils setup
//        frontLeft  = Utils.getInstance().hardwareMap.get(DcMotor.class, "frontLeft");
//        frontRight = Utils.getInstance().hardwareMap.get(DcMotor.class, "frontRight");
//        backLeft   = Utils.getInstance().hardwareMap.get(DcMotor.class, "backLeft");
//        backRight  = Utils.getInstance().hardwareMap.get(DcMotor.class, "backRight");
//
//        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
//        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
//
//        // Fetching the internal Hub gyroscope
//        imu = Utils.getInstance().hardwareMap.get(IMU.class, "imu");
//
//        // Set your orientation: Logo Facing UP, USB Facing BACKWARD
//        IMU.Parameters imuParameters = new IMU.Parameters(
//                new RevHubOrientationOnRobot(
//                        RevHubOrientationOnRobot.LogoFacingDirection.UP,
//                        RevHubOrientationOnRobot.UsbFacingDirection.BACKWARD
//                )
//        );
//        imu.initialize(imuParameters);
//
//        // Reverse the left motors so giving positive power moves the robot forward
//        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
//        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
//    }
//
//    public void resetHeading() {
//        imu.resetYaw();
//    }
//
//    public double getHeading() {
//        return imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);
//    }
//
//    public void driveFieldOriented(double strafe, double forward, double turn) {
//        double robotHeading = getHeading();
//
//        // Rotate the joystick values relative to the field angle
//        double rotatedStrafe  = strafe * Math.cos(-robotHeading) - forward * Math.sin(-robotHeading);
//        double rotatedForward = strafe * Math.sin(-robotHeading) + forward * Math.cos(-robotHeading);
//
//        // Boost strafe slightly to counter mechanical side-to-side friction
//        rotatedStrafe = rotatedStrafe * 1.1;
//
//        // Mecanum equations mapping directly to your named power variables
//        double frontLeftPower  = rotatedForward + rotatedStrafe + turn;
//        double frontRightPower = rotatedForward - rotatedStrafe - turn;
//        double backLeftPower   = rotatedForward - rotatedStrafe + turn;
//        double backRightPower  = rotatedForward + rotatedStrafe - turn;
//
//        // Scale power values safely down if any wheel calculation goes over 100% (1.0)
//        double max = Math.max(Math.abs(frontLeftPower), Math.abs(frontRightPower));
//        max = Math.max(max, Math.abs(backLeftPower));
//        max = Math.max(max, Math.abs(backRightPower));
//
//        if (max > 1.0) {
//            frontLeftPower  /= max;
//            frontRightPower /= max;
//            backLeftPower   /= max;
//            backRightPower  /= max;
//        }
//
//        // Apply calculations straight to your physical motors
//        frontLeft.setPower(frontLeftPower);
//        frontRight.setPower(frontRightPower);
//        backLeft.setPower(backLeftPower);
//        backRight.setPower(backRightPower);
//    }
//
//    public static void clearInstance() {
//        instance = null;
//    }
//}
package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.seattlesolvers.solverslib.command.SubsystemBase;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

public class Drivetrain extends SubsystemBase {

    private static Drivetrain instance;

    public DcMotor frontLeft;
    public DcMotor frontRight;
    public DcMotor backLeft;
    public DcMotor backRight;
    public IMU imu;

    // Return the existing drivetrain or initialize it with this OpMode's hardware.
    public static synchronized Drivetrain getInstance(HardwareMap hardwareMap) {
        if (instance == null) {
            instance = new Drivetrain(hardwareMap);
        }
        return instance;
    }

    private Drivetrain(HardwareMap hardwareMap) {

        // Retrieve motors using the names in the Robot Configuration.
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");

        // Reverse the left motors.
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        // Set motor behavior when power is zero.
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // Retrieve and initialize the Control Hub IMU.
        imu = hardwareMap.get(IMU.class, "imu");

        IMU.Parameters parameters = new IMU.Parameters(
                new RevHubOrientationOnRobot(
                        RevHubOrientationOnRobot.LogoFacingDirection.UP,
                        RevHubOrientationOnRobot.UsbFacingDirection.BACKWARD
                )
        );

        imu.initialize(parameters);
    }

    public void resetHeading() {
        imu.resetYaw();
    }

    public double getHeading() {
        return imu.getRobotYawPitchRollAngles()
                .getYaw(AngleUnit.RADIANS);
    }

    // Arguments: strafe, forward, turn.
    public void driveFieldOriented(
            double strafe, double forward, double turn) {

        double heading = getHeading();

        // Rotate joystick inputs using the robot heading.
        double rotatedStrafe =
                strafe * Math.cos(-heading)
                        - forward * Math.sin(-heading);

        double rotatedForward =
                strafe * Math.sin(-heading)
                        + forward * Math.cos(-heading);

        // Small compensation for sideways friction.
        rotatedStrafe *= 1.1;

        // Mecanum wheel calculations.
        double fl = rotatedForward + rotatedStrafe + turn;
        double fr = rotatedForward - rotatedStrafe - turn;
        double bl = rotatedForward - rotatedStrafe + turn;
        double br = rotatedForward + rotatedStrafe - turn;

        // Normalize all wheel powers to a maximum magnitude of 1.
        double max = Math.max(
                Math.max(Math.abs(fl), Math.abs(fr)),
                Math.max(Math.abs(bl), Math.abs(br))
        );

        if (max > 1.0) {
            fl /= max;
            fr /= max;
            bl /= max;
            br /= max;
        }

        frontLeft.setPower(fl);
        frontRight.setPower(fr);
        backLeft.setPower(bl);
        backRight.setPower(br);
    }

    public void stop() {
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
    }

    // Call only when no active command or OpMode is using this instance.
    public static synchronized void clearInstance() {
        if (instance != null) {
            instance.stop();
            instance = null;
        }
    }
}

