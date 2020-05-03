import org.sql2o.Connection;

import java.util.List;

public class Sighting implements DatabaseManagement {
    private String species;
    private String location;
    private String rangerName;
    private int id;


    public Sighting(String rangerName, String species, String location) {

        this.species = species;
        this.location = location;
        this.rangerName = rangerName;

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

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object otherSighting) {
        if (!(otherSighting instanceof Sighting)) {
            return false;
        } else {
            Sighting newSighting = (Sighting) otherSighting;
            return this.getRangerName().equals(newSighting.getRangerName()) &&
                    this.getSpecies().equals(newSighting.getSpecies()) &&
                    this.getLocation().equals(newSighting.getLocation());
        }

    }
    @Override
    public void save() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO sightings (rangerName,species,location) VALUES (:rangerName, :species,:location)";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("rangerName", this.rangerName)
                    .addParameter("species", this.species)
                    .addParameter("location", this.location)
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
    public List<Endangered> getEndangered() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM animals where sightingId=:id";
            return con.createQuery(sql)
                    .addParameter("id", this.id)
                    .executeAndFetch(Endangered .class);
        }
    }
    public List<UnEndangered> getUnEndangered() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM animals where sightingId=:id";
            return con.createQuery(sql)
                    .addParameter("id", this.id)
                    .executeAndFetch(UnEndangered .class);
        }
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
