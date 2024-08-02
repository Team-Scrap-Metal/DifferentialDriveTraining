// Copyright 2021-2024 FRC 6328
// http://github.com/Mechanical-Advantage
//
// This program is free software; you can redistribute it and/or
// modify it under the terms of the GNU General Public License
// version 3 as published by the Free Software Foundation or
// available in the root directory of this project.
//
// This program is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
// GNU General Public License for more details.

package frc.robot.subsystems.drive;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import org.littletonrobotics.junction.Logger;

public class Drive extends SubsystemBase {
  private final DriveIO io;
  private final DriveIOInputsAutoLogged inputs = new DriveIOInputsAutoLogged();
  public final DifferentialDriveOdometry differentialDriveOdometry;
  public final Field2d field;
  /** Creates a new Drive. */
  public Drive(DriveIO io) {
    this.io = io;
    differentialDriveOdometry =
        new DifferentialDriveOdometry(
            new Rotation2d(), inputs.driveLeftPositionMeters, inputs.driveRightPositionMeters);
    field = new Field2d();
  }

  @Override
  public void periodic() {
    io.updateInputs(inputs);
    Logger.processInputs("Drive", inputs);
    this.updateOdometry();
    field.setRobotPose(differentialDriveOdometry.getPoseMeters());
    SmartDashboard.putData(field);
  }

  public void tankDrive(double leftJoystick, double rightJoystick) {
    io.setVoltage(
        leftJoystick * Constants.BATTERY_VOLTAGE, rightJoystick * Constants.BATTERY_VOLTAGE);
  }

  /** Stops the drive. */
  public void stop() {
    io.setVoltage(0, 0);
  }

  public void updateOdometry() {
    differentialDriveOdometry.update(
        inputs.driveRotationRad, inputs.driveLeftPositionMeters, inputs.driveRightPositionMeters);
  }
}
