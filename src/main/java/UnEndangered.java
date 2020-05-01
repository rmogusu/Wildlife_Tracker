import org.sql2o.Connection;

import java.util.List;

public class UnEndangered extends Animal{
    private int id;
    public UnEndangered(String name, int sightingId) {
        this.name = name;
        this.sightingId = sightingId;
//        this.healthyLevel = MAX_HEALTHY_LEVEL / 2;
//        this.illLevel = MAX_ILL_LEVEL / 2;
//        this.okayLevel = MAX_OKAY_LEVEL / 2;
//        endangeredLevel = MAX_ENDANGERED_LEVEL / 2;
//        type = DATABASE_TYPE;
//        timer = new Timer();
    }

    public static List<UnEndangered> all() {
        String sql = "SELECT * FROM animals";
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery(sql)
                    .executeAndFetch(UnEndangered.class);
        }
    }
    public static UnEndangered find(int id) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM animals where id=:id";
            UnEndangered unendangered = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(UnEndangered.class);
            return unendangered;
        }
    }
}
