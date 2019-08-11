import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

//TODO: Test for equal values such as 0, 2, 2 or even 2, 2, 2
//TODO: Test for heap invariance
public class TestRunner {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(testAdd.class, testPoll.class, testAddArray.class,
                                             testAddList.class, testRemove.class);

        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }

        System.out.println(result.wasSuccessful());
    }
}
