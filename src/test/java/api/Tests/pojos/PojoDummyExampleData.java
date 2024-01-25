package api.Tests.pojos;


import java.util.Objects;

public class PojoDummyExampleData {

    private Integer id;
    private String employeeName;
    private Integer employeeSalary;
    private Integer employeeAge;
    private String profileImage;

    /**
     * No args constructor for use in serialization
     */
    public PojoDummyExampleData() {
    }

    /**
     * @param employeeName
     * @param employeeAge
     * @param id
     * @param profileImage
     * @param employeeSalary
     */
    public PojoDummyExampleData(Integer id, String employeeName, Integer employeeSalary, Integer employeeAge, String profileImage) {
        super();
        this.id = id;
        this.employeeName = employeeName;
        this.employeeSalary = employeeSalary;
        this.employeeAge = employeeAge;
        this.profileImage = profileImage;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public Integer getEmployeeSalary() {
        return employeeSalary;
    }

    public void setEmployeeSalary(Integer employeeSalary) {
        this.employeeSalary = employeeSalary;
    }

    public Integer getEmployeeAge() {
        return employeeAge;
    }

    public void setEmployeeAge(Integer employeeAge) {
        this.employeeAge = employeeAge;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    @Override
    public String toString() {
        return "PojoDummyExampleData [" +
                "id=" + Objects.toString(id, "<null>") + ", " +
                "employeeName=" + Objects.toString(employeeName, "<null>") + ", " +
                "employeeSalary=" + Objects.toString(employeeSalary, "<null>") + ", " +
                "employeeAge=" + Objects.toString(employeeAge, "<null>") + ", " +
                "profileImage=" + Objects.toString(profileImage, "<null>") +
                "]";
    }

}
