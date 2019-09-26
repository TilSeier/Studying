package com.tilseier.studying.screens.observer;

public class JobSearch {

    public void findJob(){

        JavaDeveloperJobSite jobSite = new JavaDeveloperJobSite();

        jobSite.addVacancy("First Java Position");
        jobSite.addVacancy("Second Java Position");

        Observer firstSubscriber = new Subscriber("Gabby Wong");
        Observer secondSubscriber = new Subscriber("Lusy Baringstone");

        jobSite.addObserver(firstSubscriber);
        jobSite.addObserver(secondSubscriber);

        jobSite.addVacancy("Third Java Position");

        jobSite.removeVacancy("First Java Position");

        jobSite.removeObserver(secondSubscriber);

        jobSite.addVacancy("Forth Java Position");

    }

}
