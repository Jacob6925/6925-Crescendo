package frc.robot.subsystems.Intake;

public class IndexerConstants {

    //Indexer Speeds
    public static final double k_intakeSpeed = 0.3;
    public static final double k_ejectSpeed = -0.1;
    public static final double k_feedShooterSpeed = -0.5;
    public static final double k_pulseSpeed = 0.1;
    public static final double k_ampSpeed = 0.2;

    public static final int indexerCurrentLimit = 30;

    public enum IndexerSpeed {
        NONE(0.0),
        INTAKE(k_intakeSpeed),
        EJECT(k_ejectSpeed),
        PULSE(k_pulseSpeed),
        FEED_SHOOTER(k_feedShooterSpeed),
        AMP(k_ampSpeed);
     
        public final double speed;

        IndexerSpeed(double speed) {
          this.speed = speed;
        }
    }
}
