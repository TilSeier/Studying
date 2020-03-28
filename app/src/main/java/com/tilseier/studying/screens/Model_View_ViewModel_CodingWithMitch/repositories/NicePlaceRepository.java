package com.tilseier.studying.screens.Model_View_ViewModel_CodingWithMitch.repositories;

import com.tilseier.studying.screens.Model_View_ViewModel_CodingWithMitch.models.NicePlace;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.MutableLiveData;

/*
 *Singleton pattern
 */
public class NicePlaceRepository {

    private static NicePlaceRepository instance;
    private ArrayList<NicePlace> dataSet = new ArrayList<>();

    public static NicePlaceRepository getInstance(){
        if (instance == null) {
            instance = new NicePlaceRepository();
        }
        return instance;
    }

    //Pretend to get data from a webservice or online source
    public MutableLiveData<List<NicePlace>> getNicePlaces(){
        setNicePlaces();//instead of retrieve data from the data source

        MutableLiveData<List<NicePlace>> data = new MutableLiveData<>();
        data.setValue(dataSet);
        return data;
    }

    private void setNicePlaces(){
        //instead of retrieve data from the data source
        dataSet.add(new NicePlace("Title 1",
                "https://images.unsplash.com/photo-1503023345310-bd7c1de61c7d?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1000&q=60"));
        dataSet.add(new NicePlace("Title 2",
                "https://www.gettyimages.com/gi-resources/images/500px/983794168.jpg"));
        dataSet.add(new NicePlace("Title 3",
                "https://www.gettyimages.com/gi-resources/images/500px/983794168.jpg"));
        dataSet.add(new NicePlace("Title 4",
                "https://www.gettyimages.com/gi-resources/images/500px/983794168.jpg"));
        dataSet.add(new NicePlace("Title 5",
                "https://www.gettyimages.com/gi-resources/images/500px/983794168.jpg"));
        dataSet.add(new NicePlace("Title 6",
                "https://www.gettyimages.com/gi-resources/images/500px/983794168.jpg"));
        dataSet.add(new NicePlace("Title 7",
                "https://www.gettyimages.com/gi-resources/images/500px/983794168.jpg"));
        dataSet.add(new NicePlace("Title 8",
                "https://www.gettyimages.com/gi-resources/images/500px/983794168.jpg"));
        dataSet.add(new NicePlace("Title 9",
                "https://i.pinimg.com/originals/af/18/54/af1854b640dc6e65046e6663f0ac51a0.jpg"));
        dataSet.add(new NicePlace("Title 10",
                "https://www.gettyimages.com/gi-resources/images/500px/983794168.jpg"));
        dataSet.add(new NicePlace("Title 11",
                "https://www.gettyimages.com/gi-resources/images/500px/983794168.jpg"));
        dataSet.add(new NicePlace("Title 12",
                "https://www.gettyimages.com/gi-resources/images/500px/983794168.jpg"));
    }

}
