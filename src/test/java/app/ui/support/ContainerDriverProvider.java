package app.ui.support;

import io.karatelabs.driver.Driver;
import io.karatelabs.driver.PooledDriverProvider;
import io.karatelabs.driver.cdp.CdpDriver;
import io.karatelabs.driver.cdp.CdpDriverOptions;

import java.util.Map;

public class ContainerDriverProvider extends PooledDriverProvider {

    private final ChromeContainer container;

    public ContainerDriverProvider(ChromeContainer container) {
        super();
        this.container = container;
    }

    @Override
    protected Driver createDriver(Map<String, Object> config) {
        String wsUrl = container.getCdpUrl();
        CdpDriverOptions options = CdpDriverOptions.fromMap(config);
        return CdpDriver.connect(wsUrl, options);
    }

}
