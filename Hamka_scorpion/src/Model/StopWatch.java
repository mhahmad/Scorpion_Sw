package Model;

/*
 * This class will be used as a timer in our game
 * It's a never ending timer that will only stop and start when the stop() and start() are used respectively 
 * There is also 2 methods to get elapsed time in milliseconds (getElapsedTimeMill) and get elapsed time in seconds (getElapsedTimeSecs)
 */
public class StopWatch {

	  public static long startTime = 0;
	  public static long stopTime = 0;
	  public static boolean running = false;


	  /*
	   * Start the stopWatch
	   */
	  public void start() {
	    this.startTime = System.currentTimeMillis();
	    this.running = true;
	  }

	  /*
	   * Stop the stopWatch
	   */
	  public void stop() {
	    this.stopTime = System.currentTimeMillis();
	    this.running = false;
	  }


	  //elapsed time in milliseconds
	  public long getElapsedTimeMill() {
	    long elapsed;
	    if (running) {
	      elapsed = (System.currentTimeMillis() - startTime);
	    } else {
	      elapsed = (stopTime - startTime);
	    }
	    return elapsed;
	  }


	  //elapsed time in seconds
	  public long getElapsedTimeSecs() {
	    long elapsed;
	    if (running) {
	      elapsed = ((System.currentTimeMillis() - startTime) / 1000);
	    } else {
	      elapsed = ((stopTime - startTime) / 1000);
	    }
	    return elapsed;
	  }
	}

