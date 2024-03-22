package frc.robot.subsystems.climber;

import com.ctre.phoenix6.hardware.TalonFX;

import frc.robot.Robot;

public class ClimberRight extends ClimberSubsysBase {
    private final TalonFX climber = new TalonFX(16);

    public ClimberRight() {
        climber.getConfigurator().apply(Robot.ctreConfigs.climberConfig);
        climber.getConfigurator().apply(Robot.ctreConfigs.climbeFxConfiguration);
        climber.setInverted(true);
    }

    @Override
    public void setSpeed(double speed) {
        climber.set(speed);
    }

    @Override
    public void motorOff() {
        climber.set(0);
    }
}