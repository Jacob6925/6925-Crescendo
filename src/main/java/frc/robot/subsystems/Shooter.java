// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter extends SubsystemBase {
  private final TalonFX topMotor = new TalonFX(12);
  private final TalonFX bottomMotor = new TalonFX(13);

  public static final double MAX_VELOCITY = 0.5;

  public Shooter() {
    topMotor.setInverted(false);
    bottomMotor.setInverted(true);
  }

  public void activate(double s) {
    topMotor.set(s);
    bottomMotor.set(s);
  }
  
  public void disable() {
    topMotor.set(0);
    bottomMotor.set(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run (~every 20 ms); useful for telemetry
  }
}
