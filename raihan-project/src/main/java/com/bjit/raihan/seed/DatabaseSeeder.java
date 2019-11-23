package com.bjit.raihan.seed;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DatabaseSeeder {

    private final ItemSeeder itemsSeeder;
    private final MenuSeeder menuSeeder;
    private final OrderSeeder orderSeeder;

    @EventListener
    public void seed(ContextRefreshedEvent event) {
        itemsSeeder.seed();
        menuSeeder.seed();
        orderSeeder.seed();
    }
}
