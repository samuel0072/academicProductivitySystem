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
    private String startdate;
    private String endDate;
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

    public Project(String title, String startdate, String endDate,
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

    public String getStartdate(){
        return this.startdate;
    }

    public String getEndDate(){
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

    public void showMembers(){
        ColaboratorIterator e = (ColaboratorIterator) members.createIterator ();
        System.out.println("Colaboradores deste projeto:");
        while( e.hasNext ()){
            Colaborator cc = (Colaborator) e.next ();
            System.out.println ( "Colaborador: " +cc.getName ()+" Email: "+cc.getEmail ());
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

            if(oneTeacher && title!= null && startdate != null && endDate!= null
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
            else if(!(e instanceof Student))
                this.members.addColaborator ( e );
        }

    }

    public void addpublication(Publication e){
        publicacoes.addPublications ( e );
    }

    public Colaborator getManager(){
        return this.manager;
    }

    /*Item 3-b*/
    public void projectQuery(){

        System.out.println("Dados do projeto:");
        System.out.println("Titulo: "+ this.getTitle ());
        System.out.println("Data de inicio: "+ this.getStartdate ());
        System.out.println("Data de termino: " + this.getEndDate ());
        System.out.println("Agencia de financiamento: "+this.getFinancierAgency ());
        System.out.println ( "Valor do financiamento: "+ this.getValue () );
        System.out.println("Descricao: "+ this.getDescription ());
        this.showMembers ();
        System.out.println("Gerente do projeto : "+ manager.getName ()+", Email:"+manager.getEmail ());
        System.out.println("Status do projeto"+ this.getStatus ());
        System.out.println("Producoes academicas:");
        ArrayList<Publication> e = publicacoes.sortByYear ();

        for(Publication a: e){
            System.out.println("Publicacao: "+a.getTitle ()+", ano: "+a.getYear ());
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
