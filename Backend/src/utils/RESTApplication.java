package utils;

import spark.Spark;

import java.lang.reflect.Constructor;

/**
 * Created by Antonio Zaitoun on 13/07/2018.
 */
public abstract class RESTApplication {

    void start(int port){

        Spark.port(port);

        init();
    }

    public abstract void init();

    protected void post(String route, RESTRoute controller){
        Spark.post(route, "application/json", controller,new JSONTransformer());
    }

    protected void get(String route, RESTRoute controller){
        Spark.get(route, "application/json", controller,new JSONTransformer());
    }

    public static void launch(int port){

        StackTraceElement[] cause = Thread.currentThread().getStackTrace();

        boolean foundThisMethod = false;
        String callingClassName = null;
        for (StackTraceElement se : cause) {
            // Skip entries until we get to the entry for this class
            String className = se.getClassName();
            String methodName = se.getMethodName();
            if (foundThisMethod) {
                callingClassName = className;
                break;
            } else if (RESTApplication.class.getName().equals(className)
                    && "launch".equals(methodName)) {

                foundThisMethod = true;
            }
        }

        if (callingClassName == null) {
            throw new RuntimeException("Error: unable to determine Application class");
        }

        try {
            Class theClass = Class.forName(callingClassName, false,
                    Thread.currentThread().getContextClassLoader());
            if (RESTApplication.class.isAssignableFrom(theClass)) {
                Class<? extends RESTApplication> appClass = theClass;
                Constructor<?> ctor = appClass.getConstructor();
                Object object = ctor.newInstance();
                ((RESTApplication) object).start(port);
            } else {
                throw new RuntimeException("Error: " + theClass
                        + " is not a subclass of javafx.application.Application");
            }
        } catch (RuntimeException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

    }
}
