package frc.robot.commands.intake;

import javax.crypto.spec.PBEKeySpec;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.DigitalInput;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Intake.IntakeState;
import frc.robot.subsystems.Intake.PivotTarget;

public class IntakeData {
    private final PeriodicIO m_periodicIO; 

    public IntakeData() {
        this.m_periodicIO = new PeriodicIO();
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
        FEED_SHOOTER
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
}
