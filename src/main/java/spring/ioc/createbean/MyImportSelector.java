package spring.ioc.createbean;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class MyImportSelector implements ImportSelector {

	public String[] selectImports(AnnotationMetadata importingClassMetadata) {
		//返回类的全路径信息
		return new String[]{"spring.ioc.createbean.Bean4"};
	}

}
