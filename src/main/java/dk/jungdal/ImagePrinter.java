package dk.jungdal;
import org.apache.wicket.devutils.debugbar.DebugBar;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.EmptyPanel;
import org.apache.wicket.model.StringResourceModel;
import de.agilecoders.wicket.core.markup.html.bootstrap.navigation.BootstrapPagingNavigator;
import org.apache.log4j.Logger;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PageableListView;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class ImagePrinter extends Base {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ImagePrinter() {

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
