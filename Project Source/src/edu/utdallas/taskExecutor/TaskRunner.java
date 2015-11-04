package edu.utdallas.taskExecutor;

import edu.utdallas.blockingFIFO.BlockingTaskQueue;

public class TaskRunner implements Runnable{
    private BlockingTaskQueue taskQueue = null;
    private Task nextTask = null;
    private String taskName = "";
    private int taskNumber = 0;
    private static int number = 0;


    public TaskRunner(){
        taskNumber = number++;
    }

    public TaskRunner(String name){
        taskName = name;
        taskNumber = number++;
    }

    public TaskRunner(String name, BlockingTaskQueue blockqueue){
        taskName = name;
        taskQueue = blockqueue;
        taskNumber = number++;
    }

    public String getName(){
        return taskName;
    }

    public int getNumber(){
        return taskNumber;
    }

    public void setBlockingQueue(BlockingTaskQueue blockqueue){

        taskQueue = blockqueue;

    }

    public Task take(){
        return taskQueue.remove();
    }

    public void run(){
        while(true){
            nextTask = take();

            try{
                nextTask.execute();
            }catch(Throwable th){
                //do nothing
                //System.err.println(th.getMessage);
            }
        }
    }
}