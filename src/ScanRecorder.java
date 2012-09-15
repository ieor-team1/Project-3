
import lejos.nxt.*;
import lejos.util.Datalogger;

/**
 * records the results of a 180 degree scan (angle and light intensity
 * to a dataLogger and plays it back over a USB port
 * @author owner.GLASSEY
 */

public class ScanRecorder
{


   public ScanRecorder(NXTRegulatedMotor theMotor, LightSensor eye)
   {
      motor = theMotor;    
      _eye = eye;
      _eye.setFloodlight(false);
   }

   /**
    * returns the angle at which the maximum light intensity was found
    * @return 
    */
   public int getTargetBearing()
   {
      return _targetBearing;
   }
/**
    * returns the maximum light intensity found during the scan
    * @return  light intensity
    */
   public int getLight()
   {
      return _maxLight;
   }
/**
    * returns the angle in which the light sensor is pointing
    * @return the angle
    */
   public int getHeadAngle()
   {
      return motor.getTachoCount();
   }
 /**
    * sets the motor sped in deg/sec
    * @param speed 
    */
   public void setSpeed(int speed)
   {
      motor.setSpeed(speed);
   }
/**
    * scan from current head angle to limit angle and write the angle and 
    * light sensor value to the datalog
    * @param limitAngle 
    */
   public void scanTo(int limitAngle)
   {
      int oldAngle = motor.getTachoCount();
      motor.rotateTo(limitAngle, true);
      int light = 0;
      while (motor.isMoving())
      {
         short angle = (short) motor.getTachoCount();
         if (angle != oldAngle)
         {
            light = _eye.getLightValue();
            compareLight(light, angle);
            oldAngle = angle;
         }

         Thread.yield();
      }
   }
   
   public void compareLight(int light, int angle){
	   if(light > _maxLight){
		   _maxLight = light;
		   _targetBearing = angle;
	   }
   }
/**
    * rotate the scanner head to the angle
    * @param angle
    * @param instantReturn if true, the method is non-blocking
    */
   public void rotateTo(int angle, boolean instantReturn)
   {
      motor.rotateTo(angle, instantReturn);
   }
/**
    * rotates the scaner head to angle;  returns when rotation is complete
    * @param angle 
    */
   public void rotateTo(int angle)
   {
      rotateTo(angle, false);
   }
/**
    * scan between -90 and 90 degrees
    * @param args 
    */
   public int scan()
   {
      ScanRecorder s = new ScanRecorder(Motor.B, new LightSensor(SensorPort.S2));
      //Button.waitForAnyPress();
      s.rotateTo(-90);
      s.scanTo(90);
      int angle1 = s.getTargetBearing();
      s.scanTo(-90);
      int angle2 = s.getTargetBearing();
      s.rotateTo(0);
      return ( angle1+angle2 ) / 2;
      //s.dl.transmit();  // use usb
   }
   /******* instance variabled ***************/
   NXTRegulatedMotor motor;
   LightSensor _eye;
   int _targetBearing;
   int _maxLight;
   boolean _found = false;
}
