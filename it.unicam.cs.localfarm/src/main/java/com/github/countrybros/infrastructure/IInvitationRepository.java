package com.github.countrybros.infrastructure;

import com.github.countrybros.model.event.Invitation;

import java.util.List;

public interface IInvitationRepository extends IRepository<Invitation> {

    List<Invitation> getInvitationsByCompany(int companyId);
}
