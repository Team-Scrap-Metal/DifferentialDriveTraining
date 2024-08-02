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

import edu.wpi.first.math.VecBuilder;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.wpilibj.simulation.DifferentialDrivetrainSim;
import frc.robot.Constants;

public class DriveIOSim implements DriveIO {
  // private final DCMotorSim leftDrive;
  // private final DCMotorSim rightDrive;
  private final DifferentialDrivetrainSim differentialDrivetrainSim;

  public DriveIOSim() {
    // leftDrive =
    //     new DCMotorSim(DCMotor.getNEO(2), DriveConstants.GEAR_RATIO, DriveConstants.MOI_JKG_M2);
    // rightDrive =
    //     new DCMotorSim(DCMotor.getNEO(2), DriveConstants.GEAR_RATIO, DriveConstants.MOI_JKG_M2);

    differentialDrivetrainSim =
        new DifferentialDrivetrainSim(
            DCMotor.getNEO(2),
            DriveConstants.GEAR_RATIO,
            DriveConstants.MOI_JKG_M2,
            DriveConstants.MASS_KG,
            DriveConstants.WHEEL_RADIUS_M,
            DriveConstants.TRACK_WIDTH_M,
            VecBuilder.fill(0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1));
  }

  @Override
  public void updateInputs(DriveIOInputs inputs) {
    differentialDrivetrainSim.update(Constants.LOOP_CYCLE_SEC);
    inputs.driveCurrentAmps = differentialDrivetrainSim.getCurrentDrawAmps();
    inputs.driveLeftPositionMeters = differentialDrivetrainSim.getLeftPositionMeters();
    inputs.driveRightPositionMeters = differentialDrivetrainSim.getRightPositionMeters();
    inputs.driveRotationRad = differentialDrivetrainSim.getHeading();
  }

  @Override
  public void setVoltage(double leftVolts, double rightVolts) {
    differentialDrivetrainSim.setInputs(leftVolts, rightVolts);
  }
}
