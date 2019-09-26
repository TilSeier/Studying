package com.tilseier.studying;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.tilseier.studying.adapters.MainMenuItemsAdapter;
import com.tilseier.studying.models.MainMenuItem;
import com.tilseier.studying.models.UserModel;
import com.tilseier.studying.screens.ai.emotion_recognizer.EmotionRecognizerActivity;
import com.tilseier.studying.screens.ai.face_recognizer.FaceRecognizerActivity;
import com.tilseier.studying.screens.collections.MyCollections;
import com.tilseier.studying.screens.constraint_features.ConstraintFeaturesActivity;
import com.tilseier.studying.screens.constraint_features.ConstraintSetsActivity;
import com.tilseier.studying.screens.dagger.DaggerActivity3;
import com.tilseier.studying.screens.eventbus.CustomMessageEvent;
import com.tilseier.studying.screens.eventbus.PublisherActivity;
import com.tilseier.studying.screens.files.FilesActivity;
import com.tilseier.studying.screens.fragment_state_loss.StateLossActivity;
import com.tilseier.studying.screens.fragments.FragmentActivity;
import com.tilseier.studying.screens.observer.JobSearch;
import com.tilseier.studying.screens.retrofit.RetrofitActivity;
import com.tilseier.studying.screens.rxjava.RxActivity;
import com.tilseier.studying.screens.save_instance_state.SaveStateActivity;
import com.tilseier.studying.screens.service.ServiceActivity;
import com.tilseier.studying.screens.sorting.SortAlgorithms;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

import timber.log.Timber;

public class MainActivity extends AppCompatActivity implements MainMenuItemsAdapter.MainMenuItemsListener {

    private static final String TAG = "StateLossActivity";

    RecyclerView rvMainMenu;
    MainMenuItemsAdapter adapter;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Timber.tag(TAG).e("onCreate");

        EventBus.getDefault().register(this);

        ArrayList<MainMenuItem> mainMenuItems = new ArrayList<>();

        mainMenuItems.add(new MainMenuItem("Services", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBtnServicesClick(view);
            }
        }, "onBtnServicesClick"));
        mainMenuItems.add(new MainMenuItem("Observer", view -> {
            int example = 0;
            onBtnObserverClick(view);
        }, "onBtnObserverClick"));
        
        mainMenuItems.add(new MainMenuItem("EventBus", this::onBtnEventBusClick, "onBtnEventBusClick"));
        mainMenuItems.add(new MainMenuItem("Singleton", this::onBtnSingletonClick, "onBtnSingletonClick"));
        mainMenuItems.add(new MainMenuItem("Sort", this::onBtnSortClick, "onBtnSortClick"));
        mainMenuItems.add(new MainMenuItem("Rx Java", this::onRxJavaClick, "onRxJavaClick"));
        mainMenuItems.add(new MainMenuItem("Dagger", this::onBtnDaggerClick, "onBtnDaggerClick"));
        mainMenuItems.add(new MainMenuItem("Retrofit", this::onBtnRetrofitClick, "onBtnRetrofitClick"));
        mainMenuItems.add(new MainMenuItem("Collection", this::onBtnCollectionClick, "onBtnCollectionClick"));
        mainMenuItems.add(new MainMenuItem("Fragments", this::onBtnFragmentsClick, "onBtnFragmentsClick"));
        mainMenuItems.add(new MainMenuItem("Files", this::onBtnFilesClick, "onBtnFilesClick"));
        mainMenuItems.add(new MainMenuItem("Constraint Feature", this::onBtnConstraintFeaturesClick, "onBtnConstraintFeaturesClick"));
        mainMenuItems.add(new MainMenuItem("Constraint Sets", this::onBtnConstraintSetsClick, "onBtnConstraintSetsClick"));
        mainMenuItems.add(new MainMenuItem("AI Emotions Recognizer", this::onBtnAIEmotionRecognizerClick, "onBtnAIEmotionRecognizerClick"));
        mainMenuItems.add(new MainMenuItem("AI Face Recognizer", this::onBtnAIFaceRecognizerClick, "onBtnAIFaceRecognizerClick"));

        mainMenuItems.add(new MainMenuItem("Save State + Parcelable", this::onBtnSaveStateClick, "onBtnSaveStateClick"));

        mainMenuItems.add(new MainMenuItem("Fragment StateLoss", this::onBtnStateLossClick, "onBtnStateLossClick"));

        rvMainMenu = findViewById(R.id.rv_main_menu);
        rvMainMenu.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        adapter = new MainMenuItemsAdapter(mainMenuItems, this);

        rvMainMenu.setLayoutManager(layoutManager);
        rvMainMenu.setAdapter(adapter);

//        adapter.setOnItemClickListener(new MainMenuItemsAdapter.MainMenuItemsListener() {
//            @Override
//            public void onItemClick(String methodName, View view) {
//
//                Timber.tag(TAG).e("onItemClick in the parameter");
//
//                Method method;
//                try {
//                    method = Class.forName("StateLossActivity").getMethod(methodName, View.class);
//                    method.invoke(this, view);
//                } catch (SecurityException | NoSuchMethodException ignored) {
//
//                } catch (IllegalAccessException | InvocationTargetException | ClassNotFoundException e) {
//                    e.printStackTrace();
//                }
//            }
//        });

    }

    @Subscribe
    public void onEvent(CustomMessageEvent event){
        Log.d(TAG, "Event fired = " + event.getCustomMessage());
        Toast.makeText(this, "Message from Activity: " + event.getCustomMessage(), Toast.LENGTH_LONG).show();
    }

    public void onBtnServicesClick(View view){
        Intent intent = new Intent(this, ServiceActivity.class);
        startActivity(intent);
    }

    public void onRxJavaClick(View view){
        Intent intent = new Intent(this, RxActivity.class);
        startActivity(intent);
    }

    public void onBtnObserverClick(View view){
        JobSearch jobSearch = new JobSearch();
        jobSearch.findJob();
    }

    public void onBtnSingletonClick(View view){
        Singleton singleton = Singleton.INSTANCE;
        singleton.i = 8;
        singleton.show("Singleton 1: ");

        Singleton singleton2 = Singleton.INSTANCE;
        singleton2.show("Singleton 2: ");

        Singleton singleton3 = Singleton.INSTANCE;
        singleton3.i = 12;
        singleton3.show("Singleton 3: ");
    }

    public void onBtnEventBusClick(View view){
        Intent intent = new Intent(this, PublisherActivity.class);
        startActivity(intent);
    }

    public void onBtnDaggerClick(View view){
        Intent intent = new Intent(this, DaggerActivity3.class);
        startActivity(intent);
    }

    public void onBtnSortClick(View view){

        //http://sorting.at/

//        testSortingAlgorithms();

        int[] arr = {3,2,6,78,4,5,23,0};
        int[] arr2 = {3,2,6,78,4,5,23,0};
        int[] arr3 = {3,2,6,78,4,5,23,0};
        int[] arr4 = {3,2,6,78,4,5,23,0};
//        int[] arr5 = {6,3,78,2,4,5,23,0,7,98,1,12,34};
        int[] arr5 = {6,5,1,3,8,4,7,9,2};
        int[] arr6 = {397,34623,921,24,346253,37,101,97,9,50,265,0,72,124,23,436,7567,2341,346346,3455,235236};

        SortAlgorithms sortAlgorithms = new SortAlgorithms();

        //Select Algorithm
        System.out.println("Select Algorithm Result ASC: " + Arrays.toString(sortAlgorithms.selectSort(arr, true)));
        System.out.println("Select Algorithm Result DESC: " + Arrays.toString(sortAlgorithms.selectSort(arr, false)));
        System.out.println("=================================================================");

        //Bubble Algorithm
        System.out.println("Bubble Algorithm Result ASC: " + Arrays.toString(sortAlgorithms.bubbleSort(arr2, true)));
        System.out.println("Bubble Algorithm Result DESC: " + Arrays.toString(sortAlgorithms.bubbleSort(arr2, false)));
        System.out.println("=================================================================");

        //Insert Algorithm
        System.out.println("Insert Algorithm Result ASC: " + Arrays.toString(sortAlgorithms.insertSort(arr3, true)));
        System.out.println("Insert Algorithm Result DESC: " + Arrays.toString(sortAlgorithms.insertSort(arr3, false)));
        System.out.println("=================================================================");

        //Marge Algorithm
        System.out.println("Marge Algorithm Result ASC: " + Arrays.toString(sortAlgorithms.margeSort(arr4)));
        System.out.println("Marge Algorithm Result DESC: " + Arrays.toString(sortAlgorithms.margeSort(arr4)));
        System.out.println("=================================================================");

        //Quick Algorithm
        sortAlgorithms.doSort(arr5, 0, arr5.length-1, true);
        System.out.println("Quick Algorithm Result ASC: " + Arrays.toString(arr5));
        sortAlgorithms.doSort(arr5, 0, arr5.length-1, false);
        System.out.println("Quick Algorithm Result DESC: " + Arrays.toString(arr5));
        System.out.println("=================================================================");

        //Radix Algorithm
        sortAlgorithms.radixSort(arr6);
        System.out.println("Radix Algorithm Result ASC: " + Arrays.toString(arr6));
//        sortAlgorithms.doSort(arr5, 0, arr5.length-1, false);
//        System.out.println("Quick Algorithm Result DESC: " + Arrays.toString(arr5));
        System.out.println("=================================================================");

    }

    private void testSortingAlgorithms(){
        int testLen = 1000;

        int[] arr1 = new int[testLen];
        int[] arr2 = new int[testLen];
        int[] arr3 = new int[testLen];
        int[] arr4 = new int[testLen];
        int[] arr5 = new int[testLen];
        int[] arr6 = new int[testLen];

        SortAlgorithms sortAlgorithms = new SortAlgorithms();

        System.out.println("\n========Random Array==============");

        for (int i = 0; i < testLen; i++){
            arr6[i] = arr5[i] = arr4[i] = arr3[i] = arr2[i] = arr1[i] = (int) Math.round(Math.random() * 10000);
        }

        System.out.println("Select sort");
//        measureTime(() -> sortAlgorithms.doSort(arr5, 0, arr5.length-1, true));
        long startTime = System.currentTimeMillis();
        sortAlgorithms.selectSort(arr1, true);
        long estimatedTime = System.currentTimeMillis() - startTime;
        System.out.println("Time: " + estimatedTime);

        System.out.println("Bubble sort");
        long startTime2 = System.currentTimeMillis();
        sortAlgorithms.bubbleSort(arr2, true);
        long estimatedTime2 = System.currentTimeMillis() - startTime2;
        System.out.println("Time: " + estimatedTime2);

        System.out.println("Insert sort");
        long startTime3 = System.currentTimeMillis();
        sortAlgorithms.insertSort(arr3, true);
        long estimatedTime3 = System.currentTimeMillis() - startTime3;
        System.out.println("Time: " + estimatedTime3);

        System.out.println("Marge sort");
        long startTime4 = System.currentTimeMillis();
        sortAlgorithms.margeSort(arr4);
        long estimatedTime4 = System.currentTimeMillis() - startTime4;
        System.out.println("Time: " + estimatedTime4);

        System.out.println("Quick sort");
        long startTime5 = System.currentTimeMillis();
        sortAlgorithms.doSort(arr5, 0, arr5.length-1, true);
        long estimatedTime5 = System.currentTimeMillis() - startTime5;
        System.out.println("Time: " + estimatedTime5);

        System.out.println("Radix sort");
        long startTime6 = System.currentTimeMillis();
        sortAlgorithms.radixSort(arr6);
        long estimatedTime6 = System.currentTimeMillis() - startTime6;
        System.out.println("Time: " + estimatedTime6);

    }

    public void onBtnRetrofitClick(View view){
        Intent intent = new Intent(this, RetrofitActivity.class);
        startActivity(intent);
    }

    public void onBtnFragmentsClick(View view){
        Intent intent = new Intent(this, FragmentActivity.class);
        startActivity(intent);
    }

    public void onBtnAIEmotionRecognizerClick(View view){
        Intent intent = new Intent(this, EmotionRecognizerActivity.class);
        startActivity(intent);
    }

    public void onBtnAIFaceRecognizerClick(View view){
        Intent intent = new Intent(this, FaceRecognizerActivity.class);
        startActivity(intent);
    }

    public void onBtnFilesClick(View view){
        Intent intent = new Intent(this, FilesActivity.class);
        startActivity(intent);
    }

    public void onBtnConstraintFeaturesClick(View view){
        Intent intent = new Intent(this, ConstraintFeaturesActivity.class);
        startActivity(intent);
    }

    public void onBtnConstraintSetsClick(View view){
        Intent intent = new Intent(this, ConstraintSetsActivity.class);
        startActivity(intent);
    }

    public void onBtnCollectionClick(View view){

        int[] arr = {3, 82, 29};

        MyCollections myCollections = new MyCollections();

        myCollections.arrayList(arr);
        myCollections.linkedList(arr);
        myCollections.hashSet(arr);
        myCollections.linkedHashSet(arr);
        myCollections.treeSet(arr);
        myCollections.sortedTreeSet(arr);

        myCollections.hashMap(arr);

        myCollections.testHashCode();

    }

    public void onBtnSaveStateClick(View view){
        Intent intent = new Intent(this, SaveStateActivity.class);

        intent.putExtra("user", new UserModel(1, 40, "Chris Pratt", "man"));
        intent.putExtra("activity", "StateLossActivity");
        startActivity(intent);
    }

    public void onBtnStateLossClick(View view){
        Intent intent = new Intent(this, StateLossActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Timber.tag(TAG).e("onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Timber.tag(TAG).e("onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Timber.tag(TAG).e("onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Timber.tag(TAG).e("onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Timber.tag(TAG).e("onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Timber.tag(TAG).e("onRestart");
    }



    @Override
    public void onItemClick(String methodName, View view) {

        Timber.tag(TAG).e("onItemClick in the class");

        Method method;
        try {
            method = this.getClass().getMethod(methodName, View.class);
            method.invoke(this, view);
        } catch (SecurityException | NoSuchMethodException ignored) {

        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

    }

}

enum Singleton{
    INSTANCE;
    int i;
    void show(String str){
        System.out.println(str+i);
    }
}
