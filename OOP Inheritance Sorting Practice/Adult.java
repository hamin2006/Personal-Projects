class Adult extends Human{
    String placeOfWork;
    String occupation;
    //constructor
    public Adult(int birthYear, int birthMonth, int birthDay, String firstName, String lastName, Gender gender, String placeOfWork, String occupation) {
        super(birthYear, birthMonth, birthDay, firstName, lastName, gender);
        this.placeOfWork = placeOfWork;
        this.occupation = occupation;
    }
    //accessors
    public String getPlaceOfWork() {
        return placeOfWork;
    }
    public String getOccupation() {
        return occupation;
    }
  //mutators
    public void setPlaceOfWork(String placeOfWork) {
        this.placeOfWork = placeOfWork;
    }
    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }
}