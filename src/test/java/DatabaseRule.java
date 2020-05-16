import org.junit.rules.ExternalResource;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

public class DatabaseRule extends ExternalResource {
    @Override
    protected void before() {
        DB.sql2o = new Sql2o("jdbc:postgresql://@ec2-52-44-166-58.compute-1.amazonaws.com:5432/dck10hh0ja7lcm","dck10hh0ja7lcm", "6a200a17f78d8aba03bb4e37ecb58a1d3b9744c939bd72e96462fa940db17449");
        //DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/wildlife_tracker_test", "rose", "wambua");
    }

    @Override
    protected void after() {
        try (Connection con = DB.sql2o.open()) {
            String deleteSightingsQuery = "DELETE FROM sightings *;";
            String deleteAnimalsQuery = "DELETE FROM animals *;";
           String deleteRangersQuery = "DELETE FROM rangers *;";
           con.createQuery(deleteSightingsQuery).executeUpdate();
           con.createQuery(deleteAnimalsQuery).executeUpdate();
           con.createQuery(deleteRangersQuery).executeUpdate();
        }
    }
}