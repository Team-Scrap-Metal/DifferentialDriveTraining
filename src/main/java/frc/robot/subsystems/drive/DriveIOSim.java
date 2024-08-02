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

import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.wpilibj.simulation.DCMotorSim;
import frc.robot.Constants;

public class DriveIOSim implements DriveIO {
  private final DCMotorSim leftDrive;
  private final DCMotorSim rightDrive;

  public DriveIOSim() {
    leftDrive =
        new DCMotorSim(DCMotor.getNEO(2), DriveConstants.GEAR_RATIO, DriveConstants.MOI_JKG);
    rightDrive =
        new DCMotorSim(DCMotor.getNEO(2), DriveConstants.GEAR_RATIO, DriveConstants.MOI_JKG);
  }

  @Override
  public void updateInputs(DriveIOInputs inputs) {
    leftDrive.update(Constants.LOOP_CYCLE_SEC);
    rightDrive.update(Constants.LOOP_CYCLE_SEC);
    inputs.driveCurrentAmps =
        (leftDrive.getCurrentDrawAmps() + rightDrive.getCurrentDrawAmps()) / 2;
    inputs.driveLeftPositionRad = leftDrive.getAngularPositionRad();
    inputs.driveRightPositionRad = rightDrive.getAngularPositionRad();
  }

  @Override
  public void setLeftVoltage(double leftVolts) {
    leftDrive.setInputVoltage(leftVolts);
  }

  @Override
  public void setRightVoltage(double rightVolts) {
    rightDrive.setInputVoltage(rightVolts);
  }
}
