package helpers;

import models.User;
import play.mvc.Http.Context;
import play.mvc.Result;
import play.mvc.Security;

public class UserFilter extends Security.Authenticator {

	@Override
	public String getUsername(Context ctx) {
		if(!ctx.session().containsKey("email"))
			return null;
		String email = ctx.session().get("email");
		User u = User.find(email);
		if (u != null)
			return u.email;
		return null;
	}
	
	@Override
	public Result onUnauthorized(Context ctx) {
		return redirect("/login");
	}
}
