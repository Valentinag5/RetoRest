package co.sofkau.questions;
import co.sofkau.models.ResponsePostCrearUsuario;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class ReturnPostCrearUsuario implements Question<ResponsePostCrearUsuario> {

    @Override
    public ResponsePostCrearUsuario answeredBy(Actor actor) {
        return SerenityRest.lastResponse().as(ResponsePostCrearUsuario.class);
    }

    public static ReturnPostCrearUsuario returnPostCrearUsuario(){
        return new ReturnPostCrearUsuario();
    }
}
