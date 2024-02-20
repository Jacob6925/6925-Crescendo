package frc.robot.subsystems.Intake;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.DutyCycleOut;
import com.ctre.phoenix6.controls.PositionDutyCycle;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;
import frc.robot.subsystems.Intake.IntakeConstants.IndexerSpeed;
import frc.robot.subsystems.Intake.IntakeConstants.PivotState;

 public class IntakeSubsys extends SubsystemBase {

  //Device ID
  private static final DigitalInput intakeLimitSwitch = new DigitalInput(0);
  private static final TalonFX indexerMotor =  new TalonFX(14);
  private static final TalonFX pivotMotor = new TalonFX(15);

  //States
  private static IndexerSpeed indexerSpeed = IndexerSpeed.NONE;
  public static PivotState pivotState = PivotState.NONE;

  private PositionDutyCycle intakePivotPosition = new PositionDutyCycle(0);
  private DutyCycleOut intakePivotPercentOutput = new DutyCycleOut(0);
  

    /*============================
              Indexer
    ==============================*/
    public void setIndexerSpeed(IndexerSpeed speed) {
      indexerSpeed = speed;
      // TODO: set speed
    }

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
    
    /*============================
              Pivot
    ==============================*/
    public void setPivotState(PivotState state) {
      pivotState = state;
      // TODO: set motor to new state
    }

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

    public double getIntakePivotPosition() {
      return pivotMotor.getPosition().getValueAsDouble();
    }

    public double getIntakePivotRotorPosition() {
      return pivotMotor.getRotorPosition().getValueAsDouble();
    }

    public void goToGround() {
      pivotState = PivotState.GROUND;
    }

    public void goToSource() {
      pivotState = PivotState.SOURCE;
    }

    public void goToAmp() {
      pivotState = PivotState.AMP;
    }

    public void goToStow() {
      pivotState = PivotState.STOW;
    }

  @Override
  public void periodic() {
    if (indexerSpeed == IndexerSpeed.PULSE) {
      // Use the timer to pulse the intake on for a 1/16 second,
      // then off for a 15/16 second
      if (Timer.getFPGATimestamp() % 1.0 < (1.0 / 45.0)) {
        indexerSpeed = IndexerSpeed.PULSE;
      } else {
        indexerSpeed = IndexerSpeed.NONE;
      }

      SmartDashboard.putString("Intake State", indexerSpeed.toString());
      SmartDashboard.putNumber("Intake Pivot Position", getIntakePivotPosition());
      SmartDashboard.putNumber("Intake Pivot Rotor Position", getIntakePivotRotorPosition());
      SmartDashboard.putBoolean("Limit Switch", intakeHasNote());

    }
    if (pivotState == PivotState.GROUND && intakeHasNote()) {
       indexerSpeed = IndexerSpeed.PULSE;
       pivotState = PivotState.STOW; 
    }
  }

    public boolean intakeHasNote() {
     // NOTE: this is intentionally inverted, because the limit switch is normally closed
     return !intakeLimitSwitch.get();
    }

}
