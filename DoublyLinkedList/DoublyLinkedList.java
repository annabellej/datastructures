import java.util.Iterator;

public class DoublyLinkedList<E> implements MyList<E> {
    //instance variables
    private DoubleNode head;
    private int size;

    /**
     * Default constructor for objects of class DoublyLinkedList.
     */
    public DoublyLinkedList() {
        head = null;
        size = 0;
    }

    /**
     * Finds the number of elements in this DoublyLinkedList.
     * @return  the size of this DoublyLinkedList
     */
    public int size() {
        return size;
    }

    /**
     * Appends an element to the end of the list.
     * @param obj   the element to add to the list
     * @return  true
     */
    public boolean add(Object obj) {
        DoubleNode toAdd = new DoubleNode(obj);
        DoubleNode current = head;
        if (current==null)  {
            head = toAdd;
            size++;
            return true;
        }
        while (current.getNext() != null){
            current = current.getNext();
        }
        current.setNext(toAdd);
        toAdd.setPrevious(current);
        size++;
        return true;
    }

    /**
     * Inserts a given element at a given position and moves the rest of the elements to the right. Adjusts size.
     * @param index the position to insert the element
     * @param obj   the element to insert
     * @precondition    0 <= index <= size
     */
    public void add(int index, Object obj) {
        DoubleNode current = head;
        DoubleNode toAdd = new DoubleNode(obj);
        if (index == 0){
            toAdd.setNext(current);
            if (size != 0)  current.setPrevious(toAdd);
            head = toAdd;
            size++;
            return;
        }
        while (index != 0 && current.getNext() != null) {
            current = current.getNext();
            index--;
        }
        toAdd.setNext(current);
        if (current.getPrevious() != null) {
            toAdd.setPrevious(current.getPrevious());
            current.getPrevious().setNext(toAdd);
        }
        current.setPrevious(toAdd);
        size++;
    }

    /**
     * Finds the element at a give position.
     * @param index the position of the element to find
     * @return  the element at position index
     */
    public E get(int index) {
        if (index >= size)  return null;
        DoubleNode current = head;
        while (index!=0) {
            current = current.getNext();
            index--;
        }
        return (E) current.getValue();
    }

    /**
     * Replaces the element in the list with a new element.
     * @param index the position to replace the element at
     * @param obj   the element to replace into the list
     * @return  the element that used to be at position index
     */
    public Object set(int index, Object obj) {
        if (index >= size)  return null;
        DoubleNode toAdd = new DoubleNode(obj);
        DoubleNode old = head;
        int i = index;
        while (i!=0) {
            old = old.getNext();
            i--;
        }
        toAdd.setPrevious(old.getPrevious());
        toAdd.setNext(old.getNext());
        if (old.getPrevious() != null)  old.getPrevious().setNext(toAdd);
        if (old.getNext() != null)  old.getNext().setPrevious(toAdd);
        if (index == 0) head = toAdd;
        //old.setPrevious(null);
        //old.setNext(null);
        return old.getValue();
    }

    /**
     * Removes the element at a given position and shifts the other elements to the left. Adjusts the size.
     * @param index the position to remove at
     * @return  the element that has been deleted from the list
     */
    public E remove(int index) {
        if (index >= size)  return null;
        DoubleNode old = head;
        if (index==0){
            head = head.getNext();
            size--;
            return (E) old.getValue();
        }
        while (index!=0) {
            old = old.getNext();
            index--;
        }
        old.getPrevious().setNext(old.getNext());
        if (old.getNext()!=null)    old.getNext().setPrevious(old.getPrevious());
        //old.setPrevious(null);
        //old.setNext(null);
        size--;
        return (E) old.getValue();
    }

    @Override
    public String toString() {
        DoubleNode current = head;
        String result = "[";
        for (int i = 0; i < size; i++) {
            if (i == size - 1) result += current.getValue();
            else {
                result += current.getValue() + ", ";
            }
            current = current.getNext();
        }
        result += "]";
        return result;
    }

    /**
     * Iterates through this DoublyLinkedList.
     * @return  null
     */
    public Iterator<E> iterator() {
        return null;
    }

    /**
     * Iterates through this DoublyLinkedList.
     * @return  null
     */
    public MyListIterator<E> listIterator() {
        return null;
    }
}
