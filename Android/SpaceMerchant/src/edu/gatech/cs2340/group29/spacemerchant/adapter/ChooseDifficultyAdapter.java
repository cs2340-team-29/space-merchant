package edu.gatech.cs2340.group29.spacemerchant.adapter;

import java.util.ArrayList;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
		protected Button btn;
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
		viewHolder.btn = (Button) convertView.findViewById(R.id.difficultyButton);
		convertView.setTag(viewHolder);

		viewHolder.img.setImageResource(items.get(position).getIcon());
		viewHolder.btn.setText(items.get(position).toString());
		return (LinearLayout) convertView;
	}
}