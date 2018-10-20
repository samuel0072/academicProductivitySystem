package productivitysystem.colaborator.teacher;

import productivitysystem.colaborator.Colaborator;

public class Teacher implements Colaborator {
    private String name;
    private String email;


    @Override
    public String getEmail() {
        return this.email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;

    }

    public Teacher(String name, String email){
        this.name = name;
        this.email = email;
    }

    @Override
    public String getName(){
        return this.name;
    }

    @Override
    public void setName(String name){
        this.name = name;
    }


}
