package com.zpy.thread.task;

import android.app.Activity;
import android.content.Context;

public abstract class DefaultCallback<T> implements ApiCallback<T> {

    private Context mContext;
    public boolean isCancel;

    public DefaultCallback() {
        mContext = null;
    }

    public DefaultCallback(Context context) {
        mContext = context;
    }

    public DefaultCallback(Context context, boolean isCancel) {
        mContext = context;
        this.isCancel = isCancel;
    }

    @Override
    public void onDataReceived(T data) {
        if (mContext != null) {
            if (mContext instanceof Activity
                    && ((Activity) mContext).isFinishing()) {
                return;
            }
        }
        onDataSuccess(data);
    }

    @Override
    public void onProgress(Object id, int progress) {
    }

    @Override
    public void onException(int code, String reason) {
        if (mContext != null && mContext instanceof Activity) {
            if (((Activity) mContext).isFinishing()) {
                return;
            }
        }
    }

    public abstract void onDataSuccess(T data);
}
