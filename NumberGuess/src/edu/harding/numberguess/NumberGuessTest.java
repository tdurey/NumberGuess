package edu.harding.numberguess.test;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import android.widget.TextView;
import edu.harding.numberguess.MainActivity;
import edu.harding.numberguess.R;
import edu.harding.numberguess.Settings;
import edu.harding.numberguess.R.id;
import edu.harding.numberguess.R.string;


public class NumberGuessTest extends ActivityInstrumentationTestCase2<MainActivity> {

	private MainActivity mActivity;
	private TextView mResult;
	private String mRandomNum;
	private String mLargestNumber;
	private SharedPreferences mPrefs;


	public NumberGuessTest() {
		super(MainActivity.class); // activity to be tested
	}

	public void testPreconditions() {
		assertNotNull(mResult);
	}

	@Override
	protected void setUp() throws Exception { // called before test is executed
		super.setUp();
		mActivity = getActivity();
		mResult = (TextView) mActivity.findViewById(R.id.textView1);
		mRandomNum = mResult.getText().toString();

		if(mPrefs == null)
			mPrefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
		mLargestNumber = mPrefs.getString(Settings.LARGEST_NUMBER_PREFERENCE_KEY, getActivity().getString(R.string.largest_number));
		Log.d("HEY", ":" + mRandomNum + ", " + mLargestNumber);
	} 


	public void testVerifyTextView() {
		String last = mRandomNum.substring(mRandomNum.lastIndexOf(" ") + 1);
		Log.d("GET", last);
		assertTrue("Text should be " + last, mLargestNumber.equals(last));
	}

}