package org.firstinspires.ftc.teamcode.teleops;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes.FiducialResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import java.util.List;
import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;

@TeleOp(name = "Limelight AprilTag Reader")
public class LimelightAprilTagReader extends LinearOpMode {

    private Limelight3A limelight;
    private static final int APRILTAG_PIPELINE = 0; // Match your saved AprilTag pipeline slot

    @Override
    public void runOpMode() {
        limelight = hardwareMap.get(Limelight3A.class, "limelight");
        limelight.pipelineSwitch(APRILTAG_PIPELINE);
        limelight.start();

        waitForStart();

        while (opModeIsActive()) {
            LLResult result = limelight.getLatestResult();

            if (result != null && result.isValid()) {
                List<FiducialResult> fiducials = result.getFiducialResults();

                if (fiducials.isEmpty()) {
                    telemetry.addLine("No AprilTag detected");
                }

                for (FiducialResult fiducial : fiducials) {
                    int tagId = fiducial.getFiducialId();      // AprilTag ID number
                    String family = fiducial.getFamily();      // e.g., "36h11"
                    double tx = fiducial.getTargetXDegrees();
                    double ty = fiducial.getTargetYDegrees();
                    double ta = fiducial.getTargetArea();

                    // 3D pose of the target relative to the camera (meters & degrees)
                    Pose3D targetPose = fiducial.getTargetPoseCameraSpace();

                    telemetry.addData("Tag ID", tagId);
                    telemetry.addData("Family", family);
                    telemetry.addData("Angles (tx, ty)", "%.2f, %.2f", tx, ty);
                    telemetry.addData("Area (ta)", "%.2f%%", ta);
                    telemetry.addData("Distance Z (m)", "%.2f", targetPose.getPosition().z);
                }
            } else {
                telemetry.addLine("No AprilTag detected");
            }

            telemetry.update();
        }

        limelight.stop();
    }
}