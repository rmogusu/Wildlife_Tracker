import org.sql2o.Connection;

import java.sql.Timestamp;
import java.util.Timer;

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
    public Timestamp lastHealthy;
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
    public void depleteLevels(){
        healthLevel--;
        illLevel--;
        okayLevel--;
        adultLevel--;
        youngLevel--;
        newbornLevel--;
    }
    public void ill(){
        illLevel++;
    }
    public void okay(){
        okayLevel++;
    }
    public void health(){
        healthLevel++;
    }

//    @Override
//    public boolean equals(Object otherAnimal) {
//        if (!(otherAnimal instanceof Animal)) {
//            return false;
//        } else {
//            Animal newAnimal = (Animal) otherAnimal;
//            return this.getName().equals(newAnimal.getName()) &&
//                    this.getSightingId() == newAnimal.getSightingId();
//        }
//    }

    public void save() {
        try (Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO animals (name, health,age,sightingId) VALUES (:name, :health,:age,:sightingId)";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("name", this.name)
                    .addParameter("health", this.health)
                    .addParameter("age", this.age)
                    .addParameter("sightingId", this.sightingId)
                    .executeUpdate()
                    .getKey();
        }
    }
}
