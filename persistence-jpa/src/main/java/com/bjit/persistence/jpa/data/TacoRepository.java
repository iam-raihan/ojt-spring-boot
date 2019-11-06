package com.bjit.persistence.jpa.data;

import com.bjit.persistence.jpa.Taco;
import org.springframework.data.repository.CrudRepository;

public interface TacoRepository 
         extends CrudRepository<Taco, Long> {

}
