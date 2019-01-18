package io.kirikcoders.bitcse;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.kirikcoders.bitcse.events.CreateEventActivity;
import io.kirikcoders.bitcse.events.EventAdapter;
import io.kirikcoders.bitcse.events.myEventsAdapter;
import io.kirikcoders.bitcse.MainActivity;
/**
 * Created by Kartik on 24-Jul-18.
 */
public class EventFragment extends Fragment {
    private FloatingActionButton floatingActionButton;
    private RecyclerView recyclerView;
    private TabLayout tabLayout;
    private LottieAnimationView lottieAnimationView;
    private TextView errorMessageTextView;
    private EventAdapter adapter;
    private  myEventsAdapter ad;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_event, container, false);
        floatingActionButton = rootView.findViewById(R.id.add_event_fab);
        recyclerView = rootView.findViewById(R.id.event_recycler_view);
        tabLayout = rootView.findViewById(R.id.tabLayout);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab.getText().toString().equals("Current")){
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }
                else if(tab.getText().toString().equals("My Events")){

                    recyclerView.setAdapter(ad);
                    ad.notifyDataSetChanged();

                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        errorMessageTextView = rootView.findViewById(R.id.errorMessage);
        lottieAnimationView = rootView.findViewById(R.id.loadingAnimation);
        floatingActionButton.setOnClickListener((view) -> startActivity(new Intent(getActivity(), CreateEventActivity.class)));
        return rootView;
    }

    public void setupRecyclerView(EventAdapter adapter) {
        this.adapter=adapter;
        if (recyclerView != null) {
            errorMessageTextView.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setAdapter(adapter);
            lottieAnimationView.setVisibility(View.GONE);
            adapter.notifyDataSetChanged();
        }
    }

    public void setErrorMessage(String message) {
        errorMessageTextView.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
        errorMessageTextView.setText(message);
    }

    public void setMyEventsAdapter(myEventsAdapter ad) {
        this.ad=ad;


    }
}

