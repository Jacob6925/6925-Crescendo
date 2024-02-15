package frc.robot.subsystems.Intake;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.subsystems.Intake.IndexerConstants.IndexerSpeed;
import frc.robot.subsystems.Intake.PivotSubsys.PivotState;

 public class IntakeSubsys extends SubsystemBase {

    private static final DigitalInput intakeLimitSwitch = new DigitalInput(0);
    public static PivotState pivotState = PivotState.NONE;
    private static IndexerSpeed indexerSpeed = IndexerSpeed.NONE;

  // Pivot helper functions
  public void goToGround() {
    pivotState = PivotState.GROUND;
    indexerSpeed = IndexerSpeed.INTAKE;
  }

  public void goToSource() {
    pivotState = PivotState.SOURCE;
    indexerSpeed = IndexerSpeed.NONE;
  }

  public void goToAmp() {
    pivotState = PivotState.SOURCE;
    indexerSpeed = IndexerSpeed.NONE;
  }

  public void goToStow() {
    pivotState = PivotState.STOW;
    indexerSpeed = IndexerSpeed.NONE;
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
    }

    if (pivotState == PivotState.GROUND && intakeHasNote()) {
      indexerSpeed = IndexerSpeed.PULSE;
      pivotState = PivotState.STOW; 
    }

    SmartDashboard.putBoolean("Limit Switch", intakeHasNote());
  }

  public boolean intakeHasNote() {
    // NOTE: this is intentionally inverted, because the limit switch is normally closed
    return !intakeLimitSwitch.get();
  }

}
