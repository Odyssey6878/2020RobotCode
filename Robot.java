
package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
//import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import  edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  

  private DifferentialDrive m_robotdrive;
  private Joystick m_driverleftStick;
  private Joystick m_driverrightStick;
  private static final int leftmainDeviceID = 1; 
  private static final int rightmainDeviceID = 2;
  private static final int leftfollowDeviceID = 3; 
  private static final int rightfollowDeviceID = 4;
  private CANSparkMax m_leftmainMotor;
  private CANSparkMax m_rightmainMotor;
  private CANSparkMax m_leftfollowMotor;
  private CANSparkMax m_rightfollowMotor;

   private CANEncoder m_leftencoder;
   private CANEncoder m_rightencoder;

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
   

    m_leftmainMotor = new CANSparkMax(leftmainDeviceID, MotorType.kBrushless);
    m_rightmainMotor = new CANSparkMax(rightmainDeviceID, MotorType.kBrushless);
    m_leftfollowMotor = new CANSparkMax(leftfollowDeviceID, MotorType.kBrushless);
    m_rightfollowMotor = new CANSparkMax(rightfollowDeviceID, MotorType.kBrushless);

    m_leftmainMotor.restoreFactoryDefaults();
    m_rightmainMotor.restoreFactoryDefaults();
    m_leftfollowMotor.restoreFactoryDefaults();
    m_rightfollowMotor.restoreFactoryDefaults();

    m_robotdrive = new DifferentialDrive(m_leftmainMotor, m_rightmainMotor);

    m_driverleftStick = new Joystick(0);
    m_driverrightStick = new Joystick(1);

    m_leftfollowMotor.follow(m_leftmainMotor);
    m_rightfollowMotor.follow(m_rightmainMotor);

    m_leftencoder = m_leftmainMotor.getEncoder();
    m_rightencoder = m_rightmainMotor.getEncoder();

  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to
   * the switch structure below with additional strings. If using the
   * SendableChooser make sure to add them to the chooser code above as well.
   */
  @Override
  public void autonomousInit() {
 
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
   
    }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {

    m_robotdrive.tankDrive(m_driverleftStick.getY(), m_driverrightStick.getY());

    SmartDashboard.putNumber("Left Encoder Position", m_leftencoder.getPosition());
    SmartDashboard.putNumber("Right Encoder Position", m_rightencoder.getPosition());

    SmartDashboard.putNumber("Left Encoder Velocity", m_leftencoder.getVelocity());
    SmartDashboard.putNumber("Right Encoder Velocity", m_rightencoder.getVelocity());

  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
