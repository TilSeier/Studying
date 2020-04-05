package com.tilseier.studying.patterns.pattern_mediator;

public class JTPoorman extends Colleague{

    public JTPoorman(Mediator newMediator) {
        super(newMediator);

        System.out.println("JT Poorman signed up with the stockexchange\n");

    }

}