package com.example.beetrootapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //navigation drawer
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView navigationView = (NavigationView)findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        //bottom navigation
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.nav_cart);
        bottomNavigationView.setOnNavigationItemSelectedListener( navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new MapFragment()).commit();
    }
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment selectedFragment = null;

                    switch(menuItem.getItemId()){
                        case R.id.nav_cart:
                            selectedFragment = new MapFragment();
                            break;
                        case R.id.nav_proximity:
                            selectedFragment = new ProximityFragment();
                            break;
                        case R.id.nav_favorites:
                            selectedFragment = new FavoritesFragment();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();
                    return true;
                }

            };

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(actionBarDrawerToggle.onOptionsItemSelected((item)))
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

   @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item)
   {
       switch(item.getItemId())
       {
           case R.id.home:
               getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                       new MapFragment()).commit();
               break;
           case R.id.myFarm:
               getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                       new FarmFragment()).commit();
               break;
           case R.id.harvestProximity:
               getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                       new HarvestFragment()).commit();
               break;
           case R.id.settings:
               getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                       new SettingsFragment()).commit();
               break;
            //déconnection

       }
       drawerLayout.closeDrawer(GravityCompat.START);
       return false;
   }
}
