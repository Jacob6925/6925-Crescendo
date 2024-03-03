package frc.robot.subsystems.Intake;

import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

public class IntakeConstants {

    /*============================
            Indexer Constants
    ==============================*/

    public static final double k_intakeSpeed = 0.4;
    public static final double k_ejectSpeed = -0.1;
    public static final double k_feedShooterSpeed = -0.7;
    public static final double k_pulseSpeed = 0.1;
    public static final double k_ampSpeed = 0;

    public static final int indexerCurrentLimit = 30;

    public enum IndexerSpeed {
        NONE(0.0),
        INTAKE(k_intakeSpeed),
        EJECT(k_ejectSpeed),
        PULSE(k_pulseSpeed),
        FEED_SHOOTER(k_feedShooterSpeed),
        AMP(k_ampSpeed);
     
        public final double speed;
        IndexerSpeed(double speed) {
          this.speed = speed;
        }
    }

    /*============================
            Pivot Constants
    ==============================*/

    /* Intake Inverts and Neutral Mode */
    public static final InvertedValue INTAKE_PIVOT_INVERTED = InvertedValue.CounterClockwise_Positive;
    public static final NeutralModeValue INTAKE_PIVOT_NEUTRAL_MODE = NeutralModeValue.Brake;

    /* Intake Pivot Current Limits */
    public static final boolean INTAKE_PIVOT_ENABLE_CURRENT_LIMIT = true;
    public static final int INTAKE_PIVOT_SUPPLY_CURRENT_LIMIT = 30;
    public static final int INTAKE_PIVOT_SUPPLY_CURRENT_THRESHOLD = 30;
    public static final double INTAKE_PIVOT_SUPPLY_TIME_THRESHOLD = 0.1;

    /* Intake Pivot PID Constants */
    public static final double INTAKE_PIVOT_P = 0.1; //0.1
    public static final double INTAKE_PIVOT_I = 0;
    public static final double INTAKE_PIVOT_D = 0;
    public static final double INTAKE_PIVOT_V = 0.1; //0.01

    /* Intake Pivot Setpoints */
    public static final double INTAKE_PIVOT_GROUND = -40.05957; //TODO: Change these values
    public static final double INTAKE_PIVOT_SOURCE = 0;
    public static final double INTAKE_PIVOT_AMP = 0;
    public static final double INTAKE_PIVOT_STOW = -1.0;
    
    /* Intake Pivot Motion Magic */
    public static final double INTAKE_PIVOT_CRUISE_VELOCITY = 95;
    public static final double INTAKE_PIVOT_ACCELERATION = 140;
    //public static final double INTAKE_PIVOT_JERK = 100;

    public enum PivotState {

        NONE(Double.MAX_VALUE),
        GROUND(INTAKE_PIVOT_GROUND),
        SOURCE(INTAKE_PIVOT_SOURCE),
        AMP(INTAKE_PIVOT_AMP),
        STOW(INTAKE_PIVOT_STOW);

        public double pivotSetpoint;
        private PivotState(double pivot) {
            pivotSetpoint = pivot;
        }

    }

}
