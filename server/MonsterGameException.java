// This is the simple exception class made for the monster game
// Michael Dao s3668300 23/08/17

/* 
 * Custom exception class for the monster game
 * Comes with a JUnit tester
 */

public class MonsterGameException extends Exception 
{
	// Constructor that outputs a message when exception thrown
	public MonsterGameException(String message) 
	{
		super(message);
	}
}
