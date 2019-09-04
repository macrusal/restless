package br.com.leraning.restless.repositories;

import br.com.leraning.restless.domain.Room;
import br.com.leraning.restless.util.RoomPredicate;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * @author macrusal on 06/08/19
 * @project restless
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class RoomRepositoryTest {


    @Autowired
    private RoomRepository roomRepository;

    public static final Double FATOR_ATUALIZACAO_ADULTO = 1.1;
    List<Room> rooms = null;

    @Before
    public void setUp() throws Exception {
        rooms = new ArrayList<>();
    }

    @After
    public void tearDown() throws Exception {
        rooms.clear();
    }

    Consumer<Room> consumer = new Consumer<Room>() {
        @Override
        public void accept(Room room) {
            room.getPrice().setAdult(room.getPrice().getAdult() * FATOR_ATUALIZACAO_ADULTO);
        }
    };

    @Test
    public void testObterTodosOsQuartos() {
        List<Room> roomList = this.roomRepository.findAll();

        this.imprimirListaDeQuartos(roomList);

        Assert.assertTrue(!roomList.isEmpty());
    }

    @Test
    public void test_Remover_Todos_Os_Quartos_Com_Precos_Menor_Que_200() {
        List<Room> roomList = this.roomRepository.findAll();

        roomList.removeIf(room -> room.getPrice().getAdult() <= 200.0);

        this.imprimirListaDeQuartos(roomList);

        Assert.assertTrue(!roomList.isEmpty());
    }

    @Test
    public void test_Use_RoomPredicate_Para_Remover_Todos_Os_Quartos_Com_Precos_Menor_Que_200() {
        List<Room> roomList = this.roomRepository.findAll();

        roomList.removeIf(new RoomPredicate());

        this.imprimirListaDeQuartos(roomList);

        Assert.assertTrue(!roomList.isEmpty());
    }

    @Test
    public void test_Use_Predicate_Da_Classe_Room_Para_Obter_Todos_Os_Quartos_Com_Precos_Menor_Que_200() {
        List<Room> roomList = this.roomRepository.findAll();

        roomList.removeIf(Room::obterQuartosAdultosComValoresMenor);

        this.imprimirListaDeQuartos(roomList);

        Assert.assertTrue(!roomList.isEmpty());
    }

    @Test
    public void test_Use_Variavel_Predicate_Para_Obter_Todos_Os_Quartos_Com_Precos_Menor_Que_200() {
        List<Room> roomList = this.roomRepository.findAll();

        Predicate<Room> predicate = room -> room.getPrice().getAdult() <= 200.0;

        roomList.removeIf(predicate);

        this.imprimirListaDeQuartos(roomList);

        Assert.assertTrue(!roomList.isEmpty());
    }

    @Test
    public void test_Expressao_Lambda_In_Line_Para_Obter_Todos_Os_Quartos_Com_Precos_Menor_Que_200() {
        List<Room> roomList = this.roomRepository.findAll();

        roomList.removeIf(room -> room.getPrice().getAdult() <= 200.0);

        this.imprimirListaDeQuartos(roomList);

        Assert.assertTrue(!roomList.isEmpty());
    }

    @Test
    public void test_Atualiza_Precos_De_Hospedagem_De_Acordo_Com_O_Fator_Recebido() {
        List<Room> roomList = this.roomRepository.findAll();

        roomList.forEach(consumer);

        this.imprimirListaDeQuartos(roomList);

        Assert.assertTrue(!roomList.isEmpty());
    }

    /**
     * Imprimir a lista de quartos usando Consumer<T></T>
     * @param roomList
     */
    private void imprimirListaDeQuartos(List<Room> roomList) {
        roomList.forEach(System.out::println);
    }
}
