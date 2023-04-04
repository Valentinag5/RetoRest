package co.sofkau.questions;
import co.sofkau.models.Response;
import co.sofkau.models.ResponsePostCrearUsuario;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class ReturnPostCrearUsuario implements Question<String> {

    @Override
    public String answeredBy(Actor actor) {
        return SerenityRest.lastResponse().asString();
    }

    public static ReturnPostCrearUsuario returnPostCrearUsuario(){
        return new ReturnPostCrearUsuario();
    }
}
