package br.com.leraning.restless.resources;

import br.com.leraning.restless.domain.Price;
import br.com.leraning.restless.domain.Research;
import br.com.leraning.restless.domain.Room;
import br.com.leraning.restless.dto.ResearchNewDTO;
import br.com.leraning.restless.services.PriceService;
import br.com.leraning.restless.services.ResearchService;
import br.com.leraning.restless.services.RoomService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.oracle.javafx.jmx.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.lang.reflect.Type;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author macrusal on 04/08/19
 * @project restless
 */
@RestController
@RequestMapping(value="/restless/researchs")
public class ResearchResource {

    @Autowired
    private PriceService priceService;

    @Autowired
    private RoomService roomService;

    @Autowired
    private ResearchService researchService;

    @RequestMapping(value="{id}",method=RequestMethod.GET)
    public ResponseEntity<Research> find(@PathVariable final Integer id) {

        Research research = researchService.find(id);
        return ResponseEntity.ok().body(research);
    }

    @RequestMapping(method=RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody ResearchNewDTO researchDTO) {
        Research research = researchService.fromDTO(researchDTO);
        research = researchService.insert(research);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(research.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value="/{id}",method=RequestMethod.PUT)
    public ResponseEntity<Void> update(@RequestBody ResearchNewDTO researchDTO, @PathVariable final Integer id) {
        Research research = researchService.fromDTO(researchDTO);
        research.setId(id);
        research = researchService.update(research);

        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value="/{id}",method=RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable final Integer id) {
        researchService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/research", method = RequestMethod.GET)
    public String researchTravelPrice(@RequestParam(value="city") Integer city,
                                      @RequestParam(value="checkin") String checkin,
                                      @RequestParam(value="checkout") String checkout,
                                      @RequestParam(value="children") String children,
                                      @RequestParam(value="adults") String adults) throws JSONException {

        //https://cvcbackendhotel.herokuapp.com/hotels/avail/1032

        Long amountHostingDays = researchService.getAmountHostingDays(checkin, checkout);

        RestTemplate template = new RestTemplate();
        UriComponents uri = UriComponentsBuilder.newInstance()
                .scheme("https")
                .host("cvcbackendhotel.herokuapp.com/")
                .path("hotels/avail/" + city.toString())
                .build();

        ResponseEntity<Research[]> entity = template.getForEntity(uri.toUriString(), Research[].class);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Type type = new TypeToken<List<Research>>(){}.getType();
        List<Research> researchList = new ArrayList<>();

        for (Research research : Arrays.asList(entity.getBody())) {
            research.setRooms(researchService.getValuesHostingDays(research, amountHostingDays, adults, children));
            researchList.add(research);
        }
        String json = gson.toJson(researchList, type);
        List<Research> list = gson.fromJson(json, type);

        for (Research reserach : list) {
            for(Room room : reserach.getRooms()) {
                Price price = room.getPrice();
                priceService.insert(price);

                roomService.insert(room);
            }
            researchService.insert(reserach);
        }

        return "Olha no banco e veja se funcionou!";//gson.toJson(researchList, type);
    }
}
