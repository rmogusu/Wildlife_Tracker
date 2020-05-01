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
    public int newbornLevel;
    public int youngLevel;
    public int adultLevel;

    public Timestamp timeSported;
    public Timestamp lastHealthy;
    public Timestamp lastIll;
    public Timestamp lastOkay;

}
