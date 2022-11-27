package com.tdf.tourdefrance.repositories;

import com.tdf.tourdefrance.models.Cyclist;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICyclistRepository extends ReactiveMongoRepository<Cyclist, Integer> {
}
