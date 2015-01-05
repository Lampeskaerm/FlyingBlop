package com.ophion.blop;

import com.ophion.framework.Screen;
import com.ophion.framework.implementation.AndroidGame;;

public class SampleGame extends AndroidGame {
	
	@Override
	public Screen getInitScreen(){
		return new LoadingScreen(this);
	}
	
	@Override
	public void onBackPressed() {
		getCurrentScreen().backButton();
	}
}
