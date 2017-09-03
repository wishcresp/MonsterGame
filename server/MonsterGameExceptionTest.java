import static org.junit.Assert.*;
import org.junit.Test;

//This is the JUnit test, this will test whether it works when a custom exception is thrown 
//Michael Dao s3668300 23/08/17

public class MonsterGameExceptionTest {

	@Test(expected = MonsterGameException.class)
	public void test() throws MonsterGameException {
		throw new MonsterGameException("There has been an error");
	}
}
