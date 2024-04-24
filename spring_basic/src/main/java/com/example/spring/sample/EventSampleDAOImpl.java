package com.example.spring.sample;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Repository
//@Primary
@Qualifier("event")
public class EventSampleDAOImpl implements SampleDAO{

}
