package pl.mroziqella.impl

import org.junit.Assert
import org.junit.Test
import pl.mroziqella.inte.SharingPicture


/**
 * Created by Kamil on 25/03/2016.
 */

class ProviderTest extends Provider {

    ProviderTest() {
        super("127.0.0.1", "server", 2000);
    }
    @Test
    public void testConnected(){
        ProviderTest providerTest = new ProviderTest();
        providerTest.connect();
        SharingPicture sharingPicture = providerTest.getRmi();
        Assert.assertEquals("Test",sharingPicture.getTest());
    }

}
