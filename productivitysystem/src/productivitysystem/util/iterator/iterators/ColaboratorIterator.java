package productivitysystem.util.iterator.iterators;

import productivitysystem.colaborator.Colaborator;
import productivitysystem.util.iterator.colections.Collection;

import java.util.ArrayList;

public class ColaboratorIterator implements Iterator{
    private ArrayList<Colaborator> colaborators;
    private int index;

    public ColaboratorIterator(ArrayList <Colaborator> colaborators) {
        this.colaborators = colaborators;
        this.index = 0;
    }
    @Override
    public boolean hasNext() {
        if(this.index >= colaborators.size () || colaborators.get ( index ) == null){
            return false;
        }
        return true;
    }

    @Override
    public Object next() {
        Colaborator e = this.colaborators.get ( index );
        this.index = this.index+1;
        return e;
    }

    @Override
    public int index() {
        return this.index;
    }
}
