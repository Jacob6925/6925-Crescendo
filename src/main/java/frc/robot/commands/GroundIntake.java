package frc.robot.commands;

// import edu.wpi.first.wpilibj.RobotState;
import edu.wpi.first.wpilibj2.command.Command;
// import frc.robot.subsystems.Intake.IntakeConstants;
import frc.robot.subsystems.Intake.IntakeSubsys;


public class GroundIntake extends Command{
    
    private IntakeSubsys m_intake;

    public GroundIntake(IntakeSubsys intake) {

        m_intake = intake;
        addRequirements(m_intake);

    }

    @Override 
    public void initialize() {

        // m_intake.setIndexerSpeed(0.15);
        // m_intake.intakePivot(m_intake, IntakeConstants.PivotState.GROUND);

    }

    @Override
    public boolean isFinished() {

        return true;

    }

}
