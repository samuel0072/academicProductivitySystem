package productivitysystem.colaborator.teacher;

/*
 * @this class implements decorator design pattern
 *
 * */
public class TeacherWithProject extends Teacher {
    String role;

    public TeacherWithProject(Teacher t, String role){
        super(t.getName (), t.getEmail ());
        this.role = role;
    }

    public String getRole(){
        return this.role;
    }
    public void setRole(String role){
        this.role = role;
    }
}
