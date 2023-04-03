package co.sofkau.stepdefinitions;

import co.sofkau.models.PostCrearUsuario;
import co.sofkau.models.ResponsePostCrearUsuario;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import co.sofkau.setup.ApiSetUp;
import net.serenitybdd.rest.SerenityRest;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.assertj.core.api.Assertions;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import static co.sofkau.questions.ReturnPostCrearUsuario.returnPostCrearUsuario;
import static co.sofkau.tasks.Post.postRegistroExitoso;
import static co.sofkau.utils.ReqresResources.*;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.apache.log4j.LogManager.getLogger;
import static org.hamcrest.CoreMatchers.equalTo;

public class PostCrearUsuarioStepDefinitions extends ApiSetUp {

    private static final Logger LOGGER = getLogger(PostCrearUsuarioStepDefinitions.class);
    JSONObject resBody = null;
    JSONParser parser = new JSONParser();

    private PostCrearUsuario usuario = new PostCrearUsuario();
    @Dado("que el usuario esta en la pagina de creacion")
    public void queElUsuarioEstaEnLaPaginaDeCreacion() {
        setUp(REQRES_BASE_URL.getValue());
    }
    @Cuando("el usuario envia la solicitud de registro con el nombre y el trabajo")
    public void elUsuarioEnviaLaSolicitudDeRegistroConElNombreYElTrabajo() {
        try {
            usuario = new PostCrearUsuario();
            usuario.setNombre("morpheus");
            usuario.setTrabajo("leader");
            actor.attemptsTo(
                    postRegistroExitoso()
                            .conElRecurso(CREATE_USER.getValue())
                            .conElCuerpo(usuario)
            );
          //  System.out.println(SerenityRest.lastResponse().body().asString());
        }catch (Exception exception) {
            LOGGER.error("Error enviando informacion" + exception.getMessage());
            Assertions.fail(exception.getMessage(), exception);
        }
    }
    @Entonces("el usuario visualiza un codigo de respuesta de estado {int} y un {string}, un {string} y un {int}")
    public void elUsuarioVisualizaUnCodigoDeRespuestaDeEstadoYUnUnYUn(Integer codigo, String nombre, String trabajo, Integer id) {
        try {
            ResponsePostCrearUsuario actualResponse = returnPostCrearUsuario().answeredBy(actor);
            actor.should(
                    seeThatResponse("El codigo de respuesta es: " + HttpStatus.SC_OK,
                            response -> response.statusCode(codigo)),
                    seeThat("El nombre es",
                            nomb -> actualResponse.getNombre(),equalTo(nombre)),
                    seeThat("El trabajo es",
                            trabaj -> actualResponse.getTrabajo(),equalTo(trabajo)),
                    seeThat("El id es",
                            ids -> actualResponse.getId(),equalTo(id))

            );
            LOGGER.info("Codigo de respuesta: " + codigo);
        } catch (Exception exception) {
            LOGGER.error("Error verificando el codigo de respuesta: " + exception.getMessage());
            Assertions.fail(exception.getMessage(), exception);
        }
    }

}
