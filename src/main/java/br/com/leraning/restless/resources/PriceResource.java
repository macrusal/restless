package br.com.leraning.restless.resources;

import br.com.leraning.restless.domain.Price;
import br.com.leraning.restless.dto.PriceNewDTO;
import br.com.leraning.restless.services.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

/**
 * @author macrusal on 04/08/19
 * @project restless
 */
@RestController
@RequestMapping(value="/restless/prices")
public class PriceResource {

    @Autowired
    private PriceService priceService;

    @RequestMapping(value="{id}",method=RequestMethod.GET)
    public ResponseEntity<Price> find(@PathVariable final Integer id) {

        Price price = priceService.find(id);
        return ResponseEntity.ok().body(price);
    }

    @RequestMapping(method=RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody PriceNewDTO priceDTO) {
        Price price = priceService.fromDTO(priceDTO);
        price = priceService.insert(price);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(price.getPriceID()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value="/{id}",method=RequestMethod.PUT)
    public ResponseEntity<Void> update(@RequestBody PriceNewDTO priceDTO, @PathVariable final Integer id) {
        Price price = priceService.fromDTO(priceDTO);
        price.setPriceID(id);
        price = priceService.update(price);

        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value="/{id}",method=RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable final Integer id) {
        priceService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
