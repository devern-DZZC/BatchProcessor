import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.text.NumberFormat;

public class InsuredPerson{
    private String fullName;
    private LocalDate DOB;
    private ArrayList<Claim> claims;
    private double disbursements;
    
    public InsuredPerson(String fullName, String DOB){
        this.fullName = fullName;
        this.DOB = LocalDate.parse(DOB);
        this.claims = new ArrayList<Claim>();
        this.disbursements = 0;
    }
    
    public String getFullName() {return this.fullName;}
    public LocalDate getDOB() {return this.DOB;}
    public ArrayList<Claim> getClaims() {return this.claims;}
    public double getDisbursements() {return this.disbursements;}
    
    public int getAge(){
        LocalDate currDate = LocalDate.now();
        Period period = Period.between(getDOB(), currDate);
        return period.getYears();
    }
    
    public String getInitials(){
        String name = getFullName();
        String[] names = name.split(" ");
        String initials = "";
        
        for(String n: names){
            initials += n.substring(0,1);
        }
        return initials;
    }
    
    public void addClaim(String claimDate, double claimValue){
        Claim claim = new Claim(claimDate, claimValue, getInitials());
        this.claims.add(claim);
    }
    
    public void recordDisbursements(double value){
        for(Claim c: claims){
            if(c.getDisbursed()){
                this.disbursements += value;
            }
        }
    }
    
    public String toString(){
        String output = getFullName() + " " + getDOB() + " " + getAge() + " " + "YRS\n";
        for(Claim c: claims){
            output += c.toString() + "\n";
        }
        
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        String formattedAmount = currency.format(getDisbursements());
        
        output += "DISBURSEMENTS: " + formattedAmount;
        return output;
    }
}