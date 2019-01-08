package com.zpy.pthread;

import android.app.Activity;
import android.os.Bundle;
import com.zpy.thread.TPE;
import com.zpy.thread.task.Callback;
import android.util.Log;
import android.widget.TextView;

public class SampleMainActivity extends Activity {
    private final static String TAG = "TPE_SampleActivity";
    private Integer mCount = 0;
    private Integer mCount2 = 0;
    private TextView mCountTv;
    private TextView mInfoTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_main);
        mCountTv = (TextView) findViewById(R.id.count);
        mInfoTv = (TextView) findViewById(R.id.info);
        TPE.setDebug(true);
        for (int i = 0; i < 30; ++i) {
            final int finaltmep = i;
            TPE.get().submitDefaultConcurrentTasks(new Callback<Integer>() {
                @Override
                public void onBeforeCall() {
                    // TODO Auto-generated method stub
                    super.onBeforeCall();
                }
                @Override
                public Integer doBackground() throws Exception {
                    Thread.sleep((finaltmep%5 + 1) * 100);
                    mCount++;
                    Log.d(TAG, "submitDefaultConcurrentTasks mCount:" + mCount);
                    return super.doBackground();
                }

                @Override
                public void onComplete(Integer t) {
                    mCountTv.setText(mCountTv.getText().toString() + "\n task:" + finaltmep);
                    super.onComplete(t);
                }
            });
        }

        for (int i = 0; i< 30; ++i) {
            final int finaltmep = i;
            TPE.get().submitDefaultOrderedTasks(new Callback<Integer>() {
                @Override
                public void onBeforeCall() {
                    super.onBeforeCall();
                }

                @Override
                public Integer doBackground() throws Exception {
                    Thread.sleep((finaltmep%5 + 1) * 100);
                    mCount2++;
                    Log.d(TAG, "submitDefaultOrderedTasks mCount:" + mCount);
                    return super.doBackground();
                }

                @Override
                public void onComplete(Integer t) {
                    mInfoTv.setText(mInfoTv.getText().toString() + "\n task:" + finaltmep);
                    super.onComplete(t);
                }
            });
        }
    }
}
