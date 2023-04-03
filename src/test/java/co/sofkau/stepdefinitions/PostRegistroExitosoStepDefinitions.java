package co.sofkau.stepdefinitions;

import co.sofkau.models.ResponsePostRegistroExitoso;
import co.sofkau.setup.ApiSetUp;
import co.sofkau.models.PostRegistroExitoso;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import net.serenitybdd.rest.SerenityRest;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.assertj.core.api.Assertions;
import org.json.simple.JSONObject;

import static co.sofkau.questions.ReturnPostRegistroExitoso.returnPostRegistroExitoso;
import static co.sofkau.tasks.Post.postRegistroExitoso;
import static co.sofkau.utils.ReqresResources.*;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.apache.log4j.LogManager.getLogger;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

public class PostRegistroExitosoStepDefinitions extends ApiSetUp {

    private static final Logger LOGGER = getLogger(PostRegistroExitosoStepDefinitions.class);
    JSONObject resBody = null;

    private PostRegistroExitoso usuario = new PostRegistroExitoso();

    @Dado("que el usuario esta en la pagina de registro")
    public void queElUsuarioEstaEnLaPaginaDeRegistro() {

        setUp(REQRES_BASE_URL.getValue());
    }

    @Cuando("el usuario envia la solicitud de registro con el email y la contraseña")
    public void elUsuarioEnviaLaSolicitudDeRegistroConElEmailYLaContraseña() {
        try {
            usuario = new PostRegistroExitoso();
            usuario.setEmail("eve.holt@reqres.in");
            usuario.setPassword("pistol");
            actor.attemptsTo(
                    postRegistroExitoso()
                            .conElRecurso(REGISTER_RESOURCE.getValue())
                            .conElCuerpo(usuario)
            );
         //   System.out.println(SerenityRest.lastResponse().body().asString());
        }catch (Exception exception) {
            LOGGER.error("Error enviando informacion" + exception.getMessage());
            Assertions.fail(exception.getMessage(), exception);
        }
    }

    @Entonces("el usuario visualiza un codigo de respuesta de estado {int} y un {int} un {string}")
    public void elUsuarioVisualizaUnCodigoDeRespuestaDeEstadoYUnUn(Integer estado, Integer id, String token) {
        try {
            ResponsePostRegistroExitoso actualResponse = returnPostRegistroExitoso().answeredBy(actor);
            actor.should(
                    seeThatResponse("El codigo de respuesta es: " + HttpStatus.SC_OK,
                            response -> response.statusCode(estado)),
                    seeThat("Id",
                            ids -> actualResponse.getId(),equalTo(id)),
                    seeThat("Token",
                            tokens -> actualResponse.getToken(),equalTo(token))
            );
            LOGGER.info("Codigo de respuesta: " + estado);
        } catch (Exception exception) {
            LOGGER.error("Error verificando el codigo de respuesta: " + exception.getMessage());
            Assertions.fail(exception.getMessage(), exception);
        }
    }
}
