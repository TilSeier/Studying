package com.tilseier.studying.screens.Model_View_ViewModel_CodingWithMitch.viewmodels;

import android.os.AsyncTask;

import com.tilseier.studying.screens.Model_View_ViewModel_CodingWithMitch.models.NicePlace;
import com.tilseier.studying.screens.Model_View_ViewModel_CodingWithMitch.repositories.NicePlaceRepository;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

//OR MitchMVVMActivityViewModel
public class NicePlacesViewModel extends ViewModel {

    //LiveData - can't be changed directly, only observed
    //MutableLiveData - can be changed and observed

    private MutableLiveData<List<NicePlace>> mNicePlaces;
    private NicePlaceRepository mRepo;
    private MutableLiveData<Boolean> mIsUpdating = new MutableLiveData<>();

    public void init(){
        if (mNicePlaces != null) {
            return;
        }
        mRepo = NicePlaceRepository.getInstance();
        mNicePlaces = mRepo.getNicePlaces();
    }

    public void addNewValue(final NicePlace nicePlace){
        mIsUpdating.setValue(true);

        new AsyncTask<Void, Void, Void>(){
            @Override
            protected void onPostExecute(Void aVoid) {//executes second
                super.onPostExecute(aVoid);
                List<NicePlace> currentPlaces = mNicePlaces.getValue();
                currentPlaces.add(nicePlace);
                mNicePlaces.postValue(currentPlaces);
                mIsUpdating.setValue(false);
            }

            @Override
            protected Void doInBackground(Void... voids) {//executes first
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute();
    }

    public LiveData<List<NicePlace>> getNicePlaces(){
        return mNicePlaces;
    }

    public LiveData<Boolean> getIsUpdating(){
        return mIsUpdating;
    }

}
