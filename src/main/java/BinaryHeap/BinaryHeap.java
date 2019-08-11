package BinaryHeap;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

//TODO:Better Visualisation
/**
 * It implements a MIN binary heap by default, although it can be configured as a MAX binary heap by passing it as a
 * parameter to constructor.
 * @param <T>
 */
public class BinaryHeap<T extends Comparable<T>> {
    private ArrayList<T> heap;
    private BinaryHeapType type;

    public BinaryHeap(){
        this(BinaryHeapType.MIN);
    }

    public BinaryHeap(BinaryHeapType type){
        this.type = type;
        heap = new ArrayList<>();
    }

    /**
     * Adds the element t to its proper place in the heap.
     * @param t the element we want to add to the heap
     */
    public void add(T t){
        heap.add(t);
        int currentIndex = heap.size() - 1;
        while (failsHeapInvarianceUp(currentIndex))
            currentIndex = swap(currentIndex, parentOf(currentIndex));
    }

    /**
     * Adds the contents of array to the heap
     * @param t the array we want to add to the heap
     */
    public void add(T[] t){
        for (int i = 0; i < t.length; i++){
            add(t[i]);
        }
    }

    /**
     * Adds the contents of the list to the heap
     * @param t the list we want to add to the heap
     */
    public void add(List<T> t){
        t.forEach(this::add);
    }

    /**
     * Removes and returns the top element of the heap.
     * @return object removed from heap
     */
    public T poll(){
        if (heap.size() == 0)
            throw new NullPointerException();
        return removeAt(0);
    }

    /**
     * Finds and removes object t from heap.
     * @param t object we want to remove
     */
    public void remove(T t){
        int index = indexOf(t);
        if (index == -1)
            throw new NoSuchElementException("Object could not be found.");
        else
            removeAt(indexOf(t));
    }

    public String toString(){
        StringBuilder builder = new StringBuilder();
        heap.forEach( (h) -> {
            builder.append(h.toString());
            if (h != heap.get(heap.size()-1))
                builder.append(", ");
        });
        return builder.toString();
    }

    public int size(){
        return heap.size();
    }

    /**
     * Helper method which returns 2*i+1, which corresponds to the left children node.
     * @param i int
     * @return int
     */
    private int leftChildOf(int i){
        if (i < 0)
            throw new IllegalArgumentException("Index can't be negative.");
        else if (i >= heap.size() || 2*i + 1 >= heap.size())
            throw new IndexOutOfBoundsException();
        else
            return 2*i + 1;
    }

    /**
     * Helper method which returns 2*i+2, which corresponds to the right children node.
     * @param i int
     * @return int
     */
    private int rightChildOf(int i){
        if (i < 0)
            throw new IllegalArgumentException("Index can't be negative.");
        else if (i >= heap.size() || 2*i + 2 >= heap.size())
            throw new IndexOutOfBoundsException();
        else
            return 2*i + 2;
    }

    /**
     * Removes the node at index i.
     * @param i int
     * @return Removed object
     */
    private T removeAt(int i){
        if (i < 0)
            throw new IllegalArgumentException("Index can't be negative.");
        else if (i >= heap.size())
            throw new IndexOutOfBoundsException();
        else{
            T t = heap.get(i);
            swap(i, heap.size()-1);
            heap.remove(heap.size()-1);

            //If there is no more elements in heap no need to do the following...
            if (heap.size() != 0){
                while (failsHeapInvarianceUp(i))
                    i = swap(i, parentOf(i));

                while (failsHeapInvarianceLeft(i) || failsHeapInvarianceRight(i)){
                    //If both children fail heap invariance we always choose the minimum of two.
                    //Note that minimumOfChildren returns left child by default if both children are equal.
                    if (failsHeapInvarianceLeft(i) && failsHeapInvarianceRight(i))
                        i = swap(i, minimumOfChildren(i));
                    else if (failsHeapInvarianceLeft(i))
                        i = swap(i, leftChildOf(i));
                    else
                        i = swap(i, rightChildOf(i));
                }
            }

            return t;
        }
    }

    /**
     * Helper method which returns (i-1)/2, which corresponds to the parent node.
     * @param i int
     * @return int
     */
    private int parentOf(int i) {
        if (i < 0)
            throw new IllegalArgumentException("Index can't be negative.");
        else if (i >= heap.size())
            throw new IndexOutOfBoundsException();
        else if( i == 0 )
            return 0;
        else
            return (i-1)/2;
    }

    /**
     * Returns the index of the minimum of two children, returns left if both children are equal.
     * @param i int
     * @return int
     */
    private int minimumOfChildren(int i) {
        if (heap.get(leftChildOf(i)).compareTo(heap.get(rightChildOf(i))) <= 0)
            return leftChildOf(i);
        else
            return rightChildOf(i);
    }

    /**
     * Returns true if the element i fails the heap invariance criteria when compared to its parent.
     * @param i the index of element we want to check heap invariance for
     * @return boolean
     */
    private boolean failsHeapInvarianceUp(int i) {
        if (i < 0)
            throw new IllegalArgumentException("Index can't be negative.");
        else if (i >= heap.size())
            throw new IndexOutOfBoundsException();
        //the top element always satisfies it
        else if (i == 0)
            return false;
        else
            return type.getType()*heap.get(i).compareTo(heap.get(parentOf(i))) > 0;
    }

    /**
     * Returns true if the element i fails the heap invariance criteria.
     * @param i the index of element we want to check heap invariance for
     * @return boolean
     */
    private boolean failsHeapInvarianceLeft(int i) {
        if (i < 0)
            throw new IllegalArgumentException("Index can't be negative.");
        else if (i >= heap.size())
            throw new IndexOutOfBoundsException();
        //If left children does not exist, it satisfies heap invariance
        else if (2*i + 1 >= heap.size())
            return false;
        else
            return type.getType()*heap.get(i).compareTo(heap.get(leftChildOf(i))) < 0;
    }

    /**
     * Returns true if the element i fails the heap invariance criteria.
     * @param i the index of element we want to check heap invariance for
     * @return boolean
     */
    private boolean failsHeapInvarianceRight(int i) {
        if (i < 0)
            throw new IllegalArgumentException("Index can't be negative.");
        else if (i >= heap.size())
            throw new IndexOutOfBoundsException();
        //If right children does not exist, it satisfies the heap invariance
        else if (2*i + 2 >= heap.size())
            return false;
        else
            return type.getType()*heap.get(i).compareTo(heap.get(rightChildOf(i))) < 0;
    }

    /**
     * Swaps the contents of index i and index j, returns j.
     * @param i index in range of [0, heap.size())
     * @param j index in range of [0, heap.size())
     * @return j int
     */
    private int swap(int i, int j) {
        if (i < 0 || j < 0)
            throw new IllegalArgumentException("Index can't be negative.");
        else if (i >= heap.size() || j >= heap.size())
            throw new IndexOutOfBoundsException();
        else if (i != j){
            T tmp = heap.get(i);
            heap.set(i, heap.get(j));
            heap.set(j, tmp);
        }
        return j;
    }

    private int indexOf(T t){
        for( int i = 0; i < heap.size(); i++){
            if (heap.get(i) == t)
                return i;
        }
        return -1;
    }
}
