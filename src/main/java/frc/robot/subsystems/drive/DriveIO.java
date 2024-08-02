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

import org.littletonrobotics.junction.AutoLog;
import org.opencv.core.RotatedRect;

import edu.wpi.first.math.geometry.Rotation2d;

public interface DriveIO {
  @AutoLog
  public static class DriveIOInputs {
    public double driveLeftPositionMeters = 0;
    public double driveCurrentAmps = 0;
    public Rotation2d driveRotationMeters = new Rotation2d();
    public double driveRightPositionMeters = 0;
  }

  /** Updates the set of loggable inputs. */
  public default void updateInputs(DriveIOInputs inputs) {}

  /** Run open loop at the specified voltage. */
  public default void setVoltage(double leftVolts, double rightVolts) {}

  
}
