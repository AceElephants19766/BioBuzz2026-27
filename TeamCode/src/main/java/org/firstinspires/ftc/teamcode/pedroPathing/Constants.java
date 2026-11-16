package org.firstinspires.ftc.teamcode.pedroPathing;

import com.pedropathing.algorithm.Foresight;
import com.pedropathing.algorithm.ForesightConfig;
import com.pedropathing.controllers.Controller;
import com.pedropathing.follower.Follower;
import com.pedropathing.math.Matrix;
import com.pedropathing.math.Vector2D;
import com.pedropathing.revhub.drivetrains.Mecanum;
import com.pedropathing.revhub.drivetrains.MecanumConfig;
import com.pedropathing.revhub.localizers.Encoder;
import com.pedropathing.revhub.localizers.PinpointConfig;
import com.pedropathing.revhub.localizers.PinpointLocalizer;
import com.pedropathing.revhub.localizers.RevHubIMU;
import com.pedropathing.revhub.localizers.TwoWheelConfig;
import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.Constantino;

/**
 * Pedro Pathing 3.0 constants. The values below are placeholders: run the AutoTune procedures in
 * {@link Tuning} (see https://pedropathing.com/docs/pathing/tuning) and paste the generated configs here.
 * If you use a different drivetrain or localizer, swap the config classes and {@link #createFollower} to match.
 */
public class Constants {
    public static MecanumConfig drivetrainConfig = new MecanumConfig(c -> {
        c.frontLeftName.set(Constantino.FRONT_LEFT);
        c.frontRightName.set(Constantino.FRONT_RIGHT);
        c.backLeftName.set(Constantino.BACK_LEFT);
        c.backRightName.set(Constantino.BACK_RIGHT);
        c.frontLeftDirection.set(DcMotorSimple.Direction.FORWARD);
        c.frontRightDirection.set(DcMotorSimple.Direction.REVERSE);
        c.backLeftDirection.set(DcMotorSimple.Direction.REVERSE);
        c.backRightDirection.set(DcMotorSimple.Direction.FORWARD);
    });

    public static TwoWheelConfig localizerConfig = new TwoWheelConfig(c -> {
        c.xPodName.set("FRONT_RIGHT");
        c.yPodName.set("FRONT_LEFT");
        c.imuName.set("imu");
        c.xPodOffset.set(-4.603737435346751);
        c.yPodOffset.set(9.180124232182132);
        c.forwardTicksToInches.set(0.001979);
        c.strafeTicksToInches.set(0.001979);
        c.xPodDirection.set(Encoder.REVERSE);
        c.yPodDirection.set(Encoder.FORWARD);
        c.imu.set(new RevHubIMU(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.LEFT,
                RevHubOrientationOnRobot.UsbFacingDirection.UP
        )));
    });

    public static ForesightConfig foresightConfig = new ForesightConfig(
            c -> {
                Controller primaryTranslationalForward = Controller.proportional(0.20659891784597315);
                Controller secondaryTranslationalForward = Controller.proportional(0.07633277971933113);
                Controller primaryTranslationalLateral = Controller.proportional(0.6057912835254579);
                Controller secondaryTranslationalLateral = Controller.proportional(0.22382369222143988);
                c.forwardTranslational.set(Controller.piecewise(secondaryTranslationalForward).put(2.5, primaryTranslationalForward));
                c.strafeTranslational.set(Controller.piecewise(secondaryTranslationalLateral).put(2.5, primaryTranslationalLateral));
                c.coast.set(Controller.proportionalFeedforward(0.018199649266204636));
                c.brake.set(Controller.proportionalFeedforward(0.015469701876273941));
                c.headingFeedback.set(Controller.proportional(1.240224265098136));
                c.headingBrakeCoefficients.set(Vector2D.cartesian(0.05216472128629321, -0.0048529560213991995));
                c.linearBrakeCoefficients.set(Matrix.diag(0.0523451728703431, 0.06630649472529934));
                c.quadraticBrakeCoefficients.set(Matrix.diag(0.0023320515151911653, 0.0015979369828422727));
                c.maxAchievableForwardVelocity.set(53.44952835304266);
                c.maxAchievableStrafeVelocity.set(38.57170873794343);
                c.naturalForwardDeceleration.set(40.60016254106493);
                c.naturalStrafeDeceleration.set(54.303945579683024);
            }
    );
    public static Follower createFollower(HardwareMap hardwareMap) {
        return new Follower(
                null,
                new Mecanum(hardwareMap, drivetrainConfig),
                null
        );
    }
}
