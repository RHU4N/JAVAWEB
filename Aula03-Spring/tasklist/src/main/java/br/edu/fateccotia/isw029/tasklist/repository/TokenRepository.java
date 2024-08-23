package br.edu.fateccotia.isw029.tasklist.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.edu.fateccotia.isw029.tasklist.model.Token;

@Repository
public interface TokenRepository extends CrudRepository<Token, Integer> {

}
