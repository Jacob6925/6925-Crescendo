package frc.robot.commands.autonomous;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.teleop.IntakeCommand;
import frc.robot.subsystems.Intake.IntakeSubsys;
import frc.robot.subsystems.Intake.IntakeConstants.IndexerSpeed;
import frc.robot.subsystems.Intake.IntakeConstants.PivotState;

public class IntakeCommandAuto extends IntakeCommand {
    private final IntakeSubsys intake;
    private PivotState state = PivotState.NONE;
    private IndexerSpeed speed = IndexerSpeed.NONE;

    public IntakeCommandAuto(IntakeSubsys intake, PivotState state, IndexerSpeed speed) {
        super(intake, state, speed);
        
        this.intake = intake;
        this.state = state;
        this.speed = speed;
    }

    @Override
    public void end(boolean interrupted) {}

    @Override
    public boolean isFinished() {
        SmartDashboard.putNumber("i.gP()", intake.getPosition());
        SmartDashboard.putNumber("s.pS", state.pivotSetpoint);
        SmartDashboard.putNumber("i.gP() - s.pS", (intake.getPosition() - state.pivotSetpoint));
        SmartDashboard.putNumber("abs(i.gP() - s.pS)", Math.abs(intake.getPosition() - state.pivotSetpoint));

        // TODO: add check for stall
        // maybe pg 17 of https://store.ctr-electronics.com/content/user-manual/Falcon%20500%20v3%20User%27s%20Guide.pdf
        return Math.abs(intake.indexerMotor.get()-0.05) >= speed.speed && MathUtil.applyDeadband(intake.getPosition() - state.pivotSetpoint, 1) <= 3;
        
    }
}