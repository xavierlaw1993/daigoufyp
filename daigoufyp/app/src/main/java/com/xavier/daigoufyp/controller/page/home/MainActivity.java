package com.xavier.daigoufyp.controller.page.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.google.inject.Inject;
import com.squareup.picasso.Picasso;
import com.xavier.daigoufyp.R;
import com.xavier.daigoufyp.controller.page.abs.AbsSpiceActivity;
import com.xavier.daigoufyp.controller.page.login.LoginActivity;
import com.xavier.daigoufyp.controller.page.order.MyOrderFragment;
import com.xavier.daigoufyp.controller.page.product.NewProductFragment;
import com.xavier.daigoufyp.model.User;

import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

@ContentView(R.layout.activity_main)
public class MainActivity extends AbsSpiceActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Inject
    User user;

    @InjectView(R.id.toolbar)
    Toolbar toolbar;

    @InjectView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @InjectView(R.id.nav_view)
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FacebookSdk.sdkInitialize(getApplicationContext());
        super.onCreate(savedInstanceState);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();


        navigationView.setNavigationItemSelectedListener(this);

        //default set home fragment
        if (savedInstanceState == null) {
            if (getIntent().hasExtra("TO_FRAGMENT")) {
                switch (getIntent().getIntExtra("TO_FRAGMENT", 0)) {
                    case R.id.nav_order:
                        navigationView.getMenu().performIdentifierAction(R.id.nav_order, 0);
                        break;
                    case R.id.nav_create_product:
                        navigationView.getMenu().performIdentifierAction(R.id.nav_create_product, 0);
                        break;
                }
            }
            navigationView.getMenu().performIdentifierAction(R.id.nav_home, 0);
        }

        View headerView = navigationView.getHeaderView(0);
        ImageView userIconImageView = (ImageView) headerView.findViewById(R.id.userIconImageView);
        TextView userNameTextView = (TextView) headerView.findViewById(R.id.userNameTextView);
        TextView userEmailTextView = (TextView) headerView.findViewById(R.id.userEmailTextView);
        try {
            Picasso.with(this)
                    .load(user.user_profile_pic)
                    .into(userIconImageView);
            userNameTextView.setText(user.user_name);
            userEmailTextView.setText(user.user_email);
        } catch (Exception ex) {
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Class fragmentClass;
        Fragment fragment = null;

        switch (item.getItemId()) {
            case R.id.nav_home:
                fragmentClass = HomeFragment.class;
                break;
            case R.id.nav_create_product:
                fragmentClass = NewProductFragment.class;
                break;
            case R.id.nav_create_shop:
                fragmentClass = HomeFragment.class;
                break;
            case R.id.nav_profile:
                fragmentClass = HomeFragment.class;
                break;
            case R.id.nav_order:
                fragmentClass = MyOrderFragment.class;
                break;
            case R.id.nav_product:
                fragmentClass = HomeFragment.class;
                break;
            case R.id.nav_shop:
                fragmentClass = HomeFragment.class;
                break;
            case R.id.nav_logout:
                fragmentClass = null;
                LoginManager.getInstance().logOut();
                user.clearUser().commit();
                Intent i = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
                break;
            default:
                fragmentClass = HomeFragment.class;
                break;
        }
        if (fragmentClass != null) {
            try {
                fragment = (Fragment) fragmentClass.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
            // Insert the fragment by replacing any existing fragment
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.container, fragment).commit();

            // Highlight the selected item has been done by NavigationView
            item.setChecked(true);
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        return true;
    }
}
