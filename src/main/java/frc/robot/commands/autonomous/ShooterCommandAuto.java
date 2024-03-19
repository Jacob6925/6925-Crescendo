package frc.robot.commands.autonomous;

import frc.robot.commands.teleop.ShooterCommand;
import frc.robot.subsystems.ShooterSubsys;

public class ShooterCommandAuto extends ShooterCommand {
    public ShooterCommandAuto(ShooterSubsys s_Shooter, double top, double bottom) {
        super(s_Shooter, top, bottom);
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}