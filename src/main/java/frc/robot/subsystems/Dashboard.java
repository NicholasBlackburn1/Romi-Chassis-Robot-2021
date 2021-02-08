package frc.robot.subsystems;

import java.util.Map;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * This class is for Displaying Data to user
 * @author Nicholas Blackburn
 */

public class Dashboard extends SubsystemBase {
    
    // Encoder Entrys in the nwtwork tables 
    private NetworkTableEntry rightEncoderEntry;
    private NetworkTableEntry leftEncoderEntry;

    // JoyStick Network Table entrys
    private NetworkTableEntry rightJoystickEntry;
    private NetworkTableEntry leftJoystickEntry;

    private RomiDrivetrain romidrivetrain;
    
    public Dashboard(RomiDrivetrain drivetrain){
        this.romidrivetrain = drivetrain;

        // Quick and easy way of showing teleop dashboard on start up
        // but you could add the dashboard at lets say robot init or auto init if you have an auto dashboard
        showTeleopDashData();
    }

    // The Telop Dashboard that display all the robots data to the user in a gui Based way

    public void showTeleopDashData(){
        // Sends Simple Log to Console for telling user about opend
        DriverStation.reportWarning("[Teleop DashBoard]" + "TeleOPMode for Dashboard is enabled", false);
        
        // Declares new Shuffleboard tab
        final ShuffleboardTab Teleop_Dashboard = Shuffleboard.getTab("TeleopDash");

        // adds Right Encoder Entry  to Teleop Dash
        rightEncoderEntry = Teleop_Dashboard.add("Right Encoder", false).withSize(2, 1).withPosition(0, 0)
                .withWidget(BuiltInWidgets.kEncoder).getEntry();

        // adds Right Encoder Entry  to Teleop Dash
        leftEncoderEntry = Teleop_Dashboard.add("Left Encoder", false).withSize(2, 1).withPosition(0, 2)
                .withWidget(BuiltInWidgets.kEncoder).getEntry();

         // adds Right Encoder Entry  to Teleop Dash
        leftJoystickEntry = Teleop_Dashboard.add("Left Joystick pos", false).withSize(2, 2).withPosition(4, 0)
                .withWidget(BuiltInWidgets.kEncoder).getEntry();

        rightJoystickEntry = Teleop_Dashboard.add("Right JoyStick pos", false).withSize(2, 2).withPosition(7, 0)
        .withWidget(BuiltInWidgets.kEncoder).getEntry();
    }

    // this keeps all the dashboard data updated so the user has accurate info from robot
    public void dashboardData() {
        // Encoder Data -> pushed to network tables every 10 ms
        rightEncoderEntry.setValue(romidrivetrain.getRightEncoder());
        leftEncoderEntry.setValue(romidrivetrain.getLeftEncoder());

        // Joystic data -> pushed to network tables every 10 ms
        rightJoystickEntry.setDouble(romidrivetrain.getTurnPos());
        leftJoystickEntry.setDouble(romidrivetrain.getThrottlePos());

        
    }

}
