package productivitysystem.colaborator.researcher;

import productivitysystem.colaborator.Colaborator;

public class Researcher implements Colaborator {
    private String name;
    private String email;

    @Override
    public String getEmail() {
        return this.email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    public Researcher(String name, String email){
        this.name = name;
        this.email = email;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;

    }


}
