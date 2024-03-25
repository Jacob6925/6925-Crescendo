package frc.robot.subsystems.Shooter;

import com.ctre.phoenix6.signals.NeutralModeValue;

public class ShooterConstants {

    /* Shooter Neutral Mode */
    public static final NeutralModeValue SHOOTER_NEUTRAL_MODE = NeutralModeValue.Coast;

    /* Shooter Current Limits */
    public static final boolean SHOOTER_ENABLE_CURRENT_LIMIT = true;
    public static final int SHOOTER_SUPPLY_CURRENT_LIMIT = 40;
    public static final int SHOOTER_SUPPLY_CURRENT_THRESHOLD = 60;
    public static final double SHOOTER_SUPPLY_TIME_THRESHOLD = 0.1;
    
}
