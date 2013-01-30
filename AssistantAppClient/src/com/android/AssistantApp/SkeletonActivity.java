package com.android.AssistantApp;

import com.example.android.skeletonapp.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

public class SkeletonActivity extends Activity {
    
    static final private int BACK_ID = Menu.FIRST;
    static final private int CLEAR_ID = Menu.FIRST + 1;
    
    protected ArrayAdapter<CharSequence> mAdapter;
    private Spinner spinner;

    private SkeletonActivity sk;
    public EditText mEditor;
    
    public SkeletonActivity() {
    }

    /** Called with the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inflate our UI from its XML layout description.
        setContentView(R.layout.skeleton_activity);

        // Find the text editor view inside the layout, because we
        // want to do various programmatic things with it.
        this.sk = this;
        mEditor = (EditText) findViewById(R.id.editor);
        spinner = (Spinner) findViewById(R.id.Spinner01);
        this.mAdapter = ArrayAdapter.createFromResource(this, R.array.Planets,
                android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(this.mAdapter);
        
        // Hook up button presses to the appropriate event handler.
       // ((Button) findViewById(R.id.back)).setOnClickListener(mBackListener);
        ((ImageButton) findViewById(R.id.image)).setOnClickListener(mClearListener);
        
        mEditor.setText(getText(R.string.main_label));
    }

    /**
     * Called when the activity is about to start interacting with the user.
     */
    @Override
    protected void onResume() {
        super.onResume();
    }

    /**
     * Called when your activity's options menu needs to be created.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        // We are going to create two menus. Note that we assign them
        // unique integer IDs, labels from our string resources, and
        // given them shortcuts.
        menu.add(0, BACK_ID, 0, R.string.back).setShortcut('0', 'b');
        menu.add(0, CLEAR_ID, 0, R.string.clear).setShortcut('1', 'c');

        return true;
    }

    /**
     * Called right before your activity's option menu is displayed.
     */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);

        // Before showing the menu, we need to decide whether the clear
        // item is enabled depending on whether there is text to clear.
        menu.findItem(CLEAR_ID).setVisible(mEditor.getText().length() > 0);

        return true;
    }

    /**
     * Called when a menu item is selected.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case BACK_ID:
            finish();
            return true;
        case CLEAR_ID:
            mEditor.setText("");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A call-back for when the user presses the clear button.
     */
    OnClickListener mClearListener = new OnClickListener() {
        public void onClick(View v) {
        	int op = getCase(spinner.getSelectedItem().toString());
        	if (op==-1) announce("failed to select option", true);
        	else new sender(sk).execute(op + mEditor.getText().toString());
        }
    };
    
    private int getCase(String option){
    	if(option.equals("Homework")){
    		return 0;
    	}else if(option.equals("get .mp3")){
    		return 1;
    	}
    	return -1;
    }
    
    private void announce(String note, Boolean length){
    	if(length) Toast.makeText(sk.getApplicationContext(), note, Toast.LENGTH_LONG).show();
    	else Toast.makeText(sk.getApplicationContext(), note, Toast.LENGTH_SHORT).show();
    }
}