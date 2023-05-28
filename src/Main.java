import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main{
    static Map<Long,Employ> data=new HashMap<>();
    public static void main(String[] args) {
       
        String path="";
        boolean isQuit=true;
        do{
            try{
            Scanner sc=new Scanner(System.in);
            System.out.print("Emp-System"+path+">");
            String command=sc.nextLine();
            if(command.equalsIgnoreCase("quit")){
                isQuit=false;
            }
            if(isQuit){
            String cmd = command.split(" ")[0];
            if(cmd.equalsIgnoreCase("viewall"))
                viewAll();
            if(cmd.equalsIgnoreCase("view")){
                Long id=Long.valueOf(command.split(" ")[1]);
                System.out.println(viewData(id));
                path="\\"+data.get(id).getEmpName();
            }
            else if(cmd.equalsIgnoreCase("add"))
                addData(command.split(" ")[1]);
            else if(cmd.equalsIgnoreCase("update"))
                updateData(Long.valueOf(command.split(" ")[1]), command.split(" ")[2]);
            }
        }
            catch(Exception ex){
                System.out.println(ex.getMessage());
            }
        }while(isQuit);
    
    }
    private static void viewAll(){
        for(Long id:data.keySet()){
            System.out.println("Id: "+data.get(id).getEmpId()+", Name: "+data.get(id).getEmpName());
        }
    }
    Employ addEmploy(String name, List<String> skillSet){
        return null;
    }
    private static String viewData(Long empId){
        Employ entry=data.get(empId);
        return "Id: "+entry.getEmpId()+", Name: "+entry.getEmpName();
    }
    private static void addData(String name){
        Employ create=new Employ(name);
        data.put(create.getEmpId(),create);
    }
    private static void updateData(Long empId,String update){
        data.get(empId).setEmpName(update);
    }
}