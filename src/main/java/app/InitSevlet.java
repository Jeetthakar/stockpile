package app;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class InitSevlet extends HttpServlet {
Logger Logging =Logger.getLogger(InitSevlet.class);
	private static Properties configProp = new Properties();
	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occure
	 */
	public void init() {
		InputStream in1 = this.getClass().getClassLoader().getResourceAsStream("/resources/application.properties");
		InputStream in2 = this.getClass().getClassLoader().getResourceAsStream("/resources/data.properties");
		InputStream in3 = this.getClass().getClassLoader().getResourceAsStream("/resources/database.properties");
		InputStream in4 = this.getClass().getClassLoader().getResourceAsStream("/resources/log4j.properties");
		InputStream in5 = this.getClass().getClassLoader().getResourceAsStream("/resources/IndexValidation.properties");
		InputStream in6 = this.getClass().getClassLoader().getResourceAsStream("/resources/define_and_compose_all_child_index.txt");
		InputStream in7 = this.getClass().getClassLoader().getResourceAsStream("/resources/query.properties");
		InputStream in8 = this.getClass().getClassLoader().getResourceAsStream("/resources/user_query.properties");
		InputStream in9 = this.getClass().getClassLoader().getResourceAsStream("/resources/System_config.properties");
		InputStream in10 = this.getClass().getClassLoader().getResourceAsStream("/resources/s_p_define_compose_single_child_index.txt");
		InputStream in11 = this.getClass().getClassLoader().getResourceAsStream("/resources/ftpDetails.properties");
		InputStream in12 = this.getClass().getClassLoader().getResourceAsStream("/resources/l4j3.properties");
		
        
		try {
            configProp.load(in1);
            Logging.debug(in1+" inside try....");
            configProp.load(in2);
            configProp.load(in3);
            configProp.load(in4);
            configProp.load(in5);
            configProp.load(in6);
            configProp.load(in7);
            configProp.load(in8);
            configProp.load(in9);
            configProp.load(in10);
            configProp.load(in11);
            configProp.load(in12);
        } catch (IOException e) {
        //    e.printStackTrace();
        	Logging.debug(e);
        }

		// Put your code here
		
	}

}
