package edu.utdallas.taskExecutorImpl;

import java.util.List;
import java.util.ArrayList;
import edu.utdallas.blockingFIFO.BlockingTaskQueue;
import edu.utdallas.taskExecutor.TaskRunner;
import edu.utdallas.taskExecutor.Task;

/**
 * Created by assar on 11/2/2015.
 */
public class TaskExecutorImpl {

    List<TaskRunner> runnerPool;
    BlockingTaskQueue taskQueue;

    public TaskExecutorImpl(int threadCount){

        taskQueue = new BlockingTaskQueue();

        runnerPool = new ArrayList<TaskRunner>();

        for(int i = 0; i < threadCount; i++){
            runnerPool.add(new TaskRunner("TaskThread " + Integer.toString(i), taskQueue));

            new Thread(runnerPool.get(i), runnerPool.get(i).getName()).start();
        }
    }

    public void addTask (Task aTask){
        taskQueue.add(aTask);
    }
}
