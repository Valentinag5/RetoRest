package co.sofkau.stepdefinitions;

import co.sofkau.setup.ApiSetUp;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import org.apache.hc.core5.http.HttpStatus;
import org.apache.log4j.Logger;
import org.assertj.core.api.Assertions;

import static co.sofkau.tasks.Get.getListarUsuarios;
import static co.sofkau.utils.ReqresResources.*;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.apache.log4j.LogManager.getLogger;

public class GetNotFoundStepDefinitions extends ApiSetUp {

    private static final Logger LOGGER = getLogger(GetNotFoundStepDefinitions.class);

    @Dado("que el usuario esta en la pagina de reqres")
    public void queElUsuarioEstaEnLaPaginaDeReqres() {
        setUp(REQRES_BASE_URL.getValue());
    }
    @Cuando("el usuario envia la peticion")
    public void elUsuarioEnviaLaPeticion() {
        try {
            actor.attemptsTo(
                    getListarUsuarios().withTheResource(GET_NOT_FOUND.getValue())
            );
        } catch (Exception exception) {
            LOGGER.error("Error enviando informacion" + exception.getMessage());
            Assertions.fail(exception.getMessage(), exception);
        }
    }
    @Entonces("el usuario visualiza el codigo de respuesta {int}")
    public void elUsuarioVisualizaElCodigoDeRespuesta(Integer estado) {
        try {
            actor.should(
                    seeThatResponse("El codigo de respuesta es: " + HttpStatus.SC_OK,
                            response -> response.statusCode(estado))
            );
            LOGGER.info("Codigo de respuesta: " + estado);
        } catch (Exception exception) {
            LOGGER.error("Error verificando el codigo de respuesta: " + exception.getMessage());
            Assertions.fail(exception.getMessage(), exception);
        }
    }
}
