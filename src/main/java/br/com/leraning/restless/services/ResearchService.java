package br.com.leraning.restless.services;

import br.com.leraning.restless.domain.Research;
import br.com.leraning.restless.domain.Room;
import br.com.leraning.restless.dto.ResearchNewDTO;
import br.com.leraning.restless.repositories.ResearchRepository;
import br.com.leraning.restless.services.exceptions.DataIntegrityException;
import br.com.leraning.restless.services.exceptions.ResearchNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

/**
 * @author macrusal on 04/08/19
 * @project restless
 */
@Service
public class ResearchService {

    private static final String DATE_PATTERN_DDMMYYYY = "dd/MM/yyyy";

    private static final Double CALCULATION_FACTOR = 0.7;

    @Autowired
    ResearchRepository researchRepository;

    public Research find(final Integer id) {

        Optional<Research> research = researchRepository.findById(id);

        return research.orElseThrow(() -> new ResearchNotFoundException(
                "Quarto não encontrado! Id: " + id + ", Tipo: " + Research.class.getName()));
    }

    /**
     * @param research
     * @return
     */
    public Research insert(Research research) {
        research.setId(null);
        research = researchRepository.save(research);
        return research;
    }

    /**
     * @param research
     * @return
     */
    public Research update(Research research) {
        Research researchNovo = find(research.getId());
        updateData(researchNovo, research);
        return researchRepository.save(researchNovo);
    }

    /**
     * @param id
     * @return
     */
    public void delete(Integer id) {
        find(id);
        try {
            researchRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir um Preço que possui associações!");
        }
    }

    private void updateData(Research researchNovo, Research research) {
        researchNovo.setId(research.getId());
        researchNovo.setCityCode(research.getCityCode());
        researchNovo.setCityName(research.getCityName());
        researchNovo.setRooms(research.getRooms());
        researchNovo.setName(research.getName());
    }

    public Research fromDTO(ResearchNewDTO researchDTO) {
        Research research = new Research();
        research.setName(researchDTO.getName());
//        researchDTO.getRoomsNewDTO()
//        research.setRooms();

        research.setCityName(researchDTO.getCityName());
        research.setCityCode(researchDTO.getCityCode());
        research.setId(researchDTO.getId());
        return research;
    }

    public List<Room> getValuesHostingDays(Research researchResponse, Long quantity, String adults, String children) {

        List<Room> roomList = new ArrayList<>();

        double childrenValue = 0;
        for(Room room : researchResponse.getRooms()) {

            double adultsValue = ((room.getPrice().getAdult() * quantity * Integer.parseInt(adults))/CALCULATION_FACTOR);
            room.getPrice().setAdult(formattValueWithoutSign(adultsValue));

            if(!children.isEmpty() && Integer.parseInt(children) > 0) {
                childrenValue = ((room.getPrice().getChild() * quantity * Integer.parseInt(children))/CALCULATION_FACTOR);
                room.getPrice().setChild(formattValueWithoutSign(childrenValue));
            } else {
                room.getPrice().setChild(formattValueWithoutSign(0.0));
            }

            if(!children.isEmpty() && Integer.parseInt(children) > 0) {
                room.setTotalPrice(formattValueWithoutSign(adultsValue + childrenValue));
            } else {
                room.setTotalPrice(formattValueWithoutSign(adultsValue));
            }
            roomList.add(room);
        }
        return roomList;
    }

    private Double formattValueWithoutSign(double value) {

        DecimalFormat formatter = (DecimalFormat) NumberFormat.getCurrencyInstance(Locale.US);
        DecimalFormatSymbols symbols = formatter.getDecimalFormatSymbols();
        symbols.setCurrencySymbol(""); // Don't use null.
        formatter.setDecimalFormatSymbols(symbols);

        return Double.parseDouble(formatter.format(value).replace(",", ""));
    }

    public Long getAmountHostingDays(@RequestParam(value = "checkin", required = false) String checkin,
                                     @RequestParam(value = "checkout", required = false) String checkout) {
        LocalDate localDateIn = null;
        LocalDate localDateOut = null;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN_DDMMYYYY);

        localDateIn = LocalDate.parse(checkin, formatter);
        localDateOut = LocalDate.parse(checkout, formatter);

        return ChronoUnit.DAYS.between(localDateIn, localDateOut);
    }
}
