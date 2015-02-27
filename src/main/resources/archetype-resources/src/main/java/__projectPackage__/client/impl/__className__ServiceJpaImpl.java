package ${groupId}.${projectPackage}.client.impl;

import ${groupId}.${projectPackage}.client.${className}Service;
import ${groupId}.${projectPackage}.dto.SimpleDTO;
import ${groupId}.${projectPackage}.model.SimpleModel;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;

public class ${className}ServiceJpaImpl implements ${className}Service {

	@Inject
    private EntityManager entityManager;
	
	@Override
	@Transactional
	public SimpleDTO sayHello(String name) {
		return new SimpleDTO();
	}
	
}
