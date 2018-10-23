package productivitysystem.util.iterator.iterators;

import productivitysystem.colaborator.Colaborator;
import productivitysystem.production.Publication;

import java.util.ArrayList;

public class PublicationIterator implements Iterator{
    private ArrayList<Publication> publications;
    private int index;

    public PublicationIterator(ArrayList <Publication> publications) {
        this.publications = publications;
        this.index = 0;
    }
    @Override
    public boolean hasNext() {
        if(this.index >= publications.size () || publications.get ( index ) == null){
            return false;
        }
        return true;
    }

    @Override
    public Object next() {
        Publication e = this.publications.get ( index );
        index = index +1;
        return e;
    }

    @Override
    public int index() {
        return this.index;
    }
}
