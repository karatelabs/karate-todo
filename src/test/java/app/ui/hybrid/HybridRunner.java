package app.ui.hybrid;

import com.intuit.karate.Results;
import com.intuit.karate.Runner;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class HybridRunner {

    @Test
    void testApi() {
        Results results = Runner.path("classpath:app/ui/hybrid").parallel(1);
        assertEquals(0, results.getFailCount(), results.getErrorMessages());
    }

}
