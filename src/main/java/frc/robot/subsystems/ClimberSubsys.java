package frc.robot.subsystems;

//import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ClimberSubsys extends SubsystemBase {

    private final TalonFX climberRight = new TalonFX(14);
    private final TalonFX climberLeft = new TalonFX(15);

    public ClimberSubsys(){

    }
 
    public void setMotor(double speed){
        climberLeft.set(speed);
        climberRight.set(speed);
    }
    
    public void climberOff(){
        climberLeft.set(0);
        climberRight.set(0);
    }

    
    @Override
    public void periodic(){
    }

}