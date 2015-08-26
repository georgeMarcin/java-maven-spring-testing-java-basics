package org.mbiczak;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Simple controller.
 */
@RestController
public class CharGrouperController {
    /**
     * Service provided for the controller call.
     */
    @Autowired
    private CharGrouper grouper;

    /**
     * Controller GET call for /group.
     *
     * @param chars GET request input
     * @return GET request result
     */
    @RequestMapping(value = "/group", method = RequestMethod.GET)
    @ResponseBody
    public String group(@RequestParam String chars) {
        return grouper.group(chars);
    }
}
