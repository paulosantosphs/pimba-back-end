package pimba.domain.park;

import org.json.JSONArray;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pimba.domain.address.Address;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

/**
 * Created by paulo on 02/04/17.
 */
public class ParkServiceTest {
    @InjectMocks
    ParkService service;

    @Mock
    ParkRepository parkRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void queryTest() {
        String location1 = "Avenida Paulista e Brigadeiro Luiz Antonio - Avenida Paulista - Bela Vista, São Paulo - SP";
        String location2 = "R. Diogo de Vasconcelos, 122 - Pilar, Ouro Preto - MG, 35400-000";
        String location3 = "Av. Augusto de Lima, 1549 - Barro Preto, Belo Horizonte - MG, 30190-002";
        String location4 = "Área de Proteção Ambiental Carste de Lagoa Santa - Av. Paulo Ferreira da Costa, Lagoa Santa - MG, 33400-000";
        String query1 = service.query(location1);
        String query2 = service.query(location2);
        String query3 = service.query(location3);
        String query4 = service.query(location4);
        Assert.assertEquals(query1, "Avenida,Paulista,e,Brigadeiro,Luiz,Antonio,Avenida,Paulista,Bela,Vista,Sao,Paulo,SP");
        Assert.assertEquals(query2, "R,Diogo,de,Vasconcelos,122,Pilar,Ouro,Preto,MG,35400000");
        Assert.assertEquals(query3, "Av,Augusto,de,Lima,1549,Barro,Preto,Belo,Horizonte,MG,30190002");
        Assert.assertEquals(query4, "Area,de,Protecao,Ambiental,Carste,de,Lagoa,Santa,Av,Paulo,Ferreira,da,Costa,Lagoa,Santa,MG,33400000");
    }

    @Test
    public void coordinatesTest() {
        String query1 = "R,Diogo,de,Vasconcelos,122,Pilar,Ouro,Preto,MG,35400000";
        String query2 = "Av,Augusto,de,Lima,1549,Barro,Preto,Belo,Horizonte,MG,30190002";
        JSONArray c1 = service.getCoordinates(query1);
        JSONArray c2 = service.getCoordinates(query2);
        Assert.assertEquals(c1.toString(), "[-43.506921,-20.387719]");
        Assert.assertEquals(c2.toString(), "[-43.951053,-19.921531]");

    }

    @Test
    public void queryDistanceTest() {
        List<Park> parks = new ArrayList<>();
        Address address1 = new Address(-20.387719, -43.506921);
        Park park1 = new Park(address1, "Park1", "park1");
        Address address2 = new Address(-20.437719, -43.656921);
        Park park2 = new Park(address2, "Park2", "park2");
        Address address3 = new Address(-20.627719, -43.786921);
        Park park3 = new Park(address3, "Park3", "park3");
        parks.add(park1);
        parks.add(park2);
        parks.add(park3);
        Assert.assertEquals(service.createDestinationsQuery(parks), "-20.387719,-43.506921|-20.437719,-43.656921|-20.627719,-43.786921|");
    }

    @Test
    public void orderParksAndDistance() {
        List<Park> parks = new ArrayList<>();
        Address address1 = new Address(-20.387719, -43.506921);
        Park park1 = new Park(address1, "Park1", "park1");
        Address address2 = new Address(-20.437719, -43.656921);
        Park park2 = new Park(address2, "Park2", "park2");
        Address address3 = new Address(-20.627719, -43.786921);
        Park park3 = new Park(address3, "Park3", "park3");
        parks.add(park1);
        parks.add(park2);
        parks.add(park3);


        Double pointLatitude = -20.487719;
        Double pointLongitude = -43.606921;

        double radius = 10;
        double latitudeRadius = service.latitudeRadius(radius);
        double longitudeRadius = service.longitudeRadius(radius);

        when(parkRepository.getListParkByLocation(pointLatitude, pointLongitude, latitudeRadius, longitudeRadius)).thenReturn(parks);
        ParkResponse parkResponse = service.getParks(pointLatitude, pointLongitude, radius);
        List<ParkDistance> distances = parkResponse.getParkDistances();
        Park parkDistance1 = distances.get(0).getPark();
        Park parkDistance2 = distances.get(1).getPark();
        Park parkDistance3 = distances.get(2).getPark();

        Assert.assertEquals(park1, parkDistance1);
        Assert.assertEquals(park2, parkDistance2);
        Assert.assertEquals(park3, parkDistance3);

        Integer time1 = distances.get(0).getTime();
        Integer time2 = distances.get(1).getTime();
        Integer time3 = distances.get(2).getTime();

        Assert.assertEquals(time1.intValue(), 1730);
        Assert.assertEquals(time2.intValue(), 3462);
        Assert.assertEquals(time3.intValue(), 2293);

        Integer distance1 = distances.get(0).getDistance();
        Integer distance2 = distances.get(1).getDistance();
        Integer distance3 = distances.get(2).getDistance();

        Assert.assertEquals(distance1.intValue(), 20577);
        Assert.assertEquals(distance2.intValue(), 14182);
        Assert.assertEquals(distance3.intValue(), 29846);
    }

}
