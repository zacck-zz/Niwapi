package com.semasoft.niwapi;

import java.util.List;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.sax.StartElementListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class ContestAdapter extends BaseAdapter {
	private Activity activity;
	private LayoutInflater inflater;
	private List<Contest> Citems;
	ImageLoader imageLoader = NiwapiController.getInstance().getImageLoader();
	Context ctx;
	String idp[];
	String TAG = "CONTESTADAPTER";

	public ContestAdapter(Activity a, List<Contest> contestItems) {
		this.ctx = a;
		this.activity = a;
		this.Citems = contestItems;
		idp = new String[contestItems.size()];

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
		// TextView title = (TextView) v.findViewById(R.id.tvSongName);

		// getting movie data for the row
		Contest m = Citems.get(p);

		// thumbnail image
		thumbNail.setImageUrl(m.getImageLink(), imageLoader);
		idp[p] = m.getId();
		Log.d(TAG, idp[p] + "Set");
		final String x = m.getId();

		TextView tvt = (TextView)v.findViewById(R.id.tvTitle);
		tvt.setText(m.getTitle());

		// lets add clicklisteners to our buttons
		ImageButton btV = (ImageButton) v.findViewById(R.id.btVote);
		ImageButton btT = (ImageButton) v.findViewById(R.id.btTry);

		// add their click listeners
		btT.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent tryIntent = new Intent(ctx, TryActivity.class);
				tryIntent.putExtra("contest_id", x);
				ctx.startActivity(tryIntent);

			}
		});
		btV.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Log.d(TAG, "From inner button " + x);

			}
		});

		return v;

	}

}
