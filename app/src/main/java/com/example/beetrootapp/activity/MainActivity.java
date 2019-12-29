package com.example.beetrootapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.beetrootapp.fragment.FavoritesFragment;
import com.example.beetrootapp.fragment.MapFragment;
import com.example.beetrootapp.fragment.ProximityFragment;
import com.example.beetrootapp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    protected DrawerLayout drawerLayout;
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
               Intent openFarmActivity = new Intent(this, FarmActivity.class);
               startActivity(openFarmActivity);
               break;
           case R.id.harvestProximity:
               Intent openHarvestActivity = new Intent(this, HarvestActivity.class);
               startActivity(openHarvestActivity);
               break;
           case R.id.settings:
               Intent openUserActivity = new Intent(this, UserInfoActivity.class);
               startActivity(openUserActivity);
               break;
            // TODO : déconnection

       }
       drawerLayout.closeDrawer(GravityCompat.START);
       return false;
   }
}
