package productivitysystem.util.iterator.colections;

import productivitysystem.colaborator.Colaborator;
import productivitysystem.util.iterator.iterators.ColaboratorIterator;
import productivitysystem.util.iterator.iterators.Iterator;

import java.util.ArrayList;

public class ColaboratorCollection implements Collection {
    private int numItens;
    private ArrayList<Colaborator> colaborators;

    public ColaboratorCollection(){
        this.colaborators = new ArrayList <Colaborator> (  );
        this.numItens = 0;
    }

    @Override
    public Iterator createIterator() {
        return new ColaboratorIterator (this.colaborators);
    }

    public void addColaborator(Colaborator e){
        colaborators.add ( e );
        this.numItens +=1;
    }

    public int getNumItens(){
        return this.numItens;
    }
}
