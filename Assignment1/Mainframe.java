import java.util.ArrayList;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.time.temporal.ChronoUnit;

public class Mainframe{
    private ArrayList<InsuredPerson> clients;
    private final int MAX_DATE;
    
    public Mainframe(){
        this.clients = new ArrayList<InsuredPerson>();
        this.MAX_DATE = 60;
    }
    
    public ArrayList getClients(){return this.clients;}
    public int getMAX_DATE(){return MAX_DATE;}
    
    public void addClient(String fullName, String DOB){
        InsuredPerson person = new InsuredPerson(fullName, DOB);
        this.clients.add(person);
    }
    
    public void addClaim(int clientIndex, String claimDate, double claimValue){
        if(clientIndex >= 0 && clientIndex < clients.size()){
            InsuredPerson person = this.clients.get(clientIndex);
            if(person != null)
                person.addClaim(claimDate, claimValue);
        }else
            System.out.println("Error...");
    }
    
    public boolean validateDayRange(LocalDate claimDate){
        LocalDate currDate = LocalDate.now();
        long days = ChronoUnit.DAYS.between(claimDate, currDate);
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
    
    public LocalDate convertDate(String dateString){
        if(dateString == null)
            return null;
            
        try{
            String[] parts = dateString.split(" ");
            String day = parts[0].substring(0, (parts[0].length()-2));
            String cleanDate = day + " " + parts[1] + " " + parts[2];
            
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy", Locale.ENGLISH);
            LocalDate date = LocalDate.parse(cleanDate, formatter);
            return date;
        }
        catch(Exception e){
            return null;
        }
        
    }
}