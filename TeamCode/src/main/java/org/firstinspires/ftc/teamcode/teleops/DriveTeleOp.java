//
//package org.firstinspires.ftc.teamcode.teleops;
//
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
//import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
//
//
//@TeleOp
//public class DriveTeleOp extends LinearOpMode {
//
//    @Override
//    public void runOpMode() throws InterruptedException {
//        // 1. Retrieve the global singleton instance of the drivetrain
//        Drivetrain drivetrain = Drivetrain.getInstance();
//
//        // 2. Initialize the motors using this run's hardwareMap
//        //drivetrain.init(hardwareMap);
//
//        telemetry.addData("Status", "Drivetrain Initialized. Ready to drive!");
//        telemetry.update();
//
//        // Wait for the driver to press PLAY
//        waitForStart();
//
//        // Run until the driver presses STOP
//        while (opModeIsActive()) {
//
//            // 3. Get gamepad inputs
//            // Note: Left stick Y is negated because pushing the stick up returns a negative value
//            double driveInput  = -gamepad1.left_stick_y;
//            double strafeInput =  gamepad1.left_stick_x;
//            double turnInput   =  gamepad1.right_stick_x;
//
//            // 4. Apply Deadzones (prevents robot from drifting if the controller sticks are loose)
//            if (Math.abs(driveInput)  < 0.05) driveInput  = 0.0;
//            if (Math.abs(strafeInput) < 0.05) strafeInput = 0.0;
//            if (Math.abs(turnInput)   < 0.05) turnInput   = 0.0;
//
//            // 5. Input Squaring (optional, improves precision at slower speeds)
//            // Math.signum preserves the negative/positive direction of the stick
//            driveInput  = Math.signum(driveInput)  * Math.pow(driveInput, 2);
//            strafeInput = Math.signum(strafeInput) * Math.pow(strafeInput, 2);
//            turnInput   = Math.signum(turnInput)   * Math.pow(turnInput, 2);
//
//            // 6. Pass the sanitized inputs directly to the singleton drivetrain
//            drivetrain.driveFieldOriented(driveInput, strafeInput, turnInput);
//
//            // 7. Telemetry output to monitor real-time power levels
//            telemetry.addData("Inputs", "Drive: %.2f | Strafe: %.2f | Turn: %.2f", driveInput, strafeInput, turnInput);
//            telemetry.addData("Motor Powers", "FL: %.2f | FR: %.2f | BL: %.2f | BR: %.2f",
//                    drivetrain.frontLeft.getPower(),
//                    drivetrain.frontRight.getPower(),
//                    drivetrain.backLeft.getPower(),
//                    drivetrain.backRight.getPower());
//            telemetry.update();
//        }
//
//        // 8. Safely cut power to the motors when the OpMode closes
//       // drivetrain.stop();
//    }
//}
package org.firstinspires.ftc.teamcode.teleops;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;

@TeleOp(name = "Drive TeleOp", group = "Competition")
public class DriveTeleOp extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

        // Initialize the drivetrain with this OpMode's hardware map.
        Drivetrain drivetrain = Drivetrain.getInstance(hardwareMap);

        telemetry.addLine("Drivetrain initialized.");
        telemetry.addLine("Waiting for START...");
        telemetry.update();

        try {
            waitForStart();

            if (isStopRequested()) {
                return;
            }

            while (opModeIsActive()) {

                // Read controller inputs.
                double forward = gamepad1.left_stick_y;
                double strafe = gamepad1.left_stick_x;
                double turn = gamepad1.right_stick_x;

                // Deadzones.
                if (Math.abs(forward) < 0.05) forward = 0.0;
                if (Math.abs(strafe) < 0.05) strafe = 0.0;
                if (Math.abs(turn) < 0.05) turn = 0.0;

                // Square inputs for finer control at low speeds.
                forward = Math.signum(forward) * forward * forward;
                strafe = Math.signum(strafe) * strafe * strafe;
                turn = Math.signum(turn) * turn * turn;

                // Optional slow mode: hold left bumper to reduce speed.
                double speed = gamepad1.left_bumper ? 0.35 : 1.0;

                forward *= speed;
                strafe *= speed;
                turn *= speed;

                // Reset heading by pressing Y.
                if (gamepad1.y) {
                    drivetrain.resetHeading();
                }

                // Method order is strafe, forward, turn.
                drivetrain.driveFieldOriented(
                        strafe, forward, turn);

                telemetry.addData(
                        "Heading (degrees)",
                        "%.1f",
                        Math.toDegrees(drivetrain.getHeading())
                );

                telemetry.addData("Forward", "%.2f", forward);
                telemetry.addData("Strafe", "%.2f", strafe);
                telemetry.addData("Turn", "%.2f", turn);

                telemetry.addData(
                        "Motor Powers",
                        "FL %.2f | FR %.2f | BL %.2f | BR %.2f",
                        drivetrain.frontLeft.getPower(),
                        drivetrain.frontRight.getPower(),
                        drivetrain.backLeft.getPower(),
                        drivetrain.backRight.getPower()
                );

                telemetry.update();
            }

        } finally {
            // Ensure the motors stop if the OpMode exits.
            drivetrain.stop();
        }
    }
}