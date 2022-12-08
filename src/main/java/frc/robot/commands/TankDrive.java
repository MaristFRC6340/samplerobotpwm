// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.subsystems.DriveTrain;

public class TankDrive extends CommandBase {
  /** Creates a new TankDrive. */

  // Create DriveTrain instance
  private final DriveTrain train;

  public TankDrive(DriveTrain subsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    train = subsystem;
    addRequirements(train);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    // Reads Joysticks and assigns power to Subsystem
    double leftPower = Robot.getLeftJoystick().getY() * Constants.speedMultiplier;
    double rightPower = Robot.getRightJoystick().getY() * Constants.speedMultiplier;

    train.drive(leftPower, rightPower);

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    // Stops the Robot
    train.drive(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
