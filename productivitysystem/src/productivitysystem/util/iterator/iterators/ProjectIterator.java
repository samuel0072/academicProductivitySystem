package productivitysystem.util.iterator.iterators;

import productivitysystem.project.Project;

import java.util.ArrayList;

public class ProjectIterator implements Iterator{
    private ArrayList<Project> projects;
    private int index;

    public ProjectIterator(ArrayList <Project> projects) {
        this.projects = projects;
        this.index = 0;
    }
    @Override
    public boolean hasNext() {
        if(this.index >= projects.size () || projects.get ( index ) == null){
            return false;
        }
        return true;
    }

    @Override
    public Object next() {
        Project e = this.projects.get ( index );
        index = index +1;
        return e;
    }

    @Override
    public int index() {
        return this.index;
    }
}
