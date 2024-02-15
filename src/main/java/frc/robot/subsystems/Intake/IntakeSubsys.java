// package frc.robot.subsystems.Intake;

// import com.ctre.phoenix6.hardware.TalonFX;

// import edu.wpi.first.math.controller.PIDController;
// import edu.wpi.first.math.util.Units;
// import edu.wpi.first.wpilibj.DigitalInput;
// import edu.wpi.first.wpilibj.Timer;
// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
// import edu.wpi.first.wpilibj2.command.SubsystemBase;
// import frc.lib.math.Conversions;

// import frc.robot.subsystems.Intake.IndexerSubsys;
// import frc.robot.subsystems.Intake.PivotSubsys;
// import frc.robot.subsystems.Intake.IndexerConstants.IndexerSpeed;
// import frc.robot.subsystems.Intake.PivotSubsys.PivotTarget;
// import frc.robot.subsystems.Intake.IndexerConstants;

// public class IntakeSubsys extends SubsystemBase {

//   private static final DigitalInput intakeLimitSwitch = new DigitalInput(0);

  
//   private static PivotTarget pivotTarget = PivotTarget.NONE;
//   private static IndexerSpeed intakeState = IndexerSpeed.NONE;
//   private static double intakePivotVoltage = 0;
//   private static double intakeSpeed = 0;

//   // Pivot helper functions
//   public void goToGround() {
//     pivotTarget = PivotTarget.GROUND;
//     intakeState = IndexerSpeed.INTAKE;
//   }

//   public void goToSource() {
//     pivotTarget = PivotTarget.SOURCE;
//     intakeState = IndexerSpeed.NONE;
//   }

//   public void goToAmp() {
//     pivotTarget = PivotTarget.SOURCE;
//     intakeState = IndexerSpeed.NONE;
//   }

//   public void goToStow() {
//     pivotTarget = PivotTarget.STOW;
//     intakeState = IndexerSpeed.NONE;
//   }


//   @Override
//   public void periodic() {
//     if (intakeState == IndexerSpeed.PULSE) {
//       // Use the timer to pulse the intake on for a 1/16 second,
//       // then off for a 15/16 second
//       if (Timer.getFPGATimestamp() % 1.0 < (1.0 / 45.0)) {
//         intakeSpeed = IndexerConstants.k_pulseSpeed;
//       } else {
//         intakeSpeed = 0;
//       }
//     }

//     if (pivotTarget == PivotTarget.GROUND && intakeHasNote() && isPivotAtTarget()) {
//       intakeState = IndexerSpeed.PULSE;
//       pivotTarget = PivotTarget.STOW; 
//     }

//     SmartDashboard.putBoolean("Limit Switch", intakeHasNote());
//   }

//   public boolean intakeHasNote() {
//     // NOTE: this is intentionally inverted, because the limit switch is normally closed
//     return !intakeLimitSwitch.get();
//   }

//   public boolean isPivotAtTarget() {
//     return Math.abs(getPivotAngleDegrees() - pivotTarget.getAngle()) < 5;
//   }

// }
