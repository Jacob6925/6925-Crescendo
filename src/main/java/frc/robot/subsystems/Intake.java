package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.Helpers;

public class Intake extends Subsystem {

    private final TalonFX pivotMotor = new TalonFX(1);
    private final TalonFX intakeMotor =  new TalonFX(2);
    private final DigitalInput m_IntakeLimitSwitch = new DigitalInput(0);

    // Pivot set point angles
    public static final double k_pivotAngleGround = 60;
    public static final double k_pivotAngleSource = 190;
    public static final double k_pivotAngleAmp = 190;
    public static final double k_pivotAngleStow = 27;
    
    // Intake speeds
    public static final double k_intakeSpeed = 0.7;
    public static final double k_ejectSpeed = -0.45;
    public static final double k_feedShooterSpeed = -0.5;

    private static final double k_pivotMotorP = 0.12;
    private static final double k_pivotMotorI = 0.0;
    private static final double k_pivotMotorD = 0.001;
  
    private final PIDController m_pivotPID = new PIDController(k_pivotMotorP, k_pivotMotorI, k_pivotMotorD);

/*-------------------------------- Private instance variables ---------------------------------*/
    private static Intake mInstance;
    private PeriodicIO m_periodicIO;

    public static Intake getInstance() {
      if (mInstance == null) {
        mInstance = new Intake();
      }
      return mInstance;
    }

    private Intake() {

    super("Intake"); 
    m_periodicIO = new PeriodicIO();
    
  }

    private static class PeriodicIO {
        // Input: Desired state
        PivotTarget pivot_target = PivotTarget.STOW;
        IntakeState intake_state = IntakeState.NONE;
    
        // Output: Motor set values
        double intake_pivot_voltage = 0.0;
        double intake_speed = 0.0;
      }
      public enum PivotTarget {
        NONE,
        GROUND,
        SOURCE,
        AMP,
        STOW
      }
    
      public enum IntakeState {
        NONE,
        INTAKE,
        EJECT,
        PULSE,
        FEED_SHOOTER,
      }

 /*-------------------------------- Generic Subsystem Functions --------------------------------*/

  @Override
  public void periodic() {
    checkAutoTasks();

    // Pivot control
    double pivot_angle = pivotTargetToAngle(m_periodicIO.pivot_target);
    m_periodicIO.intake_pivot_voltage = m_pivotPID.calculate(getPivotAngleDegrees(), pivot_angle);

    // If the pivot is at exactly 0.0, it's probably not connected, so disable it
    if (getPivotAngleDegrees() == 0.0) {
      m_periodicIO.intake_pivot_voltage = 0.0;
    }

    // Intake control
    m_periodicIO.intake_speed = intakeStateToSpeed(m_periodicIO.intake_state);
    putString("State", m_periodicIO.intake_state.toString());
  }

  @Override
  public void writePeriodicOutputs() {
    pivotMotor.setVoltage(m_periodicIO.intake_pivot_voltage);
    intakeMotor.set(m_periodicIO.intake_speed);
  }

  @Override
  public void stop() {
    m_periodicIO.intake_pivot_voltage = 0.0;
    m_periodicIO.intake_speed = 0.0;
  }

  @Override
  public void outputTelemetry() {
    SmartDashboard.putNumber("Speed", intakeStateToSpeed(m_periodicIO.intake_state));
    SmartDashboard.putNumber("Pivot/Abs Enc (getPivotAngleDegrees)", getPivotAngleDegrees());
    SmartDashboard.putNumber("Pivot/Setpoint", pivotTargetToAngle(m_periodicIO.pivot_target));
    SmartDashboard.putNumber("Pivot/Power", m_periodicIO.intake_pivot_voltage);
    SmartDashboard.putBoolean("Limit Switch", getIntakeHasNote());
  }

  @Override
  public void reset() {
  }

  public double pivotTargetToAngle(PivotTarget target) {
    switch (target) {
      case GROUND:
        return k_pivotAngleGround;
      case SOURCE:
        return k_pivotAngleSource;
      case AMP:
        return k_pivotAngleAmp;
      case STOW:
        return k_pivotAngleStow;
      default:
        // "Safe" default
        return 180;
    }
  }

  public double intakeStateToSpeed(IntakeState state) {
    switch (state) {
      case INTAKE:
        return k_intakeSpeed;
      case EJECT:
        return k_ejectSpeed;
      case PULSE:
        // Use the timer to pulse the intake on for a 1/16 second,
        // then off for a 15/16 second
        if (Timer.getFPGATimestamp() % 1.0 < (1.0 / 45.0)) {
          return k_intakeSpeed;
        }
        return 0.0;
      case FEED_SHOOTER:
        return k_feedShooterSpeed;
      default:
        // "Safe" default
        return 0.0;
    }
  }

  /*---------------------------------- Custom Public Functions ----------------------------------*/

  public IntakeState getIntakeState() {
    return m_periodicIO.intake_state;
  }

  public double getPivotAngleDegrees() {
    
    double value = pivotMotor.getPosition().getValue();
    return Units.rotationsToDegrees(Helpers.modRotations(value));
    
  }

  public boolean getIntakeHasNote() {
    // NOTE: this is intentionally inverted, because the limit switch is normally
    // closed
    return !m_IntakeLimitSwitch.get();
  }

  // Pivot helper functions
  public void goToGround() {
    m_periodicIO.pivot_target = PivotTarget.GROUND;
    m_periodicIO.intake_state = IntakeState.INTAKE;
  }

  public void goToSource() {
    m_periodicIO.pivot_target = PivotTarget.SOURCE;
    m_periodicIO.intake_state = IntakeState.NONE;
  }

  public void goToAmp() {
    m_periodicIO.pivot_target = PivotTarget.SOURCE;
    m_periodicIO.intake_state = IntakeState.NONE;
  }

  public void goToStow() {
    m_periodicIO.pivot_target = PivotTarget.STOW;
    m_periodicIO.intake_state = IntakeState.NONE;
  }

  // Intake helper functions
  public void intake() {
    m_periodicIO.intake_state = IntakeState.INTAKE;
  }

  public void eject() {
    m_periodicIO.intake_state = IntakeState.EJECT;
  }

  public void pulse() {
    m_periodicIO.intake_state = IntakeState.PULSE;
  }

  public void feedShooter() {
    m_periodicIO.intake_state = IntakeState.FEED_SHOOTER;
  }

  public void stopIntake() {
    m_periodicIO.intake_state = IntakeState.NONE;
    m_periodicIO.intake_speed = 0.0;
  }

  public void setState(IntakeState state) {
    m_periodicIO.intake_state = state;
  }

  public void setPivotTarget(PivotTarget target) {
    m_periodicIO.pivot_target = target;
  }

  /*---------------------------------- Custom Private Functions ---------------------------------*/
  private void checkAutoTasks() {
    // If the intake is set to GROUND, and the intake has a note, and the pivot is
    // close to it's target
    // Stop the intake and go to the SOURCE position
    if (m_periodicIO.pivot_target == PivotTarget.GROUND && getIntakeHasNote() && isPivotAtTarget()) {
      m_periodicIO.pivot_target = PivotTarget.STOW;
      m_periodicIO.intake_state = IntakeState.NONE;
    }
  }

  private boolean isPivotAtTarget() {
    return Math.abs(getPivotAngleDegrees() - pivotTargetToAngle(m_periodicIO.pivot_target)) < 5;
  }
}
