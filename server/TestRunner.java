import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner 
{
	public static void main(String[] args) 
	{
		System.out.println("Starting up JUnit tests...");
		System.out.println("Please use the sockecho program and point it at this"
		         + " device on port 3216 to complete the first test");
		Result result;
		
		/*
		 * MonsterAiTest
		 */
		result = JUnitCore.runClasses(MonsterAiTest.class);

		for (Failure failure : result.getFailures())
			System.out.println(failure.toString());

		System.out.println(result.wasSuccessful());
		
		/*
		 * ConnHandlerTest
		 */
		result = JUnitCore.runClasses(ConnHandlerTest.class);

		for (Failure failure : result.getFailures())
			System.out.println(failure.toString());

		System.out.println(result.wasSuccessful());

		
	}
}