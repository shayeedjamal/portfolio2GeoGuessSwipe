package com.example.geoguessswipe;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<GeoObject> mGeoObjects;
    String answer;
    private GestureDetector mGestureDetector;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mGeoObjects = new ArrayList<>();

        for (int i = 0; i < GeoObject.PRE_DEFINED_GEO_OBJECT_NAMES.length; i++) {

            mGeoObjects.add(new GeoObject(GeoObject.PRE_DEFINED_GEO_OBJECT_NAMES[i],
                    GeoObject.PRE_DEFINED_GEO_OBJECT_IMAGE_IDS[i]));
        }

        final RecyclerView mGeoRecyclerView = findViewById(R.id.recyclerView);

        RecyclerView.LayoutManager mLayoutManager = new StaggeredGridLayoutManager(1, LinearLayoutManager.VERTICAL);

        mGeoRecyclerView.setLayoutManager(mLayoutManager);
        mGeoRecyclerView.setHasFixedSize(true);
        final GeoObjectAdapter mAdapter = new GeoObjectAdapter(this, mGeoObjects);
        mGeoRecyclerView.setAdapter(mAdapter);


        mGestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }
        });

        ItemTouchHelper.SimpleCallback simpleItemTouchCallback =
                new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
                    @Override
                    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                        return false;
                    }


                    //called when the user swipes on a Viewholder;
                    @Override
                    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int swipe) {
                        int position = (viewHolder.getAdapterPosition());
                        verifyAnswer(position, swipe);
                        mGeoObjects.remove(position);
                        mAdapter.notifyItemRemoved(position);
                    }


                };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(mGeoRecyclerView);

    }

    public String retrieveImageName(int index) {
        String answser = getResources().getResourceName(mGeoObjects.get(index).getmGeoImageName());
        answser = answser.substring(answser.indexOf("_") + 1, answser.lastIndexOf("_"));
        return answser;
    }


    public void verifyAnswer(int index, int swipeDirection) {
        answer = retrieveImageName(index);

        switch (swipeDirection) {
            case ItemTouchHelper.LEFT:
                if (answer.equals("yes")) {
                    rightAnswer();
                } else {
                    wrongAnswer();
                }
                break;
            case ItemTouchHelper.RIGHT:
                if (answer.equals("no")) {
                    rightAnswer();
                } else {
                    wrongAnswer();
                }
                break;
            default:
                break;
        }
    }

        public void rightAnswer() {
            Toast.makeText(this,"Correct", Toast.LENGTH_SHORT).show();
        }

        public void wrongAnswer() {
            Toast.makeText(this,"Wrong", Toast.LENGTH_SHORT).show();
        }



    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        View child = rv.findChildViewUnder(e.getX(), e.getY());
        int mAdapterPosition = rv.getChildAdapterPosition(child);
        if (child != null && mGestureDetector.onTouchEvent(e)) {
            Toast.makeText(this, mGeoObjects.get(mAdapterPosition).getmGeoName(), Toast.LENGTH_SHORT).show();
        }
        return false;

    }


    public void onTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {

    }


    public void onRequestDisallowInterceptTouchEvent(boolean b) {

    }




    }

