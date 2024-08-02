package frc.robot.subsystems.drive;

import edu.wpi.first.math.util.Units;

public class DriveConstants {
  public static final double GEAR_RATIO = 6.11;
  public static final double MOI_JKG = 0.008;
  public static final double MASS_KGS = Units.lbsToKilograms(100);
  public static final double WHEEL_RADIUS_M = Units.inchesToMeters(3);
  public static final double TRACK_WIDTH_M =Units.inchesToMeters(27);
}
