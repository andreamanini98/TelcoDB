package it.polimi.telcodb.services;

import it.polimi.telcodb.entities.OptionalProduct;
import it.polimi.telcodb.entities.ValidityPeriod;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ConfirmationPageService {

    public BigDecimal computeTotalCost(ValidityPeriod validityPeriod, List<OptionalProduct> optionalProductList) {
        BigDecimal result = validityPeriod.getMonthlyFee().multiply(BigDecimal.valueOf(validityPeriod.getNumberOfMonths()));
        if (optionalProductList != null) {
            for (OptionalProduct o : optionalProductList)
                result = result.add(o.getMonthlyFee().multiply(BigDecimal.valueOf(validityPeriod.getNumberOfMonths())));
        }
        return result;
    }

}
