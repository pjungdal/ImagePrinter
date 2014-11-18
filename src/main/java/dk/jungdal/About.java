package dk.jungdal;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;

/**
 */
public class About extends Base {
    /**
     * Constructor
     */
    public About()
    {
        Link status = new Link("status") {
            public void onClick() {
                setResponsePage(ImagePrinter.class);
            }
        };
        add(status);

        Link jobs = new Link("jobs") {
            public void onClick() {
                setResponsePage(Jobs.class);
            }
        };
        add(jobs);

        Link printere = new Link("printere") {
            public void onClick() {
                setResponsePage(Printere.class);
            }
        };
        add(printere);

        Link about = new Link("about") {
            public void onClick() {
                setResponsePage(About.class);
            }
        };
        add(about);

    }
}
