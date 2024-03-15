package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;

public class ClimberSubsys extends SubsystemBase {
    private final TalonFX climberRight = new TalonFX(16);
    private final TalonFX climberLeft = new TalonFX(17);

    public ClimberSubsys() {
        climberLeft.getConfigurator().apply(Robot.ctreConfigs.climberConfig);
        climberRight.getConfigurator().apply(Robot.ctreConfigs.climberConfig);
    }

    public void setLeft(double speed) {
        climberLeft.set(speed);
    }
    
    public void setRight(double speed) {
        climberRight.set(speed);
    }
    
    public void climberOff(){
        climberLeft.set(0);
        climberRight.set(0);
    }
 
    @Override
    public void periodic(){}
}