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

import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.commands.autonomous.IntakeCommandAuto;
import frc.robot.commands.autonomous.ShooterCommandAuto;
import frc.robot.commands.teleop.IntakeCommand;
import frc.robot.commands.teleop.ShooterCommand;
import frc.robot.commands.teleop.TeleopSwerve;
import frc.robot.subsystems.ShooterSubsys;
import frc.robot.subsystems.SwerveSubsys;
// import frc. robot.subsystems.ClimberSubsys;
import frc.robot.subsystems.Intake.IntakeConstants;
import frc.robot.subsystems.Intake.IntakeSubsys;
import frc.robot.subsystems.Intake.IntakeConstants.IndexerSpeed;
import frc.robot.subsystems.Intake.IntakeConstants.PivotState;

public class RobotContainer {
    /* Controllers */
    private final Joystick driver = new Joystick(0);
    private final Joystick operator = new Joystick(1); 

    /* Drive Controls */
    private final int translationAxis = XboxController.Axis.kLeftY.value;
    private final int strafeAxis = XboxController.Axis.kLeftX.value;
    private final int rotationAxis = XboxController.Axis.kRightX.value;

    /* Driver Buttons */
    private final JoystickButton zeroGyro = new JoystickButton(driver, XboxController.Button.kY.value);
    private final JoystickButton robotCentric = new JoystickButton(driver, XboxController.Button.kLeftBumper.value);

    /* Subsystems */
    private final SwerveSubsys s_Swerve = new SwerveSubsys();
    private final ShooterSubsys s_Shooter = new ShooterSubsys();
    private final IntakeSubsys s_intake = new IntakeSubsys();
    // private final ClimberSubsys s_climber = new ClimberSubsys();

     /* AutoChooser */
    private final SendableChooser<Command> autoChooser;
   

    /** The container for the robot. Contains subsystems, OI devices, and commands. */
    public RobotContainer() {
        
        // Configure the button bindings
        configureButtonBindings();
        
        //Pathplanner commands - templates
        // NamedCommands.registerCommand("Spin Up Shooter", new SpinUpShooter(s_Shooter));

        NamedCommands.registerCommand("testTwo", Commands.print("Passed marker 2"));
        NamedCommands.registerCommand("print hello", Commands.print("hello"));
         
        // Register PathPlanner named commands
        NamedCommands.registerCommand("Spin Up Shooter", new ShooterCommandAuto(s_Shooter, -0.75,-0.75));
        NamedCommands.registerCommand("Ground Intake", new IntakeCommandAuto(s_intake, PivotState.GROUND, IndexerSpeed.INTAKE));
        NamedCommands.registerCommand("Stow Intake", new IntakeCommandAuto(s_intake, PivotState.STOW, IndexerSpeed.PULSE));
        NamedCommands.registerCommand("Score Gamepiece", new IntakeCommandAuto(s_intake, IntakeSubsys.pivotState, IndexerSpeed.FEED_SHOOTER));


        //Auto chooser
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
        zeroGyro.onTrue(new InstantCommand(() -> s_Swerve.zeroHeading()));
        
        //Temporary Shooter Buttons
        new JoystickButton(operator, 2).whileTrue(new ShooterCommand(s_Shooter, -0.85, -0.85));
        new JoystickButton(operator, 4).whileTrue(new ShooterCommand(s_Shooter, -0.75, -0.75));
    
        //Temporary Indexer Buttons
        new JoystickButton(operator, 1).whileTrue(new IntakeCommand(s_intake, IntakeSubsys.pivotState, IntakeConstants.IndexerSpeed.FEED_SHOOTER));
        new JoystickButton(operator, 6).whileTrue(new IntakeCommand(s_intake, IntakeSubsys.pivotState, IntakeConstants.IndexerSpeed.INTAKE));
        new JoystickButton(operator, 12).whileTrue(new IntakeCommand(s_intake, IntakeSubsys.pivotState, IntakeConstants.IndexerSpeed.AMP));

        //Temporary Pivot Buttons
        new JoystickButton(operator, 5).onTrue(new IntakeCommand(s_intake, IntakeConstants.PivotState.GROUND, IntakeConstants.IndexerSpeed.INTAKE));
        new JoystickButton(operator, 3).onTrue(new IntakeCommand(s_intake, IntakeConstants.PivotState.STOW, IntakeSubsys.indexerSpeed));
        new JoystickButton(operator, 11).onTrue(new IntakeCommand(s_intake, IntakeConstants.PivotState.AMP, IntakeSubsys.indexerSpeed));
      
        //Temporary Climber Buttons
        // new JoystickButton(operator, 7).whileTrue(new TeleopClimber(s_climber, 0.3, 0));
        // new JoystickButton(operator, 8).whileTrue(new TeleopClimber(s_climber, 0, 0.3));
        // new JoystickButton(operator, 9).whileTrue(new TeleopClimber(s_climber, -0.3, 0));
        // new JoystickButton(operator, 10).whileTrue(new TeleopClimber(s_climber, 0, -0.3));

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
