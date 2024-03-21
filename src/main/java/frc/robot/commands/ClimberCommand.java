package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.climber.ClimberLeft;
import frc.robot.subsystems.climber.ClimberRight;
import frc.robot.subsystems.climber.ClimberSubsysBase;

public class ClimberCommand extends Command {
    private final ClimberLeft climberLeft;
    private final ClimberRight climberRight;
    private final double leftSpeed;
    private final double rightSpeed;

    public ClimberCommand(ClimberLeft climberLeft, ClimberRight climberRight, double leftSpeed, double rightSpeed) {
        this.climberLeft = climberLeft;
        this.climberRight = climberRight;
        this.leftSpeed = leftSpeed;
        this.rightSpeed = rightSpeed;

        addRequirements(climberLeft, climberRight);
    }

    @Override
    public void initialize() {}

    @Override
    public void execute() {
        climberLeft.setSpeed(leftSpeed);
        climberRight.setSpeed(rightSpeed);
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