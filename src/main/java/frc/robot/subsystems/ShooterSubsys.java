package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ShooterSubsys extends SubsystemBase {
    private final TalonFX shooterMotorTop = new TalonFX(12);
    private final TalonFX shooterMotorBottom = new TalonFX(13);

    private final double MAX_VALUE = 0.5;
    
    public ShooterSubsys(){
        shooterMotorBottom.setInverted(true);
    }

    public void setMotor(double top, double bottom){
        shooterMotorTop.set(top);
        shooterMotorBottom.set(bottom);
    }
    
    public void intake(){
        shooterMotorTop.set(MAX_VALUE);
        shooterMotorBottom.set(MAX_VALUE);
    }

    public void outake(){
        shooterMotorTop.set(-MAX_VALUE);
        shooterMotorBottom.set(-MAX_VALUE);
    }

    public void shooterOff(){
        shooterMotorTop.set(0);
        shooterMotorBottom.set(0);
    }

    @Override
    public void periodic(){
    }  
}
