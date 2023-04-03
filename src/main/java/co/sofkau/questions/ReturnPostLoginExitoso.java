package co.sofkau.questions;

import co.sofkau.models.ResponsePostLoginExitoso;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class ReturnPostLoginExitoso implements Question<ResponsePostLoginExitoso> {


        @Override
        public ResponsePostLoginExitoso answeredBy(Actor actor) {
            return SerenityRest.lastResponse().as(ResponsePostLoginExitoso.class);
        }

        public static co.sofkau.questions.ReturnPostLoginExitoso returnPostLoginExitoso(){
            return new co.sofkau.questions.ReturnPostLoginExitoso();
        }
}


