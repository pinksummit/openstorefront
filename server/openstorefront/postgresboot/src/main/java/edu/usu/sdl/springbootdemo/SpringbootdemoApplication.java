package edu.usu.sdl.springbootdemo;

import edu.usu.sdl.openstorefront.common.util.StringProcessor;
import edu.usu.sdl.openstorefront.common.util.TimeUtil;
import edu.usu.sdl.openstorefront.core.entity.ApprovalStatus;
import edu.usu.sdl.springbootdemo.entity.Component;
import java.security.SecureRandom;
import java.util.Random;
import java.util.logging.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
//@ComponentScan(basePackages = {"edu.usu.sdl.springbootdemo", "edu.usu.sdl.springbootdemo.entity"})
public class SpringbootdemoApplication
{

	private static final Logger LOG = Logger.getLogger(SpringbootdemoApplication.class.getName());

	public static void main(String[] args)
	{
		SpringApplication.run(SpringbootdemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(ComponentRepository repository)
	{
		return (args) -> {

			Random rnd = new SecureRandom();

			Component component = new Component();
			component.setComponentId(StringProcessor.uniqueId());
			component.setName("Test-Spring-" + (rnd.nextInt(100)));
			component.setDescription("Test Description-Spring");
			component.setComponentType("MYType");
			component.setOrganization("TEST-Spring");
			component.setApprovalState(ApprovalStatus.APPROVED);
			component.setGuid("5555555");
			component.setLastActivityDts(TimeUtil.currentDate());
			component.setActiveStatus("A");
			component.setCreateUser("ds");
			component.setCreateDts(TimeUtil.currentDate());
			component.setUpdateUser("ds");
			component.setUpdateDts(TimeUtil.currentDate());

			repository.save(component);

			LOG.info("Component found with findAll():");
			LOG.info("-------------------------------");
			for (Component componentFound : repository.findAll()) {
				LOG.info(componentFound.getName());
			}
			LOG.info("");

		};

	}

}
