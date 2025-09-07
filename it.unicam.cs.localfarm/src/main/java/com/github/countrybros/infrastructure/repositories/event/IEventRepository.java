package com.github.countrybros.infrastructure.repositories.event;

import com.github.countrybros.model.company.Company;
import com.github.countrybros.model.event.EventState;
import com.github.countrybros.model.user.User;
import org.springframework.data.repository.CrudRepository;
import com.github.countrybros.model.event.Event;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IEventRepository extends CrudRepository<Event, Integer> {

    List<Event> getAllByState(EventState state);

    Event findTopByOrderByIdDesc();

    boolean existsByName(String name);

    List<Event> findAllByState(EventState state);

    List<Event> findAllBySubscribers_Id(int userId);

    List<Event> findAllByOrganizer(User organizer);

    List<Event> getAllByParticipantsIsContaining(List<Company> participants);
}

