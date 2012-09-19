import lejos.nxt.Button;
import lejos.nxt.LightSensor;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.robotics.navigation.DifferentialPilot;


public class Navigator {
	
	float wheelDiameter =  5.6f;
    float trackWidth = 11.4f;
    LightSensor lightSensor = new LightSensor(SensorPort.S4);   
	DifferentialPilot pilot = new DifferentialPilot(wheelDiameter, trackWidth, Motor.A, Motor.C);
	ScanRecorder s = new ScanRecorder(Motor.B, lightSensor);
	double gain = 1f;
	public void go(){
		pilot.setTravelSpeed(30);
		while(true){
	      int maxLight = s.scan();
	      System.out.println("Max Light = " + maxLight + " Angle =" + s._angle1);
	      if(maxLight>55){
	    	  pilot.stop();
	    	  pilot.rotate(180);
	      }
	      pilot.steer(-s._angle1*gain);
	}
	}
	
}