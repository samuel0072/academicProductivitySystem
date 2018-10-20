package productivitysystem.laboratory;

import productivitysystem.colaborator.Colaborator;
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

public class Laboratory {

    private static Laboratory _instance = null;

    private ColaboratorCollection colaborators;
    private ProjectCollection projects;
    private MentoringCollection mentorings;
    private PublicationCollection publications;

    protected Laboratory(){
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

    public Mentoring getMentoring(String teacherEmail, String studentEmail){
        MentoringIterator e = (MentoringIterator) mentorings.createIterator ();
        Mentoring target = null;
        while(e.hasNext ()){
            Mentoring a = (Mentoring) e.next ();
            if(a.getTeacher ().getEmail ().equals ( teacherEmail )
                    && a.getStudent ().getEmail ().equals ( studentEmail )){
                target = a;
            }
        }
        return target;

    }

    public Publication getPublication(String title){
        PublicationIterator e = (PublicationIterator) publications.createIterator ();
        Publication target = null;
        while(e.hasNext ()){
            Publication a = (Publication) e.next ();
            if(a.getTitle () == title){
                target = a;
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

        System.out.println("Relatorio de producoes academicas do laboratorio");

        System.out.println("Numero de colaboradores: " + colaborators.getNumItens ());

        System.out.println("Numero de projetos em elaboracao: " + elaboring);
        System.out.println("Numero de projetos em andamento: " + progress );
        System.out.println("Numero de projetos finalizados: "+ done);
        System.out.println("Numero total de projetos:" + projects.getNumItens ());

        System.out.println("Numero de publicacoes: " + publications.getNumItens ());
        System.out.println("Numero de orientacoes: " + mentorings.getNumItens ());



    }
}
