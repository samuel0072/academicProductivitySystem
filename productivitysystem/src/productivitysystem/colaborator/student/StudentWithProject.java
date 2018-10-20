package productivitysystem.colaborator.student;

/*
* @this class implements decorator design pattern
*
* */
public class StudentWithProject extends Student{
    private String role;

    public StudentWithProject(Student st, String role){
        super(st.getName (), st.getType (),st.getEmail ());
        this.role = role;
    }

    public String getRole(){
        return this.role;
    }

    public void setRole(String role){
        this.role = role;
    }
}
