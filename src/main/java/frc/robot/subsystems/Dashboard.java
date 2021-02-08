package frc.robot.dashboard;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * This class is for Displaying Data to user
 * @author Nicholas Blackburn
 */

public class Dashboard extends SubsystemBase {
    

    private NetworkTableEntry encoderEntry;
    private NetworkTableEntry motorPowerEntry;
    
    public Dashboard(){

    }

    // Shows The User Data via function
    public void showTeleopDashData(){
        // Sends Simple Log to Console for telling user about opend
        DriverStation.reportWarning("[Nicholas's DashBoard]" + "TeleOPMode for Dashboard is enabled", false);
    
        final ShuffleboardTab Teleop_Dashboard = Shuffleboard.getTab("TeleopDash");
    }

}
