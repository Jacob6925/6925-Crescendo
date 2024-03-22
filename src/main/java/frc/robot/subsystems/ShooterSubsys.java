package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ShooterSubsys extends SubsystemBase {
    public final TalonFX shooterMotorTop = new TalonFX(12);
    public final TalonFX shooterMotorBottom = new TalonFX(13);

    private double topVelocity = 0;
    private double bottomVelocity = 0;
    private final double VELOCITY_OFFSET = 0.05;

    private final double MAX_VALUE = 0.5;
    
    public ShooterSubsys() {
        shooterMotorBottom.setInverted(true);
    }

    public void setMotor(double top, double bottom) {
        shooterMotorTop.set(top);
        shooterMotorBottom.set(bottom);

        topVelocity = top;
        bottomVelocity = bottom;
    }
    
    public void intake() {
        shooterMotorTop.set(MAX_VALUE);
        shooterMotorBottom.set(MAX_VALUE);

        topVelocity = MAX_VALUE;
        bottomVelocity = MAX_VALUE;
    }

    public void outake() {
        shooterMotorTop.set(-MAX_VALUE);
        shooterMotorBottom.set(-MAX_VALUE);

        topVelocity = -MAX_VALUE;
        bottomVelocity = -MAX_VALUE;
    }

    public void shooterOff() {
        shooterMotorTop.set(0);
        shooterMotorBottom.set(0);
        
        topVelocity = 0;
        bottomVelocity = 0;
    }

    private boolean getTopShooterState() {
        return shooterMotorTop.get() + VELOCITY_OFFSET >= topVelocity;
    }

    private boolean getBottomShooterState() {
        return shooterMotorBottom.get() + VELOCITY_OFFSET >= bottomVelocity;
    }

    @Override
    public void periodic() {
        SmartDashboard.putBoolean("Top Shooter", getTopShooterState());
        SmartDashboard.putBoolean("Bottom Shooter", getBottomShooterState());
    }  
}