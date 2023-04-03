package co.sofkau.stepdefinitions;

import co.sofkau.models.PostRegistroFallido;
import co.sofkau.setup.ApiSetUp;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.assertj.core.api.Assertions;
import org.json.simple.JSONObject;

import static co.sofkau.tasks.Post.postRegistroExitoso;
import static co.sofkau.utils.ReqresResources.LOGIN_RESOURCE;
import static co.sofkau.utils.ReqresResources.REQRES_BASE_URL;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.apache.log4j.LogManager.getLogger;

public class PostLoginFallidoStepDefinitions extends ApiSetUp {

    private static final Logger LOGGER = getLogger(PostLoginExitosoStepDefinitions.class);
    JSONObject resBody = null;

    private PostRegistroFallido usuario = new PostRegistroFallido();

    @Dado("que el usuario esta en la pagina de login fallido")
    public void queElUsuarioEstaEnLaPaginaDeLoginFallido() {
        setUp(REQRES_BASE_URL.getValue());
    }
    @Cuando("el usuario envia la solicitud de login con el email")
    public void elUsuarioEnviaLaSolicitudDeLoginConElEmail() {
        try {
            usuario = new PostRegistroFallido();
            usuario.setEmail("peter@klaven");
            actor.attemptsTo(
                    postRegistroExitoso()
                            .conElRecurso(LOGIN_RESOURCE.getValue())
                            .conElCuerpo(usuario)
            );
          //  System.out.println(SerenityRest.lastResponse().body().asString());
        }catch (Exception exception) {
            LOGGER.error("Error enviando informacion" + exception.getMessage());
            Assertions.fail(exception.getMessage(), exception);
        }
    }
    @Entonces("el usuario visualiza un codigo de respuesta  {int}")
    public void elUsuarioVisualizaUnCodigoDeRespuesta(Integer estado) {
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
