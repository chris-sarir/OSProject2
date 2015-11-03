package edu.utdallas.taskExecutorImpl;

import edu.utdallas.taskExecutor.Task;
import edu.utdallas.taskExecutor.TaskRunner;

import java.util.List;

/**
 * Created by assar on 11/2/2015.
 */
public class TaskExecutorImpl {

    List<TaskRunner> runnerPool;
    BlockingTaskQueue taskQueue;

    public TaskExecutorImpl(int threadCount){
        for (int i = 0; i < threadCount; i++){
            runnerPool.add(new TaskRunner("TaskThread" + Integer.toString(i)));
        }
    }

    public void addTask (Task aTask){
        taskQueue.add(aTask);
    }


}
