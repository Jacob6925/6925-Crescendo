package frc.robot.subsystems.climber;

import java.util.ArrayList;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public abstract class ClimberSubsysBase extends SubsystemBase {
    private static ArrayList<ClimberSubsysBase> climbers = new ArrayList<>();

    public ClimberSubsysBase() {
        climbers.add(this);
    }
    
    public static void climbersOff() {
        climbers.forEach(climber -> climber.motorOff());
    }

    @Override
    public void periodic() {}

    public abstract void setSpeed(double speed);
    public abstract void motorOff();
}