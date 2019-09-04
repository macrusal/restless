package br.com.leraning.restless.repositories;

import br.com.leraning.restless.domain.Research;
import org.hamcrest.CoreMatchers;
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

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author macrusal on 06/08/19
 * @project restless
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class ResearchRepositoryTest {

    @Autowired
    private ResearchRepository researchRepository;

    List<Research> researchs = null;

    @Before
    public void setUp() throws Exception {
        researchs = new ArrayList<>();
    }

    @After
    public void tearDown() throws Exception {
        researchs.clear();
    }

    @Test
    public void obterTodasAsPesquisas() {
        List<Research> researchList = this.researchRepository.findAll();
        researchList.removeIf(research -> research.getRooms() == null);
//        researchList.forEach(System.out::println);
        assertThat(researchList.isEmpty(), is(false));
    }
}
