package frc.robot.commands.teleop;

import frc.robot.subsystems.ClimberSubsys;
import edu.wpi.first.wpilibj2.command.Command;

public class ClimberCommand extends Command {
    private ClimberSubsys climber;
    private double leftSpeed;
    private double rightSpeed;

    public ClimberCommand(ClimberSubsys climber, double leftSpeed, double rightSpeed){
        this.climber = climber;
        this.leftSpeed = leftSpeed;
        this.rightSpeed = rightSpeed;
        addRequirements(climber);
    }

    @Override
    public void initialize(){}

    @Override
    public void execute(){
        climber.setLeft(leftSpeed);
        climber.setRight(rightSpeed);
    }

    @Override
    public void end(boolean interrupted){
        climber.climberOff();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}