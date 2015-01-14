package com.anna.sent.soft.packageinfoviewer;

import java.util.List;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		StringBuilder sb = new StringBuilder();

		PackageManager packageManager = getPackageManager();
		List<PackageInfo> packages = packageManager
				.getInstalledPackages(PackageManager.GET_UNINSTALLED_PACKAGES
						| PackageManager.GET_ACTIVITIES);
		for (int i = 0; i < packages.size(); ++i) {
			PackageInfo packageInfo = packages.get(i);
			sb.append(i + " " + packageInfo.packageName);
			sb.append("\n");

			if (packageInfo.activities != null) {
				for (int j = 0; j < packageInfo.activities.length; ++j) {
					ActivityInfo activityInfo = packageInfo.activities[j];
					sb.append("\t" + i + "." + j + " " + activityInfo.name);
					sb.append("\n");
				}
			}

			sb.append("\n");
		}

		TextView tv = (TextView) findViewById(R.id.textView);
		tv.setText(sb.toString());
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
		if (id == R.id.action_settings) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}
}