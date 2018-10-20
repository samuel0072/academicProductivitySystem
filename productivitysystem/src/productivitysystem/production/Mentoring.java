package productivitysystem.production;

import  productivitysystem.colaborator.teacher.Teacher;
import  productivitysystem.colaborator.student.Student;

public class Mentoring{
    Teacher teacher;
    Student student;

    public Mentoring(Teacher t, Student s){
        this.teacher = t;
        this.student = s;
    }

    public Teacher getTeacher(){
        return this.teacher;
    }
    public Student getStudent(){
        return this.student;
    }
}
