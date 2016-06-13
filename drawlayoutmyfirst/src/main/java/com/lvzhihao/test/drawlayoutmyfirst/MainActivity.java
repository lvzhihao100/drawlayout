package com.lvzhihao.test.drawlayoutmyfirst;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.lvzhihao.test.drawlayoutmyfirst.fragment.EventFragment;
import com.lvzhihao.test.drawlayoutmyfirst.fragment.FollowersFragment;
import com.lvzhihao.test.drawlayoutmyfirst.fragment.FollowingFragment;
import com.lvzhihao.test.drawlayoutmyfirst.fragment.GistsFragment;
import com.lvzhihao.test.drawlayoutmyfirst.fragment.IssueFragment;
import com.lvzhihao.test.drawlayoutmyfirst.fragment.RepositoryFragment;
import com.lvzhihao.test.drawlayoutmyfirst.utils.ArrayUtils;
import com.lvzhihao.test.drawlayoutmyfirst.utils.FragmentUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private ListView listView;
    private String[] texts;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private Fragment repositoryFragment;
    private EventFragment eventFragment;
    private FollowersFragment followersFragment;
    private FollowingFragment followingFragment;
    private GistsFragment gistsFragment;
    private IssueFragment issueFragment;
    private List<Fragment> fragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findView();
        toolbar.setTitle("主界面");
        toolbar.setTitleTextColor(Color.parseColor("#ffffff"));
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        toggle.syncState();
        drawerLayout.addDrawerListener(toggle);
        List<HashMap<String,Object>> data = new ArrayList<HashMap<String,Object>>();
        texts = ArrayUtils.getMenuItemTexts();
        for (int i = 0; i< texts.length; i++) {
            HashMap<String, Object> stringObjectHashMap = new HashMap<>();
            stringObjectHashMap.put("id", texts[i]);
            data.add(stringObjectHashMap);
        }
        SimpleAdapter simple = new SimpleAdapter(this, data,
               R.layout.menu_list, new String[] { "id" },
                new int[] { R.id.text1 });
        listView.setAdapter(simple);
        listView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction()==MotionEvent.ACTION_DOWN||event.getAction()==MotionEvent.ACTION_UP){
                    return false;
                }
                return true;
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                toolbar.setTitle(texts[position]);
                if (position>0) {
                    FragmentUtil.switchFragment(fragmentList.get(position-1));
                }
            }
        });
        initFragment();
    }

    private void initFragment() {
        fragmentList = new ArrayList<>(6);
        repositoryFragment = new RepositoryFragment();
        eventFragment = new EventFragment();
        followersFragment = new FollowersFragment();
        followingFragment = new FollowingFragment();
        gistsFragment = new GistsFragment();
        issueFragment = new IssueFragment();
        fragmentList.add(repositoryFragment);
        fragmentList.add(eventFragment);
        fragmentList.add(followersFragment);
        fragmentList.add(followingFragment);
        fragmentList.add(gistsFragment);
        fragmentList.add(issueFragment);
        FragmentUtil.initFragment(this,repositoryFragment);
    }

    private void findView() {
        toolbar = (Toolbar) findViewById(R.id.tb_my);
        drawerLayout = (DrawerLayout) findViewById(R.id.dl_my);
        listView = (ListView) findViewById(R.id.lv_menu);
    }
}
