import java.util.ArrayList;

public class Mainframe{
    private ArrayList<InsuredPerson> clients;
    private int MAX_DATE;
    
    public Mainframe(){
        this.clients = new ArrayList<InsuredPerson>(100);
        this.MAX_DATE = 60;
    }
    
    public void addClient(String fullName, String DOB){
        InsuredPerson person = new InsuredPerson(fullName, DOB);
        this.clients.add(person);
    }
    
    public void addClaim
}