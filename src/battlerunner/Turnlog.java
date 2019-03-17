package battlerunner;

import net.sf.robocode.io.Logger;
import robocode.control.events.BattleAdaptor;
import robocode.control.events.BattleCompletedEvent;
import robocode.control.events.BattleErrorEvent;
import robocode.control.events.BattleFinishedEvent;
import robocode.control.events.BattleMessageEvent;
import robocode.control.events.BattlePausedEvent;
import robocode.control.events.BattleResumedEvent;
import robocode.control.events.BattleStartedEvent;
import robocode.control.events.RoundEndedEvent;
import robocode.control.events.RoundStartedEvent;
import robocode.control.events.TurnEndedEvent;
import robocode.control.events.TurnStartedEvent;
import robocode.control.snapshot.IRobotSnapshot;


public class Turnlog extends BattleAdaptor{
	
	
	CSVwriter fileLogger = CSVwriter.getCSVwriter();
	
	
	protected int loggCycle = 5;
	/**
	 * True to specify that the position during each turn should be printed out.
	 */
	protected boolean isDumpingPositions =true;

	/**
	 * True to specify that each turn should be printed out.
	 */
	protected boolean isDumpingTurns =true;
	
	/**
	 * True to specify that Robot output should be printed out.
	 */
	protected boolean isDumpingOutput = true;

	@Override
	public void onBattleCompleted(BattleCompletedEvent event) {
		System.out.println("-- Battle has completed --");
         
         // Print out the sorted results with the robot names
         System.out.println("Battle results:");
         for (robocode.BattleResults result : event.getSortedResults()) {
             System.out.println("  " + result.getTeamLeaderName() + ": " + result.getScore());
         }
	}

	@Override
	public void onBattleError(BattleErrorEvent event) {
	    System.out.println("Err> " + event.getError());
	}

	@Override
	public void onBattleFinished(BattleFinishedEvent event) {
		// TODO Auto-generated method stub
		super.onBattleFinished(event);
	}

	@Override
	public void onBattleMessage(BattleMessageEvent event) {
		// TODO Auto-generated method stub
		 System.out.println("Msg> " + event.getMessage());
	}

	@Override
	public void onBattlePaused(BattlePausedEvent event) {
		// TODO Auto-generated method stub
		super.onBattlePaused(event);
	}

	@Override
	public void onBattleResumed(BattleResumedEvent event) {
		// TODO Auto-generated method stub
		super.onBattleResumed(event);
	}

	@Override
	public void onBattleStarted(BattleStartedEvent event) {
		// TODO Auto-generated method stub
		super.onBattleStarted(event);
	}

	@Override
	public void onRoundEnded(RoundEndedEvent event) {
		// TODO Auto-generated method stub
		super.onRoundEnded(event);
	}

	@Override
	public void onRoundStarted(RoundStartedEvent event) {
		// TODO Auto-generated method stub
		super.onRoundStarted(event);
	}


//	public void onTurnEnded(TurnEndedEvent event) {
//		if (isDumpingTurns) {
//			Logger.realOut.println("turn " + event.getTurnSnapshot().getTurn());
//		}
//		for (IRobotSnapshot robot : event.getTurnSnapshot().getRobots()) {
//			if (isDumpingPositions) {
//				Logger.realOut.print(robot.getVeryShortName());
//				Logger.realOut.print(" X:");
//				Logger.realOut.print(robot.getX());
//				Logger.realOut.print(" Y:");
//				Logger.realOut.print(robot.getY());
//				Logger.realOut.print(" V:");
//				Logger.realOut.print(robot.getVelocity());
//				Logger.realOut.println();
//			}
//			if (isDumpingOutput) {
//				Logger.realOut.print(robot.getOutputStreamSnapshot());
//			}
//		}
//	}
//	
	
	@Override
	public void onTurnEnded(TurnEndedEvent event) {
		if (event.getTurnSnapshot().getTurn()%5 == 0) {
			fileLogger.writeTurnLogg(event);
		}
		
	}

	@Override
	public void onTurnStarted(TurnStartedEvent event) {
		// TODO Auto-generated method stub
		super.onTurnStarted(event);
	}

	
	
}