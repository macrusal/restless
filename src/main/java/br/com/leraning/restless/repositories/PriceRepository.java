package br.com.leraning.restless.repositories;

import br.com.leraning.restless.domain.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author macrusal on 04/08/19
 * @project restless
 */
@Repository
public interface PriceRepository extends JpaRepository<Price, Integer> {
}
