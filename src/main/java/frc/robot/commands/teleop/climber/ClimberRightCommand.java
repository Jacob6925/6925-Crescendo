package frc.robot.commands.teleop.climber;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.climber.ClimberRight;

public class ClimberRightCommand extends Command {
    private final ClimberRight climberRight;
    private final double rightSpeed;

    public ClimberRightCommand(ClimberRight climberRight, double rightSpeed) {
        this.climberRight = climberRight;
        this.rightSpeed = rightSpeed;

        addRequirements(climberRight);
    }

    @Override
    public void initialize() {}

    @Override
    public void execute() {
        climberRight.setSpeed(rightSpeed);
    }

    @Override
    public void end(boolean interrupted) {
        climberRight.climberOff(); // turns both left and right off
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}