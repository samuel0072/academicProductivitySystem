import productivitysystem.colaborator.Colaborator;
import productivitysystem.colaborator.researcher.Researcher;
import productivitysystem.colaborator.student.Student;
import productivitysystem.colaborator.teacher.Teacher;
import productivitysystem.laboratory.Laboratory;
import productivitysystem.production.Mentoring;
import productivitysystem.production.Publication;
import productivitysystem.project.Project;

import java.util.Scanner;

public class Main {
    public static Scanner reader = new Scanner ( System.in );
    public static Laboratory laboratory = Laboratory.Instance ();

    public static void main(String[] args){
        System.out.println("Bem vindo!");
        menu();
    }

    public static void menu(){
        int d = 0;
        while(true) {
            System.out.println ( "\n\tO que deseja fazer?" );

            System.out.println ( "\t1 - Adicionar um novo colaborador" );
            System.out.println ( "\t2 - Adicionar um novo projeto" );
            System.out.println ( "\t3 - Adicionar uma nova publicacao" );
            System.out.println ( "\t4 - Adicionar uma nova orientacao" );

            System.out.println ( "\t5 - Modificar um projeto" );
            System.out.println ( "\t6 - Modificar uma publicacao" );
            System.out.println ( "\t7 - Gerar um relatorio geral do laboratorio" );
            System.out.println ( "\t8 - Gerar um relatorio sobre algum projeto em especifico" );
            System.out.println ( "\t9 - Gerar um relatorio sobre algum membro do lab");

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
                    menuEditProj ();
                    break;
                case 6:
                    menuModPub ();
                    break;
                case 7:
                    laboratory.report ();
                    break;
                case 8:
                    generateProjRep ();
                    break;
                case 9:
                    menuMemberReport ();
                    break;
            }
            System.out.println("Digite ENTER para continuar...");
            reader.nextLine ();
            clear ();
        }

    }

    public static void menuAddCol() {

        String name, email;
        String[] types = {"Graduacao", "Mestrado", "Doutorado"};
        int d;
        System.out.println("\n\tQue tipo de colaborador deseja adicionar?");
        System.out.println("\t1 - Estudante");
        System.out.println("\t2 - Professor");
        System.out.println("\t3 - Pesquisador");

        d = readEntrance ( 0, 4 );

        System.out.println("\n\tInforme:");
        System.out.println("\tNome:");
        name = reader.nextLine ();

        System.out.println("\tEmail?");
        email = reader.nextLine ();

        switch(d) {
            case 1:
                System.out.println("\n\tQue tipo de aluno?");
                System.out.println("\t1 - Graduacao");
                System.out.println("\t2 - Mestrado");
                System.out.println("\t3 - Doutorado");
                d = readEntrance ( 0, 4 );
                laboratory.addColaborator (  new Student(name, types[d-1] , email) );
                break;
            case 2:
                laboratory.addColaborator (  new Teacher ( name, email ) );
                break;

            case 3:
                laboratory.addColaborator (  new Researcher ( name, email ) );
                break;
        }
    }

    public  static void menuAddProj(){

         String title;
         int startdate;
         int endDate;
         String financierAgency;
         double value;
         String objective;
         String description;
         Colaborator manager;
         String email;

         System.out.println("informe:");
         System.out.println("titulo do projeto:");
         title = reader.nextLine ();

         System.out.println("data de inicio do projeto:");
         startdate = readEntrance ( 1989, 2019 );

         System.out.println("data de termino do projeto:");
         endDate = readEntrance ( 1989, 2019 );

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
         manager = null;

         while(manager == null) {
             System.out.println("Digite o email:");
             email = reader.nextLine ();
             manager = laboratory.getColaborator ( email );
         }
         laboratory.addProject ( new Project ( title, startdate, endDate, financierAgency, value, objective,
                 description, "Em elaboracao" , manager) );

    }

    public static void menuAddPub(){
         String conference;
         int year;
         String title;

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
        Colaborator teacher = null;
        Colaborator student = null;
        String emailT;
        String emailS;

        laboratory.showMembers ();

        while(!(teacher instanceof Teacher)) {
            System.out.println("\n\tDentre os colaboradores acima digite o email do professor");
            emailT = reader.nextLine ();
            teacher = laboratory.getColaborator ( emailT );
        }

        while(!(student instanceof Student)) {
            System.out.println("\n\tDentre os colaboradores acima digite o email do estudante");
            emailS = reader.nextLine ();
            student = laboratory.getColaborator ( emailS );
        }

        laboratory.addMentoring ( new Mentoring ( (Teacher) teacher, (Student) student ) );


    }

    public static void generateProjRep(){
        String title;
        Project target = null;

        laboratory.showProjects ();

        while(target == null) {
            System.out.println("\tInforme o titulo do projeto:");
            title = reader.nextLine ();
            target = laboratory.getProject ( title );
        }

        target.projectQuery ();
    }

    public static void menuModPub(){
        String title;
        Publication target = null;
        int d;

        laboratory.showPublications ();

        while(target == null) {
            System.out.println("\n\tInforme o titulo da publicacao:");
            title = reader.nextLine ();
            target = laboratory.getPublication ( title );
        }
        System.out.println("\t\nO que deseja fazer?");
        System.out.println("\t1 -Adicionar autor");
        System.out.println ( "\t2 -Associar a um projeto" );
        d = readEntrance ( 0, 3 );

        if(d == 1){
            String email;
            Colaborator author = null;
            laboratory.showMembers ();
            while(author == null){
                System.out.println("\n\tInforme o email do autor:");
                email = reader.nextLine ();
                author = laboratory.getColaborator ( email );
            }
            target.addAuthor ( author );

        }
        else {
            String titleP;
            Project p = null;
            laboratory.showProjects ();
            while(p == null){
                System.out.println("\n\tinforme o titulo do projeto:");
                titleP = reader.nextLine ();
                p = laboratory.getProject ( titleP );
                target.addProject ( p );
            }
        }
    }

    public static void menuEditProj(){
        String title;
        Project target = null;
        int d;

        laboratory.showProjects ();
        while(target == null) {
            System.out.println("\n\tInforme o titulo do projeto:");
            title = reader.nextLine ();
            target = laboratory.getProject ( title );
        }
        System.out.println("\n\tO que deseja fazer?");
        System.out.println("\t1 - Alocar um membro ao projeto");
        System.out.println("\t2 - Mudar estado do projeto");
        System.out.println("\t3 - Adicionar uma publicacao");
        d = readEntrance ( 0, 4 );

        if(d == 1){
            System.out.println("\n\tEscolha o email do colaborador:");
            laboratory.showMembers ();
            Colaborator e = null;
            String email;
            while(e == null) {
                System.out.println("\n\tDigite o email:");
                email = reader.nextLine ();
                e = laboratory.getColaborator ( email );
            }
            target.addMember ( e );
        }
        else if(d == 2){
            System.out.println("\n\tQual estado deseja adotar?");
            System.out.println("\n\t1 - Em andamento");
            System.out.println("\n\t2 - Concluido");
            d = readEntrance ( 0, 3 );
            String[] status = {"Em andamento", "Concluido"};
            System.out.println(target.setStatus ( status[d-1] )?
                    "\n\tStatus mudado com sucsso!": " Ops... Falta algo nesse projeto");
        }
        else {
            laboratory.showPublications ();
            Publication pu = null;
            while(pu == null){
                System.out.println("\tInforme o titulo da publicacao:");
                title = reader.nextLine ();
                pu = laboratory.getPublication ( title );
            }
            target.addPublication ( pu );
        }

    }

    public static void menuMemberReport(){
        String email;
        Colaborator c = null;
        laboratory.showMembers ();
        while(c == null){
            System.out.println("informe o email do membro:");
            email = reader.nextLine ();
            c = laboratory.getColaborator ( email );
        }
        laboratory.memberProduction ( c );

    }

    public static int readEntrance(int lowBound, int upBound){
        int d = lowBound;
        while(d <= lowBound || d >= upBound) {
            try {
                d = reader.nextInt ();
            }
            catch(Exception e){
                d = lowBound;
            }
        }
        reader.nextLine ();
        return d;
    }

    public static double readEntrance(double lowBound, double upBound){
        double d = lowBound;
        while(d <= lowBound || d >= upBound) {
            try {
                d = reader.nextDouble ();
            }
            catch(Exception e){
                d = lowBound;
            }
        }
        reader.nextLine ();
        return d;
    }

    public static void clear(){
        System.out.println("\n\n\n");
    }
}
