package com.anna.sent.soft.packageinfoviewer;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class PackagesArrayAdapter extends ArrayAdapter<PackageInfo> {
	private List<PackageInfo> mPackages = new ArrayList<PackageInfo>();

	public PackagesArrayAdapter(Context context, List<PackageInfo> packages) {
		super(context, R.layout.list_item_package);
		mPackages = new ArrayList<PackageInfo>(packages);
	}

	@Override
	public int getCount() {
		return mPackages.size();
	}

	@Override
	public PackageInfo getItem(int position) {
		return mPackages.get(position);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view;
		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) getContext()
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(R.layout.list_item_package, parent, false);
		} else {
			view = convertView;
		}

		PackageInfo packageInfo = mPackages.get(position);

		TextView textView = (TextView) view.findViewById(R.id.textView);

		StringBuilder sb = new StringBuilder();
		sb.append(position + " " + packageInfo.packageName);

		if (packageInfo.activities != null) {
			int len = packageInfo.activities.length;
			if (len > 0) {
				sb.append("\n");
				for (int j = 0; j < len; ++j) {
					ActivityInfo activityInfo = packageInfo.activities[j];
					sb.append("\t" + position + "." + j + " "
							+ activityInfo.name);
					if (j < len - 1) {
						sb.append("\n");
					}
				}
			}
		}

		textView.setText(sb);

		return view;
	}
}