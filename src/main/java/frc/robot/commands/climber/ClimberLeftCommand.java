package frc.robot.commands.climber;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.climber.ClimberLeft;
import frc.robot.subsystems.climber.ClimberSubsysBase;

public class ClimberLeftCommand extends Command {
    private final ClimberLeft climberLeft;
    private final double leftSpeed;

    public ClimberLeftCommand(ClimberLeft climberLeft, double leftSpeed) {
        this.climberLeft = climberLeft;
        this.leftSpeed = leftSpeed;

        addRequirements(climberLeft);
    }

    @Override
    public void initialize() {}

    @Override
    public void execute() {
        climberLeft.setSpeed(leftSpeed);
    }

    @Override
    public void end(boolean interrupted) {
        ClimberSubsysBase.climberOff();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}