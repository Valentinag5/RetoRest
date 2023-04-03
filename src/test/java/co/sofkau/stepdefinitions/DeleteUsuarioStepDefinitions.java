package co.sofkau.stepdefinitions;

import co.sofkau.models.PostCrearUsuario;
import co.sofkau.models.ResponsePostCrearUsuario;
import co.sofkau.setup.ApiSetUp;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import net.serenitybdd.rest.SerenityRest;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.assertj.core.api.Assertions;

import static co.sofkau.questions.ReturnPostCrearUsuario.returnPostCrearUsuario;
import static co.sofkau.tasks.Delete.delete;
import static co.sofkau.tasks.Patch.patch;
import static co.sofkau.utils.ReqresResources.LIST_USERS;
import static co.sofkau.utils.ReqresResources.REQRES_BASE_URL;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.apache.log4j.LogManager.getLogger;
import static org.hamcrest.CoreMatchers.equalTo;

public class DeleteUsuarioStepDefinitions extends ApiSetUp {

    private static final Logger LOGGER = getLogger(PutActualizarUsuarioStepDefinitions.class);
    @Dado("que el usuario esta en la pagina de eliminacion")
    public void queElUsuarioEstaEnLaPaginaDeEliminacion() {
        setUp(REQRES_BASE_URL.getValue());
    }
    @Cuando("el usuario envia la solicitud de eliminacion")
    public void elUsuarioEnviaLaSolicitudDeEliminacion() {
        try {
            actor.attemptsTo(
                    delete()
                            .conElRecurso(LIST_USERS.getValue())
            );
        }catch (Exception exception) {
            LOGGER.error("Error enviando informacion" + exception.getMessage());
            Assertions.fail(exception.getMessage(), exception);
        }
    }
    @Entonces("el usuario visualiza un codigo de respuesta de estado <{int}>")
    public void elUsuarioVisualizaUnCodigoDeRespuestaDeEstado(Integer estado) {
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
