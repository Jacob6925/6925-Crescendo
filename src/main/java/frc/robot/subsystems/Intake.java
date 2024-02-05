package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Intake extends SubsystemBase {
    private final TalonFX wristMotor = new TalonFX(1);
    private final TalonFX intakeMotor = new TalonFX(2);

    public Intake(){
        wristMotor.config(NeutralMode.Brake);
        intakeMotor.setNeutralMode(NeutralMode.Brake);

        wristMotor.configFactoryDefault();
        intakeMotor.configFactoryDefault();

        final TalonFXConfiguration wrist = new TalonFXConfiguration();
        wrist.peakOutputForward = 0.5;  // [0, 1], 0 being no output allowed, 1 being full forward output
        wrist.peakOutputReverse = -0.5;  // [-1, 0], 0 being no output allowed, -1 being full reverse output
        wrist.slot0.kP = 0.2;
        wrist.slot0.kD = 0.2;

        /* Configure the TalonFX with the TalonFXConfiguration object */
        wristMotor.configAllSettings(wrist);
        wristMotor.setSelectedSensorPosition(0);

        final TalonFXConfiguration intake = new TalonFXConfiguration();
        intake.peakOutputForward = 0.5;  // [0, 1], 0 being no output allowed, 1 being full forward output
        intake.peakOutputReverse = -0.5;  // [-1, 0], 0 being no output allowed, -1 being full reverse output

        /* Configure the TalonFX with the TalonFXConfiguration object */
        intakeMotor.configAllSettings(intake);
    }

    public Command wristGround(){
        return this.runOnce(() -> wristMotor.set(ControlMode.Position, 0));

    }

    public Command wristHandoff(){
        return this.runOnce(() -> wristMotor.set(ControlMode.Position, 0));

    }

    public Command wristAmp(){
        return this.runOnce(() -> wristMotor.set(ControlMode.Position, 0));

    }

    public Command wristZero(){
        return this.runOnce(() -> wristMotor.set(ControlMode.Position, 0));

    }

    public void SwingArmOff(){
        wristMotor.set(ControlMode.PercentOutput, 0);
    }
    
    public void zeroPosition(){
        wristMotor.setSelectedSensorPosition(0);
    }

    @Override
    public void periodic(){
        SmartDashboard.putNumber("Intake Position", wristMotor.getSelectedSensorPosition());
    }
}