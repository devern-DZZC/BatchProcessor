import java.time.LocalDate;
import java.text.NumberFormat;

public class Claim{
    private String claimNo;
    private static int counter = 1;
    private int claimCounter;
    private LocalDate claimDate;
    private LocalDate processedDate;
    private double claimValue;
    private boolean disbursed;
    private String claimNote;
    
    public Claim(String claimDate, double claimValue, String claimOwnerInitials){
        this.claimDate = LocalDate.parse(claimDate);
        this.claimValue = claimValue;
        this.claimCounter = this.counter;
        this.counter++;
        this.claimNo = claimOwnerInitials + claimCounter;
        this.disbursed = false;
        this.claimNote = "";
        
    }
    
    public String getClaimNo(){return this.claimNo; }
    public int getClaimCounter(){return this.claimCounter;}
    public LocalDate getClaimDate(){return this.claimDate;}
    public LocalDate getProcessedDate(){return this.processedDate;}
    public double getClaimValue() {return this.claimValue;}
    public boolean getDisbursed() {return this.disbursed;}
    public String getClaimNote(){return this.claimNote;}
    
    public void disburse(boolean disbursed){
        this.disbursed = disbursed;
        this.processedDate = LocalDate.now();
    }
    
    public void setClaimNote(String note){
        if(note != null){
            this.claimNote = "\t" + note;
        }
        return;
    }
    
    public String toString(){
        String bool = "";
        if (this.disbursed){
            bool = "Y";
        }
        else{
            bool = "N";
        }
        
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        String formattedAmount = currency.format(getClaimValue());
        
        String output ="CLAIM NO: " + getClaimNo() + " " + getClaimDate() + " " + formattedAmount + " PAID: " + bool + 
        " PROCESSED ON: " + this.processedDate;
        
        if (!this.claimNote.equals("")){
            output += "\n NOTE:" + this.claimNote;
        }
        
        return output;
    }
    
    
}