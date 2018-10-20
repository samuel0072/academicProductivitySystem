package productivitysystem.util.iterator.colections;

import productivitysystem.colaborator.Colaborator;
import productivitysystem.production.Publication;
import productivitysystem.util.iterator.iterators.ColaboratorIterator;
import productivitysystem.util.iterator.iterators.Iterator;
import productivitysystem.util.iterator.iterators.PublicationIterator;

import java.util.ArrayList;

public class PublicationCollection implements Collection{
    private int numItens;
    private ArrayList<Publication> publications;

    public PublicationCollection(){
        this.publications = new ArrayList <Publication> (  );
        this.numItens = 0;
    }

    @Override
    public Iterator createIterator() {
        return new PublicationIterator (this.publications);
    }

    public void addPublications(Publication e){
        publications.add ( e );
        this.numItens +=1;
    }

    public int getNumItens(){
        return this.numItens;
    }

    public ArrayList<Publication> sortByYear(){
        ArrayList<Publication> target = new ArrayList <Publication> (  );
        PublicationIterator it = (PublicationIterator)this.createIterator ();

        while(it.hasNext ()){
            Publication a = (Publication) it.next();
            target.add(a);
        }

        for(int i = 0; i < target.size (); i++){
            for (int j = 0; j < target.size () - 1; j++){
                Publication a = target.get ( j );
                Publication b = target.get( j + 1);
                if(a.getYear () < b.getYear ()){
                    target.add ( j+1, a );
                    target.add(j, b);
                }
            }
        }
        return target;

    }

}
