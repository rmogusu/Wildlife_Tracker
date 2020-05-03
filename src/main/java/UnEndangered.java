import org.sql2o.Connection;

import java.sql.Timestamp;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class UnEndangered extends Animal implements DatabaseManagement{
    private int id;
    private int unendangeredLevel;
    public static final int MAX_UNENDANGERED_LEVEL = 10;
    public static final String DATABASE_TYPE = "unEndangered";
    public Timestamp lastConserved;

    public UnEndangered(String name,String health,String age, int sightingId) {
        this.name = name;
        this.health =health;
        this.age=age;
        this.sightingId = sightingId;
        this.healthLevel = MAX_HEALTH_LEVEL / 2;
        this.illLevel = MAX_ILL_LEVEL / 2;
        this.okayLevel = MAX_OKAY_LEVEL / 2;
        this.adultLevel = MAX_ADULT_LEVEL / 2;
        this.youngLevel = MAX_YOUNG_LEVEL / 2;
        this.newbornLevel = MAX_NEWBORN_LEVEL / 2;
        timer = new Timer();
        unendangeredLevel = MAX_UNENDANGERED_LEVEL / 2;
        type = DATABASE_TYPE;

    }
    public int getUnEndangeredLevel(){
        return unendangeredLevel ;
    }
    public void conserve(){
        if (unendangeredLevel >= MAX_UNENDANGERED_LEVEL){
            throw new UnsupportedOperationException("You have reached maximum number of conserving!");
        }

        unendangeredLevel++;
    }

    @Override
    public void depleteLevels(){
        if (isAlive()){
            healthLevel--;
            illLevel--;
            okayLevel--;
            adultLevel--;
            youngLevel--;
            newbornLevel--;
            unendangeredLevel--;
        }
    }
    @Override
    public boolean isAlive() {
        if (healthLevel <= MIN_ALL_LEVELS ||
                illLevel <= MIN_ALL_LEVELS ||
                okayLevel <= MIN_ALL_LEVELS ||
                adultLevel <= MIN_ALL_LEVELS ||
                youngLevel <= MIN_ALL_LEVELS ||
                newbornLevel <= MIN_ALL_LEVELS) {
            return false;
        }
        return true;
    }
    public void startTimer(){
        UnEndangered  currentUnEndangered = this;
        TimerTask timerTask = new TimerTask(){
            @Override
            public void run() {
                if (!currentUnEndangered .isAlive()){
                    cancel();
                }
                depleteLevels();
            }
        };
        this.timer.schedule(timerTask, 0, 600);
    }
    @Override
    public boolean equals(Object otherUnEndangered) {
        if (!(otherUnEndangered instanceof UnEndangered )) {
            return false;
        } else {
            UnEndangered  newUnEndangered = (UnEndangered)  otherUnEndangered ;
            return this.getName().equals(newUnEndangered.getName()) &&
                    this.getHealth() .equals(newUnEndangered.getHealth() ) &&
                    this.getAge() .equals(newUnEndangered.getAge() ) &&
                    this.getSightingId() == newUnEndangered .getSightingId();
        }
    }
    public static List<UnEndangered> all() {
        String sql = "SELECT * FROM animals WHERE type='unEndangered';";
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery(sql)
                    .throwOnMappingFailure(false)
                    .executeAndFetch(UnEndangered.class);
        }
    }

    public static UnEndangered find(int id) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM animals where id=:id";
            UnEndangered unendangered = con.createQuery(sql)
                    .addParameter("id", id)
                    .throwOnMappingFailure(false)
                    .executeAndFetchFirst(UnEndangered.class);
            return unendangered;
        }
    }
}
