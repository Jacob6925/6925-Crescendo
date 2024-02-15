package frc.lib.util;

public class SquaredInput {
  public static double scale(double deadbandLimit, double input) {
    double numeratorFactor = (Math.abs(input) - deadbandLimit);
    double denominatorFactor = 1 - deadbandLimit;
    double sign = (input >= 0 ? 1 : -1);

    return sign * ((numeratorFactor * numeratorFactor) / (denominatorFactor * denominatorFactor));
  }
}