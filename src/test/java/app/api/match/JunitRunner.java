package app.api.match;

import io.karatelabs.junit6.Karate;
import org.junit.jupiter.api.DynamicNode;

class JunitRunner {

    @Karate.Test
    Iterable<DynamicNode> testDemo() {
        return Karate.run("match.feature").relativeTo(getClass());
    }

}
