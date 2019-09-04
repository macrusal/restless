package br.com.leraning.restless.util;

import br.com.leraning.restless.domain.Price;

import java.util.Comparator;

/**
 * @author macrusal on 06/08/19
 * @project restless
 */
public class PriceComparator implements Comparator<Price> {

    @Override
    public int compare(Price price1, Price price2) {
        return (int) (price2.getAdult() - price1.getAdult());
    }
}
