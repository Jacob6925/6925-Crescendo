package frc.robot.subsystems.Intake;

import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

public class IntakeConstants {

    /*============================
            Indexer Constants
    ==============================*/

    public static final double INDEXER_INTAKE_SPEED = 0.4;
    public static final double INDEXER_EJECT_SPEED = -0.1;
    public static final double INDEXER_FEED_SHOOTER_SPEED = -0.7;
    public static final double INDEXER_PULSE_SPEED = 0.4;
    public static final double INDEXER_AMP_SPEED = -0.25;

    /* Intake Neutral Mode */
    public static final NeutralModeValue INTAKE_NEUTRAL_MODE = NeutralModeValue.Coast;
 
    /* Intake Current Limits */
    public static final boolean INTAKE_ENABLE_CURRENT_LIMIT = true; // enable current limit
    public static final int INTAKE_SUPPLY_CURRENT_THRESHOLD = 30; // once the motor reaches this ampage, the SUPPLY_TIME_THRESHOLD will start (0-511)
    public static final double INTAKE_SUPPLY_TIME_THRESHOLD = 0.1; // time period that unlimited ampage can be drawn before limit applys (0-1.275)
    public static final int INTAKE_SUPPLY_CURRENT_LIMIT = 20; // max current in Amps (0-800)
 
    public static final double INTAKING_NOTE_CURRENT = 43;

    public enum IndexerSpeed {
        NONE(0.0),
        INTAKE(INDEXER_INTAKE_SPEED),
        EJECT(INDEXER_EJECT_SPEED),
        PULSE(INDEXER_PULSE_SPEED),
        FEED_SHOOTER(INDEXER_FEED_SHOOTER_SPEED),
        AMP(INDEXER_AMP_SPEED);
     
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
    public static final double INTAKE_PIVOT_P = 0.1;
    public static final double INTAKE_PIVOT_I = 0;
    public static final double INTAKE_PIVOT_D = 0;
    public static final double INTAKE_PIVOT_V = 0.1;

    /* Intake Pivot Setpoints */
    public static final double INTAKE_PIVOT_GROUND = -41;
    public static final double INTAKE_PIVOT_SOURCE = 0;
    public static final double INTAKE_PIVOT_AMP = -17.5;
    public static final double INTAKE_PIVOT_STOW = -1;
    
    /* Intake Pivot Motion Magic */
    public static final double INTAKE_PIVOT_CRUISE_VELOCITY = 65;
    public static final double INTAKE_PIVOT_ACCELERATION = 40;

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
