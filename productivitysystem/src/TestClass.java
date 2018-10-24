import productivitysystem.colaborator.researcher.Researcher;
import productivitysystem.colaborator.student.Student;
import productivitysystem.colaborator.teacher.Teacher;
import productivitysystem.laboratory.Laboratory;
import productivitysystem.production.Mentoring;
import productivitysystem.production.Publication;
import productivitysystem.project.Project;

public class TestClass {
    public static void main(String[] args) {
        Laboratory laboratory = Laboratory.Instance ();

        Student s1, s2, s3;
        Teacher t1, t2, t3;
        Researcher r1, r2, r3;
        Project p1, p2, p3;
        Publication pu1, pu2, pu3;
        Mentoring m1, m2 , m3;

        s1 = new Student ( "Samuel", "Graduacao", "sls@ic.ufal.br"   );
        s2 = new Student ( "Fernando", "Graduacao", "lfsl@ic.ufal.br");
        s3 = new Student ( "Eric", "Graduacao", "esc2@ic.ufal.br"    );
        laboratory.addColaborator ( s1 );
        laboratory.addColaborator ( s2 );
        laboratory.addColaborator ( s3 );

        t1 = new Teacher ( "Baldoino", "baldu@ic.ufal.br" );
        t2 = new Teacher ("Rodrigo", "rodrigo@ic.ufal.br");
        t3 = new Teacher ("Roberta", "roberta@ic.ufal.br");
        laboratory.addColaborator ( t1 );
        laboratory.addColaborator ( t2 );
        laboratory.addColaborator ( t3 );

        r1 = new Researcher ( "Andre lage", "lage@ic.ufal.br" );
        r2 = new Researcher ( "Eliana", "eliana@ic.ufal.br" );
        r3 = new Researcher ( "Willy", "willy@ic.ufal.br" );
        laboratory.addColaborator ( r1 );
        laboratory.addColaborator ( r2 );
        laboratory.addColaborator ( r3 );

        p1 = new Project ( "Superdumpy1", 2018, 2019,
                "SamuelLimited", 10000, "Alguma coisa",
                "Isso ae", "Em elaboracao", t1);
        p2 = new Project("Superdumpy2", 2019, 2020,
                "SamuelLimited", 2000, "ALgo",
                "isso ae", "Em elaboracao", t2);
        p3 = new Project("Superdumpy3", 2020, 2021,
                "SamuelLimited", 20000, "kkk",
                "isso ae", "Em elaboracao", r1);
        laboratory.addProject ( p1 );
        laboratory.addProject ( p2 );
        laboratory.addProject ( p3 );

        pu1 = new Publication ( "Start", 2019, "Sup1" );
        pu2 = new Publication ( "start", 2020,"Sup2" );
        pu3 = new Publication ( "start", 2021, "Sup3" );
        laboratory.addPublication ( pu1 );
        laboratory.addPublication ( pu2 );
        laboratory.addPublication ( pu3 );

        m1 = new Mentoring ( t1, s1 );
        m2 = new Mentoring ( t2, s2 );
        m3 = new Mentoring ( t3, s3 );
        laboratory.addMentoring ( m1 );
        laboratory.addMentoring ( m2 );
        laboratory.addMentoring ( m3 );

        p1.addMember ( s1 );
        p1.addMember ( s2 );
        p1.addMember ( s3 );
        p1.addMember ( t2 );
        p1.addMember ( r2 );

        p2.addMember ( s1 );
        p2.addMember ( s2 );
        p2.addMember ( s3 );
        p2.addMember ( t1 );
        p2.addMember ( r1 );

        p3.addMember ( s1 );
        p3.addMember ( s2 );
        p3.addMember ( s3 );
        p3.addMember ( t1 );
        p3.addMember ( r2 );
        p1.setStatus ( "Em andamento" );

        pu1.addAuthor ( s1 );
        pu2.addAuthor ( t1 );
        pu1.addAuthor ( t1 );
        laboratory.memberProduction ( s1 );
        //System.out.println(laboratory.getPublication ( "Sup1" ).getTitle ());

    }
}
