package frc.robot.commands;

import frc.robot.subsystems.Climber;
import edu.wpi.first.wpilibj2.command.Command;

public class ClimberCommand extends Command{
    private Climber climber;
    private double speed;

    public ClimberCommand(Climber c_climber, double speed){
        climber = c_climber;
        this.speed = speed;
        addRequirements(climber);
    }

    @Override
    public void initialize(){
    }

    @Override
    public void execute(){
        climber.setMotor(speed);
    }

    @Override
    public void end(boolean interrupted){
        climber.climberOff();
    }

     @Override
    public boolean isFinished(){
        return false;
    }
}

