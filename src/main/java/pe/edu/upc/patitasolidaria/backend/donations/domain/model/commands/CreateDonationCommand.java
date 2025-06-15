package pe.edu.upc.patitasolidaria.backend.donations.domain.model.commands;

import jakarta.validation.constraints.*;
import pe.edu.upc.patitasolidaria.backend.donations.domain.model.aggregates.PaymentMethod;

import java.math.BigDecimal;

public record CreateDonationCommand(
        @NotNull @Positive BigDecimal amount,
        @NotNull PaymentMethod paymentMethod,
        @NotBlank String transactionNumber,
        @NotBlank @Size(min = 2, max = 100) String donorName
) {}
