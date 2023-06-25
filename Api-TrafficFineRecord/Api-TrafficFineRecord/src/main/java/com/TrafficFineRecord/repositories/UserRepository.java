package com.TrafficFineRecord.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.TrafficFineRecord.entities.UserPartialReplica;

@Repository
public interface UserRepository
		extends JpaRepository<UserPartialReplica, String>, JpaSpecificationExecutor<UserPartialReplica> {

}
