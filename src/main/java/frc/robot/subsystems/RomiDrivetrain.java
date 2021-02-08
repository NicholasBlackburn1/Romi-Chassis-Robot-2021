// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class RomiDrivetrain extends SubsystemBase
{
    private static final double kCountsPerRevolution = 1440.0;
    private static final double kWheelDiameterInch = 2.75591; // 70 mm

    // The Romi has the left and right motors set to
    // PWM channels 0 and 1 respectively
    private final Spark m_leftMotor = new Spark(Constants.SPARK_LEFT);
    private final Spark m_rightMotor = new Spark(Constants.SPARK_RIGHT);

    // The Romi has onboard encoders that are hardcoded
    // to use DIO pins 4/5 and 6/7 for the left and right
    private final Encoder m_leftEncoder = new Encoder(4, 5);
    private final Encoder m_rightEncoder = new Encoder(6, 7);

    private double throttle;
    private double turn;

    // Set up the differential drive controller
    private final DifferentialDrive m_diffDrive = new DifferentialDrive(m_leftMotor, m_rightMotor);

    /** Creates a new RomiDrivetrain. */
    public RomiDrivetrain()
    {
        // Use inches as unit for encoder distances
        m_leftEncoder.setDistancePerPulse((Math.PI * kWheelDiameterInch) / kCountsPerRevolution);
        m_rightEncoder.setDistancePerPulse((Math.PI * kWheelDiameterInch) / kCountsPerRevolution);
        resetEncoders();
    }

    public void arcadeDrive(double xaxisSpeed, double zaxisRotate)
    {
        m_diffDrive.arcadeDrive(xaxisSpeed, zaxisRotate);
    }

    // Drive robot til  to the limit of the joystick
    public void deadbandedArcadeDrive() {
		
		if (RobotContainer.driverController.getRawAxis(Constants.RightStickX) > 0.1
				|| RobotContainer.driverController.getRawAxis(Constants.RightStickY) < -0.1) {
			if (RobotContainer.driverController.getRawAxis(Constants.RightStickX) < 0) {
				throttle = -Math.sqrt(Math.abs(RobotContainer.driverController.getRawAxis(Constants.RightStickX)));
			} else {
				throttle = Math.sqrt(RobotContainer.driverController.getRawAxis(Constants.RightStickX));
			}
		} else {
			throttle = 0;
		}
		/* check deadband */

		if (RobotContainer.driverController.getRawAxis(Constants.LeftStickY) > 0.2
				|| RobotContainer.driverController.getRawAxis(Constants.LeftStickY) < -0.2) {
			if (RobotContainer.driverController.getRawAxis(Constants.LeftStickY) < 0) {
				turn = -Math.sqrt(Math.abs(RobotContainer.driverController.getRawAxis(Constants.LeftStickY)));
			} else {
				turn = Math.sqrt(RobotContainer.driverController.getRawAxis(Constants.LeftStickY));
			}
		} else {
			turn = 0;
		}
		arcadeDrive(throttle, -turn);
	}

    public void resetEncoders()
    {
        m_leftEncoder.reset();
        m_rightEncoder.reset();
    }

    public double getLeftDistanceInch()
    {
        return m_leftEncoder.getDistance();
    }

    public double getRightDistanceInch()
    {
        return m_rightEncoder.getDistance();
    }

    // returns throttlePos
    public double getThrottlePos(){
        return throttle
    }

    // returns Turn pos
    public double getTurnPos(){
        return turn;
    }
    @Override
    public void periodic()
    {
       
    }

    @Override
    public void simulationPeriodic()
    {
        // This method will be called once per scheduler run during simulation
    }
}
