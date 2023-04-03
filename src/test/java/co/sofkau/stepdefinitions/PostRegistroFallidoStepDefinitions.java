package co.sofkau.stepdefinitions;


import co.sofkau.models.PostRegistroFallido;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import co.sofkau.setup.ApiSetUp;
import net.serenitybdd.rest.SerenityRest;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.assertj.core.api.Assertions;
import org.json.simple.JSONObject;

import static co.sofkau.tasks.Post.postRegistroExitoso;
import static co.sofkau.utils.ReqresResources.*;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.apache.log4j.LogManager.getLogger;

public class PostRegistroFallidoStepDefinitions extends ApiSetUp {

    private static final Logger LOGGER = getLogger(PostRegistroFallidoStepDefinitions.class);
    JSONObject resBody = null;

    private PostRegistroFallido usuario = new PostRegistroFallido();

    @Dado("que el usuario esta en la pagina de registro fallido")
    public void queElUsuarioEstaEnLaPaginaDeRegistroFallido() {

        setUp(REQRES_BASE_URL.getValue());
    }
    @Cuando("el usuario envia la solicitud de registro con el email")
    public void elUsuarioEnviaLaSolicitudDeRegistroConElEmail() {
        try {
            usuario = new PostRegistroFallido();
            usuario.setEmail("sydney@fife");
            actor.attemptsTo(
                    postRegistroExitoso()
                            .conElRecurso(REGISTER_RESOURCE.getValue())
                            .conElCuerpo(usuario)
            );
            System.out.println(SerenityRest.lastResponse().body().asString());
        }catch (Exception exception) {
            LOGGER.error("Error enviando informacion" + exception.getMessage());
            Assertions.fail(exception.getMessage(), exception);
        }
    }

    @Entonces("el usuario visualiza un codigo de respuesta de estado {int}")
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
