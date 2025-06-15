package pe.edu.upc.patitasolidaria.backend.donations.domain.model.commands;

import pe.edu.upc.patitasolidaria.backend.donations.domain.model.aggregates.PaymentMethod;

import java.math.BigDecimal;

public record UpdateDonationCommand(
        Long id,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        String transactionNumber,
        String donorName
) {}
