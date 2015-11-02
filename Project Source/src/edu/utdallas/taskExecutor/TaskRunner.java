package edu.utdallas.taskExecutor;

import java.lang.reflect.Array;

/**
 * Created by mdw130530- on 11/2/2015.
 */

public class TaskRunner implements Runnable{
    //private BlockingQueue taskQueue = null; //Cannot use BlockingQueue from concurrent
    private Class<?> taskQueue = null;
    private Task nextTask = null;

    TaskRunner(){

    }

    TaskRunner(Class<?> blockqueue){
        taskQueue = blockqueue;
    }

    public void setBlockingQueue(Class<?> blockqueue){
        //if (blockqueue instanceof BlockingTaskQueue );
        taskQueue = blockqueue;
        // else throw exception
    }

    public Task take(){
        return taskQueue.remove();
        // this.BlockingTaskQueue.remove();
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
