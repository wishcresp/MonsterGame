import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner 
{
	public static void main(String[] args) 
	{
		System.out.println("Starting up JUnit tests...");
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