package productivitysystem.util.iterator.colections;

import productivitysystem.production.Mentoring;
import productivitysystem.util.iterator.iterators.Iterator;
import productivitysystem.util.iterator.iterators.MentoringIterator;

import java.util.ArrayList;

public class MentoringCollection implements Collection {

    private int numItens;
    private ArrayList<Mentoring> mentorings;

    public MentoringCollection(){
        this.mentorings = new ArrayList <Mentoring> (  );
        this.numItens = 0;
    }

    @Override
    public Iterator createIterator() {
        return new MentoringIterator (this.mentorings);
    }

    public void addMentoring(Mentoring e){
        mentorings.add ( e );
        this.numItens +=1;
    }

    public int getNumItens(){
        return this.numItens;
    }
}
