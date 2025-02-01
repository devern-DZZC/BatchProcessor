import java.time.LocalDate;

public class Claim{
    String claimNo;
    int claimCounter;
    LocalDate claimDate;
    LocalDate processedDate;
    double claimValue;
    boolean disbursed;
    String claimNote;
    
    public Claim(String claimDate, double claimValue, String claimOwnerInitials){
        this.claimDate = LocalDate.parse(claimDate);
        this.claimValue = claimValue;
        this.claimNo = claimOwnerInitials;
        
    }
    
}