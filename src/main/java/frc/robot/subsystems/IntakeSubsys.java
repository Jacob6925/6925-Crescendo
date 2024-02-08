package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.lib.math.Conversions;

public class IntakeSubsys extends SubsystemBase {
  /*
   * Constants for this class
   * TODO: modify angles, intake speeds, and PID values
   */
  private static final TalonFX pivotMotor = new TalonFX(1);
  private static final TalonFX intakeMotor =  new TalonFX(2);
  private static final DigitalInput intakeLimitSwitch = new DigitalInput(0);

  // Pivot set point angles
  private static final double k_pivotAngleGround = 5;
  private static final double k_pivotAngleSource = 90;
  private static final double k_pivotAngleAmp = 90;
  private static final double k_pivotAngleStow = 27;
  
  // Intake speeds
  private static final double k_intakeSpeed = 0.7;
  private static final double k_ejectSpeed = -0.45;
  private static final double k_feedShooterSpeed = -0.5;
  private static final double k_pulseSpeed = k_intakeSpeed;

  private final double k_pivotMotorP = 0.12;
  private final double k_pivotMotorI = 0.0;
  private final double k_pivotMotorD = 0.001;

  private final PIDController m_pivotPID = new PIDController(k_pivotMotorP, k_pivotMotorI, k_pivotMotorD);

  /*
   * Start of code
   */
  
  private static PivotTarget pivotTarget = PivotTarget.NONE;
  private static IntakeState intakeState = IntakeState.NONE;
  private static double intakePivotVoltage = 0;
  private static double intakeSpeed = 0;

  private enum PivotTarget {
    NONE(-1),
    GROUND(k_pivotAngleGround),
    SOURCE(k_pivotAngleSource),
    AMP(k_pivotAngleAmp),
    STOW(k_pivotAngleStow);

    private PivotTarget(double angle) {
      this.angle = angle;
    }

    private final double angle;
    public double getAngle() {
      return angle;
    }
  }

  private enum IntakeState {
    NONE(0.0),
    INTAKE(k_intakeSpeed),
    EJECT(k_ejectSpeed),
    PULSE(k_pulseSpeed),
    FEED_SHOOTER(k_feedShooterSpeed);
 
    private IntakeState(double speed) {
      intakeSpeed = speed;
      intakeMotor.set(speed);

      this.speed = speed;
    }

    private final double speed;
    public double getSpeed() {
      return speed;
    }
  }

  // Pivot helper functions
  public void goToGround() {
    pivotTarget = PivotTarget.GROUND;
    intakeState = IntakeState.INTAKE;
  }

  public void goToSource() {
    pivotTarget = PivotTarget.SOURCE;
    intakeState = IntakeState.FEED_SHOOTER;
    // TODO: should this be the above or intakeState = IntakeState.NONE; ?
  }

  public void goToAmp() {
    pivotTarget = PivotTarget.SOURCE;
    intakeState = IntakeState.NONE;
  }

  public void goToStow() {
    pivotTarget = PivotTarget.STOW;
    intakeState = IntakeState.NONE;
  }

  // Intake helper functions
  public void intake() {
    intakeState = IntakeState.INTAKE;
  }

  public void eject() {
    intakeState = IntakeState.EJECT;
  }

  public void pulse() {
    intakeState = IntakeState.PULSE;
  }

  public void feedShooter() {
    intakeState = IntakeState.FEED_SHOOTER;
  }

  public void stopIntake() {
    intakeState = IntakeState.NONE;
  }







  
  @Override
  public void periodic() {
    if (intakeState == IntakeState.PULSE) {
      // Use the timer to pulse the intake on for a 1/16 second,
      // then off for a 15/16 second
      if (Timer.getFPGATimestamp() % 1.0 < (1.0 / 45.0)) {
        intakeSpeed = k_pulseSpeed;
      } else {
        intakeSpeed = 0;
      }
    }

    if (pivotTarget.getAngle() != -1.0) {
      // TODO: write code for setting motor to position based on angle
    }

    if (pivotTarget == PivotTarget.GROUND && intakeHasNote() && isPivotAtTarget()) {
      intakeState = IntakeState.PULSE;
      pivotTarget = PivotTarget.STOW; // TODO: is this the right position for when a note is detected, or should it be NONE
    }

    // TODO: implement PID
    // maybe code below? idk
    /*
    // Pivot control
    double pivotAngle = pivotTarget.getAngle();
    if (pivotAngle != -1.0) {
      intakePivotVoltage = m_pivotPID.calculate(getPivotAngleDegrees(), pivotAngle);
    }

    // If the pivot is at exactly 0.0, it's probably not connected, so disable it
    if (getPivotAngleDegrees() == 0.0) {
      intakePivotVoltage = 0.0;
    }
    */

    intakeSpeed = intakeState.getSpeed();
    outputTelemetry();

    pivotMotor.setVoltage(intakePivotVoltage);
    intakeMotor.set(intakeSpeed);
  }


  public double getPivotAngleDegrees() {
    double value = pivotMotor.getPosition().getValue();
    return Units.rotationsToDegrees(Conversions.modRotations(value));
  }

  public boolean intakeHasNote() {
    // NOTE: this is intentionally inverted, because the limit switch is normally closed
    return !intakeLimitSwitch.get();
  }

  /*---------------------------------- Custom Private Functions ---------------------------------*/
  private boolean isPivotAtTarget() {
    return Math.abs(getPivotAngleDegrees() - pivotTarget.getAngle()) < 5;
  }

  public void outputTelemetry() {
    SmartDashboard.putString("Intake State", intakeState.toString());

    SmartDashboard.putNumber("Speed", intakeSpeed);
    SmartDashboard.putNumber("Pivot/Abs Enc (getPivotAngleDegrees)", getPivotAngleDegrees());
    SmartDashboard.putNumber("Pivot/Setpoint", pivotTarget.getAngle());
    SmartDashboard.putNumber("Pivot/Power", intakePivotVoltage);
    SmartDashboard.putBoolean("Limit Switch", intakeHasNote());
  }
}
