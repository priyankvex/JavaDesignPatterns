package observerpattern;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by priyankvex on 15/5/17.
 *
 * Observer Pattern :
 * It defines one to many dependency between objects so that when one object changes state,
 * all of its dependents are notified automatically.
 *
 * In this demo, we'll create a score observable that will emit scores of a tennis game.
 * As tennis players like to be famous, their scores will be sent to many observers.
 *
 * Observers can be added or removed at runtime.
 *
 * Design Principle :
 * Strive for loosely coupled designed between objects that interact.
 */
public class TennisScoreObserverPattern {

    public static void main(String[] args) {

        Observable scoreObservable = new TennisScoreObservable();
        // create the observers
        Observer fancyScorePanelObserver = new FancyScorePanel();
        Observer oldSchoolScorePanelObserver = new OldSchoolScorePanel();
        Observer gameStatsObserver = new GameStatsObserver();
        // register the observers
        scoreObservable.registerObserver(fancyScorePanelObserver);
        scoreObservable.registerObserver(oldSchoolScorePanelObserver);
        scoreObservable.registerObserver(gameStatsObserver);
        // update the score
        scoreObservable.updateScore("Game not yet started");
        // send some nice tennis score
        scoreObservable.updateScore("30-40");
        // send error
        scoreObservable.updateScore(null);
        // we don't need old school display panel. De-register the old school panel observer
        scoreObservable.removeObserver(oldSchoolScorePanelObserver);
        // new score update!
        scoreObservable.updateScore("40-40");
    }

    /**
     * Interface for the observable objects
     */
    private interface Observable{
        void registerObserver(Observer observer);
        void removeObserver(Observer observer);
        void updateScore(String score);
        void notifyObservers();
        void notifyObservers(String errorMessage);
    }

    /**
     * Interface for observer objects
     */
    private interface Observer {
        void update(Observable observable, String errorMessage);
    }

    /**
     * Observable that emits tennis scores.
     * To qualify as observable it must implement the {@link Observable} interface
     */
    private static class TennisScoreObservable implements Observable{

        private String currentScore;

        private List<Observer> observers;

        TennisScoreObservable(){
            currentScore = "SET1##0-0##0-0";
            observers = new LinkedList<>();
        }

        @Override
        public void registerObserver(Observer observer) {
            observers.add(observer);
        }

        @Override
        public void removeObserver(Observer observer) {
            int position = observers.indexOf(observer);
            observers.remove(position);
        }

        /**
         * Notify all observers to pull the nee scores from the observable
         */
        @Override
        public void notifyObservers() {
            for (Observer observer : observers){
                // observers will pull the data from the observer when notified
                observer.update(this, null);
            }
        }

        /**
         * Push the error message to all observers
         * @param errorMessage error message. Hopefully, not fatal!
         */
        @Override
        public void notifyObservers(String errorMessage) {
            for (Observer observer : observers){
                // push the error message to all the observers
                observer.update(this, errorMessage);
            }
        }

        String getCurrentScore() {
            return currentScore;
        }

        /**
         * updates the score or handles the error case.
         * Uses push or pull strategy accordingly.
         * @param currentScore current score in string format. null for error.
         */
        @Override
        public void updateScore(String currentScore) {
            if (currentScore == null){
                this.notifyObservers("Error while fetching score");
            }
            else {
                this.currentScore = currentScore;
                this.notifyObservers();
            }
        }
    }

    /**
     * Observer for the game stats engine.
     * Helps make the commentators sound smart.
     */
    private static class GameStatsObserver implements Observer {

        @Override
        public void update(Observable observable, String errorMessage) {
            if (errorMessage == null){
                // no error message was pushed
                if (observable instanceof TennisScoreObservable){
                    TennisScoreObservable scoreObservable = (TennisScoreObservable)observable;
                    String score = scoreObservable.getCurrentScore();
                    System.out.println("Score " + score + " sent to game stats engine");
                }
            }
            else{
                // an error message was pushed
                System.out.println("An error occurred! Can't get scores");
            }
        }
    }

    /**
     * Observer for an old school score panel.
     * Tennis players care for their poor fans.
     */
    private static class OldSchoolScorePanel implements Observer {

        @Override
        public void update(Observable observable, String errorMessage) {
            if (errorMessage == null){
                // no error message was pushed
                if (observable instanceof TennisScoreObservable){
                    TennisScoreObservable scoreObservable = (TennisScoreObservable)observable;
                    String score = scoreObservable.getCurrentScore();
                    System.out.println("Score in old school score panel : " + score);
                }
            }
            else{
                // an error message was pushed
                System.out.println("An error occurred! Can't get scores");
            }
        }
    }

    /**
     * Observer for a fancy score panel.
     * What's is not old school, is fancy.
     */
    private static class FancyScorePanel implements Observer {

        @Override
        public void update(Observable observable, String errorMessage) {
            if (errorMessage == null){
                // no error message was pushed
                if (observable instanceof TennisScoreObservable){
                    TennisScoreObservable scoreObservable = (TennisScoreObservable)observable;
                    String score = scoreObservable.getCurrentScore();
                    System.out.println("Score in fancy display panel : " + score);
                }
            }
            else{
                // an error message was pushed
                System.out.println("An error occurred! Can't get scores");
            }
        }
    }
}
