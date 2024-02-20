package frc.robot.subsystems.Intake;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.subsystems.Intake.IndexerConstants.IndexerSpeed;

 public class IntakeSubsys extends SubsystemBase {

  //private static final DigitalInput intakeLimitSwitch = new DigitalInput(0);
  private static IndexerSpeed indexerSpeed = IndexerSpeed.NONE;

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

    // if (pivotState == PivotState.GROUND && intakeHasNote()) {
    //   indexerSpeed = IndexerSpeed.PULSE;
    //   pivotState = PivotState.STOW; 
    // }

    //SmartDashboard.putBoolean("Limit Switch", intakeHasNote());
  }

  // public boolean intakeHasNote() {
  //   // NOTE: this is intentionally inverted, because the limit switch is normally closed
  //   return !intakeLimitSwitch.get();
  // }

}
