package com.app.emp.mgnt;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    static Map<Long, Employ> data = new HashMap<>();
    static boolean isRecordOpen=false;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String path = "";
        Long id=0L;
        boolean isQuit = false;
        System.out.print("Do you want to store data (Y/N)?:");
        String option = sc.nextLine();
        while (option.length() != 1) {
            System.out.print("Please try again and select Y/N:");
            option = sc.nextLine();
        }
        char ch = option.charAt(0);
        System.out.println(ch);
        do {
            try {
                String consolePath="Emp-System" + path + ">";
                System.out.print(consolePath);
                String command = sc.nextLine();
                if (command.equalsIgnoreCase("quit")) {
                    isQuit = true;
                }
                if (!isQuit&&!isRecordOpen) {
                    String cmd = command.split(" ")[0];
                    if (getDbConnection(ch).equals("Connected")) {

                    } else {
                        if (cmd.equalsIgnoreCase("viewall")) {
                            viewAll();
                        } else if (cmd.equalsIgnoreCase("view")) {
                            try{
                            id = Long.valueOf(command.split(" ")[1]);
                            if(viewData(id)!=null)
                                path = "\\" + viewData(id).getEmpName();
                            else
                                System.out.println("Record Not Found");
                            }catch(Exception ex){
                                String name="";
                                if(command.split(" ").length==2)
                                    name=command.split(" ")[1];
                                if(command.split(" ").length==3)
                                    name=command.split(" ")[1]+ " "+command.split(" ")[2];
                                System.out.println("Invalid...");
                                viewData(name);
                            }
                        } else if (cmd.equalsIgnoreCase("add")) {
                            addData(getName(cmd, command));
                        } else if (cmd.equalsIgnoreCase("update")) {
                            updateData(Long.valueOf(command.split(" ")[1]), getName(cmd, command));
                        } else if (cmd.equalsIgnoreCase("delete")) {
                            if(command.split(" ").length==2)
                                System.out.println("Recrod Deleted:"+deleteRecord(Long.valueOf(command.split(" ")[1])));
                            else if(command.split(" ").length==3)
                                System.out.println(deleteRecord(Long.valueOf(command.split(" ")[1]),command.split(" ")[2]));
                            else
                                System.out.println("Delete will accept only 1/2 args");
                        } else {
                            System.out.println("Invalid Command...");
                        }
                    }

                }
                if(!isQuit&&isRecordOpen){
                    Employ employ=data.get(id);
                    if(command.split(" ")[0].equalsIgnoreCase("close")||command.split(" ")[0].equalsIgnoreCase("exit")){
                        id=0L;
                        path="";
                        isRecordOpen=false;
                    }
                    boolean isEmpty=false;
                    if(employ.getAddress()==null){
                        isEmpty=true;
                        System.out.print("Address, ");
                    }
                    if(employ.getContact()==null){
                        isEmpty=true;
                        System.out.print("Contact, ");
                    }
                    if(employ.getEducation()==null){
                        isEmpty=true;
                        System.out.print("Education, ");
                    }
                    if(employ.getEmployment()==null){
                        isEmpty=true;
                        System.out.print("Employment, ");
                    }
                    if(employ.getSkills()==null){
                        isEmpty=true;
                        System.out.print("Skills ");
                    }
                    if(employ.getExperience()!=null){
                        isEmpty=true;
                        System.out.print("Experience, ");
                    }
                    if(isEmpty)
                        System.out.println(" are/is not updated...");
                    
                    System.out.print("Do you want to update?(Y/N):");
                    char input=sc.nextLine().charAt(0);
                    if(input=='Y'||input=='y'){
                        System.out.println("Coming soon...");
                    }else if(input=='N'||input=='n'){
                        System.out.println("Coming soon...");
                    }else{
                        System.out.println("Coming soon...");
                    }
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        } while (!isQuit);

    }

    private static void viewAll() {
        for (Long id : data.keySet()) {
            System.out.println("Id: " + data.get(id).getEmpId() + ", Name: " + data.get(id).getEmpName());
        }
    }

    Employ addEmploy(String name, List<String> skillSet) {
        return null;
    }

    private static Employ viewData(Long empId) {
        Employ entry = data.get(empId);
        if(entry!=null){
            isRecordOpen=true;
            return entry;
        }
        else{
            return new Employ();
        }
    }
    private static void viewData(String empName) {
        for(Long emp:data.keySet()){
            if(data.get(emp).getEmpName().equals(empName)){
                System.out.println("Emp ID:"+emp+"\t"+"Emp Name:"+data.get(emp).getEmpName());
            }
        }
    }
    private static void addData(String name) {
        Employ create = new Employ(name);
        data.put(create.getEmpId(), create);
    }

    private static void updateData(Long empId, String update) {
        data.get(empId).setEmpName(update);
    }
    private static String deleteRecord(Long empId){
        return data.remove(empId).getEmpName();
    }
    
    private static String deleteRecord(Long empId,String empName){
        if(data.get(empId).getEmpName().equals(empName)){
            data.remove(empId);
            return "Deleted Successfully";
        }
        else
            return "Record Not Found";
    }
    private static String getName(String cmd, String command) {
        String empName = "";
        if (command.contains("\"")) {
            if (cmd.equalsIgnoreCase("add"))
                empName = command.split("\"")[1];
            if (cmd.equalsIgnoreCase("update"))
                empName = command.split("\"")[2];
        } else {
            String name[] = command.split(" ");
            if (name.length >= 3) {
                if (cmd.equalsIgnoreCase("add"))
                    empName = name[1] + " " + name[2];
                if (name.length >= 4 && cmd.equalsIgnoreCase("update"))
                    empName = name[2] + " " + name[3];
                if (name.length<=3&&cmd.equalsIgnoreCase("update"))
                    empName = name[2];
            } else {
                if (cmd.equalsIgnoreCase("add"))
                    empName = name[1];
                if (cmd.equalsIgnoreCase("update"))
                    empName = name[2];
            }
        }
        return empName;
    }

    private static String getDbConnection(char ch) {
        if (ch == 'Y' || ch == 'y')
            return "Connected";
        else
            return "notConnected";
    }
}