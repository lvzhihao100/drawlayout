package com.lvzhihao.test.drawlayoutmyfirst.utils;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.lvzhihao.test.drawlayoutmyfirst.MainActivity;
import com.lvzhihao.test.drawlayoutmyfirst.R;

/**
 * Created by vzhihao on 2016/6/13.
 */
public class FragmentUtil {
    private static  Fragment showedFragment;
    private  static Activity activity;
    private static FragmentManager mFragmentMan;

    public static void initFragment(Activity activity,Fragment first){

        mFragmentMan = ((MainActivity) activity).getSupportFragmentManager();
        showedFragment=first;
        mFragmentMan.beginTransaction().add(R.id.fl_main,showedFragment).commit();
    }
    public static void switchFragment( Fragment to) {
        if (showedFragment != to) {

            FragmentTransaction transaction = mFragmentMan.beginTransaction().setCustomAnimations(
                    android.R.anim.fade_in, android.R.anim.slide_out_right);
            if (!to.isAdded()) {    // 先判断是否被add过
                transaction.hide(showedFragment).add(R.id.fl_main, to).commit(); // 隐藏当前的fragment，add下一个到Activity中
            } else {
                transaction.hide(showedFragment).show(to).commit(); // 隐藏当前的fragment，显示下一个
            }
            showedFragment = to;
        }
    }
}
