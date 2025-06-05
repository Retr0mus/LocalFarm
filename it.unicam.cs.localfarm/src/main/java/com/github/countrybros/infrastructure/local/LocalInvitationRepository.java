package com.github.countrybros.infrastructure.local;

import com.github.countrybros.infrastructure.IInvitationRepository;
import com.github.countrybros.model.event.Invitation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LocalInvitationRepository implements IInvitationRepository {

    private final Map<Integer, Invitation> invitationRepository = new HashMap<>();


    @Override
    public Invitation get(int id) {

        return invitationRepository.get(id);
    }

    @Override
    public void delete(int id) {

        invitationRepository.remove(id);
    }

    @Override
    public void save(Invitation obj) {

        invitationRepository.put(obj.getId(), obj);
    }

    @Override
    public boolean exists(int id) {

        return invitationRepository.containsKey(id);
    }

    @Override
    public List<Invitation> getAll() {

        return new ArrayList<>(invitationRepository.values());
    }

    @Override
    public List<Invitation> getInvitationsByCompany(int companyId) {

        List<Invitation> invitations = new ArrayList<>(invitationRepository.values());

        invitations.removeIf(
                i -> i.getReciver().getId() != companyId);

        return invitations;
    }
}
