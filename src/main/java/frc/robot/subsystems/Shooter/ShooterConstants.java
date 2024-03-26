package frc.robot.subsystems.Shooter;

import com.ctre.phoenix6.signals.NeutralModeValue;

public class ShooterConstants {

    /* Shooter Neutral Mode */
    public static final NeutralModeValue SHOOTER_NEUTRAL_MODE = NeutralModeValue.Coast;

    /* Shooter Current Limits */
    public static final boolean SHOOTER_ENABLE_CURRENT_LIMIT = true; // enable current limit
    public static final int SHOOTER_SUPPLY_CURRENT_THRESHOLD = 60; // once the motor reaches this ampage, the SUPPLY_TIME_THRESHOLD will start (0-511)
    public static final double SHOOTER_SUPPLY_TIME_THRESHOLD = 0.1; // time period that unlimited ampage can be drawn before limit applys (0-1.275)
    public static final int SHOOTER_SUPPLY_CURRENT_LIMIT = 40; // max current in Amps (0-800)
}
