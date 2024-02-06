package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter extends SubsystemBase {

    private final TalonFX shooterMotorTop = new TalonFX(12);
    private final TalonFX shooterMotorBottom = new TalonFX(13);
    
    public Shooter(){
        shooterMotorBottom.setInverted(true);
    }

    public void setMotor(double speed){
        shooterMotorTop.set(speed);
        shooterMotorBottom.set(speed);
    }
    
    public void intake(){
        shooterMotorTop.set(.5);
        shooterMotorBottom.set(.5);
    }

    public void outake(){
        shooterMotorTop.set(-0.5);
        shooterMotorBottom.set(-0.5);
    }

    public void shooterOff(){
        shooterMotorTop.set(0);
        shooterMotorBottom.set(0);
    }

    @Override
    public void periodic(){
    }  
}
