package com.tilseier.studying.patterns.pattern_command;

// This is known as the invoker
// It has a method press() that when executed
// causes the execute method to be called

// The execute method for the Command interface then calls
// the method assigned in the class that implements the
// Command interface

public class DeviceButton{

//    private static List<Command> commands = new LinkedList<>();
    Command theCommand;

    public DeviceButton(Command newCommand){

        theCommand = newCommand;

    }

    public void press(){

//        commands.add(0, theCommand);
        theCommand.execute();

    }

    // Now the remote can undo past commands

    public void pressUndo(){

//        commands.get(0).undo();
//        commands.remove(0);
        theCommand.undo();

    }

}