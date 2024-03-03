package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ShooterSubsys;

public class SpinUpShooter extends Command{

    private ShooterSubsys m_shooter;

    public SpinUpShooter(ShooterSubsys shooter) {

        m_shooter = shooter;
        addRequirements(m_shooter);

    }

    @Override 
    public void initialize() {
        m_shooter.setMotor(-0.75);
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
