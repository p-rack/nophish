package de.tudarmstadt.informatik.secuso.phishedu;

import android.os.Bundle;
import de.tudarmstadt.informatik.secuso.phishedu.backend.BackendController;

public class YouAreCorrectActivity extends CategorySwipeActivity {
	
	//int level; is used as index for the consequences type
	
	protected static int[][] consequencesLayoutIds = {
		{
			R.layout.you_are_correct
		}
	};
	
	protected int[][] getLayouts(){
		return consequencesLayoutIds;
	}
		
	protected void onStartClick(){
		setResult(RESULT_OK);
		finish();
	}

	@Override
	protected int getCategory() {
		return 0;
	}

	@Override
	protected String startButtonText() {
		return "Nächste URL";
	}
	
}
