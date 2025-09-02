package com.github.countrybros.infrastructure.repositories.event;

import com.github.countrybros.model.event.Invitation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Crud repo for Invitation.
 */
@Repository
public interface IInvitationRepository extends CrudRepository<Invitation, Integer> {

    Invitation getInvitationById(int id);

    List<Invitation> findAllByReceiver_Id(int receiverId);
}
