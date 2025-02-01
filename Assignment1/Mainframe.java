import java.util.ArrayList;
import java.time.LocalDate;
import java.time.Period;

public class Mainframe{
    private ArrayList<InsuredPerson> clients;
    private final int MAX_DATE;
    
    public Mainframe(){
        this.clients = new ArrayList<InsuredPerson>();
        this.MAX_DATE = 60;
    }
    
    public void addClient(String fullName, String DOB){
        InsuredPerson person = new InsuredPerson(fullName, DOB);
        this.clients.add(person);
    }
    
    public void addClaim(int clientIndex, String claimDate, double claimValue){
        if(clientIndex > 0 && clientIndex < clients.size()){
            InsuredPerson person = this.clients.get(clientIndex);
            if(person != null)
                person.addClaim(claimDate, claimValue);
        }else
            System.out.println("Error...");
    }
    
    public boolean validateDayRange(LocalDate claimDate){
        LocalDate currDate = LocalDate.now();
        Period period = Period.between(claimDate, currDate);
        int days = period.getDays();
        if(days > 60 || claimDate.isAfter(currDate))
            return false;
        else
            return true;
    }
    
    public void processClaims(){
        for(InsuredPerson client: clients){
            ArrayList<Claim> claims = client.getClaims();
            for(Claim claim: claims){
                if(this.validateDayRange(claim.getClaimDate())){
                    claim.disburse(true);
                    client.recordDisbursements(claim.getClaimValue());
                }
                else{
                    claim.disburse(false);
                    claim.setClaimNote("CLAIM OLDER THAN 60 DAYS / INVALID DATE");
                }
            }
        }
    }
    
    public String getReports(){
        String output="";
        for(InsuredPerson client: clients){
            if(client != null)
                output += client.toString() + "\n";
        }
        return output;
    }
}