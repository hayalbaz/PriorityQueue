import BinaryHeap.*;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class testRemove {
    private BinaryHeap<Integer> heapMin = new BinaryHeap<Integer>();
    private BinaryHeap<Integer> heapMax = new BinaryHeap<Integer>(BinaryHeapType.MAX);

    @Test
    public void testRemoveMethod(){
        int[] arr = {-1, 8, 0, 3, 9, 7};
        heapMin.add(Arrays.stream(arr).boxed().toArray(Integer[]::new));
        heapMin.remove(-1);
        assertEquals("0, 3, 7, 8, 9", heapMin.toString());
        heapMax.add(Arrays.stream(arr).boxed().toArray(Integer[]::new));
        heapMax.remove(8);
        assertEquals("9, 3, 7, -1, 0", heapMax.toString());
    }
}
