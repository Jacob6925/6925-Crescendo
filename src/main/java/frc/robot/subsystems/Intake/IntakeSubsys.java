package frc.robot.subsystems.Intake;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.DutyCycleOut;
import com.ctre.phoenix6.controls.PositionDutyCycle;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;
import frc.robot.subsystems.Intake.IntakeConstants.IndexerSpeed;
import frc.robot.subsystems.Intake.IntakeConstants.PivotState;

 public class IntakeSubsys extends SubsystemBase {

  //Device ID
  private static final DigitalInput intakeLimitSwitch = new DigitalInput(9);
  private final TalonFX indexerMotor =  new TalonFX(14);
  private final TalonFX pivotMotor = new TalonFX(15);;

  //States
  private static IndexerSpeed indexerSpeed = IndexerSpeed.NONE;
  public static PivotState pivotState = PivotState.NONE;

  // Motion Magic
  private PositionDutyCycle intakePivotPosition = new PositionDutyCycle(0);
  private DutyCycleOut intakePivotPercentOutput = new DutyCycleOut(0);

  public IntakeSubsys() {
    configIntakePivotMotor();
  }
  

  /*============================
            Indexer
  ==============================*/
  public void setIndexerSpeed(IndexerSpeed speed) {
    indexerSpeed = speed;
    indexerMotor.set(indexerSpeed.speed);
  }
    
  /*============================
            Pivot
  ==============================*/
  public void intakePivot(double position) {
    intakePivotPosition.Position = position;
    pivotMotor.setControl(intakePivotPosition);
  }

  public void intakePivotPercentOutput(double percentOutput) {
    intakePivotPercentOutput.Output = percentOutput;
    pivotMotor.setControl(intakePivotPercentOutput);
  }

  public void resetIntakePivot() {
    pivotMotor.setPosition(0);
  }

  public void configIntakePivotMotor() {
    pivotMotor.getConfigurator().apply(new TalonFXConfiguration());
    pivotMotor.getConfigurator().apply(Robot.ctreConfigs.intakePivotFXConfig);
    resetIntakePivot();
  }

  public double getIntakePivotRotorPosition() {
    double motorRotations = pivotMotor.getRotorPosition().getValueAsDouble();
    return motorRotations;
  }


  @Override
  public void periodic() {
    // if (indexerSpeed == IndexerSpeed.PULSE) {
    //   // Use the timer to pulse the intake on for a 1/16 second,
    //   // then off for a 15/16 second
    //   if (Timer.getFPGATimestamp() % 1.0 < (1.0 / 45.0)) {
    //     indexerSpeed = IndexerSpeed.PULSE;
    //   } else {
    //     indexerSpeed = IndexerSpeed.NONE;
    //   }
    // }
    if (intakeHasNote() && indexerMotor.get() > 0) {
      setIndexerSpeed(IndexerSpeed.NONE);
      //  indexerSpeed = IndexerSpeed.PULSE;
      //  pivotState = PivotState.STOW; 
    }
    SmartDashboard.putString("Intake State", indexerSpeed.toString());
    SmartDashboard.putNumber("Pivot Position", getIntakePivotRotorPosition());
    //SmartDashboard.putBoolean("Note in intake", intakeHasNote());
  }

    public boolean intakeHasNote() {
     // NOTE: this is intentionally inverted, because the limit switch is normally closed
     return !intakeLimitSwitch.get();
    }

}
