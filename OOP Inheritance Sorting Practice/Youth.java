class Youth extends Human{
    int schoolGrade;
    String schoolName;
    //constructor
    public Youth(int birthYear, int birthMonth, int birthDay, String firstName, String lastName, Gender gender, int schoolGrade, String schoolName) {
        super(birthYear, birthMonth, birthDay, firstName, lastName, gender);
        this.schoolGrade = schoolGrade;
        this.schoolName = schoolName;
    }
  //accessor
    public int getSchoolGrade() {
        return schoolGrade;
    }
    public String getSchoolName() {
        return schoolName;
    }
  //mutator
    public void setSchoolGrade(int schoolGrade) {
        this.schoolGrade = schoolGrade;
    }
    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }
}