package edu.gatech.cs2340.group29.spacemerchant.activity;

import java.util.ArrayList;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import edu.gatech.cs2340.group29.spacemerchant.R;
import edu.gatech.cs2340.group29.spacemerchant.model.Difficulty;

public class ChooseDifficultyAdapter extends ArrayAdapter<Difficulty> {
	private ArrayList<Difficulty> items;
	private Context context;

	public ChooseDifficultyAdapter(Context context, int layoutResourceId, ArrayList<Difficulty> items) {
		super(context, layoutResourceId, items);
		this.context = context;
		this.items = items;
	}

	static class ViewHolder {
		protected ImageView img;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;

		if (convertView == null) {
			LayoutInflater inf = ((Activity) context).getLayoutInflater();
			convertView = inf.inflate(R.layout.difficultylogo, parent, false);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		
		viewHolder = new ViewHolder();
		viewHolder.img = (ImageView) convertView.findViewById(R.id.difficulty);
		convertView.setTag(viewHolder);

		viewHolder.img.setImageResource(items.get(position).getIcon());
		return (LinearLayout) convertView;
	}
}