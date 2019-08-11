import BinaryHeap.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * This test creates a MIN binary heap and adds 1 and 2. To verify it, size should be
 * 2 after adding 2 elements.
 */
public class testAdd {
    private BinaryHeap<Integer> heapMin = new BinaryHeap<Integer>();
    private BinaryHeap<Integer> heapMax = new BinaryHeap<Integer>(BinaryHeapType.MAX);

    @Test
    public void testAddMethod(){
        heapMin.add(1);
        heapMin.add(2);
        assertEquals(2, heapMin.size());
        assertEquals("1, 2", heapMin.toString());

        heapMax.add(1);
        heapMax.add(2);
        assertEquals(2, heapMax.size());
        assertEquals("2, 1", heapMax.toString());
    }
}
