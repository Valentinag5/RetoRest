package co.sofkau.tasks;

import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;

public class Delete implements Task {

    private String recurso;

    public Delete conElRecurso(String recurso) {
        this.recurso = recurso;
        return this;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                net.serenitybdd.screenplay.rest.interactions.Delete.from(recurso)
                        .with(
                                requestSpecification -> requestSpecification.relaxedHTTPSValidation()
                                        .contentType(ContentType.JSON)
                        )
        );
    }

    public static Delete delete(){
        return new Delete();
    }
}
