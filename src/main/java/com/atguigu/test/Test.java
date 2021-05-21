package com.atguigu.test;

import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
        int arr[] = {8, 5, 3, 2, 4};
        for(int i=0;i<arr.length;i++){
            for (int j=0;j<arr.length-i-1;j++){
                if(arr[j] > arr[j+1]){
                    int tmp = arr[j+1];
                    arr[j+1] = arr[j];
                    arr[j] = tmp;
                }
            }
            System.out.println(Arrays.toString(arr));
        }
    }
}
