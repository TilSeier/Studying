package com.tilseier.studying.screens.throwable;

public class StackOverflowErrorExample {

    //https://stackoverflow.com/questions/214741/what-is-a-stackoverflowerror
    //The most common case that can possibly exhaust a Java application’s
    //stack is recursion. In recursion, a method invokes itself during its execution.
    //Recursion is considered as a powerful general-purpose programming
    // technique, but must be used with caution, to avoid StackOverflowError.

    public static void recursivePrint(int num) {
        System.out.println("Number: " + num);

        if(num == 0)
            return;
        else
            recursivePrint(++num);
    }

    public static void main(String[] args) {
        StackOverflowErrorExample.recursivePrint(1);
    }
}