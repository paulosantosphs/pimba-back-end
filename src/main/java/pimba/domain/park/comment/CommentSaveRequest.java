package pimba.domain.park.comment;

import javax.validation.constraints.NotNull;

/**
 * Created by paulo on 13/05/17.
 */
public class CommentSaveRequest {

    @NotNull
    private Integer parkId;
    @NotNull
    private String comment;

    public CommentSaveRequest(Integer parkId, String comment) {
        this.parkId = parkId;
        this.comment = comment;
    }

    public Integer getParkId() {
        return parkId;
    }

    public void setParkId(Integer parkId) {
        this.parkId = parkId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
