package msc.ais.soleerp.model.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * @author Konstantinos Raptis [kraptis at unipi.gr] on 28/2/2021.
 */
@JsonPropertyOrder({
    "formattedDate", "income"
})
public class MonthlyIncomeResponse {

    private LocalDate localDate;
    private Double income;
    private String formattedDate;
    private static final DateTimeFormatter dateTimeFormatter;

    static {
        ZoneId zone = ZoneId.of("UTC");
        dateTimeFormatter = DateTimeFormatter
            .ofPattern("MM/yyyy")
            .withZone(zone);
    }

    public MonthlyIncomeResponse() {

    }

    private MonthlyIncomeResponse(Builder builder) {
        localDate = builder.localDate;
        income = builder.income;
    }

    @JsonIgnore
    public LocalDate getLocalDate() {
        return localDate;
    }

    @JsonProperty("month")
    public String getFormattedDate() {
        if (Objects.isNull(formattedDate)) {
            formattedDate = dateTimeFormatter.format(localDate);
        }
        return formattedDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    @JsonProperty("amount")
    public Double getIncome() {
        return income;
    }

    public void setIncome(Double income) {
        this.income = income;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private LocalDate localDate;
        private Double income;

        public Builder() {
        }

        public Builder localDate(LocalDate localDate) {
            this.localDate = localDate;
            return this;
        }

        public Builder income(Double income) {
            this.income = income;
            return this;
        }

        public MonthlyIncomeResponse build() {
            return new MonthlyIncomeResponse(this);
        }
    }

    @Override
    public String toString() {
        return "MonthlyIncomeResponse{" +
            "localDate=" + localDate +
            ", income=" + income +
            '}';
    }
}
