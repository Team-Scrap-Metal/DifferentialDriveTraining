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
import org.littletonrobotics.junction.Logger;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drive extends SubsystemBase {
 private final DriveIO io;
 private final DriveIOInputsAutoLogged inputs = new DriveIOInputsAutoLogged();
  /** Creates a new Drive. */
  public Drive(DriveIO io) {
    this.io = io;
   
  }

  @Override
  public void periodic() {
    io.updateInputs(inputs);
    Logger.processInputs("Drive", inputs);
  }

  public void tankDrive(double leftJoystick, double rightJoystick){
    io.setLeftVoltage(leftJoystick * 12);
    io.setRightVoltage(rightJoystick * 12);
  }

  /** Stops the drive. */
  public void stop() {
   io.setLeftVoltage(0);
   io.setRightVoltage(0);
  }
}
