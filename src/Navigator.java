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
	double gain = 0.5f;
	public void go(){
		while(true){
	      int angle = s.scan();
	      if(s.getLight()>46){
	    	  pilot.rotate(180);
	      }
	      pilot.steer(angle*gain);
		}
	}
	
}
