package frc.robot;

//import java.util.ArrayList;
//import java.util.List;

import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.auto.NamedCommands;

//import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

import edu.wpi.first.wpilibj2.command.Commands;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.commands.*;
import frc.robot.subsystems.*;

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
    private final Swerve s_Swerve = new Swerve();
    private final Shooter s_Shooter = new Shooter();
    //private final Intake i_intake = Intake.getInstance();

     /* AutoChooser */
     private final SendableChooser<Command> autoChooser;

     double speed = 0;
   

    /** The container for the robot. Contains subsystems, OI devices, and commands. */
    public RobotContainer() {
        s_Swerve.setDefaultCommand(
            new TeleopSwerve(
                s_Swerve, 
                () -> -driver.getRawAxis(translationAxis), 
                () -> -driver.getRawAxis(strafeAxis), 
                () -> -driver.getRawAxis(rotationAxis), 
                () -> robotCentric.getAsBoolean()
            )
        );

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
        zeroGyro.onTrue(new InstantCommand(() -> s_Swerve.zeroHeading()));
        
        //Temporary Shooter Buttons
        new JoystickButton(operator, 3).whileTrue(new ShooterCommand(s_Shooter, -0.5));
        new JoystickButton(operator, 5).whileTrue(new ShooterCommand(s_Shooter, 0.5));

        //Temporary Intake Buttons
        //new JoystickButton(operator, 6).onTrue(i_intake.goToGround());
        //new JoystickButton(operator, 7).onTrue(i_intake.goToSource());
        //new JoystickButton(operator, 8).onTrue(i_intake.goToAmp());
        //new JoystickButton(operator, 9).onTrue(i_intake.goToStow());

        //new JoystickButton(operator, 10).whileTrue(i_intake.intake());
        //new JoystickButton(operator, 11).whileTrue(i_intake.pulse());
        //new JoystickButton(operator, 12).whileTrue(i_intake.eject());
        //new JoystickButton(operator, 13).whileTrue(i_intake.feedShooter());
        //new JoystickButton(operator, 14).whileTrue(i_intake.stopIntake());
        //new JoystickButton(operator, 15).whileTrue(i_intake.pulse());
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
