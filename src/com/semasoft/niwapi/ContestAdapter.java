package com.semasoft.niwapi;

import java.util.List;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class ContestAdapter extends BaseAdapter {
	private Activity activity;
	private LayoutInflater inflater;
	private List<Contest> Citems;
	ImageLoader imageLoader = NiwapiController.getInstance().getImageLoader();
	
	public ContestAdapter(Activity a , List<Contest> contestItems)
	{
		this.activity = a;
		this.Citems = contestItems;
		
	}

	@Override
	public int getCount() {
		return Citems.size();
	}

	@Override
	public Object getItem(int pos) {
		return Citems.get(pos);
	}

	@Override
	public long getItemId(int p) {
		return Integer.parseInt(Citems.get(p).getId());
	}

	@Override
	public View getView(int p, View v, ViewGroup vg) {
		// push data to view
		if (inflater == null)
			inflater = (LayoutInflater) activity
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		if (v == null)
			v = inflater.inflate(R.layout.im_list_item, null);

		if (imageLoader == null)
			imageLoader = NiwapiController.getInstance().getImageLoader();
		NetworkImageView thumbNail = (NetworkImageView) v
				.findViewById(R.id.ivContest);
		//TextView title = (TextView) v.findViewById(R.id.tvSongName);

		// getting movie data for the row
		Contest m = Citems.get(p);

		// thumbnail image
		thumbNail.setImageUrl(m.getImageLink(), imageLoader);

		// title
		//title.setText(m.getName());

		return v;

	}

}
