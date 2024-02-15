package frc.robot;

import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.auto.NamedCommands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

import edu.wpi.first.wpilibj2.command.Commands;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.IntakeCommand;
import frc.robot.commands.ShooterCommand;
import frc.robot.commands.TeleopSwerve;
import frc.robot.subsystems.ShooterSubsys;
import frc.robot.subsystems.SwerveSubsys;
import frc.robot.subsystems.Intake.IndexerSubsys;
import frc.robot.subsystems.Intake.IntakeSubsys;
import frc.robot.subsystems.Intake.PivotSubsys;

public class RobotContainer {
    /* Controllers */
    private final Joystick driver = new Joystick(0);
    public final Joystick operator = new Joystick(1); 

    /* Drive Controls */
    private final int translationAxis = XboxController.Axis.kLeftY.value;
    private final int strafeAxis = XboxController.Axis.kLeftX.value;
    private final int rotationAxis = XboxController.Axis.kRightX.value;

    /* Driver Buttons */
    // private final JoystickButton zeroGyro = new JoystickButton(driver, XboxController.Button.kY.value);
    // private final JoystickButton robotCentric = new JoystickButton(driver, XboxController.Button.kLeftBumper.value);

    /* Subsystems */
    //private List<Subsystem> m_allSubsystems = new ArrayList<>();
    // private final SwerveSubsys s_Swerve = new SwerveSubsys();
    private final ShooterSubsys s_Shooter = new ShooterSubsys();
    private final IntakeSubsys s_intake = new IntakeSubsys();
    private final IndexerSubsys s_indexer = new IndexerSubsys();

     /* AutoChooser */
    //  private final SendableChooser<Command> autoChooser;
   

    /** The container for the robot. Contains subsystems, OI devices, and commands. */
    public RobotContainer() {
        new PivotSubsys();
        // Configure the button bindings
        configureButtonBindings();
        
        //Pathplanner commands - templates
        NamedCommands.registerCommand("testOne", Commands.print("Passed marker 1"));
        NamedCommands.registerCommand("testTwo", Commands.print("Passed marker 2"));
        NamedCommands.registerCommand("print hello", Commands.print("hello"));
         
        //Auto chooser
        // autoChooser = AutoBuilder.buildAutoChooser("New Auto"); // Default auto will be `Commands.none()`
        // SmartDashboard.putData("Auto Mode", autoChooser);
    }

    private void configureButtonBindings() {
        // /* Driver Buttons */
        // s_Swerve.setDefaultCommand(
        //     new TeleopSwerve(
        //         s_Swerve, 
        //         () -> -driver.getRawAxis(translationAxis), 
        //         () -> -driver.getRawAxis(strafeAxis), 
        //         () -> -driver.getRawAxis(rotationAxis), 
        //         () -> robotCentric.getAsBoolean()
        //     )
        // );
        // zeroGyro.onTrue(new InstantCommand(() -> s_Swerve.zeroHeading()));
        
        //Temporary Shooter Buttons
        new JoystickButton(operator, 1).whileTrue(new ShooterCommand(s_Shooter, -0.65));
        new JoystickButton(operator, 6).whileTrue(new ShooterCommand(s_Shooter, -0.35));

        //Temporary Intake Buttons
        new JoystickButton(operator, 2).whileTrue(new IntakeCommand(s_intake, s_indexer, () -> s_indexer.feedShooter()));
        new JoystickButton(operator, 5).onTrue(new IntakeCommand(s_intake, s_indexer, () -> s_intake.goToGround()));

        new JoystickButton(operator, 7).onTrue(new IntakeCommand(s_intake, s_indexer, () -> s_intake.goToSource()));
        new JoystickButton(operator, 8).onTrue(new IntakeCommand(s_intake, s_indexer, () -> s_intake.goToAmp()));
        new JoystickButton(operator, 9).onTrue(new IntakeCommand(s_intake, s_indexer, () -> s_intake.goToStow()));
        new JoystickButton(operator, 10).whileTrue(new IntakeCommand(s_intake, s_indexer, () -> s_indexer.intake()));
        new JoystickButton(operator, 11).whileTrue(new IntakeCommand(s_intake, s_indexer, () -> s_indexer.eject()));

    }

    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand() {
        // return autoChooser.getSelected();
        return null;
    }
    
}
