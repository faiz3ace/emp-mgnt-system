import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.regex.Pattern;

public class Employ {
    private Long empId;
    private String empName;
    private static Set<Long> Ids = new HashSet<>();

    public Employ() {

    }

    public Employ(String empName) {
        if (empName.length() >= 3) {
            if (Pattern.matches("[a-zA-Z-\\s]+", empName)) {
                generateEmpId();
                this.empName = empName;
                System.out.println("EmpID:" + this.empId);
            } else {
                System.out.println("Name sould not be contain digit or special character");
            }
        } else{
            System.out.println("Name should be 3 or grater than 3 character");
        }
    }

    public Long getEmpId() {
        return this.empId;
    }

    public String getEmpName() {
        return this.empName;
    }

    public void setEmpName(String empName) {
        if (empName.length() >= 3) {
            if (Pattern.matches("[a-zA-Z-\\s]+", empName)) {
                generateEmpId();
                this.empName = empName;
                System.out.println("EmpID:" + this.empId);
            } else {
                System.out.println("Name sould not be contain digit or special character");
            }
        } else
            System.out.println("Name should be 3 or greater than 3 Character");
    }

    private void generateEmpId() {
        this.empId = Long.valueOf(Ids.size() + 1);
        Ids.add(this.empId);
    }
}
