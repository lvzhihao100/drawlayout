# drawlayout
使用drawlayout实现侧滑菜单栏
##参考网址
使用Toolbar + DrawerLayout快速实现高大上菜单侧滑
http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2015/0303/2522.html
android官方侧滑菜单DrawerLayout详解
http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2014/0925/1713.html
 Android组件——使用DrawerLayout仿网易新闻v4.4侧滑菜单
http://blog.csdn.net/jdsjlzx/article/details/43447713
##注意事项
顺序：内容->左侧滑（gravity="start"）->右侧滑
listview不滑动，设置onclicklistener action.down返回false
##关键代码
	toggle = new ActionBarDrawerToggle(this,  
	drawerLayout, toolbar, R.string.open, R.string.close) {
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