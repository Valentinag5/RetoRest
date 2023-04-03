package co.sofkau.stepdefinitions;


import co.sofkau.models.PostLoginExitoso;
import co.sofkau.models.ResponsePostLoginExitoso;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import co.sofkau.setup.ApiSetUp;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.assertj.core.api.Assertions;
import org.json.simple.JSONObject;

import static co.sofkau.questions.ReturnPostLoginExitoso.returnPostLoginExitoso;
import static co.sofkau.tasks.Post.postRegistroExitoso;
import static co.sofkau.utils.ReqresResources.*;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.apache.log4j.LogManager.getLogger;
import static org.hamcrest.CoreMatchers.equalTo;

public class PostLoginExitosoStepDefinitions extends ApiSetUp {

    private static final Logger LOGGER = getLogger(PostLoginExitosoStepDefinitions.class);
    JSONObject resBody = null;

    private PostLoginExitoso usuario = new PostLoginExitoso();
    @Dado("que el usuario esta en la pagina de login exitoso")
    public void queElUsuarioEstaEnLaPaginaDeLoginExitoso() {
        setUp(REQRES_BASE_URL.getValue());
    }
    @Cuando("el usuario envia la solicitud de login con el email y la contraseña")
    public void elUsuarioEnviaLaSolicitudDeLoginConElEmailYLaContraseña() {
        try {
            usuario = new PostLoginExitoso();
            usuario.setEmail("eve.holt@reqres.in");
            usuario.setClave("cityslicka");
            actor.attemptsTo(
                    postRegistroExitoso()
                            .conElRecurso(LOGIN_RESOURCE.getValue())
                            .conElCuerpo(usuario)
            );
           // System.out.println(SerenityRest.lastResponse().body().asString());
        }catch (Exception exception) {
            LOGGER.error("Error enviando informacion" + exception.getMessage());
            Assertions.fail(exception.getMessage(), exception);
        }
    }
    @Entonces("el usuario visualiza un codigo de respuesta de estado {int} y un {string}")
    public void elUsuarioVisualizaUnCodigoDeRespuestaDeEstadoYUn(Integer estado, String token) {
        try {
            ResponsePostLoginExitoso actualResponse = returnPostLoginExitoso().answeredBy(actor);
            actor.should(
                    seeThatResponse("El codigo de respuesta es: " + HttpStatus.SC_OK,
                            response -> response.statusCode(estado)),
                    seeThat("El token es: ",
                            ids -> actualResponse.getToken(),equalTo(token))
            );
            LOGGER.info("Codigo de respuesta: " + estado);
        } catch (Exception exception) {
            LOGGER.error("Error verificando el codigo de respuesta: " + exception.getMessage());
            Assertions.fail(exception.getMessage(), exception);
        }
    }

}
