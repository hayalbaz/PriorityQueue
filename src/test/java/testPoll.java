import BinaryHeap.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * This test creates a MIN binary heap and adds 1 and 2. When polled first poll should be 1
 * and second poll should be 2.
 */
public class testPoll {
    private BinaryHeap<Integer> heapMin = new BinaryHeap<Integer>();
    private BinaryHeap<Integer> heapMax = new BinaryHeap<Integer>(BinaryHeapType.MAX);

    @Test
    public void testPollMethod(){
        heapMin.add(1);
        heapMin.add(2);
        assertEquals(1, (int) heapMin.poll());
        assertEquals("2", heapMin.toString());
        assertEquals(2, (int) heapMin.poll());

        heapMax.add(1);
        heapMax.add(2);
        assertEquals(2, (int) heapMax.poll());
        assertEquals("1", heapMax.toString());
        assertEquals(1, (int) heapMax.poll());
    }
}
