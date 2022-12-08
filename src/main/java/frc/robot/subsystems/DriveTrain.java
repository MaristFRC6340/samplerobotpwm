// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveTrain extends SubsystemBase {
  /** Creates a new DriveTrain. */

  // Spark Motor Controllers for drive Motors
  private Spark leftFront;
  private Spark leftRear;
  private Spark rightFront;
  private Spark rightRear;


  public DriveTrain() {

    // Initialize Motor Controllers
    leftFront = new Spark(0);
    leftRear = new Spark(1);
    rightFront = new Spark(2);
    rightRear = new Spark(3);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void drive(double leftPower, double rightPower) {

    leftFront.set(-leftPower);
    leftRear.set(-leftPower);
    rightFront.set(rightPower);
    rightRear.set(rightPower);

  }

}

