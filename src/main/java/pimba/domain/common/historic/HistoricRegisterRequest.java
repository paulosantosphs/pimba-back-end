package pimba.domain.common.historic;

import javax.validation.constraints.NotNull;

/**
 * Created by paulo on 11/05/17.
 */
public class HistoricRegisterRequest {

    private Double payment;

    @NotNull
    private Integer parkId;

    @NotNull
    private Boolean botton;

    public HistoricRegisterRequest() {
    }

    public HistoricRegisterRequest(Double payment, Integer parkId, Boolean botton) {
        this.payment = payment;
        this.parkId = parkId;
        this.botton = botton;
    }

    public Double getPayment() {
        return payment;
    }

    public void setPayment(Double payment) {
        this.payment = payment;
    }

    public Integer getParkId() {
        return parkId;
    }

    public void setParkId(Integer parkId) {
        this.parkId = parkId;
    }

    public Boolean getBotton() {
        return botton;
    }

    public void setBotton(Boolean botton) {
        this.botton = botton;
    }
}

