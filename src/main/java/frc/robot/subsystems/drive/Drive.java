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
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import org.littletonrobotics.junction.Logger;

public class Drive extends SubsystemBase {
  private final DriveIO io;
  private final DriveIOInputsAutoLogged inputs = new DriveIOInputsAutoLogged();
  /** Creates a new Drive. */
  public final DifferentialDriveOdometry differentialDriveOdometry;

  public final Field2d field;

  public Drive(DriveIO io) {
    this.io = io;
    differentialDriveOdometry =
        new DifferentialDriveOdometry(
            new Rotation2d(), inputs.driveLeftPositionRad, inputs.driveRightPositionRad);
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

  /** Run closed loop at the specified voltage. */
  public void tankDrive(double leftJoystick, double rightJoystick) {
    io.setLeftVoltage(leftJoystick * 12);
    io.setRightVoltage(rightJoystick * 12);
  }

  /** Stops the drive. */
  public void stop() {
    io.setLeftVoltage(0);

    io.setRightVoltage(0);
  }

  public void updateOdometry() {
    differentialDriveOdometry.update(
        new Rotation2d(), inputs.driveRightPositionRad * Units.inchesToMeters(3), inputs.driveLeftPositionRad * Units.inchesToMeters(3));
  }
}
