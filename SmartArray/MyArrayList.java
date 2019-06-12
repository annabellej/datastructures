import java.util.Iterator;

/**
 * MyArrayList adds functions to a normal array. Like arrays, it has random access. However, it is also flexible
 * in its size. The user can add and delete elements freely and  MyArrayList will adjust accordingly.
 *
 * @author Annabelle Ju
 * @version May 14, 2019
 *
 */
public class MyArrayList<E> implements MyList<E> {
    //instance variables
    private Object[] list;
    private int size;

    /**
     * Default constructor for objects of class MyArrayList.
     */
    public MyArrayList() {
        list = null;
        size = 0;
    }

    /**
     * Finds the number of elements in this MyArrayList.
     * @return  the size of this MyArrayList
     */
    public int size() {
        return size;
    }

    /**
     * Appends an element to the end of the list.
     * @param obj the element to add to the list
     * @return  true
     */
    public boolean add(Object obj) {
        if (list == null) {
            list = new Object[1];
            list[0] = obj;
        }
        else {
            int len = list.length;
            Object[] result = new Object[len+1];
            for (int i = 0; i < len; i++){
                result[i] = list[i];
            }
            result[len] = obj;
            list = result;
        }
        size += 1;
        return true;
    }

    /**
     * Inserts a given element at a given position and moves the rest of the elements to the right. Adjusts size.
     * @param index the position to insert the element
     * @param obj the element to insert
     * @precondition    0 <= index <= size
     */
    public void add(int index, Object obj) {
        if (list == null) {
            list = new Object[1];
            list[0] = obj;
        }
        else if (index == size) {
            add(obj);
        }
        else {
            int len = list.length;
            Object[] result = new Object[len+1];
            int i = 0;
            while (i != index){
                result[i] = list[i];
                i++;
            }
            result[i] = obj;
            for (int j = i+1; j < len+1; j++){
                result[j] = list[j-1];
            }
            list = result;
        }
        size += 1;
    }

    /**
     * Finds the element at a give position.
     * @param index the position of the element to find
     * @return  the element at position index
     */
    public E get(int index) {
        if (index >= size || index < 0) {
            return null;
        }
        return (E) list[index];
    }

    /**
     * Replaces the element in the list with a new element.
     * @param index the position to replace the element at
     * @param obj   the element to replace into the list
     * @return  the element that used to be at position index
     */
    public Object set(int index, Object obj) {
        if (index >= size || index < 0) {
            return null;
        }
        Object oldElement = list[index];
        list[index] = obj;
        return oldElement;
    }

    /**
     * Removes the element at a given position and shifts the other elements to the left. Adjusts the size.
     * @param index the position to remove at
     * @return  the element that has been deleted from the list
     */
    public E remove(int index) {
        if (index >= size || index < 0) {
            return null;
        }
        Object oldElement = list[index];
        int len = list.length;
        Object[] result = new Object[len-1];
        int j = 0;
        for (int i = 0; i < len-1; i++) {
            if (i == j && i == index) {
                j++;
                i--;
                continue;
            }
            result[i] = list[j];
            j++;
        }
        list = result;
        size -= 1;
        return (E) oldElement;
    }

    /**
     * @insertDoc
     */
    @Override
    public String toString() {
        String result = "[";
        for (int i = 0; i < size; i++) {
            if (i == size - 1) result += list[i];
            else {
                result += list[i] + ", ";
            }
        }
        result += "]";
        return result;
    }

    /**
     * Iterates through this MyArrayList.
     * @return  null
     */
    public Iterator<E> iterator() {
        return null;
    }

    /**
     * Iterates through this MyArrayList.
     * @return  null
     */
    public MyListIterator<E> listIterator() {
        return null;
    }
}
