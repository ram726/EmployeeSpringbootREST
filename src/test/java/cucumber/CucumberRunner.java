package cucumber;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = {"src/test/resources/features"},
        plugin = {"pretty","json:CucumberReport"},
      glue = {"cucumber"})
public class CucumberRunner
{
    public CucumberRunner() {
    }
}
