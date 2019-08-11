package BinaryHeap;

public enum BinaryHeapType {
    MIN(-1),
    MAX(1);

    private int type;

    BinaryHeapType(int type) {
        this.type = type;
    }

    public int getType(){
        return type;
    }
}
