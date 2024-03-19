package frc.robot.commands.teleop;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Intake.IntakeSubsys;
import frc.robot.subsystems.Intake.IntakeConstants.IndexerSpeed;
import frc.robot.subsystems.Intake.IntakeConstants.PivotState;

public class IntakeCommand extends Command {
    private final IntakeSubsys intake;
    private final PivotState state;
    private final IndexerSpeed speed;

    public IntakeCommand(IntakeSubsys intake, PivotState state, IndexerSpeed speed) {
        this.intake = intake;
        this.state = state;
        this.speed = speed;
        
        addRequirements(intake);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {}

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        if (state != null) intake.setPivotState(state);
        if (speed != null) intake.setIndexerSpeed(speed);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        intake.setIndexerSpeed(IndexerSpeed.NONE);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;        
    }
}