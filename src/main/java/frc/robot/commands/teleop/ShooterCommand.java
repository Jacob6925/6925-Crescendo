package frc.robot.commands.teleop;

import frc.robot.subsystems.ShooterSubsys;
import edu.wpi.first.wpilibj2.command.Command;

public class ShooterCommand extends Command {
    private final ShooterSubsys shooter;
    private final double top;
    private final double bottom;

    public ShooterCommand(ShooterSubsys s_shooter, double top, double bottom){
        shooter = s_shooter;
        this.top = top;
        this.bottom = bottom;

        addRequirements(shooter);
    }

    @Override
    public void initialize() {}

    @Override
    public void execute() {
        if (top != 0 && bottom != 0) shooter.setMotor(top, bottom);
    }

    @Override
    public void end(boolean interrupted) {
        // shooter.shooterOff();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}