package controllers;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * API controller used to call all Kweet model related calls.
 * Uses Service to communicate with the data storage option.
 * @author Arnoud Bevers
 * @project kwetter
 */
@ApplicationPath("api")
public class JaxRSApplication extends Application {

}
