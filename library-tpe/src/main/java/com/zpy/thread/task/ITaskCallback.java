package com.zpy.thread.task;


public interface ITaskCallback<Result> extends IProgress<Result> {

    void onBeforeCall();

    void onAfterCall();

    void onComplete(Result data);

    void onException(Throwable t);

    void onCancelled();

}
