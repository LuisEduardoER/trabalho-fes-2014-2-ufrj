package br.com.vocealuga.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Public {
	
	static final String TIPO_EPISODIO = "episodio";
	static final String TIPO_SERIE = "serie";
	static final String TIPO_GERAL = "geral";
	
	String tipo() default TIPO_GERAL;
	
}
