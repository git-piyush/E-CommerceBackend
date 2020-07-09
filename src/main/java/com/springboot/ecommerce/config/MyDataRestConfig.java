package com.springboot.ecommerce.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.core.mapping.ExposureConfiguration;
import org.springframework.data.rest.core.mapping.ExposureConfigurer;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;

import com.springboot.ecommerce.entity.Country;
import com.springboot.ecommerce.entity.Product;
import com.springboot.ecommerce.entity.ProductCategory;
import com.springboot.ecommerce.entity.State;

@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer{

	private EntityManager entityManager;
	
	@Autowired
	public MyDataRestConfig(EntityManager theEntityManager) {
		super();
		this.entityManager = theEntityManager;
	}


	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
		HttpMethod[] theUnsupportedActions = {HttpMethod.PUT, HttpMethod.POST, HttpMethod.DELETE};
		
		
		//disable HTTP method for product:put,post,delete
		disableHttpMethod(Product.class, config, theUnsupportedActions);

		
		
		//disable HTTP method for ProductCategory:put,post,delete
				disableHttpMethod(ProductCategory.class, config, theUnsupportedActions);
				
				//disable HTTP method for product:put,post,delete
				disableHttpMethod(Country.class, config, theUnsupportedActions);

				
				
				//disable HTTP method for ProductCategory:put,post,delete
						disableHttpMethod(State.class, config, theUnsupportedActions);
						
				//call an internal helper method
						exposeIds(config);
		
	}


	private void disableHttpMethod(Class theClass,RepositoryRestConfiguration config, HttpMethod[] theUnsupportedActions) {
		config.getExposureConfiguration()
				.forDomainType(theClass)
				.withItemExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions))
				.withCollectionExposure((metadata, httpMethods) -> httpMethods.disable(theUnsupportedActions));
	}


	private void exposeIds(RepositoryRestConfiguration config) {
		//expose entity ids
		//-get a list of all entity class from the entity managaer
		Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities();
		
		// -create an array of the entity type
		List<Class> entityClasses = new ArrayList<>();
		
		// -get the entity types for the entities
		for(EntityType tempEntityType : entities) {
			entityClasses.add(tempEntityType.getJavaType());
		}
		
		//expose the entity ids for the array of entity/domain types
		Class[] domainTypes = entityClasses.toArray(new Class[0]);
		config.exposeIdsFor(domainTypes);
		
		
	}

	
}
