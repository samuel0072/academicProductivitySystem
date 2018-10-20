import productivitysystem.colaborator.researcher.Researcher;
import productivitysystem.colaborator.researcher.ResearcherWithProject;
import productivitysystem.colaborator.student.Student;
import productivitysystem.colaborator.student.StudentWithProject;
import productivitysystem.colaborator.teacher.Teacher;
import productivitysystem.colaborator.teacher.TeacherWithProject;
import productivitysystem.laboratory.Laboratory;
import productivitysystem.util.iterator.colections.ColaboratorCollection;
import productivitysystem.util.iterator.colections.PublicationCollection;

import java.util.Scanner;

public class Main {
    public static Scanner reader = new Scanner ( System.in );
    public static Laboratory laboratory = Laboratory.Instance ();

    public static void main(){
        System.out.println("Bem vindo!");

    }

    public static void menu(){
        System.out.println("O que deseja fazer?");

        System.out.println("1 - Adicionar um novo colaborador");
        System.out.println("2 - Adicionar um novo projeto");
        System.out.println("3 - Adicionar uma nova publicacao" );
        System.out.println("4 - Adicionar uma nova orientacao");

        System.out.println("5 - Modificar um projeto");
        System.out.println("6 - Modificar uma publicacao" );
        System.out.println("7 - Gerar um relatorio geral do laboratorio");
        System.out.println ( "8 - Gerar um relatorio sobre algum projeto em especifico" );

    }

    public static void menuAddCol() {

        String name, email, role;
        String[] types = {"Graduacao", "Mestrado", "Doutorado"};
        int d;
        System.out.println("Que tipo de colaborador deseja adicionar?");
        System.out.println("1 - Estudante");
        System.out.println("2 - Professor");
        System.out.println("3 - Pesquisador");

        d = readEntrance ( 0, 4 );

        if(reader.hasNext ()) {
            reader.nextLine ();
        }

        System.out.println("Qual seu nome?");
        name = reader.nextLine ();

        System.out.println("Qual seu email?");
        email = reader.nextLine ();

        System.out.println("Que papel ele assume no projeto?");
        role = reader.nextLine ();

        switch(d) {
            case 1:
                System.out.println("Que tipo de aluno?");
                System.out.println("1 - Graduacao");
                System.out.println("2 - Mestrado");
                System.out.println("3 - Doutorado");
                d = readEntrance ( 0, 4 );
                laboratory.addColaborator (  new StudentWithProject ( new Student(name, types[d-1] , email), role ) );

            case 2:
                laboratory.addColaborator ( new TeacherWithProject ( new Teacher ( name, email ), role ) );

            case 3:
                laboratory.addColaborator ( new ResearcherWithProject ( new Researcher ( name, email ), role ) );
        }
    }

    public  static void menuAddProj(){
         String title;
         String startdate;
         String endDate;
         String financierAgency;
         double value;
         String objective;
         String description;
         ColaboratorCollection members;
         PublicationCollection publicacoes;
         String status;

         if(reader.hasNext ()) {
             reader.nextLine ();
         }
    }

    public static int readEntrance(int lowBound, int upBound){
        if(reader.hasNext ()) {
            reader.nextLine ();
        }
        int d = lowBound;
        while(d <= lowBound || d >= upBound) {
            try {
                d = reader.nextInt ();
            }
            catch(Exception e){
                d = lowBound;
            }
        }
        return d;
    }
}
