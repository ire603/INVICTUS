package victus.table;

import pl.zankowski.iextrading4j.client.*;

public class IEX {
    private static IEXApiClient iexTradingClient;
    public IEX() {

    }
    public static IEXCloudClient initiateIEX() {
        final IEXCloudClient cloudClient = IEXTradingClient.create( IEXTradingApiVersion.IEX_CLOUD_BETA_SANDBOX,
                new IEXCloudTokenBuilder()
                        .withPublishableToken("Tpk_18dfe6cebb4f41ffb219b9680f9acaf2")
                        .withSecretToken("Tsk_3eedff6f5c284e1a8b9bc16c54dd1af3")
                        .build());
    
    
        return cloudClient;
    }

}
