package pimba.login.model.passport.facebook;

import pimba.login.model.passport.Passport;
import pimba.login.model.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(FacebookPassport.DESCRIMINATOR)
public class FacebookPassport extends Passport {
	public static final String DESCRIMINATOR = "FacebookPassport";

	@JsonIgnore
	private String accessToken;

	// constructor
	public FacebookPassport(User user, String accessToken) {
		super(user);
		this.accessToken = accessToken;
	}

	public FacebookPassport(User user) {
		super(user);
	}

	protected FacebookPassport() {
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

}
