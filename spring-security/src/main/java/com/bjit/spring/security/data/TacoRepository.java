package com.bjit.spring.security.data;

import com.bjit.spring.security.Taco;
import org.springframework.data.repository.CrudRepository;

public interface TacoRepository 
         extends CrudRepository<Taco, Long> {

}
