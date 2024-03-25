package frc.robot;

import com.ctre.phoenix6.configs.CANcoderConfiguration;
import com.ctre.phoenix6.configs.CurrentLimitsConfigs;
import com.ctre.phoenix6.configs.MotionMagicConfigs;
import com.ctre.phoenix6.configs.MotorOutputConfigs;
import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.configs.SoftwareLimitSwitchConfigs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;

import frc.robot.subsystems.Intake.IntakeConstants;

public final class CTREConfigs {
    public final TalonFXConfiguration swerveAngleFXConfig = new TalonFXConfiguration();
    public final TalonFXConfiguration swerveDriveFXConfig = new TalonFXConfiguration();
    public final TalonFXConfiguration intakePivotFXConfig = new TalonFXConfiguration();
    public final CANcoderConfiguration swerveCANcoderConfig = new CANcoderConfiguration();
    public final SoftwareLimitSwitchConfigs climberConfig = new SoftwareLimitSwitchConfigs();

    public CTREConfigs() {
        /** Swerve CANCoder Configuration */
        swerveCANcoderConfig.MagnetSensor.SensorDirection = Constants.Swerve.cancoderInvert;

        /** Swerve Angle Motor Configurations */
        /* Motor Inverts and Neutral Mode */
        swerveAngleFXConfig.MotorOutput.Inverted = Constants.Swerve.angleMotorInvert;
        swerveAngleFXConfig.MotorOutput.NeutralMode = Constants.Swerve.angleNeutralMode;

        /* Gear Ratio and Wrapping Config */
        swerveAngleFXConfig.Feedback.SensorToMechanismRatio = Constants.Swerve.angleGearRatio;
        swerveAngleFXConfig.ClosedLoopGeneral.ContinuousWrap = true;
        
        /* Current Limiting */
        swerveAngleFXConfig.CurrentLimits.SupplyCurrentLimitEnable = Constants.Swerve.angleEnableCurrentLimit;
        swerveAngleFXConfig.CurrentLimits.SupplyCurrentLimit = Constants.Swerve.angleCurrentLimit;
        swerveAngleFXConfig.CurrentLimits.SupplyCurrentThreshold = Constants.Swerve.angleCurrentThreshold;
        swerveAngleFXConfig.CurrentLimits.SupplyTimeThreshold = Constants.Swerve.angleCurrentThresholdTime;

        /* PID Config */
        swerveAngleFXConfig.Slot0.kP = Constants.Swerve.angleKP;
        swerveAngleFXConfig.Slot0.kI = Constants.Swerve.angleKI;
        swerveAngleFXConfig.Slot0.kD = Constants.Swerve.angleKD;

        /** Swerve Drive Motor Configuration */
        /* Motor Inverts and Neutral Mode */
        swerveDriveFXConfig.MotorOutput.Inverted = Constants.Swerve.driveMotorInvert;
        swerveDriveFXConfig.MotorOutput.NeutralMode = Constants.Swerve.driveNeutralMode;

        /* Gear Ratio Config */
        swerveDriveFXConfig.Feedback.SensorToMechanismRatio = Constants.Swerve.driveGearRatio;

        /* Current Limiting */
        swerveDriveFXConfig.CurrentLimits.SupplyCurrentLimitEnable = Constants.Swerve.driveEnableCurrentLimit;
        swerveDriveFXConfig.CurrentLimits.SupplyCurrentLimit = Constants.Swerve.driveCurrentLimit;
        swerveDriveFXConfig.CurrentLimits.SupplyCurrentThreshold = Constants.Swerve.driveCurrentThreshold;
        swerveDriveFXConfig.CurrentLimits.SupplyTimeThreshold = Constants.Swerve.driveCurrentThresholdTime;

        /* PID Config */
        swerveDriveFXConfig.Slot0.kP = Constants.Swerve.driveKP;
        swerveDriveFXConfig.Slot0.kI = Constants.Swerve.driveKI;
        swerveDriveFXConfig.Slot0.kD = Constants.Swerve.driveKD;

        /* Open and Closed Loop Ramping */
        swerveDriveFXConfig.OpenLoopRamps.DutyCycleOpenLoopRampPeriod = Constants.Swerve.openLoopRamp;
        swerveDriveFXConfig.OpenLoopRamps.VoltageOpenLoopRampPeriod = Constants.Swerve.openLoopRamp;

        swerveDriveFXConfig.ClosedLoopRamps.DutyCycleClosedLoopRampPeriod = Constants.Swerve.closedLoopRamp;
        swerveDriveFXConfig.ClosedLoopRamps.VoltageClosedLoopRampPeriod = Constants.Swerve.closedLoopRamp;

        // Pivot Configs
        
        /* Intake Pivot Output and Neutral Mode */
        MotorOutputConfigs intakePivotOutput = intakePivotFXConfig.MotorOutput;
        intakePivotOutput.Inverted = IntakeConstants.INTAKE_PIVOT_INVERTED;
        intakePivotOutput.NeutralMode = IntakeConstants.INTAKE_PIVOT_NEUTRAL_MODE;
        
        /* Intake Pivot Current Limits */
        CurrentLimitsConfigs intakePivotCurrentLimits = intakePivotFXConfig.CurrentLimits;
        intakePivotCurrentLimits.SupplyCurrentLimitEnable = IntakeConstants.INTAKE_PIVOT_ENABLE_CURRENT_LIMIT;
        intakePivotCurrentLimits.SupplyCurrentLimit = IntakeConstants.INTAKE_PIVOT_SUPPLY_CURRENT_LIMIT;
        intakePivotCurrentLimits.SupplyCurrentThreshold = IntakeConstants.INTAKE_PIVOT_SUPPLY_CURRENT_THRESHOLD;
        intakePivotCurrentLimits.SupplyTimeThreshold = IntakeConstants.INTAKE_PIVOT_SUPPLY_TIME_THRESHOLD;
        
        /* Intake Pivot PID Config */
        Slot0Configs intakePivotSlot0 = intakePivotFXConfig.Slot0;
        intakePivotSlot0.kP = IntakeConstants.INTAKE_PIVOT_P;
        intakePivotSlot0.kI = IntakeConstants.INTAKE_PIVOT_I;
        intakePivotSlot0.kD = IntakeConstants.INTAKE_PIVOT_D;
        intakePivotSlot0.kV = IntakeConstants.INTAKE_PIVOT_V;
        
        /* Intake Pivot Feedback Config */
        // FeedbackConfigs intakePivotFeedback = intakePivotFXConfig.Feedback;
        // intakePivotFeedback.FeedbackRemoteSensorID = Constants.INTAKE_CANCODER_ID;
        // intakePivotFeedback.FeedbackSensorSource = FeedbackSensorSourceValue.SyncCANcoder;
        // intakePivotFeedback.SensorToMechanismRatio = 1;
        // intakePivotFeedback.RotorToSensorRatio = 25; 
        
        MotionMagicConfigs intakePivotMotionMagic = intakePivotFXConfig.MotionMagic;
        intakePivotMotionMagic.MotionMagicCruiseVelocity = IntakeConstants.INTAKE_PIVOT_CRUISE_VELOCITY;
        intakePivotMotionMagic.MotionMagicAcceleration = IntakeConstants.INTAKE_PIVOT_ACCELERATION;

        /* Climber Config */
        // climberConfig.ForwardSoftLimitEnable = true;
        // climberConfig.ForwardSoftLimitThreshold = 0;
    }
}
