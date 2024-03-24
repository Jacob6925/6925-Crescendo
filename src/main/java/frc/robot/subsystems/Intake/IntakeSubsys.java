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
    public static IndexerSpeed indexerSpeed = IndexerSpeed.NONE;
    public static PivotState pivotState;

    // Motion Magic
    private final PositionDutyCycle intakePivotPosition = new PositionDutyCycle(0);

    public IntakeSubsys() {
        pivotMotor.getConfigurator().apply(Robot.ctreConfigs.intakePivotFXConfig);
        resetPivot();
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
    @Override
    public void periodic() {

        /*
         * i realized why this doesn't work (we're stupid)
         * it immediately sets the speed to NONE, then tests if it is PULSE next (which it's never pulse, because we just changed it)
         * - Jacob
         */

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

        SmartDashboard.putString("Intake State", indexerSpeed.name());
        SmartDashboard.putString("Pivot Position", pivotState.name());
        // SmartDashboard.putBoolean("Note in intake", intakeHasNote());
    }

    // public boolean intakeHasNote() {
    //     // NOTE: this is intentionally inverted, because the limit switch is normally closed
    //     return !intakeLimitSwitch.get();
    // }

}