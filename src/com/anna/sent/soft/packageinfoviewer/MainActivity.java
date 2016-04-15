package com.anna.sent.soft.packageinfoviewer;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends Activity implements TextWatcher {
	private List<PackageInfo> mPackages = new ArrayList<PackageInfo>();
	private ListView mListView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		PackageManager packageManager = getPackageManager();
		mPackages = packageManager
				.getInstalledPackages(PackageManager.GET_UNINSTALLED_PACKAGES
						| PackageManager.GET_ACTIVITIES);

		mListView = (ListView) findViewById(R.id.listView);
		mListView.setAdapter(new PackagesArrayAdapter(this, mPackages));

		EditText editText = (EditText) findViewById(R.id.editText);
		editText.addTextChangedListener(this);
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

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {
	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
	}

	@Override
	public void afterTextChanged(Editable s) {
		String filter = s.toString();

		if (s.length() == 0) {
			mListView.setAdapter(new PackagesArrayAdapter(this, mPackages));
		} else {
			List<PackageInfo> packages = new ArrayList<PackageInfo>();
			for (int i = 0; i < mPackages.size(); ++i) {
				PackageInfo packageInfo = mPackages.get(i);
				if (packageInfo.packageName.contains(filter)) {
					packages.add(packageInfo);
				}
			}

			mListView.setAdapter(new PackagesArrayAdapter(this, packages));
		}
	}
}