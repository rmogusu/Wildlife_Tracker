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
}
