import org.sql2o.Connection;

public class Sighting {
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
    public void save() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO sightings (rangerName,species,location) VALUES (:rangerName, :species,:location)";
            con.createQuery(sql)
                    .addParameter("rangerName", this.rangerName)
                    .addParameter("species", this.species)
                    .addParameter("location", this.location)
                    .executeUpdate();
        }
    }
}
