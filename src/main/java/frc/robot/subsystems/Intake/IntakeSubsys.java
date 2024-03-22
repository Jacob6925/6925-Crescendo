package frc.robot.subsystems.intake;

import com.ctre.phoenix6.controls.PositionDutyCycle;
import com.ctre.phoenix6.hardware.TalonFX;

//import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;
import frc.robot.subsystems.intake.IntakeConstants.IndexerSpeed;
import frc.robot.subsystems.intake.IntakeConstants.PivotState;

public class IntakeSubsys extends SubsystemBase {

    //Device ID
    //private static final DigitalInput intakeLimitSwitch = new DigitalInput(9);
    public final TalonFX indexerMotor =  new TalonFX(14);
    public final TalonFX pivotMotor = new TalonFX(15);

    //States
    public static IndexerSpeed indexerSpeed = IndexerSpeed.NONE;
    public static PivotState pivotState = PivotState.NONE;

    // Motion Magic
    private final PositionDutyCycle intakePivotPosition = new PositionDutyCycle(0);

    public IntakeSubsys() {
        pivotMotor.getConfigurator().apply(Robot.ctreConfigs.intakePivotFXConfig);
        resetIntakePivot();
    }
  

    /*============================
              Indexer
    ==============================*/
    public void setIndexerSpeed(IndexerSpeed speed) {
        indexerSpeed = speed;
        indexerMotor.set(indexerSpeed.speed);
    }
    
    /*============================
              Pivot
    ==============================*/
    public void setPivotState(PivotState position) {
        if (position != PivotState.NONE) {
            intakePivotPosition.Position = position.pivotSetpoint;
            pivotMotor.setControl(intakePivotPosition);
            pivotState = position;
        }
    }

    public void resetIntakePivot() {
        pivotMotor.setPosition(0);
    }

    public double getIntakePivotRotorPosition() {
        double motorRotations = pivotMotor.getRotorPosition().getValueAsDouble();
        return motorRotations;
    }

    public double getPosition() {
        return intakePivotPosition.Position;
    }
    


    /*============================
              Periodic
    ==============================*/
    @Override
    public void periodic() {
        // if (indexerSpeed == IndexerSpeed.PULSE) {
        //     // Use the timer to pulse the intake on for a 1/16 second,
        //     // then off for a 15/16 second
        //     if (Timer.getFPGATimestamp() % 1.0 < (1.0 / 45.0)) {
        //         indexerSpeed = IndexerSpeed.PULSE;
        //     } else {
        //         indexerSpeed = IndexerSpeed.NONE;
        //     }
        // }

        // if (intakeHasNote() && pivotState == PivotState.GROUND && indexerMotor.get() > 0.05) {
        //     Commands.runOnce(() -> {
        //         setIndexerSpeed(IndexerSpeed.NONE);
        //         setPivotState(PivotState.STOW);
        //     }, this);
        // }

        SmartDashboard.putString("Intake State", indexerSpeed.toString());
        SmartDashboard.putNumber("Pivot Position", getIntakePivotRotorPosition());
        // SmartDashboard.putBoolean("Note in intake", intakeHasNote());
    }

    // public boolean intakeHasNote() {
    //     // NOTE: this is intentionally inverted, because the limit switch is normally closed
    //     return !intakeLimitSwitch.get();
    // }

}