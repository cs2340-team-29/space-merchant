package edu.gatech.cs2340.group29.spacemerchant.adapter;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import edu.gatech.cs2340.group29.spacemerchant.R;

public class ChooseDifficultyAdapter extends ArrayAdapter<String> {
	private ArrayList<String> items;
	private Context context;

	public ChooseDifficultyAdapter(Context context, int layoutResourceId,
			ArrayList<String> items) {
		super(context, layoutResourceId, items);
		this.context = context;
		this.items = items;
	}

	static class ViewHolder {
		protected TextView difficulty;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;

		if (convertView == null) {
			LayoutInflater inf = ((Activity) context).getLayoutInflater();
			convertView = inf.inflate(R.layout.choose_difficulty_layout_view,
					parent, false);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}

		viewHolder = new ViewHolder();
		viewHolder.difficulty = (TextView) convertView
				.findViewById(R.id.difficulty);
		convertView.setTag(viewHolder);

		viewHolder.difficulty.setText(items.get(position));

		return (LinearLayout) convertView;
	}
}