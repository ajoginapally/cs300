import java.util.Arrays;

public class Sorting {
    public static void bubbleSort(int[] a){
        int n=a.length; boolean swapped;
        for(int i=0;i<n-1;i++){
            swapped=false;
            for(int j=0;j<n-1-i;j++){
                if(a[j]>a[j+1]){int t=a[j]; a[j]=a[j+1]; a[j+1]=t; swapped=true;}
            }
            if(!swapped) break;
        }
    }

    public static void main(String[] args){
        int[] a = {5,2,9,1,5,6};
        System.out.println(Arrays.toString(a));
        bubbleSort(a);
        System.out.println(Arrays.toString(a));
        int[] b = {3,1,4,1,5,9};
        Arrays.sort(b);
        System.out.println(Arrays.toString(b));
    }
}