package frc.robot.subsystems.Intake;

import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

public class PivotConstants {

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
    public static final double INTAKE_PIVOT_V = 0; //0.01

    /* Intake Pivot Setpoints */
    public static final double k_pivotAngleGround = 5;
    public static final double k_pivotAngleSource = 90;
    public static final double k_pivotAngleAmp = 90;
    public static final double k_pivotAngleStow = 27;
    
    /* Intake Pivot Motion Magic */
    public static final double INTAKE_PIVOT_CRUISE_VELOCITY = 95;
    public static final double INTAKE_PIVOT_ACCELERATION = 140;
    //public static final double INTAKE_PIVOT_JERK = 100;
}
