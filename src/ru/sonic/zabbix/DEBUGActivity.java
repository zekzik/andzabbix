package ru.sonic.zabbix;

import ru.sonic.zabbix.base.ZabbixAPIException;
import ru.sonic.zabbix.base.ZabbixAPIHandler;
import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.widget.TabHost;

/**
 * display active triggers in a listview
 * @author gryphius
 *
 */
public class DEBUGActivity extends TabActivity {
	protected static ZabbixAPIHandler api = null;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main); //Default layout was tabs ---zak
		
		Resources res = getResources(); // Resource object to get Drawables
        TabHost tabHost = getTabHost();  // The activity TabHost
        TabHost.TabSpec spec;  
        Intent intent;  
// since our layout is not tab layout we have to modify based on our UI which is Linear layout
//we have to change tabHost --zak
        intent = new Intent().setClass(this, ActiveTriggerActivity.class);
        spec = tabHost.newTabSpec("ActiveTrigger").setIndicator("ActiveTrigger",
                res.getDrawable(R.drawable.graph))
           .setContent(intent);
        tabHost.addTab(spec);
        
        intent = new Intent().setClass(this, HostListActivity.class);
        intent.putExtra("groupID", "");
        spec = tabHost.newTabSpec("HostList").setIndicator("HostList",
                          res.getDrawable(R.drawable.icon))
                    .setContent(intent);
        tabHost.addTab(spec);
        
        intent = new Intent().setClass(this, GraphHostsActivity.class);
        spec = tabHost.newTabSpec("Graph").setIndicator("Graph",
                          res.getDrawable(R.drawable.ok_icon))
                    .setContent(intent);
        tabHost.addTab(spec);

        
        tabHost.setCurrentTab(0);
	}
	
	public static String getActiveTriggers () throws ZabbixAPIException {
		Log.d("DEBUGActivity", "getActiveTriggers...");
		int size = api.getActiveTriggersCount("ds").size();
		Log.d("DEBUGActivity", "size"+size);
		return ""+size;
	}
}
