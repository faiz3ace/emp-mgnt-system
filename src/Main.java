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

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String path = "";
        boolean isQuit = true;
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
                System.out.print("Emp-System" + path + ">");
                String command = sc.nextLine();
                if (command.equalsIgnoreCase("quit")) {
                    isQuit = false;
                }
                if (isQuit) {
                    String cmd = command.split(" ")[0];
                    if (getDbConnection(ch).equals("Connected")) {

                    } else {
                        if (cmd.equalsIgnoreCase("viewall")) {
                            viewAll();
                        } else if (cmd.equalsIgnoreCase("view")) {
                            Long id = Long.valueOf(command.split(" ")[1]);
                            System.out.println(viewData(id));
                            path = "\\" + data.get(id).getEmpName();
                        } else if (cmd.equalsIgnoreCase("add")) {
                            addData(getName(cmd, command));
                        } else if (cmd.equalsIgnoreCase("update")) {
                            updateData(Long.valueOf(command.split(" ")[1]), getName(cmd, command));
                        } else if (cmd.equalsIgnoreCase("delete")) {
                            System.out.println("In progress");
                        } else {
                            System.out.println("Invalid Command...");
                        }
                    }

                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        } while (isQuit);

    }

    private static void viewAll() {
        for (Long id : data.keySet()) {
            System.out.println("Id: " + data.get(id).getEmpId() + ", Name: " + data.get(id).getEmpName());
        }
    }

    Employ addEmploy(String name, List<String> skillSet) {
        return null;
    }

    private static String viewData(Long empId) {
        Employ entry = data.get(empId);
        return "Id: " + entry.getEmpId() + ", Name: " + entry.getEmpName();
    }

    private static void addData(String name) {
        Employ create = new Employ(name);
        data.put(create.getEmpId(), create);
    }

    private static void updateData(Long empId, String update) {
        data.get(empId).setEmpName(update);
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