/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.usu.sdl.springbootdemo;

import edu.usu.sdl.springbootdemo.entity.Component;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author dshurtleff
 */
public interface ComponentRepository
		extends CrudRepository<Component, String>
{

	List<Component> findByName(String name);

}
