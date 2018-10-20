package productivitysystem.colaborator.researcher;

/*
 * @this class implements decorator design pattern
 *
 * */

public class ResearcherWithProject extends Researcher{
    String role;
    public ResearcherWithProject(Researcher rt, String role){
        super(rt.getName (),rt.getEmail ());
        this.role = role;
    }
}
