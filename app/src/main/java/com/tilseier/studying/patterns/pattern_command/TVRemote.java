package com.tilseier.studying.patterns.pattern_command;

public class TVRemote {

    public static ElectronicDevice getDevice(){

        return new Television();

    }

}