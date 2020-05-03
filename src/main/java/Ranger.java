import org.sql2o.Connection;

import java.util.List;

public class Ranger {
    private String name;
    private int badgeNo;
    private int contact;
    private int id;


    public Ranger(String name,int badgeNo, int contact){
        this.badgeNo= badgeNo;
        this.contact = contact;
        this.name=name;

    }
    public String getName() {
        return name;
    }
    public int getBadgeNo() {
        return badgeNo;
    }
    public int getId() {
        return id;
    }
    public int getContact () {
        return contact;
    }
    @Override
    public boolean equals(Object otherRanger) {
        if (!(otherRanger instanceof Ranger)) {
            return false;
        } else {
            Ranger newRanger = (Ranger) otherRanger;
            return this.getName().equals(newRanger.getName()) &&
                    this.getBadgeNo()==newRanger.getBadgeNo() &&
                    this.getContact()==newRanger.getContact();

        }

    }

    public void save() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO rangers (name,badgeNo,contact) VALUES (:name, :badgeNo,:contact)";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("name", this.name)
                    .addParameter("badgeNo", this.badgeNo)
                    .addParameter("contact", this.contact)
                    .executeUpdate()
                    .getKey();
        }
    }
    public static List<Ranger> all() {
        String sql = "SELECT * FROM rangers";
        try(Connection con = DB.sql2o.open()) {
            return con.createQuery(sql)
                    .executeAndFetch(Ranger.class);
        }
    }
    public static Ranger find(int id) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM rangers where id=:id";
            Ranger ranger = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Ranger.class);
            return ranger;
        }
    }
    public void delete() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "DELETE FROM rangers WHERE id = :id;";
            con.createQuery(sql)
                    .addParameter("id", this.id)
                    .executeUpdate();
        }
    }
}
