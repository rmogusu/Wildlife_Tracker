import org.sql2o.Connection;

import java.util.List;
import java.util.Timer;

public class Endangered  extends Animal {
    private int id;
    public Endangered(String name, int sightingId) {
        this.name = name;
        this.sightingId = sightingId;
//        this.healthyLevel = MAX_HEALTHY_LEVEL / 2;
//        this.illLevel = MAX_ILL_LEVEL / 2;
//        this.okayLevel = MAX_OKAY_LEVEL / 2;
//        endangeredLevel = MAX_ENDANGERED_LEVEL / 2;
//        type = DATABASE_TYPE;
//        timer = new Timer();
    }

    public static List<Endangered> all() {
        String sql = "SELECT * FROM animals";
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery(sql)
                    .executeAndFetch(Endangered.class);
        }
    }
    public static Endangered find(int id) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM animals where id=:id";
            Endangered endangered = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Endangered.class);
            return endangered;
        }
    }
}