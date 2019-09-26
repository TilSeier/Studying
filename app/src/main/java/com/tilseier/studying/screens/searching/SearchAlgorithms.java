package com.tilseier.studying.screens.searching;

public class SearchAlgorithms {

    public int binarySearchItemIndex(int[] mas, int num){

        int size = mas.length;
        int first = 0;
        int last = size - 1;

        while (first <= last){

            int center = (first + last) / 2;

            if(mas[center] == num){
                return center;
            }else if(mas[center] > num){
                last = center - 1;
            } else {
                first = center + 1;
            }

        }
        return -1;

    }

    public String binarySearchSquare(double num){

        double accuracy = 0.01;
        double first = 0.0;
        double last = num;
        int guesses = 0;
        double guess = (last + first) / 2.0;

        while (Math.abs((Math.pow(guess, 2)) - num) >= accuracy){

            if (Math.pow(guess, 2) < num){
                first = guess;
            } else {
                last = guess;
            }
            guess = (last + first) / 2.0;

            guesses += 1;
        }

        return guess + "is close enough to square root of " + num + "Guesses: " + guesses;

    }

}
