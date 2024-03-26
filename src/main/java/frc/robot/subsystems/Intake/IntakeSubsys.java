package frc.robot.subsystems.Intake;

import com.ctre.phoenix6.controls.PositionDutyCycle;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;
import frc.robot.subsystems.Intake.IntakeConstants.IndexerSpeed;
import frc.robot.subsystems.Intake.IntakeConstants.PivotState;

public class IntakeSubsys extends SubsystemBase {

    // Device ID
    // private static final DigitalInput intakeLimitSwitch = new DigitalInput(9);
    public final TalonFX indexerMotor =  new TalonFX(14);
    public final TalonFX pivotMotor = new TalonFX(15);

    // States
    private IndexerSpeed indexerSpeed = IndexerSpeed.NONE;
    private PivotState pivotState;

    // Motion Magic
    private final PositionDutyCycle intakePivotPosition = new PositionDutyCycle(0);

    public IntakeSubsys() {
        pivotMotor.getConfigurator().apply(Robot.ctreConfigs.intakePivotFXConfig);
        indexerMotor.getConfigurator().apply(Robot.ctreConfigs.intakeIndexerFXConfig);
        resetPivot();
    }
  

    /*============================
              Indexer
    ==============================*/
    public void setIndexerSpeed(IndexerSpeed speed) {
        setIndexerSpeed(speed, true);
    }


    private void setIndexerSpeed(IndexerSpeed speed, boolean updateSpeed) {
        if (updateSpeed) indexerSpeed = speed;
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

    public void resetPivot() {
        pivotMotor.setPosition(0);
        pivotState = PivotState.NONE;
    }

    // TODO: remove?
    public double getIntakePivotRotorPosition() {
        return pivotMotor.getRotorPosition().getValueAsDouble();
    }
    
    /*============================
              Periodic
    ==============================*/
    private int pulseCount = 0;

    // Called every ~20ms
    @Override
    public void periodic() {
        // Possible issue: if the indexer is spinning out because of pulse then gets changed to FEED_SHOOTER, the note may not make it
        // possible solution: add a lastSpeed var
        if (indexerSpeed == IndexerSpeed.PULSE) {
            // Use the count to turn indexer off for 10 periodic iterations (about 200ms / 0.2s)
            // then intake for for 40 periodic iterations (800ms = 0.8s)
            // mod 50 because 1 period of cycling is 50 iterations
            if (pulseCount % 50 >= 0 && pulseCount % 50 <= 10) {
                setIndexerSpeed(IndexerSpeed.NONE, false);
            } else {
                setIndexerSpeed(IndexerSpeed.INTAKE, false);
            }
            pulseCount++;
        }

        // if (intakeHasNote() && pivotState == PivotState.GROUND && indexerMotor.get() > 0.05) {
        //     Commands.runOnce(() -> {
        //         setIndexerSpeed(IndexerSpeed.NONE);
        //         setPivotState(PivotState.STOW);
        //     }, this);
        // }

        SmartDashboard.putString("Intake State", indexerSpeed.name());
        SmartDashboard.putString("Pivot Position", pivotState.name());
        // SmartDashboard.putBoolean("Note in intake", intakeHasNote());
    }

    // public boolean intakeHasNote() {
    //     // NOTE: this is intentionally inverted, because the limit switch is normally closed
    //     return !intakeLimitSwitch.get();
    // }

}