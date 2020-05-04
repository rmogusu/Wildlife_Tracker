import org.sql2o.Connection;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Sighting implements DatabaseManagement {
    private String species;
    private String location;
    private String rangerName;
    private int id;
    private Timestamp   timeSpotted;
    private int rangerId;


    public Sighting(String rangerName, String species, String location, int rangerId) {

        this.species = species;
        this.location = location;
        this.rangerName = rangerName;
        this.rangerId =rangerId;

    }
    public String getRangerName() {
        return rangerName;
    }

    public String getSpecies() {
        return species;
    }

    public String getLocation() {
        return location;
    }
    public int getRangerId(){
        return rangerId;
    }
    public int getId() {
        return id;
    }
    public Timestamp  getTimeSpotted(){
        return timeSpotted ;
    }
    @Override
    public boolean equals(Object otherSighting) {
        if (!(otherSighting instanceof Sighting)) {
            return false;
        } else {
            Sighting newSighting = (Sighting) otherSighting;
            return this.getRangerName().equals(newSighting.getRangerName()) &&
                    this.getSpecies().equals(newSighting.getSpecies()) &&
                    this.getLocation().equals(newSighting.getLocation())&&
                    this.getRangerId() == newSighting.getRangerId();
        }

    }
    @Override
    public void save() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO sightings (rangerName,species,location,rangerId,timeSpotted) VALUES (:rangerName, :species,:location,:rangerId,now())";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("rangerName", this.rangerName)
                    .addParameter("species", this.species)
                    .addParameter("location", this.location)
                    .addParameter("rangerId", this.rangerId)
                    .executeUpdate()
                    .getKey();
        }
    }
    public static List<Sighting> all() {
        String sql = "SELECT * FROM sightings";
        try(Connection con = DB.sql2o.open()) {
            return con.createQuery(sql)
                    .executeAndFetch(Sighting.class);
        }
    }
    public static Sighting find(int id) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM sightings where id=:id";
            Sighting sighting = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Sighting.class);
            return sighting;
        }
    }

public List<Object> getAnimals() {
    List<Object> allAnimals = new ArrayList<Object>();
    try(Connection con = DB.sql2o.open()) {
        String sqlEndangered = "SELECT * FROM animals WHERE sightingId=:id AND type='endangered';";
        List<Endangered> endangered= con.createQuery(sqlEndangered)
                .addParameter("id", this.id)
                .throwOnMappingFailure(false)
                .executeAndFetch(Endangered .class);
        allAnimals.addAll(endangered);

        String sqlUnEndangered = "SELECT * FROM animals WHERE sightingId=:id AND type='unEndangered';";
        List<UnEndangered > unEndangered = con.createQuery(sqlUnEndangered)
                .addParameter("id", this.id)
                .throwOnMappingFailure(false)
                .executeAndFetch(UnEndangered .class);
        allAnimals .addAll(unEndangered);
    }

    return allAnimals;
}
    @Override
    public void delete() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "DELETE FROM sightings WHERE id = :id;";
            con.createQuery(sql)
                    .addParameter("id", this.id)
                    .executeUpdate();
        }
    }
}
