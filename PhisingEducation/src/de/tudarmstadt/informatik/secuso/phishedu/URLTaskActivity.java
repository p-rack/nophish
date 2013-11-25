package de.tudarmstadt.informatik.secuso.phishedu;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.TextView;
import de.tudarmstadt.informatik.secuso.phishedu.backend.BackendController;
import de.tudarmstadt.informatik.secuso.phishedu.backend.PhishResult;

public class URLTaskActivity extends PhishBaseActivity {

	private TextView urlText;
	private int level;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		this.level = getIntent().getIntExtra(Constants.EXTRA_LEVEL, 0);

		setContentView(R.layout.urltask_task);
		updateScore();

		urlText = (TextView) findViewById(R.id.url_task_url);
		nextURL();
		setTitles();

		// In Level 2 we don't need to check if it is a phish
		if (level == 2) {
			Intent levelIntent = new Intent(this, ProofActivity.class);
			levelIntent.putExtra(Constants.EXTRA_LEVEL, this.level);
			startActivityForResult(levelIntent, 1);
		}

		// set size of shown url depending on level
		setUrlSize();

	}

	private void setUrlSize() {
		TextView url = (TextView) findViewById(R.id.url_task_url);
		float textSize = url.getTextSize();

		switch (level) {
		case 0:
			// should not reach this code, as urltask is called beginning from
			// level 2
			break;
		case 1:
			// should not reach this code, as urltask is called beginning from
			// level 2
			break;
		case 2:
			textSize = 25;
			break;

		case 3:
			textSize = 25;
			break;
		case 4:
			textSize = 20;
			break;
		case 5:
			textSize = 18;
			break;
		case 6:
			textSize = 15;
			break;
		case 7:
			textSize = 13;
			break;
		default:
			break;
		}
		url.setTextSize(textSize);
	}

	private void setTitles() {
		ActionBar ab = getActionBar();

		ab.setTitle(Constants.levelTitlesIds[BackendController.getInstance()
				.getLevel()]);
		ab.setSubtitle(getString(R.string.exercise));
		ab.setIcon(getResources().getDrawable(R.drawable.desktop));
	}

	private void nextURL() {
		String[] urlArray = BackendController.getInstance().getNextUrl();

		// build string from array
		StringBuilder sb = new StringBuilder();
		// adds 9 character string at beginning
		for (int i = 0; i < urlArray.length; i++) {
			sb.append(urlArray[i]);
		}

		urlText.setText(sb.toString());
		updateScore();
	}

	public void clickAccept(View view) {
		clicked(true);
	}

	public void clickDecline(View view) {
		clicked(false);
	}

	private void clicked(boolean acceptance) {
		PhishResult result = BackendController.getInstance().userClicked(
				acceptance);
		Class followActivity = ResultActivity.class;
		if (result == PhishResult.Phish_Detected) {
			followActivity = ProofActivity.class;
		}
		Intent levelIntent = new Intent(this, followActivity);
		levelIntent.putExtra(Constants.EXTRA_RESULT, result.getValue());
		levelIntent.putExtra(Constants.EXTRA_LEVEL, this.level);
		levelIntent.putExtra(Constants.EXTRA_SITE_TYPE, BackendController
				.getInstance().getSiteType().getValue());
		levelIntent.putExtra(Constants.EXTRA_ATTACK_TYPE, BackendController
				.getInstance().getAttackType().getValue());
		startActivityForResult(levelIntent, 1);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		nextURL();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		// Respond to the action bar's Up/Home button
		case android.R.id.home:
			levelCanceldWarning();
			return true;
		case R.id.restart_level:
			levelRestartWarning();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onBackPressed() {
		levelCanceldWarning();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu items for use in the action bar
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.urltask_menu, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	protected void onStart() {
		super.onStart();
		if (level == 2) {
			Intent levelIntent = new Intent(this, ProofActivity.class);
			levelIntent.putExtra(Constants.EXTRA_LEVEL, this.level);
			startActivityForResult(levelIntent, 1);
		} else {
			sendScrollToRight();
		}
	}

	private void sendScrollToRight() {
		final Handler handler = new Handler();
		final HorizontalScrollView hsv = (HorizontalScrollView) findViewById(R.id.url_horizintal_sv);

		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(5);
				} catch (InterruptedException e) {
				}
				handler.post(new Runnable() {
					@Override
					public void run() {
						hsv.fullScroll(View.FOCUS_RIGHT);
					}
				});
			}
		}).start();
	}
}
