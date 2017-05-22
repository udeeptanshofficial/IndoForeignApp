package com.example.puff.finalproject.student;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.puff.finalproject.R;

public class MainDrawer extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }



   @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_upload) {
            getFragmentManager().beginTransaction().replace(R.id.content_main_drawer,new Upload()).addToBackStack(null).commit();
        } else if (id == R.id.nav_category) {
            getFragmentManager().beginTransaction().replace(R.id.content_main_drawer,new Categories()).addToBackStack(null).commit();

        } else if (id == R.id.nav_links) {
            getFragmentManager().beginTransaction().replace(R.id.content_main_drawer,new QuickLink()).addToBackStack(null).commit();
        } else if (id == R.id.nav_about) {
            getFragmentManager().beginTransaction().replace(R.id.content_main_drawer,new AboutUs()).addToBackStack(null).commit();

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }
        else if (id == R.id.nav_logout) {
            Intent loginscreen=new Intent(this,Splash.class);
            loginscreen.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(loginscreen);
        }
        else if(id == R.id.nav_home)
        {
            Intent i=new Intent(this,MainDrawer.class);
            startActivity(i);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    Intent intent;
    public void suggestCollege(View view){
        intent = new Intent(this,CollegeSuggestions.class);
        startActivity(intent);

    }
    public void requestStatus(View view){
        intent = new Intent(this,ApplicationList.class);
        startActivity(intent);
    }
    public void topSearches(View view){
        intent = new Intent(this,TopSearch.class);
        startActivity(intent);
    }
    public void rateAgent(View view){
        intent = new Intent(this,AgentRating.class);
        startActivity(intent);
    }


}
