package co.sofkau.tasks;

import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;

public class Put implements Task {

    private String recurso;
    private Object cuerpo;

    public Put conElRecurso(String recurso) {
        this.recurso = recurso;
        return this;
    }

    public Put conElCuerpo(Object cuerpo) {
        this.cuerpo = cuerpo;
        return this;
    }
    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                net.serenitybdd.screenplay.rest.interactions.Put.to(recurso)
                        .with(
                                requestSpecification -> requestSpecification.relaxedHTTPSValidation()
                                        .contentType(ContentType.JSON)
                                        .body(cuerpo)
                        )
        );

    }

    public static Put put(){
        return new Put();
    }
}