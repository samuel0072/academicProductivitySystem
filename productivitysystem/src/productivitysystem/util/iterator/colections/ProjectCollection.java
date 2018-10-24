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

    public ArrayList<Project> sortByYear() {
        ArrayList<Project> target = new ArrayList <> (  );
        ProjectIterator it = (ProjectIterator) this.createIterator ();

        while(it.hasNext ()){
            Project a = (Project) it.next();
            target.add(a);
        }

        for(int i = 0; i < target.size (); i++){
            for (int j = 0; j < target.size () - 2; j++){
                Project a = target.get ( j );
                Project b = target.get( j + 1);
                if(a.getStartdate () > b.getStartdate ()){
                    target.add ( j+1, a );
                    target.add(j, b);
                }
            }
        }
        return target;

    }
}
