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
import com.pedropathing.revhub.localizers.TwoWheelLocalizer;
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
        c.xPodName.set(Constantino.BACK_RIGHT);
        c.yPodName.set(Constantino.FRONT_LEFT);
        c.imuName.set("imu");
        c.xPodOffset.set(-2.17);
        c.yPodOffset.set(2.76);
        c.forwardTicksToInches.set(1/505.253788);
        c.strafeTicksToInches.set(1/505.253788);
        c.xPodDirection.set(Encoder.REVERSE);
        c.yPodDirection.set(Encoder.REVERSE);
        c.imu.set(new RevHubIMU(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.LEFT,
                RevHubOrientationOnRobot.UsbFacingDirection.UP
        )));
    });


    public static ForesightConfig foresightConfig = new ForesightConfig(
            c -> {
                Controller primaryTranslationalForward = Controller.proportional(0.13919415293058152);
                Controller secondaryTranslationalForward = Controller.proportional(0.05142852017158357);
                Controller primaryTranslationalLateral = Controller.proportional(0.07421384085344859);
                Controller secondaryTranslationalLateral = Controller.proportional(0.027420031164999657);

                c.forwardTranslational.set(Controller.piecewise(secondaryTranslationalForward).put(2.5, primaryTranslationalForward));
                c.strafeTranslational.set(Controller.piecewise(secondaryTranslationalLateral).put(2.5, primaryTranslationalLateral));

                c.coast.set(Controller.proportionalFeedforward(0.0033952063977147917));
                c.brake.set(Controller.proportionalFeedforward(0.002885925438057573));

                c.headingFeedback.set(Controller.proportional(2.6999681974531025));
                c.headingBrakeCoefficients.set(Vector2D.cartesian(0.0488764158468133, -4.420759091318805E-4));

                c.linearBrakeCoefficients.set(Matrix.diag(0.05894286792518784, 0.0519664317062747));
                c.quadraticBrakeCoefficients.set(Matrix.diag(4.695689161177705E-4, 3.1066571713102785E-4));

                c.maxAchievableForwardVelocity.set(200.0177381765418);
                c.maxAchievableStrafeVelocity.set(196.39977974368475);
                c.naturalForwardDeceleration.set(94.76547732814778);
                c.naturalStrafeDeceleration.set(201.97264066970388);
            }
    );


    public static Follower createFollower(HardwareMap hardwareMap) {
        return new Follower(
                new TwoWheelLocalizer(hardwareMap, localizerConfig),
                new Mecanum(hardwareMap, drivetrainConfig),
                new Foresight(foresightConfig)
        );
    }
}
