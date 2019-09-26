package com.tilseier.studying.screens.sorting;

import java.util.ArrayList;
import java.util.Arrays;

public class SortAlgorithms {

    private int[] arr;

    public void setArray(int[] array) {
        arr = array;
    }

    public int[] getArray() {
        return arr;
    }

    ////O(n)
    public int[] selectSort(int[] mas, boolean b){
        int temp;
        int tempMinMaxIndex;
        for (int i = 0; i < mas.length - 1; i++){
            tempMinMaxIndex = i;
            for (int j = i+1; j < mas.length; j++){
                if((b && mas[tempMinMaxIndex] > mas[j]) || (!b && mas[tempMinMaxIndex] < mas[j])){
                    tempMinMaxIndex = j;
                }
            }
            if(tempMinMaxIndex!=i){
                temp = mas[i];
                mas[i] = mas[tempMinMaxIndex];
                mas[tempMinMaxIndex] = temp;
            }
        }

        return mas;
    }

    //O(n^2)
    public int[] bubbleSort(int[] mas, boolean b){
        int temp;

        for (int i = 0; i < mas.length; i++){
            for (int j = 0; j < mas.length - i - 1; j++){
                if((b && mas[j] > mas[j+1]) || (!b && mas[j] < mas[j+1])){
                    temp = mas[j];
                    mas[j] = mas[j+1];
                    mas[j+1] = temp;
                }
            }
        }

        return mas;
    }

    //O(n^2)
    public int[] insertSort(int[] mas, boolean b){
//        int temp;
//        for (int i = 0; i < mas.length - 1; i++){
//            for (int j = i+1; j > 0; j--){
//                if((b && mas[j] < mas[j-1]) || (!b && mas[j] > mas[j-1])){
//                    temp = mas[j];
//                    mas[j] = mas[j-1];
//                    mas[j-1] = temp;
//                }else {
//                    break;
//                }
//            }
//        }

        //OR

        for (int i = 1; i < mas.length; i++){
            int cur = mas[i];
            int j = i;
            while (j > 0 && ((b && cur < mas[j - 1]) || (!b && cur > mas[j - 1]))){
                mas[j] = mas[j - 1];
                j--;
            }
            mas[j] = cur;
        }


        return mas;
    }

    //O(n log n)
    public int[] margeSort(int[] mas){
        int[] tmp;
        int[] curentSrc = mas;
        int[] currentDest = new int[mas.length];

        int size = 1;
        while (size < mas.length){
            for (int i = 0; i < mas.length; i += 2 * size){
                marge(curentSrc, i, curentSrc, i + size, currentDest, i, size);
            }

            tmp = curentSrc;
            curentSrc = currentDest;
            currentDest = tmp;

            size = size * 2;

//            System.out.println(Arrays.toString(curentSrc));
        }
        return curentSrc;
    }

    private void marge(int[] src1, int src1Start, int[] src2, int src2Start, int[] dest, int destStart, int size) {
        int index1 = src1Start;
        int index2 = src2Start;

        int src1End = Math.min(src1Start + size, src1.length);
        int src2End = Math.min(src2Start + size, src2.length);

        int iterationCount = src1End - src1Start + src2End - src2Start;

        for (int i = destStart; i < destStart + iterationCount; i++){
            if (index1 < src1End && (index2 >= src2End || src1[index1] < src2[index2])){
                dest[i] = src1[index1];
                index1++;
            } else {
                dest[i] = src2[index2];
                index2++;
            }
        }
    }

    //O(n log n) ; O(n^2)
    public void quickSort(int[] mas, int first, int last){

//        int centerIndex = (int) Math.ceil(mas.length/2);
//        int center = mas[(int)Math.ceil(mas.length/2)];
//        int center = mas[(int)Math.ceil((first + last) / 2)];
        int center = mas[(first + last) / 2];
        int i = first, j = last;
        int temp;

        do{

            while (mas[i] < center && i < last) i++;
            while (mas[j] > center && j > first) j--;

            if(i <= j){
                if(mas[i] > mas[j]){
                    temp = mas[j];
                    mas[j] = mas[j];
                    mas[j] = temp;
                }
                i++;
                j--;
            }

        } while (i <= j);

        if(i < last)
            quickSort(mas, i, last);
        if (first < j)
            quickSort(mas, first, j);
    }

    //Основуэться на принципі "Розділяй та володарюй"
    public void doSort(int[] mas, int start, int end, boolean b) {
        if (start >= end)
            return;
        int i = start, j = end;
        int cur = i - (i - j) / 2;
        while (i < j) {
            while (i < cur && ((b && mas[i] <= mas[cur])) || (!b && mas[i] >= mas[cur])) {
                i++;
            }
            while (j > cur && ((b && mas[cur] <= mas[j]) || (!b && mas[cur] >= mas[j]))) {
                j--;
            }
            if (i < j) {
                int temp = mas[i];
                mas[i] = mas[j];
                mas[j] = temp;
                if (i == cur)
                    cur = j;
                else if (j == cur)
                    cur = i;
            }
        }
//        System.out.println(
//                "mas = " + Arrays.toString(mas) +
//                "; start = " + start +
//                "; end = " + end +
//                "; i = " + i +
//                "; j = " + j +
//                "; cur = " + cur);
        doSort(mas, start, cur, b);
        doSort(mas,cur+1, end, b);
    }

    //Порозрядне сортування
    public int[] radixSort2(int[] mas){

        int RADIX = 10;
        ArrayList[] bucketsArray = new ArrayList[RADIX];
        for (int count = 0; count < bucketsArray.length; count++){
            bucketsArray[count] = new ArrayList<>();
        }

        boolean maxDigitsLenghth = false;
        int temp = -1, placeValue = 1;
        while (!maxDigitsLenghth){
            maxDigitsLenghth = true;
            for (Integer element: mas){
                temp = element / placeValue;
                bucketsArray[temp%RADIX].add(element);
                if (maxDigitsLenghth && temp > 0){
                    maxDigitsLenghth = false;
                }
            }

            int a = 0;
            for (int b = 0; b < RADIX; b++){
                for (Object i: bucketsArray[b]){
                    mas[a++] = (int) i;
                }
                bucketsArray[b].clear();
            }
            placeValue = placeValue * RADIX;
        }
        return mas;

    }


    // The main function to that sorts arr[] of size n using
    // Radix Sort
    public void radixSort(int[] mas)
    {
        int n = mas.length;
        // Find the maximum number to know number of digits
        int m = getMax(mas, n);

        // Do counting sort for every digit. Note that instead
        // of passing digit number, exp is passed. exp is 10^i
        // where i is current digit number
        for (int exp = 1; m/exp > 0; exp *= 10)
            countSort(mas, n, exp);
    }

    // A utility function to get maximum value in arr[]
    private int getMax(int[] mas, int n)
    {
        int mx = mas[0];
        for (int i = 1; i < n; i++)
            if (mas[i] > mx)
                mx = mas[i];
        return mx;
    }

    // A function to do counting sort of arr[] according to
    // the digit represented by exp.
    private void countSort(int[] mas, int n, int exp)
    {
        int[] output = new int[n]; // output array
        int i;
        int[] count = new int[10];
        Arrays.fill(count,0);

        // Store count of occurrences in count[]
        for (i = 0; i < n; i++)
            count[ (mas[i]/exp)%10 ]++;

//        System.out.println("Count: " + Arrays.toString(count));

        // Change count[i] so that count[i] now contains
        // actual position of this digit in output[]
        for (i = 1; i < 10; i++)
            count[i] += count[i - 1];

//        System.out.println("Count 2: " + Arrays.toString(count));

        // Build the output array
        for (i = n - 1; i >= 0; i--)
        {
            output[count[ (mas[i]/exp)%10 ] - 1] = mas[i];
//            System.out.print(" | " + (count[ (mas[i]/exp)%10 ] - 1) + " = " + mas[i] + " ");
            count[ (mas[i]/exp)%10 ]--;
        }

//        System.out.println();
//        System.out.println("Count 3: " + Arrays.toString(count));
//        System.out.println("Output: " + Arrays.toString(output));

        // Copy the output array to arr[], so that arr[] now
        // contains sorted numbers according to curent digit
        for (i = 0; i < n; i++)
            mas[i] = output[i];
    }

}
