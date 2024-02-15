package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Intake.IntakeSubsys;
import frc.robot.subsystems.Intake.IndexerSubsys;

public class IntakeCommand extends Command {
    private final IntakeSubsys intake;
    private final IndexerSubsys indexer;
    Runnable runnable;

    public IntakeCommand(IntakeSubsys intake, IndexerSubsys indexer,Runnable runnable) {
        this.intake = intake;
        this.indexer = indexer;
        this.runnable = runnable;

        addRequirements(intake);
        addRequirements(indexer);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {}

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        runnable.run();
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        indexer.stopIntake();
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }


    /*
     * Custom Methods
     * Each method returns the instance of this class so that it can be coded to be called when a button is pressed
     */
    public Command goToGround() {
        intake.goToGround();
        return this;
    }

    public Command goToSource() {
        intake.goToSource();
        return this;
    }

    public Command goToAmp() {
        intake.goToAmp();
        return this;
    }

    public Command goToStow() {
        intake.goToStow();
        return this;
    }

    public Command intake() {
        indexer.intake();
        return this;
    }

    public Command eject() {
        indexer.eject();
        return this;
    }

    public Command pulse() {
        indexer.pulse();
        return this;
    }

    public Command feedShooter() {
        indexer.feedShooter();
        return this;
    }

    public Command stopIntake() {
        indexer.stopIntake();
        return this;
    }
}