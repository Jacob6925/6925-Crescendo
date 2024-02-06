// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj2.command.Command;

public class ShooterCommand extends Command {
  private final Shooter shooter;

  public ShooterCommand(Shooter shooter) {
    this.shooter = shooter;
    addRequirements(shooter);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    shooter.activate(Shooter.MAX_VELOCITY);
  }

  @Override
  public void end(boolean interrupted) {
    shooter.disable();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
