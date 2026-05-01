import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MercadoriaTest {

    @Test
    void mercadoriaDeveSerCriada(){
        RequestMercadoriaDTO request = new ResponseMercadoriaDTO("Fogão", "Fogão de 4 bocas", 275.04);
        
        ReponseMercadoriaDTO mercadoria = mercadoriaService.criarNovaMercadoria(request);

        assertEquals(request.nome(), mercadoria.nome());
        assertTrue(mercadoriaService.findMercadoriaByID(mercadoria.id()).isPresent());

    }

}
