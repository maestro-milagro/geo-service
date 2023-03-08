import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoServiceImpl;

import java.util.stream.Stream;

public class GeoServiceImplTest {
    @ParameterizedTest
    @MethodSource("byIpParameters")
    public void byIpTest(String ip, Location expected) {
        GeoServiceImpl geoService = new GeoServiceImpl();
        Location result = geoService.byIp(ip);

        Assertions.assertEquals(expected, result);

    }

    public static Stream<Arguments> byIpParameters() {
        return Stream.of(Arguments.of("127.0.0.1", new Location(null, null, null, 0)),
                Arguments.of("172.0.32.11", new Location("Moscow", Country.RUSSIA, "Lenina", 15)),
                Arguments.of("96.44.183.149", new Location("New York", Country.USA, " 10th Avenue", 32)),
                Arguments.of("172.1.42.12", new Location("Moscow", Country.RUSSIA, null, 0)),
                Arguments.of("96.45.225.147", new Location("New York", Country.USA, null, 0)),
                Arguments.of("23.43.45.213", null));
    }

    @Test
    public void byCoordinates() {
        GeoServiceImpl geoService = new GeoServiceImpl();
        RuntimeException thrown = Assertions.assertThrows(RuntimeException.class, () -> {
                    geoService.byCoordinates(21.2, 2.3);
                }
                , "RuntimeException was expected");
        Assertions.assertEquals("Not implemented", thrown.getMessage());
    }
}
