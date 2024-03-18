package frc.robot.commands.autonomous;

import frc.robot.commands.teleop.ClimberCommand;
import frc.robot.subsystems.ClimberSubsys;

public class ClimberCommandAuto extends ClimberCommand {
    private ClimberSubsys climber;
    private double leftSpeed;
    private double rightSpeed;

    public ClimberCommandAuto(ClimberSubsys climber, double leftSpeed, double rightSpeed){
        super(climber, leftSpeed, rightSpeed);

        this.climber = climber;
        this.leftSpeed = leftSpeed;
        this.rightSpeed = rightSpeed;
    }

    @Override
    public boolean isFinished() {
        return (climber.climberLeft.get() >= leftSpeed) && (climber.climberRight.get() >= rightSpeed);
    }
}