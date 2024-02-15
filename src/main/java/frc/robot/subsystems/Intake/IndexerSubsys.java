package frc.robot.subsystems.Intake;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.subsystems.Intake.IndexerConstants.IndexerSpeed;

public class IndexerSubsys extends SubsystemBase{

    //Motor ID
    private static final TalonFX indexerMotor =  new TalonFX(14);

    private static IndexerSpeed indexerSpeed = IndexerSpeed.NONE;

    // Intake helper functions
    public void intake() {
    indexerSpeed = IndexerSpeed.INTAKE;
    indexerMotor.set(indexerSpeed.speed);
    }

    public void eject() {
    indexerSpeed = IndexerSpeed.EJECT;
    indexerMotor.set(indexerSpeed.speed);
    }

    public void pulse() {
    indexerSpeed = IndexerSpeed.PULSE;
    indexerMotor.set(indexerSpeed.speed);
    }

    public void feedShooter() {
    indexerSpeed = IndexerSpeed.FEED_SHOOTER;
    indexerMotor.set(indexerSpeed.speed);
    }

    public void amp() {
    indexerSpeed = IndexerSpeed.AMP;
    indexerMotor.set(indexerSpeed.speed);
    }

    public void stopIntake() {
    indexerSpeed = IndexerSpeed.NONE;
    indexerMotor.set(indexerSpeed.speed);
    }
    
  @Override
  public void periodic() {
    SmartDashboard.putString("Intake State", indexerSpeed.toString());
  }
}


