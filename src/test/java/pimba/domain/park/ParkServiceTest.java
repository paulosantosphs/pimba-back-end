package pimba.domain.park;

import org.json.JSONArray;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

/**
 * Created by paulo on 02/04/17.
 */
public class ParkServiceTest {
    @InjectMocks
    ParkService service;

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

}
