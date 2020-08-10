package spring.ioc.applicationListener;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Client {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(Config.class);
		app.publishEvent(new ApplicationEvent("自定义事件") {
			/**  */
			private static final long serialVersionUID = 1L;

			@Override
			public Object getSource() {
				// TODO Auto-generated method stub
				return super.getSource();
			}
		});
		
		app.close();
	}
}
