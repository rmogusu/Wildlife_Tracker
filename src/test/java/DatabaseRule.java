import org.junit.rules.ExternalResource;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

public class DatabaseRule extends ExternalResource {
    @Override
    protected void before() {
        DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/wildlife_tracker_test", "rose", "wambua");
    }

    @Override
    protected void after() {
        try (Connection con = DB.sql2o.open()) {
            String deleteSightingsQuery = "DELETE FROM sightings *;";
            String deleteAnimalsQuery = "DELETE FROM animals *;";
//            String deleteRangersQuery = "DELETE FROM rangers *;";
           con.createQuery(deleteSightingsQuery).executeUpdate();
            con.createQuery(deleteAnimalsQuery).executeUpdate();
//            con.createQuery(deleteRangersQuery).executeUpdate();
        }
    }
}