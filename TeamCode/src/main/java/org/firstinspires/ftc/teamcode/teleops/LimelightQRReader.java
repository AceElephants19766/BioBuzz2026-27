package org.firstinspires.ftc.teamcode.teleops;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes.BarcodeResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import java.util.List;

@TeleOp(name = "Limelight QR Reader")
public class LimelightQRReader extends LinearOpMode {

    private Limelight3A limelight;
    private static final int BARCODE_PIPELINE = 0; // Match your saved pipeline slot

    @Override
    public void runOpMode() {
        limelight = hardwareMap.get(Limelight3A.class, "limelight");
        limelight.pipelineSwitch(BARCODE_PIPELINE);
        limelight.start();

        waitForStart();

        while (opModeIsActive()) {
            LLResult result = limelight.getLatestResult();

            if (result != null && result.isValid()) {
                List<BarcodeResult> barcodes = result.getBarcodeResults();

                for (BarcodeResult barcode : barcodes) {
                    String qrText = barcode.getData();      // Decoded QR string
                    String family = barcode.getFamily();    // e.g., "QR_CODE"
                    double tx = barcode.getTargetXDegrees();
                    double ty = barcode.getTargetYDegrees();

                    telemetry.addData("QR Data", qrText);
                    telemetry.addData("Type", family);
                    telemetry.addData("Angles (tx, ty)", "%.2f, %.2f", tx, ty);
                }
            } else {
                telemetry.addLine("No QR code detected");
            }

            telemetry.update();
        }

        limelight.stop();
    }
}