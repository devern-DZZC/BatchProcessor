import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.time.LocalDate;

public class InsuranceApplication{
    public static void main(String[] args){
        Mainframe mainframe = new Mainframe();
        
        try{
            File file = new File("data.txt");
            Scanner sc = new Scanner(file);
            String data = "";
            
            while(sc.hasNextLine()){
                data = sc.nextLine();
                String[] parameters = data.split(", ");
                int size = parameters.length;
                
                // InsuredPerson takes 2 parameters
                if(size == 2){
                    LocalDate date = mainframe.convertDate(parameters[1]);
                    mainframe.addClient(parameters[0], date.toString());
                }
                
                // Claims takes 3 parameters
                else if(size == 3){
                    LocalDate date = mainframe.convertDate(parameters[1]);
                    mainframe.addClaim(Integer.parseInt(parameters[0]), date.toString(), Double.parseDouble(parameters[2]));
                }
                
            }
            
        } catch(Exception e){
            System.out.println(e.toString());
        }
        
        mainframe.processClaims();
        System.out.println(mainframe.getReports());
            
    }
}