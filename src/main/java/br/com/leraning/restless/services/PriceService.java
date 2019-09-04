package br.com.leraning.restless.services;

import br.com.leraning.restless.domain.Price;
import br.com.leraning.restless.dto.PriceDTO;
import br.com.leraning.restless.dto.PriceNewDTO;
import br.com.leraning.restless.repositories.PriceRepository;
import br.com.leraning.restless.services.exceptions.DataIntegrityException;
import br.com.leraning.restless.services.exceptions.PriceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author macrusal on 04/08/19
 * @project restless
 */
@Service
public class PriceService {

    @Autowired
    PriceRepository priceRepository;

    public Price find(final Integer id) {

        Optional<Price> price = priceRepository.findById(id);

        return price.orElseThrow(() -> new PriceNotFoundException(
                "Preço não encontrado! Id: " + id + ", Tipo: " + Price.class.getName()));
    }

    /**
     * @param price
     * @return
     */
    public Price insert(Price price) {
        price.setPriceID(null);
        price = priceRepository.save(price);
        return price;
    }

    /**
     * @param price
     * @return
     */
    public Price update(Price price) {
        Price priceNovo = find(price.getPriceID());
        updateData(priceNovo, price);
        return priceRepository.save(priceNovo);
    }

    /**
     * @param id
     * @return
     */
    public void delete(Integer id) {
        find(id);
        try {
            priceRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir um Preço que possui associações!");
        }
    }

    private void updateData(Price priceNovo, Price price) {
        priceNovo.setChild(price.getChild());
        priceNovo.setAdult(price.getAdult());
    }

    public Price fromDTO(PriceNewDTO priceDTO) {
        Price price = new Price();
        price.setAdult(priceDTO.getAdult());
        price.setChild(priceDTO.getChild());
        price.setPriceID(priceDTO.getPriceID());
        return price;
    }
}
