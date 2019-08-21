package com.example.administrator.week;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.example.administrator.week.fragment.AppFragment;
import com.example.administrator.week.fragment.FirstFragment;
import com.example.administrator.week.fragment.FourthFragment;
import com.example.administrator.week.fragment.MainFragment;
import com.example.administrator.week.fragment.PhotoFragment;
import com.example.administrator.week.fragment.PurseFragment;
import com.example.administrator.week.fragment.SecondFragment;
import com.example.administrator.week.fragment.ThirdFragment;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener
{

    private DrawerLayout mDrawerLayout;
    private FirstFragment fg1;
     private SecondFragment fg2;
     private ThirdFragment fg3;
     private FourthFragment fg4;
     private RelativeLayout first;
     private RelativeLayout second;
     private  RelativeLayout third;
     private RelativeLayout fouth;
    private MainFragment mainFragment;
    private AppFragment settingFragment;
    private PurseFragment purseFragment;
    private PhotoFragment photoFragment;
    private FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        fragmentManager=getSupportFragmentManager();
        mDrawerLayout=(DrawerLayout)findViewById(R.id.drawer_layout);
        first=(RelativeLayout)findViewById(R.id.first_layout);
         second=(RelativeLayout)findViewById(R.id.second_layout);
         third=(RelativeLayout)findViewById(R.id.third_layout);
         fouth=(RelativeLayout)findViewById(R.id.fourth_layout);
         first.setOnClickListener(this);
         second.setOnClickListener(MainActivity.this);
         third.setOnClickListener(MainActivity.this);
         fouth.setOnClickListener(MainActivity.this);
         setchoicItem(0);

        NavigationView navigationView=(NavigationView)findViewById(R.id.nav_view);
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null)
        {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }
       navigationView.setCheckedItem(R.id.nav_call);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.nav_call:
                        showMainFragment();
                        break;
                    case R.id.nav_system:
                        showSystemFragment();
                        break;
                    case R.id.nav_purse:
                        showPurseFragment();
                        break;
                    case R.id.nav_photo:
                        showPhotoFragment();
                        break;
                    case R.id.nav_task:
                        showTaskFragment();
                        break;
                    case R.id.nav_sensor:
                        showSensorFragment();
                        break;
                    default:
                }
                mDrawerLayout.closeDrawers();
                return true;
            }
        });
    }
    public void showMainFragment()
    {
        Intent intent=new Intent(MainActivity.this,CheckActivity.class);
        startActivity(intent);
    }
    public void showSensorFragment()
    {
        Intent intent=new Intent(MainActivity.this,Sensor.class);
        startActivity(intent);
    }
    public void showSystemFragment()
    {
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        hideFragments(fragmentTransaction);
        if (settingFragment==null)
        {
            settingFragment=new AppFragment();
            fragmentTransaction.add(R.id.content,settingFragment);
        }
        fragmentTransaction.show(settingFragment);
        fragmentTransaction.commit();
    }
    public void showPurseFragment()
    {
        Intent intent=new Intent(MainActivity.this,Location.class);
        startActivity(intent);
    }
    public void showPhotoFragment()
    {
        Intent intent=new Intent(this,Picture.class);
        startActivity(intent);
    }
    public void showTaskFragment()
    {
        Intent intent=new Intent(this,SystemInfoActivity.class);
        startActivity(intent);
    }
    public void onClick(View view)
     {
     switch (view.getId())
     {
     case R.id.first_layout:
     setchoicItem(0);
     break;
     case R.id.second_layout:
     setchoicItem(1);
     break;
     case R.id.third_layout:
     setchoicItem(2);
     break;
     case R.id.fourth_layout:
     setchoicItem(3);
     break;
     case R.id.nav_call:
     showMainFragment();
     break;
     default:
     break;
     }
     }
     private void setchoicItem(int index)
     {
     FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
     hideFragments(fragmentTransaction);
     switch (index)
     {
         case 0:
             if(fg1==null)
             {
             fg1=new FirstFragment();
             fragmentTransaction.add(R.id.content,fg1);
             }
             else
             {
             fragmentTransaction.show(fg1);
             }
        break;
        case 1:
             if(fg2==null)
             {
             fg2=new SecondFragment();
             fragmentTransaction.add(R.id.content,fg2);
             }
             else
             {
             fragmentTransaction.show(fg2);
             }
             break;
     case 2:
             if(fg3==null)
             {
             fg3=new ThirdFragment();
             fragmentTransaction.add(R.id.content,fg3);
             }
             else
             {
             fragmentTransaction.show(fg3);
             }
     break;
     case 3:
         if(fg4==null)
         {
             Intent intent=new Intent(MainActivity.this, MianActivity.class);
             startActivity(intent);
         }
         else
         {
         fragmentTransaction.show(fg4);
         }
     break;
     }
     fragmentTransaction.commit();
     }

    private void hideFragments(FragmentTransaction fragmentTransaction)
    {
         if(fg1!=null)
         {
         fragmentTransaction.hide(fg1);
         }
         if(fg2!=null)
         {
         fragmentTransaction.hide(fg2);
         }
         if(fg3!=null)
         {
         fragmentTransaction.hide(fg3);
         }
         if(fg4!=null)
         {
         fragmentTransaction.hide(fg4);
         }
        if (mainFragment!=null)
        {
            fragmentTransaction.hide(mainFragment);
        }
        if (settingFragment!=null)
        {
            fragmentTransaction.hide(settingFragment);
        }
        if(purseFragment!=null)
        {
            fragmentTransaction.hide(purseFragment);
        }
        if (photoFragment!=null)
        {
            fragmentTransaction.hide(photoFragment);
        }

    }
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.backup:
                Toast.makeText(this,"你点击了创建群聊",Toast.LENGTH_SHORT).show();
                break;
            case R.id.delete:
                Toast.makeText(this,"你点击了扫一扫",Toast.LENGTH_SHORT).show();
                break;
            case R.id.settings:
                Toast.makeText(this,"你点击了设置",Toast.LENGTH_SHORT).show();
                break;
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
            default:
        }
        return true;
    }
}
