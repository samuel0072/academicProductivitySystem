package productivitysystem.production;

import productivitysystem.colaborator.Colaborator;
import productivitysystem.util.iterator.colections.ColaboratorCollection;
import productivitysystem.util.iterator.iterators.ColaboratorIterator;
import productivitysystem.project.Project;

import java.util.ArrayList;

public class Publication{

    private ColaboratorCollection authors;
    private String conference;
    private int year;
    private String title;
    Project project;

    public Publication(String conference, int year, String title){
        this.authors = new ColaboratorCollection ();
        this.title = title;
        this.conference = conference;
        this.year = year;
    }

    public void showAuthors(){
        ColaboratorIterator e = (ColaboratorIterator) authors.createIterator ();
        System.out.println("\n\tColaboradores desta publicação:");
        while( e.hasNext ()){
            Colaborator cc = (Colaborator) e.next ();
            System.out.println ( "\tColaborador: " +cc.getName ()+" Email:"+cc.getEmail ());
        }

    }

    public String getTitle(){
        return this.title;
    }

    public String getConference(){
        return this.conference;
    }

    public int getYear(){
        return this.year;
    }

    public void addAuthor(Colaborator e){
        this.authors.addColaborator ( e );
    }

    public Colaborator getColaborator(String email) {
        ColaboratorIterator e = (ColaboratorIterator)authors.createIterator ();
        Colaborator target  = null;
        while(e.hasNext ()) {
            Colaborator a = (Colaborator)e.next ();
            if(a.getEmail ().equals ( email )){
                target = a;
                break;
            }
        }
        return target;
    }

    public Project getProject(){
        return this.project;
    }
    /*Item 2 implementado aqui*/
    public boolean addProject(Project e){
        boolean suceed = false;
        if(this.project == null && e.getStatus().equals("Em andamento")){
            project = e;
            e.addpublication ( this );
            suceed =  true;
        }
        return suceed;
    }
}
