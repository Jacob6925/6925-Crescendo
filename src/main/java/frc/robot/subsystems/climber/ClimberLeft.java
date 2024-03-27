package frc.robot.subsystems.climber;

import com.ctre.phoenix6.hardware.TalonFX;

import frc.robot.Robot;

public class ClimberLeft extends ClimberSubsysBase {
    private final TalonFX climber = new TalonFX(17);

    public ClimberLeft() {
        climber.getConfigurator().apply(Robot.ctreConfigs.climberConfig);
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