package app;

import app.resource.HelloResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

public class App extends Application<AppConfig> {

    @Override
    public void run(AppConfig configuration, Environment environment) throws Exception {
        environment.jersey().register(new HelloResource());
    }

    public static void main(String[] args) throws Exception {
        new App().run(args);
    }
}
