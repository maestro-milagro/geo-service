package ru.netology.sender;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoServiceImpl;
import org.mockito.Mockito;
import ru.netology.i18n.LocalizationServiceImpl;
import ru.netology.sender.MessageSenderImpl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MessageSenderImplTest {
    @Test
    public void sendForRussiaTest() {
        String expected = "Добро пожаловать";
        GeoServiceImpl geoService = Mockito.mock(GeoServiceImpl.class);
        Mockito.when(geoService.byIp(Mockito.any())).thenReturn(new Location("Moscow", Country.RUSSIA, null, 0));
        LocalizationServiceImpl localizationService = Mockito.mock(LocalizationServiceImpl.class);
        Mockito.when(localizationService.locale(Mockito.any())).thenReturn("Добро пожаловать");
        Map<String, String> headers = new HashMap<>();
        MessageSenderImpl messageSender = new MessageSenderImpl(geoService, localizationService);
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "172.1.42.12");

        String result = messageSender.send(headers);

        Assertions.assertEquals(expected, result);
    }

    @Test
    public void sendForOthersTest() {
        String expected = "Welcome";
        GeoServiceImpl geoService = Mockito.mock(GeoServiceImpl.class);
        Mockito.when(geoService.byIp(Mockito.any())).thenReturn(new Location("New York", Country.USA, null, 0));
        LocalizationServiceImpl localizationService = Mockito.mock(LocalizationServiceImpl.class);
        Mockito.when(localizationService.locale(Mockito.any())).thenReturn("Welcome");
        Map<String, String> headers = new HashMap<>();
        MessageSenderImpl messageSender = new MessageSenderImpl(geoService, localizationService);
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "96.45.225.147");

        String result = messageSender.send(headers);

        Assertions.assertEquals(expected, result);
    }
}


