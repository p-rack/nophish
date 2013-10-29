package de.tudarmstadt.informatik.secuso.phishedu.levelintros;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import de.tudarmstadt.informatik.secuso.phishedu.common.Constants;

public class Level4InfoFragment extends Fragment {

	static Level4InfoFragment init(int resource) {
		Level4InfoFragment fragment = new Level4InfoFragment();

		Bundle args = new Bundle();
		args.putInt("resource", resource);
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View layoutView = inflater.inflate(
				this.getArguments().getInt(Constants.LEVEL_LAYOUT_KEY),
				container, false);
		return layoutView;
	}
}