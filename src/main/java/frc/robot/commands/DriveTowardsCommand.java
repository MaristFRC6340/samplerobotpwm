// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import org.photonvision.PhotonCamera;
import org.photonvision.targeting.PhotonTrackedTarget;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

/**
 * Robot Drives towards an April Tag
 * Robot Stops within 5 feet of Tag
 * Hint: Use Y position of Tag to determine stop
 */

public class DriveTowardsCommand extends CommandBase {
  /** Creates a new DriveTowardsCommand. */

  private DriveTrain drivetrain;
  private long startTime;
  private double duration;
  private double power;
  private int targetID = -1;

  private PhotonCamera camera = new PhotonCamera("photonvision");


  public DriveTowardsCommand(DriveTrain dtrain, double pwr, double time, int tID) {
    // Use addRequirements() here to declare subsystem dependencies.
    drivetrain = dtrain;
    duration = time;
    power = pwr;
    targetID = tID;
    addRequirements(drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // Get Target
    var result = camera.getLatestResult();

    boolean hasTargets = result.hasTargets();

    double error = 0;

    if (hasTargets) {
      PhotonTrackedTarget target = result.getBestTarget();
      error = target.getYaw();
      targetID = target.getFiducialId();
    }


    // Set Error
    
    double kP = 0.03;

    double turnPower = power * error * kP;
    if (turnPower > 0.4) {
      turnPower = 0.4;
    }
    if (turnPower < -0.4) {
      turnPower = -0.4;
    }
    System.out.println(error*power);
    System.out.println(targetID);
    drivetrain.drive(-turnPower, turnPower);


  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
