package productivitysystem.colaborator.student;

import productivitysystem.colaborator.Colaborator;

public class Student implements Colaborator {

    private String name;
    private String type;
    private String email;
    private int projectsInProgress;

    public Student(String name, String type,String email) {
        this.name = name;
        this.type = type;
        this.email = email;
        this.projectsInProgress = 0;

    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name= name;

    }

    public String getType(){
        return this.type;
    }

    public String getEmail(){
        return this.email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    /*Observer*/
    public void addProjectInProgress(){
        this.projectsInProgress+=1;
    }
    public void removeProjectInProgress(){
        this.projectsInProgress -=1;
    }

    public int getProjectsInProgress(){
        return this.projectsInProgress;
    }


}
