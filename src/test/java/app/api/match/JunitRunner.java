package app.api.match;

import com.intuit.karate.junit5.Karate;

class JunitRunner {

    @Karate.Test
    Karate testDemo() {
        return Karate.run("match.feature").relativeTo(getClass());
    }

}
