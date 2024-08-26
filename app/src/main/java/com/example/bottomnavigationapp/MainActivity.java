package com.example.bottomnavigationapp;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        loadFragment(new AccountFragment(),true);

        FrameLayout frameLayout = findViewById(R.id.frameLayout);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottonNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int itemId = item.getItemId();

                if (itemId == R.id.menuHome) {
                    loadFragment(new HomeFragment(),false);
                } else if (itemId == R.id.menuVariety) {
                    loadFragment(new VarietyFragment(),false);
                } else if (itemId == R.id.menuCard) {
                    loadFragment(new CardFragment(),false);
                } else {
                    loadFragment(new AccountFragment(),false);
                }
                return true;
            }
        });

    }

    public void loadFragment(Fragment fragment, boolean check) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if (check) {
            fragmentTransaction.add(R.id.frameLayout, fragment);
        } else{
            fragmentTransaction.replace(R.id.frameLayout,fragment);
        }
        fragmentTransaction.commit();
    }
}