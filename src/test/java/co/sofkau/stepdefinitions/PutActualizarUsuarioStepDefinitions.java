package co.sofkau.stepdefinitions;

import co.sofkau.models.PostCrearUsuario;
import co.sofkau.models.ResponsePostCrearUsuario;
import co.sofkau.setup.ApiSetUp;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.assertj.core.api.Assertions;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import static co.sofkau.questions.ReturnPostCrearUsuario.returnPostCrearUsuario;
import static co.sofkau.tasks.Put.put;
import static co.sofkau.utils.ReqresResources.*;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.apache.log4j.LogManager.getLogger;
import static org.hamcrest.CoreMatchers.equalTo;

public class PutActualizarUsuarioStepDefinitions extends ApiSetUp {

    private static final Logger LOGGER = getLogger(PutActualizarUsuarioStepDefinitions.class);
    JSONObject resBody = null;
    JSONParser parser = new JSONParser();

    private PostCrearUsuario usuario = new PostCrearUsuario();
    @Dado("que el usuario esta en la pagina de actualizacion")
    public void queElUsuarioEstaEnLaPaginaDeActualizacion() {
        setUp(REQRES_BASE_URL.getValue());
    }
    @Cuando("el usuario envia la solicitud de actualizacion con el nombre y el trabajo")
    public void elUsuarioEnviaLaSolicitudDeActualizacionConElNombreYElTrabajo() {
        try {
            usuario = new PostCrearUsuario();
            usuario.setNombre("morpheus");
            usuario.setTrabajo("zion resident");
            actor.attemptsTo(
                    put()
                            .conElRecurso(LIST_USERS.getValue())
                            .conElCuerpo(usuario)
            );
           // System.out.println(SerenityRest.lastResponse().body().asString());
        }catch (Exception exception) {
            LOGGER.error("Error enviando informacion" + exception.getMessage());
            Assertions.fail(exception.getMessage(), exception);
        }
    }
    @Entonces("el usuario visualiza un codigo de respuesta de estado {int}, el {string} y el {string}")
    public void elUsuarioVisualizaUnCodigoDeRespuestaDeEstadoElYEl(Integer estado, String nombre, String trabajo) {
        try {
            ResponsePostCrearUsuario actualResponse = returnPostCrearUsuario().answeredBy(actor);
            actor.should(
                    seeThatResponse("El codigo de respuesta es: " + HttpStatus.SC_OK,
                            response -> response.statusCode(estado)),
                    seeThat("Nombre",
                            nombr -> actualResponse.getNombre(),equalTo(nombre)),
                    seeThat("Trabajo",
                            trabaj -> actualResponse.getTrabajo(),equalTo(trabajo))
            );
            LOGGER.info("Codigo de respuesta: " + estado);
        } catch (Exception exception) {
            LOGGER.error("Error verificando el codigo de respuesta: " + exception.getMessage());
            Assertions.fail(exception.getMessage(), exception);
        }
    }
}
