package SingletonPattern;

import java.util.PriorityQueue;

/**
 * In this example, we create the singleton object eagerly to achieve thread safety.
 * This allows us to get away with putting the getInstance() method inside synchronized block
 * as synchronized blocks are expensive.
 *
 * We'll use the singleton pattern to instantiate a JobQueue class as we only need one queue to be
 * handling the jobs pool.
 *
 * Though this approach is very efficient and effective against thread safety.
 * One drawback is lack of generics if you want that. As static variables can not be initialized by
 * generic types.
 *
 * Created by priyankvex on 9/7/17.
 */
public class EagerSingleton {

    static class JobQueue{

        /**
         * The JVM guarantees that the instance is created before any thread accesses the
         * static instance variable.
         */
        private static JobQueue instance = new JobQueue();

        private PriorityQueue<String> jobQueue;

        private JobQueue(){
            // make the constructor private
            jobQueue = new PriorityQueue<String>();
        }

        public static JobQueue getInstance(){
            return instance;
        }

        public void addJob(String tag){
            System.out.println("Job added with tag " + tag);
            jobQueue.add(tag);
        }
    }

    public static void main(String[] args){

        JobQueue jobQueue = JobQueue.getInstance();
        jobQueue.addJob("job_tag_123213");
    }

}
