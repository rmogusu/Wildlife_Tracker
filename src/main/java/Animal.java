import org.sql2o.Connection;

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


    public Timestamp timeSpotted;
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

    public void save() {
        try (Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO animals (name, sightingId) VALUES (:name, :sightingId)";
            con.createQuery(sql)
                    .addParameter("name", this.name)
                    .addParameter("sightingId", this.sightingId)
                    .executeUpdate();
        }
    }
}