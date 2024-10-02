package cucumber.Options;

import org.junit.runner.RunWith;
//import org.junit.jupiter.api.Test;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;


@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/java/features",plugin="json:target/jsonReports/cucumber-report.json",glue= {"stepDefinations"},stepNotifications=true)
public class TestRunner {
//,tags= "@DeletePlace"
}
