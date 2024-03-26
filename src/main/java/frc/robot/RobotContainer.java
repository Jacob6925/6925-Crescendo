package frc.robot;

import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.auto.NamedCommands;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.commands.teleop.ClimberCommand;
import frc.robot.commands.teleop.IntakeCommand;
import frc.robot.commands.teleop.ShooterCommand;
import frc.robot.commands.teleop.TeleopSwerve;
import frc.robot.subsystems.SwerveSubsys;
import frc.robot.subsystems.Intake.IntakeConstants;
import frc.robot.subsystems.Intake.IntakeSubsys;
import frc.robot.subsystems.Intake.IntakeConstants.IndexerSpeed;
import frc.robot.subsystems.Intake.IntakeConstants.PivotState;
import frc.robot.subsystems.Shooter.ShooterSubsys;
import frc. robot.subsystems.ClimberSubsys;

public class RobotContainer {
    /* Controllers */
    private final Joystick driver = new Joystick(0);
    private final Joystick operator = new Joystick(1); 

    /* Drive Controls */
    private final int translationAxis = XboxController.Axis.kLeftY.value;
    private final int strafeAxis = XboxController.Axis.kLeftX.value;
    private final int rotationAxis = XboxController.Axis.kRightX.value;

    /* Driver Buttons */
    private final JoystickButton robotCentric = new JoystickButton(driver, XboxController.Button.kLeftBumper.value);

    /* Subsystems */
    public final SwerveSubsys s_Swerve = new SwerveSubsys();
    public final ShooterSubsys s_Shooter = new ShooterSubsys();
    public final IntakeSubsys s_Intake = new IntakeSubsys();
    public final ClimberSubsys s_Climber = new ClimberSubsys();

     /* AutoChooser */
    private final SendableChooser<Command> autoChooser;

    public static RobotContainer instance;
    
    public static RobotContainer getInstance() {
        return instance;
    }
   

    /** The container for the robot. Contains subsystems, OI devices, and commands. */
    public RobotContainer() {
        instance = this;
        
        // Configure the button bindings
        configureButtonBindings();

        // Register PathPlanner named commands
        NamedCommands.registerCommand("Spin Up Shooter", new InstantCommand(() -> s_Shooter.setMotor(-0.75,-0.75)));

        NamedCommands.registerCommand("Ground Intake", new InstantCommand(() -> {
            s_Intake.setPivotState(PivotState.GROUND);
            s_Intake.setIndexerSpeed(IndexerSpeed.INTAKE);
        }, s_Intake));
        
        NamedCommands.registerCommand("Stow Intake", new InstantCommand(() -> {
            s_Intake.setPivotState(PivotState.STOW);
            s_Intake.setIndexerSpeed(IndexerSpeed.PULSE);
        }, s_Intake));
        
        NamedCommands.registerCommand("Score Gamepiece", new InstantCommand(() -> {
            s_Intake.setPivotState(PivotState.NONE);
            s_Intake.setIndexerSpeed(IndexerSpeed.FEED_SHOOTER);
        }, s_Intake));
        
        NamedCommands.registerCommand("Stop Indexer", new InstantCommand(() -> s_Intake.setIndexerSpeed(IndexerSpeed.NONE), s_Intake));

        NamedCommands.registerCommand("Stop Shooter", new InstantCommand(() -> s_Shooter.shooterOff(), s_Shooter));

        

        // Auto chooser
        autoChooser = AutoBuilder.buildAutoChooser("New Auto"); // Default auto will be `Commands.none()`
        SmartDashboard.putData("Auto Mode", autoChooser);

        // Setup camera
        CameraServer.startAutomaticCapture();
    }

    private void configureButtonBindings() {
        /* Driver Buttons */
        s_Swerve.setDefaultCommand(
            new TeleopSwerve(
                s_Swerve, 
                () -> -driver.getRawAxis(translationAxis), 
                () -> -driver.getRawAxis(strafeAxis), 
                () -> -driver.getRawAxis(rotationAxis), 
                () -> robotCentric.getAsBoolean()
            )
        );
        // Zerp Gyro
        new JoystickButton(driver, XboxController.Button.kY.value).onTrue(new InstantCommand(() -> s_Swerve.zeroHeading()));
        // Toggle Half Speed (start a new scope for toggleHalfSpeedCommand)
        {
            InstantCommand toggleHalfSpeedCommand = new InstantCommand(() -> {s_Swerve.toggleHalfSpeed();}, s_Swerve);
            new JoystickButton(driver, XboxController.Button.kRightBumper.value).onTrue(toggleHalfSpeedCommand).onFalse(toggleHalfSpeedCommand);
        }
        new JoystickButton(operator, 1).whileTrue(new IntakeCommand(s_Intake, null, IntakeConstants.IndexerSpeed.FEED_SHOOTER)); // 1 - feed shooter
        new JoystickButton(operator, 2).whileTrue(new ShooterCommand(s_Shooter, -0.85, -0.85)); // 2 - start shooter
        // new JoystickButton(operator, 3).whileTrue(new InstantCommand(() -> s_Climber.setLeft(-0.3))); // 3 - left climber down
        // new JoystickButton(operator, 4).whileTrue(new InstantCommand(() -> s_Climber.setRight(-0.3))); // 4 - right climber down
        // new JoystickButton(operator, 5).whileTrue(new InstantCommand(() -> s_Climber.setLeft(0.3))); // 5 - left climber up
        // new JoystickButton(operator, 6).whileTrue(new InstantCommand(() -> s_Climber.setRight(0.3))); // 6 - right climber up
        new JoystickButton(operator, 3).whileTrue(new ClimberCommand(s_Climber, -0.3, -0.3)); // climber down
        // new JoystickButton(operator, 5).whileTrue(new ClimberCommand(s_Climber, 0.3, 0.3)); // climber up
        new JoystickButton(operator, 5).whileTrue(new IntakeCommand(s_Intake, null, IndexerSpeed.PULSE));
        new JoystickButton(operator, 7).onTrue(new IntakeCommand(s_Intake, PivotState.AMP, IndexerSpeed.NONE)); // 7 - amp pivot
        new JoystickButton(operator, 8).whileTrue(new IntakeCommand(s_Intake, PivotState.NONE, IndexerSpeed.AMP)); // 8 - amp indexer
        new JoystickButton(operator, 9).whileTrue(new IntakeCommand(s_Intake, null, IndexerSpeed.INTAKE)); // 9 - intake (centering)
        // new JoystickButton(operator, 10).onTrue(); // 10 - HP
        new JoystickButton(operator, 11).onTrue(new IntakeCommand(s_Intake, PivotState.GROUND, IndexerSpeed.INTAKE)); // 11 - intake out
        new JoystickButton(operator, 12).onTrue(new IntakeCommand(s_Intake, PivotState.STOW, IndexerSpeed.NONE)); // 12 - intake in
    }

    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand() {
        return autoChooser.getSelected();
    }
    
}
