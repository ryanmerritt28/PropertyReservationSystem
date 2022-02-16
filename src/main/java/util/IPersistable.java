package util;

public interface IPersistable {
    
    //void writeToDB (String dbname);
    //void writeToFile();
    int Persist();
    static final String dbname = "Team1_ReservationSystem";
    static final String path = "Data/";
}
