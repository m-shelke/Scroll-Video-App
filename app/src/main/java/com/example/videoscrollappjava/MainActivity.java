package com.example.videoscrollappjava;

import android.os.Bundle;
import android.view.WindowManager;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.videoscrollappjava.databinding.ActivityMainBinding;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    ArrayList<Video> videoArrayList;
    VideoAdapter adapter;

    FirebaseVideoAdapter firebaseVideoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

       // getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

//        Video video1 = new Video("https://firebasestorage.googleapis.com/v0/b/creative-cart-app.appspot.com/o/Video%2F1751475660803.mp4?alt=media&token=93cc6765-ed9d-4e3a-91e4-a5582e4f5231","Title Of Video1", "support.microsoft.com’s server IP address could not be found.");
//       Video video2 = new Video("https://firebasestorage.googleapis.com/v0/b/creative-cart-app.appspot.com/o/Video%2F1751475660803.mp4?alt=media&token=93cc6765-ed9d-4e3a-91e4-a5582e4f5231","Title Of Video2", "support.microsoft.com’s server IP address could not be found.");
//        Video video3 = new Video("https://firebasestorage.googleapis.com/v0/b/creative-cart-app.appspot.com/o/Video%2F1751475660803.mp4?alt=media&token=93cc6765-ed9d-4e3a-91e4-a5582e4f5231","Title Of Video3", "support.microsoft.com’s server IP address could not be found.");
//
//        videoArrayList = new ArrayList<>();
//
//        videoArrayList.add(video1);
//        videoArrayList.add(video2);
//        videoArrayList.add(video3);
//
//        adapter = new VideoAdapter(videoArrayList);
//
//        binding.viewPager.setAdapter(new VideoAdapter(videoArrayList));


        FirebaseRecyclerOptions<FirebaseVideo> options = new FirebaseRecyclerOptions.Builder<FirebaseVideo>()
                .setQuery(FirebaseDatabase.getInstance().getReference("Video"),FirebaseVideo.class)
                        .build();

        firebaseVideoAdapter = new FirebaseVideoAdapter(options);

        binding.viewPager.setAdapter(firebaseVideoAdapter);

    }

    @Override
    protected void onStart() {
        firebaseVideoAdapter.startListening();
        super.onStart();
    }

    @Override
    protected void onResume() {
        firebaseVideoAdapter.startListening();
        super.onResume();
    }

    @Override
    protected void onStop() {
        firebaseVideoAdapter.stopListening();
        super.onStop();
    }
}