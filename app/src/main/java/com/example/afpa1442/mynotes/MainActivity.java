package com.example.afpa1442.mynotes;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.afpa1442.mynotes.classes.Project;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Project> projects = new ArrayList<>();
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private NoteApplication application;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        application = (NoteApplication) getApplication();
        projects = application.getAllProjects();

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        Log.d("Projects", projects.toString());
        ArrayAdapter<Project> drawerAdapter = new ArrayAdapter<>(this, R.layout.left_drawer, projects);
        mDrawerList.setAdapter(drawerAdapter);

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.app_name, R.string.app_name) {
            public void onDrawerClosed(View view) {
                Log.d("HomeActivity", "onDrawerClosed");
            }

            public void onDrawerOpened(View drawerView) { Log.d("HomeActivity", "onDrawerOpened");}
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);





    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        int id = item.getItemId();
        application.setCurrentProject(id);
        application.buildNotesList(application.getCurrentProject().getId());
        return super.onOptionsItemSelected(item);
    }

}
