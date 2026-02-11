
import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;

        /* ALL: Muestra absolutamente todos los mensajes,TRACE, DEBUG, INFO, WARN y ERROR. */

        /* TRACE: Muestra: TRACE, DEBUG, INFO, WARN y ERROR. Se usa para un seguir muy
        detalladamente el programa. */

        /* DEBUG: Muestra: DEBUG, INFO, WARN y ERROR. NO muestra TRACE. Se usa para depurar
         errores mientras desarrollas. */

        /* INFO: Muestra: INFO, WARN y ERROR. NO muestra DEBUG ni TRACE. Sirve para información
         general del programa.*/

        /* WARN: Muestra: WARN y ERROR. NO muestra INFO, DEBUG ni TRACE. Solo avisos importantes
         y errores.*/

        /* ERROR: Solo muestra ERROR. Solo errores graves.*/

        /* OFF: No muestra absolutamente nada. */

public class LoggingTest{
    int nostaticMember1;
    static Logger logger = (Logger) LoggerFactory.getLogger(LoggingTest.class);

    public static void main(String[] args) {
        logger.setLevel(Level.TRACE);
        division(23,0);



    }

    private static void division(int i, int j) {
        logger.trace("trace log: you have entered the method 'division'");
        logger.debug("debug log: we use this level of log to debug our program");
        logger.info("info log: this is used to show normal information that happens in our program");
        logger.warn("warn log: used to show potential");
        logger.error("error log: used for login problems and error that concern");
        try {
            int result = 1/j;
            logger.trace("result = " + result);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
}
