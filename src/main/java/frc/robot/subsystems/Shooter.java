// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter extends SubsystemBase {
  /*
   * get all motors
   * inverse motors that need inversing
   * 
   * TODO
   * update with proper device IDs
   * update configs (TalonFX#getConfigurator())
   */
  private final TalonFX topMotor = new TalonFX(0);
  private final TalonFX bottomMotor = new TalonFX(0);

  private final double MAX_VELOCITY = 0.0;

  public Shooter() {
    topMotor.setInverted(false);
    bottomMotor.setInverted(true);
  }

  public void activate() {
    topMotor.set(MAX_VELOCITY);
    bottomMotor.set(MAX_VELOCITY);
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
