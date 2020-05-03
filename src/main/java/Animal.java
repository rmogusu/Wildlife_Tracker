import org.sql2o.Connection;

import java.sql.Timestamp;
import java.util.Timer;
import java.util.TimerTask;

public abstract class Animal {

    public String name;
    public String health;
    public String age;
    public int sightingId;
    public int id;
    public String type;

    public int healthLevel;
    public int illLevel;
    public int okayLevel;
    public int adultLevel;
    public int youngLevel;
    public int newbornLevel;


    public static final int MAX_HEALTH_LEVEL = 37;
    public static final int MAX_ILL_LEVEL = 10;
    public static final int MAX_OKAY_LEVEL = 20;
    public static final int MIN_ALL_LEVELS = 0;

    public static final int MAX_ADULT_LEVEL = 70;
    public static final int MAX_YOUNG_LEVEL = 18;
    public static final int MAX_NEWBORN_LEVEL = 3;

    public Timestamp timeSpotted;
    public Timestamp lastHealth;
    public Timestamp lastIll;
    public Timestamp lastOkay;
    public Timer timer;

    public String getName() {
        return name;
    }

    public int getSightingId() {
        return sightingId;
    }

    public String getHealth() {
        return health;
    }

    public String getAge() {
        return age;
    }

    public int getId() {
        return id;
    }
    public int getHealthLevel(){
        return healthLevel;
    }
    public int getIllLevel(){
        return illLevel;
    }
    public int getOkayLevel(){
        return okayLevel;
    }
    public int getAdultLevel(){
        return adultLevel;
    }
    public int getYoungLevel(){
        return youngLevel;
    }
    public int getNewbornLevel(){
        return newbornLevel;
    }
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
    public void depleteLevels() {
        if (isAlive()) {
            healthLevel--;
            illLevel--;
            okayLevel--;
            adultLevel--;
            youngLevel--;
            newbornLevel--;
        }
    }
    public void ill(){
        if (illLevel >= MAX_ILL_LEVEL){
            throw new UnsupportedOperationException("Your ill is at maximum level.!");
        }
        try(Connection con = DB.sql2o.open()) {
            String sql = "UPDATE animals SET lastill = now() WHERE id = :id";
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        }
        illLevel++;
    }
    public void okay(){
        if (okayLevel >= MAX_OKAY_LEVEL){
            throw new UnsupportedOperationException("Your okay is at maximum level.!");
        }
        try(Connection con = DB.sql2o.open()) {
            String sql = "UPDATE animals SET lastokay = now() WHERE id = :id";
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        }
        okayLevel++;
    }
    public void health(){
        if (healthLevel >= MAX_HEALTH_LEVEL){
            throw new UnsupportedOperationException("Your health is at maximum level.!");
        }
        try(Connection con = DB.sql2o.open()) {
            String sql = "UPDATE animals SET lasthealth = now() WHERE id = :id";
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        }
        healthLevel++;
    }
    public Timestamp getTimeSpotted (){
        return timeSpotted ;
    }
    public Timestamp getLastHealth(){
        return lastHealth ;
    }
    public Timestamp getLastIll(){
        return lastIll;
    }
    public Timestamp getLastOkay(){
        return lastOkay;
    }

    public void save() {
        try (Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO animals (name, health,age,sightingId,timeSpotted,type) VALUES (:name, :health,:age,:sightingId,now(),:type)";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("name", this.name)
                    .addParameter("health", this.health)
                    .addParameter("age", this.age)
                    .addParameter("sightingId", this.sightingId)
                    .addParameter("type", this.type)
                    .executeUpdate()
                    .getKey();
        }
    }
    public void delete() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "DELETE FROM animals WHERE id = :id;";
            con.createQuery(sql)
                    .addParameter("id", this.id)
                    .executeUpdate();
        }
    }
}
