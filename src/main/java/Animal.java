import java.sql.Timestamp;
import java.util.Timer;

public abstract class Animal {
    public Timer timer;
    public String name;
    public int sightingId;
    public int id;
    public String type;

    public int healthyLevel;
    public int illLevel;
    public int okayLevel;


    public Timestamp timeSported;
    public Timestamp lastHealthy;
    public Timestamp lastIll;
    public Timestamp lastOkay;

    public String getName() {
        return name;
    }

    public int getSightingId() {
        return sightingId;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object otherAnimal) {
        if (!(otherAnimal instanceof Animal)) {
            return false;
        } else {
            Animal newAnimal = (Animal) otherAnimal;
            return this.getName().equals(newAnimal.getName()) &&
                    this.getSightingId() == newAnimal.getSightingId();
        }
    }

}
