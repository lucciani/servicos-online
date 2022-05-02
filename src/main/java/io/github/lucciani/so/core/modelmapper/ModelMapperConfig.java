package io.github.lucciani.so.core.modelmapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

	@Bean
	public ModelMapper modelMapper() {

		var modelMapper = new ModelMapper();

		CategoriaModelMapper.modelCategoriaDepartamentoCompact(modelMapper);

		return modelMapper;
	}

}
