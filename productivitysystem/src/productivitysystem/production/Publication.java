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

    public Publication(ColaboratorCollection authors, String conference, int year, String title){
        this.authors = authors;
        this.title = title;
        this.conference = conference;
        this.year = year;
    }

    public void showAuthors(){
        ColaboratorIterator e = (ColaboratorIterator) authors.createIterator ();
        System.out.println("Colaboradores desta publicação:");
        while( e.hasNext ()){
            Colaborator cc = (Colaborator) e.next ();
            System.out.println ( "Colaborador: " +cc.getName ()+" Email:"+cc.getEmail ());
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

    /*Item 2 implementado aqui*/
    public void addProject(Project e){
        if(this.project == null && e.getStatus().equals("Em adamento")){
            project = e;
        }
    }
}
