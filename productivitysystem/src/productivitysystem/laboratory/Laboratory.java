package productivitysystem.laboratory;

import productivitysystem.colaborator.Colaborator;
import productivitysystem.colaborator.student.Student;
import productivitysystem.colaborator.teacher.Teacher;
import productivitysystem.production.Mentoring;
import productivitysystem.production.Publication;
import productivitysystem.project.Project;
import productivitysystem.util.iterator.colections.ColaboratorCollection;
import productivitysystem.util.iterator.colections.MentoringCollection;
import productivitysystem.util.iterator.colections.ProjectCollection;
import productivitysystem.util.iterator.colections.PublicationCollection;
import productivitysystem.util.iterator.iterators.ColaboratorIterator;
import productivitysystem.util.iterator.iterators.MentoringIterator;
import productivitysystem.util.iterator.iterators.ProjectIterator;
import productivitysystem.util.iterator.iterators.PublicationIterator;

import java.util.ArrayList;

public class Laboratory {

    private static Laboratory _instance = null;

    private ColaboratorCollection colaborators;
    private ProjectCollection projects;
    private MentoringCollection mentorings;
    private PublicationCollection publications;

    private Laboratory(){
        this.colaborators = new ColaboratorCollection ();
        this.mentorings = new MentoringCollection ();
        this.projects = new ProjectCollection ();
        this.publications = new PublicationCollection ();

    }

    public static Laboratory Instance(){
        if(_instance == null){
            _instance = new Laboratory ();
        }
        return _instance;
    }

    public void addColaborator(Colaborator e) {
        colaborators.addColaborator ( e );
    }

    public void addProject(Project e){
        projects.addProject ( e );
    }

    public void addPublication(Publication e){
        publications.addPublications ( e );
    }

    public void addMentoring(Mentoring e){
        mentorings.addMentoring ( e );
    }

    public Colaborator getColaborator(String email){
        ColaboratorIterator e = (ColaboratorIterator) colaborators.createIterator ();
        Colaborator target = null;

        while(e.hasNext ()){
            Colaborator a = (Colaborator) e.next ();
            if(a.getEmail ().equals(email)){
                target = a;
                break;
            }
        }
        return target;

    }

    public Project getProject(String title){
        ProjectIterator e = (ProjectIterator) projects.createIterator ();
        Project target = null;
        while(e.hasNext ()){
            Project a = (Project) e.next ();
            if(a.getTitle ().equals ( title )){
                target = a;
            }
        }
        return target;

    }

    public MentoringCollection getMentorings(String teacherEmail){
        MentoringCollection result = new MentoringCollection ();

        MentoringIterator e = (MentoringIterator) mentorings.createIterator ();

        Mentoring target = null;

        while(e.hasNext ()){
            Mentoring a = (Mentoring) e.next ();
            if(a.getTeacher ().getEmail ().equals ( teacherEmail )){
                result.addMentoring ( a );
            }
        }
        return result;

    }

    public Publication getPublication(String title){
        PublicationIterator e = (PublicationIterator) publications.createIterator ();
        Publication target = null;
        while(e.hasNext ()){
            Publication a = (Publication) e.next ();
            if(a.getTitle () == title){
                target = a;
                break;
            }
        }
        return target;

    }

    public void showMembers(){
        System.out.println("Membros do lab:");

        ColaboratorIterator e = (ColaboratorIterator) colaborators.createIterator ();
        while(e.hasNext ()){
            Colaborator a = (Colaborator) e.next ();
            System.out.println("Nome: "+ a.getName () +", email: "+ a.getEmail ());
        }
    }

    /*Item 4*/
    public void report(){

        int elaboring = 0, progress = 0, done = 0;
        ProjectIterator e = (ProjectIterator) projects.createIterator ();

        while(e.hasNext ()){
            Project a = (Project)e.next ();
            String status = a.getStatus ();
            if(status.equals ( "Em elaboracao" )){
                elaboring+=1;
            }
            else if(status.equals ( "Em andamento" )){
                progress+=1;
            }
            else{
                done+=1;
            }

        }

        System.out.println("\nRelatorio de producoes academicas do laboratorio");

        System.out.println("\tNumero de colaboradores: " + colaborators.getNumItens ());

        System.out.println("\tNumero de projetos em elaboracao: " + elaboring);
        System.out.println("\tNumero de projetos em andamento: " + progress );
        System.out.println("\tNumero de projetos finalizados: "+ done);
        System.out.println("\tNumero total de projetos:" + projects.getNumItens ());

        System.out.println("\tNumero de publicacoes: " + publications.getNumItens ());
        System.out.println("\tNumero de orientacoes: " + mentorings.getNumItens ());



    }

    /*item 3 letra a*/
    public void memberProduction(Colaborator e){

        ProjectCollection projectsCol = new ProjectCollection ();
        PublicationCollection publicationsCol = new PublicationCollection ();
        MentoringCollection mentoringsCol = new MentoringCollection ();

        ProjectIterator projectIt = (ProjectIterator) projects.createIterator ();
        PublicationIterator publicationIt = (PublicationIterator) publications.createIterator ();
        MentoringIterator mentoringIt = (MentoringIterator) mentorings.createIterator ();

        while(projectIt.hasNext ()) {
            Project p = (Project)projectIt.next ();
            if(p.getMember ( e.getEmail () ) != null) {
                projectsCol.addProject ( p );
            }

        }
        while(publicationIt.hasNext ()) {
            Publication p = (Publication)publicationIt.next ();
            if(p.getColaborator ( e.getEmail () ) != null) {
                publicationsCol.addPublications ( p );
            }

        }
        while(mentoringIt.hasNext ()) {
            Mentoring p = (Mentoring) mentoringIt.next ();
            if(p.getTeacher ( ).getEmail ().equals ( e.getEmail () )) {
                mentoringsCol.addMentoring ( p );
            }
        }

        ArrayList<Publication> memberPubs = publicationsCol.sortByYear ();
        ArrayList<Project> memberProjs = projectsCol.sortByYear ();

        System.out.println("\n\tInformacoes sobre este colaborador:");
        System.out.println("\n\tNome: "+ e.getName ());
        System.out.println("\n\tEmail: "+ e.getEmail ());
        System.out.println( (e instanceof Student) ?"\n\tEstudante de "+ ((Student) e).getType () : "" );

        System.out.println("\n\tProjetos:");
        for (Project p1: memberProjs) {
            if(!p1.getStatus ().equals ( "Em andamento" )) {
                System.out.println("\tProjeto: " + p1.getTitle () +", ano: "+p1.getStartdate ());
            }
        }
        System.out.println("\t-Projetos em andamento:");
        for (Project p1: memberProjs) {
            if(p1.getStatus ().equals ( "Em andamento" )) {
                System.out.println("\tProjeto: " + p1.getTitle () +", ano: "+p1.getStartdate ());
            }
        }

        if(e instanceof Teacher ){
            System.out.println("\n\tOrienta os seguintes alunos:");
            mentoringIt = (MentoringIterator) mentoringsCol.createIterator ();

            while(mentoringIt.hasNext ()){
                Mentoring m1 = (Mentoring) mentoringIt.next();
                Student s1 = m1.getStudent ();
                System.out.println("\tAluno: "+s1.getName () +", Tipo: "+s1.getType ());
            }
        }

        System.out.println("\n\tPublicacoes:");
        for (Publication p1: memberPubs) {
            System.out.println("\tPublicacao: " + p1.getTitle () +", ano: "+p1.getYear ());
        }

    }
}
