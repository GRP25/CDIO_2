package rest;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * Herfra udgår alle andre paths, da klassen extender Application.
 * */

@ApplicationPath("/rest")
public class ApplicationConfig extends Application {
}

