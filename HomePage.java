package org.colibrisoft.colibri;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;

public class HomePage extends AppCompatActivity{

    private FirebaseAuth mAuth;
    private Toolbar mToolbar;
    /*private ViewPager mViewPager;
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private TabLayout mTabLayout;*/
    private String NameVal;
    private String UserPhone;

    private DatabaseReference mUserRef;

    private BottomNavigationView mBottomNav;
    private FrameLayout mHomeFrame;

    private ChatsFragment chatsFragment;
    private NotificationFragment notificationFragment;
    private AccountFragment accountFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        mAuth = FirebaseAuth.getInstance();
        mToolbar = (Toolbar) findViewById(R.id.home_page_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Chats");


        mHomeFrame = (FrameLayout) findViewById(R.id.home_frame);
        mBottomNav = (BottomNavigationView) findViewById(R.id.home_page_bottom);

        if (mAuth.getCurrentUser() != null) {
            mUserRef = FirebaseDatabase.getInstance().getReference().child("User").child(mAuth.getCurrentUser().getUid());

        }


        chatsFragment = new ChatsFragment();
        notificationFragment = new NotificationFragment();
        accountFragment = new AccountFragment();

        /*mViewPager = (ViewPager) findViewById(R.id.main_tabPager);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager.setAdapter(mSectionsPagerAdapter);
        mTabLayout = (TabLayout) find(FrameViewById(R.id.main_tabs);
        mTabLayout.setupWithViewPager(mViewPager);*/

        /*NameVal = getIntent().getExtras().getString("Name_Val");
        UserPhone = getIntent().getExtras().getString("Phone_Val");*/

        mBottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){

                    case R.id.bottom_chats:
                        mBottomNav.setItemBackgroundResource(R.color.colorPrimary);
                        getSupportActionBar().setTitle("Chats");
                        setFragment(chatsFragment);
                        return true;

                        case R.id.bottom_notifications:
                        mBottomNav.setItemBackgroundResource(R.color.colorPrimary);
                        setFragment(notificationFragment);
                        getSupportActionBar().setTitle("Notifications");
                        return true;

                        case R.id.bottom_account:
                        mBottomNav.setItemBackgroundResource(R.color.colorPrimary);
                        setFragment(accountFragment);
                        getSupportActionBar().setTitle("Account");
                        return true;

                        default:
                            return false;

                }

            }
        });
    }

    private void setFragment(Fragment fragment) {

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.home_frame, fragment);
        fragmentTransaction.commit();

    }

    @Override
    public void onStart() {

        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser==null){
            sendToStart();
        } else {

            mUserRef.child("online").setValue("true");

        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if(currentUser != null) {
            mUserRef.child("online").setValue(ServerValue.TIMESTAMP);

        }
    }

    private void sendToStart() {

        Intent authIntent = new Intent(HomePage.this, MainActivity.class);
        startActivity(authIntent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_menu, menu);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        if (item.getItemId() == R.id.main_logout_btn){

            FirebaseAuth.getInstance().signOut();
            sendToStart();

        }

        if (item.getItemId() == R.id.main_settings_btn){
            Intent settingsIntent = new Intent(HomePage.this, SettingsActivity.class);
            //settingsIntent.putExtra("current_user_name", NameVal);
            //settingsIntent.putExtra("current_user_phone", UserPhone);
            startActivity(settingsIntent);
        }
        
        if (item.getItemId()==R.id.main_allusers_btn){
            Intent usersIntent = new Intent(HomePage.this, UsersActivity.class);
            startActivity(usersIntent);
        }

        return true;
    }
}
