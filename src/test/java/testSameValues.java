import BinaryHeap.*;
import BinaryHeap.Exceptions.EmptyHeapException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Arrays;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;

public class testSameValues {
    private BinaryHeap<Integer> heapMin = new BinaryHeap<Integer>();
    private BinaryHeap<Integer> heapMax = new BinaryHeap<Integer>(BinaryHeapType.MAX);
    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void testHeapWithSameValues(){
        int[] arr = {2, 2, 2, 2, 2, 2};
        heapMin.add(Arrays.stream(arr).boxed().toArray(Integer[]::new));
        heapMax.add(Arrays.stream(arr).boxed().toArray(Integer[]::new));

        heapMin.add(3);
        assertEquals("2, 2, 2, 2, 2, 2, 3", heapMin.toString());
        heapMax.add(3);
        assertEquals("3, 2, 2, 2, 2, 2, 2", heapMax.toString());

        heapMin.remove(2);
        assertEquals("2, 2, 2, 3, 2, 2", heapMin.toString());

        heapMax.add(1);
        heapMax.remove(3);
        assertEquals("2, 2, 2, 1, 2, 2, 2", heapMax.toString());

    }
}
