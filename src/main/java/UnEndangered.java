import org.sql2o.Connection;

import java.util.List;

public class UnEndangered extends Animal{
    private int id;
    public UnEndangered(String name,String health,String age, int sightingId) {
        this.name = name;
        this.health =health;
        this.age=age;
        this.sightingId = sightingId;
        this.healthLevel = MAX_HEALTH_LEVEL / 2;
        this.illLevel = MAX_ILL_LEVEL / 2;
        this.okayLevel = MAX_OKAY_LEVEL / 2;
        this.adultLevel = MAX_ADULT_LEVEL / 2;
        this.youngLevel = MAX_YOUNG_LEVEL / 2;
        this.newbornLevel = MAX_NEWBORN_LEVEL / 2;
//        endangeredLevel = MAX_ENDANGERED_LEVEL / 2;
//        type = DATABASE_TYPE;
//        timer = new Timer();
    }
    public int getHealthLevel(){
        return healthLevel;
    }
    public int getIllLevel(){
        return illLevel;
    }
    public int getOkayLevel(){
        return okayLevel;
    }
    public int getAdultLevel(){
        return adultLevel;
    }
    public int getYoungLevel(){
        return youngLevel;
    }
    public int getNewbornLevel(){
        return newbornLevel;
    }
    @Override
    public boolean equals(Object otherUnEndangered) {
        if (!(otherUnEndangered instanceof UnEndangered )) {
            return false;
        } else {
            UnEndangered  newUnEndangered = (UnEndangered)  otherUnEndangered ;
            return this.getName().equals(newUnEndangered.getName()) &&
                    this.getHealth() .equals(newUnEndangered.getHealth() ) &&
                    this.getAge() .equals(newUnEndangered.getAge() ) &&
                    this.getSightingId() == newUnEndangered .getSightingId();
        }
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
