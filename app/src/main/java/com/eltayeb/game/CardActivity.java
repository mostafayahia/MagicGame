/*
 * Copyright (C) 2018 Yahia H. El-Tayeb
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.eltayeb.game;

import com.eltayeb.game.R;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class CardActivity extends Activity implements OnClickListener {
	
	private static final boolean GAME_ON = true;
	private static final boolean GAME_OFF = false;
	private static final int CARDS = 4;
	int[] numbers = new int[] {8, 2, 4, 1};
	int counter = 0, number = 0;
	private Button trueBtn, falseBtn;
	private TextView cardView, questView;
	private Drawable[] cards;
	private boolean state;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.card);
		loadView();
		// instantiate the game at the beginning
		if (savedInstanceState == null) { 
			scrollCards();
			state = GAME_ON;
		} else { // retrieve the stored data 
		    number = savedInstanceState.getInt("number");
		    counter = savedInstanceState.getInt("counter");
		    state = savedInstanceState.getBoolean("state");
		    if (state == GAME_ON) cardView.setBackgroundDrawable(cards[counter-1]);
		    else /* game off */   makeChange();
		}
		
	}

	@SuppressWarnings("deprecation")
	private void scrollCards() {
		/*
		ButtonListener trueButtonListener  = new ButtonListener(true);
        ButtonListener falseButtonListener = new ButtonListener(false);
        */
		cardView.setBackgroundDrawable(cards[counter++]);
		
	}

	private void loadView() {
		// TODO Auto-generated method stub
		trueBtn   = (Button)   findViewById(R.id.trueBtn);
		falseBtn  = (Button)   findViewById(R.id.falseBtn);
		cardView  = (TextView) findViewById(R.id.cardView);
		questView = (TextView) findViewById(R.id.questView);
		cards = new Drawable[] {
				getResources().getDrawable(R.drawable.card3),
				getResources().getDrawable(R.drawable.card1),
				getResources().getDrawable(R.drawable.card2),
				getResources().getDrawable(R.drawable.card0)
		};
		
		trueBtn.setOnClickListener(this);	
		falseBtn.setOnClickListener(this);
	}
	/*
	private class ButtonListener implements View.OnClickListener {
		Boolean isTrueBtn;
		
		ButtonListener(boolean isTrueBtn) { this.isTrueBtn = isTrueBtn; }
		
		@SuppressWarnings("deprecation")
		@Override
		public void onClick(View v) {
			if (state == GAME_ON) {
				if (isTrueBtn)
				    number += numbers[counter-1];
			
				if (counter < CARDS)
					cardView.setBackgroundDrawable(cards[counter++]);
				else  
			    	{ makeChange(); state = GAME_OFF; }
			} else  { // GAME_OFF
			    if (isTrueBtn) // play trick button role now 
			    	startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://vimeo.com/103112616")));
			    else           // play share button role now 
			    	startActivity(new Intent(Intent.ACTION_SEND).setType("text/plain")
			    			.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=com.eltayeb.game"));
			}
		}
	} */
	
	@SuppressWarnings("deprecation")
	@Override
	public void onClick(View v) {
		if (state == GAME_ON) {
			if (R.id.trueBtn == v.getId())
			    number += numbers[counter-1];
		
			if (counter < CARDS)
				cardView.setBackgroundDrawable(cards[counter++]);
			else  
		    	{ makeChange(); state = GAME_OFF; }
		} else  { // GAME_OFF
		    if (R.id.trueBtn == v.getId()) // play trick button role now 
		    	// startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://vimeo.com/103112616")));
		    	startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=x2LARaU-a0w")));
		    else                           // play share button role now 
		    	startActivity(new Intent(Intent.ACTION_SEND).setType("text/plain")
		    			.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=com.eltayeb.game"));
		}		
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
		// store my data to retrieve it later
		outState.putInt("number", number);
		outState.putBoolean("state", state);
		outState.putInt("counter", counter);
	}
	
	@SuppressWarnings("deprecation")
	private void makeChange() {
		// TODO Auto-generated method stub
		cardView.setBackgroundDrawable(getApplicationContext().getResources().getDrawable(R.drawable.card_bg));
	    cardView.setText(getResources().getString(R.string.your_number_str) + number 
	    		+ getResources().getString(R.string.press_back_btn_str));
	    questView.setVisibility(View.INVISIBLE);
	    
	    // true button play trick button role now
	    trueBtn.setText(R.string.trick_str);
	    
	    // false button play share button role now
	    falseBtn.setText(R.string.share_str);
	    
	}


}