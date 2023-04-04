package co.sofkau.questions;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class ReturnGetListar implements Question<String> {

    @Override
    public String answeredBy(Actor actor) {

        return SerenityRest.lastResponse().asString();

    }

    public static ReturnGetListar returnGetListar(){
        return new ReturnGetListar();
    }
}
