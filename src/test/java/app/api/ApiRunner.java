package app.api;

import com.intuit.karate.Results;
import com.intuit.karate.Runner;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 *
 * @author peter
 */
class ApiRunner {

    @Test
    void testApi() {
        Results results = Runner.path("classpath:app/api").parallel(1);
        assertEquals(0, results.getFailCount(), results.getErrorMessages());
    }

}
