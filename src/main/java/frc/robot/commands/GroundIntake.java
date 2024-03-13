package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Intake.IntakeSubsys;
import frc.robot.subsystems.Intake.IntakeConstants.IndexerSpeed;
import frc.robot.subsystems.Intake.IntakeConstants.PivotState;


public class GroundIntake extends Command{
    
    private IntakeSubsys m_intake;

    public GroundIntake(IntakeSubsys intake) {

        m_intake = intake;
        addRequirements(m_intake);

    }

    @Override 
    public void initialize() {
        new TeleopIntake(m_intake, PivotState.GROUND, IndexerSpeed.INTAKE);
        m_intake.intakePivot(PivotState.GROUND.pivotSetpoint);
        m_intake.setIndexerSpeed(IndexerSpeed.INTAKE);

        // m_intake.setIndexerSpeed(0.15);
        // m_intake.intakePivot(m_intake, IntakeConstants.PivotState.GROUND);

    }

    @Override
    public boolean isFinished() {

        return true;

    }

}
