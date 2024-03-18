package frc.robot.commands.autonomous;

import frc.robot.commands.teleop.ShooterCommand;
import frc.robot.subsystems.ShooterSubsys;

public class ShooterCommandAuto extends ShooterCommand {
    private ShooterSubsys shooter;
    private double top;
    private double bottom;

    private final double DEADBAND = 0.05;

    public ShooterCommandAuto(ShooterSubsys s_shooter, double top, double bottom){
        super(s_shooter, top, bottom);
        
        this.shooter = s_shooter;
        this.top = top;
        this.bottom = bottom;
    }

    @Override
    public boolean isFinished(){
        return shooter.shooterMotorTop.get()+DEADBAND >= top && shooter.shooterMotorBottom.get()+DEADBAND >= bottom;
    }
}