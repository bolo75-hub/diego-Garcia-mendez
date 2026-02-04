import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingTest{
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(LoggingTest.class);
        logger.trace(null);
        logger.debug(null);
        logger.info("");
        logger.warn(null);
        logger.error(null);
    }
}
