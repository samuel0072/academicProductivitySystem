package productivitysystem.util.iterator.iterators;

import productivitysystem.production.Mentoring;

import java.util.ArrayList;

public class MentoringIterator implements Iterator{
    private ArrayList<Mentoring> mentorings;
    private int index;

    public MentoringIterator(ArrayList <Mentoring> mentorings) {
        this.mentorings = mentorings;
        this.index = 0;
    }
    @Override
    public boolean hasNext() {
        if(this.index >= mentorings.size () || mentorings.get ( index ) == null){
            return false;
        }
        return true;
    }

    @Override
    public Object next() {
        Mentoring e = this.mentorings.get ( index );
        index = index  + 1;
        return e;
    }

    @Override
    public int index() {
        return this.index;
    }
}
