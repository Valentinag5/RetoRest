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
import org.json.simple.JSONObject;

import static co.sofkau.questions.ReturnPostCrearUsuario.returnPostCrearUsuario;
import static co.sofkau.tasks.Patch.patch;
import static co.sofkau.tasks.Put.put;
import static co.sofkau.utils.ReqresResources.LIST_USERS;
import static co.sofkau.utils.ReqresResources.REQRES_BASE_URL;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.apache.log4j.LogManager.getLogger;
import static org.hamcrest.CoreMatchers.equalTo;

public class PatchActualizarUsuarioStepDefinitions extends ApiSetUp {

    private static final Logger LOGGER = getLogger(PutActualizarUsuarioStepDefinitions.class);
    JSONObject resBody = null;

    private PostCrearUsuario usuario = new PostCrearUsuario();
    @Dado("que el usuario esta en la pagina de actualizacion patch")
    public void queElUsuarioEstaEnLaPaginaDeActualizacionPatch() {
        setUp(REQRES_BASE_URL.getValue());
    }
    @Cuando("el usuario envia la solicitud de actualizacion patch con el nombre y el trabajo")
    public void elUsuarioEnviaLaSolicitudDeActualizacionPatchConElNombreYElTrabajo() {
        try {
            usuario = new PostCrearUsuario();
            usuario.setNombre("morpheus");
            usuario.setTrabajo("zion resident");
            actor.attemptsTo(
                    patch()
                            .conElRecurso(LIST_USERS.getValue())
                            .conElCuerpo(usuario)
            );
          //  System.out.println(SerenityRest.lastResponse().body().asString());
        }catch (Exception exception) {
            LOGGER.error("Error enviando informacion" + exception.getMessage());
            Assertions.fail(exception.getMessage(), exception);
        }
    }
    @Entonces("el usuario visualiza un codigo de respuesta de estado {int}, el nombre {string} y el trabajo {string}")
    public void elUsuarioVisualizaUnCodigoDeRespuestaDeEstadoElNombreYElTrabajo(Integer estado, String nombre, String trabajo) {
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
