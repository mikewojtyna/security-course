package pl.strefakursow.security.openid;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.Map;

@Controller
@RequestMapping("/openid/me")
public class OpenIdController {
	@GetMapping
	public ModelAndView me(Principal principal) {
		return new ModelAndView("openid-me",
			Map.of("username", principal.getName()));
	}
}
