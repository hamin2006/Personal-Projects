class Introducer{
    String createPublicIntroduction(Human person){
        String output = "I am pleased to introduce" + person.firstName + " " + person.lastName+".";
        if(person.gender == Gender.MALE){
            output += " He is " + person.calculateCurrentAgeInYears() + " years old.";
        }else{
            output += " She is " + person.calculateCurrentAgeInYears() + " years old.";
        }

        try{
            if (person instanceof Adult ) {
                if (person.gender == Gender.MALE) {
                    output += " He works at " + ((Adult) person).placeOfWork + " as an " + ((Adult) person).occupation + ".";
                } else if (person.gender == Gender.FEMALE) {
                    output += " She works at " + ((Adult) person).placeOfWork + " as an " + ((Adult) person).occupation + ".";
                }
            }else if (person instanceof Youth){
                if (person.gender == Gender.MALE) {
                    output += " He goes to " + ((Youth) person).schoolName + ", and is in grade" + ((Youth) person).schoolGrade + ".";
                } else if (person.gender == Gender.FEMALE) {
                    output += " She goes to " + ((Youth) person).schoolName + ", and is in grade" + ((Youth) person).schoolGrade + ".";
                }
            }else if (person instanceof HenryWiseWoodStudent){
                if (person.gender == Gender.MALE) {
                    output += " He belongs to " + ((HenryWiseWoodStudent) person).homeRoomTeacher + "'s homeroom, which is room " + ((HenryWiseWoodStudent) person).homeRoom + ".";
                } else if (person.gender == Gender.FEMALE) {
                    output += " She belongs to " + ((HenryWiseWoodStudent) person).homeRoomTeacher + "'s homeroom, which is room " + ((HenryWiseWoodStudent) person).homeRoom + ".";
                }
            }
        }catch(Exception e){
          System.out.println("Object class failed");
        }

        return output;
    }
}