
import org.sql2o.*;
import java.net.URI;
import java.net.URISyntaxException;
public class DB {
    public static Sql2o sql2o = new Sql2o("jdbc:postgresql://ec2-34-235-108-68.compute-1.amazonaws.com:5432/d9h45fe3o5b0fq","hgugbppvqhhfpj", "fb3a49599658e8899882d153779f9d0c9420ac8b66be90a9f2767b1bf8b10205");

    //public static Sql2o sql2o = new Sql2o("jdbc:postgresql://localhost:5432/wildlife_tracker", "rose", "wambua");

//    private static URI dbUri;
//    public static Sql2o sql2o;
//
//    static {
//
//        try {
//            if (System.getenv("DATABASE_URL") == null) {
//                dbUri = new URI("postgres://localhost:5432/wildlife_tracker");
//            } else {
//                dbUri = new URI(System.getenv("DATABASE_URL"));
//            }
//
//            int port = dbUri.getPort();
//            String host = dbUri.getHost();
//            String path = dbUri.getPath();
//            String username = (dbUri.getUserInfo() == null) ? null : dbUri.getUserInfo().split(":")[0];
//            String password = (dbUri.getUserInfo() == null) ? null : dbUri.getUserInfo().split(":")[1];
//
//            sql2o = new Sql2o("jdbc:postgresql://" + host + ":" + port + path, username, password);
//        } catch (URISyntaxException e) {
//        }
//    }
}
