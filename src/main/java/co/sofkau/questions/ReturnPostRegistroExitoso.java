package co.sofkau.questions;
import co.sofkau.models.ResponsePostCrearUsuario;
import co.sofkau.models.ResponsePostRegistroExitoso;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class ReturnPostRegistroExitoso implements Question<ResponsePostRegistroExitoso> {

    @Override
    public ResponsePostRegistroExitoso answeredBy(Actor actor) {
        return SerenityRest.lastResponse().as(ResponsePostRegistroExitoso.class);
    }

    public static ReturnPostRegistroExitoso returnPostRegistroExitoso(){
        return new ReturnPostRegistroExitoso();
    }
}
