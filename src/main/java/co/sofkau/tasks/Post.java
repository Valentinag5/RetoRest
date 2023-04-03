package co.sofkau.tasks;

import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;

public class Post implements Task {

    private String recurso;
    private Object cuerpo;

    public Post conElRecurso(String recurso){
        this.recurso=recurso;
        return this;
    }

    public Post conElCuerpo (Object cuerpo){
        this.cuerpo=cuerpo;
        return this;
    }
    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                net.serenitybdd.screenplay.rest.interactions.Post.to(recurso)
                        .with(
                                requestSpecification -> requestSpecification.relaxedHTTPSValidation()
                                        .contentType(ContentType.JSON)
                                        .body(cuerpo)
                        )
        );

    }
    public static Post postRegistroExitoso(){
        return new Post();
    }

}
