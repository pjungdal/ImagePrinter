package dk.jungdal;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.EmptyPanel;
import org.apache.wicket.model.StringResourceModel;

/**
 * @author cdcurry
 * @since x.x
 */
public abstract class Base extends WebPage {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Base() {


		add(new Label("pageTitle", "ImagePrinter"));
        add(new Label("buildVersion", "Version 0.1.0"));
    }

}