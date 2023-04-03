package co.sofkau.stepdefinitions;
import co.sofkau.models.ResponseGetListar;
import co.sofkau.models.ResponsePostCrearUsuario;
import co.sofkau.setup.ApiSetUp;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import net.serenitybdd.rest.SerenityRest;
import org.apache.hc.core5.http.HttpStatus;
import org.apache.log4j.Logger;
import org.assertj.core.api.Assertions;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import static co.sofkau.questions.ReturnGetListar.returnGetListar;
import static co.sofkau.questions.ReturnPostCrearUsuario.returnPostCrearUsuario;
import static co.sofkau.tasks.Get.getListarUsuarios;
import static co.sofkau.utils.ReqresResources.*;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.apache.log4j.LogManager.getLogger;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;


public class GetListarUsuariosStepDefinitions extends ApiSetUp {

    private static final Logger LOGGER = getLogger(GetListarUsuariosStepDefinitions.class);

    JSONObject resBody = null;
    JSONArray resArray = null;

    @Dado("que el usuario esta en la pagina principal")
    public void queElUsuarioEstaEnLaPaginaPrincipal() {
          setUp(REQRES_BASE_URL.getValue());
    }
    @Cuando("el usuario envia la solicitud")
    public void elUsuarioEnviaLaSolicitud() {
        try {
            actor.attemptsTo(
                    getListarUsuarios().withTheResource(LIST_USERS.getValue())
            );
        } catch (Exception exception) {
            LOGGER.error("Error enviando informacion" + exception.getMessage());
            Assertions.fail(exception.getMessage(), exception);
        }

    }
    @Entonces("el usuario visualiza el codigo {int}")
    public void elUsuarioVisualizaElCodigo(Integer estado) {
        try {
            ResponseGetListar actualResponse = returnGetListar().answeredBy(actor);
            actor.should(
                    seeThatResponse("El codigo de respuesta es: " + HttpStatus.SC_OK,
                            response -> response.statusCode(estado)),
                    seeThat("Correo",
                            response -> actualResponse.getDatos().toString(),containsString("ABCDE"))
            );
            System.out.println(actualResponse.getDatos().toString());
            LOGGER.info("Codigo de respuesta: " + estado);
        } catch (Exception exception) {
            LOGGER.error("Error verificando el codigo de respuesta: " + exception.getMessage());
            Assertions.fail(exception.getMessage(), exception);
        }
    }



}
