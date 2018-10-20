package productivitysystem.util.iterator.colections;

import productivitysystem.colaborator.Colaborator;
import productivitysystem.project.Project;
import productivitysystem.util.iterator.iterators.Iterator;
import productivitysystem.util.iterator.iterators.ProjectIterator;


import java.util.ArrayList;

public class ProjectCollection implements Collection{
    private int numItens;
    private ArrayList<Project> projects;

    public ProjectCollection(){
        this.projects = new ArrayList <Project> (  );
        this.numItens = 0;
    }

    @Override
    public Iterator createIterator() {
        return new ProjectIterator (this.projects);
    }

    public void addProject(Project e){
        projects.add ( e );
        this.numItens +=1;
    }

    public int getNumItens(){
        return this.numItens;
    }
}
