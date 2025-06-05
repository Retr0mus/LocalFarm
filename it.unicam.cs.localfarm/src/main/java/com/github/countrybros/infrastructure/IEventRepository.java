package com.github.countrybros.infrastructure;

import com.github.countrybros.model.event.Event;
import org.springframework.stereotype.Repository;

@Repository
public interface IEventRepository extends IRepository<Event> {

}
