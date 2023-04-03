package co.sofkau.tasks;

import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;

public class Patch implements Task {

    private String recurso;
    private Object cuerpo;

    public Patch conElRecurso(String recurso) {
        this.recurso = recurso;
        return this;
    }

    public Patch conElCuerpo(Object cuerpo) {
        this.cuerpo = cuerpo;
        return this;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                net.serenitybdd.screenplay.rest.interactions.Patch.to(recurso)
                        .with(
                                requestSpecification -> requestSpecification.relaxedHTTPSValidation()
                                        .contentType(ContentType.JSON)
                                        .body(cuerpo)
                        )
        );

    }

    public static Patch patch() {
        return new Patch();
    }

}