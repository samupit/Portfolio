package anzsjop_portfolio.portfolio;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SpotifyController {

    @RequestMapping("/")
	public @ResponseBody static void greeting() {
        System.out.println("fuckedi duck");
    }
    
}
