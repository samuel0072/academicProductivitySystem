import productivitysystem.colaborator.Colaborator;
import productivitysystem.colaborator.researcher.Researcher;
import productivitysystem.colaborator.researcher.ResearcherWithProject;
import productivitysystem.colaborator.student.Student;
import productivitysystem.colaborator.student.StudentWithProject;
import productivitysystem.colaborator.teacher.Teacher;
import productivitysystem.colaborator.teacher.TeacherWithProject;
import productivitysystem.laboratory.Laboratory;
import productivitysystem.production.Mentoring;
import productivitysystem.production.Publication;
import productivitysystem.project.Project;
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
        int d = 0;
        while(d == 0) {
            System.out.println ( "O que deseja fazer?" );

            System.out.println ( "1 - Adicionar um novo colaborador" );
            System.out.println ( "2 - Adicionar um novo projeto" );
            System.out.println ( "3 - Adicionar uma nova publicacao" );
            System.out.println ( "4 - Adicionar uma nova orientacao" );

            System.out.println ( "5 - Modificar um projeto" );
            System.out.println ( "6 - Modificar uma publicacao" );
            System.out.println ( "7 - Gerar um relatorio geral do laboratorio" );
            System.out.println ( "8 - Gerar um relatorio sobre algum projeto em especifico" );
            d = readEntrance ( 0, 9 );
            switch (d){
                case 1:
                    menuAddCol ();
                    break;
                case 2:
                    menuAddProj ();
                    break;
                case 3:
                    menuAddPub ();
                    break;
                case 4:
                    menuAddMentoring ();
                    break;
                case 5:
                    //todo
                    break;
                case 6:
                    menuModPub ();
                case 7:
                    laboratory.report ();
                case 8:
                    generateProjRep ();
            }

        }

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
         String status;
         Colaborator manager;
         String email;

         if(reader.hasNext ()) {
             reader.nextLine ();
         }
         System.out.println("informe:");
         System.out.println("titulo do projeto:");
         title = reader.nextLine ();

         System.out.println("data de inicio do projeto:");
         startdate = reader.nextLine ();

         System.out.println("data de termino do projeto:");
         endDate = reader.nextLine ();

         System.out.println("a agencia financiadora:");
         financierAgency = reader.nextLine();

         System.out.println("o valor do financiamento:");
         value = readEntrance ( 0.0, 999999.9999 );

         System.out.println("o objetivo(Em apenas uma linha)");
         objective = reader.nextLine ();

         System.out.println("A descricao(Em apenas uma linha):");
         description = reader.nextLine ();

         System.out.println("Digite o email do gerente do projeto:");
         laboratory.showMembers ();
         email = reader.nextLine ();
         Colaborator e = laboratory.getColaborator ( email );

         while(e == null) {
             System.out.println("Digite o email de novo");
             email = reader.nextLine ();
             e = laboratory.getColaborator ( email );
         }
         laboratory.addProject ( new Project ( title, startdate, endDate, financierAgency, value, objective,
                 description, "Em andamento" , e) );

    }

    public static void menuAddPub(){
         String conference;
         int year;
         String title;

         if(reader.hasNext ()){
             reader.nextLine ();
         }

         System.out.println("Informe:");
         System.out.println("Titulo:");
         title = reader.nextLine ();
         System.out.println("Ano de publicacao:");
         year = readEntrance ( 0, 9999 );
         System.out.println("Conferencia:");
         conference = reader.nextLine ();

         laboratory.addPublication ( new Publication ( conference, year, title ) );

    }

    public static void menuAddMentoring() {
        Colaborator teacher;
        Colaborator student;
        String emailT;
        String emailS;

        laboratory.showMembers ();

        if(reader.hasNext ()){
            reader.nextLine ();
        }

        System.out.println("Dentre os colaboradores acima digite o email do professor");
        emailT = reader.nextLine ();
        teacher = laboratory.getColaborator ( emailT );

        while(!(teacher instanceof Teacher)) {
            System.out.println("Dentre os colaboradores acima digite o email do professor");
            emailT = reader.nextLine ();
            teacher = laboratory.getColaborator ( emailT );
        }

        System.out.println("Dentre os colaboradores acima digite o email do estudante");
        emailS = reader.nextLine ();
        student = laboratory.getColaborator ( emailS );

        while(!(student instanceof Student)) {
            System.out.println("Dentre os colaboradores acima digite o email do estudante");
            emailS = reader.nextLine ();
            student = laboratory.getColaborator ( emailS );
        }

        laboratory.addMentoring ( new Mentoring ( (Teacher) teacher, (Student) student ) );


    }

    public static void generateProjRep(){
        String title;
        Project target = null;

        if(reader.hasNext ()) {
            reader.nextLine ();
        }

        while(target == null) {
            System.out.println("Informe o titulo do projeto:");
            title = reader.nextLine ();
            target = laboratory.getProject ( title );
        }
        target.projectQuery ();
    }

    public static void menuModPub(){
        String title;
        Publication target = null;
        int d;

        if(reader.hasNext ()) {
            reader.nextLine ();
        }

        while(target == null) {
            System.out.println("Informe o titulo do projeto:");
            title = reader.nextLine ();
            target = laboratory.getPublication ( title );
        }
        System.out.println("O que deseja fazer?");
        System.out.println("1 -Adicionar autor");
        System.out.println ( "2 -Associar a um projeto" );
        d = readEntrance ( 0, 3 );

        if(d == 1){
            String email;
            Colaborator author = null;
            while(author == null){
                System.out.println("Informe o email do autor:");
                email = reader.nextLine ();
                author = laboratory.getColaborator ( email );
            }
            target.addAuthor ( author );

        }
        else {
            String titleP;
            Project p = null;
            while(p == null){
                System.out.println("informe o titulo do projeto:");
                titleP = reader.nextLine ();
                p = laboratory.getProject ( titleP );
                target.addProject ( p );
                p.addpublication ( target );
            }
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

    public static double readEntrance(double lowBound, double upBound){
        if(reader.hasNext ()) {
            reader.nextLine ();
        }
        double d = lowBound;
        while(d <= lowBound || d >= upBound) {
            try {
                d = reader.nextDouble ();
            }
            catch(Exception e){
                d = lowBound;
            }
        }
        return d;
    }
}
