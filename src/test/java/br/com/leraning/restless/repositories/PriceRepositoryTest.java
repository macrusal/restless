package br.com.leraning.restless.repositories;

import br.com.leraning.restless.domain.Price;
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
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author macrusal on 04/08/19
 * @project restless
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class PriceRepositoryTest {

    @Autowired
    private PriceRepository priceRepository;

    List<Price> prices = null;

    @Before
    public void setUp() throws Exception {
        prices = new ArrayList<>();
    }

    @After
    public void tearDown() throws Exception {
        prices.clear();
    }

    Comparator<Price> comparatorDouble = new Comparator<Price>() {
        @Override
        public int compare(Price preco1, Price preco2) {
            return (new Double(preco1.getAdult()).compareTo(new Double(preco2.getAdult())));
        }
    };

    @Test
    public void test_obter_todos_os_precos(){
        prices = this.priceRepository.findAll();
        Assert.assertTrue(prices.size() > 0);
    }

    @Test
    public void test_obter_todos_os_precos_e_ordenar_os_valores_em_ordem_decrescente_V1(){
        prices = this.priceRepository.findAll();

        Comparator<Price> comparator = new Comparator<Price>() {
            @Override
            public int compare(Price preco1, Price preco2) {
                return (int) (preco2.getAdult() - preco1.getAdult());
            }
        };

        prices.sort(comparator);

        this.imprimirListaDePrecosOrdenada(prices);

        //list.stream().sorted().collect(Collectors.toList()).equals(list);
        Assert.assertTrue(prices.size() > 0);
    }

    @Test
    public void test_obter_todos_os_precos_e_ordenar_os_valores_em_ordem_decrescente_V2(){
        prices = this.priceRepository.findAll();

        Comparator<Price> comparator = (Price preco1, Price preco2) -> {
            return (int) (preco2.getAdult() - preco1.getAdult());
        };

        prices.sort(comparator);

        this.imprimirListaDePrecosOrdenada(prices);

        Assert.assertTrue(prices.size() > 0);
    }

    @Test
    public void test_obter_todos_os_precos_e_ordenar_os_valores_em_ordem_decrescente_V3(){
        prices = this.priceRepository.findAll();

        Comparator<Price> comparator = (preco1, preco2) -> (int) (preco2.getAdult() - preco1.getAdult());

        prices.sort(comparator);

        this.imprimirListaDePrecosOrdenada(prices);

        Assert.assertTrue(prices.size() > 0);
    }

    @Test
    public void test_obter_todos_os_precos_e_ordenar_os_valores_em_ordem_decrescente_V4(){
        prices = this.priceRepository.findAll();

        prices.sort((preco1, preco2) -> (int) (preco2.getAdult() - preco1.getAdult()));

        this.imprimirListaDePrecosOrdenada(prices);

        Assert.assertTrue(prices.size() > 0);
    }

    @Test
    public void test_obter_todos_os_precos_e_ordenar_os_valores_em_ordem_decrescente_V5(){
        prices = this.priceRepository.findAll();

        prices.sort((preco1, preco2) -> (new Double(preco1.getAdult()).compareTo(new Double(preco2.getAdult()))));

        this.imprimirListaDePrecosOrdenada(prices);

        Assert.assertTrue(prices.size() > 0);
    }

    @Test
    public void test_obter_todos_os_precos_e_ordenar_os_valores_em_ordem_decrescente_V6(){
        prices = this.priceRepository.findAll();

        prices.sort(comparatorDouble);

        this.imprimirListaDePrecosOrdenada(prices);

        Assert.assertTrue(prices.size() > 0);
    }

    /**
     * Uso de Consumer para fazer a impressao da lista de precos
     * @param prices
     */
    private void imprimirListaDePrecosOrdenada(List<Price> prices) {
        prices.forEach(System.out::println);
    }
}
