import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;

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
                    mainframe.addClient(parameters[0], parameters[1]);
                }
                
                // Claims takes 3 parameters
                else if(size == 3){
                    mainframe.addClaim(Integer.parseInt(parameters[0]), parameters[1], Double.parseDouble(parameters[3]));
                }
                
            }
            
        } catch(Exception e){
            System.out.println(e.toString());
        }
        
        mainframe.processClaims();
        mainframe.getReports();
            
    }
}