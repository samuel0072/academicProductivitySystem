package productivitysystem.project;

import productivitysystem.colaborator.Colaborator;
import productivitysystem.colaborator.student.Student;
import productivitysystem.colaborator.teacher.Teacher;
import productivitysystem.production.Publication;
import productivitysystem.util.iterator.colections.ColaboratorCollection;
import productivitysystem.util.iterator.colections.PublicationCollection;
import productivitysystem.util.iterator.iterators.ColaboratorIterator;

import java.util.ArrayList;

/*
* This class uses the design pattern Observer*/
public class Project {

    private String title;
    private int startdate;
    private int endDate;
    private String financierAgency;
    private double value;
    private String objective;
    private String description;
    private ColaboratorCollection members;
    private PublicationCollection publicacoes;
    private String status;

    Colaborator manager;

    boolean oneTeacher;

    private ArrayList<Student> observers = new ArrayList <Student> (  );

    public Project(String title, int startdate, int endDate,
                   String financierAgency, double value, String objective,
                   String description, String status, Colaborator manager)  {

        this.members = new ColaboratorCollection ();
        this.publicacoes = new PublicationCollection ();
        this.members.addColaborator ( manager );

        this.description = description;
        this.endDate = endDate;
        this.financierAgency = financierAgency;
        this.manager = manager;
        this.objective = objective;
        this.status = status;
        this.title = title;
        this.startdate = startdate;

        if(value > 0){
            this.value = value;
        }
        if(manager instanceof Teacher ) {
            oneTeacher = true;
        }
        else{
            oneTeacher = false;
        }
    }

    public String getTitle(){
        return this.title;
    }

    public int getStartdate(){
        return this.startdate;
    }

    public int getEndDate(){
        return this.endDate;
    }

    public String getFinancierAgency(){
        return this.financierAgency;
    }

    public String getObjective(){
        return this.objective;
    }

    public String getDescription(){
        return this.description;
    }

    public String getStatus(){
        return this.status;
    }

    public double getValue(){
        return this.value;
    }

    public Colaborator getManager(){
        return this.manager;
    }

    public Colaborator getMember(String email) {
        ColaboratorIterator e = (ColaboratorIterator) members.createIterator ();
        Colaborator target = null;

        while(e.hasNext ()) {
            Colaborator a = (Colaborator)e.next ();
            if(a.getEmail ().equals ( email )){
                target = a;
                break;
            }
        }
        return target;
    }


    public void showMembers(){
        ColaboratorIterator e = (ColaboratorIterator) members.createIterator ();
        System.out.println("\n\tColaboradores deste projeto:\n");
        while( e.hasNext ()){
            Colaborator cc = (Colaborator) e.next ();
            System.out.println ( "\tColaborador: " +cc.getName ()+" Email: "+cc.getEmail ());
        }
    }

    public void setValue(double value){
        if(value > 0){
            this.value = value;
        }
    }

    /*item 1-b*/
    public boolean setStatus(String status){
        boolean suceed = false;
        if(this.status.equals("Em elaboracao") && status.equals ( "Em andamento" )){

            if(oneTeacher && title!= null && startdate != 0 && endDate!= 0
                    && financierAgency != null && value > 0 && objective != null
                    && description != null && members.getNumItens () > 0
                    && manager != null) {

                this.status = "Em andamento";
                this.updateInc ();
                suceed = true;
            }
        }
        else if(this.status.equals ( "Em andamento" ) && status.equals ( "Concluido" )) {

            if(this.publicacoes.getNumItens () > 0){
                this.status = "Concluido";
                this.updateDec ();
                suceed = true;
            }
        }
        return suceed;
    }

    public void addMember(Colaborator e){

        if(this.getStatus ().equals ( "Em elaboracao" )){
            if(e instanceof Teacher && !oneTeacher){
                oneTeacher = true;
            }
            if(e instanceof Student && ((Student) e).getType ().equals ( "Graduacao" )
                    && ((Student) e).getProjectsInProgress () < 2) {
                this.members.addColaborator ( e );
                this.attach ( (Student) e );

            }
            else if((!(e instanceof Student)) || (!((Student) e).getType ().equals ( "Graduacao" )))
                this.members.addColaborator ( e );
        }

    }

    public void addPublication(Publication e){
        if(this.status.equals ( "Em andamento" ) ){
            publicacoes.addPublications ( e );
            if(e.getProject () == null) {
                e.addProject ( this );
            }
        }

    }

    /*Item 3-b*/
    public void projectQuery(){

        System.out.println("Dados do projeto:\n");
        System.out.println("\tTitulo: "+ this.getTitle ());
        System.out.println("\n\tData de inicio: "+ this.getStartdate ());
        System.out.println("\n\tData de termino: " + this.getEndDate ());
        System.out.println("\n\tAgencia de financiamento: "+this.getFinancierAgency ());
        System.out.println ( "\n\tValor do financiamento: "+ this.getValue () );
        System.out.println("\n\tDescricao: "+ this.getDescription ());
        this.showMembers ();
        System.out.println("\n\tGerente do projeto : "+ manager.getName ()+", Email:"+manager.getEmail ());
        System.out.println("\n\tStatus do projeto: "+ this.getStatus ());
        System.out.println("\n\tProducoes academicas:");
        ArrayList<Publication> e = publicacoes.sortByYear ();

        for(Publication a: e){
            System.out.println("\n\tPublicacao: "+a.getTitle ()+", ano: "+a.getYear ());
        }

    }

    /*Observer*/

    public void attach(Student e){
        this.observers.add ( e );
    }

    public void detach(Student e){
        this.observers.remove ( e );
    }

    public void updateInc(){
        for (Student e:
             observers) {
            e.addProjectInProgress ();
        }
    }

    public void updateDec(){
        for (Student e:
             observers) {
            e.removeProjectInProgress ();

        }
    }

}
