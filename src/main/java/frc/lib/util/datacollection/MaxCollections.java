package frc.lib.util.datacollection;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

public class MaxCollections {
    private final Robot robotInstance;
    private final DoubleSupplier speedSupplier;
    private final DoubleSupplier accelerationSupplier;

    private boolean collectData = false;
    private boolean periodicInitiated = false;
    private double maxSpeed = 0;
    private double maxAccel = 0;

    public MaxCollections(Robot robotInstance, DoubleSupplier speedSupplier, DoubleSupplier accelerationSupplier) {
        this.robotInstance = robotInstance;
        this.speedSupplier = speedSupplier;
        this.accelerationSupplier = accelerationSupplier;

        SmartDashboard.putNumber("Max Speed", 0);
        SmartDashboard.putNumber("Max Acceleration", 0);
    }

    public void startCollections() {
        collectData = true;
        if (!periodicInitiated) {
            periodicInitiated = true;
            robotInstance.addPeriodic(() -> {
                if (collectData) {
                    if (speedSupplier != null) {
                        double currSpeed = speedSupplier.getAsDouble();
                        if (currSpeed > maxSpeed) {
                            maxSpeed = currSpeed;
                            SmartDashboard.putNumber("Max Speed", maxSpeed);
                        }
                    }
                    
                    if (accelerationSupplier != null) {
                        double currAccel = accelerationSupplier.getAsDouble();
                        if (currAccel > maxAccel) {
                            maxAccel = currAccel;
                            SmartDashboard.putNumber("Max Acceleration", maxAccel);
                        }
                    }
                }
            }, 1);
        }
    }

    public void stopCollection() {
        collectData = false;
    }
}
