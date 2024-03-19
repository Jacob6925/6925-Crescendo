package frc.robot.commands.teleop;

import frc.robot.subsystems.ClimberSubsys;
import edu.wpi.first.wpilibj2.command.Command;

public class ClimberCommand extends Command {
    private final ClimberSubsys climber;
    private final double leftSpeed;
    private final double rightSpeed;

    public ClimberCommand(ClimberSubsys climber, double leftSpeed, double rightSpeed) {
        this.climber = climber;
        this.leftSpeed = leftSpeed*-1;
        this.rightSpeed = rightSpeed*-1;

        addRequirements(climber);
    }

    @Override
    public void initialize() {}

    @Override
    public void execute() {
        climber.setLeft(leftSpeed);
        climber.setRight(rightSpeed);
    }

    @Override
    public void end(boolean interrupted) {
        climber.climberOff();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}