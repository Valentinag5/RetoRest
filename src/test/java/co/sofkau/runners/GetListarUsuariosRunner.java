package co.sofkau.runners;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        features = {"src/test/resources/features/GetListarUsuarios.feature"},
        glue = {"co.sofkau.stepdefinitions"},
        tags = ""

)
public class GetListarUsuariosRunner {
}
