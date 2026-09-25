package Practice;

import java.util.ArrayList;

public class practice_methods {

    public static int[] removeDuplicates(int[] arr) {
        if (arr.length == 0 || arr == null) {
            return new int[0];
        }
        int[] temp = new int[arr.length];
        int len = 0;
        for (int x: arr) {
            boolean found = false;
            for (int y : temp) {
                if (x == y) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                temp[len++] = x;
            }
        }
        
        int[] result = new int[len];
        for (int i = 0; i < len; i++) {
            result[i] = temp[i];
        }
        return result;
    }

    public static int removeFirst(int[] data, int size, int target) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (data[i] == target) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            return size;
        }
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        return size - 1;
    }

    


    public static void main(String[] args) {
        int[] arr = {1, 2, 2, 3, 4, 4, 5, 5, 5};
        int[] result = removeDuplicates(arr);
        // for (int i : result) {
        //     System.out.print(i + " ");
        // }


    }
}
