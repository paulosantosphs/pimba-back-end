package pimba.domain.park.evaluation;

import javax.validation.constraints.NotNull;

/**
 * Created by paulo on 13/05/17.
 */
public class EvaluationSaveRequest {

    @NotNull
    private Integer parkId;
    @NotNull
    private Integer stars;

    public EvaluationSaveRequest() {
    }

    public EvaluationSaveRequest(Integer parkId, Integer stars) {
        this.parkId = parkId;
        this.stars = stars;
    }

    public Integer getParkId() {
        return parkId;
    }

    public void setParkId(Integer parkId) {
        this.parkId = parkId;
    }

    public Integer getStars() {
        return stars;
    }

    public void setStars(Integer stars) {
        this.stars = stars;
    }
}
