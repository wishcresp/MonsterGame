import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner 
{

	public static void main(String[] args) 
	{
		System.out.println("Starting up JUnit tests...");

		Result result = JUnitCore.runClasses(ConnHandlerTest.class);

		for (Failure failure : result.getFailures())
			System.out.println(failure.toString());

		System.out.println(result.wasSuccessful());
		
		

		// OH BOY I HOPE THIS WORKS
		Result result2 = JUnitCore.runClasses(MonsterGameExceptionTest.class);

		for (Failure failure : result.getFailures())
			System.out.println(failure.toString());

		System.out.println(result.wasSuccessful());

	}

}
