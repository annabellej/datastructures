import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        HeapDisplay display = new HeapDisplay();
        HeapUtil util = new HeapUtil();
        Integer[] array = new Integer[12];
        array[1] = 7;
        array[2] = 43;
        array[3] = 12;
        array[4] = 25;
        array[5] = 9;
        array[6] = 5;
        array[7] = 87;
        array[8] = 45;
        array[9] = 66;
        array[10] = 2;
        display.displayHeap(array, 11);

        System.out.println("TYPE N TO PROCEED. NEXT METHOD: INSERT");
        Scanner scan = new Scanner(System.in);
        if (scan.hasNext() && scan.next().equals("N"));

        //insert a new element:
        util.insert(array, 90, 11);
        display.displayHeap(array, 11);

        System.out.println("TYPE N TO PROCEED. NEXT METHOD: REMOVE");
        scan = new Scanner(System.in);
        if (!scan.hasNext()) return;

        //remove root element:
        util.remove(array, 11);
        display.displayHeap(array, 11);

        System.out.println("TYPE N TO PROCEED. NEXT METHOD: BUILD HEAP (with new array)");
        scan = new Scanner(System.in);
        if (!scan.hasNext()) return;

        //display new array:
        array = new Integer[12];
        array[1] = 6;
        array[2] = 34;
        array[3] = 5;
        array[4] = 9;
        array[5] = 11;
        array[6] = 56;
        array[7] = 53;
        array[8] = 1;
        array[9] = 8;
        array[10] = 49;
        array[11] = 74;
        display.displayHeap(array, 11);

        System.out.println("TYPE N TO RUN BUILD HEAP");
        scan = new Scanner(System.in);
        if (!scan.hasNext()) return;

        //build new heap:
        util.buildHeap(array, 11);
        display.displayHeap(array, 11);

        System.out.println("TYPE N TO PROCEED. NEXT METHOD: HEAP SORT (with new array)");
        scan = new Scanner(System.in);
        if (!scan.hasNext()) return;

        array = new Integer[12];
        array[1] = 12;
        array[2] = 5;
        array[3] = 78;
        array[4] = 98;
        array[5] = 8;
        array[6] = 6;
        array[7] = 45;
        array[8] = 13;
        array[9] = 67;
        array[10] = 35;
        array[11] = 82;
        System.out.print("array: [");
        for (int i = 1; i <= 11; i++){
            if (i == 11) System.out.print(array[i]);
            else System.out.print(array[i] + ", ");
        }
        System.out.println("]");

        System.out.println("TYPE N TO RUN HEAP SORT");
        scan = new Scanner(System.in);
        if (!scan.hasNext()) return;

        util.heapSort(array, 11);
        System.out.print("array: [");
        for (int i = 1; i <= 11; i++){
            if (i == 11) System.out.print(array[i]);
            else System.out.print(array[i] + ", ");
        }
        System.out.println("]");

        System.out.println("TEST END");
    }
}
