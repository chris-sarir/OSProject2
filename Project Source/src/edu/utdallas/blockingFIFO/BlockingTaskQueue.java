package edu.utdallas.blockingFIFO;

import edu.utdallas.taskExecutor.Task;

public class BlockingTaskQueue {
	/*Implementation of the Blocking FIFO Queue*/

    private  Task[] taskArray = new Task[100];
    private  int addPointer = 0;		//addPointer points to next index to add a new Task to
    //when the next index is !null, then block the calling thread

    private int removePointer = 0;	//removePointer points to next index to remove a Task from
    //when the next index is null, then block the calling thread

    Object monitor = new Object();

    public BlockingTaskQueue(){
        synchronized(monitor) {
            monitor.notify();
        }
    }


    public void add (Task task)
    {
        try{
            while(taskArray[addPointer] != null){
                synchronized(monitor) {  // Start Critical Section
                    monitor.wait();
                }
            }

            taskArray[addPointer] = task;
            addPointer = (addPointer + 1) % 100;

            synchronized(monitor) { // End Critical Section
                monitor.notify();
            }

        }catch(InterruptedException ex){
            System.out.println("Error");
        }
    }

    public Task remove ()
    {

        Task t = null;
        try{
            while(taskArray[removePointer] == null){
                synchronized(monitor) {  // Start Critical Section
                    monitor.wait();
                }
            }

            t = taskArray[removePointer];
            taskArray[removePointer] = null;
            removePointer = (removePointer + 1)%100;

            synchronized(monitor) { // End Critical Section
                monitor.notify();
            }

        }catch(InterruptedException ex){

        }
        return t;
    }

}
