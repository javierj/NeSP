package iwt2.concretesyntax.freemaker;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

public class FMTemplate {

	Template temp;
	 Configuration cfg;
	 public static String TEMP_DIR = "./resources/templates";
	
	public FMTemplate(String path) {

        /* Create and adjust the configuration singleton */
        cfg = new Configuration(Configuration.VERSION_2_3_25);
        try {
			cfg.setDirectoryForTemplateLoading(new File(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        cfg.setLogTemplateExceptions(false);

	}

	
	public FMTemplate() {
		this(TEMP_DIR);
	}
	
	
	/**
	 * Call this method before any call to process
	 * @param string
	 */
	public void setTemplateFile(String string) {
		try {
			temp = cfg.getTemplate(string);
		} catch (Exception e) {
			throw new java.lang.RuntimeException(e);
		}
	}

	/**
	 * It i better o use any specific processToX.
	 * @param root
	 * @param out
	 */
	void process(Map<String, Object> root, Writer out) {		
		try {
			temp.process(root, out);
		} catch (TemplateException | IOException e) {
			// TODO Auto-generated catch block
			throw new java.lang.RuntimeException(e);
		}
	}
	
	public String processToString(Map<String, Object> root) {
		StringWriter out = new StringWriter();
		this.process(root, out);
		return out.toString();
		
	}


	public void processToConsole(Map<String, Object> root) {
		this.process(root, new OutputStreamWriter(System.out));
	}
}
