class HenryWiseWoodStudent extends Youth{
    int homeRoom;
    String homeRoomTeacher;
  //constructor
    public HenryWiseWoodStudent(int birthYear, int birthMonth, int birthDay, String firstName, String lastName, Gender gender, int schoolGrade, int homeRoom, String homeRoomTeacher) {
        super(birthYear, birthMonth, birthDay, firstName, lastName, gender, schoolGrade, "Henry Wise Wood High School");
        this.homeRoom = homeRoom;
        this.homeRoomTeacher = homeRoomTeacher;
    }
  //accessors
    public int getHomeRoom() {
        return homeRoom;
    }
    public String getHomeRoomTeacher() {
        return homeRoomTeacher;
    }
  //mutators
    public void setHomeRoom(int homeRoom) {
        this.homeRoom = homeRoom;
    }
    public void setHomeRoomTeacher(String homeRoomTeacher) {
        this.homeRoomTeacher = homeRoomTeacher;
    }
}