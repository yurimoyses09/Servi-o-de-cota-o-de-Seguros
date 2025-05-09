package com.acme.insurance.utils;

import com.acme.insurance.config.RulesException;
import com.acme.insurance.dtos.CustomerRequest;
import com.acme.insurance.dtos.InsuranceQuoteRequest;
import com.acme.insurance.dtos.OfferResponse;
import com.acme.insurance.dtos.ProductResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class InsuranceQuoteValidator {

    @Autowired
    private final Utils utils;

    public InsuranceQuoteValidator(Utils utils) {
        this.utils = utils;
    }

    public void Validate(InsuranceQuoteRequest request, OfferResponse offer, ProductResponse product) {
        validateExistenceAndStatus(offer, product);
        validateCoverages(request, offer);
        validateAssistances(request, offer);
        validatePremiumAmount(request, offer);
        validateCoverageTotal(request, offer);
        validateCustomer(request.getCustomer());
    }

    private void validateExistenceAndStatus(OfferResponse offer, ProductResponse product) {
        if (offer == null || product == null || !offer.isActive() || !product.isActive()) {
            throw new RulesException("A oferta ou produto não existem ou não estão ativos.");
        }
    }

    private void validateCoverages(InsuranceQuoteRequest request, OfferResponse offer) {
        for (String coverage : request.getCoverages().keySet()) {
            if (!offer.getCoverages().containsKey(coverage)) {
                throw new RulesException("Cobertura informada '%s' não existe na oferta.".formatted(coverage));
            }
        }
    }

    private void validateAssistances(InsuranceQuoteRequest request, OfferResponse offer) {
        for (String assistance : request.getAssistances()) {
            if (!offer.getAssistances().contains(assistance)) {
                throw new RulesException("Assistência '%s' não está disponível na oferta.".formatted(assistance));
            }
        }
    }

    private void validatePremiumAmount(InsuranceQuoteRequest request, OfferResponse offer) {
        BigDecimal total = request.getTotal_monthly_premium_amount();
        BigDecimal min = offer.getMonthly_premium_amount().getMin_amount();
        BigDecimal max = offer.getMonthly_premium_amount().getMax_amount();

        if (total.compareTo(min) < 0 || total.compareTo(max) > 0) {
            throw new RulesException("O prêmio mensal está fora dos limites permitidos (%.2f - %.2f)."
                    .formatted(min, max));
        }
    }

    private void validateCoverageTotal(InsuranceQuoteRequest request, OfferResponse offer) {
        BigDecimal offerTotal = offer.SumCoverageValue(offer.getCoverages().values());
        BigDecimal requestTotal = utils.SumCoverageValue(request.getCoverages().values());

        if (requestTotal.compareTo(offerTotal) > 0) {
            throw new RulesException("O total das coberturas solicitadas (%.2f) excede o permitido (%.2f)."
                    .formatted(requestTotal, offerTotal));
        }
    }

    private void validateCustomer(CustomerRequest customer) {
        customer.validate();
    }
}
