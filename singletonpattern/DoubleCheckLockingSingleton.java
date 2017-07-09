package singletonpattern;

import java.util.PriorityQueue;

/**
 * In this example we are going to implement a thread safe singleton pattern
 * using double check locking.
 * In this method, first we create if the instance is created and if not,
 * then we synchronize.
 * It saves us from synchronizing every time we all getInstance().
 *
 * Created by priyankvex on 9/7/17.
 */
public class DoubleCheckLockingSingleton {

    public static class JobQueue{

        /**
         * volatile keyword ensures that multiples threads handle the instance variable
         * correctly, when it is being initialized .
         */
        private volatile static JobQueue instance;

        private PriorityQueue<String> jobQueue;

        private JobQueue(){
            jobQueue = new PriorityQueue<String>();
        }

        public static JobQueue getInstance(){
            if (instance == null){
                synchronized (JobQueue.class){
                    if (instance == null){
                        instance = new JobQueue();
                    }
                }
            }
            return instance;
        }

        public void addJob(String jobTag){
            jobQueue.add(jobTag);
            System.out.println(String.format("Job added with tag %s", jobTag));
        }

    }

    public static void main(String[] args){
        JobQueue jobQueue = JobQueue.getInstance();
        jobQueue.addJob("job_tag_12334");
    }
}
