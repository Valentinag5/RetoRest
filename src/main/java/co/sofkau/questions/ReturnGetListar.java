package co.sofkau.questions;
import co.sofkau.models.ResponseGetListar;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class ReturnGetListar implements Question<ResponseGetListar> {

    @Override
    public ResponseGetListar answeredBy(Actor actor) {

        return SerenityRest.lastResponse().as(ResponseGetListar.class);

    }

    public static ReturnGetListar returnGetListar(){
        return new ReturnGetListar();
    }
}
