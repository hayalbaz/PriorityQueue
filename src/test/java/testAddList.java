import BinaryHeap.*;
import org.junit.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class testAddList {
    private BinaryHeap<Integer> heapMin = new BinaryHeap<Integer>();
    private BinaryHeap<Integer> heapMax = new BinaryHeap<Integer>(BinaryHeapType.MAX);

    @Test
    public void testAddListMethod(){
        int[] arr = {-1, 8, 0, 3, 9, 7};
        heapMin.add(Arrays.stream(arr).boxed().collect(Collectors.toList()));
        assertEquals("-1, 3, 0, 8, 9, 7", heapMin.toString());
        heapMax.add(Arrays.stream(arr).boxed().collect(Collectors.toList()));
        assertEquals("9, 8, 7, -1, 3, 0", heapMax.toString());
    }
}
