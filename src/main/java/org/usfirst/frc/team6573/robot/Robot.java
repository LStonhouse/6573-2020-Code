package org.usfirst.frc.team6573.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import subsystems.DriveTrain;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.cscore.UsbCamera; 
import edu.wpi.first.wpilibj.Encoder;





/**
* The VM is configured to automatically run this class, and to call the
* functions corresponding to each mode, as described in the IterativeRobot
* documentation. If you change the name of this class or the package after
* creating this project, you must also update the manifest file in the resource
* directory.
*/
public class Robot extends TimedRobot {
	final String startRight = "rights";
	final String startCenter = "center";
	final String startLeft = "left";
	String autoSelected;
	SendableChooser<String> chooser = new SendableChooser<>();

	double k = 0.08;
	double kP = 1;

	public static DriveTrain driveTrain;

	// Declaring
		
	// JOYSTICK
	Joystick leftJoy;
	Joystick rightJoy;

	double magnitude;
	double twist;
	double curve;
	// MOTOR
	Spark flDrive;
	Spark frDrive;
	Spark blDrive;
	Spark brDrive;
	Spark blSteer;
	Spark brSteer;
	Spark flSteer;
	Spark frSteer;

	Timer autoTimer;

	// CAMERA
	UsbCamera Camera;
	

	/**
	 * This function is run when the robot is first started up and should be used
	 * for any initialization code.
	 */
	@Override
	public void robotInit() {
		
	// Defining
		
		// These numbers represent the PWM ports on the roboRio
		// that the motor controllers are plugged. For example,
		// the leftMotor is plugged into PWM port 0.

		
		driveTrain = new DriveTrain();

		// Controllers
		leftJoy = new Joystick(0);
		rightJoy = new Joystick(1);
	
		// Timers
		
		autoTimer = new Timer();

		// Camera
		Camera = CameraServer.getInstance().startAutomaticCapture();
		Camera.setFPS(100);

	}

	@Override
	public void autonomousInit() {
		autoSelected = chooser.getSelected();
		// autoSelected = SmartDashboard.getString("Auto Selector",
		// defaultAuto);
		System.out.println("Auto selected: " + autoSelected);

		autoTimer = new Timer();
		autoTimer.reset();
		autoTimer.start();
		
		
	}

	@Override
	public void autonomousPeriodic()  {

// Motor Controls

		// This delay is here since the motor controllers
		// update every 20ms. No point in trying to update
		// more often.
		Timer.delay(0.020);
	}
	
	@Override
	public void teleopInit() {
		driveTrain.enable();

	autoTimer.stop();
	}

	
	@Override
	public void teleopPeriodic() {
		
		magnitude = leftJoy.getX();
		curve = leftJoy.getZ();
		twist = leftJoy.getY();
		
		driveTrain.drive(leftJoy.getZ() * .5, leftJoy.getX() * .5, leftJoy.getY());
	 

		// This delay is here since the motor controllers
		// update every 20ms. No point in trying to update
		// more often.
		Timer.delay(0.020);
	}

	@Override
	public void testPeriodic() {
	}
}

