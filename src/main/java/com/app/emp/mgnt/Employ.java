package com.app.emp.mgnt;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

import lombok.Data;

@Data
public class Employ {
    private Long empId;
    private String empName;
    private static Set<Long> Ids = new HashSet<>();
    Address address;
    Contact contact;
    Education education;
    Employment employment;
    Experience experience;
    Skills skills;

    public Employ() {

    }
    public void setSkills(Skills skills){
        this.skills=skills;
    }
    public Skills getSkills(){
        return skills;
    }
    public void setExperience(Experience experience){
        this.experience=experience;
    }
    public Experience getExperience(){
        return experience;
    }
    public void setEmployment(Employment employment){
        this.employment=employment;
    }
    public Employment getEmployment(){
        return employment;
    }
    public void setEducation(Education education){
        this.education=education;
    }
    public Education getEducation(){
        return education;
    }
    public void setContact(Contact contact){
        this.contact=contact;
    }
    public Contact getContact(){
        return contact;
    }
    public void setAddress(Address address){
        this.address=address;
    }
    public Address getAddress(){
        return address;
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
                //generateEmpId();
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
