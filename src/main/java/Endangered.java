import org.sql2o.Connection;

import java.sql.Timestamp;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Endangered  extends Animal implements DatabaseManagement {
    private int id;
    private int endangeredLevel;
    public static final int MAX_ENDANGERED_LEVEL = 10;
    public static final String DATABASE_TYPE = "endangered";


    public Endangered(String name, String health,String age,int sightingId) {
        this.name = name;
        this.health=health;
        this.age=age;
        this.sightingId = sightingId;
        this.healthLevel = MAX_HEALTH_LEVEL / 2;
        this.illLevel = MAX_ILL_LEVEL / 2;
        this.okayLevel = MAX_OKAY_LEVEL / 2;
        this.adultLevel = MAX_ADULT_LEVEL / 2;
        this.youngLevel = MAX_YOUNG_LEVEL / 2;
        this.newbornLevel = MAX_NEWBORN_LEVEL / 2;
        timer = new Timer();
        endangeredLevel = MAX_ENDANGERED_LEVEL / 2;
        type = DATABASE_TYPE;

    }
    public int getEndangeredLevel(){
        return endangeredLevel ;
    }
    public void saving(){
        if (endangeredLevel >= MAX_ENDANGERED_LEVEL){
            throw new UnsupportedOperationException("You have reached maximum number of saving!");
        }
        endangeredLevel++;
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
            endangeredLevel--;
        }
    }
    public void startTimer(){
        Endangered  currentEndangered = this;
        TimerTask timerTask = new TimerTask(){
            @Override
            public void run() {
                if (!currentEndangered .isAlive()){
                    cancel();
                }
                depleteLevels();
            }
        };
        this.timer.schedule(timerTask, 0, 600);
    }
    @Override
    public boolean equals(Object otherEndangered) {
        if (!(otherEndangered instanceof Endangered )) {
            return false;
        } else {
            Endangered  newEndangered = (Endangered)  otherEndangered ;
            return this.getName().equals(newEndangered.getName()) &&
                    this.getHealth() .equals(newEndangered.getHealth() ) &&
                    this.getAge().equals(newEndangered.getAge() ) &&
                    this.getSightingId() == newEndangered .getSightingId();
        }
    }
    public static List<Endangered> all() {
        String sql = "SELECT * FROM animals WHERE type='endangered' ORDER BY timeSpotted DESC ;";
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery(sql)
                    .throwOnMappingFailure(false)
                    .executeAndFetch(Endangered.class);
        }
    }
    public static Endangered find(int id) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM animals where id=:id";
            Endangered endangered = con.createQuery(sql)
                    .addParameter("id", id)
                    .throwOnMappingFailure(false)
                    .executeAndFetchFirst(Endangered.class);
            return endangered;
        }
    }
}