package battlerunner;

import java.util.Random;

import robocode.RobocodeFileWriter;
import robocode.RobotStatus;
import robocode.control.*;
import robocode.control.events.*;
 
 //
 // Application that demonstrates how to run two sample robots in Robocode using the
 // RobocodeEngine from the robocode.control package.
 //
 // @author Flemming N. Larsen
 //
 public class BattleRunner {
	 
 //	 RobocodeFileWriter fileWriter = new RobocodeFileWriter("./test.txt");\
	 
	 static Random generator = new Random();
	
	 private static int Tank1;
	 private static int Tank2;
	 
     public static void main(String[] args) {
 
         // Disable log messages from Robocode
         RobocodeEngine.setLogMessagesEnabled(true);

         // Create the RobocodeEngine
         //   RobocodeEngine engine = new RobocodeEngine(); // Run from current working directory
         RobocodeEngine engine = new RobocodeEngine(new java.io.File("C:\\robocode")); // Run from C:/Robocode
 
         // Add our own battle listener to the RobocodeEngine 
         engine.addBattleListener(new Turnlog());
 
         // Show the Robocode battle view
         engine.setVisible(true);
 
         // Setup the battle specification
 
         int numberOfRounds = 10;
         BattlefieldSpecification battlefield = new BattlefieldSpecification(800, 600); // 800x600
         RobotSpecification[] selectedRobots = engine.getLocalRepository();
         
         Tank1 = generator.nextInt(selectedRobots.length);
         Tank2 = generator.nextInt(selectedRobots.length);
         RobotSpecification[] sample = {
        		 selectedRobots[Tank1],
        		 selectedRobots[Tank2]
         };
         
  
         
 
         BattleSpecification battleSpec = new BattleSpecification(numberOfRounds, battlefield, sample);
 
         // Run our specified battle and let it run till it is over
         engine.runBattle(battleSpec, true); // waits till the battle finishes
 
         // Cleanup our RobocodeEngine
         engine.close();
 
         // Make sure that the Java VM is shut down properly
         System.exit(0);
     }
 }
 
 //
 // Our private battle listener for handling the battle event we are interested in.
 //
 class BattleObserver extends BattleAdaptor {
 
     // Called when the battle is completed successfully with battle results
     public void onBattleCompleted(BattleCompletedEvent e) {
         System.out.println("-- Battle has completed --");
         
         // Print out the sorted results with the robot names
         System.out.println("Battle results:");
         for (robocode.BattleResults result : e.getSortedResults()) {
             System.out.println("  " + result.getTeamLeaderName() + ": " + result.getScore());
         }
     }
 
     // Called when the game sends out an information message during the battle
     public void onBattleMessage(BattleMessageEvent e) {
         System.out.println("Msg> " + e.getMessage());
     }
 
     // Called when the game sends out an error message during the battle
     public void onBattleError(BattleErrorEvent e) {
         System.out.println("Err> " + e.getError());
     }
 }
 