package battlerunner;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

import net.sf.robocode.io.Logger;
import robocode.control.events.TurnEndedEvent;
import robocode.control.snapshot.IRobotSnapshot;

public class CSVwriter {
	
	private static CSVwriter singletonInstance = new CSVwriter();
	private BufferedWriter bufWriter= null;
	private final static String filepath = "C:\\Users\\logicaltrojan\\Desktop\\RobcodeData\\Data.csv";
	
	
	private CSVwriter() {
		try {
			bufWriter = Files.newBufferedWriter(Paths.get(filepath), Charset.forName("UTF-8"));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static CSVwriter getCSVwriter() {
		return singletonInstance;
	}
	

	public void writeTurnLogg(TurnEndedEvent event) {


		
		for (IRobotSnapshot robot : event.getTurnSnapshot().getRobots()) {
			
			try {
				bufWriter.write(robot.getShortName());
				bufWriter.write(robot.getOutputStreamSnapshot());
				bufWriter.write("\n");
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

				
		}
		
	}

		
		
	
	public void writeRoundLogg() {
		
	}
	
	
	

}
