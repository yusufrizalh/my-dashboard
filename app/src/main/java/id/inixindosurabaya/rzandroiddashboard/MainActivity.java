package id.inixindosurabaya.rzandroiddashboard;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.TypedArray;
import android.os.Build;
import android.os.Bundle;


public class MainActivity extends AppCompatActivity {
    // GLOBAL VARIABLES
    // berisi komponen yang digunakan dalam layout
    Toolbar tool_bar;
    DrawerLayout drawerLayout;
    RecyclerView recyclerView;
    ActionBarDrawerToggle drawerToggle;
    RecyclerView.Adapter recyclerViewAdapter;
    String navTitles[];     // nama menu
    TypedArray navIcons;    // icon menu

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // konfigurasi toolbar
        setupToolbar();

        // mengenali semua komponen
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        drawerLayout = (DrawerLayout)findViewById(R.id.drawerMainActivity);
        // mengenali nama menu dan icon
        navTitles = getResources()
                .getStringArray(R.array.navDrawerItems);
        navIcons = getResources()
                .obtainTypedArray(R.array.navDrawerIcons);
        // mengenali recyclerview adapter
        recyclerViewAdapter =
                new RecyclerViewAdapter(navTitles, navIcons, this);
        recyclerView.setAdapter(recyclerViewAdapter);

        // ---------------------------------------

        // meletakkan array menu dan icon kedalam recyclerview
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // menempatkan toggle
        setupDrawerToggle();

        // menu yg menjadi tampilan pertama
        Fragment fragment1 = new Fragment_1();
        FragmentTransaction transaction =
                getSupportFragmentManager()
                .beginTransaction();
        transaction.replace(R.id.containerView,
                fragment1, null);
        transaction.commit();
    }

    private void setupDrawerToggle() {
        drawerToggle = new ActionBarDrawerToggle(
                this, drawerLayout, tool_bar,
                R.string.app_name, R.string.app_name
        );
        drawerToggle.syncState();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setupToolbar() {
        tool_bar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(tool_bar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }
}
