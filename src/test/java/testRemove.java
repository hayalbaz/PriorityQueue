import BinaryHeap.*;
import BinaryHeap.Exceptions.EmptyHeapException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Arrays;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;

public class testRemove {
    private BinaryHeap<Integer> heapMin = new BinaryHeap<Integer>();
    private BinaryHeap<Integer> heapMax = new BinaryHeap<Integer>(BinaryHeapType.MAX);
    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void testRemoveMethod(){
        int[] arr = {-1, 8, 0, 3, 9, 7};
        heapMin.add(Arrays.stream(arr).boxed().toArray(Integer[]::new));
        heapMin.remove(-1);
        assertEquals("0, 3, 7, 8, 9", heapMin.toString());
        heapMax.add(Arrays.stream(arr).boxed().toArray(Integer[]::new));
        heapMax.remove(8);
        assertEquals("9, 3, 7, -1, 0", heapMax.toString());


        exception.expect(NoSuchElementException.class);
        heapMin.remove(-99);
        exception.expect(NoSuchElementException.class);
        heapMax.remove(-99);

        heapMin = new BinaryHeap<Integer>();
        heapMax = new BinaryHeap<Integer>(BinaryHeapType.MAX);
        exception.expect(EmptyHeapException.class);
        heapMax.remove(1);
        exception.expect(EmptyHeapException.class);
        heapMin.remove(1);
    }
}
