import BinaryHeap.*;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertTrue;

public class testHeapInvariance {
    private BinaryHeap<Integer> heapMin = new BinaryHeap<Integer>();
    private BinaryHeap<Integer> heapMax = new BinaryHeap<Integer>(BinaryHeapType.MAX);

    @Test
    public void testHeapInvarianceForHeap(){
        int[] arr = {-1, 8, 0, 3, 9, 7};
        heapMin.add(Arrays.stream(arr).boxed().toArray(Integer[]::new));
        heapMax.add(Arrays.stream(arr).boxed().toArray(Integer[]::new));

        int oldVal = -99;
        int newVal;
        for (int i = 0; i < heapMin.size(); i++){
            newVal = heapMin.poll();
            assertTrue(oldVal > newVal);
            oldVal = newVal;
        }

        oldVal = 99;
        for (int i = 0; i < heapMax.size(); i++){
            newVal = heapMax.poll();
            assertTrue(oldVal < newVal);
            oldVal = newVal;
        }
    }
}
