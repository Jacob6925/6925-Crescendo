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
import frc.lib.util.SquaredInput;
import frc.robot.commands.IntakeCommand;
import frc.robot.commands.ShooterCommand;
import frc.robot.commands.TeleopSwerve;
import frc.robot.subsystems.IntakeSubsys;
import frc.robot.subsystems.ShooterSubsys;
import frc.robot.subsystems.SwerveSubsys;

public class RobotContainer {
    /* Controllers */
    private final Joystick driver = new Joystick(0);
    public final Joystick operator = new Joystick(1); 

    /* Drive Controls */
    private final int translationAxis = XboxController.Axis.kLeftY.value;
    private final int strafeAxis = XboxController.Axis.kLeftX.value;
    private final int rotationAxis = XboxController.Axis.kRightX.value;

    /* Driver Buttons */
    private final JoystickButton zeroGyro = new JoystickButton(driver, XboxController.Button.kY.value);
    private final JoystickButton robotCentric = new JoystickButton(driver, XboxController.Button.kLeftBumper.value);

    /* Subsystems */
    //private List<Subsystem> m_allSubsystems = new ArrayList<>();
    private final SwerveSubsys s_Swerve = new SwerveSubsys();
    private final ShooterSubsys s_Shooter = new ShooterSubsys();
    private final IntakeSubsys s_intake = new IntakeSubsys();

     /* AutoChooser */
     private final SendableChooser<Command> autoChooser;
   

    /** The container for the robot. Contains subsystems, OI devices, and commands. */
    public RobotContainer() {
        // Configure the button bindings
        configureButtonBindings();
        
        //Pathplanner commands - templates
        NamedCommands.registerCommand("marker1", Commands.print("Passed marker 1"));
        NamedCommands.registerCommand("marker2", Commands.print("Passed marker 2"));
        NamedCommands.registerCommand("print hello", Commands.print("hello"));
         
        //Auto chooser
        autoChooser = AutoBuilder.buildAutoChooser("New Auto"); // Default auto will be `Commands.none()`
        SmartDashboard.putData("Auto Mode", autoChooser);
    }

    private void configureButtonBindings() {
        /* Driver Buttons */
        /*
         * translationAxis = XboxController.Axis.kLeftY.value;
         * strafeAxis      = XboxController.Axis.kLeftX.value;
         * rotationAxis    = XboxController.Axis.kRightX.value;
         */
        s_Swerve.setDefaultCommand(
            new TeleopSwerve(
                s_Swerve, 
                () -> -SquaredInput.scale(Constants.stickDeadband, driver.getRawAxis(translationAxis)),
                () -> -SquaredInput.scale(Constants.stickDeadband, driver.getRawAxis(strafeAxis)), 
                () -> -SquaredInput.scale(Constants.stickDeadband, driver.getRawAxis(rotationAxis)), 
                () -> robotCentric.getAsBoolean()
            )
        );
        zeroGyro.onTrue(new InstantCommand(() -> s_Swerve.zeroHeading()));
        
        //Temporary Shooter Buttons
        new JoystickButton(operator, 1).whileTrue(new ShooterCommand(s_Shooter, -0.65));
        new JoystickButton(operator, 6).whileTrue(new ShooterCommand(s_Shooter, -0.35));

        //Temporary Intake Buttons
        new JoystickButton(operator, 2).whileTrue(new IntakeCommand(s_intake).feedShooter());
        new JoystickButton(operator, 5).onTrue(new IntakeCommand(s_intake).goToGround());

        new JoystickButton(operator, 7).onTrue(new IntakeCommand(s_intake).goToSource());
        new JoystickButton(operator, 8).onTrue(new IntakeCommand(s_intake).goToAmp());
        new JoystickButton(operator, 9).onTrue(new IntakeCommand(s_intake).goToStow());
        new JoystickButton(operator, 10).whileTrue(new IntakeCommand(s_intake).intake());
        new JoystickButton(operator, 11).whileTrue(new IntakeCommand(s_intake).eject());

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
